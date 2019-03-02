package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.tutorproject;

public class tutorprojectDAO {

	Connection c = null;
	Statement s = null;
	ResultSet r = null;


	/**
	* Get Database Connection
	* @return Statement Object
	*/

	public Statement getConnection() {


	try {

	Class.forName("org.sqlite.JDBC");

	c = DriverManager.getConnection("jdbc:sqlite:projMentoring.sqlite");
	s = c.createStatement();

	} catch (SQLException e) {

	e.printStackTrace();

	} catch (ClassNotFoundException c) {

	c.printStackTrace();

	}


	return s;

	}


	/**
	* Close any open database connection
	* 
	*/

	public void closeConnection() {

	try {

	if (s != null) {

	s.close();

	}

	if (c != null) {

	c.close();

	}

	} catch (SQLException e) {

	e.printStackTrace();

	}

	}


	/**
	* Retrieve all Contacts
	* 
	* @return
	*/

	public ArrayList<tutorproject> selectAlltutorprojects() {


	ArrayList<tutorproject> TutorProjectArray = new ArrayList<tutorproject>();

	try {

	String sql = "select * from tutorproject";

	ResultSet rs = getConnection().executeQuery(sql);


	if (rs != null) {

	while (rs.next()) {


	tutorproject individualTutorProject = new tutorproject();


	try {

	individualTutorProject.setTutorid(rs.getInt(1));
	individualTutorProject.setProjectid(rs.getInt(2));
	

	System.out.println(individualTutorProject.getTutorid()+individualTutorProject.getProjectid());


	} catch (SQLException s) {

	s.printStackTrace();

	}


	TutorProjectArray.add(individualTutorProject);

	}


	rs.close();

	}

	} catch (SQLException s) {

	System.out.println(s);

	}


	closeConnection();

	return TutorProjectArray;

	}


	public boolean insertTutorProject(tutorproject t) throws SQLException {


	boolean b = false;

	try {

	String sql = "insert into tutorproject (tutorid,projectid) values ('" + t.getTutorid() + "','" + t.getProjectid()+ "');";

	System.out.println(sql);

	b = getConnection().execute(sql);

	closeConnection();

	b = true;

	} catch (SQLException e) {

	throw new SQLException("TutorProject Not Added");

	}

	return b;

	}

    /*WanoGoo: The update tutorProject shouldn't really exist as it is right now.
     *Because we are never going to change an id of an entity.But this is left
     *here as example.
     *
     *In the case, that were to happen. Ichanged the query where clause condition
     *to include both ids, as there are a composite key. This means, I've had to 
     *add a new parameter on the method declaration. Named int j. 
     * */

	public boolean updateTutorProject(tutorproject t, int i,int j) throws SQLException

	{

	boolean b = false;

	try

	{

	String sql = "UPDATE tutorproject SET tutorid = '" + t.getTutorid()  + "', projectid = '" + t.getProjectid()  + "' WHERE tutorid = " + i + " AND projectid="+j+";";

	System.out.println(sql);

	b = getConnection().execute(sql);

	closeConnection();

	b = true;

	} catch (SQLException e) {

	throw new SQLException("TutorProject Not Updated");

	}

	return b;

	}


	public boolean deletetutorproject(int i,int j) throws SQLException

	{

	boolean b = false;


	try

	{

	String sql = "DELETE FROM tutorproject WHERE tutorid = '" + i +"AND projectid= "+j+"';";

	System.out.println(sql);


	b = getConnection().execute(sql);

	System.out.println("tutorproject deleted");
	
	closeConnection();


	b = true;

	} catch (SQLException e) {

	throw new SQLException("tutorproject not Deleted");

	}


	return b;

	}

	}


