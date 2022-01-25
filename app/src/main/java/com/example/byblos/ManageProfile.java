package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ManageProfile extends AppCompatActivity {

    public Button addserviceButton, deleteserviceButton, viewServiceButton;
    public static RadioButton carRentalBranchBtn, truckRentalBranchBtn, movingAssBranchBtn;
    public static RadioGroup radioGroup;

    public static TextView branchLabel;
    public static String cityName;

    //public static Boolean carVis, truckVis, assVis;
    public static Boolean showCarAdmin = true;
    public static Boolean showTruckAdmin = true;
    public static Boolean showAssAdmin = true;

    public  static Boolean carVisOttawaEmp = true;
    public  static Boolean truckVisOttawaEmp = true;
    public  static Boolean assVisOttawaEmp = true;

    public  static Boolean carVisTorontoEmp = true;
    public  static Boolean truckVisTorontoEmp = true;
    public  static Boolean assVisTorontoEmp  = true;

    public  static Boolean carVisMontrealEmp = true;
    public  static Boolean truckVisMontrealEmp = true;
    public  static Boolean assVisMontrealEmp  = true;

    public  static Boolean carVisKingstonEmp = true;
    public  static Boolean truckVisKingstonEmp = true;
    public  static Boolean assVisKingstonEmp  = true;

    public  static Boolean carVisWindsorEmp = true;
    public  static Boolean truckVisWindsorEmp = true;
    public  static Boolean assVisWindsorEmp  = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_profile);

        //receives the extras (get branch city name and visibility bools from admin) that were sent from the button in complete profile
        Bundle branchName = getIntent().getExtras();
        if (branchName!=null){
            cityName = branchName.getString("city");
            showCarAdmin = branchName.getBoolean("carVis");
            showTruckAdmin = branchName.getBoolean("tVis");
            showAssAdmin = branchName.getBoolean("aVis");
        }

        radioGroup = findViewById(R.id.servicesBranchBtnGr);
        carRentalBranchBtn = findViewById(R.id.carRentalBranchBtn);
        truckRentalBranchBtn = findViewById(R.id.truckRentalBranchBtn);
        movingAssBranchBtn = findViewById(R.id.movingAssBranchBtn);
        branchLabel = findViewById(R.id.branchName);
        viewServiceButton = findViewById(R.id.viewServiceButton);

        addserviceButton = findViewById(R.id.addserviceButton);
        addserviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // when a service is added
                // 1. It should disappear from the employee service page permanently
                // a. the visibility for that service in that branch var should be false (static)
                // 2. It should show up on the complete branch page (until changed by employee)
                // 3. It should

                CompleteBranchProfile cBP = new CompleteBranchProfile();

              if (carRentalBranchBtn.isChecked()){
                    carRentalBranchBtn.setVisibility(View.INVISIBLE);
                    setCarVisEmp(cityName);
                    cBP.showCarCBP(cityName);
                }
                if (truckRentalBranchBtn.isChecked()){
                    truckRentalBranchBtn.setVisibility(View.INVISIBLE);
                    setTruckVisEmp(cityName);
                    cBP.showTruckCBP(cityName);
                }
                if (movingAssBranchBtn.isChecked()){
                    movingAssBranchBtn.setVisibility(View.INVISIBLE);
                    setAssVisEmp(cityName);
                    cBP.showAssCBP(cityName);
                }
                Intent newI = new Intent(ManageProfile.this, CompleteBranchProfile.class);
                startActivity(newI);
            }
        });

        deleteserviceButton = findViewById(R.id.deleteserviceButton);
        deleteserviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delServiceEmp();
            }
        });

        // changing branch name
        branchLabel.setText(cityName);

        // handling initial visibility
        // if visibility is off on admin page, it should be  off here too
        // also ensures that the visibility on the employee page stays the same as when you left it

        if (showCarAdmin == false || getCarVisEmp(cityName)== false){
            carRentalBranchBtn.setVisibility(View.INVISIBLE);
            //setCarVisEmp(cityName);
        } else {
            carRentalBranchBtn.setVisibility(View.VISIBLE);
        }

        if (showTruckAdmin == false || getTruckVisEmp(cityName)== false){
            truckRentalBranchBtn.setVisibility(View.INVISIBLE);
            //setTruckVisEmp(cityName);
        } else {
            truckRentalBranchBtn.setVisibility(View.VISIBLE);
        }
        if (showAssAdmin == false || getAssVisEmp(cityName)== false){
            movingAssBranchBtn.setVisibility(View.INVISIBLE);
            //setAssVisEmp(cityName);
        } else {
            movingAssBranchBtn.setVisibility(View.VISIBLE);
        }

    }

    public void openCompleteProfileActivity (View view) {
        Intent iCP = new Intent(ManageProfile.this, CompleteBranchProfile.class) ;
        startActivity(iCP);
    }

    public void delServiceEmp(){
        Intent delSer = new Intent(this, DeleteServiceEmployee.class);

        Boolean cV = getCarVisEmp(cityName);
        Boolean tV = getTruckVisEmp(cityName);
        Boolean aV = getAssVisEmp(cityName);

        delSer.putExtra("city", cityName);
        delSer.putExtra("carVis", cV);
        delSer.putExtra("tVis", tV);
        delSer.putExtra("aVis", aV);

        startActivity(delSer);
        finish();
    }

   public void setCarVisEmp(String cityName){
        if (cityName.equals("Ottawa")){
            carVisOttawaEmp = false;
        } else if (cityName.equals("Toronto")){
            carVisTorontoEmp = false;
        } else if (cityName.equals("Montreal")){
            carVisMontrealEmp = false;
        } else if (cityName.equals("Kingston")){
            carVisKingstonEmp = false;
        } else {
            carVisWindsorEmp = false;
        }
    }

    public void setTruckVisEmp(String cityName){
        if (cityName.equals("Ottawa")){
            truckVisOttawaEmp = false;
        } else if (cityName.equals("Toronto")){
            truckVisTorontoEmp = false;
        } else if (cityName.equals("Montreal")){
            truckVisMontrealEmp = false;
        } else if (cityName.equals("Kingston")){
            truckVisKingstonEmp = false;
        } else {
            truckVisWindsorEmp = false;
        }
    }

    public void setAssVisEmp(String cityName){
        if (cityName.equals("Ottawa")){
            assVisOttawaEmp = false;
        } else if (cityName.equals("Toronto")){
            assVisTorontoEmp = false;
        } else if (cityName.equals("Montreal")){
            assVisMontrealEmp = false;
        } else if (cityName.equals("Kingston")){
            assVisKingstonEmp = false;
        } else {
            assVisWindsorEmp = false;
        }
    }

    public Boolean getCarVisEmp(String cityName) {
        if (cityName.equals("Ottawa")){
            if(showCarAdmin == false){
                return false;
            } else{
                return carVisOttawaEmp;
            }

        } else if (cityName.equals("Toronto")){
            if(showCarAdmin == false){
                return false;
            } else{
                return carVisTorontoEmp;
            }
        } else if (cityName.equals("Montreal")){
            if(showCarAdmin == false){
                return false;
            } else{
                return carVisMontrealEmp;
            }
        } else if (cityName.equals("Kingston")){
            if(showCarAdmin == false){
                return false;
            } else{
                return carVisKingstonEmp;
            }
        } else {
            if(showCarAdmin == false){
                return false;
            } else{
                return carVisWindsorEmp;
            }
        }
    }

    public Boolean getTruckVisEmp(String cityName) {
        if (cityName.equals("Ottawa")){
            if(showTruckAdmin == false){
                return false;
            } else {
                return truckVisOttawaEmp;
            }
        } else if (cityName.equals("Toronto")){
            if(showTruckAdmin == false){
                return false;
            } else{
                return truckVisTorontoEmp;
            }
        } else if (cityName.equals("Montreal")){
            if(showTruckAdmin == false){
                return false;
            } else{
                return truckVisMontrealEmp;
            }
        } else if (cityName.equals("Kingston")){
            if(showTruckAdmin == false){
                return false;
            } else{
                return truckVisKingstonEmp;
            }
        } else {
            if(showTruckAdmin == false){
                return false;
            } else{
                return truckVisWindsorEmp;
            }
        }
    }

    public Boolean getAssVisEmp(String cityName) {
        if (cityName.equals("Ottawa")){
            if(showAssAdmin == false){
                return false;
            } else{
                return assVisOttawaEmp;
            }
        } else if (cityName.equals("Toronto")){
            if(showAssAdmin == false){
                return false;
            } else{
                return assVisTorontoEmp;
            }
        } else if (cityName.equals("Montreal")){
            if(showAssAdmin == false){
                return false;
            } else{
                return assVisMontrealEmp;
            }
        } else if (cityName.equals("Kingston")){
            if(showAssAdmin == false){
                return false;
            } else{
                return assVisKingstonEmp;
            }
        } else {
            if(showAssAdmin == false){
                return false;
            } else{
                return assVisWindsorEmp;
            }
        }
    }

}
