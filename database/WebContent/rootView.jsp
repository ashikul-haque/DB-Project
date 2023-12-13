<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Root page</title>
</head>
<body>

<div align = "center">
	
	<form action = "initialize">
		<input type = "submit" value = "Initialize the Database"/>
	</form>
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

<h1></h1>
    <div align="center">
    	<table border="1" cellpadding="6">
            <caption><h2></h2></caption>
            <tr>
                <th>Select One</th>
            </tr>
            <c:forEach var="query" items="${queries}">
                <tr style="text-align:center">
                	<td><form action="runQuery" method="post">
    					<input type="hidden" name="query" value="${query}">
    					<input type="submit" value="${query}"/>
                    </form></td>
            </c:forEach>
        </table>
        <table border="1" cellpadding="6">
            <caption><h2></h2></caption>
            <tr>
                <th>Result</th>
            </tr>
            <c:forEach var="item" items="${lists}">
                <tr style="text-align:center">
                    <td><c:out value="${item}" /></td>
            </c:forEach>
        </table>
	</div>
	</div>

</body>
</html>