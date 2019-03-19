package advprog.mmu.ac.uk.newmonitorsupport;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class postInserter extends AppCompatActivity {

    int threadID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_inserter);


        Intent ids = getIntent();

        threadID = ids.getIntExtra("threadID", 0);
        System.out.println(threadID);


        //to make network calls on the main thread ( strict mode "hack"):
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final EditText post = findViewById(R.id.editTextPost);
        Button button = findViewById(R.id.saveButton);

        final HashMap<String, String> params = new HashMap<>();

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // multiple parameter versions, now going to update it
                // to only accept one parameter which will send all the JSON
/*
                params.put("name", name.getText().toString());
                params.put("email", email.getText().toString());
                String url = "http://10.182.54.190:8000/add_contact";
                performPostCall(url, params);
*/

//second approach, using json and gson
                Gson gson = new Gson();
                //get strings and store in variable
                String postS = post.getText().toString();

                Post p = new Post();
                if(threadID == 6)
                {
                    //calender add later on
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat mdformat = new SimpleDateFormat("yyyy/MM/dd");
                    String strDate = mdformat.format(calendar.getTime());

                    p = new Post(threadID, strDate, postS);
                }
                else
                {
                    p = new Post(threadID, "", postS);
                }



                //now will put the class into json string
                String postJson = gson.toJson(p);

                //check the string
                System.out.println(postJson);

                //sending it with the parameter attached
                //params.put("json", contactJson);
                params.put("post", postJson);

                //for the assignment, might need to send more stuff, such as an api key
                //params.put("apikey","*apikey*");
                //url to connect
                //String url = "http://10.182.54.190:8000/add_contact_json";
                String url = "http://100.66.210.159:8005/projMonitoringdb/apiPost";
                //now to send it
                performPostCall(url, params);
            }
        });

    }

    public String performPostCall(String requestURL, HashMap<String, String> postDataParams) {
        URL url;
        String response = "";

        try {
            url = new URL(requestURL);

            //create the connection object
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
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

                Toast.makeText(this, "post Saved: :)", Toast.LENGTH_LONG).show();
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null) {
                    response += line;
                }
            } else {
                Toast.makeText(this, "Error failed to save post :(", Toast.LENGTH_LONG).show();
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
