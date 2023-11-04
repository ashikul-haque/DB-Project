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
		 
		 <a href="requestquote.jsp"target ="_self" > New Request Quote</a><br><br> 
		 
		 <div align="center">
 
        <table border="1" cellpadding="6">
            <caption><h1>List of SUbmitted QuoteReqs</h1></caption>
            <tr>
                <th>Tree 1</th>
                <th>Tree 2</th>
                <th>Tree 3</th>
                <th>Client ID</th>
                <th>Date Submitted</th>
                <th>Status</th>
                <th>Note</th>
            </tr>
            <c:forEach var="users" items="${listQuoteReqs}">
                <tr style="text-align:center">
                    <td><c:out value="${users.treeID1}" /></td>
                    <td><c:out value="${users.treeID2}" /></td>
                    <td><c:out value="${users.treeID3}"/></td>
                    <td><c:out value="${users.clientID}"/></td>
                    <td><c:out value="${users.dateSubmitted}" /></td>
                    <td><c:out value="${users.status}" /></td>
                    <td><c:out value="${users.clientNote}"/></td>
                    <td>
                    	<form action="giveQuote" method="post" ${users.status == 'accepted' ? '' : 'style="display:none;"'}>
                    		<input type="hidden" name="quoteReqID" value="${users.quoteRequestID}">
                    		<input type="submit" value="Show"/>
                    	</form>
                    </td>
            </c:forEach>
        </table>
	</div>
		 
		 </center>
	</body>
</html>