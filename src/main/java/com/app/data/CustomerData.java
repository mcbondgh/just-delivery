package com.app.data;

import com.app.repository.CustomerRepository;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

public class CustomerData implements CustomerRepository {
    private int customerId;
    private String fullname, mobileNumber, address, email;
    private Byte[] profileImage;
    private Timestamp registrationDate;
    private String status;

    public CustomerData() {
    }

    public CustomerData(int customerId, String fullname, String mobileNumber, String address, String email, String status) {
        this.customerId = customerId;
        this.fullname = fullname;
        this.mobileNumber = mobileNumber;
        this.address = address;
        this.email = email;
        this.status = status;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte[] getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Byte[] profileImage) {
        this.profileImage = profileImage;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus() {return this.status;}

    //INTERFACE IMPLEMENTATION
    @Override
    public List<Object> getAllCustomers() {
        return Collections.emptyList();
    }

    @Override
    public String getCustomerById(int customerId) {
        return "";
    }
}
