package in.ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.dto.Student;
import in.ineuron.service.IStudentService;
import in.ineuron.servicefactory.StudentServiceFactory;

@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doProcess(request, response);

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	public void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		System.out.println("The RequestURI is ::" + request.getRequestURI());

		System.out.println("The Path Info is::" + request.getPathInfo());

		if (request.getRequestURI().endsWith("/addform")) {

			PrintWriter out = response.getWriter();
			String sname = request.getParameter("sname");
			String sage = request.getParameter("sage");
			String saddress = request.getParameter("saddr");

			Student student = new Student();

			student.setSname(sname);
			student.setSage(Integer.parseInt(sage));
			student.setSaddress(saddress);

			IStudentService service = StudentServiceFactory.getStudentService();

			String status = service.addStudent(student);

			if (status.equals("success")) {
				request.setAttribute("status", "success");
				RequestDispatcher rsd = request.getRequestDispatcher("../insertResult.jsp");
				rsd.forward(request, response);
			} else {

				RequestDispatcher rsd = request.getRequestDispatcher("../insertResult.jsp");
				rsd.forward(request, response);
			}

			out.close();
		}

		if (request.getRequestURI().endsWith("/searchform")) {

			PrintWriter out = response.getWriter();

			String Sid = request.getParameter("sid");

			// Student student=new Student();

			IStudentService service = StudentServiceFactory.getStudentService();
			Student student = service.searchStudent(Integer.parseInt(Sid));

			RequestDispatcher rd = null;
			request.setAttribute("student", student);
			rd = request.getRequestDispatcher("/display.jsp");
			rd.forward(request, response);

			out.close();
		}

		if (request.getRequestURI().endsWith("/deleteform")) {
			PrintWriter out = response.getWriter();

			String Sid = request.getParameter("sid");

			// Student student=new Student();

			IStudentService service = StudentServiceFactory.getStudentService();
			String status = service.deleteStudent(Integer.parseInt(Sid));
			if (status.equals("success")) {
				request.setAttribute("status", "success");
				RequestDispatcher rsd = request.getRequestDispatcher("../delete.jsp");
				rsd.forward(request, response);
			} else if (status.equals("fail")) {
				request.setAttribute("status", "fail");
				RequestDispatcher rsd = request.getRequestDispatcher("../delete.jsp");
				rsd.forward(request, response);

			} else {
				request.setAttribute("status", "not found");
				RequestDispatcher rsd = request.getRequestDispatcher("../delete.jsp");
				rsd.forward(request, response);

			}
			out.close();
		}

		if (request.getRequestURI().endsWith("editform")) {
			String sid = request.getParameter("sid");

			IStudentService service = StudentServiceFactory.getStudentService();
			Student student = service.searchStudent(Integer.parseInt(sid));

			RequestDispatcher rd = null;
			if (student != null) {
				request.setAttribute("student", student);
				rd = request.getRequestDispatcher("../update.jsp");
				rd.forward(request, response);
			}
		}
		if (request.getRequestURI().endsWith("updateRecord")) {
			String sid = request.getParameter("sid");
			String sname = request.getParameter("sname");
			String sage = request.getParameter("sage");
			String saddr = request.getParameter("saddr");

			Student student = new Student();
			student.setSid(Integer.parseInt(sid));
			student.setSaddress(saddr);
			student.setSname(sname);
			student.setSage(Integer.parseInt(sage));

			IStudentService service = StudentServiceFactory.getStudentService();
			String status = service.updateStudent(student);
			PrintWriter out = response.getWriter();

			if (status.equals("success")) {
				RequestDispatcher rsd = request.getRequestDispatcher("../../updateSuccess.html");
				rsd.forward(request, response);
			} else if (status.equals("fail")) {
				RequestDispatcher rsd = request.getRequestDispatcher("../../updateFail.html");
				rsd.forward(request, response);
			} else {

				RequestDispatcher rsd = request.getRequestDispatcher("../../updateRecNotFound.html");
				rsd.forward(request, response);
			}
			out.close();

		}
	}
}
