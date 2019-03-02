package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.student;

public class studentDAO {
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
	public ArrayList<student> selectAllStudents() {

		ArrayList<student> studentArray = new ArrayList<student>();
		try {
			String sql = "select * from student";
			ResultSet rs = getConnection().executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {

					student individualStudent = new student();

					try {
						individualStudent.setId(rs.getInt(1));
						individualStudent.setUsername(rs.getString("username"));
						individualStudent.setPassword(rs.getString("password"));
						System.out.println(individualStudent.getUsername());

					} catch (SQLException s) {
						s.printStackTrace();
					}

					studentArray.add(individualStudent);
				}

				rs.close();
			}
		} catch (SQLException s) {
			System.out.println(s);
		}

		closeConnection();
		return studentArray;
	}
	
	public boolean insertStudent(student s) throws SQLException {

		boolean b = false;
		try {
			String sql = "insert into student (username, password) values ('" + s.getUsername() + "','" + s.getPassword() + "');";
			System.out.println(sql);
			b = getConnection().execute(sql);
			closeConnection();
			b = true;
		} catch (SQLException e) {
			throw new SQLException("Student Not Added");
		}
		return b;
	}
	

	
	public boolean updateStudent(student s, int i) throws SQLException
	{
		boolean b = false;
		try
		{
			String sql = "UPDATE student SET username = '" + s.getUsername()  + "', password = '" + s.getPassword() + "' WHERE id = " + i + ";";
			System.out.println(sql);
			b = getConnection().execute(sql);
			closeConnection();
			b = true;
		} catch (SQLException e) {
			throw new SQLException("Student Not Updated");
		}
		return b;
	}
	
	public boolean deleteStudent(int s) throws SQLException
	{
		boolean b = false;
		
		try
		{
			String sql = "DELETE FROM student WHERE id = '" + s + "';";
			System.out.println(sql);
			
			b = getConnection().execute(sql);
			closeConnection();
			
			b = true;
		} catch (SQLException e) {
			throw new SQLException("Student not Deleted");
		}
		
		return b;
	}
}