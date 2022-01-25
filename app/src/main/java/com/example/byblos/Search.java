package com.example.byblos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class Search extends AppCompatActivity {
    public static Button searchCityBtn, searchHoursBtn, searchServicesBtn, previousPage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        previousPage = findViewById(R.id.previousPageBtn);

        previousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Search.this, SelectBranchCustomer.class));
            }
        });
        //creating car rental onclick method to open the car rental customer form
        searchCityBtn = findViewById(R.id.searchCityBtn);
        searchCityBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCities();
            }
        });
//creating truck rental onclick method to open the truck rental customer form
        searchHoursBtn = findViewById(R.id.searchHoursBtn);
        searchHoursBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openHours();
            }
        });
//creating moving assistance onclick method to open the moving assistance customer form
        searchServicesBtn = findViewById(R.id.searchServicesBtn);
        searchServicesBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //openServices();
            }
        });
    }
    public void openCities(){
        Intent intent12 = new Intent(Search.this,Cities.class);
        startActivity(intent12);
    }
    public void openHours(){
        Intent intent = new Intent(this,Hours.class);
        startActivity(intent);
    }
    /*public void openMovingAssistanceForm(){
        Intent intent3 = new Intent(this,MovingAssistancePage.class);
        startActivity(intent3);
    }*/
}
