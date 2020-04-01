<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body{
margin: 100px;
text-align: center;
}
input{
width:230px;}
</style>
<meta charset="ISO-8859-1">
<title>Login Emetad</title>
</head>
<body>
<fieldset>
<legend><h2>Emetad login page</h2></legend>
<form name="loginForm" method="post" action="loginController">
<table align="center">
<tr>
<td>Email:</td>
<td><input type="text" name="emailInput" placeholder="Enter your Email address"></td>
</tr>
<tr>
<td>Password:</td>
<td><input type="Password" name="pwdInput" placeholder="Enter your password"></td>
</tr>
</table>
<div style="text-align: center;">
<input type="Reset">
<input type="submit" value="Login">
</div>
</form>
</fieldset>
</body>
</html>