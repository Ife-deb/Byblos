package com.example.byblos;

import android.content.Intent;
import android.widget.DatePicker;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class CarRentalPage extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference reference;

    private DatePicker date1picker;
    private DatePicker date2picker;
    private DatePicker dobpicker;

    private TimePicker time1picker;
    private TimePicker time2picker;

    private Spinner spinner1;
    private static final String[] licenseOptions ={"G","G1","G3"};

    private Spinner spinner2;
    private static final String[] vehicleOptions ={"Compact","Intermediate","SUV"};

    private Button btn1;
    private EditText firstname, lastname, dateofbirth, time1, time2;
    private EditText customeraddress, customeremail, customercity, customerpostalcode;
    public static String cityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_rental_page);

        Bundle branchName = getIntent().getExtras();

        if (branchName != null) {
            cityName = branchName.getString("city");
        }
        firstname = findViewById(R.id.firstName);
        lastname = findViewById(R.id.lastName);
        customeraddress = findViewById(R.id.customerAddress);
        customerpostalcode = findViewById(R.id.customerPostalCode);
        customeremail = findViewById(R.id.customerEmail);
        customercity = findViewById(R.id.customerCity);

        dobpicker = (DatePicker) findViewById(R.id.DateOfBirth);
        date1picker = (DatePicker) findViewById(R.id.date1);
        date2picker = (DatePicker) findViewById(R.id.date2);
        time1 = findViewById(R.id.pickupTime1);
        time2 = findViewById(R.id.dropTime1);

        spinner1 = (Spinner) findViewById(R.id.licenseType);
        ArrayAdapter<String> stringArrayAdapter1 = new ArrayAdapter<String>(CarRentalPage.this, android.R.layout.simple_spinner_item, licenseOptions);
        stringArrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(stringArrayAdapter1);

        spinner2 = (Spinner) findViewById(R.id.vehicleType);
        ArrayAdapter<String> stringArrayAdapter2 = new ArrayAdapter<String>(CarRentalPage.this, android.R.layout.simple_spinner_item, vehicleOptions);
        stringArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(stringArrayAdapter2);


        btn1 = (Button) findViewById(R.id.submitRequestCar);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = firstname.getText().toString();
                String lname = lastname.getText().toString();
                String dob1 = dobpicker.getDayOfMonth() + "/" + (dobpicker.getMonth() + 1) + "/" + dobpicker.getYear();
                String pickup = date1picker.getDayOfMonth() + "/" + (date1picker.getMonth() + 1) + "/" + date1picker.getYear();
                String dropoff = date2picker.getDayOfMonth() + "/" + (date2picker.getMonth() + 1) + "/" + date2picker.getYear();
                String picktime = time1.getText().toString();
                String droptime = time2.getText().toString();
                String email = customeremail.getText().toString();
                String email2 = customeremail.getText().toString();
                String address = customeraddress.getText().toString();
                String postalcode = customerpostalcode.getText().toString();
                String city1 = customercity.getText().toString();

                //cleaning up email from unnecessary dots before @
                StringBuilder sb = new StringBuilder(email);
                int check = sb.indexOf(".");
                while (check >= 0 && check < sb.indexOf("@")) {
                    sb.deleteCharAt(sb.indexOf("."));
                    check = sb.indexOf(".");
                }
                //clean email address
                email2 = sb.toString();
                //Unique id is email without the dot (Firebase crashes if the uid has a "." in it)
                //remove all the last dot from the email
                if (sb.indexOf(".") != -1) {
                    sb.deleteCharAt(sb.indexOf("."));
                }
                //unique id: is email without dots.
                String uid = sb.toString();

                CarRequest carRequest = new CarRequest(dob1, fname, lname, address, city1, postalcode, email, pickup, dropoff, picktime, droptime);
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Requests");
                reference.child(city1).child(uid).setValue(carRequest);

                //validateEmail();
                //validateFirstName();
                //validateLastName();
                //validateBirth();
                //validateAddress();

                //if(isSelected() && validateEmail()){
                //return;
                //}
                openRequests();


            }
            });


        }

        private boolean isSelected () {
            int i = 0;
            String selected = String.valueOf(spinner2.getSelectedItem());
            if (selected == "Compact") {
                i = 1;
            } else if (selected == "Intermediate") {
                i = 2;
            } else if (selected == "SUV") {
                i = 3;
            } else {
                i = 4;
            }
            return i != 4;
        }
        private boolean validateEmail () {
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

        private boolean validateFirstName () {
            String firstChk = firstname.getText().toString();
            if (firstChk.isEmpty()) {
                firstname.setError("First name cannot be empty");
                return false;
            }
            String namePattern = "[a-zA-Z]+";

            if (!firstChk.matches(namePattern)) {
                firstname.setError("Only letters in first name");
            }

            firstname.setError(null);
            return true;
        }

        private boolean validateLastName () {
            String lastChk = lastname.getText().toString();
            if (lastChk.isEmpty()) {
                lastname.setError("Last name cannot be empty");
                return false;
            }
            String namePattern = "[a-zA-Z]+";

            if (!lastChk.matches(namePattern)) {
                lastname.setError("Only letters in last name");
            }

            lastname.setError(null);
            return true;
        }

    /*private boolean validateBirth(){
        String birthChk = dateofbirth.getText().toString();
        if (birthChk.isEmpty()){
            dateofbirth.setError("cannot be empty");
            return false;
        }

        dateofbirth.setError(null);
        return true;
    }*/

        private boolean validateAddress () {
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
        private boolean validatePostalCode () {
            String postalChk = customerpostalcode.getText().toString();
            String alphanumeric = "[A-Z0-9]*";
            if (postalChk.isEmpty()) {
                customerpostalcode.setError("Field cannot be empty");
                return false;
            }

            if (!postalChk.matches(alphanumeric)) {
                customerpostalcode.setError("Postal Code can only contain uppercase numbers and letters");
                return false;

            }

            if (postalChk.length() != 6) {
                customerpostalcode.setError("Postal Code can only be length of 6");
                return false;

            }

            //Intent intentCode = new Intent(this, CompleteBranchProfile.class);
            //intentCode.putExtra("code", postalChk);

            customerpostalcode.setError(null);
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

    private boolean validateStartHours() {
        String hS = time1.getText().toString();
        String hE = time2.getText().toString();
        String hPattern = "(0[0-9]|1[0-2])+:+[0-5]+[0-9]+[AP]+[M]";

        if (!hS.isEmpty()) {
            if (!hS.matches(hPattern)) {
                time1.setError("Starting hours does not match 12 hr format ##:##");
                return false;
            }
        }

        //if (hS.matches(hE)){
        //startHrs.setError("Starting hours and ending hours can't match");
        //return false;

        //}

        //Intent intentStartH = new Intent(this, CompleteBranchProfile.class);
        //intentStartH.putExtra("startH", hS);

        time1.setError(null);
        return true;
    }

    //validates ending hours
    private boolean validateEndHours() {
        String hE = time2.getText().toString();
        String hS = time1.getText().toString();
        String hPattern = "(0[0-9]|1[0-2])+:+[0-5]+[0-9]+[AP]+[M]";

        if (!hE.isEmpty()){
            if (!hE.matches(hPattern)) {
                time2.setError("Ending hours does not match 12 hr format ##:##");
                return false;
            }

        }

        //if (hE.matches(hS)){
        //startHrs.setError("Starting hours and ending hours can't match");
        //return false;

        //}

        //Intent intentEndH = new Intent(this, CompleteBranchProfile.class);
        //intentEndH.putExtra("endH", hE);

        time2.setError(null);
        return true;

    }

        public void openRequests () {
            String fname = firstname.getText().toString();
            String lname = lastname.getText().toString();
            String picktime = time1.getText().toString();
            String droptime = time2.getText().toString();
            String city1 = customercity.getText().toString();

            if (!isSelected() | !validateEmail() | !validateFirstName() | !validateLastName() | !validateAddress() | !validatePostalCode() | !validateCity() | !validateStartHours() | !validateEndHours()) {
                return;
            } else {

                Intent intentValues = new Intent(CarRentalPage.this, SelectBranchCustomer.class);
                intentValues.putExtra("name", fname + " " + lname);
                intentValues.putExtra("city", city1);
                intentValues.putExtra("pickup", picktime);
                intentValues.putExtra("dropoff", droptime);

                startActivity(intentValues);
            }


        }
}




