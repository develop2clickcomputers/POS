package com.click.online.dao;
import java.util.ArrayList;
import java.util.List;

import com.click.online.dao.bean.MerchantDetails;
import com.click.online.dao.bean.MerchantSales;
import com.click.online.db.DerbyConnection;
public class MechantDetailsDAO {
    public MerchantDetails getMerchantDetails(String merchantId) {
    	MerchantDetails merchantDetails = new MerchantDetails(); 
    	merchantDetails.setMerchantId(merchantId);
    	DerbyConnection con = new DerbyConnection(); 
    	merchantDetails = con.getMerchantDetails(con.getDerbyConnection(), merchantDetails);
    	System.out.println(merchantDetails.toString());
    	return merchantDetails;
    }
    public List<MerchantSales> getMerchantSaleDetails(String merchantId) {
    	List<MerchantSales> merchantSaleDetailList = new ArrayList<MerchantSales>();
    	DerbyConnection con = new DerbyConnection(); 
    	merchantSaleDetailList = con.getMerchantSaleDetails(con.getDerbyConnection(),merchantId);
    	System.out.println(merchantSaleDetailList);
		return merchantSaleDetailList;
    }
}
