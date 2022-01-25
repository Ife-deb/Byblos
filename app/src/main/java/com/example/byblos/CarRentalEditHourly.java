package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CarRentalEditHourly extends AppCompatActivity {

    public static EditText carRentalNewHourlyRate;
    public static TextView carRentalCrntRate;
    private Button saveBtn, discardBtn;
    public static String carRentalNewHourlyRateStr;
    public static String carRentalCrntHourlyRateStr = "0.00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_rental_edit_hourly);

        carRentalCrntRate = findViewById(R.id.carRentalCrntRate);
        carRentalNewHourlyRate = findViewById(R.id.carRentalNewHourlyRate);
        // get prevHourlyrate to make it the crnt hourly rate
        Bundle prevRate = getIntent().getExtras();
        if (prevRate!=null){
            carRentalCrntHourlyRateStr = prevRate.getString("prevRate");
        }
        carRentalCrntRate.setText(carRentalCrntHourlyRateStr);
        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateRate()){
                    return;
                } else {
                    carRentalNewHourlyRateStr = carRentalNewHourlyRate.getText().toString();
                    carRentalCrntHourlyRateStr = carRentalNewHourlyRateStr;

                    Intent saveIntent = new Intent(CarRentalEditHourly.this, ManageBranches.class);
                    carRentalCrntRate.setText(carRentalCrntHourlyRateStr); // not too necessary but allows you to see new rate before it closes
                    startActivity(saveIntent);
                }
            }
        });

        discardBtn = findViewById(R.id.discardBtn);
        discardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CarRentalEditHourly.this, ManageBranches.class));
            }
        });
    }

    public String getCarRentalCrntHourlyRateStr(){
        return carRentalCrntHourlyRateStr;
    }


    private Boolean validateRate() {
        String rateChk = carRentalNewHourlyRate.getText().toString();
        if (rateChk.isEmpty()) {
            carRentalNewHourlyRate.setError("Field cannot be empty");
            return false;
        }
        return true;
    }


}