package com.cv.parser.entity;


public class Applicant {

    private int id;
    private String profile;
    private String email;
    private String phoneNumber;
    private String links;
    private String objective;

    public Applicant() {
	this.id = 0;
	this.profile = null;
	this.email = null;
	this.phoneNumber = null;
	this.links = null;
	this.objective = null;
    }

    public int getId() {
	return id;
    }
    
    public void setId(int id) {
	this.id = id;
    }
    
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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
	return "Applicant [id=" + id + ", profile=" + profile + ", email=" + email + ", phoneNumber=" + phoneNumber
		+ ", links=" + links + ", objective=" + objective + "]";
    }
}
