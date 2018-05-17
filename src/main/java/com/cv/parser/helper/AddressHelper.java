package com.cv.parser.helper;

/**
 * Helper class for {@link} Applicant
 * @author RAYMARTHINKPAD
 *
 */
public class AddressHelper {
    private String streetNo;
    private String streetName;
    private String appNo;
    private String city;
    private String provinceState; // is the hook
    private String postalCode;
    private String country;
    
    public AddressHelper() {
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
     * Universal format: 
     * 	<city>, <province/state> <postalCode/zipCode>
     *  Canada: 
     *   Dollard-des-Ormeaux, QC H9B 1R7
     *  USA:
     *   San Antonio, TX
     *   San Manteo, CA
     * 
     * Support only USA and Canada address
     * need all Canadian provinces and territories
     * need all USA states 
     */
    
    
}
