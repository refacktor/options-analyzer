package com.spxvol.math;

public class BlackScholesGreeks extends BlackScholes {

	
	protected BlackScholesGreeks(Indicator optionType, double timeToExpiry,
			double underlyingSpotPrice, double strikePrice, double riskFreeRate) {
		super(optionType, timeToExpiry, underlyingSpotPrice, strikePrice, riskFreeRate);
	}
	
	public static OptionGreeks getOptionGreeks(Indicator optionType, double timeToExpiry,
			double underlyingSpotPrice, double strikePrice, double riskFreeRate, double volatility) {
		BlackScholesGreeks bs = new BlackScholesGreeks(optionType, timeToExpiry, underlyingSpotPrice, strikePrice,
				riskFreeRate);
		return bs.getOptionGreeks(optionType, volatility);
	}

	public OptionGreeks getOptionGreeks(Indicator optionType, double volatility) {
		double[] array = calculate(optionType, volatility);
		double delta = array[0];
		double gamma = array[1];
		double vega = array[2];
		double theta = (array[3] / 1);
		double rho = array[4];
		return new OptionGreeks(delta, theta, rho, gamma, vega);
	}

	private double[] calculate(Indicator optionType, double volatility) {
		double[] p = new double[5];
		double d1 = d1(volatility);
		double d2 = d2(volatility);
		double sd1 = standardNormalDistribution(d1);
		
		double thetaLeft = -(underlyingSpotPrice * sd1 * volatility) / (2 * Math.sqrt(timeToExpiry));
		double thetaRight = getThetaRight(optionType, d2);

		// delta
		p[0] = getDelta(optionType, d1);
		// gamma
		p[1] = sd1 / (underlyingSpotPrice * volatility * Math.sqrt(timeToExpiry));
		// vega
		p[2] = underlyingSpotPrice * sd1 * Math.sqrt(timeToExpiry);
		// theta
		p[3] = getTheta(optionType, thetaLeft, thetaRight);
		// rho
		p[4] = getRho(optionType, d2);

		return p;
	}

	private double getDelta(Indicator optionType, double d1) {
		return Indicator.P == optionType ? n(d1) - 1 : n(d1);
	}

	private double getRho(Indicator optionType, double d2) {
		double rho = strikePrice * timeToExpiry * Math.exp(-riskFreeRate * timeToExpiry)
				* n(Indicator.P == optionType ? -d2 : d2);
		return Indicator.P == optionType ? -rho : rho;
	}

	private double getTheta(Indicator optionType, double thetaLeft, double thetaRight) {
		return Indicator.P == optionType ? thetaLeft + thetaRight : thetaLeft - thetaRight;
	}

	private double getThetaRight(Indicator optionType, double d2) {
		return riskFreeRate * strikePrice * Math.exp(-riskFreeRate * timeToExpiry)
				* n(Indicator.P == optionType ? -d2 : d2);
	}

	private double standardNormalDistribution(double sigma) {
		double top = Math.exp(-0.5 * Math.pow(sigma, 2));
		double bottom = Math.sqrt(2 * Math.PI);
		return top / bottom;
	}
}
