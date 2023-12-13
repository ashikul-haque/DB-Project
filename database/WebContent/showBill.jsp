<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">

		<form action="home">
			<input type="submit" value="Return to Homepage" />
		</form>

		<div align="center">
			<table border="1" cellpadding="6">
				<caption>
					<h1>Bill for Quote Request ${bill.quoteReqID}</h1>
				</caption>
				<tr>
					<th>Bill ID</th>
					<th>Issue Date</th>
					<th>Amount</th>
					<th>Status</th>
					<th>Note</th>
					<th>Action</th>
				</tr>
				<tr style="text-align: center">
					<td><c:out value="${bill.billID}" /></td>
					<td><c:out value="${bill.dateIssued}" /></td>
					<td><c:out value="${bill.amountDue}" /></td>
					<td><c:out value="${bill.status}" /></td>
					<td><c:out value="${bill.note}" /></td>

					<td>
						<form action="payBill"
							${user != 'david' && bill.status!= 'paid' ? '' : 'style="display:none;"'}
							method="post">
							<input type="hidden" name="quoteReqID" value="${bill.quoteReqID}">
							<input type="hidden" name="billID" value="${bill.billID}">
							<input type="hidden" name="amount" value="${bill.amountDue}">
							<input type="submit" value="Pay Bill" />
						</form>

						<form action="disputeBill"
							${user != 'david' && disputeHistory != 1 ? '' : 'style="display:none;"'}
							method="post">
							<input type="hidden" name="quoteReqID" value="${bill.quoteReqID}">
							<input type="hidden" name="billID" value="${bill.billID}">
							<input type="hidden" name="user" value="${user}"> <input
								type="text" name="note" placeholder="Enter Comment"> <input
								type="submit" value="Dispute" />
						</form>

						<form action="changeBill"
							${user == 'david' && bill.status!= 'paid' ? '' : 'style="display:none;"'}
							method="post">
							<input type="hidden" name="quoteReqID" value="${bill.quoteReqID}">
							<input type="hidden" name="billID" value="${bill.billID}">
							<input type="text" name="amount" placeholder="New Amount">
							<input type="submit" value="Change Amount" />
						</form>
					</td>
			</table>
		</div>


		<div style="margin-bottom: 20px;"></div>

		<c:if test="${disputeHistory == 1}">

			<table border="1" cellpadding="5">
				<caption>
					<h1>Dispute History</h1>
				</caption>
				<tr>
					<th>Date and Time</th>
					<th>User</th>
					<th>David</th>
				</tr>

				<c:forEach var="dispute" items="${listDisputes}">
					<tr>
						<td>${dispute.timeAndDate}</td>
						<td>${dispute.user != 'david' ? dispute.changelog : ''}</td>
						<td>${dispute.user == 'david' ? dispute.changelog : ''}</td>
					</tr>
				</c:forEach>

				<tr>
					<td> </td>
					<td>
						<form action="disputeBill"
							${user != 'david' && bill.status!= 'paid' ? '' : 'style="display:none;"'} method="post">
							<input type="hidden" name="quoteReqID" value="${bill.quoteReqID}">
							<input type="hidden" name="billID" value="${bill.billID}">
							<input type="hidden" name="user" value="${user}"> <input
								type="text" name="note" placeholder="Comment"> <input
								type="submit" value="Submit" />
						</form>
						
					</td>
					<td>
						<form action="disputeBill"
							${user == 'david' && bill.status!= 'paid' ? '' : 'style="display:none;"'} method="post">
							<input type="hidden" name="quoteReqID" value="${bill.quoteReqID}">
							<input type="hidden" name="billID" value="${bill.billID}">
							<input type="hidden" name="user" value="${user}"> <input
								type="text" name="note" placeholder="Comment"> <input
								type="submit" value="Submit" />
						</form>
						
					</td>
				</tr>

			</table>
		</c:if>

	</div>
</body>
</html>