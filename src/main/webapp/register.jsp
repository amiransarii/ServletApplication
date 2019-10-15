<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
</head>
<body>
<form action="servlet/Register" method="post">  
Name:<input type="text" name="userName"/><br/><br/>  
Password:<input type="password" name="pass"/><br/><br/>  
Confirm Password:<input type="password" name="cpass"/><br/><br/>  
Email Id:<input type="text" name="userEmail"/><br/><br/>  
Country:  
<select name="userCountry">  
<option>India</option>  
<option>Malaysia</option>  
<option>United States</option> 
<option>United Kingdom</option>  
</select>  
  
<br/><br/>  
<input type="submit" value="register"/>  
  
</form>  
</body>
</html>