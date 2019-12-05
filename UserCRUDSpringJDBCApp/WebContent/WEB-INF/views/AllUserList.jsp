<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ALL USER LIST</title>
</head>
<body>
<div align="right">
		<a href="/UserCRUDSpringJDBCApp/adminLogin/logout">Logout here</a>
		</div>
	<div align="center">
		<h2>LIST OF USERS</h2>
		<hr>
		<table border="1" cellpadding="5">
			<tr>
				<td>ID</td>
				<td>NAME</td>
				<td>EMAIL</td>
				<td>PHONE</td>
				<td>DESIGNATION</td>
				<td>GENDER</td>
				<td>MARRITAL STATUS</td>
				<td>DATE OF BIRTH</td>
				<td>EDIT/DELETE</td>
				<td>APPROVE</td>
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
					<td><a href="/UserCRUDSpringJDBCApp/admin/edit?id=${user.id}">EDIT</a>/
						<a href="/UserCRUDSpringJDBCApp/admin/delete?id=${user.id}">DELETE</a>
					</td>
					<c:set var="approval" value="${user.approval}" />
					<c:if test="${approval == 1}">
						<td>APPROVED</td>
					</c:if>
					<c:if test="${approval == 0}">
						<td><a
							href="/UserCRUDSpringJDBCApp/admin/approveUser?id=${user.id}">APPROVE</a></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>