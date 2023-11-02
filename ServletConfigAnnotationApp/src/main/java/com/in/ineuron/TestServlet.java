package com.in.ineuron;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/Test" }, initParams = { @WebInitParam(name = "url", value = "jdbc:mysql:///telusko"),
		@WebInitParam(name = "username", value = "root"),
		@WebInitParam(name = "password", value = "root") }, loadOnStartup = 10)

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Connection connection = null;
	private PreparedStatement pstmt = null;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Loaded Successfully");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void init() throws ServletException {

		String jdbcUrl = getInitParameter("url");
		String username = getInitParameter("username");
		String password = getInitParameter("password");

		try {
			connection = DriverManager.getConnection(jdbcUrl, username, password);

			System.out.println("Connection Establised Successfully");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String sname = request.getParameter("username");
		String sage = request.getParameter("userage");
		String saddress = request.getParameter("useraddr");

		PrintWriter out = response.getWriter();

		if (connection != null) {
			try {

				String SqlInsertQuery = "insert into student(sname,sage,saddress) values(?,?,?)";

				pstmt = connection.prepareStatement(SqlInsertQuery);


				if (pstmt != null) {

					pstmt.setString(1, sname);
					pstmt.setInt(2, Integer.parseInt(sage));
					pstmt.setString(3, saddress);

					int rowsAffected = pstmt.executeUpdate();

					if (rowsAffected == 1) {
						out.println("<h1 style='color:green' text-align='centre'>REGISTRATION SUCCESSFUL</h1>");

					} else {
						out.println(
								"<h1 style='color:red' text-align='centre'>REGISTRATION Failed...Please Try Again with Below Link</h1>");
						out.println("<a href='/reg.html'/>|Registration| >");
					}
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
