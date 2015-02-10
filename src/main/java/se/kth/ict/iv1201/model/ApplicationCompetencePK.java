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
public class ApplicationCompetencePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ApplicationCompetenceID")
    private int applicationCompetenceID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PersonID")
    private int personID;

    /**
     *
     */
    public ApplicationCompetencePK() {
    }

    /**
     *
     * @param applicationCompetenceID
     * @param personID
     */
    public ApplicationCompetencePK(int applicationCompetenceID, int personID) {
        this.applicationCompetenceID = applicationCompetenceID;
        this.personID = personID;
    }

    /**
     *
     * @return
     */
    public int getApplicationCompetenceID() {
        return applicationCompetenceID;
    }

    /**
     *
     * @param applicationCompetenceID
     */
    public void setApplicationCompetenceID(int applicationCompetenceID) {
        this.applicationCompetenceID = applicationCompetenceID;
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
        hash += (int) applicationCompetenceID;
        hash += (int) personID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationCompetencePK)) {
            return false;
        }
        ApplicationCompetencePK other = (ApplicationCompetencePK) object;
        if (this.applicationCompetenceID != other.applicationCompetenceID) {
            return false;
        }
        if (this.personID != other.personID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.model.ApplicationCompetencePK[ applicationCompetenceID=" + applicationCompetenceID + ", personID=" + personID + " ]";
    }
    
}
