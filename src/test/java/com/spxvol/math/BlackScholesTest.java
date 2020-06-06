package com.spxvol.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.spxvol.math.BlackScholes.Indicator;
import com.spxvol.math.BlackScholes.RangeCode;

public class BlackScholesTest {

	private static final double DELTA = 1e-15;

	@Test
	public void testWithPIndicator() {
		final BlackScholes blackScholes = new BlackScholes(14, Indicator.P, 35, 236.9, 250, 0.02);
		Assertions.assertEquals(0.1847963015818032, blackScholes.reverse(), DELTA);

	}

	@Test
	public void testWithCIndicator() {
		final BlackScholes blackScholes = new BlackScholes(1.19, Indicator.C, 35, 236.9, 240, 0.02);
		Assertions.assertEquals(0.07713796146256777, blackScholes.reverse(), DELTA);
	}

	@Test
	public void testWithNoSolutionInRange() {
		final BlackScholes blackScholes = new BlackScholes(14, Indicator.P, 35, 0, 250, 0.02);
		Assertions.assertEquals(RangeCode.NO_SOLUTION.getValue(), blackScholes.reverse(), DELTA);
	}
}
