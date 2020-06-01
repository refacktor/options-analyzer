package com.spxvol.www.datastore;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

@Component
public class AggregationQuery {

	private final Logger logger = Logger.getLogger(getClass().getName());

	@PersistenceContext private EntityManager em;

	public List<AggregationSummary> summarize() {
		
		TypedQuery<AggregationSummary> query =
				em.createQuery("select new com.spxvol.www.datastore.AggregationSummary(symbol, avg(iv), min(iv), max(iv)) "
						+ "from OptionQuote group by symbol", AggregationSummary.class);
		
		return query.getResultList();
	}

}
