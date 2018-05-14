package com.cv.parser.entity;

public class Applicant {

    private String name;
    private String address;
    private String email;
    private String phoneNumber;

    public Applicant() {

    }

    public Applicant(String name, String address, String email, String phoneNumber) {
	this.name = name;
	this.address = address;
	this.email = email;
	this.phoneNumber = phoneNumber;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
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

    public String getPhoneNumber() {
	return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
	return "Applicant [name=" + name + ", address=" + address + ", email=" + email + ", phoneNumber=" + phoneNumber
		+ "]";
    }
}
