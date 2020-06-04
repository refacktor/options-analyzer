package com.spxvol.www.services;

import static java.util.stream.Collectors.groupingBy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.uuid.Generators;
import com.google.common.collect.Lists;
import com.spxvol.www.datastore.AggregationSummary;
import com.spxvol.www.datastore.OptionQuote;
import com.spxvol.www.datastore.OptionQuoteRepository;
import com.spxvol.www.datastore.Underlying;
import com.spxvol.www.datastore.UnderlyingRepository;
import com.spxvol.www.model.Heatmap;
import com.spxvol.www.model.ScreenerParams;

@Component
public class OptionQuoteService {

	private final UnderlyingRepository underlyingRepository;

	private final OptionQuoteRepository optionQuoteRepository;

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}") private int batchSize;

	private static final ZoneId MARKET_TIME_ZONE = ZoneId.of("America/New_York");

	@PersistenceContext private EntityManager em;

	private final Logger logger = Logger.getLogger(getClass().getName());

	public OptionQuoteService(UnderlyingRepository underlyingRepository,
			OptionQuoteRepository optionQuoteRepository) {
		super();
		this.underlyingRepository = underlyingRepository;
		this.optionQuoteRepository = optionQuoteRepository;
	}

	public List<OptionQuote> build(JsonNode rootNode) {
		List<OptionQuote> results = new ArrayList<>();
		rootNode.withArray("options").elements().forEachRemaining(node -> {
			JsonNode optionChainResponseNode = node.get("OptionChainResponse");

			JsonNode expirationNode = node.get("Expiration");
			LocalDate expiration = LocalDate.of(expirationNode.get("year").asInt(),
					expirationNode.get("month").asInt(), expirationNode.get("day").asInt());

			optionChainResponseNode.withArray("OptionPair").elements().forEachRemaining(optionPairNode -> {
				long uniquePair = Generators.timeBasedGenerator().generate().timestamp();
				JsonNode callNode = optionPairNode.get("Call");
				JsonNode putNode = optionPairNode.get("Put");
				results.add(this.build(expiration, uniquePair, callNode));
				results.add(this.build(expiration, uniquePair, putNode));
			});
		});
		return results;
	}

	public OptionQuote build(LocalDate expiration, long uniquePair, JsonNode jsonNode) {

		final String symbol = jsonNode.get("symbol").asText();
		Underlying underlying = underlying(symbol);

		JsonNode optionGreeksNode = jsonNode.get("OptionGreeks");
		Function<String, BigDecimal> greek = field -> {
			final double asDouble = optionGreeksNode.get(field).asDouble();
			return asDouble < 0.0 || asDouble > 1.0 ? null : BigDecimal.valueOf(asDouble);
		};

		return new OptionQuote(uniquePair, jsonNode.get("optionCategory").asText(),
				jsonNode.get("optionRootSymbol").asText(), expiration, jsonNode.get("adjustedFlag").asBoolean(),
				jsonNode.get("displaySymbol").asText(), jsonNode.get("optionType").asText(),
				BigDecimal.valueOf(jsonNode.get("strikePrice").asDouble()), underlying,
				BigDecimal.valueOf(jsonNode.get("bid").asDouble()), BigDecimal.valueOf(jsonNode.get("ask").asDouble()),
				jsonNode.get("bidSize").asLong(), jsonNode.get("askSize").asLong(), jsonNode.get("inTheMoney").asText(),
				jsonNode.get("volume").asLong(), jsonNode.get("openInterest").asInt(),
				BigDecimal.valueOf(jsonNode.get("netChange").asDouble()),
				BigDecimal.valueOf(jsonNode.get("lastPrice").asDouble()), jsonNode.get("quoteDetail").asText(),
				jsonNode.get("osiKey").asText(), greek.apply("rho"), greek.apply("vega"), greek.apply("theta"),
				greek.apply("delta"), greek.apply("gamma"), greek.apply("iv"),
				optionGreeksNode.get("currentValue").asBoolean());
	}

	private Underlying underlying(final String symbol) {
		return underlyingRepository.findById(symbol).orElseGet(() -> underlyingRepository.save(new Underlying(symbol)));
	}

	public Map<LocalDate, Map<Long, Map<String, List<OptionQuote>>>> chainMap(String symbol) {
		List<OptionQuote> chains = optionQuoteRepository
				.findBySymbol(underlyingRepository.findById(symbol.toUpperCase()).get().getSymbol());
		Comparator<OptionQuote> strikePriceComparator = (o1, o2) -> o1.getStrikePrice().compareTo(o2.getStrikePrice());
		Comparator<OptionQuote> dateTimeComparator = (o1, o2) -> o1.getExpiration().compareTo(o2.getExpiration());
		Map<LocalDate, Map<Long, Map<String, List<OptionQuote>>>> chainMap = chains.stream()
				.sorted(strikePriceComparator).sorted(dateTimeComparator)
				.collect(groupingBy(OptionQuote::getDate, LinkedHashMap::new, groupingBy(OptionQuote::getUniquePair,
						LinkedHashMap::new, groupingBy(OptionQuote::getOptionType))));
		return chainMap;
	}

	public void saveAll(String symbol, List<OptionQuote> quotes) {
		optionQuoteRepository.deleteAll(optionQuoteRepository.findBySymbol(symbol));
		Lists.partition(quotes, batchSize).parallelStream().forEach(optionQuoteRepository::saveAll);
	}

	public List<AggregationSummary> aggregation() {
		return this.summarize();
	}

	public List<String> allSymbols() {
		return underlyingRepository.findAll().stream().map(Underlying::getSymbol).collect(Collectors.toList());
	}

	public List<OptionQuote> allOptions() {
		return optionQuoteRepository.findAll();
	}

	public List<OptionQuote> search(ScreenerParams params) {
		
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<OptionQuote> cq = cb.createQuery(OptionQuote.class);
		final Root<OptionQuote> from = cq.from(OptionQuote.class);
		final List<String> symbols = Arrays.asList(params.getSymbols().split("[\\s\\,]+"));
		Predicate predicate = from.get("symbol").in(symbols);
	
		if(params.getMinDelta() != null) {
			predicate = cb.and(predicate,
					cb.or(cb.greaterThan(from.get("delta"), params.getMinDelta()),
			              cb.lessThan(from.get("delta"), -params.getMinDelta())));
		}
		if(params.getMaxDelta() != null) {
			predicate = cb.and(predicate,
					cb.and(cb.lessThan(from.get("delta"), params.getMaxDelta()),
			               cb.greaterThan(from.get("delta"), -params.getMaxDelta())));
		}
	
		if(params.getMinDays() != null) {
			LocalDate minDay = LocalDate.now(MARKET_TIME_ZONE).plusDays(params.getMinDays());
			predicate = cb.and(predicate, cb.greaterThanOrEqualTo(from.get("expiration"), minDay));
		}
		if(params.getMaxDays() != null) {
			LocalDate maxDay = LocalDate.now(MARKET_TIME_ZONE).plusDays(params.getMaxDays());
			predicate = cb.and(predicate, cb.lessThanOrEqualTo(from.get("expiration"), maxDay));
		}
		
		cq.select(from).where(predicate);
		return em.createQuery(cq).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<AggregationSummary> summarize() {
	
		Query query = em.createNativeQuery(
				"select symbol, median(iv) as average_implied_volatility, "
				+ "min(iv) as lowest_implied_volatility, "
				+ "max(iv) as highest_implied_volatility "
				+ "from OPTION_QUOTE group by symbol",
				AggregationSummary.class);
	
		return query.getResultList();
	}
	
	public Heatmap heatmap(String symbol, boolean skipStrikes) {
		List<OptionQuote> chains = optionQuoteRepository
				.findBySymbol(underlyingRepository.findById(symbol.toUpperCase()).get().getSymbol());
		return new Heatmap(chains, skipStrikes);
	}

}
