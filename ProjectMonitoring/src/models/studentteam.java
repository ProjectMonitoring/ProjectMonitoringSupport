package models;

public class studentteam {
	private int studentid;
    private int teamid;

	//for getting project ready to use but at time of use dont have any data to put into variables

	public studentteam() {


	}


	//WanoGoo: 
	//The statement: "for inserting, don't need to put the id in"
    //is invalid for a weak entity. As the primary key is composite.
	//It wont autoincrement, thus there no need to make this constructor
	//because it will be a repetition of the one below.

	
	//need constructor with id for updating existing

	public studentteam(int studentid, int teamid){

	this.studentid = studentid;

	this.teamid = teamid;


	}


	public int getStudentid() {

	return studentid;

	}


	public void setStudentid(int studentid) {

	this.studentid = studentid;

	}

	public int getTeamid() {

	return teamid;

	}


    public void setTeamid(int teamid) {

	this.teamid = teamid;

	}

}
