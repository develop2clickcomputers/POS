<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
<link href="<c:url value="/resources/core/main.css" />" rel="stylesheet">
<link href="<c:url value="/resources/core/click.css" />" rel="stylesheet">
<title>Login</title>
</head>
<body>
<div class="login-card">
<form:form method="post"
           modelAttribute="userLogin" action="login">
	<table>
		<tr><td>UserName</td><td><input type="text" name="userName" placeholder="Username"/></td></tr>
		<tr><td>Password</td><td><input type="password" name="userPassword" placeholder="Password"/></td></tr>
		<tr><td colspan="2"><input type="submit" class="login login-submit" value="Login"/></td></tr>
	</table>
	${message}
</form:form>
<div class="login-help">
    <a href="#">Register</a> • <a href="#">Forgot Password</a>
  </div>
</div>
<script src='http://codepen.io/assets/libs/fullpage/jquery_and_jqueryui.js'></script>
</body>
</html>
