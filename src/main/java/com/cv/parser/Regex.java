package com.cv.parser;

public enum Regex {
    LINK ("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)"), 
    EMAIL ("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+"), 
    PHONE ("\\(?([0-9]{3})\\)?[-. ]([0-9]{3})[-. ]?[-. ]?([0-9]{4})"), 
    OBJECTIVE ("\\b(?i)(objective|objectives|objectif)\\b");

    private final String name;

    private Regex(String name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return this.name;
    }
}