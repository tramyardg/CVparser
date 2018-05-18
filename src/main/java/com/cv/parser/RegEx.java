package com.cv.parser;

public enum RegEx {
    LINK ("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)"), 
    EMAIL ("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+"), 
    PHONE ("\\(?([0-9]{3})\\)?[-. ]([0-9]{3})[-. ]?[-. ]?([0-9]{4})"), 
    OBJECTIVE ("\\b(Objective|Objectives|OBJECTIVE|OBJECTIVES)([^-!@#$%^&*()+.,?])\\b"),
    EDUCATION ("\\b(Education|Educations|EDUCATION|EDUCATIONS)\\b"),
    EXPERIENCE ("\\b(Experience|Experiences|EXPERIENCE|EXPERIENCES)\\b");
    
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