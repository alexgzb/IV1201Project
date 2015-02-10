/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author iv1201
 */
@Embeddable
public class ApplicationAvailabilityPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ApplicationAvailabilityID")
    private int applicationAvailabilityID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PersonID")
    private int personID;

    /**
     *
     */
    public ApplicationAvailabilityPK() {
    }

    /**
     *
     * @param applicationAvailabilityID
     * @param personID
     */
    public ApplicationAvailabilityPK(int applicationAvailabilityID, int personID) {
        this.applicationAvailabilityID = applicationAvailabilityID;
        this.personID = personID;
    }

    /**
     *
     * @return
     */
    public int getApplicationAvailabilityID() {
        return applicationAvailabilityID;
    }

    /**
     *
     * @param applicationAvailabilityID
     */
    public void setApplicationAvailabilityID(int applicationAvailabilityID) {
        this.applicationAvailabilityID = applicationAvailabilityID;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) applicationAvailabilityID;
        hash += (int) personID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationAvailabilityPK)) {
            return false;
        }
        ApplicationAvailabilityPK other = (ApplicationAvailabilityPK) object;
        if (this.applicationAvailabilityID != other.applicationAvailabilityID) {
            return false;
        }
        if (this.personID != other.personID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.model.ApplicationAvailabilityPK[ applicationAvailabilityID=" + applicationAvailabilityID + ", personID=" + personID + " ]";
    }
    
}
