<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Info</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="menu.jsp"></jsp:include>
	
	<h3>Hello: ${user.userName}</h3>
	
	User Name: <b>${user.userName}</b>
	<br/>
	Gender: ${user.gender} <br/>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>