package advprog.mmu.ac.uk.newmonitorsupport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TutorViewProjectActivity extends AppCompatActivity {

    int projectID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_view_project);


        Intent ids = getIntent();

        projectID = ids.getIntExtra("projectID", 0);
        System.out.println(projectID);

        StudentTeamDAO dao = new StudentTeamDAO();

        ArrayList<Project> allProjects = dao.getProject();

        //after we find the thread store it here
        Project selectedProject = new Project();

        for(Project individualProject: allProjects){
            if(individualProject.getId() == projectID){
                selectedProject = new Project(individualProject.getId(),individualProject.getUnit(),individualProject.getYear(),individualProject.getProjectname(), individualProject.getFeedback(), individualProject.getGrade());
            }
        }

        TextView projectName = findViewById(R.id.txtProjectName);
        projectName.setText("Project Name " + selectedProject.getProjectname());

        TextView projectUnit = findViewById(R.id.txtProjectUnit);
        projectUnit.setText("Unit: " + selectedProject.getUnit());

        TextView projectFeedback = findViewById(R.id.txtProjectFeedback);
        projectFeedback.setText("Tutor Feedback: " + selectedProject.getFeedback());

        //create studentDAO
        StudentDAO studentDAO =new StudentDAO();
        //grab all students and put in arraylist
        ArrayList<Student> allStudents = studentDAO.getAllStudents();



        ListView students = findViewById(R.id.studentList);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, allStudents);

        students.setAdapter(arrayAdapter);

        TutorDAO tutorDAO = new TutorDAO();

        ArrayList<Tutor> allTutors = tutorDAO.getAllTutors();

        Tutor selectedTutor = new Tutor();

        for(Tutor individualTutor : allTutors)
        {
            selectedTutor = new Tutor(individualTutor.getId(), individualTutor.getUsername(), individualTutor.getPassword());
        }

        TextView tutor = findViewById(R.id.txtViewTutor);
        tutor.setText("Your Active Tutor: " + selectedTutor.getUsername());


    }
}
