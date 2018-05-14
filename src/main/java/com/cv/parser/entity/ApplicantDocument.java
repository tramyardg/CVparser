package com.cv.parser.entity;

public class ApplicantDocument {
    private int id;
    private String document;

    public ApplicantDocument(int id, String details) {
	this.id = id;
	this.document = details;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getDetails() {
	return document;
    }

    public void setDetails(String details) {
	this.document = details;
    }

    @Override
    public String toString() {
	return "ApplicantDocument [id=" + id + ", details=" + document + "]";
    }
}
