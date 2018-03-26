<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%-- <script src="<c:url value="/resources/core/jquery.1.10.2.min.js" />"></script> --%>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="<c:url value="/resources/core/jquery.autocomplete.min.js" />"></script>
<link href="<c:url value="/resources/core/main.css" />" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sale</title>
<style>
  #feedback { font-size: 1.4em; }
  #selectable .ui-selecting { background: #FECA40; }
  #selectable .ui-selected { background: #F39814; color: white; }
  #selectable { list-style-type: none; margin: 0; padding: 0; width: 60%; }
  #selectable li { margin: 3px; padding: 0.4em; font-size: 1.4em; height: 18px; }
  </style>
</head>
<body>
 Welcome
	<b><input type="text"  id="merchantName" value=" ${merchantName}" readonly="readonly"></b>,

	<P>
		Initiate a Sale ...
	</P>
	<div id="updatStatus">
	</div>
	 <div>
	<form  id="search-form"> 
	<input type="text"  id="searchProductByName" value="">
	<input type="text"  id="productPrice" value="">
	<span>
	  <button id="add_items_row_1" type="submit">Add</button>
	</span>
	</form>
    
    <form  id="search-form-1"> 
	<input type="text"  id="searchProductByName_1" value="">
	<input type="text"  id="productPrice_1" value="">
	<span>
	  <button id="add_items_row_2" type="submit">Add</button>
	</span>
	</form>	
	
  </div>
  <script>
  $(document).ready(function() {
	  
	  var obj = $("#add_items_row_1");
	  enableSearchButton(obj,true);
	  
	  var obj1 = $("#add_items_row_2");
	  enableSearchButton(obj1,true);
	  
	  $('#searchProductByName').autocomplete({
			serviceUrl: '${pageContext.request.contextPath}/getProductNames',
			paramName: "productName",
			delimiter: ",",
		    transformResult: function(response) {
				return {
				      //must convert json to javascript object before process
				      suggestions: $.map($.parseJSON(response), function(item) {
				      return { value: item.productName, data: item.id};
				   })
			     };       
		    }
	  });	
	  
	  $('#searchProductByName_1').autocomplete({
			serviceUrl: '${pageContext.request.contextPath}/getProductNames',
			paramName: "productName",
			delimiter: ",",
		    transformResult: function(response) {
				return {
				      //must convert json to javascript object before process
				      suggestions: $.map($.parseJSON(response), function(item) {
				      return { value: item.productName, data: item.id};
				   })
			     };       
		    }
	  });	
	  	  
	  
	  $("#productPrice").click(function(event) {
		  var obj = $("#add_items_row_1");
		  enableSearchButton(obj,false);
		  var searchProductName = $("#searchProductByName").val();
		  var obj = $('#productPrice');
		  searchProductPrice(obj,searchProductName); 
	  });
	  
	  $("#productPrice_1").click(function(event) {
		  var obj = $("#add_items_row_2");
		  enableSearchButton(obj,false);
		  var searchProductName = $("#searchProductByName_1").val();
		  var obj = $('#productPrice_1');
		  searchProductPrice(obj,searchProductName); 
	  });
	  	  
	  
	  $("#search-form").submit(function(event) {
		    var obj = $("#add_items_row_1");
			// Prevent the form from submitting via the browser.
			event.preventDefault();
			var productName = $("#searchProductByName").val();
			var productPrice = $("#productPrice").val();
			submitForm1(productName,productPrice);
			
			// Disble the search button
			enableSearchButton(obj,true);

		});
	  $("#search-form-1").submit(function(event) {
		    var obj = $("#add_items_row_2");

			// Prevent the form from submitting via the browser.
			event.preventDefault();
			var productName = $("#searchProductByName_1").val();
			var productPrice = $("#productPrice_1").val();
			submitForm1(productName,productPrice);
			  
			// Disble the search button
			enableSearchButton(obj,true);

		});	  
  });
  
	function searchProductPrice(obj,searchProductByName) {

		var search = {}
		search["productName"] = searchProductByName;
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/getProductPrice",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);				
				displayProductPrice(obj,data);
			},
			error : function(e) {
				console.log("ERROR: ", e);				
				displayProductPrice(obj,e);
			},
			done : function(e) {
				console.log("DONE");				
			}
		});

	}
	function displayProductPrice(obj,data) {		
		var productPrice = data.result[0].productPrice;
		obj.val(productPrice);
	}	
	
	function submitForm1(productName,productPrice) {
		var search = {}
		search["productName"]   = productName;
		search["productPrices"] = productPrice;
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/submitPurchase",
			data : JSON.stringify(search),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				displaySales(data);
			},
			error : function(e) {
				console.log("ERROR: ", e);
				displaySales(e);
			},
			done : function(e) {
				console.log("DONE");
				var obj = $("#add_items_row_2");
				enableSearchButton(obj,true);
			}
		});
	}

	function enableSearchButton(obj,flag) {		
		obj.prop("disabled", flag);
	}

	function displaySales(data) {
		var productName = data.result[0].productName;
		var json = "<h4>Status</h4><pre>"
				+ productName + " failure.</pre>";
        if(null != productName) {
        	json = "<h4>Status</h4><pre>"
				+ productName + " added successfully.</pre>";
        }				
		$('#updatStatus').html(json);
	}	
	
  </script>
</body>
</html>