package advprog.mmu.ac.uk.newmonitorsupport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.Serializable;

public class Tutor extends AppCompatActivity implements Serializable {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int id ;
    private String username;
    private String password;


    public Tutor(int id, String username, String password){
        this.username=username;
        this.password=password;
    }

    public String getId() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }












}
