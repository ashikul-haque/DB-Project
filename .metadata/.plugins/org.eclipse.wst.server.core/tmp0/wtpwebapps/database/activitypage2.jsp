<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Activity page</title>
</head>

<center><h1>Welcome! You have been successfully logged in</h1> </center>

 
	<body>
	 <center>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 
		 <a href="addtree.jsp"target ="_self" > Add Tree</a><br><br> 
		 
		 <form action="quotereq">
		 	<table border="1" cellpadding="5">
				<tr>
					<th>Note: </th>
					<td align="center" colspan="3">
						<input type="text" name="note" size="45">
					</td>
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Request Quote"/>
					</td>
				</tr>
		 </form>
		 
		 </center>
	</body>
</html>