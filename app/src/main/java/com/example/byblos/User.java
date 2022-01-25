package com.example.byblos;


public class User {
    private String firstName, lastName, email, password, uid, employee;

    public User(String firstName, String lastName, String email, String password, String employee, String uid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.employee = employee;
        this.uid = uid;
    }


    //these are hooks for added functionality later but not necessary

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmployee() {
        return employee;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmployee(boolean isEmployee) {
        if (isEmployee){
            employee="yes";
        }
        else {
            employee="no";
        }
    }
}
