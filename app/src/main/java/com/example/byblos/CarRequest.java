package com.example.byblos;

public class CarRequest {

    public String dob, firstname, lastname, address, city, postalcode, email, carpickup, cardropoff,picktime, droptime;

    public CarRequest(){
    }

    public CarRequest(String dob, String firstname, String lastname, String address, String city, String postalcode, String email, String carpickup, String cardropoff, String picktime, String droptime){
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
    }

    public String toString(){
        return city + email;
    }

    public String getEmail(){
        return email;
    }

    public String getCity(){
        return city;
    }


}
