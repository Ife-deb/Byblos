package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CompleteBranchProfile extends AppCompatActivity {

    public static String phoneNum;
    public static String address;
    public static String cityName;
    public static String postalCode;
    public static String startHrs;
    public static String endHrs;
    public static String startHrsSet = "12:00AM";
    public static String endHrsSet = "4:00PM";
    public static TextView phoneLabel, addressLabel, cityLabel, postalCodeLabel, startHLabel, endHLabel, car, truck, moving;

    public boolean showCar = false;
    public boolean showTruck = false;
    public boolean showAss = false;

    public  static Boolean carVisOttawaCBP = false;
    public  static Boolean truckVisOttawaCBP = false;
    public  static Boolean assVisOttawaCBP = false;

    public  static Boolean carVisTorontoCBP = false;
    public  static Boolean truckVisTorontoCBP = false;
    public  static Boolean assVisTorontoCBP  = false;

    public  static Boolean carVisMontrealCBP = false;
    public  static Boolean truckVisMontrealCBP = false;
    public  static Boolean assVisMontrealCBP  = false;

    public  static Boolean carVisKingstonCBP = false;
    public  static Boolean truckVisKingstonCBP = false;
    public  static Boolean assVisKingstonCBP  = false;

    public  static Boolean carVisWindsorCBP = false;
    public  static Boolean truckVisWindsorCBP = false;
    public  static Boolean assVisWindsorCBP  = false;

    public static boolean firstTimeMPEmp = true;
    public Button request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_branch_profile);

        //get phone num
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            phoneNum = extras.getString("phone");
            address = extras.getString("address");
            cityName = extras.getString("city");
            postalCode = extras.getString("code");
            startHrs = extras.getString("startH");
            endHrs = extras.getString("endH");
        }

        //initialize labels
        phoneLabel = findViewById(R.id.phoneNumber);
        addressLabel = findViewById(R.id.address);
        cityLabel = findViewById(R.id.branchName);
        postalCodeLabel = findViewById(R.id.postalCode);
        startHLabel = findViewById(R.id.hStart);
        endHLabel = findViewById(R.id.hEnd);
        car = findViewById(R.id.car);
        truck = findViewById(R.id.truck);
        moving = findViewById(R.id.moving);
        request = findViewById(R.id.requestButton);

        // set labels
        cityLabel.setText(cityName);
        phoneLabel.setText(phoneNum);
        addressLabel.setText(address);
        postalCodeLabel.setText(postalCode);

        if ((!startHrs.matches("")) && (!endHrs.matches(""))) {
            startHLabel.setText(startHrs);
            endHLabel.setText(endHrs);
        } else if ((!startHrs.matches("")) && (endHrs.matches(""))) {
            startHLabel.setText(startHrs);
            endHLabel.setText("4:00PM");
        } else if ((startHrs.matches("")) && (!endHrs.matches(""))) {
            startHLabel.setText("12:00AM");
            endHLabel.setText(endHrs);
        } else {
            startHLabel.setText("12:00AM");
            endHLabel.setText("4:00PM");
        }

        // set visibilty

        if (getCarCBP(cityName) == false){
            car.setVisibility(View.INVISIBLE);
        } else{
            car.setVisibility(View.VISIBLE);
        }
        if (getTruckCBP(cityName) == false){
            truck.setVisibility(View.INVISIBLE);
        } else{
            truck.setVisibility(View.VISIBLE);
        }
        if (getAssCBP(cityName) == false){
            moving.setVisibility(View.INVISIBLE);
        } else{
            moving.setVisibility(View.VISIBLE);
        }


        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CompleteBranchProfile.this, RequestPage.class));
            }
        });
    }

    public void goBackActivity (View view) {
        Intent intent = new Intent(this, BranchProfile.class);
        startActivity(intent);
    }

    public void openManageProfile(View view){
        // sends the visibility status from admin (manage branches) to the employee services page (manage profile)
        // if it is the first time b/c
        if (firstTimeMPEmp == true) {
            ManageBranches vis1 = new ManageBranches();

            Intent intent2 = new Intent(this, ManageProfile.class);

            Boolean v1O = vis1.getCarVis(cityName);
            Boolean v2O = vis1.getTruckVis(cityName);
            Boolean v3O = vis1.getAssVis(cityName);

            intent2.putExtra("city", cityName);
            intent2.putExtra("carVis", v1O);
            intent2.putExtra("tVis",v2O);
            intent2.putExtra("aVis", v3O);

            firstTimeMPEmp = false;
            startActivity(intent2);

        } else {
            // if not the first time then when you click on the manage profile from the complete profile
            // the visibility setting from the MP page (made by employee) should be saved
            // (not the visibility setting saved by admin)
            ManageProfile vis2 = new ManageProfile();
            ManageBranches vis3 = new ManageBranches(); // in case where admin adds a service back that they prev deleted, it should appear in mP

            Intent intent3 = new Intent(this, ManageProfile.class);

            Boolean v1O = vis2.getCarVisEmp(cityName);
            Boolean v2O = vis2.getTruckVisEmp(cityName);
            Boolean v3O = vis2.getAssVisEmp(cityName);

            Boolean a1O = vis3.getCarVis(cityName);
            Boolean a2O = vis3.getTruckVis(cityName);
            Boolean a3O = vis3.getAssVis(cityName);

            if (a1O == true && v1O == false){
                intent3.putExtra("carVis", true);
            } else if (v1O == true) {
                intent3.putExtra("carVis", true);
            } else {
                intent3.putExtra("carVis", false);
            }

            if (a2O == true && v2O == false){
                intent3.putExtra("tVis", true);
            } else if (v2O == true) {
                intent3.putExtra("tVis", true);
            } else {
                intent3.putExtra("tVis", false);
            }

            if (a3O == true && v3O == false){
                intent3.putExtra("aVis", true);
            } else if (v3O == true) {
                intent3.putExtra("aVis", true);
            } else {
                intent3.putExtra("aVis", false);
            }

            intent3.putExtra("city", cityName);
            startActivity(intent3);
        }
    }
    public void showCarCBP(String cityName){
        if (cityName.equals("Ottawa")){
            carVisOttawaCBP = true;
        } else if (cityName.equals("Toronto")){
            carVisTorontoCBP = true;
        } else if (cityName.equals("Montreal")){
            carVisMontrealCBP = true;
        } else if (cityName.equals("Kingston")){
            carVisKingstonCBP = true;
        } else {
            carVisWindsorCBP = true;
        }
    }
    public void showTruckCBP(String cityName){
        if (cityName.equals("Ottawa")){
            truckVisOttawaCBP = true;
        } else if (cityName.equals("Toronto")){
            truckVisTorontoCBP = true;
        } else if (cityName.equals("Montreal")){
            truckVisMontrealCBP = true;
        } else if (cityName.equals("Kingston")){
            truckVisKingstonCBP = true;
        } else {
            truckVisWindsorCBP = true;
        }
    }

    public void showAssCBP(String cityName){
        if (cityName.equals("Ottawa")){
            assVisOttawaCBP = true;
        } else if (cityName.equals("Toronto")){
            assVisTorontoCBP = true;
        } else if (cityName.equals("Montreal")){
            assVisMontrealCBP = true;
        } else if (cityName.equals("Kingston")){
            assVisKingstonCBP = true;
        } else {
            assVisWindsorCBP = true;
        }
    }

    public Boolean getCarCBP(String cityName){
        if (cityName.equals("Ottawa")){
            return carVisOttawaCBP;
        } else if (cityName.equals("Toronto")){
            return carVisTorontoCBP;
        } else if (cityName.equals("Montreal")){
            return carVisMontrealCBP;
        } else if (cityName.equals("Kingston")){
            return carVisKingstonCBP;
        } else {
            return carVisWindsorCBP;
        }
    }
    public Boolean getTruckCBP(String cityName){
        if (cityName.equals("Ottawa")){
            return truckVisOttawaCBP;
        } else if (cityName.equals("Toronto")){
            return truckVisTorontoCBP;
        } else if (cityName.equals("Montreal")){
            return truckVisMontrealCBP;
        } else if (cityName.equals("Kingston")){
            return truckVisKingstonCBP;
        } else {
            return truckVisWindsorCBP;
        }
    }

    public Boolean getAssCBP(String cityName){
        if (cityName.equals("Ottawa")){
            return assVisOttawaCBP;
        } else if (cityName.equals("Toronto")){
            return assVisTorontoCBP;
        } else if (cityName.equals("Montreal")){
            return assVisMontrealCBP;
        } else if (cityName.equals("Kingston")){
            return assVisKingstonCBP;
        } else {
            return assVisWindsorCBP;
        }
    }

   /* public void setVisibilty(){

        // initial visit
        if (firstTime == true){
            car.setVisibility(View.INVISIBLE);
            truck.setVisibility(View.INVISIBLE);
            moving.setVisibility(View.INVISIBLE);
            firstTime = false;
        } else {
            if (showCar == true){
                car.setVisibility(View.VISIBLE);
            }
            if (showTruck == true){
                truck.setVisibility(View.VISIBLE);
            }

            if (showAss == true){
                moving.setVisibility(View.VISIBLE);
            }
        }
    }*/
}