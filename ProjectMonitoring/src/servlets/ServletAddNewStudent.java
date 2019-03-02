package servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.student;
import models.studentDAO;

public class ServletAddNewStudent extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	// Using the doGet to serve the page with a form on a GET request
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher view = req.getRequestDispatcher("addnew.jsp");
		view.forward(req, resp);
	}
	
	// Using the doPost to handle what happens when the form is POST'ed 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		studentDAO dao = new studentDAO();
		
		String username = (String) req.getParameter("username");
		String password = (String) req.getParameter("password");
		
		student s = new student(username, password);
		try {
			boolean done = dao.insertStudent(s);
			System.out.println(done);
			if (done) {
				resp.sendRedirect("home");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}