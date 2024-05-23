package com.example.carrental_homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class CarRental_signup extends AppCompatActivity {

    EditText editTextfullName, editTextEmail, editTextSubdivision, editTextBlk, editTextLot, editTextPassword;
    TextView loginbtn;
    Button btnSignUp;
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
        setContentView(R.layout.activity_car_rental_signup);

        mAuth = FirebaseAuth.getInstance();

        editTextfullName = findViewById(R.id.signUp_fullName);
        editTextEmail = findViewById(R.id.signUp_email);
        editTextSubdivision = findViewById(R.id.signUp_subdivision);
        editTextBlk = findViewById(R.id.signUp_block);
        editTextLot = findViewById(R.id.signUp_lot);
        editTextPassword = findViewById(R.id.signUp_password);
        btnSignUp = findViewById(R.id.signUp_SUbtn);
        progressBar = findViewById(R.id.progressBar);
        loginbtn = findViewById(R.id.clickLogInbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openLogInPage = new Intent(getApplicationContext(), CarRental_login.class);
                startActivity(openLogInPage);
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                String fullname, email, subdivision, blk, lot, password;

                fullname = String.valueOf(editTextfullName.getText());
                email = String.valueOf(editTextEmail.getText());
                subdivision = String.valueOf(editTextSubdivision.getText());
                blk = String.valueOf(editTextBlk.getText());
                lot = String.valueOf(editTextLot.getText());
                password = String.valueOf(editTextPassword.getText());

                if (TextUtils.isEmpty(fullname)){
                    Toast.makeText(CarRental_signup.this,"Enter Full Name" ,Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(CarRental_signup.this,"Enter Email" ,Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(subdivision)){
                    Toast.makeText(CarRental_signup.this,"Enter Subdivision" ,Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(blk) && TextUtils.isEmpty(lot)){
                    Toast.makeText(CarRental_signup.this,"Enter Block & Lot" ,Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(CarRental_signup.this,"Enter Password" ,Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);

                                if (task.isSuccessful()) {
                                    Toast.makeText(CarRental_signup.this, "Account Created",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(CarRental_signup.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });
    }
}