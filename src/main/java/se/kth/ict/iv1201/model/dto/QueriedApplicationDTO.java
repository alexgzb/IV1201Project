
package se.kth.ict.iv1201.model.dto;

import java.util.Date;

/**
 *
 * @author Christian Schreil
 */
public class QueriedApplicationDTO {
    
    private String firstname;
    private String lastname;
    private Date registrationDate;
    private boolean hired;
    private Date lastModified;

    public QueriedApplicationDTO() {
    }

    public QueriedApplicationDTO(String firstname, String lastname, Date registrationDate, boolean hired, Date lastModified) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.registrationDate = registrationDate;
        this.hired = hired;
        this.lastModified = lastModified;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isHired() {
        return hired;
    }

    public void setHired(boolean hired) {
        this.hired = hired;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getLastModified() {
        return lastModified;
    }
    
    
    
    
}
