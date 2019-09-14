<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="http://localhost:8080/profile/check">
AccountNo:<input type="text" name="acno"><br>
Ifsc Code:<input type="text" name="ifsc"><br>
Name:<input type="password" name="name"><br>
Gaurdian's Name:<input type="text" name="gname"><br>
AccountType:<select>
<option selected>Savings</option>
<option>Current</option>
</select><br>
Address:<textarea rows=10 cols=10 name="address"></textarea><br>
<input type="submit" value="save">
</body>
</html>