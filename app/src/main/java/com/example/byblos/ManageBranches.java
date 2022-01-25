package com.example.byblos;

import static com.example.byblos.PageSelectService.carRentalBtn;
import static com.example.byblos.PageSelectService.movingAssistanceBtn;
import static com.example.byblos.PageSelectService.truckRentalBtn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ManageBranches extends AppCompatActivity {

    public  Button editserviceButton, addserviceButton, deleteserviceButton, prevPageBtn;
    public  RadioGroup radioGroup;
    public  RadioButton carRentalBranchBtn, truckRentalBranchBtn, movingAssBranchBtn;
    public static TextView branchLabel;
    public static String cityName = "placeHolder";

    public static Boolean carVisOttawa = true;
    public static Boolean truckVisOttawa = true;
    public static Boolean assVisOttawa = true;

    public static Boolean carVisToronto = true;
    public static Boolean truckVisToronto = true;
    public static Boolean assVisToronto  = true;

    public static Boolean carVisMontreal = true;
    public static Boolean truckVisMontreal = true;
    public static Boolean assVisMontreal  = true;

    public static Boolean carVisKingston = true;
    public static Boolean truckVisKingston = true;
    public static Boolean assVisKingston  = true;

    public static Boolean carVisWindsor = true;
    public static Boolean truckVisWindsor = true;
    public static Boolean assVisWindsor  = true;

    public static Boolean showCar = true;
    public static Boolean showTruck = true;
    public static Boolean showAss = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_branches);
        //get branch city name and visibility bools
        Bundle branchName = getIntent().getExtras();
        if (branchName!=null){
            cityName = branchName.getString("city");
            showCar = branchName.getBoolean("carVis");
            showTruck = branchName.getBoolean("tVis");
            showAss = branchName.getBoolean("aVis");
        }

        radioGroup = findViewById(R.id.servicesBranchBtnGr);
        carRentalBranchBtn = findViewById(R.id.carRentalBranchBtn);
        truckRentalBranchBtn = findViewById(R.id.truckRentalBranchBtn);
        movingAssBranchBtn = findViewById(R.id.movingAssBranchBtn);

        // changing branch name
        branchLabel = findViewById(R.id.manageBranchesTitle);
        branchLabel.setText(cityName);


        // handling initial visibility (so if admin deleted a service in the branch then went to prev page the service
        // should still be gone when then come back to that branch page
        if (showCar == false){
            carRentalBranchBtn.setVisibility(View.INVISIBLE);
        } else {
            carRentalBranchBtn.setVisibility(View.VISIBLE);
        }
        if (showTruck == false){
            truckRentalBranchBtn.setVisibility(View.INVISIBLE);
        } else{
            truckRentalBranchBtn.setVisibility(View.VISIBLE);
        }
        if (showAss == false){
            movingAssBranchBtn.setVisibility(View.INVISIBLE);
        } else{
            movingAssBranchBtn.setVisibility(View.VISIBLE);
        }

        editserviceButton = findViewById(R.id.editserviceButton);
        editserviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (carRentalBranchBtn.isChecked()){
                    openCarRentalActivity();
                }
                else if (truckRentalBranchBtn.isChecked()){
                    openTruckRentalActivity();
                }
                else if (movingAssBranchBtn.isChecked()){
                    openMovingAssActivity();
                }
            }
        });

        addserviceButton = findViewById(R.id.addserviceButton);
        addserviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddServices();
            }
        });

        deleteserviceButton = findViewById(R.id.deleteserviceButton);
        deleteserviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (carRentalBranchBtn.isChecked()){
                   carRentalBranchBtn.setVisibility(View.INVISIBLE);
                   setCarVis(cityName);
                }
                if (truckRentalBranchBtn.isChecked()){
                    truckRentalBranchBtn.setVisibility(View.INVISIBLE);
                    setTruckVis(cityName);
                }
                if (movingAssBranchBtn.isChecked()){
                    movingAssBranchBtn.setVisibility(View.INVISIBLE);
                    setAssVis(cityName);
                }
            }
        });

        prevPageBtn = findViewById(R.id.prevPageBtn);
        prevPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManageBranches.this, SelectBranch.class));
            }
        });

    }

    public void openAddServices(){
        Intent addIntent = new Intent(this, AddServiceBranches.class);

        Boolean v1O = getCarVis(cityName);
        Boolean v2O = getTruckVis(cityName);
        Boolean v3O = getAssVis(cityName);

        addIntent.putExtra("city", cityName);
        addIntent.putExtra("carVis", v1O);
        addIntent.putExtra("tVis",v2O);
        addIntent.putExtra("aVis", v3O);

        startActivity(addIntent);
    }

    public void openCarRentalActivity(){
        CarRentalEditHourly ex1 = new CarRentalEditHourly();
        String value = ex1.getCarRentalCrntHourlyRateStr();
        Intent crIntent = new Intent(this, CarRentalEditHourly.class);
        crIntent.putExtra("prevRate", value);
        startActivity(crIntent);

    }

    public void openTruckRentalActivity(){
        TruckRentalEditHourlyRate ex2 = new TruckRentalEditHourlyRate();
        String value = ex2.getTruckRentalCrntHourlyRateStr();
        Intent trIntent = new Intent(this, TruckRentalEditHourlyRate.class);
        trIntent.putExtra("prevRate", value);
        startActivity(trIntent);
    }

    public void openMovingAssActivity(){
        MovingAssEditHourly ex3 = new MovingAssEditHourly();
        String value = ex3.getMoveAssCrntHourlyRateStr();
        Intent maIntent = new Intent(this, MovingAssEditHourly.class);
        maIntent.putExtra("prevRate", value);
        startActivity(maIntent);
    }

    public void openDeleteBranch(){
        Intent delBraIntent = new Intent(this, DeleteBranchPage.class);
        startActivity(delBraIntent);
    }

    public void setCarVis(String cityName){
        if (cityName.equals("Ottawa")){
            carVisOttawa = false;
        } else if (cityName.equals("Toronto")){
            carVisToronto = false;
        } else if (cityName.equals("Montreal")){
            carVisMontreal = false;
        } else if (cityName.equals("Kingston")){
            carVisKingston = false;
        } else {
            carVisWindsor = false;
        }
    }

    public void setTruckVis(String cityName){
        if (cityName.equals("Ottawa")){
            truckVisOttawa = false;
        } else if (cityName.equals("Toronto")){
            truckVisToronto = false;
        } else if (cityName.equals("Montreal")){
            truckVisMontreal = false;
        } else if (cityName.equals("Kingston")){
            truckVisKingston = false;
        } else {
            truckVisWindsor = false;
        }
    }

    public void setAssVis(String cityName){
        if (cityName.equals("Ottawa")){
            assVisOttawa = false;
        } else if (cityName.equals("Toronto")){
            assVisToronto = false;
        } else if (cityName.equals("Montreal")){
            assVisMontreal = false;
        } else if (cityName.equals("Kingston")){
            assVisKingston = false;
        } else {
            assVisWindsor = false;
        }
    }

    public Boolean getCarVis(String cityName) {
        if (cityName.equals("Ottawa")){
            return carVisOttawa;
        } else if (cityName.equals("Toronto")){
            return carVisToronto;
        } else if (cityName.equals("Montreal")){
            return carVisMontreal;
        } else if (cityName.equals("Kingston")){
            return carVisKingston;
        } else {
            return carVisWindsor;
        }
    }

    public Boolean getTruckVis(String cityName) {
        if (cityName.equals("Ottawa")){
            return truckVisOttawa;
        } else if (cityName.equals("Toronto")){
            return truckVisToronto;
        } else if (cityName.equals("Montreal")){
            return truckVisMontreal;
        } else if (cityName.equals("Kingston")){
            return truckVisKingston;
        } else {
            return truckVisWindsor;
        }
    }

    public Boolean getAssVis(String cityName) {
        if (cityName.equals("Ottawa")){
            return assVisOttawa;
        } else if (cityName.equals("Toronto")){
            return assVisToronto;
        } else if (cityName.equals("Montreal")){
            return assVisMontreal;
        } else if (cityName.equals("Kingston")){
            return assVisKingston;
        } else {
            return assVisWindsor;
        }
    }
}