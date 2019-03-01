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

    Boolean idTutor;
    Boolean idStudent;

    EditText user;
    EditText pass;
    TextView message;

    TutorDAO dao= new TutorDAO();
    StudentDAO sdao=new StudentDAO();

    //New arraylist in loop
    //ArrayList<String> tusernames=dao.getTutorUsernames();
    //ArrayList<String> tpasswords=dao.getTutorPasswords() ;

    ArrayList<Tutor> t = dao.getAllTutors();
    ArrayList<Student> s=sdao.getAllStudents();
   // ArrayList<String> spasswords=sdao.getStudentPasswords() ;

    String[] tutors = {};
    String[] students = {};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);


        System.out.println("Login Page loaded");

    }




    public void onClick_Login(View v) {
        Intent ids=getIntent();
        //ids.getBooleanExtra("idTutor",true);
        //ids.getBooleanExtra("idStudent",true);



        user = findViewById(R.id.editUsername);
        pass = findViewById(R.id.editPassword);
        message = findViewById(R.id.message_id);

        String username = user.getText().toString();
        String password = pass.getText().toString();
        String mess = message.getText().toString();

        //Check if parameters match Value.

        /*Store json. arraylist  sperate values.
        and iterate over paswords,*/



            //If id Tutor recieved iterate over the json Array
           boolean youAreTutor=  ids.getBooleanExtra("idTutor",true);
           System.out.println(youAreTutor);
           boolean youAreStudent =  ids.getBooleanExtra("idStudent",true);
           System.out.println(youAreStudent);

        if(youAreTutor==true) {
            for (Tutor tutor : t) {
                if (tutor.getUsername().equals(username)) {
                    if (tutor.getPassword().equals(password)) {
                        Intent intent = new Intent(this, TutorPage.class);
                        intent.putExtra("tutorID",tutor.getId());
                        startActivity(intent);



                    }
                }
            }


        }
        if(youAreStudent==true) {
            for (Student student : s) {
                if (student.getUsername().equals(username)) {
                    if (student.getPassword().equals(password)) {
                        Intent intent = new Intent(this, StudentPage.class);
                        intent.putExtra("studentID",student.getId());

                        System.out.println("THE CURRENT ID IS !!!!!!!!! :" +student.getId());
                        startActivity(intent);

                        }
                    }
                }
            }
        }//end of login method
    };//end of class

