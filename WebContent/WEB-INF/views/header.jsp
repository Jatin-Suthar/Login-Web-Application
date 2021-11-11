<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div style="background:#EOEOEO; height: 55px; padding: 5px;">
	<div style="float: left">
		<h1>My Site</h1>
	</div>
	
	<div style="float: right; padding: 10px; text-align: right">
		Hello <b>${loginedUser.userName}</b>
		<br/>
		Search <input name="search">
	</div>
</div>