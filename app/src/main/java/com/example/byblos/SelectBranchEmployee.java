package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectBranchEmployee extends AppCompatActivity {
    public static Button ottawa, toronto, montreal, kingston, windsor, prevPageBtn, searchPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_branch_employee);

        ottawa = findViewById(R.id.ottawaBranchE);
        toronto = findViewById(R.id.torontoBranchE);
        montreal = findViewById(R.id.montrealBranchE);
        kingston = findViewById(R.id.kingstonBranchE);
        windsor = findViewById(R.id.windsorBranchE);
        prevPageBtn = findViewById(R.id.prevPageBtn);
        searchPage = findViewById(R.id.searchBtn);

        prevPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectBranchEmployee.this, Login.class));
            }
        });

        searchPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectBranchEmployee.this, Search.class));
            }
        });
    }


    public void onClickBranchE(View view){

        switch(view.getId()){
            case R.id.ottawaBranchE:


                Intent intentOttawa = new Intent(this, BranchProfile.class);
                intentOttawa.putExtra("city", "Ottawa");
                startActivity(intentOttawa);

                break;

            case R.id.torontoBranchE:


                Intent intentToronto = new Intent(this, BranchProfile.class);
                intentToronto.putExtra("city", "Toronto");
                startActivity(intentToronto);
                //ManageBranches.branchLabel.setText("Toronto");
                break;

            case R.id. montrealBranchE:


                Intent intentMontreal = new Intent(this, BranchProfile.class);
                intentMontreal.putExtra("city", "Montreal");
                startActivity(intentMontreal);

                break;

            case R.id.kingstonBranchE:


                Intent intentKingston = new Intent(this, BranchProfile.class);
                intentKingston.putExtra("city", "Kingston");
                startActivity(intentKingston);

                break;

            case R.id.windsorBranchE:


                Intent intentWindsor = new Intent(this, BranchProfile.class);
                intentWindsor.putExtra("city", "Windsor");
                startActivity(intentWindsor);

                break;
        }
    }
}
