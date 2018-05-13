package com.cv.parser.applicant;

public class JobApplicant {
    
    private int id;
    private String details;
    
    public JobApplicant(int id, String details) {
	this.id = id;
	this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
	return "JobApplicant [id=" + id + ", details=" + details + "]";
    }
}
