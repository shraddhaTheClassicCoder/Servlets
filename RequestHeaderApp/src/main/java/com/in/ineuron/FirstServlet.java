package com.in.ineuron;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/test")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out=response.getWriter();
		out.println("<body>");
		out.println("<center>");
		out.println("<h1 style=bgcolor:red text-align='center'>Request Header Information</h1>");
		out.println("<table border='1'>");
		out.println("<th>Header Name</th><th>Header Value</th>");
		Enumeration<String> headerNames=request.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			String headerName=(String)headerNames.nextElement();
		   Enumeration<String> value=request.getHeaders(headerName);
		   
		   out.println("<td>"+headerName+"</td>");
		   out.println("<td>"+value+"</td>");
		   out.println("</tr>");
		   
		}
		out.println("</table>");
		out.println("<center>");
		out.println("</body>");
        out.close();		
	}

}
