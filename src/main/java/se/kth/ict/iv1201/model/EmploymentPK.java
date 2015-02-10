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
public class EmploymentPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "EmploymentID")
    private int employmentID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PersonID")
    private int personID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "JobID")
    private int jobID;

    /**
     *
     */
    public EmploymentPK() {
    }

    /**
     *
     * @param employmentID
     * @param personID
     * @param jobID
     */
    public EmploymentPK(int employmentID, int personID, int jobID) {
        this.employmentID = employmentID;
        this.personID = personID;
        this.jobID = jobID;
    }

    /**
     *
     * @return
     */
    public int getEmploymentID() {
        return employmentID;
    }

    /**
     *
     * @param employmentID
     */
    public void setEmploymentID(int employmentID) {
        this.employmentID = employmentID;
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
    public int getJobID() {
        return jobID;
    }

    /**
     *
     * @param jobID
     */
    public void setJobID(int jobID) {
        this.jobID = jobID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) employmentID;
        hash += (int) personID;
        hash += (int) jobID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmploymentPK)) {
            return false;
        }
        EmploymentPK other = (EmploymentPK) object;
        if (this.employmentID != other.employmentID) {
            return false;
        }
        if (this.personID != other.personID) {
            return false;
        }
        if (this.jobID != other.jobID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.model.EmploymentPK[ employmentID=" + employmentID + ", personID=" + personID + ", jobID=" + jobID + " ]";
    }
    
}
