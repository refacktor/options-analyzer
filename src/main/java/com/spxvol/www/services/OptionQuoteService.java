package com.spxvol.www.services;

import static java.util.stream.Collectors.groupingBy;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.spxvol.www.datastore.AggregationSummary;
import com.spxvol.www.datastore.OptionQuote;
import com.spxvol.www.datastore.OptionQuoteRepository;
import com.spxvol.www.datastore.Underlying;
import com.spxvol.www.datastore.UnderlyingRepository;
import com.spxvol.www.model.Constants;
import com.spxvol.www.model.Heatmap;
import com.spxvol.www.model.ScreenerParams;

@Component
public class OptionQuoteService {

	private final UnderlyingRepository underlyingRepository;

	private final OptionQuoteRepository optionQuoteRepository;

	@PersistenceContext private EntityManager em;

	private final Logger logger = Logger.getLogger(getClass().getName());

	public OptionQuoteService(UnderlyingRepository underlyingRepository,
			OptionQuoteRepository optionQuoteRepository) {
		super();
		this.underlyingRepository = underlyingRepository;
		this.optionQuoteRepository = optionQuoteRepository;
	}

	public Map<LocalDate, Map<String, Map<String, List<OptionQuote>>>> chainMap(String symbol) {
		List<OptionQuote> chains = optionQuoteRepository
				.findBySymbol(underlyingRepository.findById(symbol.toUpperCase()).get().getSymbol());
		Comparator<OptionQuote> strikePriceComparator = (o1, o2) -> o1.getStrikePrice().compareTo(o2.getStrikePrice());
		Comparator<OptionQuote> dateTimeComparator = (o1, o2) -> o1.getExpiration().compareTo(o2.getExpiration());
		Map<LocalDate, Map<String, Map<String, List<OptionQuote>>>> chainMap = chains.stream()
				.sorted(strikePriceComparator).sorted(dateTimeComparator)
				.collect(groupingBy(OptionQuote::getDate, LinkedHashMap::new, groupingBy(o -> o.getExpiration() + " " + o.getStrikePrice(),
						LinkedHashMap::new, groupingBy(OptionQuote::getOptionType))));
		return chainMap;
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
	
		if(params.getMinProbability() != null) {
			predicate = cb.and(predicate, 
							cb.greaterThanOrEqualTo(from.get("probability"), params.getMinProbability()));
		}
		if(params.getMaxProbability() != null) {
			predicate = cb.and(predicate, 
							cb.lessThanOrEqualTo(from.get("probability"), params.getMaxProbability()));
		}
	
		if(params.getMinDays() != null) {
			LocalDate minDay = LocalDate.now(Constants.MARKET_TIME_ZONE).plusDays(params.getMinDays());
			predicate = cb.and(predicate, cb.greaterThanOrEqualTo(from.get("expiration"), minDay));
		}
		if(params.getMaxDays() != null) {
			LocalDate maxDay = LocalDate.now(Constants.MARKET_TIME_ZONE).plusDays(params.getMaxDays());
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
		final Underlying underlying = underlyingRepository.findById(symbol.toUpperCase()).get();
		List<OptionQuote> chains = optionQuoteRepository.findBySymbol(underlying.getSymbol());
		return new Heatmap(underlying, chains, skipStrikes);
	}

}
