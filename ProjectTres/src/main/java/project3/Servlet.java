package project3;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Predictor myData;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Servlet() {
		super();
		myData = new Predictor("./project3/data2.txt");
		
		
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("userName");
		String pass = request.getParameter("passWord");
		request.setAttribute("userName",user); 
		request.setAttribute("passWord", pass);
		
		
		if(request.getParameter("indexButton")!=null) {
			response.getWriter().append("<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"<head>\r\n" + 
					"<meta charset=\"ISO-8859-1\">\r\n" + 
					"<title>Insert title here</title>\r\n" + 
					"</head>\r\n" +
					"</body>\r\n" ); 
			
			
			response.getWriter().append(user+" here are your choices:<br>	<form action=http://localhost:8080/ProjectTres/Servlet\r\n" + 
					"		method=\"get\">\r\n" + 
					"		<input type=\"hidden\" value=\""+user+"\" name=\"userName\">\r\n" + 
					"		<input type=\"submit\" value=\"View Instances\" name=\"viewButton\">\r\n" + 
					"		<input type=\"submit\" value=\"Add Instance\" name=\"addButton\">\r\n" + 
					"<input type=\"submit\" value=\"Delete Instance\" name=\"deleteButton\">\r\n" + 
					"	</form>\r\n" + 
					"</body>\r\n" + 
					"</html>");
			
			
			response.getWriter().append(
					myData.toString()+
					"</body>\r\n" + 
					"</html>");
			
		}
		
		if(request.getParameter("viewButton")!=null) {
			
			RequestDispatcher rd=request.getRequestDispatcher("/Test.jsp");
			rd.forward(request,response);
		}		
		if(request.getParameter("addButton")!=null) {
			String value = "<select name=\"activities\">";
//			Iterator<Instance> iter = myData.getIterator();
//			while (iter.hasNext()) {
//				Instance c = iter.next();
				
				String [] temp = myData.getActivities();
				for(int num = temp.length; num > 0; num--) {
				value += "<option value=\""+temp[num-1]+"\">"+temp[num-1]+"</option>";
				}
			value += "</select>\r\n";
			request.setAttribute("dropDownOptions",value); 	
			RequestDispatcher rd=request.getRequestDispatcher("/adder.jsp");
			rd.forward(request,response);
		}		
		if(request.getParameter("pickColorButton")!=null) {
			response.getWriter().append("<html>\r\n" + 
					"<head>\r\n" + 
					"<meta charset=\"ISO-8859-1\">\r\n" + 
					"<title>Insert title here</title>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					user+"	\r\n" + 
					"	<form action=\"/ProjectTres/Servlet\" method=\"get\">\r\n" + 
					"	    <input type=\"hidden\" value="+user+" name=\"userName\">\r\n" + 
					"		\r\n" + 
					"		Please select a color:<br>\r\n" + 
					"		<select name=\"backgroundColor\">\r\n" + 
					"			<option value=\"powderblue\">powderblue</option>\r\n" + 
					"			<option value=\"Tomato\">Tomato</option>\r\n" + 
					"			<option value=\"Orange\">Orange</option>\r\n" + 
					"			<option value=\"LightGray\">LightGray</option>\r\n" + 
					"			<option value=\"SlateBlue\">SlateBlue</option>\r\n" + 
					"			<option value=\"MediumSeaGreen\">MediumSeaGreen</option>\r\n" + 
					"		</select>\r\n" + 
					"		<br> \r\n" + 
					"		<input type=\"submit\" value=\"Go!\" name=\"indexButton\">\r\n" + 
					"	</form>\r\n" + 
					"</body>\r\n" + 
					"</html>");
		}		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
