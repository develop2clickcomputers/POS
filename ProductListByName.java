package com.click.online.dao.bean;

public class ProductListByName {
	
	public int id;
	String productName;
	double productPrice;
	
	public ProductListByName(int id,String productName,double productPrice) {
		this.id = id;
		this.productName = productName;
		this.productPrice = productPrice;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

}
