package com.spxvol.www.datastore;

import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class AggregationQueryTest extends AggregationQuery {

	private final Logger logger = Logger.getLogger(getClass().getName());

	@Test
	void test() {
		List<AggregationSummary> summarize = super.summarize();
		
		logger.info(summarize.toString());
	}
}
