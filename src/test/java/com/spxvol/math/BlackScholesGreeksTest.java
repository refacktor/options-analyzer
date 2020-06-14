package com.spxvol.math;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.spxvol.math.BlackScholes.Indicator;

public class BlackScholesGreeksTest {

	private static final double TOLERANCE = 0.01;

	//per this site
	//http://www.soarcorp.com/black_scholes_calculator.jsp
	
	@Test
	public void testOptionGreeks() {
		final int timeToExpiry = 7; //0.0191780821917808 per year
		final double underlyingSpotPrice = 16.81;
		final double strikePrice = 21;
		final double riskFreeRate = 0.01;
		final double callOptionPrice = 2.599;
		final double putOptionPrice = 6.785;
		
		double bidIv = BlackScholes.reverse(callOptionPrice, Indicator.C, timeToExpiry, underlyingSpotPrice, strikePrice, riskFreeRate);
		double askIv = BlackScholes.reverse(putOptionPrice, Indicator.P, timeToExpiry, underlyingSpotPrice, strikePrice, riskFreeRate);
		
		Assertions.assertEquals(4.27, bidIv, 0.01);
		Assertions.assertEquals(4.27, askIv, 0.01);
		
		OptionGreeks callOptionGreeks = BlackScholesGreeks.getOptionGreeks(Indicator.C, timeToExpiry, underlyingSpotPrice,
				strikePrice, riskFreeRate, bidIv);
		
		OptionGreeks putOptionGreeks = BlackScholesGreeks.getOptionGreeks(Indicator.P, timeToExpiry, underlyingSpotPrice,
				strikePrice, riskFreeRate, bidIv);
		
		
		Assertions.assertEquals(0.468, callOptionGreeks.getDelta(), TOLERANCE);
		Assertions.assertEquals(0.040, callOptionGreeks.getGamma(), TOLERANCE);
		Assertions.assertEquals(0.101, callOptionGreeks.getRho(), TOLERANCE);
		Assertions.assertEquals(-103.108, callOptionGreeks.getTheta(), TOLERANCE);
		Assertions.assertEquals(0.926, callOptionGreeks.getVega(), TOLERANCE);

		
		Assertions.assertEquals(-0.532, putOptionGreeks.getDelta(), TOLERANCE);
		Assertions.assertEquals(0.040, putOptionGreeks.getGamma(), TOLERANCE);
		Assertions.assertEquals(-0.302, putOptionGreeks.getRho(), TOLERANCE);
		Assertions.assertEquals(-102.898, putOptionGreeks.getTheta(), TOLERANCE);
		Assertions.assertEquals(0.926, putOptionGreeks.getVega(), TOLERANCE);
	}

	@Test
	public void testCHK() {
		final int timeToExpiry = 7;
		final double underlyingSpotPrice = 16.81;
		final int strikePrice = 21;
		final double riskFreeRate = 0.01;
		final double optionPrice = 2.60;
		double iv = BlackScholes.reverse(optionPrice, Indicator.C, timeToExpiry, underlyingSpotPrice, strikePrice, riskFreeRate);
		Assertions.assertEquals(4.27, iv, 0.01);
		OptionGreeks optionGreeks = BlackScholesGreeks.getOptionGreeks(Indicator.C, timeToExpiry, underlyingSpotPrice, strikePrice, riskFreeRate, iv);
		Assertions.assertEquals(0.468, optionGreeks.getDelta(), 0.001);
	}
	
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
		Assertions.assertEquals(0.042, optionGreeks.getGamma(), TOLERANCE);
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
		Assertions.assertEquals(0.042, optionGreeks.getGamma(), TOLERANCE);
		Assertions.assertEquals(12.587, optionGreeks.getVega(), TOLERANCE);
		Assertions.assertEquals(-4.478, optionGreeks.getTheta(), TOLERANCE);
		Assertions.assertEquals(-8.409, optionGreeks.getRho(), TOLERANCE);
	}
}
