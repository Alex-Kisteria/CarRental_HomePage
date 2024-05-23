package com.example.carrental_homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CarRental_login extends AppCompatActivity {

    EditText editTextfullName, editTextEmail, editTextSubdivision, editTextBlk, editTextLot, editTextPassword;
    TextView signUpbtn;
    Button btnLogIn;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_rental_login);

        mAuth = FirebaseAuth.getInstance();

        //editTextfullName = findViewById(R.id.signUp_fullName);
        editTextEmail = findViewById(R.id.login_email);
        //editTextSubdivision = findViewById(R.id.signUp_subdivision);
        //editTextBlk = findViewById(R.id.signUp_block);
        //editTextLot = findViewById(R.id.signUp_lot);
        editTextPassword = findViewById(R.id.login_password);
        btnLogIn = findViewById(R.id.login_loginbtn);
        progressBar = findViewById(R.id.progressBar);
        signUpbtn = findViewById(R.id.clickSignUpbtn);

        signUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSignUpPage = new Intent(getApplicationContext(), CarRental_signup.class);
                startActivity(openSignUpPage);
                finish();
            }
        });


        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String fullname, email, subdivision, blk, lot, password;

                //fullname = String.valueOf(editTextfullName.getText());
                email = String.valueOf(editTextEmail.getText());
                //subdivision = String.valueOf(editTextSubdivision.getText());
                //blk = String.valueOf(editTextBlk.getText());
                //lot = String.valueOf(editTextLot.getText());
                password = String.valueOf(editTextPassword.getText());

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(CarRental_login.this,"Enter Email" ,Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(CarRental_login.this,"Enter Password" ,Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(CarRental_login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }
}