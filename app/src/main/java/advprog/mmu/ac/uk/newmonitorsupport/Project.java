package advprog.mmu.ac.uk.newmonitorsupport;

public class Project {

    private int id;
    private String unit;
    private String year;
    private String projectname;
    private String feedback;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    private String grade;


    public Project(int id, String unit, String year, String projectname, String feedback, String grade) {
        this.id = id;
        this.unit = unit;
        this.year = year;
        this.projectname = projectname;
        this.feedback = feedback;
        this.grade = grade;
    }

    public String toString(){
        return "" +id+unit+year+projectname+feedback+grade;
    }
}
