package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectBranch extends AppCompatActivity {
    public static Button ottawa, toronto, montreal, kingston, windsor;
    public Button prevPageBtn;
   // private String manageBranchesTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_branch);

        ottawa = findViewById(R.id.ottawaBranch);
        toronto = findViewById(R.id.torontoBranch);
        montreal = findViewById(R.id.montrealBranch);
        kingston = findViewById(R.id.kingstonBranch);
        windsor = findViewById(R.id.windsorBranch);
        prevPageBtn = findViewById(R.id.prevPageBtn);

        prevPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectBranch.this, Login.class));
            }
        });
    }

   public void onClickBranch(View view){

       ManageBranches ex1 = new ManageBranches();

        switch(view.getId()){
            case R.id.ottawaBranch:

                Intent intentOttawa = new Intent(this, ManageBranches.class);
                intentOttawa.putExtra("city", "Ottawa");

                Boolean v1O = ex1.getCarVis("Ottawa");
                Boolean v2O = ex1.getTruckVis("Ottawa");
                Boolean v3O = ex1.getAssVis("Ottawa");

                intentOttawa.putExtra("carVis", v1O);
                intentOttawa.putExtra("tVis",v2O);
                intentOttawa.putExtra("aVis", v3O);

                startActivity(intentOttawa);
                break;

            case R.id.torontoBranch:


                Intent intentToronto = new Intent(this, ManageBranches.class);
                intentToronto.putExtra("city","Toronto");

                Boolean v1T = ex1.getCarVis("Toronto");
                Boolean v2T = ex1.getTruckVis("Toronto");
                Boolean v3T = ex1.getAssVis("Toronto");

                intentToronto.putExtra("carVis", v1T);
                intentToronto.putExtra("tVis",v2T );
                intentToronto.putExtra("aVis", v3T);

                startActivity(intentToronto);

                break;

            case R.id. montrealBranch:


                Intent intentMontreal = new Intent(this, ManageBranches.class);
                intentMontreal.putExtra("city","Montreal");

                Boolean v1M = ex1.getCarVis("Montreal");
                Boolean v2M = ex1.getTruckVis("Montreal");
                Boolean v3M = ex1.getAssVis("Montreal");

                intentMontreal.putExtra("carVis", v1M);
                intentMontreal.putExtra("tVis",v2M);
                intentMontreal.putExtra("aVis", v3M);

                startActivity(intentMontreal);

                break;

            case R.id.kingstonBranch:

                Intent intentKingston = new Intent(this, ManageBranches.class);
                intentKingston.putExtra("city","Kingston");

                Boolean v1K = ex1.getCarVis("Kingston");
                Boolean v2K = ex1.getTruckVis("Kingston");
                Boolean v3K = ex1.getAssVis("Kingston");

                intentKingston.putExtra("carVis", v1K);
                intentKingston.putExtra("tVis",v2K);
                intentKingston.putExtra("aVis", v3K);

                startActivity(intentKingston);

                break;
                
            case R.id.windsorBranch:


                Intent intentWindsor = new Intent(this, ManageBranches.class);
                intentWindsor.putExtra("city","Windsor");

                Boolean v1W = ex1.getCarVis("Windsor");
                Boolean v2W = ex1.getTruckVis("Windsor");
                Boolean v3W = ex1.getAssVis("Windsor");

                intentWindsor.putExtra("carVis", v1W);
                intentWindsor.putExtra("tVis",v2W);
                intentWindsor.putExtra("aVis", v3W);

                startActivity(intentWindsor);

                break;
        }
   }






}
