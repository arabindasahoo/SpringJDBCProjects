<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>forgotPasswordPage</title>
<style type="text/css">
.error{
color :red;
}
</style>
</head>
<body>
		<div align="center">
		<h2>FORGOT PASSWORD PAGE</h2>
		<h3>ENTER YOUR DETAILS TO RETRIVE CHANGE PASSWORD</h3><hr>
		<form:form action="/UserCRUDSpringJDBCApp/adminLogin/newPassword" method="post" modelAttribute="ForgotPassword">
		<table>
		<tr>
		<td>ENTER YOUR EMAIL:</td>
		<td><form:input path="Email"/></td>
		<td><form:errors path="Email" cssClass="error"/></td>
		</tr>
		<tr>
		<td>ENTER PHONE NUMBER:</td>
		<td><form:input path="phone"/></td>
		<td><form:errors path="phone" cssClass="error"/></td>
		</tr>
		<tr>
		<td><input type="submit" value="Submit"> </td>
		</tr>
		</table>
		
		</form:form>
		
		</div>
</body>
</html>