<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
		
		<form action = "home">
				<input type = "submit" value = "Return to Homepage"/>
		</form>
		
		<div align="center">
        <table border="1" cellpadding="6">
            <caption><h1>List of Quotes</h1></caption>
            <tr>
                <th>Price</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Submission Date</th>
                <th>Status</th>
                <th>Note</th>
            </tr>
            <c:forEach var="users" items="${listQuotes}">
                <tr style="text-align:center">
                    <td><c:out value="${users.price}" /></td>
                    <td><c:out value="${users.workPeriodStartDate}" /></td>
                    <td><c:out value="${users.workPeriodEndDate}" /></td>
                    <td><c:out value= "${users.dateSubmitted}" /></td>
                    <td><c:out value="${users.status}" /></td>
                    <td><c:out value="${users.note}" /></td>
            </c:forEach>
        </table>
		</div>
		
		<table border="1" cellpadding="6">
            <tr>
               <form action = "rejectQuote" ${showReject == 1 ? '' : 'style="display:none;"'}>
					<input type = "submit" value = "Reject"/>
			   </form>
		
			   <form action = "acceptQuote" ${showReject == 1 ? '' : 'style="display:none;"'}>
					<input type = "submit" value = "Accept"/>
			   </form>
            </tr>
		</table>
		
		
		<form action="submitQuote">
			<table border="1" cellpadding="5">
				<tr>
					<th>Quote Request ID: </th>
					<td align="center" colspan="3">
						${quoteRequestID}
					</td>
				</tr>
				<tr>
					<th>Price: </th>
					<td align="center" colspan="3">
						<input type="text" name="price" size="45">
					</td>
				</tr>
				<tr>
					<th>Start Date: </th>
					<td align="center" colspan="3">
						<input type="text" name="workPeriodStartDate" size="45">
					</td>
				</tr>
				<tr>
					<th>End Date: </th>
					<td align="center" colspan="3">
						<input type="text" name="workPeriodEndDate" size="45">
					</td>
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="Submit"/>
					</td>
				</tr>
			</table>
			
		</form>
	</div>
</body>
</html>