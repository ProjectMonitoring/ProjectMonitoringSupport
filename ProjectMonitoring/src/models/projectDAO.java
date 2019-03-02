package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.project;

public class projectDAO {
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
	public ArrayList<project> selectAllProjects() {

		ArrayList<project> projectArray = new ArrayList<project>();
		try {
			String sql = "select * from project";
			ResultSet rs = getConnection().executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {

					project individualProject = new project();

					try {
						individualProject.setId(rs.getInt(1));
						individualProject.setUnit(rs.getString("unit"));
						individualProject.setYear(rs.getString("year"));
						individualProject.setProjectname(rs.getString("projectname"));
						individualProject.setFeedback(rs.getString("feedback"));
						individualProject.setGrade(rs.getString("grade"));
						System.out.println(individualProject.getProjectname());

					} catch (SQLException s) {
						s.printStackTrace();
					}

					projectArray.add(individualProject);
				}

				rs.close();
			}
		} catch (SQLException s) {
			System.out.println(s);
		}

		closeConnection();
		return projectArray;
	}
	
	public boolean insertProject(project p) throws SQLException {

		boolean b = false;
		try {
			String sql = "insert into project (unit, year, projectname, feedback, grade) values ('" + p.getUnit() + "','" + p.getYear() + "', '" + p.getProjectname() + "', '" + p.getFeedback() + "', '" + p.getGrade() + "');";
			System.out.println(sql);
			b = getConnection().execute(sql);
			closeConnection();
			b = true;
		} catch (SQLException e) {
			throw new SQLException("Project Not Added");
		}
		return b;
	}
	

	
	public boolean updateProject(project p, int i) throws SQLException
	{
		boolean b = false;
		try
		{
			String sql = "UPDATE project SET unit = '" + p.getUnit()  + "', year = '" + p.getYear() + "', projectname = '" + p.getProjectname() + "', feedback = '" + p.getFeedback() + "', grade = '" + p.getGrade() + "' WHERE id = " + i + ";";
			System.out.println(sql);
			b = getConnection().execute(sql);
			closeConnection();
			b = true;
		} catch (SQLException e) {
			throw new SQLException("Project Not Updated");
		}
		return b;
	}
	
	public boolean deleteProject(int p) throws SQLException
	{
		boolean b = false;
		
		try
		{
			String sql = "DELETE FROM project WHERE id = '" + p + "';";
			System.out.println(sql);
			
			b = getConnection().execute(sql);
			closeConnection();
			
			b = true;
		} catch (SQLException e) {
			throw new SQLException("Project not Deleted");
		}
		
		return b;
	}
}
