package com.cv.parser.saveas;

public class CandidateBean {

    private String profile;
    private String education;
    private String experiences;
    private String skills;

    public String getProfile() {
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

    public String getExperiences() {
        return experiences;
    }

    public void setExperiences(String experiences) {
        this.experiences = experiences;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "CandidateBean [\nprofile=" + profile
                + ", \neducation=" + education
                + ", \nexperiences=" + experiences
                + ", \nskills=" + skills +
                "]";
    }

}
