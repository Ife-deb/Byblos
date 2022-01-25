package com.example.byblos;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity {
    //variables:
    private RadioGroup radioGroup;
    //text fields
    private TextInputLayout firstName, lastName, email, password, confirmPassword;
    //buttons
    private Button registerBtn, returnToLoginBtn;

    //DB
    private FirebaseDatabase root;
    private DatabaseReference reference;




    boolean isEmployee;

    private RadioButton employeeRBtn, customerRBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        employeeRBtn = findViewById(R.id.employeeRadioButton);
        customerRBtn = findViewById(R.id.customerRadioButton);

        firstName = findViewById(R.id.firstnameField);
        lastName = findViewById(R.id.lastnameField);
        email = findViewById(R.id.emailField);
        password = findViewById(R.id.passwordField);
        confirmPassword = findViewById(R.id.confirmPasswordField);

        registerBtn = findViewById(R.id.registerButton);
        returnToLoginBtn = findViewById(R.id.alreadyHaveAccountBtn);

        radioGroup = findViewById(R.id.radio_Group);

        //checks which radio button is clicked
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == employeeRBtn.getId()){
                    isEmployee=true;
                }
                else isEmployee = false;
            }
        });


       //save user info to data base after input validation
       registerBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               registerUser(v);
           }
       });

    }
    // important for testing!
    public SignUp(Context context){
    }

    public SignUp(){}


    //add user
    public void registerUser(View v){
        //validate first
        if (!validateEmail() | !validatePassword() | !validateRole() | !validatePassword() | !validateLastName() | !validateFirstName() | !validateConfirmedPassword()){
            return;
        }
        root = FirebaseDatabase.getInstance();
        reference = root.getReference("users");
        //get user input
        String first_name = firstName.getEditText().getText().toString();
        String last_name = lastName.getEditText().getText().toString();
        String email_address = email.getEditText().getText().toString();
        String password_input = password.getEditText().getText().toString();
        String confirm_password = confirmPassword.getEditText().getText().toString();

        //create user object
        //cleaning up email from all unnecessary dots before @
        StringBuilder sb = new StringBuilder(email_address);
        int check= sb.indexOf(".");
        while(check>=0 && check<sb.indexOf("@")) {
            sb.deleteCharAt(sb.indexOf("."));
            check = sb.indexOf(".");
        }
        email_address = sb.toString();

        //Unique id is email without the dot (Firebase crashes if the uid has a "." in it)
        //remove the last dot from the email
        if (sb.indexOf(".")!=-1){
            sb.deleteCharAt(sb.indexOf("."));
        }
        //unique id: is email without dots.
        String uid = sb.toString();
        String employee="";
        //add user with uid
        if (isEmployee) {
            employee = "yes";
        }
        else {
            employee = "no";
        }
        User newUser = new User(first_name, last_name, email_address, password_input, employee, uid);
        reference.child(uid).setValue(newUser);
        openLoginActivity(v);
    }
    //Info validation
    //checks if radio button is checked
    private boolean validateRole(){
        if (!employeeRBtn.isChecked() && !customerRBtn.isChecked()){
            employeeRBtn.setError("");
            customerRBtn.setError("");
            return false;
        }
        employeeRBtn.setError(null);
        customerRBtn.setError(null);
        return true;
    }

    //checks if name is empty or contains numbers
    //validates first name
    private boolean validateFirstName () {
        String name = firstName.getEditText().getText().toString();
        if (name.isEmpty()){
            firstName.setError("Field cannot be empty");
            return false;
        }

        firstName.setError(null);
        firstName.setErrorEnabled(false);
        return true;
    }
    //validates last name
    private boolean validateLastName () {
        String name = lastName.getEditText().getText().toString();
        if (name.isEmpty()){
            lastName.setError("Field cannot be empty");
            return false;
        }
        lastName.setError(null);
        lastName.setErrorEnabled(false);
        return true;
    }

    //validates email
    private boolean validateEmail () {
        String emailChk = email.getEditText().getText().toString();
        if (emailChk.isEmpty()) {
            email.setError("Email cannot be empty");
            return false;
        }
        //this string represents the email pattern
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!emailChk.matches(emailPattern)){
            email.setError("incorrect email");
            return false;
        }

        email.setError(null);
        email.setErrorEnabled(false);
        return true;

    }
    //validates password
    private boolean validatePassword(){
        String pswrd = password.getEditText().getText().toString();
        if (pswrd.isEmpty()) {
            password.setError("Password cannot be empty");
            return false;
        }
        password.setError(null);
        password.setErrorEnabled(false);
        return true;
    }

    //validates confirmed password
    private boolean validateConfirmedPassword(){
        String pswrd = password.getEditText().getText().toString();
        String confirmPswrd = confirmPassword.getEditText().getText().toString();
        if (!pswrd.matches(confirmPswrd)){
            confirmPassword.setError("Passwords do not match");
            return false;
        }

        if (confirmPswrd.isEmpty()) {
            confirmPassword.setError("Confirm password cannot be empty");
            return false;
        }
        confirmPassword.setError(null);
        confirmPassword.setErrorEnabled(false);
        return true;

    }
    public boolean passwordsMatch(String pswrd){
        String confirmPswrdT = "test";
        if (!pswrd.matches(confirmPswrdT)){
            return false;
        }
        if (pswrd.isEmpty()) {
            return false;
        }
        return true;

    }


    public void openLoginActivity (View view) {
        Intent intent = new Intent(this, Login.class) ;
        startActivity(intent);
    }
}