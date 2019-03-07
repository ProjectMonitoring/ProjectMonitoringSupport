package advprog.mmu.ac.uk.newmonitorsupport;

public class Post {

    private int id;
    private int threadid;
    private String date;
    private String text;

    public Post(int id, int threadid, String date, String text){
        this.id = id;
        this.threadid = threadid;
        this.date = date;
        this.text = text;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getThreadid() {
        return threadid;
    }

    public void setThreadid(int threadid) {
        this.threadid = threadid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }



    public String toString(){
        return "Posted at: " + date + " - " + text;
    }


}
