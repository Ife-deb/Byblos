package com.example.byblos;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
//import android.usernameField

public class WelcomeScreen extends AppCompatActivity {

    public TextView welcomeMessage, role;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        welcomeMessage = findViewById(R.id.userNameTxt);
        role = findViewById(R.id.roleTxt);
        displayWelcomeMessage();
    }

        private void displayWelcomeMessage() {

        Intent intent = getIntent();
        String user_firstName = intent.getStringExtra("firstName");
        String user_employee = intent.getStringExtra("employee");
        String message = "Welcome " + user_firstName;
        String employeeTag = "Employee";
        String customerTag = "Customer";

        if (user_employee.equals("yes")) {
                role.setText(employeeTag);
        }

        else {
            role.setText(customerTag);
        }

        welcomeMessage.setText(message);
    }

    public void openSelectBranchActivity (View view) {

        Intent intent = getIntent();
        String user_employee = intent.getStringExtra("employee");

        if (user_employee.equals("yes")) {
            intent = new Intent(this, SelectBranchEmployee.class) ;
            startActivity(intent);
            finish();
        }

        else {
            intent = new Intent(this, SelectBranchCustomer.class) ;
            startActivity(intent);
            finish();
        }
    }
}
