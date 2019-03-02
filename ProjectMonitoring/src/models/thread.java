package models;

public class thread {
	private int id;
	private int projectid;
	private String title;
	private String date;
	
	public thread() {
		
	}
	public thread(int projectid, String title, String date) {
		this.projectid = projectid;
		this.title = title;
		this.date = date;
	}
	public thread(int id, int projectid, String title, String date) {
		this.id = id;
		this.projectid = id;
		this.title = title;
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProjectid() {
		return projectid;
	}
	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
