package advprog.mmu.ac.uk.newmonitorsupport;

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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class StudentPosts extends AppCompatActivity {

    int threadID;
    ListView postList;

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

        final ArrayList<Post> allPosts = postDAO.getPosts();

        ArrayList<Post> threadSpecificPosts = new ArrayList<Post>();

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

        final HashMap<String, Integer> params = new HashMap<>();

        if (threadID != 9) {
            postList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view,
                                               int position, long id) {
                    // TODO Auto-generated method stub

                    //postList.remove(position);

                    //arrayAdapter.notifyDataSetChanged();

                    //oast.makeText(MainActivity.this, "Item Deleted", Toast.LENGTH_LONG).show();

                    System.out.println("worked");


                    Toast.makeText(StudentPosts.this, "you pressed " + allPosts.get(position).getId(),Toast.LENGTH_SHORT).show();


                    params.put("id", allPosts.get(position).getId());

                    String url = "http://100.66.210.159:8005/projMonitoringdb/apiPost";

                    performPostCall(url, params);

                    return true;
                }

            });

        }
    }
    public String performPostCall(String requestURL, HashMap<String, Integer> postDataParams) {
        URL url;
        String response = "";

        try {
            url = new URL(requestURL);

            //create the connection object
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("DELETE");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //write/send/post data to the connection using output stream and buffered writer

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            //write / send / post key / value data (url encoded) to the server
            writer.write(getPostDataString(postDataParams));

            //clear the writer
            writer.flush();
            writer.close();

            //close output stream
            os.close();

            //get the serve response code to determine what to do next (i.e success / erro)
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode = " + responseCode);

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                Toast.makeText(this, "post deleted", Toast.LENGTH_LONG).show();
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                Toast.makeText(this, "Error failed to delete post", Toast.LENGTH_LONG).show();
                response = "";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("response = " + response);
        return response;
    }

    //this method converts a hashmap to a URL query string of key/value pairs
    //e.g: name = kaleem&job=tutors)
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry: params.entrySet())
        {
            if (first)
            {
                first = false;
            }
            else
            {
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();

    }

}

}
