package com.spxvol.math;

import static com.spxvol.math.BlackScholes.RangeCode.NO_SOLUTION;

import org.apache.commons.math3.special.Erf;

public class BlackScholes {
	private static final long N_MAX = 1000;
	private static final double TOLERANCE = 1e-9;

	private double a = 1e-9;
	private double b = 10 + TOLERANCE;

	final private double callValue;
	final private Indicator indicator;
	final private double timeToExpiry;
	final private double underlyingSpotPrice;
	final private double strikePrice;
	final private double riskFreeRate;

	public static double reverse(double optionPrice, Indicator optionType, double timeToExpiry, double underlyingSpotPrice, double strikePrice, double riskFreeRate) {
		BlackScholes bs = new BlackScholes(optionPrice, optionType, timeToExpiry, underlyingSpotPrice, strikePrice, riskFreeRate);
		return bs.getReverseBlackScholes();
	}
	
	protected BlackScholes(double optionPrice, Indicator optionType, double timeToExpiry, double underlyingSpotPrice, double strikePrice, double riskFreeRate) {
		this.indicator = optionType;
		this.timeToExpiry = timeToExpiry / 365;
		this.underlyingSpotPrice = underlyingSpotPrice;
		this.strikePrice = strikePrice;
		this.riskFreeRate = riskFreeRate;
		
		if (indicator == Indicator.P && optionPrice != 0) {
			// put-call parity
			this.callValue = optionPrice - strikePrice * Math.exp(-riskFreeRate * timeToExpiry) + underlyingSpotPrice;
		}
		else {
			this.callValue = optionPrice;			
		}
	}

	protected double getReverseBlackScholes() {
		
		if(callValue == 0) {
			return 0;
		}
		if(sign(f(a)) == sign(f(b))) {
			return 0;
		}
		
		int n = 0;
		while (n < N_MAX) {
			double c = (a + b) / 2;

			if (f(c) == 0 || ((b - a) / 2 < TOLERANCE)) {
				return c;
			}
			n += 1;
			if (sign(f(c)) == sign(f(a))) {
				a = c;
			} else {
				b = c;
			}
		}
		
		return NO_SOLUTION.getValue();
	}

	private double f(double sigma) {
		double d1 = (Math.log(underlyingSpotPrice / strikePrice) + (riskFreeRate + Math.pow(sigma, 2) / 2.0) * timeToExpiry) / (sigma * Math.sqrt(timeToExpiry));
		double d2 = d1 - sigma * Math.sqrt(timeToExpiry);
		return n(d1) * underlyingSpotPrice - n(d2) * strikePrice * Math.exp(-riskFreeRate * timeToExpiry) - callValue;
	}

	private double n(double x) {
		return (1.0 + Erf.erf(x / Math.sqrt(2.0))) / 2.0;
	}

	
	private double sign(double x) {
		return Math.signum(x);
	}

	public enum Indicator {
		P('P'), C('C');
		char indicator;

		Indicator(char indicator) {
			this.indicator = indicator;
		}

		public char getIndicator() {
			return indicator;
		}
	}

	public enum RangeCode {
		NO_SOLUTION(-1);
		int value;

		RangeCode(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	}
}
