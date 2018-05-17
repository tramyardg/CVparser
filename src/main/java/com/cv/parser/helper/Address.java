package com.cv.parser.helper;

/**
 * Helper class for {@link} Applicant and {@link} ApplicantExperience
 * @author RAYMARTHINKPAD
 *
 */
public class Address {
    private String streetNo; // optional
    private String streetName; // optional
    private String appNo; // optional
    private String city; // you can use a list
    private String provinceState; // you can use a list
    private String postalCode; // you can use regular expression for each country but this is also optional
    private String country; // optional
    
    public Address() {
	this.streetNo = null;
	this.streetName = null;
	this.appNo = null;
	this.city = null;
	this.provinceState = null;
	this.postalCode = null;
	this.country = null; 
    }

    @Override
    public String toString() {
	return "Address [streetNo=" + streetNo + ", streetName=" + streetName + ", appNo=" + appNo + ", city=" + city
		+ ", provinceState=" + provinceState + ", postalCode=" + postalCode + ", country=" + country + "]";
    }
    
    /*
     * Address usually follows a universal format for both countries: 
     *  <streetNo> <streetName>
     * 	<city>, <province/state> <postalCode/zipCode>
     * 
     * Sometimes only city and province/state is present (most likely)
     * 
     * Example:
     *  Canada 
     *   Dollard-des-Ormeaux, QC H9B 1R7
     *  US
     *   San Antonio, TX
     *   San Manteo, CA
     * 
     * Support only USA and Canada address
     * list: need all Canadian provinces and territories
     * list: need all USA states 
     */
    
}
