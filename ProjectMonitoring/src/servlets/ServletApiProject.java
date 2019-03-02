package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


import models.project;
import models.projectDAO;

public class ServletApiProject extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	projectDAO dao = new projectDAO();
	
	//to make stuff into JSON strings etc
	Gson gson = new Gson();
	PrintWriter writer;

	
	//retrieve
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ArrayList<project> allProjects = dao.selectAllProjects();

		//going to set the type to json, not HTML like in previous assignment servlets
		resp.setContentType("application/json");
		
		writer = resp.getWriter();
		
		//string to change the values in db to json
		
		String conJSON = gson.toJson(allProjects);
		
		//will write the json string to screen
		writer.write(conJSON);
		
		//close to ensure no overflow
		writer.close();
		
	}
	
	//next thing to do is to add the rest of the crud functionality
	//to the DAO of student and tutor
	//so that is insert, update, delete
	//then once that is done, i can add that functionality to this api
	
	//i might have to make a seperate api for each table though
	
	//i can also make it work on the front end for the web page just
	//to show it works and have a web solution
	
	
	//insert
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//{"username":"nick2.0","password":"password1"} works in postman
		writer = resp.getWriter();
		resp.setContentType("text/html;charset=UTF-8");
		
		//need to put the json string sent into a string firstly
		String response = req.getParameter("project");
		
		project newProject = gson.fromJson(response, project.class);

		boolean inserted = false;
			
		try
		{
			//will now insert it into the db
			inserted = dao.insertProject(newProject);
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		if(inserted)
		{
			writer.write("new project inserted");
		}
		else
		{
			writer.write("error");
		}
		
		writer.close();
	}
	
	
	
	//update
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//{"id":1,"username":"nick","password":"password"} works in postman
		
		writer = resp.getWriter();
		resp.setContentType("text/html;charset=UTF-8");
		
		String response =  req.getParameter("project");
		
		project updateProject = gson.fromJson(response, project.class);
		
		
		boolean updated = false;
		try
		{
			//will now update it into the db
			updated = dao.updateProject(updateProject, updateProject.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		if(updated)
		{
			writer.write("project: " + updateProject.getProjectname() + " updated");
		}
		else
		{
			writer.write("error");
		}
		
		writer.close();
	}
	
	
	
	//delete
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//apiStudent?id=5 works in postman
		writer = resp.getWriter();
		resp.setContentType("text/html;charset=UTF-8");
	
		int id = Integer.valueOf(req.getParameter("id"));
		
		System.out.println("project to delete: " + id);
		
		boolean deleted = false;
		
		try
		{
			deleted = dao.deleteProject(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(deleted)
		{
			writer.write("project deleted");
		}
		else
		{
			writer.write("error");
		}
		
		writer.close();
	}
	
	
	
}

