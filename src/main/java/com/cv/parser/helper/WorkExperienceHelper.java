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
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
	return "WorkExperienceHelper [position=" + position + ", company=" + company + ", address=" + address
		+ ", dateFrom=" + dateFrom + ", dateTo=" + dateTo + ", description=" + description + "]";
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
