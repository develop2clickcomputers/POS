package com.click.online;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.click.online.dao.LoginDAO;
import com.click.online.dao.MechantDetailsDAO;
import com.click.online.dao.bean.MerchantDetails;
import com.click.online.dao.bean.MerchantSales;
import com.click.online.dao.bean.MerchantSalesJsonObject;
import com.click.online.form.model.Login;
import com.click.online.form.model.SaleCall;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/*
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);		
		String formattedDate = dateFormat.format(date);		
		model.addAttribute("serverTime", formattedDate );		
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		logger.info("Get Login! The client locale is {}.", locale);
		model.addAttribute("message", "" );
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Locale locale, Model model) {
		logger.info("Get Login! The client locale is {}.", locale);
		model.addAttribute("message", "" );
		return "login";
	}
	
	@RequestMapping(value = "/logoutPost", method = RequestMethod.POST)
	public String logoutPost(Locale locale, Model model) {
		logger.info("Get Login! The client locale is {}.", locale);
		model.addAttribute("message", "" );
		return "login";
	}	
	
	@RequestMapping(value = "/saleCall", method = RequestMethod.POST)
	public String callSale(Locale locale, @ModelAttribute("saleCall") @Validated SaleCall saleCall,Model model) {
		String viewName ="makeSale";
		logger.info("Post callSale! The client locale is {}.", locale);
		logger.info("Post callSale! View Name is {}.", viewName);
		logger.info("Post callSale! Merchant Name is {}.", saleCall.getMerchantName());
		model.addAttribute("merchantName", saleCall.getMerchantName());	
		return viewName;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(Locale locale, @ModelAttribute("userLogin") @Validated Login login,Model model) {
		String viewName ="login";
		LoginDAO loginDAO = new LoginDAO();
		logger.info("Post Login! The client locale is {}.", locale);
		logger.info("Post Login! UserName {}.", login.getUserName());
		logger.info("Post Login! UserPassword {}.", login.getUserPassword());
		if(loginDAO.checkLogin(login.getUserName(), login.getUserPassword())) {
			MechantDetailsDAO mechantDetailsDAO = new MechantDetailsDAO();
			MerchantDetails merchantDetails = mechantDetailsDAO.getMerchantDetails(login.getUserName());
			model.addAttribute("userName", merchantDetails.getMerchantId());
			model.addAttribute("storeName", merchantDetails.getMerchantName());	
			model.addAttribute("storeCity", merchantDetails.getMerchantCity());	
			List<MerchantSales> saleList = loginDAO.getSaleDetailsByMerchantID(login.getUserName());
			model.addAttribute("storeSaleList", saleList);	
			viewName = "salesStoreHome";
		} else {
			model.addAttribute("message", "Invalid Login" );
		}
		return viewName;
	}
	
	/*
	 * Datatable
	 * 
	 */
	
	 @RequestMapping(value = "/merchantSalesList", method = RequestMethod.GET, produces = "application/json")
	 public @ResponseBody String merchantSalesList(HttpServletRequest  request) throws IOException {
			//Get Store User Name
		   String storeUserName = "M10134582";
		   if (null != request.getParameter("storeUserName")) {
			   storeUserName = request.getParameter("storeUserName");
			   System.out.println("merchantSalesList:storeUserName:"+storeUserName);
		   }
		   System.out.println("merchantSalesList:storeUserName:full:"+storeUserName);
	    	//Fetch the page number from client
	    	Integer pageNumber = 0;
	    	if (null != request.getParameter("iDisplayStart"))
	    		pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/10)+1;		
	    	
	    	//Fetch search parameter
	    	String searchParameter = request.getParameter("sSearch");
	    	
	    	//Fetch Page display length
	    	Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
	    	
	    	//Create page list data
	    	List<MerchantSales> merchantSaleList = createPaginationData(pageDisplayLength,storeUserName);
	    	System.out.println("merchantSalesList:merchantSaleList:"+merchantSaleList);
			//Here is server side pagination logic. Based on the page number you could make call 
			//to the data base create new list and send back to the client. For demo I am shuffling 
			//the same list to show data randomly
			if (pageNumber == 1) {
				Collections.shuffle(merchantSaleList);
			}else if (pageNumber == 2) {
				Collections.shuffle(merchantSaleList);
			}else {
				Collections.shuffle(merchantSaleList);
			}
			
			//Search functionality: Returns filtered list based on search parameter
			merchantSaleList = getListBasedOnSearchParameter(searchParameter,merchantSaleList);
			
			
			MerchantSalesJsonObject merchantSalesJsonObject = new MerchantSalesJsonObject();
			//Set Total display record
			merchantSalesJsonObject.setiTotalDisplayRecords(500);
			//Set Total record
			merchantSalesJsonObject.setiTotalRecords(500);
			merchantSalesJsonObject.setAaData(merchantSaleList);
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String responseData = gson.toJson(merchantSalesJsonObject);
			System.out.println("merchantSalesList:responseData:"+responseData);
	     return responseData;
	 }
	 
		private List<MerchantSales> getListBasedOnSearchParameter(String searchParameter,List<MerchantSales> merchantSalesList) {
			
			if (null != searchParameter && !searchParameter.equals("")) {
				List<MerchantSales> merchantSalesListForSearch = new ArrayList<MerchantSales>();
				searchParameter = searchParameter.toUpperCase();
				for (MerchantSales merchantSales : merchantSalesList) {
					if (merchantSales.getMerchantCustomerID().toUpperCase().indexOf(searchParameter)!= -1 || 
						merchantSales.getMerchantCustomerName().toUpperCase().indexOf(searchParameter)!= -1 || 
						merchantSales.getMerchantSaleInvoiceNumber().toUpperCase().indexOf(searchParameter)!= -1 || 
						merchantSales.getMerchantSalesAmount().toUpperCase().indexOf(searchParameter)!= -1) {
						merchantSalesListForSearch.add(merchantSales);					
					}					
				}
				merchantSalesList = merchantSalesListForSearch;
				merchantSalesListForSearch = null;
			}
			return merchantSalesList;
		}
	 
		private List<MerchantSales> createPaginationData(Integer pageDisplayLength,String merchantId) {
			List<MerchantSales> merchantSalesList = new ArrayList<MerchantSales>();
			MechantDetailsDAO mechantDetailsDAO = new MechantDetailsDAO();
			merchantSalesList = mechantDetailsDAO.getMerchantSaleDetails(merchantId);
			/*String merchantCustomerID = "ABCDE12345";
			String merchantCustomerName = "Abcde";
			String merchantSaleInvoiceNumber = "Abcde135792468";
			String merchantSalesAmount = "200";
			for (int i = 0; i < 1; i++) {
			    MerchantSales merchantSales = new MerchantSales();
			    merchantSales.setMerchantCustomerID(merchantCustomerID);	
			    merchantSales.setMerchantCustomerName(merchantCustomerName);
			    merchantSales.setMerchantSaleInvoiceNumber(merchantSaleInvoiceNumber);
			    merchantSales.setMerchantSalesAmount(merchantSalesAmount);
			    merchantSalesList.add(merchantSales);
			    
			    merchantCustomerID = "ABCDE123456";
				merchantCustomerName = "Abcdef";
				merchantSaleInvoiceNumber = "Abcdef1357924689";
				merchantSalesAmount = "300";
			    merchantSales = new MerchantSales();
			    merchantSales.setMerchantCustomerID(merchantCustomerID);	
			    merchantSales.setMerchantCustomerName(merchantCustomerName);
			    merchantSales.setMerchantSaleInvoiceNumber(merchantSaleInvoiceNumber);
			    merchantSales.setMerchantSalesAmount(merchantSalesAmount);
			    merchantSalesList.add(merchantSales);
			    
			    merchantCustomerID = "ABCDE123457";
				merchantCustomerName = "Abcdeg";
				merchantSaleInvoiceNumber = "Abcde135792469";
				merchantSalesAmount = "400";
			    merchantSales = new MerchantSales();
			    merchantSales.setMerchantCustomerID(merchantCustomerID);	
			    merchantSales.setMerchantCustomerName(merchantCustomerName);
			    merchantSales.setMerchantSaleInvoiceNumber(merchantSaleInvoiceNumber);
			    merchantSales.setMerchantSalesAmount(merchantSalesAmount);
			    merchantSalesList.add(merchantSales);

			    merchantCustomerID = "ABCDE123458";
				merchantCustomerName = "Abcdeh";
				merchantSaleInvoiceNumber = "Abcde13579246810";
				merchantSalesAmount = "500";
			    merchantSales = new MerchantSales();
			    merchantSales.setMerchantCustomerID(merchantCustomerID);	
			    merchantSales.setMerchantCustomerName(merchantCustomerName);
			    merchantSales.setMerchantSaleInvoiceNumber(merchantSaleInvoiceNumber);
			    merchantSales.setMerchantSalesAmount(merchantSalesAmount);
			    merchantSalesList.add(merchantSales);
			}
			
			for (int i = 0; i < pageDisplayLength-5; i++) {
			    MerchantSales merchantSales = new MerchantSales();
			    merchantCustomerID = "ABCDE123459";
				merchantCustomerName = "Abcdei";
				merchantSaleInvoiceNumber = "Abcde13579246811";
				merchantSalesAmount = "100";
			    merchantSales = new MerchantSales();
			    merchantSales.setMerchantCustomerID(merchantCustomerID);	
			    merchantSales.setMerchantCustomerName(merchantCustomerName);
			    merchantSales.setMerchantSaleInvoiceNumber(merchantSaleInvoiceNumber);
			    merchantSales.setMerchantSalesAmount(merchantSalesAmount);			     
			    merchantSalesList.add(merchantSales);  
			}*/
			return merchantSalesList;
		}	
	
}
