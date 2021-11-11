<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Product</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="menu.jsp"></jsp:include>
	
	<h3>Delete Product</h3>
	
	<p style="color: red;">${errorString}</p>
	<a href="productList">Product List</a>
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>