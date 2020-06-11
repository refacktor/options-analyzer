package com.spxvol.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.spxvol.math.BlackScholes.Indicator;

public class BlackScholesGreeksTest {

	
	private static final double TOLERANCE = 1e-9;
	
	@Test
	public void testOptionGreeksWithCIndictor() {
		final BlackScholesGreeks blackScholes = new BlackScholesGreeks(Indicator.C, 0.34, 56.25, 55, 0.0285);
		OptionGreeks optionGreeks = blackScholes.getOptionGreeks(Indicator.C, 0.28);
		Assertions.assertEquals(0.610, round(optionGreeks.getDelta(), 3), TOLERANCE);
		Assertions.assertEquals(0.042, round(optionGreeks.getGamma(), 3), TOLERANCE);
		Assertions.assertEquals(12.587, round(optionGreeks.getVega(), 3), TOLERANCE);
		Assertions.assertEquals(-6.030, round(optionGreeks.getTheta(), 3), TOLERANCE);
		Assertions.assertEquals(10.110, round(optionGreeks.getRho(), 3), TOLERANCE);

	}

	@Test
	public void testOptionGreeksWithPIndictor() {
		final BlackScholesGreeks blackScholes = new BlackScholesGreeks(Indicator.P, 0.34, 56.25, 55, 0.0285);
		OptionGreeks optionGreeks = blackScholes.getOptionGreeks(Indicator.P, 0.28);
		Assertions.assertEquals(-0.390, round(optionGreeks.getDelta(), 3), TOLERANCE);
		Assertions.assertEquals(0.042, round(optionGreeks.getGamma(), 3), TOLERANCE);
		Assertions.assertEquals(12.587, round(optionGreeks.getVega(), 3), TOLERANCE);
		Assertions.assertEquals(-4.478, round(optionGreeks.getTheta(), 3), TOLERANCE);
		Assertions.assertEquals(-8.409, round(optionGreeks.getRho(), 3), TOLERANCE);
	}
	
	@Test
	public void testOptionGreeksWithCIndictorDirectCall() {
		OptionGreeks optionGreeks = BlackScholesGreeks.getOptionGreeks(Indicator.C, 0.34, 56.25, 55, 0.0285, 0.28);
		Assertions.assertEquals(0.610, round(optionGreeks.getDelta(), 3), TOLERANCE);
		Assertions.assertEquals(0.042, round(optionGreeks.getGamma(), 3), TOLERANCE);
		Assertions.assertEquals(12.587, round(optionGreeks.getVega(), 3), TOLERANCE);
		Assertions.assertEquals(-6.030, round(optionGreeks.getTheta(), 3), TOLERANCE);
		Assertions.assertEquals(10.110, round(optionGreeks.getRho(), 3), TOLERANCE);
		
	}
	
	@Test
	public void testOptionGreeksWithPIndictorDirectCall() {
		OptionGreeks optionGreeks = BlackScholesGreeks.getOptionGreeks(Indicator.P, 0.34, 56.25, 55, 0.0285,0.28);
		Assertions.assertEquals(-0.390, round(optionGreeks.getDelta(), 3), TOLERANCE);
		Assertions.assertEquals(0.042, round(optionGreeks.getGamma(), 3), TOLERANCE);
		Assertions.assertEquals(12.587, round(optionGreeks.getVega(), 3), TOLERANCE);
		Assertions.assertEquals(-4.478, round(optionGreeks.getTheta(), 3), TOLERANCE);
		Assertions.assertEquals(-8.409, round(optionGreeks.getRho(), 3), TOLERANCE);
	}

	private double round(double d, int places) {
		int factor = (int) Math.pow(10, places);
		return (double) Math.round(d * factor) / factor;
	}
}
