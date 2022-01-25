package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AddServiceBranches extends AppCompatActivity {

    public Button goBackBtn;
    public Button delAss, delTruck, delCar;
    public static TextView branchLabel;
    public TextView addServiceEmp;
    public static String cityName = "placeHolder";
    public static Boolean showCar = true;
    public static Boolean showTruck = true;
    public static Boolean showAss = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_branches);
        Bundle branchName = getIntent().getExtras();
        if (branchName!=null){
            cityName = branchName.getString("city");
            showCar = branchName.getBoolean("carVis");
            showTruck = branchName.getBoolean("tVis");
            showAss = branchName.getBoolean("aVis");
        }

        delAss = findViewById(R.id.addAss);
        delAss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent delAss = new Intent(AddServiceBranches.this, ManageBranches.class);
                ManageBranches ex0 = new ManageBranches();

                ex0.showAss = true;
                showAss = true;

                if (cityName.equals("Ottawa")){
                    ex0.assVisOttawa = true;
                } else if (cityName.equals("Toronto")){
                    ex0.assVisToronto = true;
                } else if (cityName.equals("Montreal")){
                    ex0.assVisMontreal = true;
                } else if (cityName.equals("Kingston")){
                    ex0.assVisKingston = true;
                } else {
                    ex0.assVisWindsor = true;
                }

                startActivity(delAss);

            }
        });

        delTruck = findViewById(R.id.addTruck);
        delTruck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent delTruck = new Intent(AddServiceBranches.this, ManageBranches.class);
                ManageBranches ex1 = new ManageBranches();

                ex1.showTruck = true;
                showTruck = true;

                if (cityName.equals("Ottawa")){
                    ex1.truckVisOttawa = true;
                } else if (cityName.equals("Toronto")){
                    ex1.truckVisToronto = true;
                } else if (cityName.equals("Montreal")){
                    ex1.truckVisMontreal = true;
                } else if (cityName.equals("Kingston")){
                    ex1.truckVisKingston = true;
                } else {
                    ex1.truckVisWindsor = true;
                }

                startActivity(delTruck);
            }
        });

        delCar = findViewById(R.id.addCar);
        delCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent delCar = new Intent(AddServiceBranches.this, ManageBranches.class);
                ManageBranches ex3 = new ManageBranches();

                ex3.showCar = true;
                showCar = true;

                if (cityName.equals("Ottawa")){
                    ex3.carVisOttawa = true;
                } else if (cityName.equals("Toronto")){
                    ex3.carVisToronto = true;
                } else if (cityName.equals("Montreal")){
                    ex3.carVisMontreal = true;
                } else if (cityName.equals("Kingston")){
                    ex3.carVisKingston = true;
                } else {
                    ex3.carVisWindsor = true;
                }

                startActivity(delCar);
            }
        });

        // find views
        branchLabel = findViewById(R.id.branchName);
        addServiceEmp = findViewById(R.id.addServiceEmp);
        branchLabel.setText(cityName);

        if (showCar == true){
            delCar.setVisibility(View.INVISIBLE);
        } else {
            delCar.setVisibility(View.VISIBLE);
        }

        if (showTruck == true){
            delTruck.setVisibility(View.INVISIBLE);
        } else {
            delTruck.setVisibility(View.VISIBLE);
        }

        if (showAss == true){
            delAss.setVisibility(View.INVISIBLE);
        }else {
            delAss.setVisibility(View.VISIBLE);
        }

        goBackBtn = findViewById(R.id.prevPageBtn);
        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(AddServiceBranches.this, ManageBranches.class);
                ManageBranches ex2 = new ManageBranches();
                if (showCar == false){
                    ex2.showCar = false;
                }
                if (showTruck == false){
                    ex2.showTruck = false;
                }
                if (showAss == false){
                    ex2.showAss =  false;
                }
                startActivity(a);
            }
        });

    }
}