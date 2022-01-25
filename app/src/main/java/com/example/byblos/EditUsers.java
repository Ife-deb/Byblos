package com.example.byblos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditUsers extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_users);

        listView = findViewById(R.id.list);



        ArrayList<String> users = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.list_item, users);
        listView.setAdapter(adapter);
        //reference points to users node in database
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("users");
        //access all users
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //clears the users list: so we always have the latest info from database
                users.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String type;
                    if (snapshot.child("employee").getValue().toString().equals("yes")){
                        type="employee";
                    }
                    else{
                        type="customer";
                    }
                    String user = "name: "+ snapshot.child("firstName").getValue().toString()+ " "+ snapshot.child("lastName").getValue().toString() +"\n"+type;
                    users.add(user);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        //remove user
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int itemPosition = position;
                new AlertDialog.Builder(EditUsers.this).setTitle("remove?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        users.remove(itemPosition);
                        adapter.notifyDataSetChanged();
                    }
                }).setNegativeButton("No", null).show();
                return true;
            }
        });
    }
}