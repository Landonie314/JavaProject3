<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prediction</title>
</head>
<body>
<%
		String name = (String) request.getAttribute("userName");
	%>
	<%=name%> 
	<form action="/ProjectTres/Servlet" method="get">
	    <input type="hidden" value=<%=name%> name="userName">
		<%
			String selectionText = (String) request.getAttribute("dropDownOptions");
			String selectionText2 = (String) request.getAttribute("dropDownOptions2");
		%>
		Please select the outlook:<br><%=selectionText%><br> 
		Please select the temperature:<br>
		<input type="number" name="temper" min="1" max="100" value="1"><br>
		Please select the humidity:<br>
		<input type="number" name="humid" min="1" max="100" value="1"><br>
		Please select if it is windy:<br><%=selectionText2%><br>
		<br><input type="submit" value="Get Predicted Activity!" name="preButton">
	</form>
</body>
</html>