package com.in.ineuron;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DispServelet
 */
public class DispServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	static {
		System.out.println("DispServelet .class file is loading...");
	}

	public DispServelet() {

		System.out.println("DispServelet class is instantiated...");
	}

	@Override
	public void init() throws ServletException {

		System.out.println("DispServelet is initiated...");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		out.println("<html><head><title>OUTPUT</title></head>");

		out.println("<body>");
		out.println("<table border='1'>");
		out.println("<centre>");
		out.println("<th>INIT PARAMATERS</th><th>INIT VALUES</th>");

		ServletContext context = getServletContext();

		Enumeration<String> parameterNames = context.getInitParameterNames();

		while (parameterNames.hasMoreElements()) {
			out.println("<tr>");

			String ParameterName = parameterNames.nextElement();

			String Value = context.getInitParameter(ParameterName);

			out.println("<td>" + ParameterName + "</td>");
			out.println("<td>" + Value + "</td>");
			out.println("</tr>");
		}
		out.println("</centre>");
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
       
	}



}
