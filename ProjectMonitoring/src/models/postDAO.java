package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.post;

public class postDAO {
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
	public ArrayList<post> selectAllPosts() {

		ArrayList<post> postArray = new ArrayList<post>();
		try {
			String sql = "select * from post";
			ResultSet rs = getConnection().executeQuery(sql);

			if (rs != null) {
				while (rs.next()) {

					post individualPost = new post();

					try {
						individualPost.setId(rs.getInt(1));
						individualPost.setThreadid(rs.getInt("threadid"));
						individualPost.setDate(rs.getString("date"));
						individualPost.setText(rs.getString("text"));
						System.out.println(individualPost.getText());

					} catch (SQLException s) {
						s.printStackTrace();
					}

					postArray.add(individualPost);
				}

				rs.close();
			}
		} catch (SQLException s) {
			System.out.println(s);
		}

		closeConnection();
		return postArray;
	}
	
	public boolean insertPost(post p) throws SQLException {

		boolean b = false;
		try {
			String sql = "insert into post (threadid, date, text) values ('" + p.getThreadid() + "','" + p.getDate() + "', '" + p.getText() + "');";
			System.out.println(sql);
			b = getConnection().execute(sql);
			closeConnection();
			b = true;
		} catch (SQLException e) {
			throw new SQLException("Post Not Added");
		}
		return b;
	}
	

	
	public boolean updatePost(post p, int i) throws SQLException
	{
		boolean b = false;
		try
		{
			String sql = "UPDATE post SET threadid = '" + p.getThreadid()  + "', date = '" + p.getDate() + "', text = '" + p.getText() + "' WHERE id = " + i + ";";
			System.out.println(sql);
			b = getConnection().execute(sql);
			closeConnection();
			b = true;
		} catch (SQLException e) {
			throw new SQLException("Post Not Updated");
		}
		return b;
	}
	
	public boolean deletePost(int p) throws SQLException
	{
		boolean b = false;
		
		try
		{
			String sql = "DELETE FROM post WHERE id = '" + p + "';";
			System.out.println(sql);
			
			b = getConnection().execute(sql);
			closeConnection();
			
			b = true;
		} catch (SQLException e) {
			throw new SQLException("Post not Deleted");
		}
		
		return b;
	}
}
