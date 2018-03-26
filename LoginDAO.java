package com.click.online.dao;

import java.util.ArrayList;
import java.util.List;

import com.click.online.dao.bean.MerchantSales;
import com.click.online.db.DerbyConnection;
public class LoginDAO {
    String userName;
    String userPassword;
    
    public LoginDAO() {
    	
    }

    public boolean checkLogin(String userName,String userPassword) {
    	setUserName(userName);
    	setUserPassword(userPassword);
    	DerbyConnection con = new DerbyConnection();    	
    	return con.loginCheck(con.getDerbyConnection(), getUserName(), getUserPassword());
    }
    
   public List<MerchantSales> getSaleDetailsByMerchantID(String userName) {
	   List<MerchantSales> saleList = new ArrayList<MerchantSales>();
	   MerchantSales merchantSales = new MerchantSales();
	   merchantSales.setMerchantCustomerID("147852");
	   merchantSales.setMerchantCustomerName("Sa Ra");
	   merchantSales.setMerchantSaleDate("20-02-2018");
	   merchantSales.setMerchantSaleInvoiceNumber("INV124578");
	   merchantSales.setMerchantSalesAmount("10");
	   saleList.add(merchantSales);
	   merchantSales = new MerchantSales();
	   merchantSales.setMerchantCustomerID("147853");
	   merchantSales.setMerchantCustomerName("Va Ra");
	   merchantSales.setMerchantSaleDate("20-02-2018");
	   merchantSales.setMerchantSaleInvoiceNumber("INV124579");
	   merchantSales.setMerchantSalesAmount("15");
	   saleList.add(merchantSales);
	   return saleList;
   }
    
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
}
