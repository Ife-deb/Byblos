package com.example.byblos;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BranchProfile extends AppCompatActivity {
    //text fields
    private TextInputLayout phoneNum, addr, city, postalCode, startHrs, endHrs;

    //button
    public static Button registerButton, prevPageBtn;

    //labels
    public static TextView branchLabel;
    public static String cityName;

    //firebase
    private FirebaseDatabase database;
    private DatabaseReference reference;

    public BranchProfile(){}


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_profile);

        //get branch city name

        Bundle branchName = getIntent().getExtras();
        if (branchName != null) {
            cityName = branchName.getString("city");

        }

        phoneNum = findViewById(R.id.phoneNumber);
        addr = findViewById(R.id.addressLine);
        city = findViewById(R.id.cityField);
        postalCode = findViewById(R.id.postalCodeField);
        startHrs = findViewById(R.id.hrsStartField);
        endHrs = findViewById(R.id.hrsEndField);

        //button
        registerButton = findViewById(R.id.registerButton);
        prevPageBtn = findViewById(R.id.prevPageBtn);
        prevPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BranchProfile.this, SelectBranchEmployee.class));
            }
        });


        // changing branch name
        branchLabel = findViewById(R.id.branchName);
        branchLabel.setText(cityName);

    }
    //important for unit test!
    public BranchProfile(Context context){

    }

    //validates phone number
    private boolean validatePhoneNumber() {
        String num = phoneNum.getEditText().getText().toString();
        String numFormat = "[0-9]+[0-9]+[0-9]+-+[0-9]+[0-9]+[0-9]+-+[0-9]+[0-9]+[0-9]+[0-9]";

        if (num.isEmpty()) {
            phoneNum.setError("Field cannot be empty");
            return false;
        }

        if (!num.matches(numFormat)) {
            phoneNum.setError("Phone number does not follow format ###-###-####");
            return false;
        }

        //Intent intentPhone = new Intent(this, CompleteBranchProfile.class);
        //intentPhone.putExtra("number", num);

        phoneNum.setError(null);
        phoneNum.setErrorEnabled(false);
        return true;
    }

    //validates address
    private boolean validateAddress() {
        String ad = addr.getEditText().getText().toString();
        String adFormat = "\\d+[ ]+[A-Za-z0-9.-]+[ ]+(([A-Za-z0-9.-]+[ ])?)+(?:Avenue|Boulevard|Building|Circle|Court|Crescent|Drive|Lane|Place|Road|Square|Station|Street|Terrace|Ave|Blvd|Bldg|Ci|Crt|Cres|Dr|Pl|Ln|Rd|Sq|Stn|St|Terr)\\.?+([ ]+(?:N|S|E|W))?\\.?";

        if (ad.isEmpty()) {
            addr.setError("Field cannot be empty");
            return false;
        }

        if (!ad.matches(adFormat)) {
            addr.setError("Not a real address in Canada");
            return false;
        }

        addr.setError(null);
        addr.setErrorEnabled(false);
        return true;
    }


    // validate city
    private boolean validateCity() {
        String cityChk = city.getEditText().getText().toString();
        if (cityChk.isEmpty()) {
            city.setError("Field cannot be empty");
            return false;
        }

        if (!cityChk.matches(cityName)) {
            city.setError("City does not match corresponding Branch and/or start with uppercase");
            return false;
        }

        city.setError(null);
        city.setErrorEnabled(false);
        return true;
    }

    //validates postal code
    private boolean validatePostalCode() {
        String postalChk = postalCode.getEditText().getText().toString();
        String alphanumeric = "[A-Z0-9]*";
        if (postalChk.isEmpty()) {
            postalCode.setError("Field cannot be empty");
            return false;
        }

        if (!postalChk.matches(alphanumeric)){
            postalCode.setError("Postal Code can only contain uppercase numbers and letters");
            return false;

        }

        if (postalChk.length()!=6){
            postalCode.setError("Postal Code can only be length of 6");
            return false;

        }

        postalCode.setError(null);
        postalCode.setErrorEnabled(false);
        return true;

    }

    //validates starting hours
    private boolean validateStartHours() {
        String hS = startHrs.getEditText().getText().toString();
        String hE = endHrs.getEditText().getText().toString();
        String hPattern = "(0[0-9]|1[0-2])+:+[0-5]+[0-9]+[AP]+[M]";

        if (!hS.isEmpty()) {
            if (!hS.matches(hPattern)) {
                startHrs.setError("Starting hours does not match 12 hr format ##:##");
                return false;
            }
        }

        startHrs.setError(null);
        startHrs.setErrorEnabled(false);
        return true;
    }

    //validates ending hours
    private boolean validateEndHours() {
        String hE = endHrs.getEditText().getText().toString();
        String hS = startHrs.getEditText().getText().toString();
        String hPattern = "(0[0-9]|1[0-2])+:+[0-5]+[0-9]+[AP]+[M]";

        if (!hE.isEmpty()){
            if (!hE.matches(hPattern)) {
                endHrs.setError("Ending hours does not match 12 hr format ##:##");
                return false;
            }

        }

        endHrs.setError(null);
        endHrs.setErrorEnabled(false);
        return true;

    }

    public Boolean validateAddressT(String address){
        String adFormat = "\\d+[ ]+[A-Za-z0-9.-]+[ ]+(([A-Za-z0-9.-]+[ ])?)+(?:Avenue|Boulevard|Building|Circle|Court|Crescent|Drive|Lane|Place|Road|Square|Station|Street|Terrace|Ave|Blvd|Bldg|Ci|Crt|Cres|Dr|Pl|Ln|Rd|Sq|Stn|St|Terr)\\.?+([ ]+(?:N|S|E|W))?\\.?";
        if (address.isEmpty()) {
            return false;
        }
        if (!address.matches(adFormat)) {
            return false;
        }
        return true;
    }

    public boolean validatePhoneNumberPattern(String num) {
        String numFormat = "[0-9]+[0-9]+[0-9]+-+[0-9]+[0-9]+[0-9]+-+[0-9]+[0-9]+[0-9]+[0-9]";

        if (num.isEmpty()) {
            return false;
        }
        if (!num.matches(numFormat)) {
            return false;
        }
        return true;
    }


    public void openManageProfileActivity (View view) {
       if (!validatePhoneNumber() | !validateAddress() | !validateCity() | !validatePostalCode() | !validateStartHours() | !validateEndHours()) {
           return;
        }

        else {

            String one = phoneNum.getEditText().getText().toString();
            String two = addr.getEditText().getText().toString();
            String three = city.getEditText().getText().toString();
            String four = postalCode.getEditText().getText().toString();
            String five = startHrs.getEditText().getText().toString();
            String six = endHrs.getEditText().getText().toString();

            CreateBranch createBranch = new CreateBranch(three,five,six);
            database = FirebaseDatabase.getInstance();
            reference = database.getReference("Branches");
            reference.child(three).setValue(createBranch);

            Intent intentValues = new Intent(this, CompleteBranchProfile.class);
            intentValues.putExtra("phone", one);
            intentValues.putExtra("address", two);
            intentValues.putExtra("city", three);
            intentValues.putExtra("code",four);
            intentValues.putExtra("startH", five);
            intentValues.putExtra("endH", six);

            startActivity(intentValues);
        }

    }

}


