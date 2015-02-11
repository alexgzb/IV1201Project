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
@Table(name = "CompetenceTranslation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompetenceTranslation.findAll", query = "SELECT c FROM CompetenceTranslation c"),
    @NamedQuery(name = "CompetenceTranslation.findByCompetenceTranslationID", query = "SELECT c FROM CompetenceTranslation c WHERE c.competenceTranslationID = :competenceTranslationID"),
    @NamedQuery(name = "CompetenceTranslation.findByDescription", query = "SELECT c FROM CompetenceTranslation c WHERE c.description = :description")})
public class CompetenceTranslation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CompetenceTranslationID")
    private Integer competenceTranslationID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "CompetenceID", referencedColumnName = "CompetenceID")
    @ManyToOne(optional = false)
    private Competence competenceID;
    @JoinColumn(name = "LanguageCode", referencedColumnName = "LanguageCode")
    @ManyToOne(optional = false)
    private Language languageCode;

    public CompetenceTranslation() {
    }

    public CompetenceTranslation(Integer competenceTranslationID) {
        this.competenceTranslationID = competenceTranslationID;
    }

    public CompetenceTranslation(Integer competenceTranslationID, String description) {
        this.competenceTranslationID = competenceTranslationID;
        this.description = description;
    }

    public Integer getCompetenceTranslationID() {
        return competenceTranslationID;
    }

    public void setCompetenceTranslationID(Integer competenceTranslationID) {
        this.competenceTranslationID = competenceTranslationID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Competence getCompetenceID() {
        return competenceID;
    }

    public void setCompetenceID(Competence competenceID) {
        this.competenceID = competenceID;
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
        hash += (competenceTranslationID != null ? competenceTranslationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompetenceTranslation)) {
            return false;
        }
        CompetenceTranslation other = (CompetenceTranslation) object;
        if ((this.competenceTranslationID == null && other.competenceTranslationID != null) || (this.competenceTranslationID != null && !this.competenceTranslationID.equals(other.competenceTranslationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.temp.CompetenceTranslation[ competenceTranslationID=" + competenceTranslationID + " ]";
    }
    
}
