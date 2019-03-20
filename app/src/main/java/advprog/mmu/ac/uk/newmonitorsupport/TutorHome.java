package advprog.mmu.ac.uk.newmonitorsupport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TutorHome extends AppCompatActivity {

    int id;
    Project studentsProject = new Project();
    ArrayList<Thread> allThreads = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_home);


        Intent ids = getIntent();

        id = ids.getIntExtra("ID", 0);

        //create instance of dao for studentteam which goes to team then project
        StudentTeamDAO dao = new StudentTeamDAO();
        //instance of dao for thread
        ThreadDAO tdao = new ThreadDAO();
        //in order to get all the project json data we want to display when user logs in
        ArrayList<StudentTeam> studentteam = dao.getTeamID();
        ArrayList<Team> team = dao.getTeam();
        ArrayList<Project> project = dao.getProject();
        ArrayList<Thread> thread = tdao.getThreads();



        //might have more than one thread so need arraylist to store
        //ArrayList<Thread> allThreads = new ArrayList<>();

        for (StudentTeam s : studentteam) {
            if (id == s.getStudentID()) {
                StudentTeam S = new StudentTeam(s.getStudentID(), s.getTeamID());

                for (Team t : team) {
                    if (S.getTeamID() == t.getID()) {
                        Team T = new Team(t.getID(), t.getProjectid());

                        for (Project p : project) {
                            if (T.getProjectid() == p.getId()) {
                                studentsProject = new Project(p.getId(), p.getUnit(), p.getYear(), p.getProjectname(), p.getFeedback(), p.getGrade());

                                for(Thread thread1: thread){
                                    if(thread1.getProjectid() == studentsProject.getId()){
                                        Thread finalThread = new Thread(thread1.getID(),thread1.getProjectid(),thread1.getTitle(),thread1.getDate());

                                        allThreads.add(finalThread);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        TextView projectName = findViewById(R.id.txtProjectName);

        projectName.setText(studentsProject.getProjectname());



        //for the listview of threads

        ListView threadList = findViewById(R.id.threadList);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, allThreads);

        threadList.setAdapter(arrayAdapter);

        //now need to create an onItemClickListener to the cheeseList
        threadList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            //inside this method, the code to switch activity will happen. Same idea
            //will apply here for alot of the professional development app
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

                //Toast.makeText(TutorHome.this, "you pressed " + allThreads.get(i).getID(),Toast.LENGTH_SHORT).show();

                //now to create an android intent to open the DetailsActivity,
                // passing the correct cheese i clicked (selecting from the cheese object array)

                //create an intent, give it context, link it to threadActivity
                Intent intent =  new Intent(getApplicationContext(), TutorPosts.class);

                //now to put the clicked cheese object with the intent so it can be passed over
                //to that activity when it starts

                //note, will use KEY:VALUE structure to pass the object between activities
                //this means, the key = 'cheese', value = cheeseObject from arraylist,
                //using the position that's specified by the 'i' parameter in this method.
                intent.putExtra("threadID", allThreads.get(i).getID());


                //launch the activity_details
                startActivity(intent);
            }
        });

        Button viewProjectDetails = findViewById(R.id.btnViewProjectDetails);

        viewProjectDetails.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //create an intent, give it context, link it to threadActivity
                Intent intent =  new Intent(getApplicationContext(), TutorViewProjectActivity.class);

                //now to put the clicked cheese object with the intent so it can be passed over
                //to that activity when it starts

                //note, will use KEY:VALUE structure to pass the object between activities
                //this means, the key = 'cheese', value = cheeseObject from arraylist,
                //using the position that's specified by the 'i' parameter in this method.
                intent.putExtra("projectID", studentsProject.getId());


                //launch the activity_details
                startActivity(intent);

            }

        });
    }

}
