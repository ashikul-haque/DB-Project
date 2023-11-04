<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request Quote</title>
</head>

<center><h1>Request Quote</h1> </center>

<body>

		 <center>
		 <form action="generateQuote">
		 	<table border="1" cellpadding="5">
		 		<tr>
					<th>First tree details: </th>
					<td align="center" colspan="3">
						<a href="addtree.jsp"target ="_self" > Add Tree</a><br><br> 
					</td>
				</tr>
				
				<tr>
					<th>Second tree details: </th>
					<td align="center" colspan="3">
						<a href="addtree.jsp"target ="_self" > Add Tree</a><br><br> 
					</td>
				</tr>
				
				<tr>
					<th>Third tree details: </th>
					<td align="center" colspan="3">
						<a href="addtree.jsp"target ="_self" > Add Tree</a><br><br> 
					</td>
				</tr>
				
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