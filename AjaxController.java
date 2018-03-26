package com.click.online;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.click.online.dao.bean.ProductListByName;
import com.click.online.form.model.SearchPrice;
import com.click.web.model.AjaxResponseBody;
import com.click.web.model.ProductDetails;

/*Handle Ajax controller*/

@Controller
public class AjaxController {
	
	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	
	List<ProductListByName> data = new ArrayList<ProductListByName>();
	
	AjaxController() {		
		
		data.add(new ProductListByName(1, "Seeragam", 15.0));
		data.add(new ProductListByName(2, "Click Plus", 25.0));
		data.add(new ProductListByName(3, "Cocunut Oil", 35.0));
		data.add(new ProductListByName(4, "Gingley", 45.0));
		data.add(new ProductListByName(5, "Five", 55.0));
		data.add(new ProductListByName(6, "Gingley gil", 65.0));
		data.add(new ProductListByName(7, "Ginger", 75.0));
		data.add(new ProductListByName(8, "Ground Nut", 85.0));
		data.add(new ProductListByName(9, "Dates", 95.0));
		data.add(new ProductListByName(10, "Biscuits", 105.0));
		data.add(new ProductListByName(11, "Fair and Lovely", 115.0));
		data.add(new ProductListByName(12, "Mysore Sandal Wood Soap", 125.0));
		
	}
	


	@RequestMapping(value = "/getProductNames", method = RequestMethod.GET)
	public @ResponseBody
	List<ProductListByName> getTags(@RequestParam String productName) {
		logger.info("Welcome AjaxController! productName is  {}.", productName);
		return simulateSearchResult(productName);

	}
	
	@ResponseBody
	@RequestMapping(value = "/getProductPrice", method = RequestMethod.POST)
	public 	AjaxResponseBody  getProductPrice(@RequestBody SearchPrice searchPrice) {
		AjaxResponseBody result = new AjaxResponseBody();
		logger.info("Welcome AjaxController! SearchPrice is  {}.", searchPrice.getProductName());
		List<ProductDetails> productList = getProductDetails(searchPrice.getProductName());
		logger.info("Welcome AjaxController! SearchPrice productList is  {}.", productList);
		if(productList.size() > 0) {
			result.setCode("200");
			result.setMsg("");
			result.setResult(productList);
		} else {
			result.setCode("204");
			result.setMsg("No user!");
		}
		logger.info("Welcome AjaxController! SearchPrice result is  {}.", result);
		return result;

	}

	
	@ResponseBody
	@RequestMapping(value = "/submitPurchase", method = RequestMethod.POST)
	public 	AjaxResponseBody  submitPurchase(@RequestBody SearchPrice searchPrice) {
		AjaxResponseBody result = new AjaxResponseBody();
		logger.info("Welcome AjaxController! submitPurchase is  {}.", searchPrice.getProductName());
		List<ProductDetails> productList = submitSales(searchPrice);
		logger.info("Welcome AjaxController! submitPurchase productList is  {}.", productList);
		if(productList.size() > 0) {
			result.setCode("200");
			result.setMsg("");
			result.setResult(productList);
		} else {
			result.setCode("204");
			result.setMsg("No user!");
		}
		logger.info("Welcome AjaxController! submitPurchase result is  {}.", result);		
		return result;
	}
	
	private List<ProductDetails> getProductDetails(String productName) {
		List<ProductDetails> productList = new ArrayList<ProductDetails>();
		ProductDetails pd = new ProductDetails();
		pd.setProductId("P1");
		pd.setProductName(productName);
		pd.setProductPrice("25.00");
		productList.add(pd);
		return productList;
	}
	
	private List<ProductDetails> submitSales(SearchPrice searchPrice) {
		List<ProductDetails> productList = new ArrayList<ProductDetails>();
		ProductDetails pd = new ProductDetails();
		pd.setProductId("P1");
		pd.setProductName(searchPrice.getProductName());
		pd.setProductPrice("25.00");
		pd.setProductSaleOrderId("SVC123412");
		productList.add(pd);
		return productList;
	}
	private List<ProductListByName> simulateSearchResult(String productName) {
		List<ProductListByName> result = new ArrayList<ProductListByName>();

		// iterate a list and filter by tagName
		for (ProductListByName prodName : data) {
			if (prodName.getProductName().contains(productName)) {
				result.add(prodName);
			}
		}
		logger.info("Welcome AjaxController! result is  {}.", result);
		return result;
	}
}
