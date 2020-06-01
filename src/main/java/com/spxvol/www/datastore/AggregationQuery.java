package com.spxvol.www.datastore;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

@Component
public class AggregationQuery {

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

}
