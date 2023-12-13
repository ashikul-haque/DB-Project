<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Trees</title>
</head>

<body>
	<img src="img/test.jpg" alt="Picture 1" height="50" width="50">
	<div align="center">
		<form action="home" ${source == 'home' ? '' : 'style="display:none;"'}>
			<input type="submit" value="Return to Homepage" />
		</form>

		<form action="workOrder"
			${source == 'order' ? '' : 'style="display:none;"'}>
			<input type="hidden" name="quoteReqID" value="${quoteReqID}">
			<input type="submit" value="Return to previous page" />
		</form>

		<div align="center">

			<table border="1" cellpadding="6">
				<caption>
					<h2>List of Trees for Quote ${quoteReqID}</h2>
				</caption>
				<tr>
					<th>Tree ID</th>
					<th>Size</th>
					<th>Height</th>
					<th>Location</th>
					<th>Distance to House</th>
					<th>Picture 1</th>
					<th>Picture 2</th>
					<th>Picture 3</th>
				</tr>

				<c:forEach var="tree" items="${listTrees}">
					<tr style="text-align: center">
						<td>${tree.treeID}</td>
						<td>${tree.size}</td>
						<td>${tree.height}</td>
						<td>${tree.location}</td>
						<td>${tree.distanceToHouse}</td>
						<td><img src="data:image/jpeg;base64,${tree.picture1Base64}"
							alt="Picture 1"></td>
						<td><img src="data:image/jpeg;base64,${tree.picture2Base64}"
							alt="Picture 1"></td>
						<td><img src="data:image/jpeg;base64,${tree.picture3Base64}"
							alt="Picture 1"></td>
					</tr>
				</c:forEach>
			</table>

			<form action="giveQuote"
				${source == 'home' ? '' : 'style="display:none;"'} method="post">
				<input type="hidden" name="quoteReqID" value="${quoteReqID}">
				<input type="submit" value="Accept" />
			</form>
			<form action="rejectQuoteReq"
				${source == 'home' ? '' : 'style="display:none;"'} method="post">
				<input type="hidden" name="quoteReqID" value="${quoteReqID}">
				<input type="submit" value="Reject" />
			</form>

		</div>
	</div>
</body>
</html>
