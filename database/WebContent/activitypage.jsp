<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home page</title>
</head>

<center><h1>Welcome David!</h1> </center>

<body>
<div align="center">
	<a href="login.jsp"target ="_self" > logout</a><br><br> 

    <div align="center">
 
        <table border="1" cellpadding="6">
            <caption><h2>List of QuoteReqs</h2></caption>
            <tr>
                <th>Client ID</th>
                <th>Tree Count</th>
                <th>Date Submitted</th>
                <th>Status</th>
                <th>Note</th>
                <th>Action</th>
            </tr>
            <c:forEach var="users" items="${listQuoteReqs}">
                <tr style="text-align:center">
                    <td><c:out value="${users.clientID}"/></td>
                    <td><c:out value="${users.treeCount}"/></td>
                    <td><c:out value="${users.dateSubmitted}" /></td>
                    <td><c:out value="${users.status}" /></td>
                    <td><c:out value="${users.clientNote}"/></td>
                    <td>
                    	<form action="giveQuote" method="post" ${users.status == 'quoted' || users.status == 'cancelled' ? '' : 'style="display:none;"'}>
                    		<input type="hidden" name="quoteReqID" value="${users.quoteRequestID}">
                    		<input type="submit" value="Quotes"/>
                    	</form>
                    	<form action="giveQuote" method="post" ${users.status == 'pending' ? '' : 'style="display:none;"'}>
                    		<input type="hidden" name="quoteReqID" value="${users.quoteRequestID}">
                    		<input type="submit" value="Accept"/>
                    	</form>
                    	<form action="rejectQuoteReq" method="post" ${users.status == 'pending' ? '' : 'style="display:none;"'}>
                    		<input type="hidden" name="quoteReqID" value="${users.quoteRequestID}">
                    		<input type="submit" value="Reject"/>
                    	</form>
                    	<form action="treeDetails" method="post" ${users.status == 'pending' ? '' : 'style="display:none;"'}>
                    		<input type="hidden" name="quoteReqID" value="${users.quoteRequestID}">
                    		<input type="hidden" name="source" value="home">
                    		<input type="submit" value="Tree Details"/>
                    	</form>
                    	<form action="workOrder" method="post" ${users.status == 'accepted' ? '' : 'style="display:none;"'}>
                    		<input type="hidden" name="quoteReqID" value="${users.quoteRequestID}">
                    		<input type="submit" value="Work Order"/>
                    	</form>
                    	<form action="showBill" method="post" ${users.status == 'billed' ? '' : 'style="display:none;"'}>
                    		<input type="hidden" name="quoteReqID" value="${users.quoteRequestID}">
                    		<input type="submit" value="Show Bill"/>
                    	</form>
						
                    </td>
            </c:forEach>
        </table>
	</div>
	</div>
</body>
</html>