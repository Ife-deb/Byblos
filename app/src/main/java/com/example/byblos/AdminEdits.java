package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminEdits extends AppCompatActivity {
    private Button editUsers, editBranches, prevBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edits);

        editUsers = findViewById(R.id.edUsers);
        editBranches = findViewById(R.id.edBranch);
        prevBtn = findViewById(R.id.prevBtn);

    }

    public void openSelectBranchActivity (View view) {
        Intent intent = new Intent(this, SelectBranch.class) ;
        startActivity(intent);
        finish();
    }

    public void openEditUsers (View view) {
        Intent intent = new Intent(this, EditUsers.class) ;
        startActivity(intent);
        finish();
    }

    public void goBack(View view){
        Intent intent = new Intent(this, Login.class) ;
        startActivity(intent);
        finish();
    }
}