<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>        
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Store Home</title>
</head>
<body>
  Welcome
	<b> ${userName}</b>,
	<P>
		Your are in <b> ${storeName} </b> store and it's in <b>
			${storeCity} </b> City.
	</P>
	<a href="#" onclick="javascript:window.print();" >Print this page</a>
	<a href="logout">Logout</a>
 <table>
 <tr>
 <td colspan="4">
 <form:form method="post"    modelAttribute="saleCall" action="saleCall">
 <input type="hidden" name="merchantName" value="${userName}"/>
 <input type="submit" value="Make Sale"/>
 </form:form>
 </td>
 </tr>
 <tr>
 <td colspan="4"><h1>Sale Details</h1></td>
 </tr>
 <tr>
	 <td>Customer ID</td>
	 <td>Customer Name</td>
	 <td>Sale Date</td>
	 <td>Sale InvoiceNumber</td>
	 <td>Sales Amount</td>
 </tr>
 <c:forEach items="${storeSaleList}" var="storeSaleList">
 <tr>
 <td>${storeSaleList.merchantCustomerID}</td>
 <td>${storeSaleList.merchantCustomerName}</td>
 <td>${storeSaleList.merchantSaleDate}</td>
 <td>${storeSaleList.merchantSaleInvoiceNumber}</td>
 <td>${storeSaleList.merchantSalesAmount}</td>
 </tr>
 </c:forEach>
 </table>
</body>
</html>