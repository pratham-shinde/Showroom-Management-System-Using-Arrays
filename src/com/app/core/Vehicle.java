package com.app.core;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Accept vehicle details : chasisNo(string) : UNIQUE (PK), color(string) , 
 * basePrice(double) , manufactureDate(Date),company
 */
public class Vehicle {
	private String chasisNo;
	private Color color;
	private double basePrice;
	private Date manufactureDate;
	private String company;
	//add has a relationship between vehicle 1---->1 DeliveryAddress
	private DeliveryAddress address;
	
	public static SimpleDateFormat sdf;
	
	static {
		sdf=new SimpleDateFormat("dd/MM/yyyy");
	}

	public Vehicle(String chasisNo, Color color, double basePrice, Date manufactureDate, String company) {
		super();
		this.chasisNo = chasisNo;
		this.color = color;
		this.basePrice = basePrice;
		this.manufactureDate = manufactureDate;
		this.company = company;
	}
	
	public Vehicle(String chasisNo) {
		this.chasisNo=chasisNo;
	}

	@Override
	public String toString() {
		return "Vehicle [chasisNo=" + chasisNo + ", color=" + color + ", basePrice=" + basePrice + ", manufactureDate="
				+ sdf.format(manufactureDate) + ", company=" + company + "]";
	}
	
	public boolean equals(Object o) {
		System.out.println("In vehicle equals");
		if(o instanceof Vehicle) {
			Vehicle v=(Vehicle)o;
			return this.chasisNo.equals(v.chasisNo);
		}
			
		return false;
	}

	public Color getColor() {
		return color;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	
	
	public void linkAddress(String addressLine1, String addressLine2, String city, String state, String country,String zip) {
		this.address=new DeliveryAddress(addressLine1, addressLine2, city, state, country, zip);
		System.out.println("Delivery address assigned...!!");
	}

	public DeliveryAddress getAddress() {
		return address;
	}

	public String getCompany() {
		return company;
	}
	
	
	
	
	
	
}
