package models;

public class team {
	
	private int id;
    private int projectid;

	//for getting project ready to use but at time of use dont have any data to put into variables

	public team() {


	}


	//for inserting, don't need to put the id in
	//This constructor will only contain the foreign key projectid
	//as primary key is autoincrmented.We dont want to insert
	//a repeated version of the id.

	public team(int projectid) {

	this.projectid = projectid;

	}

	//need constructor with id for updating existing

	public team(int id, int projectid){

	this.id = id;

	this.projectid = projectid;


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

	
	
}