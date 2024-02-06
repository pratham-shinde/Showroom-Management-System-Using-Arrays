package com.app.core;

public enum Color {
	WHITE(0),BLACK(10000),GREY(6000),SILVER(7000),RED(5000);
	
	private double additionalCost;
	
	Color(double additionalCost){
		this.additionalCost=additionalCost;
	}

	public double getAdditionalCost() {
		return additionalCost;
	}

	public void setAdditionalCost(double additionalCost) {
		this.additionalCost = additionalCost;
	}
	
}
