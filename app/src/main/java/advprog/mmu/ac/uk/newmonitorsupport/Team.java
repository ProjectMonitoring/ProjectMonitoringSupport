package advprog.mmu.ac.uk.newmonitorsupport;

public class Team {

    private int id;
    private int projectid;

    public Team(int id, int projectid){
        this.id = id;
        this.projectid = projectid;

    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public int getProjectid(){
        return projectid;
    }

    public void setProjectid(int projectid){
        this.projectid = projectid;
    }


}
