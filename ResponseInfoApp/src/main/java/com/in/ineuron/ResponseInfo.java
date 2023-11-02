package com.in.ineuron;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/info")
public class ResponseInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		LocalDateTime date = LocalDateTime.now();

		int hour = date.getHour();
		System.out.println(hour);

		String msg = "";
		if (hour < 12) {
			msg = msg + "Good Morning";
		} else if (hour < 16) {
			msg = msg + "Good Afternoon";
		} else if (hour < 20) {
			msg = msg + "Good Evening";
		} else if (hour < 24) {
			msg = msg + "Good Night";
		}

		out.println("<h1>Welcome to Servlet Programming</h1>");
		out.print("<h1>Greeting from Servlet</h1>" + msg);

		out.println("The Servlet Request Address is :" + request.hashCode());
		out.println("The Servlet Response Address is :" + response.hashCode());
		out.println("The currrent class object is :" + this.hashCode());
		out.println("The Current Thread  Address is :" + Thread.currentThread().hashCode());
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
