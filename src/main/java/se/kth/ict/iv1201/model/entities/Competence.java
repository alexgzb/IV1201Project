/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.model.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author iv1201
 */
@Entity
@Table(name = "Competence")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Competence.findAll", query = "SELECT c FROM Competence c"),
    @NamedQuery(name = "Competence.findByCompetenceID", query = "SELECT c FROM Competence c WHERE c.competenceID = :competenceID")})
public class Competence implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "CompetenceID")
    private Integer competenceID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competenceID")
    private Collection<CompetenceTranslation> competenceTranslationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competenceID")
    private Collection<ApplicationCompetence> applicationCompetenceCollection;

    public Competence() {
    }

    public Competence(Integer competenceID) {
        this.competenceID = competenceID;
    }

    public Integer getCompetenceID() {
        return competenceID;
    }

    public void setCompetenceID(Integer competenceID) {
        this.competenceID = competenceID;
    }

    @XmlTransient
    public Collection<CompetenceTranslation> getCompetenceTranslationCollection() {
        return competenceTranslationCollection;
    }

    public void setCompetenceTranslationCollection(Collection<CompetenceTranslation> competenceTranslationCollection) {
        this.competenceTranslationCollection = competenceTranslationCollection;
    }

    @XmlTransient
    public Collection<ApplicationCompetence> getApplicationCompetenceCollection() {
        return applicationCompetenceCollection;
    }

    public void setApplicationCompetenceCollection(Collection<ApplicationCompetence> applicationCompetenceCollection) {
        this.applicationCompetenceCollection = applicationCompetenceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (competenceID != null ? competenceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Competence)) {
            return false;
        }
        Competence other = (Competence) object;
        if ((this.competenceID == null && other.competenceID != null) || (this.competenceID != null && !this.competenceID.equals(other.competenceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.temp.Competence[ competenceID=" + competenceID + " ]";
    }
    
}
