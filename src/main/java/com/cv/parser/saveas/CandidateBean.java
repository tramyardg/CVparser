package com.cv.parser.saveas;

public class CandidateBean {

    private String profile;
    private String education;
    private String experiences;
    private String skills;

    String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    String getExperiences() {
        return experiences;
    }

    public void setExperiences(String experiences) {
        this.experiences = experiences;
    }

    String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "CandidateBean [profile=" + profile + ", education=" + education + ", experiences=" + experiences
                + ", skills=" + skills + "]";
    }

}
