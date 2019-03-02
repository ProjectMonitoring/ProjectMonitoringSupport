package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.tutor;

public class tutorDAO {
	Connection c = null;
	Statement s = null;
	ResultSet r = null;

	/**
	 * Get Database Connection
	 * 
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
	public ArrayList<tutor> selectAllTutors() {

		ArrayList<tutor> tutorArray = new ArrayList<tutor>();
		try {
			String sql = "select * from tutor";
			ResultSet rs = getConnection().executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {

					tutor individualTutor = new tutor();

					try {
						individualTutor.setId(rs.getInt(1));
						individualTutor.setUsername(rs.getString("username"));
						individualTutor.setPassword(rs.getString("password"));
						System.out.println(individualTutor.getUsername());

					} catch (SQLException s) {
						s.printStackTrace();
					}

					tutorArray.add(individualTutor);
				}

				rs.close();
			}
		} catch (SQLException s) {
			System.out.println(s);
		}

		closeConnection();
		return tutorArray;
	}

	public boolean insertTutor(tutor t) throws SQLException {

		boolean b = false;
		try {
			String sql = "insert into tutor (username, password) values ('" + t.getUsername() + "','" + t.getPassword() + "');";
			//String sql = "insert into tutor (id, username, password) values ('999','" + t.getUsername() + "','" + t.getPassword() + "');";
			System.out.println(sql);
			b = getConnection().execute(sql);
			closeConnection();
			b = true;
		} catch (SQLException e) {
			throw new SQLException("Tutor Not Added");
		}
		return b;
	}
	

	
	public boolean updateTutor(tutor t, int i) throws SQLException
	{
		boolean b = false;
		try
		{
			String sql = "UPDATE tutor SET username = '" + t.getUsername()  + "', password = '" + t.getPassword() + "' WHERE id = " + i + ";";
			System.out.println(sql);
			b = getConnection().execute(sql);
			closeConnection();
			b = true;
		} catch (SQLException e) {
			throw new SQLException("Tutor Not Updated");
		}
		return b;
	}
	
	public boolean deleteTutor(int t) throws SQLException
	{
		boolean b = false;
		
		try
		{
			String sql = "DELETE FROM tutor WHERE id = '" + t + "';";
			System.out.println(sql);
			
			b = getConnection().execute(sql);
			closeConnection();
			
			b = true;
		} catch (SQLException s) {
			throw new SQLException("Tutor not Deleted");
		}
		
		return b;
	}
	
}
