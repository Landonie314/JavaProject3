<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	//Getting all of the values from the servlet to use in the .jsp
	String pass = (String) request.getAttribute("passWord");
	String name = (String) request.getAttribute("userName");
	String fort = (String) request.getAttribute("total"); 
	int rado = Integer.parseInt(fort);
	%>
	<form action="/ProjectTres/Servlet" method="get">
    Predictions currently in your collection: <%=fort%><br>
    Select a prediction to delete:<br>
    <input type="number" value="1" name="deleter" min="1" max="<%=rado%>" >
    <input type="submit" value="Delete" name="removeButton"> <br>
    <input type="submit" value="return to home" name="indexButton">
    <input type="hidden" value=<%=name%> name="userName">
    <input type="hidden" value=<%=pass%> name="passWord">
   	</form>
   
</body>
</html>