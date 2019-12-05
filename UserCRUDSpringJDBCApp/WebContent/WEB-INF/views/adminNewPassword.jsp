<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NEW PASSWORD PAGE</title>

<style type="text/css">
.error{
color :red;
}
</style>

</head>
<body>	
		<div align="center">
		
		<h2>CREATE NEW PASSWORD</h2><hr>
		
		<form:form action="/UserCRUDSpringJDBCApp/adminLogin/changePassword" method="post" modelAttribute="Password">
		<table>
		<tr>
		<td>ENTER NEW PASSWORD:</td>
		<td><form:password path="password"/></td>
		<td><form:errors path="password" cssClass="error"/></td>
		</tr>
		<tr>
		<td><input type="submit" value="submit">  </td>
		</tr>
		</table>
		
		</form:form>
		</div>
</body>
</html>