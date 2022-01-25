package com.example.byblos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    //Variables
    private Button login;
    private TextInputLayout email, password;
    private int admE = 0;
    private int admP = 0;
    private int empE = 0;
    private int empP = 0;
    private int cusE = 0;
    private int cusP = 0;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.usernameField);
        password = findViewById(R.id.passwordField);
        login = findViewById(R.id.loginButton);

        mAuth = FirebaseAuth.getInstance();
        String emailStr = email.getEditText().getText().toString();
        String passwordStr = password.getEditText().getText().toString();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail() | !validatePassword()){
                    return;
                }
                else if ((admE == 1) && (admP ==1)){
                    Intent intent = new Intent(Login.this, AdminEdits.class);
                    startActivity(intent);
                    finish();
                }

                else if ((empE == 1) && (empP ==1)){
                    Intent intent = new Intent(Login.this, SelectBranchEmployee.class);
                    startActivity(intent);
                    finish();
                }

                else if ((cusE == 1) && (cusP ==1)){
                    Intent intent = new Intent(Login.this, SelectBranchCustomer.class);
                    startActivity(intent);
                    finish();
                }


                else {
                    login(v);
                }
            }
        });
    }

    // important for testing!
    public Login(Context context){

    }

    public Login(){}

    public void openSignUpActivity (View view) {
        Intent intent = new Intent(this, SignUp.class) ;
        startActivity(intent);
        finish();
    }

    private void login (View v){

        if (!validateEmail() | !validatePassword()){
            return;
        }

        //hardcoded
        else if ((admE == 1) && (admP ==1)){
            Intent intent = new Intent(Login.this, SelectBranch.class);
            startActivity(intent);
            finish();
        }

        else if ((empE == 1) && (empP ==1)){
            Intent intent = new Intent(Login.this, SelectBranchEmployee.class);
            startActivity(intent);
            finish();
        }


        //fetched from db
        else {
            searchUser();
        }
    }


    private void searchUser(){
        String email_input = email.getEditText().getText().toString();
        //cleaning up email from all unnecessary dots before @
        StringBuilder sb = new StringBuilder(email_input);
        int check= sb.indexOf(".");
        while(check>=0) {
            sb.deleteCharAt(sb.indexOf("."));
            check = sb.indexOf(".");
        }
        String uid = sb.toString();

        String password_input = password.getEditText().getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        //fetching user from DB
        Query searchUser = reference.orderByChild("uid").equalTo(uid);

        //Fetching user data from DB
        searchUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    //password fetched from DB
                    String passwordFromDB = snapshot.child(uid).child("password").getValue(String.class);
                    if (passwordFromDB.equals(password_input)){
                        //user type (employee="yes"||"no") fetched from the DB
                        String employee = snapshot.child(uid).child("employee").getValue(String.class);
                        //first name fetched from DB
                        String firstName = snapshot.child(uid).child("firstName").getValue(String.class);
                        //opens employee
                        Intent intent = new Intent(Login.this, WelcomeScreen.class);
                        intent.putExtra("firstName", firstName);
                        intent.putExtra("employee", employee);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    boolean validateEmail() {
        String emailChk = email.getEditText().getText().toString();
        if (emailChk.isEmpty()) {
            email.setError("Email cannot be empty");
            return false;
        }
        //this string represents the email pattern
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String adm = "admin";
        String emp = "employee";
        String cus = "customer";
        if (emailChk.matches(adm)){
            email.setError(null);
            email.setErrorEnabled(false);
            admE = 1;
            return true;
        }

        else if (emailChk.matches(emp)) {
            email.setError(null);
            email.setErrorEnabled(false);
            empE = 1;
            return true;
        }

        else if (emailChk.matches(cus)) {
            email.setError(null);
            email.setErrorEnabled(false);
            cusE = 1;
            return true;
        }

        else if (!emailChk.matches(emailPattern)) {
            email.setError("incorrect email");
            return false;
        }


        email.setError(null);
        email.setErrorEnabled(false);
        return true;
    }

    public boolean validatePassword() {
        String pswrd = password.getEditText().getText().toString();
        String adm = "admin";
        String emp = "employee";
        String cus = "customer";

        if (pswrd.isEmpty()) {
            password.setError("Password cannot be empty");
            return false;
        }

        else if (pswrd.matches(adm)){
            password.setError(null);
            password.setErrorEnabled(false);
            admP = 1;
            return true;
        }

        else if (pswrd.matches(emp)) {
            email.setError(null);
            email.setErrorEnabled(false);
            empP = 1;
            return true;
        }

        else if (pswrd.matches(cus)) {
            password.setError(null);
            password.setErrorEnabled(false);
            cusP = 1;
            return true;
        }

        password.setError(null);
        password.setErrorEnabled(false);
        return true;
    }

    boolean validateEmailT(String email) {
        if (email.isEmpty()) {
            return false;
        }
        //this string represents the email pattern
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String adm = "admin";
        String emp = "employee";
        String cus = "customer";
        if (email.matches(adm)){
            return true;
        }

        else if (email.matches(emp)) {
            return true;
        }

        else if (email.matches(cus)) {
            return true;
        }

        else if (!email.matches(emailPattern)) {
            return false;
        }
        return true;
    }
}