package advprog.mmu.ac.uk.newmonitorsupport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class LoginPage extends AppCompatActivity {


    //Retrieve idVariables from MainActivity.

    EditText user;
    EditText pass;
    //TextView message;


    //global variable of chosenOption
    String chosenOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        //need to get the string sent that determines what button was chosen
        Bundle extras = getIntent().getExtras();

        //store that chosen option in global variable chosenOption
        chosenOption = (String) extras.get("chosen");
    }




    public void onClick_Login(View v) {

        //need to get the inputs from the user, so attach the editTexts to variables
        user = findViewById(R.id.editUsername);
        pass = findViewById(R.id.editPassword);
        //message = findViewById(R.id.message_id);

        //store the inputs from both boxes
        String username = user.getText().toString();
        String password = pass.getText().toString();
        //String mess = message.getText().toString();

        //if the option chosen was tutor
        if (chosenOption.equals("Tutor"))
        {
            //create a tutorDAO
            TutorDAO tutorDAO= new TutorDAO();
            //grab all tutors and store in arraylist
            ArrayList<Tutor> allTutors = tutorDAO.getAllTutors();

            //for one to however many tutors grabbed
            for(Tutor individualTutor : allTutors)
            {
                //check if the username and passwords match what are in the database
                if (individualTutor.getUsername().equals(username) && individualTutor.getPassword().equals(password))
                {
                    //prepare new intent
                    Intent intent = new Intent(this, TutorPage.class);
                    //send the id of the tutor
                    intent.putExtra("ID",individualTutor.getId());
                    //start that activity
                    startActivity(intent);
                }
            }
        }
        //if option chosen was student
        if (chosenOption.equals("Student"))
        {
            //create studentDAO
            StudentDAO studentDAO =new StudentDAO();
            //grab all students and put in arraylist
            ArrayList<Student> allStudents = studentDAO.getAllStudents();

            //for one to however many students
            for(Student individualStudent : allStudents)
            {
                //check if match whats in database
                if (individualStudent.getUsername().equals(username) && individualStudent.getPassword().equals(password))
                {
                    //prepare intent
                    Intent intent = new Intent(this, StudentHome.class);
                    //send appropriate id
                    intent.putExtra("ID",individualStudent.getId());
                    //start activity
                    startActivity(intent);
                }
            }
        }
        else
        {
            System.out.println("not worked");
        }

        }//end of login method


    };//end of class

