package com.in.conm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Testudi
 */
public class Testudi extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		ServletContext context = getServletContext();

		context.setAttribute("name", "shraddha");
		context.setAttribute("address", "pune");

		out.println("<html><heah><title>Attribute Information of context Object at application level</title></head>");
		out.println("<body>");
		out.println("<centre>");
		out.println("<table border='1'>");

		Enumeration<String> AttributeNames = context.getAttributeNames();

		out.println("<th>Attribute Name</th><th>Attribute Values</th>");

		while (AttributeNames.hasMoreElements()) {

			String AttributeName = AttributeNames.nextElement();
			Object AttributeValue =context.getAttribute(AttributeName);
			out.println("<tr>");
			out.println("<td>" + AttributeName + "</td>");
			out.println("<td>" + AttributeValue + "</td>");
			out.println("</tr>");

		}
		out.println("</table>");
		out.println("</centre>");
		out.println("</body>");
		out.println("</html>");

	}

}
