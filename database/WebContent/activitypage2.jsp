<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home page</title>
</head>

<center><h1>Welcome! You have been successfully logged in</h1> </center>

 
	<body>
	 <center>
		 <a href="login.jsp"target ="_self" > logout</a><br><br> 
		 
		 <form action="requestQuote" method="post">
         	<input type="submit" value="New Quote Request"/>
         </form>
		 
		 <div align="center">
 
        <table border="1">
            <caption><h1>List of Submitted QuoteReqs</h1></caption>
            <tr>
                <th>Date Submitted</th>
                <th>Status</th>
                <th>Note</th>
                <th>Action</th>
            </tr>
            <c:forEach var="users" items="${listQuoteReqs}">
                <tr style="text-align:center">
                    <td><c:out value="${users.dateSubmitted}" /></td>
                    <td><c:out value="${users.status}" /></td>
                    <td><c:out value="${users.clientNote}"/></td>
                    <td>
                    	<form action="giveQuote" method="post" ${users.status == 'cancelled' || users.status == 'quoted' ? '' : 'style="display:none;"'}>
    						<input type="hidden" name="quoteReqID" value="${users.quoteRequestID}">
    						<input type="submit" value="Show Quotes"/>
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
		 
	</center>
	</body>
</html>