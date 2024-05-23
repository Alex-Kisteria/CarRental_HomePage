package com.example.carrental_homepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button logoutbtn;
    TextView userDetails;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        auth = FirebaseAuth.getInstance();
        logoutbtn = findViewById(R.id.main_logOutbtn);
        user = auth.getCurrentUser(); //this will get current user

        if (user == null){
            Intent goBackUser = new Intent(getApplicationContext(), CarRental_login.class);
            startActivity(goBackUser);
            finish();
        } else {
            userDetails.setText(user.getEmail());
        }

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent goBackUser = new Intent(getApplicationContext(), CarRental_login.class);
                startActivity(goBackUser);
                finish();
            }
        });
    }
}