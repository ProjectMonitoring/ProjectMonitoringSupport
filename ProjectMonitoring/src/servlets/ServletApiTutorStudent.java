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


import models.tutorstudent;
import models.tutorstudentDAO;

public class ServletApiTutorStudent extends HttpServlet{
	


	private static final long serialVersionUID = 1L;


	tutorstudentDAO dao = new tutorstudentDAO();


	//to make stuff into JSON strings etc

	Gson gson = new Gson();

	PrintWriter writer;



	//retrieve


	@Override

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


	ArrayList<tutorstudent> allTutorStudents = dao.selectAllTutorStudents();


	//going to set the type to json, not HTML like in previous assignment servlets

	resp.setContentType("application/json");


	writer = resp.getWriter();


	//string to change the values in db to json


	String conJSON = gson.toJson(allTutorStudents);


	//will write the json string to screen

	writer.write(conJSON);


	//close to ensure no overflow

	writer.close();


	}


	//insert


	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	//The statement below is how it data here would be displayed in json
	//{"username":"nick2.0","password":"password1"} works in postman

	writer = resp.getWriter();

	resp.setContentType("text/html;charset=UTF-8");


	//need to put the json string sent into a string firstly

	String response = req.getParameter("TutorStudent");


	tutorstudent newTutorStudent = gson.fromJson(response, tutorstudent.class);


	boolean inserted = false;


	try

	{

	//will now insert it into the db

	inserted = dao.insertTutorStudent(newTutorStudent);

	} catch (SQLException e) {

	e.printStackTrace();

	}


	if(inserted)

	{

	writer.write("new TutorStudent inserted");

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


	String response =  req.getParameter("TutorStudent");


	tutorstudent updateTutorStudent = gson.fromJson(response, tutorstudent.class);



	boolean updated = false;

	try

	{

	//will now update it into the db

	updated = dao.updateTutorStudent(updateTutorStudent, updateTutorStudent.getstudentid(),updateTutorStudent.gettutorid());

	} catch (SQLException e) {

	e.printStackTrace();

	}


	if(updated)

	{

	writer.write("TutorStudent: " + updateTutorStudent.getstudentid() +  updateTutorStudent.gettutorid() +" updated");

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


	int tutorid = Integer.valueOf(req.getParameter("tutorid"));
	int studentid = Integer.valueOf(req.getParameter("studentid"));


	System.out.println("TutorStudent to delete: " + tutorid+""+studentid );


	boolean deleted = false;


	try

	{

	deleted = dao.deleteTutorStudent(tutorid,studentid);

	} catch (SQLException e) {

	e.printStackTrace();

	}


	if(deleted)

	{

	writer.write("TutorStudent deleted");

	}

	else

	{

	writer.write("error");

	}


	writer.close();

	}




	}


	
	

