package models;

public class tutorstudent {
	
	private int studentid;
	private int tutorid;
	
	
	public tutorstudent(){
      		
	}
	
	//For insertion need to remove first id
    //But as weak entity there no need
	//for an extra constructor
	
	public tutorstudent(int studentid,int tutorid){
		this.studentid=studentid;
		this.tutorid=tutorid;
		
	}


	public int getstudentid() {
		return studentid;
	}


	public void setstudentid(int studentid) {
		this.studentid = studentid;
	}


	public int gettutorid() {
		return tutorid;
	}


	public void settutorid(int tutorid) {
		this.tutorid = tutorid;
	}
	

}
