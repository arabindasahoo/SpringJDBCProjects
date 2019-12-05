<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Home Page</title>
</head>
<body>
		<div align="right">
		<a href="/UserCRUDSpringJDBCApp/login/logout">Logout here</a>
		</div>
	<div align="center">
	
		<h2>USER HOME PAGE</h2><hr>
		<table border="1" cellpadding="10">
			<tr>
				<td>ID:</td>
				<td>NAME:</td>
				<td>EMAIL:</td>
				<td>PHONE:</td>
				<td>DESIGNATION:</td>
				<td>GENDER:</td>
				<td>MARRITAL STATUS:</td>
				<td>DATE OF BIRTH:</td>
				<td>EDIT HERE</td>
			</tr>

			<c:forEach var="user" items="${userData}">
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>

					<td>${user.email}</td>
					<td>${user.phone}</td>

					<td>${user.designation}</td>
					<td>${user.gender}</td>
					<td>${user.married}</td>
					<td>${user.dob}</td>
					<td><a href="/UserCRUDSpringJDBCApp/user/edit?id=${user.id}">EDIT</a>
			
			</td>
				</tr>
			</c:forEach>

		</table>


	</div>

</body>
</html>