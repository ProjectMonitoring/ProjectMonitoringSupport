package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.tutorstudent;

public class tutorstudentDAO {
	
	

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

	public ArrayList<tutorstudent> selectAllTutorStudents() {


	ArrayList<tutorstudent> TutorStudentArray = new ArrayList<tutorstudent>();

	try {

	String sql = "select * from TutorStudent";

	ResultSet rs = getConnection().executeQuery(sql);


	if (rs != null) {

	while (rs.next()) {


	tutorstudent individualTutorStudent = new tutorstudent();


	try {
	individualTutorStudent.setstudentid(rs.getInt(1));
	individualTutorStudent.settutorid(rs.getInt("tutorid"));
	
	

	System.out.println(individualTutorStudent.gettutorid()+individualTutorStudent.getstudentid());


	} catch (SQLException s) {

	s.printStackTrace();

	}


	TutorStudentArray.add(individualTutorStudent);

	}


	rs.close();

	}

	} catch (SQLException s) {

	System.out.println(s);

	}


	closeConnection();

	return TutorStudentArray;

	}


	public boolean insertTutorStudent(tutorstudent t) throws SQLException {


	boolean b = false;

	try {

	String sql = "insert into TutorStudent (tutorid,studentid) values ('" + t.gettutorid() + "','" + t.getstudentid()+ "');";

	System.out.println(sql);

	b = getConnection().execute(sql);

	closeConnection();

	b = true;

	} catch (SQLException e) {

	throw new SQLException("TutorStudent Not Added");

	}

	return b;

	}

    /*WanoGoo: The update TutorStudent shouldn't really exist as it is right now.
     *Because we are never going to change an id of an entity.But this is left
     *here as example.
     *
     *In the case, that were to happen. Ichanged the query where clause condition
     *to include both ids, as there are a composite key. This means, I've had to 
     *add a new parameter on the method declaration. Named int j. 
     * */

	public boolean updateTutorStudent(tutorstudent t, int i,int j) throws SQLException

	{

	boolean b = false;

	try

	{

	String sql = "UPDATE TutorStudent SET tutorid = '" + t.gettutorid()  + "', studentid = '" + t.getstudentid()  + "' WHERE tutorid = " + i + "AND studentid="+j+";";

	System.out.println(sql);

	b = getConnection().execute(sql);

	closeConnection();

	b = true;

	} catch (SQLException e) {

	throw new SQLException("TutorStudent Not Updated");

	}

	return b;

	}


	public boolean deleteTutorStudent(int i,int j) throws SQLException

	{

	boolean b = false;


	try

	{

	String sql = "DELETE FROM TutorStudent WHERE tutorid = '" + i +"AND studentid= "+j+"';";

	System.out.println(sql);


	b = getConnection().execute(sql);

	System.out.println("TutorStudent deleted");
	
	closeConnection();


	b = true;

	} catch (SQLException e) {

	throw new SQLException("TutorStudent not Deleted");

	}


	return b;

	}

	}


	
