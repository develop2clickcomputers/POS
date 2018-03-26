package com.click.web.model;

public class ProductDetails {
	
    String productId;
    String productName;
    String productPrice;
    String productDesc;
    String productSaleOrderId;
    
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductSaleOrderId() {
		return productSaleOrderId;
	}
	public void setProductSaleOrderId(String productSaleOrderId) {
		this.productSaleOrderId = productSaleOrderId;
	}
	@Override
	public String toString() {
		return "ProductDetails [productId=" + productId + ", productName=" + productName + ", productPrice="
				+ productPrice + ", productDesc=" + productDesc + ", productSaleOrderId=" + productSaleOrderId + "]";
	}
	
}
