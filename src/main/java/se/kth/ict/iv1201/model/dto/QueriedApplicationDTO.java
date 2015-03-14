
package se.kth.ict.iv1201.model.dto;

import java.util.ArrayList;
import java.util.Date;

/**
 * DTO that represents queried applications.
 * @author Christian Schreil
 */
public class QueriedApplicationDTO {
    
    private int personID;
    private String firstname;
    private String lastname;
    private Date registrationDate;
    private boolean hired;
    private Date lastModified;
    private ArrayList<QueriedApplicationAvailabilityDTO> availabilites;
    private ArrayList<QueriedApplicationCompetenceDTO> competencies;

    /**
     *
     */
    public QueriedApplicationDTO() {
    }

    /**
     *
     * @param personID
     * @param firstname
     * @param lastname
     * @param registrationDate
     * @param hired
     * @param lastModified
     */
    public QueriedApplicationDTO(int personID, String firstname, String lastname, Date registrationDate, boolean hired, Date lastModified) {
        this.personID = personID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.registrationDate = registrationDate;
        this.hired = hired;
        this.lastModified = lastModified;
    }

    /**
     *
     * @return
     */
    public int getPersonID() {
        return personID;
    }

    /**
     *
     * @param personID
     */
    public void setPersonID(int personID) {
        this.personID = personID;
    }

    
    
    /**
     *
     * @return
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     *
     * @return
     */
    public String getLastname() {
        return lastname;
    }

    /**
     *
     * @param firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     *
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     *
     * @return
     */
    public Date getRegistrationDate() {
        return registrationDate;
    }

    /**
     *
     * @param registrationDate
     */
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     *
     * @return
     */
    public boolean isHired() {
        return hired;
    }

    /**
     *
     * @param hired
     */
    public void setHired(boolean hired) {
        this.hired = hired;
    }

    /**
     *
     * @param lastModified
     */
    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    /**
     *
     * @return
     */
    public Date getLastModified() {
        return lastModified;
    }

    /**
     *
     * @return
     */
    public ArrayList<QueriedApplicationAvailabilityDTO> getAvailabilites() {
        return availabilites;
    }

    /**
     *
     * @param availabilites
     */
    public void setAvailabilites(ArrayList<QueriedApplicationAvailabilityDTO> availabilites) {
        this.availabilites = availabilites;
    }

    /**
     *
     * @param competencies
     */
    public void setCompetencies(ArrayList<QueriedApplicationCompetenceDTO> competencies) {
        this.competencies = competencies;
    }

    /**
     *
     * @return
     */
    public ArrayList<QueriedApplicationCompetenceDTO> getCompetencies() {
        return competencies;
    }
    
    
    
    
    
    
}
