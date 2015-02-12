/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author iv1201
 */
@Entity
@Table(name = "ApplicationCompetence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApplicationCompetence.findAll", query = "SELECT a FROM ApplicationCompetence a"),
    @NamedQuery(name = "ApplicationCompetence.findByApplicationCompetenceID", query = "SELECT a FROM ApplicationCompetence a WHERE a.applicationCompetenceID = :applicationCompetenceID"),
    @NamedQuery(name = "ApplicationCompetence.findByYearsOFExperience", query = "SELECT a FROM ApplicationCompetence a WHERE a.yearsOFExperience = :yearsOFExperience")})
public class ApplicationCompetence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ApplicationCompetenceID")
    private Integer applicationCompetenceID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "YearsOFExperience")
    private BigDecimal yearsOFExperience;
    @JoinColumn(name = "PersonID", referencedColumnName = "PersonID")
    @ManyToOne(optional = false)
    private Application personID;
    @JoinColumn(name = "CompetenceID", referencedColumnName = "CompetenceID")
    @ManyToOne(optional = false)
    private Competence competenceID;

    public ApplicationCompetence() {
    }

    public ApplicationCompetence(Integer applicationCompetenceID) {
        this.applicationCompetenceID = applicationCompetenceID;
    }

    public ApplicationCompetence(Integer applicationCompetenceID, BigDecimal yearsOFExperience) {
        this.applicationCompetenceID = applicationCompetenceID;
        this.yearsOFExperience = yearsOFExperience;
    }

    public Integer getApplicationCompetenceID() {
        return applicationCompetenceID;
    }

    public void setApplicationCompetenceID(Integer applicationCompetenceID) {
        this.applicationCompetenceID = applicationCompetenceID;
    }

    public BigDecimal getYearsOFExperience() {
        return yearsOFExperience;
    }

    public void setYearsOFExperience(BigDecimal yearsOFExperience) {
        this.yearsOFExperience = yearsOFExperience;
    }

    public Application getPersonID() {
        return personID;
    }

    public void setPersonID(Application personID) {
        this.personID = personID;
    }

    public Competence getCompetenceID() {
        return competenceID;
    }

    public void setCompetenceID(Competence competenceID) {
        this.competenceID = competenceID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationCompetenceID != null ? applicationCompetenceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationCompetence)) {
            return false;
        }
        ApplicationCompetence other = (ApplicationCompetence) object;
        if ((this.applicationCompetenceID == null && other.applicationCompetenceID != null) || (this.applicationCompetenceID != null && !this.applicationCompetenceID.equals(other.applicationCompetenceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.temp.ApplicationCompetence[ applicationCompetenceID=" + applicationCompetenceID + " ]";
    }
    
}
