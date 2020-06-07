package com.spxvol.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.spxvol.math.BlackScholes.Indicator;
import com.spxvol.math.BlackScholes.RangeCode;

public class BlackScholesTest {

	private static final double TOLERANCE = 1e-9;

	@Test
	public void testInTheMoneyCall() {
		final BlackScholes blackScholes = new BlackScholes(2934.70, Indicator.C, 19, 3044.31, 100, 0.02);
		Assertions.assertEquals(10.0, blackScholes.getReverseBlackScholes(), TOLERANCE);

	}

	@Test
	public void testWithPIndicator() {
		final BlackScholes blackScholes = new BlackScholes(14, Indicator.P, 35, 236.9, 250, 0.02);
		Assertions.assertEquals(0.1847963015818032, blackScholes.getReverseBlackScholes(), TOLERANCE);

	}

	@Test
	public void testWithCIndicator() {
		final BlackScholes blackScholes = new BlackScholes(1.19, Indicator.C, 35, 236.9, 240, 0.02);
		Assertions.assertEquals(0.07713796146256777, blackScholes.getReverseBlackScholes(), TOLERANCE);
	}

	@Test
	public void testWithNoSolutionInRange() {
		final BlackScholes blackScholes = new BlackScholes(14, Indicator.P, 35, 0, 250, 0.02);
		Assertions.assertEquals(RangeCode.NO_SOLUTION.getValue(), blackScholes.getReverseBlackScholes(), TOLERANCE);
	}
}
