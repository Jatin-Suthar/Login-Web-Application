<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<jsp:include page="menu.jsp"></jsp:include>
	
	<h3>Login Page</h3>
	<p style="color:red;">${errorString}</p>
	
	<form method = "POST" action="${pageContext.request.contextPath}/login">
		<table border="1">
			<tr>
               <td>User Name</td>
               <td><input type="text" name="userName" value= "${user.userName}" /> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="text" name="password" value= "${user.password}" /> </td>
            </tr>
            <tr>
               <td>Remember me</td>
               <td><input type="checkbox" name="rememberMe" value= "Y" /> </td>
            </tr>
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
                  <a href="${pageContext.request.contextPath}/">Cancel</a>
               </td>
            </tr>
		</table>
	</form>
	<p style="color:blue;">User Name: tom, password: tom001 or jerry/jerry001</p>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>