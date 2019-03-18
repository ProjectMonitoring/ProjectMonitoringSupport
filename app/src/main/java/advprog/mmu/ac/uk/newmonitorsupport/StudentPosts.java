package advprog.mmu.ac.uk.newmonitorsupport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentPosts extends AppCompatActivity {

    int threadID;
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

        for(Thread thread1: thread){
            if(thread1.getID() == threadID){
                selectedThread = new Thread(thread1.getID(),thread1.getProjectid(),thread1.getTitle(),thread1.getDate());
            }
        }

        PostDAO postDAO = new PostDAO();

        ArrayList<Post> allPosts = postDAO.getPosts();

        ArrayList<Post> threadSpecificPosts = new ArrayList<Post>();

        for(Post indiviudalPost: allPosts )
        {
            if(indiviudalPost.getThreadid() == threadID)
            {
                threadSpecificPosts.add(indiviudalPost);
            }
        }


        TextView threadName = findViewById(R.id.txtViewThreadTitle);

        threadName.setText(selectedThread.getTitle());

        TextView threadDate = findViewById(R.id.txtViewThreadDate);

        threadDate.setText(selectedThread.getDate());


        ListView postList = findViewById(R.id.listPosts);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, threadSpecificPosts);

        postList.setAdapter(arrayAdapter);




    }
}
