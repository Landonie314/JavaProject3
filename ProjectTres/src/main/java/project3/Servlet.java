/*
 * Landon Jones
 * Java Project 3
 * 4/11/2023
 */
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
		//Get login credentials from the user
		String pass = request.getParameter("passWord");
		String user = request.getParameter("userName");
		request.setAttribute("passWord", pass);
		request.setAttribute("userName",user); 
		
		//Valid Credentials? If no, get sent to relogin page. Else, proceed with code.
		if(!user.equals("md")&& !pass.equals("pw")) {
			RequestDispatcher rd=request.getRequestDispatcher("/reLog.jsp");
			rd.forward(request,response);
		}
		
		//Guess what. Else
		else {
			//When index Button pressed.
			if(request.getParameter("indexButton")!=null) {
				//Dynamic web page
			response.getWriter().append("<!DOCTYPE html>\r\n" + 
					"<html>\r\n" + 
					"<head>\r\n" + 
					"<meta charset=\"ISO-8859-1\">\r\n" + 
					"<title>Homepage</title>\r\n" + 
					"</head>\r\n" +
					"</body>\r\n" ); 
			
			//Buttons and info
			response.getWriter().append(user+" here are your choices:<br>	<form action=http://localhost:8080/ProjectTres/Servlet\r\n" + 
					"		method=\"get\">\r\n" + 
					"		<input type=\"hidden\" value=\""+user+"\" name=\"userName\">\r\n" + 
					"		<input type=\"hidden\" value=\""+pass+"\" name=\"passWord\">\r\n" + 
					"		<input type=\"submit\" value=\"Predict Activity\" name=\"viewButton\">\r\n" + 
					"		<input type=\"submit\" value=\"Logout\" name=\"LogOutBtn\">\r\n" + 
					"<input type=\"submit\" value=\"Delete Instance\" name=\"deleteButton\">\r\n" + 
					"	</form>\r\n" + 
					"</body>\r\n" + 
					"</html>");			
			}
		
		
			//Log out when pressed. Located on homepage
			else if(request.getParameter("LogOutBtn")!=null) {
			pass="";
			RequestDispatcher rd=request.getRequestDispatcher("/index.html");
			rd.forward(request,response);
		}
			//Deletes a selected instance.
			else if(request.getParameter("removeButton")!=null) {
			String tmp = request.getParameter("deleter");
			int tmps = Integer.parseInt(tmp);
			myData.removeInstance((tmps-1));
			//myData.doWrite("./project3/data2.txt");
			String fort =  Integer.toString(myData.getSize());
			request.setAttribute("total", fort);
			RequestDispatcher rd=request.getRequestDispatcher("/Test.jsp");
			rd.forward(request,response);
		}
			//Despite the name, this button just takes you to the delete web page
			else if(request.getParameter("deleteButton")!=null) {
			String fort =  Integer.toString(myData.getSize());
			request.setAttribute("total", fort);
			RequestDispatcher rd=request.getRequestDispatcher("/Test.jsp");
			rd.forward(request,response);
		}		
			//Predict parameter fillout screen
			else if(request.getParameter("viewButton")!=null) {
			//forcast
			String value = "<select name=\"weather\">";
			String value2 = "<select name=\"windy\">";

			//get values of all weather options and store them in value1 variable
			String [] temp = myData.getWeather();
			for(int num=0; num < temp.length; num++) {
				value += "<option value=\""+temp[num]+"\">"+temp[num]+"</option>";
			}
			value += "</select>\r\n";
			//Create option in combobox
			String [] temp2 = {"true", "false"};
			value2 += "<option value=\"" + temp2[0] + "\">"+ temp2[0]+"</option>";
			value2 += "<option value=\"" + temp2[1] + "\">"+ temp2[1]+"</option>";
			//pass on
			request.setAttribute("dropDownOptions",value);
			request.setAttribute("dropDownOptions2", value2);
			RequestDispatcher rd=request.getRequestDispatcher("/adder.jsp");
			rd.forward(request,response);
			
		}		
		//Predict button
			else if(request.getParameter("preButton")!=null) {
			//Store all the values as strings
		String one = request.getParameter("weather");
		String two = request.getParameter("windy");
		String three = request.getParameter("humid");
		String four = request.getParameter("temper");
		//value pairs
		request.setAttribute("weather", one);
		request.setAttribute("windy", two);
		request.setAttribute("humid", three);
		request.setAttribute("temper", four);
		
		//convert from string to integer
		int humidityNum = Integer.parseInt(three);
		int temperNum = Integer.parseInt(four);
		//convert to boolean
		boolean isWind = false;
		if(two.equals("true")) {
			isWind = true;
		}
		else if(two.equals("false")) {
			isWind = false;
		}
		//call predict
		String resultAct = myData.getActivity(one, temperNum, humidityNum, isWind);
			request.setAttribute("tey", resultAct);
			RequestDispatcher rd=request.getRequestDispatcher("/result.jsp");
			rd.forward(request,response);
				}
		
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
