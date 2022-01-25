package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class PageSelectService extends AppCompatActivity {
    public static Button carRentalBtn, truckRentalBtn, movingAssistanceBtn, previousPage, submitRate;;
    public static TextView branchLabel;
    public static String cityName;
    public static Boolean showCarAdmin = true;
    public static Boolean showTruckAdmin = true;
    public static Boolean showAssAdmin = true;
    public static RadioGroup ratings;

    private RadioButton oneR, twoR, threeR, fourR, fiveR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ratings
        ratings = findViewById(R.id.radio_Group);

        oneR = findViewById(R.id.one);
        twoR = findViewById(R.id.two);
        threeR = findViewById(R.id.three);
        fourR = findViewById(R.id.four);
        fiveR = findViewById(R.id.five);





        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_select_service);

        previousPage = findViewById(R.id.previousPageBtn);
        submitRate = findViewById(R.id.submitButton);


        previousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PageSelectService.this, SelectBranchCustomer.class));
            }
        });

        //get branch city name and visibility bools

        submitRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRating();
            }
        });

        Bundle branchName = getIntent().getExtras();

        if (branchName!=null){
            cityName = branchName.getString("city");
            showCarAdmin = branchName.getBoolean("carVis");
            showTruckAdmin = branchName.getBoolean("tVis");
            showAssAdmin = branchName.getBoolean("aVis");
        }

        // text for branch label
        branchLabel = findViewById(R.id.selectAService);
        // changing branch name
        branchLabel.setText(cityName);

//creating car rental onclick method to open the car rental customer form
        carRentalBtn = findViewById(R.id.carRentalBtn);
        carRentalBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openCarRentalForm();
            }
        });
//creating truck rental onclick method to open the truck rental customer form
        truckRentalBtn = findViewById(R.id.truckRentalBtn);
        truckRentalBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openTruckRentalForm();
            }
        });
//creating moving assistance onclick method to open the moving assistance customer form
        movingAssistanceBtn = findViewById(R.id.movingAssistanceBtn);
        movingAssistanceBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openMovingAssistanceForm();
            }
        });

        // handling initial visibility
        // if visibility is off on admin page, it should be  off here too
        // also ensures that the visibility on the customer page stays the same as when you left it

        if (showCarAdmin == false){
            carRentalBtn.setVisibility(View.INVISIBLE);
        } if (showTruckAdmin == false){
            truckRentalBtn.setVisibility(View.INVISIBLE);
        } if (showAssAdmin == false){
            movingAssistanceBtn.setVisibility(View.INVISIBLE);
        }


    }
    public void openCarRentalForm(){
        Intent intent1 = new Intent(this,CarRentalPage.class);
        intent1.putExtra("city", cityName);
        startActivity(intent1);
    }
    public void openTruckRentalForm(){
        Intent intent = new Intent(this,TruckRentalPage.class);
        intent.putExtra("city", cityName);
        startActivity(intent);
    }
    public void openMovingAssistanceForm(){
        Intent intent3 = new Intent(this,MovingAssistancePage.class);
        intent3.putExtra("city", cityName);
        startActivity(intent3);
    }

    private boolean validateRating(){
        if (!oneR.isChecked() && !twoR.isChecked() && !threeR.isChecked() && !fourR.isChecked() && !fiveR.isChecked()){
            oneR.setError("");
            twoR.setError("");
            threeR.setError("");
            fourR.setError("");
            fiveR.setError("");
            return false;
        }
        oneR.setError(null);
        twoR.setError(null);
        threeR.setError(null);
        fourR.setError(null);
        fiveR.setError(null);

        return true;
    }


    public void toastText(String message){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        toast.show();

    }






    public void submitRating(){
        //if (!validateRating()){
            //return;
        //}
        //else{
            //toastText("Thank you! Your rating has been submitted");
        //}
        toastText("Thank you! Your rating has been submitted.");





    }




}