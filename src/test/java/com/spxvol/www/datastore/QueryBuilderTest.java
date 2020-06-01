package com.spxvol.www.datastore;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.spxvol.www.model.ScreenerParams;

@SpringBootTest
class QueryBuilderTest extends QueryBuilder {


	@Test
	void test() {
		ScreenerParams sp = new ScreenerParams();
		sp.setSymbols("SPX");
		List<OptionQuote> search = super.search(sp);
		
		Assertions.assertTrue(search.size() > 0);
	}
}
