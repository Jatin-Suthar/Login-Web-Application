<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="menu.jsp"></jsp:include>
	
	<h3>Home Page</h3>
	
	This is demo Simple web Application using jsp.servlet &amp; jdbc. <br> <br>
	<b>It includes the following functions: </b>
	<ul>
		<li>Login</li>
		<li>Storing user information in cookies</li>
		<li>Product List</li>
		<li>Create Product</li>
		<li>Edit Product</li>
		<li>Delete Product</li>
	</ul>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>