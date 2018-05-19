package com.cv.parser;

public enum RegEx {
    LINK ("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)"), 
    EMAIL ("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+"), 
    PHONE ("\\(?([0-9]{3})\\)?[-. ]([0-9]{3})[-. ]?[-. ]?([0-9]{4})"), 
    OBJECTIVE ("\\b(Objective|Objectives|OBJECTIVE|OBJECTIVES)([^-!@#$%^&*()+.,?])\\b"),
    EDUCATION ("\\b(Education|Educations|EDUCATION|EDUCATIONS)\\b"),
    EXPERIENCE ("\\b(Experience(s?)|EXPERIENCE(S?))\\b"),
    SKILLS ("\\b(Skill(s?)|SKILL(S?)|Expertise(s?)|Skills\\s&?\\sExpertises)\\b"),
    LANGUAGE ("\\b(Language(s?)|LANGUAGE(S?))\\b"),
    INTEREST ("\\b(Interest(s?)|INTEREST(S?)|Activity|Activities|ACTIVITIES|ACTIVITY)\\b"),
    MEMBERSHIP ("\\b(Membership(s?)|MEMBERSHIP(S?))\\b"),
    ADDITIONAL ("\\b(Award(s?)|AWARD(S)|Honor(s?)|HONOR(S?)|Certification(s?)|CERTIFICATION(S?)|Accomplishment(s?)|ACCOMPLISHMENT(S?)|Project(s?)|PROJECT(S?))\\b"),
    DATEFROMTO ("([A-Za-z]+\\s)?([0-9]{4})\\s[-]\\s([A-Za-z]+\\s)?([0-9]{4})|([A-Za-z]*?\\sat\\s[A-Za-z]+)");
    
    // TODO
    // there is also SKILLS, LANGUAGES and
    // ADDITIONAL that contains (awards, honors, projects, courses, certification)
    
    private final String name;

    private RegEx(String name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return this.name;
    }
}