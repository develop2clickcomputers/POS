package com.click.online.dao.bean;

public class MerchantSales {
    
	private String merchantSaleInvoiceNumber;
	private String merchantSalesAmount;
	private String merchantCustomerName;
	private String merchantCustomerID;
	private String merchantSaleDate;
   
	public String getMerchantSaleInvoiceNumber() {
		return merchantSaleInvoiceNumber;
	}
	public void setMerchantSaleInvoiceNumber(String merchantSaleInvoiceNumber) {
		this.merchantSaleInvoiceNumber = merchantSaleInvoiceNumber;
	}
	public String getMerchantSalesAmount() {
		return merchantSalesAmount;
	}
	public void setMerchantSalesAmount(String merchantSalesAmount) {
		this.merchantSalesAmount = merchantSalesAmount;
	}
	public String getMerchantCustomerName() {
		return merchantCustomerName;
	}
	public void setMerchantCustomerName(String merchantCustomerName) {
		this.merchantCustomerName = merchantCustomerName;
	}
	public String getMerchantCustomerID() {
		return merchantCustomerID;
	}
	public void setMerchantCustomerID(String merchantCustomerID) {
		this.merchantCustomerID = merchantCustomerID;
	}
	public String getMerchantSaleDate() {
		return merchantSaleDate;
	}
	public void setMerchantSaleDate(String merchantSaleDate) {
		this.merchantSaleDate = merchantSaleDate;
	}
	
	@Override
	public String toString() {
		return "MerchantSales [merchantSaleInvoiceNumber=" + merchantSaleInvoiceNumber + ", merchantSalesAmount="
				+ merchantSalesAmount + ", merchantCustomerName=" + merchantCustomerName + ", merchantCustomerID="
				+ merchantCustomerID + ", merchantSaleDate=" + merchantSaleDate + "]";
	}    

}