package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DeleteServiceEmployee extends AppCompatActivity {

    public Button delCarEmpBtn, delTruckEmpBtn, delAssEmpBtn;
    public Button prevPageBtn;
    public static TextView branchLabel;
    public TextView delServiceEmp;
    public static String cityName = "placeHolder";
    public static Boolean showCarEmp = true;
    public static Boolean showTruckEmp = true;
    public static Boolean showAssEmp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_service_employee);
        Bundle branchName = getIntent().getExtras();
        if (branchName!=null){
            cityName = branchName.getString("city");
            showCarEmp = branchName.getBoolean("carVis");
            showTruckEmp = branchName.getBoolean("tVis");
            showAssEmp = branchName.getBoolean("aVis");
        }

        delCarEmpBtn = findViewById(R.id.delCarEmp);
        delCarEmpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent delAss = new Intent(DeleteServiceEmployee.this, ManageProfile.class);
                ManageProfile ex0 = new ManageProfile();
                CompleteBranchProfile ex4 = new CompleteBranchProfile();

                ex0.showCarAdmin = true;
                showCarEmp = true;

                if (cityName.equals("Ottawa")){
                    ex0.carVisOttawaEmp = true;
                    ex4.carVisOttawaCBP = false;
                } else if (cityName.equals("Toronto")){
                    ex0.carVisTorontoEmp = true;
                    ex4.carVisTorontoCBP = false;
                } else if (cityName.equals("Montreal")){
                    ex0.carVisMontrealEmp = true;
                    ex4.carVisMontrealCBP = false;
                } else if (cityName.equals("Kingston")){
                    ex0.carVisKingstonEmp = true;
                    ex4.carVisKingstonCBP = false;
                } else {
                    ex0.carVisWindsorEmp = true;
                    ex4.carVisWindsorCBP = false;
                }
                startActivity(delAss);
            }
        });

        delTruckEmpBtn = findViewById(R.id.delTruckEmp);
        delTruckEmpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent delAss = new Intent(DeleteServiceEmployee.this, ManageProfile.class);
                ManageProfile ex0 = new ManageProfile();
                CompleteBranchProfile ex4 = new CompleteBranchProfile();

                ex0.showTruckAdmin = true;
                showTruckEmp = true;
                if (cityName.equals("Ottawa")){
                    ex0.truckVisOttawaEmp = true;
                    ex4.truckVisOttawaCBP = false;
                } else if (cityName.equals("Toronto")){
                    ex0.truckVisTorontoEmp = true;
                    ex4.truckVisTorontoCBP = false;
                } else if (cityName.equals("Montreal")){
                    ex0.truckVisMontrealEmp = true;
                    ex4.truckVisMontrealCBP = false;
                } else if (cityName.equals("Kingston")){
                    ex0.truckVisKingstonEmp = true;
                    ex4.truckVisKingstonCBP = false;
                } else {
                    ex0.truckVisWindsorEmp = true;
                    ex4.truckVisWindsorCBP = false;
                }
                startActivity(delAss);
            }
        });

        delAssEmpBtn = findViewById(R.id.delAssEmp);
        delAssEmpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent delAss = new Intent(DeleteServiceEmployee.this, ManageProfile.class);
                ManageProfile ex0 = new ManageProfile();
                CompleteBranchProfile ex4 = new CompleteBranchProfile();

                ex0.showAssAdmin = true;
                showAssEmp = true;
                if (cityName.equals("Ottawa")){
                    ex0.assVisOttawaEmp = true;
                    ex4.assVisOttawaCBP = false;
                } else if (cityName.equals("Toronto")){
                    ex0.assVisTorontoEmp = true;
                    ex4.assVisTorontoCBP = false;
                } else if (cityName.equals("Montreal")){
                    ex0.assVisMontrealEmp = true;
                    ex4.assVisMontrealCBP = false;
                } else if (cityName.equals("Kingston")){
                    ex0.assVisKingstonEmp = true;
                    ex4.assVisKingstonCBP = false;
                } else {
                    ex0.assVisWindsorEmp = true;
                    ex4.assVisWindsorCBP = false;
                }
                startActivity(delAss);
            }
        });

        branchLabel = findViewById(R.id.branchName);
        branchLabel.setText(cityName);
        delServiceEmp = findViewById(R.id.delServiceEmp);

        // visibilty
        if (showCarEmp == true){
            delCarEmpBtn.setVisibility(View.INVISIBLE);
        } else {
            delCarEmpBtn.setVisibility(View.VISIBLE);
        }

        if (showTruckEmp == true){
            delTruckEmpBtn.setVisibility(View.INVISIBLE);
        } else {
            delTruckEmpBtn.setVisibility(View.VISIBLE);
        }

        if (showAssEmp == true){
            delAssEmpBtn.setVisibility(View.INVISIBLE);
        }else {
            delAssEmpBtn.setVisibility(View.VISIBLE);
        }

        prevPageBtn = findViewById(R.id.prevPageBtn);
        prevPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(DeleteServiceEmployee.this, ManageProfile.class);
                ManageProfile ex2 = new ManageProfile();

                if (showCarEmp == false){
                    ex2.showCarAdmin = false;
                }
                if (showTruckEmp == false){
                    ex2.showCarAdmin = false;
                }
                if (showAssEmp == false){
                    ex2.showCarAdmin =  false;
                }
                startActivity(i1);
            }
        });
    }
}