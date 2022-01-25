package com.example.byblos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MovingAssistancePage extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private Button movingRequestBtn;
    private DatePicker dob;
    private EditText firstname, lastname, customeraddress, customercity, customerpostal, customeremail, startAddress, startPostal, startCity, endAddress, endCity, endPostal, numMovers, numBoxes;
    public static String cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moving_assistance_page);

        Bundle branchName = getIntent().getExtras();

        if (branchName != null) {
            cityName = branchName.getString("city");
        }

        firstname = findViewById(R.id.firstName);
        lastname = findViewById(R.id.lastName);
        dob = (DatePicker)findViewById(R.id.DateOfBirth);
        customeraddress = findViewById(R.id.customerAddress);
        customercity = findViewById(R.id.customerCity);
        customerpostal = findViewById(R.id.customerPostal);
        customeremail = findViewById(R.id.customerEmail);
        startAddress = findViewById(R.id.slAddress);
        startCity = findViewById(R.id.slCity);
        startPostal = findViewById(R.id.slPostal);
        endAddress =  findViewById(R.id.elAddress);
        endCity = findViewById(R.id.elCity);
        endPostal = findViewById(R.id.elPostal);
        numMovers = findViewById(R.id.numMovers);
        numBoxes = findViewById(R.id.numBoxes);



        movingRequestBtn = findViewById(R.id.submitRequestMoving);
        movingRequestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = firstname.getText().toString();
                String lname = lastname.getText().toString();
                String dob1 = dob.getDayOfMonth() + "/" + (dob.getMonth() + 1) + "/" + dob.getYear();
                String email = customeremail.getText().toString();
                String email2 = customeremail.getText().toString();
                String cAddress = customeraddress.getText().toString();
                String cCity = customercity.getText().toString();
                String cpostal = customerpostal.getText().toString();
                String sla = startAddress.getText().toString();
                String slc = startCity.getText().toString();
                String slp = startPostal.getText().toString();
                String ela = endAddress.getText().toString();
                String elc = endCity.getText().toString();
                String elp = endPostal.getText().toString();
                String numM = numMovers.getText().toString();
                String numB = numBoxes.getText().toString();

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

                MovingRequest movingRequest = new MovingRequest(fname, lname, dob1, cAddress, cCity, cpostal, email, sla, slp, slc, ela, elc, elp, numM, numB);
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("Requests");
                reference.child(slc).child(uid).setValue(movingRequest);



                //validateFirstName();
                //validateLastName();
                //validateAddress();
                //validateEmail();
                //validateStartLocation();
                //validateEndLocation();
                //validateNumMovers();
                //validateNumBoxes();
                openRequests();

            }
        });
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


    private boolean validateStartLocation(){
        String strlocChck = startAddress.getText().toString();
        if (strlocChck.isEmpty()){
            startAddress.setError("cannot be empty");
            return false;
        }

        if (!strlocChck.matches(cityName)) {
            startAddress.setError("City does not match corresponding Branch and/or start with uppercase");
            return false;
        }

        startAddress.setError(null);
        return true;
    }

    private boolean validateEndLocation(){
        String endlocChck = endAddress.getText().toString();
        if (endlocChck.isEmpty()){
            endAddress.setError("cannot be empty");
            return false;
        }

        if (!endlocChck.matches(cityName)) {
            endAddress.setError("City does not match corresponding Branch and/or start with uppercase");
            return false;
        }

        endAddress.setError(null);
        return true;
    }

    private boolean validateAddress(){
        String addrChck = customeraddress.getText().toString();


        String adFormat = "\\d+[ ]+[A-Za-z0-9.-]+[ ]+(([A-Za-z0-9.-]+[ ])?)+(?:Avenue|Boulevard|Building|Circle|Court|Crescent|Drive|Lane|Place|Road|Square|Station|Street|Terrace|Ave|Blvd|Bldg|Ci|Crt|Cres|Dr|Pl|Ln|Rd|Sq|Stn|St|Terr)\\.?+([ ]+(?:N|S|E|W))?\\.?";
        if (addrChck.isEmpty() ){
            customeraddress.setError("cannot be empty");

            return false;
        }


        if (!addrChck.matches(adFormat)) {
            customeraddress.setError("Not a real address in Canada");

            return false;
        }


        customeraddress.setError(null);

        return true;
    }

    private boolean validateStartAddress(){

        String start = startAddress.getText().toString();


        String adFormat = "\\d+[ ]+[A-Za-z0-9.-]+[ ]+(([A-Za-z0-9.-]+[ ])?)+(?:Avenue|Boulevard|Building|Circle|Court|Crescent|Drive|Lane|Place|Road|Square|Station|Street|Terrace|Ave|Blvd|Bldg|Ci|Crt|Cres|Dr|Pl|Ln|Rd|Sq|Stn|St|Terr)\\.?+([ ]+(?:N|S|E|W))?\\.?";

        if (start.isEmpty()){

            startAddress.setError("cannot be empty");

            return false;
        }



        if ( !start.matches(adFormat)) {

            startAddress.setError("Not a real address in Canada");

            return false;
        }



        startAddress.setError(null);

        return true;
    }

    private boolean validateEndAddress(){

        String end = endAddress.getText().toString();

        String adFormat = "\\d+[ ]+[A-Za-z0-9.-]+[ ]+(([A-Za-z0-9.-]+[ ])?)+(?:Avenue|Boulevard|Building|Circle|Court|Crescent|Drive|Lane|Place|Road|Square|Station|Street|Terrace|Ave|Blvd|Bldg|Ci|Crt|Cres|Dr|Pl|Ln|Rd|Sq|Stn|St|Terr)\\.?+([ ]+(?:N|S|E|W))?\\.?";

        if (end.isEmpty()){

            endAddress.setError("cannot be empty");
            return false;
        }


        if ( !end.matches(adFormat)) {

            endAddress.setError("Not a real address in Canada");
            return false;
        }


        endAddress.setError(null);
        return true;
    }

    private boolean validatePostalCode() {
        String postalChk = customerpostal.getText().toString();

        String alphanumeric = "[A-Z0-9]*";
        if (postalChk.isEmpty() ) {
            customerpostal.setError("Field cannot be empty");

            return false;
        }



        if (!postalChk.matches(alphanumeric) ){
            customerpostal.setError("Postal Code can only contain uppercase numbers and letters");

            return false;

        }


        if (postalChk.length()!=6 ) {
            customerpostal.setError("Postal Code can only be length of 6");
            return false;

        }


        //Intent intentCode = new Intent(this, CompleteBranchProfile.class);
        //intentCode.putExtra("code", postalChk);

        customerpostal.setError(null);

        return true;

    }
    private boolean validateStartPostalCode() {

        String start = startPostal.getText().toString();

        String alphanumeric = "[A-Z0-9]*";


        if (start.isEmpty() ) {

            startPostal.setError("Field cannot be empty");

            return false;
        }




        if ( !start.matches(alphanumeric)){

            startPostal.setError("Postal Code can only contain uppercase numbers and letters");

            return false;

        }



        if ( start.length()!=6 ) {
            startPostal.setError("Postal Code can only be length of 6");
            return false;

        }


        //Intent intentCode = new Intent(this, CompleteBranchProfile.class);
        //intentCode.putExtra("code", postalChk);


        startPostal.setError(null);

        return true;

    }
    private boolean validateEndPostalCode() {

        String end = endPostal.getText().toString();
        String alphanumeric = "[A-Z0-9]*";


        if (end.isEmpty()) {

            endPostal.setError("Field cannot be empty");
            return false;
        }


        if (!end.matches(alphanumeric)){

            endPostal.setError("Postal Code can only contain uppercase numbers and letters");
            return false;

        }


        if (end.length()!=6) {
            endPostal.setError("Postal Code can only be length of 6");
            return false;

        }

        //Intent intentCode = new Intent(this, CompleteBranchProfile.class);
        //intentCode.putExtra("code", postalChk);


        endPostal.setError(null);
        return true;

    }


    private boolean validateNumMovers(){
        String numMovChck = numMovers.getText().toString();
        if (numMovChck.isEmpty()){
            numMovers.setError("cannot be empty");
            return false;
        }

        numMovers.setError(null);
        return true;
    }

    private boolean validateNumBoxes(){
        String numBoxChck = numBoxes.getText().toString();
        if (numBoxChck.isEmpty()){
            numBoxes.setError("cannot be empty");
            return false;
        }

        numBoxes.setError(null);
        return true;
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

    public void openRequests(){
        String fname = firstname.getText().toString();
        String lname = lastname.getText().toString();
        String city1 = customercity.getText().toString();

        if ( !validateEmail() | !validateFirstName() | !validateLastName() | !validateAddress() | !validateStartAddress() | !validateEndAddress() | !validateCity() | !validateStartLocation() | !validateEndLocation() | !validateNumMovers() | !validateNumBoxes() | !validatePostalCode() | !validateStartPostalCode() | !validateEndPostalCode()) {
            return;
        }

        else {

            Intent intentValues = new Intent(MovingAssistancePage.this, SelectBranchCustomer.class);
            intentValues.putExtra("name", fname + " " + lname);
            intentValues.putExtra("city", city1);

            startActivity(intentValues);
        }






    }

}