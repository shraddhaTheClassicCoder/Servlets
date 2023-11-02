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
				RequestDispatcher rsd = request.getRequestDispatcher("../successadd.html");
				rsd.forward(request, response);
			} else {

				RequestDispatcher rsd = request.getRequestDispatcher("../successfail.html");
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

			if (student != null) {

				out.println("<body>");
				out.println("<center>");
				out.println("<table border='1' style=bgcolor:'green'>");
				out.println("<tr><th>SID</th><th>SNAME</th><th>SAGE</th><th>SADDRESS</th></tr>");
				out.println("<tr>");
				out.print("<td>" + student.getSid() + "</td>");
				out.print("<td>" + student.getSname() + "</td>");
				out.print("<td>" + student.getSage() + "</td>");
				out.print("<td>" + student.getSaddress() + "</td>");
				out.println("</tr>");
				out.println("</table>");
				out.println("</center>");
				out.println("</body>");
			}

			else {
				out.println("<h1>The Record is not available in the database with given Id</h1>" + Sid);
			}

			out.close();
		}

		if (request.getRequestURI().endsWith("/deleteform")) {
			PrintWriter out = response.getWriter();

			String Sid = request.getParameter("sid");

			// Student student=new Student();

			IStudentService service = StudentServiceFactory.getStudentService();
			String status = service.deleteStudent(Integer.parseInt(Sid));
			if (status.equals("success")) {
				RequestDispatcher rsd = request.getRequestDispatcher("../deletesuccess.html");
				rsd.forward(request, response);
			} else if (status.equals("fail")) {
				RequestDispatcher rsd = request.getRequestDispatcher("../deletefail.html");
				rsd.forward(request, response);

			} 
			out.close();
		}

		if (request.getRequestURI().endsWith("editform")) {
			String sid = request.getParameter("sid");

			IStudentService service = StudentServiceFactory.getStudentService();
			Student student = service.searchStudent(Integer.parseInt(sid));
			PrintWriter out = response.getWriter();
			if (student != null) {
				// display student records as a form data so it is editable
				out.println("<body>");
				out.println("<center>");
				out.println("<form method='post' action='./controller/updateRecord'>");
				out.println("<table>");
				out.println("<tr><th>ID</th><td>" + student.getSid() + "</td></tr>");
				out.println("<input type='hidden' name='sid' value='" + student.getSid() + "'/>");
				out.println("<tr><th>NAME</th><td><input type='text' name='sname' value='" + student.getSname()
						+ "'/></td></tr>");
				out.println("<tr><th>AGE</th><td><input type='text' name='sage' value='" + student.getSage()
						+ "'/></td></tr>");
				out.println("<tr><th>ADDRESS</th><td><input type='text' name='saddr' value='" + student.getSaddress()
						+ "'/></td></tr>");
				out.println("<tr><td></td><td><input type='submit' value='update'/></td></tr>");
				out.println("</table>");
				out.println("</form>");
				out.println("</center>");
				out.println("</body>");
			} else {
				// display not found message
				out.println("<body>");
				out.println("<h1 style='color:red;text-align:center;'>Record not avaialable for the give id :: " + sid
						+ "</h1>");
				out.println("</body>");
			}
			out.close();
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
