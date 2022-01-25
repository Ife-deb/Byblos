package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MovingAssEditHourly extends AppCompatActivity {

    public static EditText moveAssNewHourlyRate;
    public static TextView moveAssCrntRate;
    public static String moveAssNewHourlyRateStr;
    public static String moveAssCrntHourlyRateStr = "0.00";
    private Button saveBtn, discardBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moving_ass_edit_hourly);

        moveAssCrntRate = findViewById(R.id.moveAssCrntRate);
        moveAssNewHourlyRate = findViewById(R.id.moveAssNewHourlyRate);

        // get prevHourlyrate to make it the crnt hourly rate
        Bundle prevRate = getIntent().getExtras();
        if (prevRate!=null){
            moveAssCrntHourlyRateStr = prevRate.getString("prevRate");
        }

        moveAssCrntRate.setText(moveAssCrntHourlyRateStr);

        saveBtn = findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateRate()){
                    return;
                } else {
                    moveAssNewHourlyRateStr = moveAssNewHourlyRate.getText().toString();
                    moveAssCrntHourlyRateStr = moveAssNewHourlyRateStr;

                    Intent saveIntent = new Intent(MovingAssEditHourly.this, ManageBranches.class);
                    moveAssCrntRate.setText(moveAssCrntHourlyRateStr); // not too necessary but allows you to see new rate before it closes
                    startActivity(saveIntent);
                }
            }
        });

        discardBtn = findViewById(R.id.discardBtn);
        discardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MovingAssEditHourly.this, ManageBranches.class));
            }
        });
    }

    public String getMoveAssCrntHourlyRateStr(){
        return moveAssCrntHourlyRateStr;
    }


    private Boolean validateRate() {
        String rateChk = moveAssNewHourlyRate.getText().toString();
        if (rateChk.isEmpty()) {
            moveAssNewHourlyRate.setError("Field cannot be empty");
            return false;
        }
        return true;
    }
}