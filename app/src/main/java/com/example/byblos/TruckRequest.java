
package com.example.byblos;

public class TruckRequest {

    public String dob, firstname, lastname, address, city, postalcode, email, carpickup, cardropoff,picktime, droptime, kms, area;

    public TruckRequest(){
    }

    public TruckRequest(String dob, String firstname, String lastname, String address, String city, String postalcode, String email, String carpickup, String cardropoff, String picktime, String droptime, String kms, String area){
        this.dob = dob;
        this.carpickup = carpickup;
        this.cardropoff = cardropoff;
        this.picktime = picktime;
        this.droptime = droptime;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.address = address;
        this.postalcode = postalcode;
        this.city = city;
        this.kms = kms;
        this.area = area;
    }
}