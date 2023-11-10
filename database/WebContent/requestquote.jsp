<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request Quote</title>
</head>

<center><h1>Request Quote</h1> </center>
<center><h2> ${treeAddStatus}</h2></center>
<body>

<center>
    <table border="1" cellpadding="5">
        <tr>
            <th>QuoteReqID: </th>
            <td align="center" colspan="3">
                ${quoteReqID}
            </td>
        </tr>

        <tr>
            <th>Add tree</th>
            <td align="center" colspan="3">
                <form action="addtree" method="post">
                    <input type="submit" value="Add"/>
                </form>
            </td>
        </tr>
        
		<form action="generateQuoteReq" method="post">
        <tr>
            <th>Note: </th>
            <td align="center" colspan="3">
                <input type="text" name="note" size="45">
            </td>
        </tr>

        <tr>
            <td align="center" colspan="5">
                
                    <input type="submit" value="Request Quote"/>
            </td>
        </tr>
        </form>
    </table>
</center>


</body>
</html>