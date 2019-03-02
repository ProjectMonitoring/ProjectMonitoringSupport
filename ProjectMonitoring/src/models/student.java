package models;

public class student {

	private int id;
	private String username;
	private String password;
	
	//constructor with nothing
	public student() {
		
	}
	//constructor with id
	public student(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	//constructor without id / will autoincrement if chosen this one is the intention
	public student(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
