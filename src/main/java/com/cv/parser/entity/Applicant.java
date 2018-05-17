package com.cv.parser.entity;

import com.cv.parser.helper.Address;

public class Applicant {

    private String name;
    private Address address;
    private String email;
    private String phoneNumber;
    private String links;
    private String objective;

    public Applicant() {
	this.name = null;
	this.address = null;
	this.email = null;
	this.phoneNumber = null;
	this.links = null;
	this.objective = null;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Address getAddress() {
	return address;
    }

    public void setAddress(Address address) {
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
    
    public String getLinks() {
	return links;
    }
    
    public void setLinks(String links) {
	this.links = links;
    }
    
    public String getObjective() {
	return objective;
    }
    
    public void setObjective(String objective) {
	this.objective = objective;
    }

    @Override
    public String toString() {
	return "Applicant [name=" + name + ", address=" + address + ", email=" + email + ", phoneNumber=" + phoneNumber
		+ ", links=" + links + ", objective=" + objective + "]";
    }
    
}
