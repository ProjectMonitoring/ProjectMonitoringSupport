package advprog.mmu.ac.uk.newmonitorsupport;

import android.os.NetworkOnMainThreadException;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class StudentDAO {

    public ArrayList<Student> getAllStudents() {

        ArrayList<Student> students = new ArrayList<>();

        HttpURLConnection urlConnection;
        InputStream in = null;
        try
        {
            // the url we wish to connect to
            URL url = new URL("http://100.66.210.159:8005/projMonitoringdb/apiStudent");
            // open the connection to the specified URL
            urlConnection = (HttpURLConnection) url.openConnection();
            // get the response from the server in an input stream
            in = new BufferedInputStream(urlConnection.getInputStream());
        } catch(
                IOException e)
        {
            e.printStackTrace();
        }

        // covert the input stream to a string
        String response = convertStreamToString(in);
        // print the response to android monitor/log cat
        System.out.println("Server response = " + response);

        try {
            // declare a new json array and pass it the string response from the server
            // this will convert the string into a JSON array which we can the iterate
            // over using a loop
            JSONArray jsonArray = new JSONArray(response);

            // use a for loop to iterate over the JSON array
            for (int i=0; i < jsonArray.length(); i++) {
                // the following line of code will get the name of the cheese from the
                // current JSON object and store it in a string variable called name
                int id = jsonArray.getJSONObject(i).getInt("id");
                String username = jsonArray.getJSONObject(i).get("username").toString();
                String password = jsonArray.getJSONObject(i).get("password").toString();

                Student student = new Student(id, username, password);
                students.add(student);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return students;
    }

    public String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}