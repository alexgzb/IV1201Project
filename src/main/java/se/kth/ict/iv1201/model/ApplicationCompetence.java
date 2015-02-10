/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @NamedQuery(name = "ApplicationCompetence.findByApplicationCompetenceID", query = "SELECT a FROM ApplicationCompetence a WHERE a.applicationCompetencePK.applicationCompetenceID = :applicationCompetenceID"),
    @NamedQuery(name = "ApplicationCompetence.findByPersonID", query = "SELECT a FROM ApplicationCompetence a WHERE a.applicationCompetencePK.personID = :personID"),
    @NamedQuery(name = "ApplicationCompetence.findByYearsOFExperience", query = "SELECT a FROM ApplicationCompetence a WHERE a.yearsOFExperience = :yearsOFExperience")})
public class ApplicationCompetence implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @EmbeddedId
    protected ApplicationCompetencePK applicationCompetencePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "YearsOFExperience")
    private BigDecimal yearsOFExperience;
    @JoinColumn(name = "PersonID", referencedColumnName = "PersonID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Application application;
    @JoinColumn(name = "CompetenceID", referencedColumnName = "CompetenceID")
    @ManyToOne(optional = false)
    private Competence competenceID;

    /**
     *
     */
    public ApplicationCompetence() {
    }

    /**
     *
     * @param applicationCompetencePK
     */
    public ApplicationCompetence(ApplicationCompetencePK applicationCompetencePK) {
        this.applicationCompetencePK = applicationCompetencePK;
    }

    /**
     *
     * @param applicationCompetencePK
     * @param yearsOFExperience
     */
    public ApplicationCompetence(ApplicationCompetencePK applicationCompetencePK, BigDecimal yearsOFExperience) {
        this.applicationCompetencePK = applicationCompetencePK;
        this.yearsOFExperience = yearsOFExperience;
    }

    /**
     *
     * @param applicationCompetenceID
     * @param personID
     */
    public ApplicationCompetence(int applicationCompetenceID, int personID) {
        this.applicationCompetencePK = new ApplicationCompetencePK(applicationCompetenceID, personID);
    }

    /**
     *
     * @return
     */
    public ApplicationCompetencePK getApplicationCompetencePK() {
        return applicationCompetencePK;
    }

    /**
     *
     * @param applicationCompetencePK
     */
    public void setApplicationCompetencePK(ApplicationCompetencePK applicationCompetencePK) {
        this.applicationCompetencePK = applicationCompetencePK;
    }

    /**
     *
     * @return
     */
    public BigDecimal getYearsOFExperience() {
        return yearsOFExperience;
    }

    /**
     *
     * @param yearsOFExperience
     */
    public void setYearsOFExperience(BigDecimal yearsOFExperience) {
        this.yearsOFExperience = yearsOFExperience;
    }

    /**
     *
     * @return
     */
    public Application getApplication() {
        return application;
    }

    /**
     *
     * @param application
     */
    public void setApplication(Application application) {
        this.application = application;
    }

    /**
     *
     * @return
     */
    public Competence getCompetenceID() {
        return competenceID;
    }

    /**
     *
     * @param competenceID
     */
    public void setCompetenceID(Competence competenceID) {
        this.competenceID = competenceID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationCompetencePK != null ? applicationCompetencePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationCompetence)) {
            return false;
        }
        ApplicationCompetence other = (ApplicationCompetence) object;
        if ((this.applicationCompetencePK == null && other.applicationCompetencePK != null) || (this.applicationCompetencePK != null && !this.applicationCompetencePK.equals(other.applicationCompetencePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.model.ApplicationCompetence[ applicationCompetencePK=" + applicationCompetencePK + " ]";
    }
    
}
