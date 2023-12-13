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
		
		<form action = "davidHome" ${user == 'david' ? '' : 'style="display:none;"'}>
				<input type = "submit" value = "Return to Homepage"/>
		</form>
		
		<form action = "userHome" ${user == 'others' ? '' : 'style="display:none;"'}>
				<input type = "submit" value = "Return to Homepage"/>
		</form>
		
		<div align="center">
        <table border="1" cellpadding="6">
            <caption><h1>Work Order</h1></caption>
            <tr>
                <th>ID</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Submission Date</th>
                <th>Tree Details</th>
                <th>Status</th>
            </tr>
					<tr style="text-align: center">
						<td><c:out value="${order.orderOfWorkID}" /></td>
						<td><c:out value="${order.startDate}" /></td>
						<td><c:out value="${order.endDate}" /></td>
						<td><c:out value="${order.dateCreated}" /></td>
						<td><c:out value="${order.status}" /></td>
						<td>
							<form action="treeDetails" method="post">
                    			<input type="hidden" name="quoteReqID" value="${quoteReqID}">
                    			<input type="hidden" name="source" value="order">
                    			<input type="submit" value="Tree Details"/>
                    		</form>
                    	</td>
						<td>
							<form action="workOrderCompleted" ${user == 'david' ? '' : 'style="display:none;"'} method="post">
								<input type="hidden" name="orderID" value="${order.orderOfWorkID}">
								<input type="hidden" name="quoteReqID" value="${quoteReqID}">
								<input type="text" name="note" placeholder="Comments">
								<input type="submit" value="Completed" />
							</form>
						</td>
			</table>
		</div>


		

	</div>
</body>
</html>