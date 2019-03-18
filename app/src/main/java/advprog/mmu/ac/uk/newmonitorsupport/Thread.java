package advprog.mmu.ac.uk.newmonitorsupport;

public class Thread {

    private int id;
    private int projectid;
    private String title;
    private String date;

    public Thread()
    {

    }
    public Thread(int id, int projectid, String title, String date){
        this.id = id;
        this.projectid = projectid;
        this.title = title;
        this.date = date;

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

    public String toString(){
        return title + " - posted at: " + date;
    }


}
