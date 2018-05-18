package com.cv.parser.helper;

/**
 * Helper class for {@link} ApplicantExperience
 * 
 * @author RAYMARTHINKPAD
 *
 */
public class WorkExperienceHelper {

    private String position;
    private String company;
    private String address;
    private String dateFrom;
    private String dateTo;
    private String description;
    
    public WorkExperienceHelper() {
	this.position = null;
	this.company = null;
	this.address = null;
	this.dateFrom = null;
	this.dateTo = null;
	this.description = null;
    }
    
    /*
     * 
     * In a resume, you may find work experiences in different formats as follows:
     * 
     * 1. 
     * <dateFrom> - <dateTo> <position> at <companyName>, <location>
     * <description>
     * 
     * 2. 
     * 	<companyName>
     *  <position>
     *  <location>  <dateFrom> - <dateTo>
     *  <description>
     * 
     * 3. 
     * <companyName>, <location>, <dateFrom> - <dateTo>
     * <position> <description> 
     * 
     * 4. 
     * <position> at <companyName>
     * <dateFrom> - <dateTo>
     * <description>
     * 
     */
    
}
