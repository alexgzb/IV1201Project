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
public class JobTranslationPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "JobTranslationID")
    private int jobTranslationID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "JobID")
    private int jobID;

    /**
     *
     */
    public JobTranslationPK() {
    }

    /**
     *
     * @param jobTranslationID
     * @param jobID
     */
    public JobTranslationPK(int jobTranslationID, int jobID) {
        this.jobTranslationID = jobTranslationID;
        this.jobID = jobID;
    }

    /**
     *
     * @return
     */
    public int getJobTranslationID() {
        return jobTranslationID;
    }

    /**
     *
     * @param jobTranslationID
     */
    public void setJobTranslationID(int jobTranslationID) {
        this.jobTranslationID = jobTranslationID;
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
        hash += (int) jobTranslationID;
        hash += (int) jobID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobTranslationPK)) {
            return false;
        }
        JobTranslationPK other = (JobTranslationPK) object;
        if (this.jobTranslationID != other.jobTranslationID) {
            return false;
        }
        if (this.jobID != other.jobID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.model.JobTranslationPK[ jobTranslationID=" + jobTranslationID + ", jobID=" + jobID + " ]";
    }
    
}
