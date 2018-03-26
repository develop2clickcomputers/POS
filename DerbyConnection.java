package com.click.online.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.click.online.dao.bean.MerchantDetails;
import com.click.online.dao.bean.MerchantSales;

public class DerbyConnection {
	public Connection getDerbyConnection() {
		Connection connect = null;
        try {
        	 //System.out.println(Class.forName("org.apache.derby.jdbc.ClientDriver"));
    	     Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();    	     
    	     connect = DriverManager.getConnection("jdbc:derby://localhost:1527/onlinebilling");    	     
		    } catch (ClassNotFoundException cnfe) {
		    	 System.out.println("org.apache.derby.jdbc.ClientDriver Not found");
		    	 cnfe.printStackTrace();
		         connect = null;
		    }catch (Exception e) {
		           e.printStackTrace();
		           connect = null;
		    }
        return connect;
	}
	
	public MerchantDetails getMerchantDetails(Connection connect,MerchantDetails merchantDetails) {
		String sqlString = "select details.merchantId,details.merchantName,details.merchantGST,details.merchantTIN,"
				+ "details.merchantPAN,details.merchantMailID,details.merchantPhone,location.merchantAddress1,"
				+ "location.merchantAddress2,location.merchantCity,location.merchantState,location.merchantCountry "
				+ "from merchantDetails details,merchantLocationDetails location where "
				+ "details.merchantId=location.merchantId and location.merchantId = '"+merchantDetails.getMerchantId()+"'";
		System.out.println("getMerchantDetails:sqlString:"+sqlString);
		merchantDetails = getMerchantDetailsFromDB(connect,sqlString,merchantDetails);		
		return merchantDetails;	    
	}
	
	public List<MerchantSales> getMerchantSaleDetails(Connection connect,String merchantId) {
		String sqlString = "select merchantCustomerID,merchantCustomerName,merchantSaleInvoiceNumber,merchantSalesAmount,merchantSaleDate from merchantSalesDetails "
				+ "where merchantId = '"+merchantId+"'";
		System.out.println("getMerchantSaleDetails:"+sqlString);
		return getMerchantSaleDetailsFromDB(connect,sqlString);
	}
	
	public boolean loginCheck(Connection connect,String userName,String userPassword) {
		String sqlString = "SELECT * FROM loginDetails where userName ='"+userName+"' "
				+ "and userPassword ='"+userPassword+"'";
		 System.out.println(sqlString);
		return executeStatement(connect,sqlString);
	}
	
	public List<MerchantSales> getMerchantSaleDetailsFromDB(Connection connect,String sqlString) {
		List<MerchantSales> merchantSaleDetailList = new ArrayList<MerchantSales>();
		PreparedStatement statement = null;
		ResultSet resultSet = null;		
        try{
        	statement = connect.prepareStatement(sqlString);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	MerchantSales merchantSales = new MerchantSales();
            	merchantSales.setMerchantCustomerID(resultSet.getString("merchantCustomerID"));
            	merchantSales.setMerchantCustomerName(resultSet.getString("merchantCustomerName"));
            	merchantSales.setMerchantSaleInvoiceNumber(resultSet.getString("merchantSaleInvoiceNumber"));
            	merchantSales.setMerchantSalesAmount(resultSet.getString("merchantSalesAmount"));
            	merchantSales.setMerchantSaleDate(resultSet.getString("merchantSaleDate"));  
            	merchantSaleDetailList.add(merchantSales);
            }
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
	    	  try {
	                if (resultSet != null) {
	                     resultSet.close();
	                }
	                if (statement != null) {
	                     statement.close();
	                }	             
	        } catch (Exception e) {
                e.printStackTrace();
	        }
	     }		
		return merchantSaleDetailList;
	}
	
	public MerchantDetails getMerchantDetailsFromDB(Connection connect,String sqlString,MerchantDetails merchantDetails) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;		
        try{
        	statement = connect.prepareStatement(sqlString);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	merchantDetails.setMerchantName(resultSet.getString("merchantName"));
            	merchantDetails.setMerchantGSTNumber(resultSet.getString("merchantGST"));
            	merchantDetails.setMerchantTINNumber(resultSet.getString("merchantTIN"));
            	merchantDetails.setMerchantPANNumber(resultSet.getString("merchantPAN"));
            	merchantDetails.setMerchantContactMailID(resultSet.getString("merchantMailID"));            	
            	merchantDetails.setMerchantContactNumber(resultSet.getString("merchantPhone"));
            	merchantDetails.setMerchantAddress(resultSet.getString("merchantAddress1"));
            	merchantDetails.setMerchantAddress2(resultSet.getString("merchantAddress2"));
            	merchantDetails.setMerchantCity(resultSet.getString("merchantCity"));
            	merchantDetails.setMerchantState(resultSet.getString("merchantState"));
            	merchantDetails.setMerchantCountry(resultSet.getString("merchantCountry"));            	
            }
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
	    	  try {
	                if (resultSet != null) {
	                     resultSet.close();
	                }
	                if (statement != null) {
	                     statement.close();
	                }	             
	        } catch (Exception e) {
                e.printStackTrace();
	        }
	     }		
		return merchantDetails;
	}
	public boolean executeStatement(Connection connect,String sqlString) {
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		boolean status = false;
        try{
        	statement = connect.prepareStatement(sqlString);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	status = true;
            }
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
	    	  try {
	                if (resultSet != null) {
	                     resultSet.close();
	                }
	                if (statement != null) {
	                     statement.close();
	                }	             
	        } catch (Exception e) {
                e.printStackTrace();
	        }
	     }
        return status;
	}
	

	public boolean checkExistance(Connection connect, String searchStr) {
	    boolean checkStatus = false;
	    System.out.println(":checkExistance:searchStr:"+searchStr);
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		  try {
	        	statement = connect.prepareStatement(searchStr);
	            resultSet = statement.executeQuery();
	            while (resultSet.next()) {
	            	 checkStatus = true;      	
	            }
	        }catch(Exception e) {
	        	e.printStackTrace();
	        }finally {
		    	  try {
		                if (resultSet != null) {
		                        resultSet.close();
		                }
		                if (statement != null) {
		                        statement.close();
		                }	             
		        } catch (Exception e) {
	                e.printStackTrace();
		        }
		    }		
	    return checkStatus;
	}
	
	public boolean insertStatement(Connection connect,String sql) {
		Statement statement = null;		
		boolean status = false;
        try{
        	statement = connect.createStatement();
        	statement.executeUpdate(sql);
        }catch(Exception e) {
        	e.printStackTrace();
        }finally {
     	  try {	             
              if (statement != null) {
                  statement.close();
              }	  
              if(connect != null) {
            	  connect.close();
              }
	      } catch (Exception e) {
	    	  System.out.println("::insertStatement::");
              e.printStackTrace();
	      }
	    }
        return status;
	}
	

}
