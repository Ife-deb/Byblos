package com.example.byblos;

public class MovingRequest {

    public String firstname;
    public String lastname;
    public String dateofbirth;
    public String customeraddress;
    public String customercity;
    public String customerpostal;
    public String customeremail;
    public String startAddress;
    public String startPostal;
    public String startCity;
    public String endAddress;
    public String endCity;
    public String endPostal;
    public String numMovers;
    public String numBoxes;

    public MovingRequest(String firstname, String lastname, String dateofbirth, String customeraddress, String customercity, String customerpostal, String customeremail, String startAddress, String startPostal, String startCity, String endAddress, String endCity, String endPostal, String numMovers, String numBoxes) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateofbirth = dateofbirth;
        this.customeraddress = customeraddress;
        this.customercity = customercity;
        this.customerpostal = customerpostal;
        this.customeremail = customeremail;
        this.startAddress = startAddress;
        this.startPostal = startPostal;
        this.startCity = startCity;
        this.endAddress = endAddress;
        this.endCity = endCity;
        this.endPostal = endPostal;
        this.numMovers = numMovers;
        this.numBoxes = numBoxes;
    }

    public MovingRequest(){
    }

}
