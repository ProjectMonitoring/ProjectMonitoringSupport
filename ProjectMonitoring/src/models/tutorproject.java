package models;

public class tutorproject {

	private int tutorid;
	private int projectid;

	
	
	public tutorproject(){
      		
	}
	
	//For insertion need to remove first id
    //But as weak entity there no need
	//for an extra constructor
	
	public tutorproject(int tutorid,int projectid){
		this.tutorid=tutorid;
		this.projectid=projectid;
	}
	
	public int getTutorid() {
		return tutorid;
	}


	public void setTutorid(int tutorid) {
		this.tutorid = tutorid;
	}


	public int getProjectid() {
		return projectid;
	}


	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}
	

}
