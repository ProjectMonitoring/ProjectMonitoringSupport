package advprog.mmu.ac.uk.newmonitorsupport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class StudentPage extends AppCompatActivity {

    String[] threadNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_page);

        Intent ids=getIntent();

        int id = ids.getIntExtra("ID",0);

        //console test for studentteam DAO

        StudentTeamDAO dao = new StudentTeamDAO();
        ThreadDAO tdao = new ThreadDAO();

        ArrayList<StudentTeam> studentteam = dao.getTeamID();
        ArrayList<Team> team = dao.getTeam();
        ArrayList<Project> project = dao.getProject();
        ArrayList<Thread> thread = tdao.getThreads();

        ArrayList<Project> finalProjects = new ArrayList<>();

        ArrayList<Thread> allThreads = new ArrayList<>();

        //for retrieving projects

        for(StudentTeam s: studentteam){
            if(id == s.getStudentID()){
                StudentTeam S = new StudentTeam(s.getStudentID(),s.getTeamID());

                for(Team t : team){
                    if(S.getTeamID() == t.getID()){
                        Team T = new Team(t.getID(),t.getProjectid());

                        for(Project p : project){
                            if(T.getProjectid() == p.getId()){
                                Project P = new Project(p.getId(),p.getUnit(),p.getYear(),p.getProjectname(),p.getFeedback(),p.getGrade());

                                for(Thread thread1: thread){
                                    if(thread1.getProjectid() == P.getId()){
                                        Thread finalThread = new Thread(thread1.getID(),thread1.getProjectid(),thread1.getTitle(),thread1.getDate());

                                        allThreads.add(finalThread);
                                    }
                                }


                            }
                        }
                    }
                }
            }
            else{
                System.out.println(id + " does not match ID within StudentTeam id" + s.getStudentID());
            }
        }





        ListView projlist = findViewById(R.id.projectLis);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, allThreads);

        projlist.setAdapter(arrayAdapter);
    }
}
