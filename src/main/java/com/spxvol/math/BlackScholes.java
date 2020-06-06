package com.spxvol.math;

import static com.spxvol.math.BlackScholes.RangeCode.NO_SOLUTION;

import org.apache.commons.math3.special.Erf;

public class BlackScholes {
	private static final long N_MAX = 1000;
	private static final double a = 1e-9;
	private static final double TOLERANCE = 1e-9;
	private static final double b = 10;

	private double value;
	private Indicator indicator;
	private double t;
	private double sT;
	private double k;
	private double r;

	public static double reverse(double optionPrice, Indicator optionType, double timeToExpiry, double underlyingSpotPrice, double strikePrice, double riskFreeRate) {
		BlackScholes bs = new BlackScholes(optionPrice, optionType, timeToExpiry, underlyingSpotPrice, strikePrice, riskFreeRate);
		return bs.reverse();
	}
	
	protected BlackScholes(double optionPrice, Indicator optionType, double timeToExpiry, double underlyingSpotPrice, double strikePrice, double riskFreeRate) {
		this.value = optionPrice;
		this.indicator = optionType;
		this.t = timeToExpiry;
		this.sT = underlyingSpotPrice;
		this.k = strikePrice;
		this.r = riskFreeRate;
	}

	protected double reverse() {
		t = t / 365;
		value = getPValue(value, indicator, t, sT, k, r);

		if (a >= b || (sign(f(a)) == sign(f(b)))) {
			return NO_SOLUTION.getValue();
		}

		return getReverseBlackScholes(a, b);
	}
	
	private double getReverseBlackScholes(double a, double b) {
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
		double d1 = (Math.log(sT / k) + (r + Math.pow(sigma, 2) / 2.0) * t) / (sigma * Math.sqrt(t));
		double d2 = d1 - sigma * Math.sqrt(t);
		return n(d1) * sT - n(d2) * k * Math.exp(-r * t) - value;
	}

	private double n(double x) {
		return (1.0 + Erf.erf(x / Math.sqrt(2.0))) / 2.0;
	}

	
	private double getPValue(double value, Indicator indicator, double t, double sT, double k, double r) {
		if (indicator == Indicator.P) {
			value = value - k * Math.exp(-r * t) + sT;
		}
		return value;
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
