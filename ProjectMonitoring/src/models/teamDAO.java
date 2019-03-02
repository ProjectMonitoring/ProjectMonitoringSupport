package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.team;


public class teamDAO {

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

	public ArrayList<team> selectAllteams() {


	ArrayList<team> teamArray = new ArrayList<team>();

	try 
	{

		String sql = "select * from team";

		ResultSet rs = getConnection().executeQuery(sql);


		if (rs != null) 
		{
			while (rs.next()) 
			{
				team individualTeam = new team();
				try 
				{
					individualTeam.setId(rs.getInt(1));
					individualTeam.setProjectid(rs.getInt(1));
					System.out.println(individualTeam.getId());
				} 
				catch (SQLException s) {
					s.printStackTrace();
				}
				teamArray.add(individualTeam);
			}
		rs.close();
		}

	} catch (SQLException s) {
	System.out.println(s);
	}
	closeConnection();
	return teamArray;
	}


	public boolean insertTeam(team t) throws SQLException {

	boolean b = false;

	try {

		String sql = "insert into team (projectid) values ('" + t.getProjectid() + "');";
	
		System.out.println(sql);
	
		b = getConnection().execute(sql);
	
		closeConnection();
	
		b = true;

	} catch (SQLException e) {
		throw new SQLException("Team Not Added");
	}
	return b;
	}




	public boolean updateTeam(team t, int i) throws SQLException
	{
		boolean b = false;
		try
		{
			String sql = "UPDATE team SET projectid = '" + t.getProjectid()  + "' WHERE id = " + i + ";";
			System.out.println(sql);
			b = getConnection().execute(sql);
			closeConnection();
			b = true;
		} catch (SQLException e) {
			throw new SQLException("team Not Updated");
			}
	return b;
	}


	public boolean deleteTeam(int t) throws SQLException
	{
		boolean b = false;
		try
		{
			String sql = "DELETE FROM team WHERE id = '" + t + "';";
			System.out.println(sql);
			b = getConnection().execute(sql);
			closeConnection();
			b = true;
		} catch (SQLException e) {
	throw new SQLException("team not Deleted");
	}


	return b;

	}
}

	


