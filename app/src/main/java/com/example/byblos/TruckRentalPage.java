package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TruckRentalPage extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private Spinner spinner1;
    private static final String[] licenseOptions ={"G","G1","G3"};
    private DatePicker dob, pickup, drop;
    private Button btn1;

    private EditText firstname, lastname, customeraddress, customerpostalcode, customercity, customeremail, prefpick, prefdrop, max, area;
    public static String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_rental_page);

        Bundle branchName = getIntent().getExtras();

        if (branchName != null) {
            cityName = branchName.getString("city");
        }

        spinner1 = (Spinner)findViewById(R.id.licenseType);
        ArrayAdapter<String>stringArrayAdapter = new ArrayAdapter<String>(TruckRentalPage.this, android.R.layout.simple_spinner_item, licenseOptions);
        stringArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(stringArrayAdapter);


        firstname = findViewById(R.id.firstName);
        lastname = findViewById(R.id.lastName);
        dob = (DatePicker)findViewById(R.id.DateOfBirth);
        customeraddress = findViewById(R.id.customerAddress);
        customerpostalcode = findViewById(R.id.customerPostalCode);
        customercity = findViewById(R.id.customerCity);
        customeremail = findViewById(R.id.customerEmail);
        pickup = (DatePicker) findViewById(R.id.date1);
        drop = (DatePicker)findViewById(R.id.date2);
        prefpick = findViewById(R.id.pickupTime1);
        prefdrop = findViewById(R.id.dropTime1);
        max = findViewById(R.id.maxKm);
        area = findViewById(R.id.areaToBeUsed);

        btn1 = (Button)findViewById(R.id.submitRequestTruck);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = firstname.getText().toString();
                String lname = lastname.getText().toString();
                String dob1 = dob.getDayOfMonth() + "/" + (dob.getMonth() + 1) + "/" + dob.getYear();
                String pickup1 = pickup.getDayOfMonth() + "/" + (pickup.getMonth() + 1) + "/" + pickup.getYear();
                String dropoff1 = drop.getDayOfMonth() + "/" + (drop.getMonth() + 1) + "/" + drop.getYear();
                String picktime = prefpick.getText().toString();
                String droptime = prefdrop.getText().toString();
                String email = customeremail.getText().toString();
                String email2 = customeremail.getText().toString();
                String address = customeraddress.getText().toString();
                String postalcode = customerpostalcode.getText().toString();
                String city1 = customercity.getText().toString();
                String kms = max.getText().toString();
                String a = area.getText().toString();

                //cleaning up email from unnecessary dots before @
                StringBuilder sb = new StringBuilder(email);
                int check= sb.indexOf(".");
                while(check>=0 && check<sb.indexOf("@")) {
                    sb.deleteCharAt(sb.indexOf("."));
                    check = sb.indexOf(".");
                }
                //clean email address
                email2 = sb.toString();
                //Unique id is email without the dot (Firebase crashes if the uid has a "." in it)
                //remove all the last dot from the email
                if (sb.indexOf(".")!=-1){
                    sb.deleteCharAt(sb.indexOf("."));
                }
                //unique id: is email without dots.
                String uid = sb.toString();

                TruckRequest truckRequest = new TruckRequest(dob1, fname, lname, address, city1, postalcode, email, pickup1, dropoff1, picktime, droptime, kms, a);
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Requests");
                reference.child(city1).child(uid).setValue(truckRequest);

                //validateEmail();
                //validateFirstName();
                //validateLastName();
                //validateAddress();
                //validateTime();
                //validateDropTime();
                //validateMax();
                //validateArea();
                openRequests();
            }
        });
    }

    private boolean validateEmail() {
        String emailChk = customeremail.getText().toString();
        if (emailChk.isEmpty()) {
            customeremail.setError("Email cannot be empty");
            return false;
        }
        //this string represents the email pattern
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!emailChk.matches(emailPattern)) {
            customeremail.setError("incorrect email");
            return false;
        }

        customeremail.setError(null);
        return true;
    }

    private boolean validateFirstName(){
        String firstChk = firstname.getText().toString();
        if (firstChk.isEmpty()){
            firstname.setError("First name cannot be empty");
            return false;
        }
        String namePattern = "[a-zA-Z]+";

        if(!firstChk.matches(namePattern)){
            firstname.setError("Only letters in first name");
        }

        firstname.setError(null);
        return true;
    }

    private boolean validateLastName(){
        String lastChk = lastname.getText().toString();
        if (lastChk.isEmpty()){
            lastname.setError("Last name cannot be empty");
            return false;
        }
        String namePattern = "[a-zA-Z]+";

        if(!lastChk.matches(namePattern)){
            lastname.setError("Only letters in last name");
        }

        lastname.setError(null);
        return true;
    }
/*
    private boolean validateBirth(){
        String birthChk = dateofbirth.getText().toString();
        if (birthChk.isEmpty()){
            dateofbirth.setError("cannot be empty");
            return false;
        }

        dateofbirth.setError(null);
        return true;
    }*/

    private boolean validateAddress() {
        String ad = customeraddress.getText().toString();
        String adFormat = "\\d+[ ]+[A-Za-z0-9.-]+[ ]+(([A-Za-z0-9.-]+[ ])?)+(?:Avenue|Boulevard|Building|Circle|Court|Crescent|Drive|Lane|Place|Road|Square|Station|Street|Terrace|Ave|Blvd|Bldg|Ci|Crt|Cres|Dr|Pl|Ln|Rd|Sq|Stn|St|Terr)\\.?+([ ]+(?:N|S|E|W))?\\.?";

        if (ad.isEmpty()) {
            customeraddress.setError("Field cannot be empty");
            return false;
        }

        if (!ad.matches(adFormat)) {
            customeraddress.setError("Not a real address in Canada");
            return false;
        }

        //Intent intentAddr = new Intent(this, CompleteBranchProfile.class);
        //intentAddr.putExtra("address", ad);

        customeraddress.setError(null);
        return true;
    }
    private boolean validatePostalCode() {
        String postalChk = customerpostalcode.getText().toString();
        String alphanumeric = "[A-Z0-9]*";
        if (postalChk.isEmpty()) {
            customerpostalcode.setError("Field cannot be empty");
            return false;
        }

        if (!postalChk.matches(alphanumeric)){
            customerpostalcode.setError("Postal Code can only contain uppercase numbers and letters");
            return false;

        }

        if (postalChk.length()!=6){
            customerpostalcode.setError("Postal Code can only be length of 6");
            return false;

        }

        //Intent intentCode = new Intent(this, CompleteBranchProfile.class);
        //intentCode.putExtra("code", postalChk);

        customerpostalcode.setError(null);
        return true;

    }

/*
    private boolean validatePickup(){
        String pChk = pickup.getText().toString();
        if (pChk.isEmpty()){
            pickup.setError("cannot be empty");
            return false;
        }

        pickup.setError(null);
        return true;
    }*/

    //private boolean validateTime(){
        //String preChk = prefpick.getText().toString();
        //if (preChk.isEmpty()){
            //prefpick.setError("cannot be empty");
            //return false;
        //}

        //prefpick.setError(null);
        //return true;
    //}

/*
    private boolean validateDrop(){
        String drChk = drop.getText().toString();
        if (drChk.isEmpty()){
            drop.setError("cannot be empty");
            return false;
        }

        drop.setError(null);
        return true;
    }*/

    //private boolean validateDropTime(){
        //String pdChk = prefdrop.getText().toString();
        //if (pdChk.isEmpty()){
            //prefdrop.setError("cannot be empty");
            //return false;
        //}

        //prefdrop.setError(null);
        //return true;
    //}

    private boolean validateStartHours() {
        String hS = prefpick.getText().toString();
        String hE = prefdrop.getText().toString();
        String hPattern = "(0[0-9]|1[0-2])+:+[0-5]+[0-9]+[AP]+[M]";

        if (!hS.isEmpty()) {
            if (!hS.matches(hPattern)) {
                prefpick.setError("Starting hours does not match 12 hr format ##:##");
                return false;
            }
        }

        //if (hS.matches(hE)){
        //startHrs.setError("Starting hours and ending hours can't match");
        //return false;

        //}

        //Intent intentStartH = new Intent(this, CompleteBranchProfile.class);
        //intentStartH.putExtra("startH", hS);

        prefpick.setError(null);
        return true;
    }

    //validates ending hours
    private boolean validateEndHours() {
        String hE = prefdrop.getText().toString();
        String hS = prefpick.getText().toString();
        String hPattern = "(0[0-9]|1[0-2])+:+[0-5]+[0-9]+[AP]+[M]";

        if (!hE.isEmpty()){
            if (!hE.matches(hPattern)) {
                prefdrop.setError("Ending hours does not match 12 hr format ##:##");
                return false;
            }

        }

        //if (hE.matches(hS)){
        //startHrs.setError("Starting hours and ending hours can't match");
        //return false;

        //}

        //Intent intentEndH = new Intent(this, CompleteBranchProfile.class);
        //intentEndH.putExtra("endH", hE);

        prefdrop.setError(null);
        return true;

    }

    private boolean validateMax(){
        String mChk = max.getText().toString();
        if (mChk.isEmpty()){
            max.setError("cannot be empty");
            return false;
        }

        max.setError(null);
        return true;
    }

    private boolean validateArea(){
        String aChk = area.getText().toString();
        if (aChk.isEmpty()){
            area.setError("cannot be empty");
            return false;
        }

        area.setError(null);
        return true;
    }

    private boolean validateCity () {
        String cityChk = customercity.getText().toString();
        if (cityChk.isEmpty()) {
            customercity.setError("Field cannot be empty");
            return false;
        }

        if (!cityChk.matches(cityName)) {
            customercity.setError("City does not match corresponding Branch and/or start with uppercase");
            return false;
        }


        //Intent intentCity = new Intent(this, CompleteBranchProfile.class);
        //intentCity.putExtra("city", cityChk);

        customercity.setError(null);
        return true;

    }

    public void openRequests(){
        String fname = firstname.getText().toString();
        String lname = lastname.getText().toString();
        String city1 = customercity.getText().toString();

        if ( !validateEmail() | !validateCity() | !validateFirstName() | !validateLastName() | !validateAddress() | !validatePostalCode() | !validateStartHours() | !validateEndHours() | !validateMax() | !validateArea()) {
            return;
        }

        else {

            Intent intentValues = new Intent(TruckRentalPage.this, SelectBranchCustomer.class);
            intentValues.putExtra("name", fname + " " + lname);
            intentValues.putExtra("city", city1);

            startActivity(intentValues);
        }






    }
}
