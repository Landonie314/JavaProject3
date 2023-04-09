<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Result</title>
</head>
<body>
<%
		String name = (String) request.getAttribute("userName");
		String action = (String) request.getAttribute("tey");
		String weather = (String) request.getAttribute("weather");
		String temper = (String) request.getAttribute("temper");
		String humid = (String) request.getAttribute("humid");
		String windy = (String) request.getAttribute("windy");
	%>
	<%=name%>
	<form action="/ProjectTres/Servlet" method="get">
		Your activity is: <%=action%> <br>
	    <input type="hidden" value=<%=action%> name="tey">
	    <input type="hidden" value=<%=windy%> name="windy">
	    <input type="hidden" value=<%=weather%> name="weather">
	    <input type="hidden" value=<%=temper%> name="temper">
	    <input type="hidden" value=<%=humid%> name="humid">
	    <input type="hidden" value=<%=name%> name="userName">
	    
	    This is predicted based on the following inputs:<br>
	    Outlook: <%=weather%><br>
	    Temperature: <%=temper%><br>
	    Humidity: <%=humid%><br>
	    Windy: <%=windy%><br>
	    
	    <input type="submit" value="return to home" name="indexButton">
	    
	    
	    
	</form>
</body>
</html>