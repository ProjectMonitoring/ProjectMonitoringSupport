package advprog.mmu.ac.uk.newmonitorsupport;

public class StudentTeam {

    private int studentID;
    private int teamID;

    public StudentTeam(int studentID, int teamID){
        this.studentID = studentID;
        this.teamID = teamID;

    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getTeamID(){
        return teamID;
    }

    public void setTeamID(int teamID){
        this.teamID = teamID;
    }


}
