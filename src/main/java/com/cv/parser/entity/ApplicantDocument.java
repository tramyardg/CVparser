package com.cv.parser.entity;

public class ApplicantDocument {
    private int id;
    private String line;

    public ApplicantDocument(int id, String line) {
        this.id = id;
        this.line = line;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "ApplicantDocument [id=" + id + ", details=" + line + "]";
    }
}
