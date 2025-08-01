package org.baseclass;

public class ProductModels {
	private String brand;
	private String model;
	public ProductModels(String brand,String model) {
		this.brand=brand;
		this.model=model;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getModel() {
		return model;
	}
}
