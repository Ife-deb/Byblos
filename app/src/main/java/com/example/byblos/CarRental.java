package com.example.byblos;

public class CarRental extends Service{

    private Double hourlyRate;
    private String branch;

    public CarRental(String firstName, String lastName, String address, String email, String branch, Double hourlyRate) {
        super( firstName, lastName, address, email);
        this.hourlyRate = hourlyRate;
        this.branch = branch;
    }
}
