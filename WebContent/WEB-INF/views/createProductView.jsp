<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create Product</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
      <jsp:include page="menu.jsp"></jsp:include>
      
      <h3>Create Product</h3>
      
      <p style="color: red;">${errorString}</p>
      
      <form method="POST" action="${pageContext.request.contextPath}/createProduct">
         <table border="0">
            <tr>
               <td>Code</td>
               <td><input type="text" name="code" value="${product.code}" /></td>
            </tr>
            <tr>
               <td>Name</td>
               <td><input type="text" name="name" value="${product.name}" /></td>
            </tr>
            <tr>
               <td>Price</td>
               <td><input type="text" name="price" value="${product.price}" /></td>
            </tr>
            <tr>
               <td colspan="2">                   
                   <input type="submit" value="Submit" />
                   <a href="productList">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
      <jsp:include page="footer.jsp"></jsp:include>
      
</body>
</html>