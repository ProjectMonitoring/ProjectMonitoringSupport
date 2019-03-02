package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.studentteam;



public class studentteamDAO {

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

	public ArrayList<studentteam> selectAllstudentteams() {


	ArrayList<studentteam> StudentTeamArray = new ArrayList<studentteam>();

	try {

	String sql = "select * from studentteam";

	ResultSet rs = getConnection().executeQuery(sql);


	if (rs != null) {

	while (rs.next()) {


	studentteam individualStudentTeam = new studentteam();


	try {

	individualStudentTeam.setStudentid(rs.getInt(1));

	individualStudentTeam.setTeamid(rs.getInt("teamid"));

	System.out.println(individualStudentTeam.getStudentid());


	} catch (SQLException s) {

	s.printStackTrace();

	}


	StudentTeamArray.add(individualStudentTeam);

	}


	rs.close();

	}

	} catch (SQLException s) {

	System.out.println(s);

	}


	closeConnection();

	return StudentTeamArray;

	}


	public boolean insertStudentTeam(studentteam t) throws SQLException {


	boolean b = false;

	try {

	String sql = "insert into studentteam (studentid,teamid) values ('" + t.getStudentid() + "','" + t.getTeamid()+ "');";

	System.out.println(sql);

	b = getConnection().execute(sql);

	closeConnection();

	b = true;

	} catch (SQLException e) {

	throw new SQLException("StudentTeam Not Added");

	}

	return b;

	}



	//dont need update, just create weak entities when needed
	//and deleted when not needed
	public boolean updateStudentTeam(studentteam t, int i) throws SQLException

	{

	boolean b = false;

	try

	{

	String sql = "UPDATE studentteam SET id = '" + t.getStudentid()  + "', teamid = '" + t.getTeamid()  + "' WHERE id = " + i + ";";

	System.out.println(sql);

	b = getConnection().execute(sql);

	closeConnection();

	b = true;

	} catch (SQLException e) {

	throw new SQLException("StudentTeam Not Updated");

	}

	return b;

	}


	public boolean deleteStudentTeam(int t) throws SQLException

	{

	boolean b = false;


	try

	{

	String sql = "DELETE FROM studentteam WHERE studentid = '" + t + "';";

	System.out.println(sql);


	b = getConnection().execute(sql);

	closeConnection();


	b = true;

	} catch (SQLException e) {

	throw new SQLException("Studentteam not Deleted");

	}


	return b;

	}

}