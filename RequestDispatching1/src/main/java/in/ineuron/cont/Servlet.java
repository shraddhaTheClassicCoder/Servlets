package in.ineuron.cont;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/valid")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		System.out.println("Control in the Servlet1");

		String sname = request.getParameter("username");
		String spassword = request.getParameter("password");

		if (sname.equals("shraddha") && spassword.equals("sahane")) {
			RequestDispatcher rds = request.getRequestDispatcher("/home.jsp");
			rds.forward(request, response);
		} else {
			ServletContext context = request.getServletContext();
			RequestDispatcher rds1 = context.getRequestDispatcher("/error.jsp");

			rds1.forward(request, response);
		}

	}

}
