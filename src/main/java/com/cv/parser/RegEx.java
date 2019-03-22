package com.cv.parser;

public enum RegEx {
    LINK("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)"),
    EMAIL("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+"),
    PHONE("\\(?([0-9]{3})\\)?[-. ]([0-9]{3})[-. ]?[-. ]?([0-9]{4})"),
    OBJECTIVE("\\b(Objective(s?)|OBJECTIVE(S?)|Summary|SUMMARY|Statement)([^-!@#$%^&*()+.,?])\\b"), // summary included here
    EDUCATION("\\b(Education(s?)|EDUCATION(S?)|Diploma(s?)|DIPLOMA(S?))\\b"),
    EXPERIENCE("\\b(Experience(s?)|EXPERIENCE(S?)|Work Experience|WORK EXPERIENCE|Work History|WORK HISTORY)\\b"),
    SKILLS("\\b(Skill(s?) & Expertise(s?)|Tool(s?) & Technolog(y?|ies?)|Skill(s?)|SKILL(S?)|Technical Skills|TECHNICAL SKILLS|Computer Skills|COMPUTER SKILLS)\\b"),
    LANGUAGE("\\b(Language(s?)|LANGUAGE(S?))\\b"),
    INTEREST("\\b(Interest(s?)|INTEREST(S?)|Activity|Activities|ACTIVITY|ACTIVITIES)\\b"),
    MEMBERSHIP("\\b(Membership(s?)|MEMBERSHIP(S?))\\b"),
    ADDITIONAL("\\b(Award(s?)|AWARD(S)|Honor(s?)|HONOR(S?)|Certification(s?)|CERTIFICATION(S?)|Accomplishment(s?)|ACCOMPLISHMENT(S?)|Project(s?)|PROJECT(S?))\\b"),
    DATEFROMTO("([A-Za-z]+\\s)?([0-9]{4})\\s[-]\\s\\b((P|p)resent|(C|c)urrent)\\b|([A-Za-z]+\\s)?([0-9]{4})");

    /**
     * Note:
     * - if you have a combination of words make sure you put them in the beginning of the list
     */

    private final String name;

    RegEx(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}