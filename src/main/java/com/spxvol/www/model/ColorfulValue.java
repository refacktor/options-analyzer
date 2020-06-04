package com.spxvol.www.model;

public class ColorfulValue {

	private Double value;

	private String color;

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ColorfulValue(Double value, String color) {
		super();
		this.value = value;
		this.color = color;
	}
}