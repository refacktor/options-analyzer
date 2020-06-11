package com.spxvol.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.spxvol.math.BlackScholes.Indicator;

public class BlackScholesGreeksTest {

	private static final double TOLERANCE = 0.01;
	
	@Test
	public void testOptionGreeksWithCIndictor() {
		final BlackScholesGreeks blackScholes = new BlackScholesGreeks(Indicator.C, 124.1, 56.25, 55, 0.0285);
		OptionGreeks optionGreeks = blackScholes.getOptionGreeks(Indicator.C, 0.28);
		Assertions.assertEquals(0.610, optionGreeks.getDelta(), TOLERANCE);
		Assertions.assertEquals(0.042, optionGreeks.getGamma(), TOLERANCE);
		Assertions.assertEquals(12.587, optionGreeks.getVega(), TOLERANCE);
		Assertions.assertEquals(-6.030, optionGreeks.getTheta(), TOLERANCE);
		Assertions.assertEquals(10.110, optionGreeks.getRho(), TOLERANCE);
	}

	@Test
	public void testOptionGreeksWithPIndictor() {
		final BlackScholesGreeks blackScholes = new BlackScholesGreeks(Indicator.P, 124.1, 56.25, 55, 0.0285);
		OptionGreeks optionGreeks = blackScholes.getOptionGreeks(Indicator.P, 0.28);
		Assertions.assertEquals(-0.390, optionGreeks.getDelta(), TOLERANCE);
		Assertions.assertEquals(0.042,  optionGreeks.getGamma(), TOLERANCE);
		Assertions.assertEquals(12.587, optionGreeks.getVega(), TOLERANCE);
		Assertions.assertEquals(-4.478, optionGreeks.getTheta(), TOLERANCE);
		Assertions.assertEquals(-8.409, optionGreeks.getRho(), TOLERANCE);
	}

	@Test
	public void testOptionGreeksWithCIndictorDirectCall() {
		OptionGreeks optionGreeks = BlackScholesGreeks.getOptionGreeks(Indicator.C, 124.1, 56.25, 55, 0.0285, 0.28);
		Assertions.assertEquals(0.610, optionGreeks.getDelta(), TOLERANCE);
		Assertions.assertEquals(0.042, optionGreeks.getGamma(), TOLERANCE);
		Assertions.assertEquals(12.587, optionGreeks.getVega(), TOLERANCE);
		Assertions.assertEquals(-6.030, optionGreeks.getTheta(), TOLERANCE);
		Assertions.assertEquals(10.110, optionGreeks.getRho(), TOLERANCE);

	}

	@Test
	public void testOptionGreeksWithPIndictorDirectCall() {
		OptionGreeks optionGreeks = BlackScholesGreeks.getOptionGreeks(Indicator.P, 124.1, 56.25, 55, 0.0285, 0.28);
		Assertions.assertEquals(-0.390, optionGreeks.getDelta(), 0.01);
		Assertions.assertEquals(0.042,  optionGreeks.getGamma(), TOLERANCE);
		Assertions.assertEquals(12.587, optionGreeks.getVega(), TOLERANCE);
		Assertions.assertEquals(-4.478, optionGreeks.getTheta(), TOLERANCE);
		Assertions.assertEquals(-8.409, optionGreeks.getRho(), TOLERANCE);
	}
}
