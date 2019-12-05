<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
<style type="text/css">
.error{
color : red;
}

</style>

</head>
<body>
	<div align="center">
			
	<h1>NEW USER REGISTRATION FORM</h1>
	<hr>
	
		<form:form action="/UserCRUDSpringJDBCApp/user/createUser" method="post" modelAttribute="user">
			<table border="0" cellpadding="5">
				<tr>
					<td align="right">Name:</td>
					<td><form:input path="name"/></td>
					<td><form:errors path="name" cssClass="error"/></td>
				</tr>

				<tr>
					<td align="right">Email:</td>
					<td><form:input path="email"/></td>
					<td><form:errors path="email" cssClass="error"/></td>
				</tr>

				<tr>
					<td align="right">Phone:</td>
					<td><form:input path="phone"/></td>
					<td><form:errors path="phone" cssClass="error"/></td>
				</tr>
				<tr>
					<td align="right">Date of Birth:</td>
					<td><form:input path="dob"/></td>
					<td><form:errors path="dob" cssClass="error"/></td>
				</tr>

				<tr>
					<td align="right">Designation</td>
					<td><form:select path="designation" >
					<form:options items="${designation}"/>
					
					</form:select></td>
					<td><form:errors path="designation" cssClass="error"/></td>
				</tr>

				<tr>
					<td align="right">Gender</td>
					<td>
					<form:radiobutton path="gender" value="Male"/>Male
					<form:radiobutton path="gender" value="female"/>Female
					<form:radiobutton path="gender" value="Others"/>Others
					</td>
					<td><form:errors path="gender" cssClass="error"/></td>
				</tr>
				
				<tr>
					<td align="right">Is Married?</td>
					<td>
					<form:select path="married">
					<form:options items="${status}"/></form:select>
					</td>
					<td><form:errors path="married" cssClass="error"/></td>
				</tr>

				<tr>
					<td align="right">Password:</td>
					<td><form:password path="password"/></td>
					<td><form:errors path="password" cssClass="error"/></td>
				</tr>

				<tr>
					<td colspan="3" align="center"><input type="submit" value="Create User"></td>
				</tr>
			</table>

		</form:form>
	</div>

</body>
</html>