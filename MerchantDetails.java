package com.click.online.dao.bean;

public class MerchantDetails {
   String merchantId;
   String merchantGSTNumber;
   String merchantTINNumber;
   String merchantPANNumber;
   String merchantName;	
   String merchantAddress;
   String merchantAddress2;
   String merchantCity;
   String merchantState;
   String merchantCountry;
   String merchantContactNumber;
   String merchantContactMailID;
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getMerchantAddress() {
		return merchantAddress;
	}
	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}
	public String getMerchantAddress2() {
		return merchantAddress2;
	}
	public void setMerchantAddress2(String merchantAddress2) {
		this.merchantAddress2 = merchantAddress2;
	}
	public String getMerchantCity() {
		return merchantCity;
	}
	public void setMerchantCity(String merchantCity) {
		this.merchantCity = merchantCity;
	}
	public String getMerchantState() {
		return merchantState;
	}
	public void setMerchantState(String merchantState) {
		this.merchantState = merchantState;
	}
	public String getMerchantCountry() {
		return merchantCountry;
	}
	public void setMerchantCountry(String merchantCountry) {
		this.merchantCountry = merchantCountry;
	}
	public String getMerchantContactNumber() {
		return merchantContactNumber;
	}
	public void setMerchantContactNumber(String merchantContactNumber) {
		this.merchantContactNumber = merchantContactNumber;
	}
	public String getMerchantContactMailID() {
		return merchantContactMailID;
	}
	public void setMerchantContactMailID(String merchantContactMailID) {
		this.merchantContactMailID = merchantContactMailID;
	}
	public String getMerchantGSTNumber() {
		return merchantGSTNumber;
	}
	public void setMerchantGSTNumber(String merchantGSTNumber) {
		this.merchantGSTNumber = merchantGSTNumber;
	}
	public String getMerchantTINNumber() {
		return merchantTINNumber;
	}
	public void setMerchantTINNumber(String merchantTINNumber) {
		this.merchantTINNumber = merchantTINNumber;
	}
	public String getMerchantPANNumber() {
		return merchantPANNumber;
	}
	public void setMerchantPANNumber(String merchantPANNumber) {
		this.merchantPANNumber = merchantPANNumber;
	}
	@Override
	public String toString() {
		return "MerchantDetails [merchantId=" + merchantId + ", merchantGSTNumber=" + merchantGSTNumber
				+ ", merchantTINNumber=" + merchantTINNumber + ", merchantPANNumber=" + merchantPANNumber
				+ ", merchantName=" + merchantName + ", merchantAddress=" + merchantAddress + ", merchantAddress2="
				+ merchantAddress2 + ", merchantCity=" + merchantCity + ", merchantState=" + merchantState
				+ ", merchantCountry=" + merchantCountry + ", merchantContactNumber=" + merchantContactNumber
				+ ", merchantContactMailID=" + merchantContactMailID + ", getMerchantId()=" + getMerchantId()
				+ ", getMerchantName()=" + getMerchantName() + ", getMerchantAddress()=" + getMerchantAddress()
				+ ", getMerchantAddress2()=" + getMerchantAddress2() + ", getMerchantCity()=" + getMerchantCity()
				+ ", getMerchantState()=" + getMerchantState() + ", getMerchantCountry()=" + getMerchantCountry()
				+ ", getMerchantContactNumber()=" + getMerchantContactNumber() + ", getMerchantContactMailID()="
				+ getMerchantContactMailID() + ", getMerchantGSTNumber()=" + getMerchantGSTNumber()
				+ ", getMerchantTINNumber()=" + getMerchantTINNumber() + ", getMerchantPANNumber()="
				+ getMerchantPANNumber() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}
