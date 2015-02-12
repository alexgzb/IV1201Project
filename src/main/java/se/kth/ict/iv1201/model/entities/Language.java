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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author iv1201
 */
@Entity
@Table(name = "Language")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Language.findAll", query = "SELECT l FROM Language l"),
    @NamedQuery(name = "Language.findByLanguageCode", query = "SELECT l FROM Language l WHERE l.languageCode = :languageCode"),
    @NamedQuery(name = "Language.findByName", query = "SELECT l FROM Language l WHERE l.name = :name")})
public class Language implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "LanguageCode")
    private String languageCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageCode")
    private Collection<CompetenceTranslation> competenceTranslationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "languageCode")
    private Collection<JobTranslation> jobTranslationCollection;

    public Language() {
    }

    public Language(String languageCode) {
        this.languageCode = languageCode;
    }

    public Language(String languageCode, String name) {
        this.languageCode = languageCode;
        this.name = name;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public Collection<CompetenceTranslation> getCompetenceTranslationCollection() {
        return competenceTranslationCollection;
    }

    public void setCompetenceTranslationCollection(Collection<CompetenceTranslation> competenceTranslationCollection) {
        this.competenceTranslationCollection = competenceTranslationCollection;
    }

    @XmlTransient
    public Collection<JobTranslation> getJobTranslationCollection() {
        return jobTranslationCollection;
    }

    public void setJobTranslationCollection(Collection<JobTranslation> jobTranslationCollection) {
        this.jobTranslationCollection = jobTranslationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (languageCode != null ? languageCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Language)) {
            return false;
        }
        Language other = (Language) object;
        if ((this.languageCode == null && other.languageCode != null) || (this.languageCode != null && !this.languageCode.equals(other.languageCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.temp.Language[ languageCode=" + languageCode + " ]";
    }

}
