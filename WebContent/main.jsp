<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
//allow access only if session exists
String user = null;
String base64=null;
if(session.getAttribute("user") == null){
	response.sendRedirect("login.html");
} 
%>
<br>
<div align="center">
    <h1>${user.intrestedIn}</h1>
    <img src="data:image/jpg;base64,${DisplayPic}" width="200" height="200"/>
</div>
</body>
</html>