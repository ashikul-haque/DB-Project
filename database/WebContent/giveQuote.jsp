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
            <caption><h1>Quote History</h1></caption>
            <tr>
                <th>Price</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Submission Date</th>
                <th>Status</th>
                <th>Note</th>
            </tr>
				<c:forEach var="users" items="${listQuotes}">
					<tr style="text-align: center">
						<td><c:out value="${users.price}" /></td>
						<td><c:out value="${users.workPeriodStartDate}" /></td>
						<td><c:out value="${users.workPeriodEndDate}" /></td>
						<td><c:out value="${users.dateSubmitted}" /></td>
						<td><c:out value="${users.status}" /></td>
						<td><c:out value="${users.note}" /></td>
						<td>
							<form action="acceptQuote"
								${showReject == 0 && users.status == 'pending' ? '' : 'style="display:none;"'} method="post">
								<input type="hidden" name="quoteID" value="${users.quoteID}">
								<input type="submit" value="Accept" />
							</form>
						</td>
						<td>
							<form action="rejectQuote"
								${showReject == 0 && users.status == 'pending' ? '' : 'style="display:none;"'}>
								<input type="hidden" name="quoteID" value="${users.quoteID}">
								<input type="text" name="note" placeholder="Enter reject reason">
								<input type="submit" value="Reject" />
							</form>
						</td>
						<td>
							<form action="quitQuote"
								${showReject == 0 && users.status == 'pending' ? '' : 'style="display:none;"'}>
								<input type="hidden" name="quoteID" value="${users.quoteID}">
								<input type="text" name="note" placeholder="Enter quit reason">
								<input type="submit" value="Cancel QuoteReq" />
							</form>
						</td>
				</c:forEach>
			</table>
		</div>


		<div style="margin-bottom: 20px;"></div>

		<c:if test="${showReject == 1}">
			<form action="submitQuote">
				<table border="1" cellpadding="5">
					<tr>
						<th>Quote Request ID:</th>
						<td align="center" colspan="3">${quoteRequestID}</td>
					</tr>
					<!-- Add other form fields as needed -->
					<tr>
						<th>Price:</th>
						<td align="center" colspan="3"><input type="text"
							name="price" size="45"></td>
					</tr>
					<tr>
						<th>Start Date:</th>
						<td align="center" colspan="3"><input type="text"
							name="workPeriodStartDate" size="45"></td>
					</tr>
					<tr>
						<th>End Date:</th>
						<td align="center" colspan="3"><input type="text"
							name="workPeriodEndDate" size="45"></td>
					</tr>
					<tr>
						<th>Note:</th>
						<td align="center" colspan="3"><input type="text" name="note"
							size="45"></td>
					</tr>
					<tr>
						<td align="center" colspan="5"><input type="hidden"
							name="quoteRequestID" value="${quoteRequestID}"> <input
							type="submit" value="Submit" /></td>
					</tr>
				</table>
			</form>
		</c:if>

	</div>
</body>
</html>