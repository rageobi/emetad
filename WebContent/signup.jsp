<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Signup Emetad</title>
<script src="js/signup.js" type="text/javascript"></script>
<style type="text/css">
body{
margin: 100px;
text-align: center;
width: max-content;/*! padding: 100px; */padding-top: 25px;padding-left: 425px;
}
input{
width:230px;}
</style>
</head>
<body>
<fieldset>
<legend><h1>Emetad Signup page</h1></legend>
<form name="loginForm" id="loginForm" method="post" action="signUpController" onSubmit="validate();" enctype='multipart/form-data'>
<table align="center">
<tr>
<td><input type="text" name="fnameInput" placeholder="First Name" required></td>
<td><input type="text" name="lnameInput" placeholder="Last Name" required></td>
</tr>
<tr>
<td><input type="hidden" id="genderInput" name="genderInput" value="M">
<input type="text" name="emailInput" placeholder="Enter your Email address" required></td>
<td><label style="margin-left:5px;">Gender<input style="width:15px;" type="radio" name="genderRadio" id="maleRadio" value="M" checked>Male
<input  style="width:15px;" type="radio" name="genderRadio" id="femaleRadio" value="F">Female
<input type="radio"  style="width:15px;" name="genderRadio" id="otherRadio" value="O" >Other</label>
</td> 
</tr>
<tr>
<td><input type="Password" id="pwdInput"name="pwdInput" placeholder="Enter your password" onkeyup="validatePassword();" required></td>
<td><input type="Password" id="pwdRptInput" name="pwdRptInput" placeholder="Re-type your password" onkeyup="validatePassword();" required></td>
</tr>
<tr>
<td><input type="text" name="stateInput" placeholder="Enter the State you are in"></td>
<td><input type="text" name="cityInput" placeholder="Enter the City you are in"></td>
</tr>
<tr>
<td><input type="text" name="phoneInput" placeholder="Enter your Mobile number"></td>
<td><input type="text" id="dobInput" name="dobInput" placeholder="Date of Birth" onfocus="(this.type='date')" required></td>
</tr>
<tr>
 <td>Choose the Image of yours:</td>
<td><input type="file" name="dpInput"  id="dpInput" onChange="validateFileType()"  accept="image/x-png,image/jpeg" required="required" /></td>
</tr>
</table>
<label style="width:200px;">Which gender are you interested in?<select id="intSelect">
  <option value="M">Male</option>
  <option value="F">Female</option>
  <option value="O">Others</option>
</select></label><input type="hidden" id="iGenderInput" name="iGenderInput" value="M">
<div style="text-align: center;">
<input type="Reset" onclick="Resetform();">
<input type="submit" value="Signup">
</div>
</form>
</fieldset>
</body>
</html>