package com.in.ineuron;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DBCommServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection connection = null;
	Statement stmt = null;
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded Successfully");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	public void init() {

		String url = getInitParameter("url");
		String username = getInitParameter("username");
		String password = getInitParameter("password");

		try {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Establised Successfully");

			String sqlSelectQuery = "select sid,sname,sage,saddress from student";

			if (connection != null) {
				Statement stmt = connection.createStatement();

			}

			if (stmt != null) {
				ResultSet res = stmt.executeQuery(sqlSelectQuery);
				System.out.println("SID" + "\t" + "SNAME" + "\t" + "SAGE" + "\t" + "SADDRESS");

				if (res != null) {
					while (res.next()) {

						int sid = res.getInt(1);
						String sname = res.getString(2);
						int sage = res.getInt(3);
						String saddress = res.getString(4);

						System.out.println(sid + "\t" + sname + "\t" + sage + "\t" + saddress);
					}
				}
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out=response.getWriter();
		
		out.println("<h1>Request type is :: " + request.getMethod() + "</h1>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1 style=bgcolor:red text-align='center'>Student Information From Database</h1>");
		out.println("<table border='1'>");
		out.println("<th>Student ID</th><th>Studnet Name</th><th>Studnet Age</th><th>Studnet Address</th>");

		Statement stmt = null;
		ResultSet res = null;

		try {

			if (connection != null) {
				stmt = connection.createStatement();

			}

			String sqlSelectQuery = "select sid,sname,sage,saddress from student";

			if (stmt != null) {
				res = stmt.executeQuery(sqlSelectQuery);

				while (res.next()) {
					out.println("</tr>");
					out.println("<td>" + res.getInt(1) + "</td>");
					out.println("<td>" + res.getString(2) + "</td>");
					out.println("<td>" + res.getInt(3) + "</td>");
					out.println("<td>" + res.getString(4) + "</td>");

					out.println("</tr>");
				}
			}

			out.println("</table>");
			out.println("<center>");
			out.println("</body>");
			out.close();
			
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
