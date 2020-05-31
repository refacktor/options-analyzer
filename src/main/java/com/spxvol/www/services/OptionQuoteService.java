package com.spxvol.www.services;

import static java.util.stream.Collectors.groupingBy;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.uuid.Generators;
import com.google.common.collect.Lists;
import com.spxvol.www.datastore.OptionQuote;
import com.spxvol.www.datastore.OptionQuoteRepository;
import com.spxvol.www.datastore.Underlying;
import com.spxvol.www.datastore.UnderlyingRepository;

@Component
public class OptionQuoteService {

	private final UnderlyingRepository underlyingRepository;

	private final OptionQuoteRepository optionQuoteRepository;

	@Value("${spring.jpa.properties.hibernate.jdbc.batch_size}") private int batchSize;

	public OptionQuoteService(UnderlyingRepository underlyingRepository, OptionQuoteRepository optionQuoteRepository) {
		super();
		this.underlyingRepository = underlyingRepository;
		this.optionQuoteRepository = optionQuoteRepository;
	}

	public List<OptionQuote> build(JsonNode rootNode) {
		List<OptionQuote> results = new ArrayList<>();
		rootNode.withArray("options").elements().forEachRemaining(node -> {
			JsonNode optionChainResponseNode = node.get("OptionChainResponse");

			JsonNode expirationNode = node.get("Expiration");
			LocalDateTime expiration = LocalDate.of(expirationNode.get("year").asInt(),
					expirationNode.get("month").asInt(), expirationNode.get("day").asInt()).atStartOfDay();

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

	public OptionQuote build(LocalDateTime expiration, long uniquePair, JsonNode jsonNode) {

		final String symbol = jsonNode.get("symbol").asText();
		Underlying underlying = underlyingRepository.findById(symbol)
				.orElseGet(() -> underlyingRepository.save(new Underlying(symbol)));

		JsonNode optionGreeksNode = jsonNode.get("OptionGreeks");
		return new OptionQuote(uniquePair, jsonNode.get("optionCategory").asText(),
				jsonNode.get("optionRootSymbol").asText(), expiration, jsonNode.get("adjustedFlag").asBoolean(),
				jsonNode.get("displaySymbol").asText(), jsonNode.get("optionType").asText(),
				BigDecimal.valueOf(jsonNode.get("strikePrice").asDouble()), underlying,
				BigDecimal.valueOf(jsonNode.get("bid").asDouble()), BigDecimal.valueOf(jsonNode.get("ask").asDouble()),
				jsonNode.get("bidSize").asLong(), jsonNode.get("askSize").asLong(), jsonNode.get("inTheMoney").asText(),
				jsonNode.get("volume").asLong(), BigDecimal.valueOf(jsonNode.get("openInterest").asDouble()),
				BigDecimal.valueOf(jsonNode.get("netChange").asDouble()),
				BigDecimal.valueOf(jsonNode.get("lastPrice").asDouble()), jsonNode.get("quoteDetail").asText(),
				jsonNode.get("osiKey").asText(), BigDecimal.valueOf(optionGreeksNode.get("rho").asDouble()),
				BigDecimal.valueOf(optionGreeksNode.get("vega").asDouble()),
				BigDecimal.valueOf(optionGreeksNode.get("theta").asDouble()),
				BigDecimal.valueOf(optionGreeksNode.get("delta").asDouble()),
				BigDecimal.valueOf(optionGreeksNode.get("gamma").asDouble()),
				BigDecimal.valueOf(optionGreeksNode.get("iv").asDouble()),
				optionGreeksNode.get("currentValue").asBoolean());
	}

	public Map<LocalDate, Map<Long, Map<String, List<OptionQuote>>>> chainMap(String symbol) {
		List<OptionQuote> chains = optionQuoteRepository.findBySymbol(underlyingRepository.findById(symbol.toUpperCase()).get());
		Comparator<OptionQuote> strikePriceComparator = (o1, o2) -> o1.getStrikePrice().compareTo(o2.getStrikePrice());
		Comparator<OptionQuote> dateTimeComparator = (o1, o2) -> o1.getTimeStamp().compareTo(o2.getTimeStamp());
		Map<LocalDate, Map<Long, Map<String, List<OptionQuote>>>> chainMap = chains.stream()
				.sorted(strikePriceComparator).sorted(dateTimeComparator)
				.collect(groupingBy(OptionQuote::getDate, LinkedHashMap::new, groupingBy(OptionQuote::getUniquePair,
						LinkedHashMap::new, groupingBy(OptionQuote::getOptionType))));
		return chainMap;
	}

	public void saveAll(List<OptionQuote> quotes) {
		Lists.partition(quotes, batchSize).parallelStream().forEach(optionQuoteRepository::saveAll);
	}

	public List<String> allSymbols() {
		return underlyingRepository.findAll().stream().map(Underlying::getSymbol).collect(Collectors.toList());
	}
}
