<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Login Page</title>
<style type="text/css">
.error {
	color: red;
}
</style>

</head>
<body>
		<a href="/UserCRUDSpringJDBCApp/">BACK TO HOME</a>
		
	<div align="center">
		<h2>ADMIN LOGIN PAGE</h2><hr>
		
		<form:form action="/UserCRUDSpringJDBCApp/adminLogin/loginAsAdmin"
			method="POST" modelAttribute="loginAdmin">

			<table>
				<tr>
					<td>User Email:</td>
					<td><form:input path="email" /></td>
					<td><form:errors path="email" cssClass="error"/></td>

				</tr>

				<tr>
					<td>User Password:</td>
					<td><form:password path="password" /></td>
					<td><form:errors path="password" cssClass="error"/></td>

				</tr>

				<tr>
					<td><input type="submit" value="Admin Login"></td>
				</tr>
			</table>

		</form:form>
			<a href="/UserCRUDSpringJDBCApp/adminLogin/forgotPassword">Forgot Password?</a>
	</div>
</body>
</html>