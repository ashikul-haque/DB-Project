<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<head><title>Tree</title></head>
</head>
<body>
<div align="center">
<form action="addtree">
			<table border="1" cellpadding="5">
				<tr>
					<th>Size: </th>
					<td align="center" colspan="3">
						<input type="text" name="size" size="45"  value="" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Height: </th>
					<td align="center" colspan="3">
						<input type="text" name="height" size="45" value="" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Location: </th>
					<td align="center" colspan="3">
						<input type="text" name="location" size="45" value="" onfocus="this.value=''">
					</td>
				</tr>
				<tr>
					<th>Distance to house: </th>
					<td align="center" colspan="3">
						<input type="text" name="distance" size="45" value="" onfocus="this.value=''">
					</td>
	
				</tr>
				<tr>
					<th>Photo 1: </th>
					<td align="center" colspan="3">
						<input type="file" name="photo1" size="45">
					</td>
	
				</tr>
				<tr>
					<th>Photo 2: </th>
					<td align="center" colspan="3">
						<input type="file" name="photo2" size="45">
					</td>
	
				</tr>
				<tr>
					<th>Photo 3: </th>
					<td align="center" colspan="3"> 
						<input type="file" name="photo3" size="45">
					</td>
				</tr>
				<tr>
					<td align="center" colspan="5">
						<input type="submit" value="AddTree"/>
					</td>
				</tr>
			</table>
			<a href="activitypage2.jsp" target="_self">Return to Home Page</a>
		</form>
</div>
</body>
</html>