package advprog.mmu.ac.uk.newmonitorsupport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TutorPosts extends AppCompatActivity {

    int threadID;

    ArrayList<Post> allPosts = new ArrayList<>();

    ArrayList<Post> threadSpecificPosts = new ArrayList<Post>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_posts);


        Intent ids = getIntent();

        threadID = ids.getIntExtra("threadID", 0);
        System.out.println(threadID);

        //to use dao for threads
        ThreadDAO tdao = new ThreadDAO();

        //to store threads to filter after
        ArrayList<Thread> thread = tdao.getThreads();

        //after we find the thread store it here
        Thread selectedThread = new Thread();

        for(Thread thread1: thread){
            if(thread1.getID() == threadID){
                selectedThread = new Thread(thread1.getID(),thread1.getProjectid(),thread1.getTitle(),thread1.getDate());
            }
        }

        PostDAO postDAO = new PostDAO();

        allPosts = postDAO.getPosts();

        threadSpecificPosts = new ArrayList<Post>();

        for(Post indiviudalPost: allPosts )
        {
            if(indiviudalPost.getThreadid() == threadID)
            {
                threadSpecificPosts.add(indiviudalPost);
            }
        }


        TextView threadName = findViewById(R.id.txtViewThreadTitle);

        threadName.setText(selectedThread.getTitle());

        ListView postList = findViewById(R.id.listPosts);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, threadSpecificPosts);

        postList.setAdapter(arrayAdapter);



        Button buttonInsert = findViewById(R.id.btnInsertPost);
        if(threadID != 9)
        {
            buttonInsert.setVisibility(View.GONE);
        }


        buttonInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //create an intent, give it context, link it to threadActivity
                Intent intent =  new Intent(getApplicationContext(), postInserter.class);

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
        ListView postList = findViewById(R.id.listPosts);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, threadSpecificPosts);

        postList.setAdapter(arrayAdapter);
    }
}
