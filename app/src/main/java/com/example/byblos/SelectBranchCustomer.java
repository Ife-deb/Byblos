package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class SelectBranchCustomer extends AppCompatActivity {
    public static Button previousPage, searchPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_branch_customer);



        previousPage = findViewById(R.id.previousPageBtn);
        searchPage = findViewById(R.id.searchBtn);

        previousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectBranchCustomer.this, Login.class));
            }
        });

        searchPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SelectBranchCustomer.this, Search.class));
            }
        });



    }

    public void onClickBranchC(View view){

        ManageBranches ex1 = new ManageBranches();


        switch(view.getId()){
            case R.id.ottawaBranchC:
                Intent intent1 = new Intent(this, PageSelectService.class);
                intent1.putExtra("city", "Ottawa");

                Boolean v1O = ex1.getCarVis("Ottawa");
                Boolean v2O = ex1.getTruckVis("Ottawa");
                Boolean v3O = ex1.getAssVis("Ottawa");

                intent1.putExtra("carVis", v1O);
                intent1.putExtra("tVis",v2O);
                intent1.putExtra("aVis", v3O);

                startActivity(intent1);
                break;
            case R.id.torontoBranchC:
                Intent intent2 = new Intent(this, PageSelectService.class);
                intent2.putExtra("city", "Toronto");

                Boolean v1T = ex1.getCarVis("Toronto");
                Boolean v2T = ex1.getTruckVis("Toronto");
                Boolean v3T = ex1.getAssVis("Toronto");

                intent2.putExtra("carVis", v1T);
                intent2.putExtra("tVis",v2T );
                intent2.putExtra("aVis", v3T);

                startActivity(intent2);
                break;
            case R.id. montrealBranchC:
                Intent intent3 = new Intent(this, PageSelectService.class);
                intent3.putExtra("city", "Montreal");

                Boolean v1M = ex1.getCarVis("Montreal");
                Boolean v2M = ex1.getTruckVis("Montreal");
                Boolean v3M = ex1.getAssVis("Montreal");

                intent3.putExtra("carVis", v1M);
                intent3.putExtra("tVis",v2M);
                intent3.putExtra("aVis", v3M);

                startActivity(intent3);
                break;
            case R.id.kingstonBranchC:
                Intent intent4 = new Intent(this, PageSelectService.class);
                intent4.putExtra("city", "Kingston");

                Boolean v1K = ex1.getCarVis("Kingston");
                Boolean v2K = ex1.getTruckVis("Kingston");
                Boolean v3K = ex1.getAssVis("Kingston");

                intent4.putExtra("carVis", v1K);
                intent4.putExtra("tVis",v2K);
                intent4.putExtra("aVis", v3K);

                startActivity(intent4);
                break;
            case R.id.windsorBranchC:
                Intent intent5 = new Intent(this, PageSelectService.class);
                intent5.putExtra("city", "Windsor");

                Boolean v1W = ex1.getCarVis("Windsor");
                Boolean v2W = ex1.getTruckVis("Windsor");
                Boolean v3W = ex1.getAssVis("Windsor");

                intent5.putExtra("carVis", v1W);
                intent5.putExtra("tVis",v2W);
                intent5.putExtra("aVis", v3W);

                startActivity(intent5);
                break;

        }
    }


}