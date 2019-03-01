package advprog.mmu.ac.uk.newmonitorsupport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class StudentPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_page);

        Intent ids=getIntent();

        int id = ids.getIntExtra("studentID",0);

        //console test for studentteam DAO

        StudentTeamDAO dao = new StudentTeamDAO();

        ArrayList<StudentTeam> studentteam = dao.getTeamID();
        ArrayList<Team> team = dao.getTeam();
        ArrayList<Project> projecr = dao.getProject();

        ArrayList<Project> finalProjects = new ArrayList<>();

        for(StudentTeam s: studentteam){
            if(id == s.getStudentID()){
                StudentTeam S = new StudentTeam(s.getStudentID(),s.getTeamID());

                for(Team t : team){
                    if(S.getTeamID() == t.getID()){
                        Team T = new Team(t.getID(),t.getProjectid());

                        for(Project p : projecr){
                            if(T.getProjectid() == p.getId()){
                                Project P = new Project(p.getId(),p.getUnit(),p.getYear(),p.getProjectname(),p.getFeedback(),p.getGrade());

                                finalProjects.add(P);
                            }
                        }
                    }
                }
            }
            else{
                System.out.println(id + " does not match ID within StudentTeam id" + s.getStudentID());
            }
        }

        ListView projlist = findViewById(R.id.projectList);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, finalProjects);

        projlist.setAdapter(arrayAdapter);
    }
}
