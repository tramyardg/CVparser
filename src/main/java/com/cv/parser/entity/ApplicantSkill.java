package com.cv.parser.entity;

public class ApplicantSkill {

    public ApplicantSkill() {

    }

    private int id;
    private String skills;

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getSkills() {
	return skills;
    }

    public void setSkills(String skills) {
	this.skills = skills;
    }

    @Override
    public String toString() {
	return "ApplicantSkill [id=" + id + ", skills=" + skills + "]";
    }

}
