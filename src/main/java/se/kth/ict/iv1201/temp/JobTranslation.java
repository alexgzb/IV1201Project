/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.temp;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "JobTranslation.findByJobTranslationID", query = "SELECT j FROM JobTranslation j WHERE j.jobTranslationID = :jobTranslationID"),
    @NamedQuery(name = "JobTranslation.findByName", query = "SELECT j FROM JobTranslation j WHERE j.name = :name")})
public class JobTranslation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "JobTranslationID")
    private Integer jobTranslationID;
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
    @JoinColumn(name = "JobID", referencedColumnName = "JobID")
    @ManyToOne(optional = false)
    private Job jobID;
    @JoinColumn(name = "LanguageCode", referencedColumnName = "LanguageCode")
    @ManyToOne(optional = false)
    private Language languageCode;

    public JobTranslation() {
    }

    public JobTranslation(Integer jobTranslationID) {
        this.jobTranslationID = jobTranslationID;
    }

    public JobTranslation(Integer jobTranslationID, String name, String description) {
        this.jobTranslationID = jobTranslationID;
        this.name = name;
        this.description = description;
    }

    public Integer getJobTranslationID() {
        return jobTranslationID;
    }

    public void setJobTranslationID(Integer jobTranslationID) {
        this.jobTranslationID = jobTranslationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Job getJobID() {
        return jobID;
    }

    public void setJobID(Job jobID) {
        this.jobID = jobID;
    }

    public Language getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(Language languageCode) {
        this.languageCode = languageCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobTranslationID != null ? jobTranslationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobTranslation)) {
            return false;
        }
        JobTranslation other = (JobTranslation) object;
        if ((this.jobTranslationID == null && other.jobTranslationID != null) || (this.jobTranslationID != null && !this.jobTranslationID.equals(other.jobTranslationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.temp.JobTranslation[ jobTranslationID=" + jobTranslationID + " ]";
    }
    
}
