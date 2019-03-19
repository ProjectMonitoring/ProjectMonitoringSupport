package advprog.mmu.ac.uk.newmonitorsupport;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
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
import java.util.HashMap;

public class StudentPosts extends AppCompatActivity {

    int threadID;
    ListView postList;

    ArrayList<Post> allPosts = new ArrayList<>();

    ArrayList<Post> threadSpecificPosts = new ArrayList<Post>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_posts);

        Intent ids = getIntent();

        threadID = ids.getIntExtra("threadID", 0);
        System.out.println(threadID);

        //to use dao for threads
        ThreadDAO tdao = new ThreadDAO();

        //to store threads to filter after
        ArrayList<Thread> thread = tdao.getThreads();

        //after we find the thread store it here
        Thread selectedThread = new Thread();

        for (Thread thread1 : thread) {
            if (thread1.getID() == threadID) {
                selectedThread = new Thread(thread1.getID(), thread1.getProjectid(), thread1.getTitle(), thread1.getDate());
            }
        }

        PostDAO postDAO = new PostDAO();

        allPosts = postDAO.getPosts();

        threadSpecificPosts = new ArrayList<Post>();

        for (Post indiviudalPost : allPosts) {
            if (indiviudalPost.getThreadid() == threadID) {
                threadSpecificPosts.add(indiviudalPost);
            }
        }


        TextView threadName = findViewById(R.id.txtViewThreadTitle);

        threadName.setText(selectedThread.getTitle());

        postList = findViewById(R.id.listPosts);

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, threadSpecificPosts);

        postList.setAdapter(arrayAdapter);


        Button buttonInsert = findViewById(R.id.btnInsertPost);
        if (threadID == 9) {
            buttonInsert.setVisibility(View.GONE);
        }

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create an intent, give it context, link it to threadActivity
                Intent intent = new Intent(getApplicationContext(), postInserter.class);

                //now to put the clicked cheese object with the intent so it can be passed over
                //to that activity when it starts

                //note, will use KEY:VALUE structure to pass the object between activities
                //this means, the key = 'cheese', value = cheeseObject from arraylist,
                //using the position that's specified by the 'i' parameter in this method.
                intent.putExtra("threadID", threadID);


                //launch the activity_details
                startActivity(intent);

            }

        });

        //to make network calls on the main thread ( strict mode "hack"):
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final HashMap<String, String> params = new HashMap<>();

        if (threadID != 9) {
            postList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view,
                                               int position, long id) {
                    // TODO Auto-generated method stub

                    //postList.remove(position);

                    //arrayAdapter.notifyDataSetChanged();

                    //oast.makeText(MainActivity.this, "Item Deleted", Toast.LENGTH_LONG).show();

                    //System.out.println("worked");


                    //Toast.makeText(StudentPosts.this, "you pressed " + allPosts.get(position).getId(),Toast.LENGTH_SHORT).show();


                    //String url = "http://10.182.61.187:8005/projMonitoringdb/apiPost";
                    //performPostCall(url, params);


                    //using alert
                    AlertDialog.Builder alert = new AlertDialog.Builder(
                                    StudentPosts.this);

                    //title of alert
                    alert.setTitle("Alert");
                    //msg within the alert
                    alert.setMessage("Are you sure to delete record");

                    //if yes
                    alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //do your work here
                            dialog.dismiss();

                        }
                    });
                    //if no
                    alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });

                    //show
                    alert.show();

                    return true;
                }

            });




        }

    }




    @Override
    protected void onResume() {
        super.onResume();


        PostDAO postDAO = new PostDAO();

        allPosts = postDAO.getPosts();

        threadSpecificPosts = new ArrayList<Post>();

        for (Post indiviudalPost : allPosts) {
            if (indiviudalPost.getThreadid() == threadID) {
                threadSpecificPosts.add(indiviudalPost);
            }
        }
        postList = findViewById(R.id.listPosts);

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, threadSpecificPosts);

        postList.setAdapter(arrayAdapter);
    }
}

