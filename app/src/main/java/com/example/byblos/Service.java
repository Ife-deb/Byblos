package com.example.byblos;

public class Service {
    public String firstName, lastName, address, email;
    public boolean isRental;

    public Service(String firstName, String lastName, String address, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
    }

    //these are hooks for added functionality later but not necessary
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

}
