package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.thread;

public class threadDAO {
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
	public ArrayList<thread> selectAllThread() {

		ArrayList<thread> threadArray = new ArrayList<thread>();
		try {
			String sql = "select * from thread";
			ResultSet rs = getConnection().executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {

					thread individualThread = new thread();

					try {
						individualThread.setId(rs.getInt(1));
						individualThread.setProjectid(rs.getInt("projectid"));
						individualThread.setTitle(rs.getString("title"));
						individualThread.setDate(rs.getString("date"));
						System.out.println(individualThread.getProjectid());

					} catch (SQLException s) {
						s.printStackTrace();
					}

					threadArray.add(individualThread);
				}

				rs.close();
			}
		} catch (SQLException s) {
			System.out.println(s);
		}

		closeConnection();
		return threadArray;
	}

	public boolean insertThread(thread t) throws SQLException {

		boolean b = false;
		try {
			String sql = "insert into thread (projectid, title, date) values ('" + t.getProjectid() + "','" + t.getTitle() + "','" + t.getDate() + "');";
			System.out.println(sql);
			b = getConnection().execute(sql);
			closeConnection();
			b = true;
		} catch (SQLException e) {
			throw new SQLException("Thread Not Added");
		}
		return b;
	}
	

	
	public boolean updateThread(thread t, int i) throws SQLException
	{
		boolean b = false;
		try
		{
			String sql = "UPDATE thread SET projectid = " + t.getProjectid()  + ", title = '" + t.getTitle() + "', date = '" + t.getDate() + "' WHERE id = " + i + ";";
			System.out.println(sql);
			b = getConnection().execute(sql);
			closeConnection();
			b = true;
		} catch (SQLException e) {
			throw new SQLException("Thread Not Updated");
		}
		return b;
	}
	
	public boolean deleteThread(int t) throws SQLException
	{
		boolean b = false;
		
		try
		{
			String sql = "DELETE FROM thread WHERE id = '" + t + "';";
			System.out.println(sql);
			
			b = getConnection().execute(sql);
			closeConnection();
			
			b = true;
		} catch (SQLException s) {
			throw new SQLException("Thread not Deleted");
		}
		
		return b;
	}
	
}
