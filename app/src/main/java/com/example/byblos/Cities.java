package com.example.byblos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cities extends AppCompatActivity {

    private ListView listView;
    private Button previousPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cities);
        previousPage = findViewById(R.id.previousPageBtn);

        previousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Cities.this, Search.class));
            }
        });

        listView = findViewById(R.id.citiesList);



        ArrayList<String> users;
        users = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item, users);
        listView.setAdapter(adapter);
        //reference points to users node in database
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Branches");
        //access all users
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clears the users list: so we always have the latest info from database
                users.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //String type;
                    //type=snapshot.child("city").getValue().toString();
                    if (snapshot.child("city").getValue() != null) {
                        String user = "Byblos Branch: " + snapshot.child("city").getValue().toString() + " Branch." + "\n";
                        users.add(user);
                    } else {
                        continue;
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}