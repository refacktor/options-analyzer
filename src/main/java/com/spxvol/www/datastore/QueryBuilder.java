package com.spxvol.www.datastore;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Component;

import com.spxvol.www.model.ScreenerParams;

@Component
public class QueryBuilder {

	private static final ZoneId MARKET_TIME_ZONE = ZoneId.of("America/New_York");

	private final Logger logger = Logger.getLogger(getClass().getName());

	@PersistenceContext private EntityManager em;

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

}
