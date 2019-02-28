package advprog.mmu.ac.uk.newmonitorsupport;

import android.content.Intent;
import android.os.StrictMode;
import android.view.View.OnClickListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    Button tutorbtn;
    Button studentbtn;
    Button goLoginbtn;

    Boolean idTutor;
    Boolean idStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        //If user click. This method is executed.
        tutorbtn = findViewById(R.id.tutorbtn);
        studentbtn = findViewById(R.id.studentbtn);


        tutorbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                idTutor = true;

                Intent tutor = new Intent(MainActivity.this, LoginPage.class);
                tutor.putExtra("idTutor", idTutor);
                startActivity(tutor);
                System.out.println("Tutor button clicked");

                //call getIntent() method in target activity
                //use Intent.getStringExtra(String idTutor)
                //Toast.makeText(LoginPage.this,idTutor);
            }
        });


        studentbtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                idStudent = true;

                Intent student = new Intent(MainActivity.this, LoginPage.class);
                student.putExtra("idStudent", idStudent);
                startActivity(student);
                System.out.println("Student button clicked");
                //Toast.makeText(LoginPage.this,idStudent);
            }


        });

    }
/* WanoGoo- 27th Feb 2019
*   Ideally, a temporal Activity that only appears for afirst time user would be great.
*   This a temporal fix. It behaves like the other methods except it has no reason to
*   pass a variable. If we cant achieve that feature then, it may be best to make
*   the login page the Mainactivity. */

    public void goLogin(View view){

        Button goLoginbtn=findViewById(R.id.goLoginbtn);

        Intent login= new Intent(this,LoginPage.class);
        startActivity(login);
        //Toast.makeText(LoginPage.this,idStudent);
    }



}
