/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author iv1201
 */
@Entity
@Table(name = "JobTranslation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobTranslation.findAll", query = "SELECT j FROM JobTranslation j"),
    @NamedQuery(name = "JobTranslation.findByJobTranslationID", query = "SELECT j FROM JobTranslation j WHERE j.jobTranslationPK.jobTranslationID = :jobTranslationID"),
    @NamedQuery(name = "JobTranslation.findByName", query = "SELECT j FROM JobTranslation j WHERE j.name = :name"),
    @NamedQuery(name = "JobTranslation.findByJobID", query = "SELECT j FROM JobTranslation j WHERE j.jobTranslationPK.jobID = :jobID")})
public class JobTranslation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @EmbeddedId
    protected JobTranslationPK jobTranslationPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "JobID", referencedColumnName = "JobID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Job job;
    @JoinColumn(name = "LanguageCode", referencedColumnName = "LanguageCode")
    @ManyToOne(optional = false)
    private Language languageCode;

    /**
     *
     */
    public JobTranslation() {
    }

    /**
     *
     * @param jobTranslationPK
     */
    public JobTranslation(JobTranslationPK jobTranslationPK) {
        this.jobTranslationPK = jobTranslationPK;
    }

    /**
     *
     * @param jobTranslationPK
     * @param name
     * @param description
     */
    public JobTranslation(JobTranslationPK jobTranslationPK, String name, String description) {
        this.jobTranslationPK = jobTranslationPK;
        this.name = name;
        this.description = description;
    }

    /**
     *
     * @param jobTranslationID
     * @param jobID
     */
    public JobTranslation(int jobTranslationID, int jobID) {
        this.jobTranslationPK = new JobTranslationPK(jobTranslationID, jobID);
    }

    /**
     *
     * @return
     */
    public JobTranslationPK getJobTranslationPK() {
        return jobTranslationPK;
    }

    /**
     *
     * @param jobTranslationPK
     */
    public void setJobTranslationPK(JobTranslationPK jobTranslationPK) {
        this.jobTranslationPK = jobTranslationPK;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public Job getJob() {
        return job;
    }

    /**
     *
     * @param job
     */
    public void setJob(Job job) {
        this.job = job;
    }

    /**
     *
     * @return
     */
    public Language getLanguageCode() {
        return languageCode;
    }

    /**
     *
     * @param languageCode
     */
    public void setLanguageCode(Language languageCode) {
        this.languageCode = languageCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobTranslationPK != null ? jobTranslationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobTranslation)) {
            return false;
        }
        JobTranslation other = (JobTranslation) object;
        if ((this.jobTranslationPK == null && other.jobTranslationPK != null) || (this.jobTranslationPK != null && !this.jobTranslationPK.equals(other.jobTranslationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.model.JobTranslation[ jobTranslationPK=" + jobTranslationPK + " ]";
    }
    
}
