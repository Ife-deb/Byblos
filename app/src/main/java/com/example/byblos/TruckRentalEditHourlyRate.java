package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TruckRentalEditHourlyRate extends AppCompatActivity {

    public static EditText truckRentalNewHourlyRate;
    public static TextView truckRentalCrntRate;
    private Button saveBtn, discardBtn;

    public static String truckRentalNewHourlyRateStr;
    public static String truckRentalCrntHourlyRateStr = "0.00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_rental_edit_hourly_rate);

        truckRentalCrntRate = findViewById(R.id.truckRentalCrntRate);
        truckRentalNewHourlyRate = findViewById(R.id.truckRentalNewHourlyRate);

        // get prevHourlyrate to make it the crnt hourly rate
        Bundle prevRate = getIntent().getExtras();
        if (prevRate!=null){
            truckRentalCrntHourlyRateStr = prevRate.getString("prevRate");
        }

        truckRentalCrntRate.setText(truckRentalCrntHourlyRateStr);


        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateRate()) {
                    return;
                } else {
                    truckRentalNewHourlyRateStr = truckRentalNewHourlyRate.getText().toString();
                    truckRentalCrntHourlyRateStr = truckRentalNewHourlyRateStr;

                    Intent saveIntent = new Intent(TruckRentalEditHourlyRate.this, ManageBranches.class);
                    truckRentalCrntRate.setText(truckRentalCrntHourlyRateStr);
                    startActivity(saveIntent);
                }
            }
        });

        discardBtn = findViewById(R.id.discardBtn);
        discardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TruckRentalEditHourlyRate.this, ManageBranches.class));
            }
        });
    }

    public String getTruckRentalCrntHourlyRateStr(){
        return truckRentalCrntHourlyRateStr;
    }

    private Boolean validateRate() {
        String rateChk = truckRentalNewHourlyRate.getText().toString();
        if (rateChk.isEmpty()) {
            truckRentalNewHourlyRate.setError("Field cannot be empty");
            return false;
        }
        return true;
    }

}