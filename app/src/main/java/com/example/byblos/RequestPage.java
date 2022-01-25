

package com.example.byblos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RequestPage extends AppCompatActivity {
    DatabaseReference databaseReference;
    ListView listView;
    ListView listView2;
    ListView listView3;

    ArrayList<String> carR = new ArrayList<String>();;
    ArrayAdapter<String> stringArrayAdapter;

    //ArrayList<String> truckR = new ArrayList<String>();;
    //ArrayAdapter<String> stringArrayAdapterT;

    //ArrayList<String> movingR = new ArrayList<String>();;
    //ArrayAdapter<String> stringArrayAdapterM;

    CarRequest car;

    public static String city;
    public static String request1;
    public static String request2;
    public static String request3;
    public static String request4;
    public static String request5;
    public static TextView cityLabel, oneLabel, twoLabel, threeLabel, fourLabel, fiveLabel;
    private Button prevPageBtn;
    private Button delete;

    //private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_page);
        //mAuth = FirebaseAuth.getInstance();
        //String emailStr = email.getEditText().getText().toString();
        //String passwordStr = password.getEditText().getText().toString();


        databaseReference = FirebaseDatabase.getInstance().getReference("Requests");
        listView = (ListView) findViewById(R.id.list);
        //listView2 = (ListView) findViewById(R.id.list2);
        //listView3 = (ListView) findViewById(R.id.list3);
        stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, carR);
        //stringArrayAdapterT = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,truckR);
        //stringArrayAdapterM = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,movingR);

        listView.setAdapter(stringArrayAdapter);
        //listView2.setAdapter(stringArrayAdapterT);
        //listView3.setAdapter(stringArrayAdapterM);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    carR.add(snapshot1.getValue().toString());
                }
                stringArrayAdapter.notifyDataSetChanged();

                //for (DataSnapshot snapshot2 : snapshot.getChildren()) {

                //truckR.add(snapshot2.getValue().toString());
                //}
                //stringArrayAdapterT.notifyDataSetChanged();

                //for (DataSnapshot snapshot3 : snapshot.getChildren()) {

                //movingR.add(snapshot3.getValue().toString());
                //}
                //stringArrayAdapterM.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int itemPostion = position;

                new AlertDialog.Builder(RequestPage.this).setTitle("deny?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        carR.remove(itemPostion);
                        stringArrayAdapter.notifyDataSetChanged();

                    }
                }) .setNegativeButton("No", null).show();

                return true;
            }
        });










        Bundle extras = getIntent().getExtras();
        if (extras!=null){
            request1 = extras.getString("name");
            //request2 = extras.getString("address");
            city = extras.getString("city");
            //request2 = extras.getString("code");
            //request5 = extras.getString("startH");
        }

        //initialize labels
        cityLabel = findViewById(R.id.branchName);
        //oneLabel = findViewById(R.id.r1);
        //twoLabel = findViewById(R.id.r2);
        //threeLabel = findViewById(R.id.r3);
        //fourLabel = findViewById(R.id.r4);
        //fiveLabel = findViewById(R.id.r5);

        // set labels
        cityLabel.setText(city);
        //oneLabel.setText(request1);

        prevPageBtn = findViewById(R.id.prevPageBtn);
        prevPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RequestPage.this, CompleteBranchProfile.class));
            }
        });
    }

    private boolean deleteRequest(String id) {
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("Requests").child(id);
        dR.removeValue();

        Toast.makeText(getApplicationContext(), "Request", Toast.LENGTH_LONG).show();
        return true;
    }




}