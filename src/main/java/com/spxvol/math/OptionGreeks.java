package com.spxvol.math;

public class OptionGreeks {
	private final double delta;
	private final double theta;
	private final double rho;
	private final double gamma;
	private final double vega;

	public OptionGreeks(double delta, double theta, double rho, double gamma, double vega) {
		super();
		this.delta = delta;
		this.theta = theta;
		this.rho = rho;
		this.gamma = gamma;
		this.vega = vega;
	}

	public double getDelta() {
		return delta;
	}

	public double getTheta() {
		return theta;
	}

	public double getRho() {
		return rho;
	}

	public double getGamma() {
		return gamma;
	}

	public double getVega() {
		return vega;
	}

	@Override
	public String toString() {
		return "OptionGreeks [delta=" + delta + ", theta=" + theta + ", rho=" + rho + ", gamma=" + gamma + ", vega="
				+ vega + "]";
	}

}
