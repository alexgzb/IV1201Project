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
    @NamedQuery(name = "CompetenceTranslation.findByCompetenceTranslationID", query = "SELECT c FROM CompetenceTranslation c WHERE c.competenceTranslationPK.competenceTranslationID = :competenceTranslationID"),
    @NamedQuery(name = "CompetenceTranslation.findByDescription", query = "SELECT c FROM CompetenceTranslation c WHERE c.description = :description"),
    @NamedQuery(name = "CompetenceTranslation.findByCompetenceID", query = "SELECT c FROM CompetenceTranslation c WHERE c.competenceTranslationPK.competenceID = :competenceID")})
public class CompetenceTranslation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @EmbeddedId
    protected CompetenceTranslationPK competenceTranslationPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Description")
    private String description;
    @JoinColumn(name = "CompetenceID", referencedColumnName = "CompetenceID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Competence competence;
    @JoinColumn(name = "LanguageCode", referencedColumnName = "LanguageCode")
    @ManyToOne(optional = false)
    private Language languageCode;

    /**
     *
     */
    public CompetenceTranslation() {
    }

    /**
     *
     * @param competenceTranslationPK
     */
    public CompetenceTranslation(CompetenceTranslationPK competenceTranslationPK) {
        this.competenceTranslationPK = competenceTranslationPK;
    }

    /**
     *
     * @param competenceTranslationPK
     * @param description
     */
    public CompetenceTranslation(CompetenceTranslationPK competenceTranslationPK, String description) {
        this.competenceTranslationPK = competenceTranslationPK;
        this.description = description;
    }

    /**
     *
     * @param competenceTranslationID
     * @param competenceID
     */
    public CompetenceTranslation(int competenceTranslationID, int competenceID) {
        this.competenceTranslationPK = new CompetenceTranslationPK(competenceTranslationID, competenceID);
    }

    /**
     *
     * @return
     */
    public CompetenceTranslationPK getCompetenceTranslationPK() {
        return competenceTranslationPK;
    }

    /**
     *
     * @param competenceTranslationPK
     */
    public void setCompetenceTranslationPK(CompetenceTranslationPK competenceTranslationPK) {
        this.competenceTranslationPK = competenceTranslationPK;
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
    public Competence getCompetence() {
        return competence;
    }

    /**
     *
     * @param competence
     */
    public void setCompetence(Competence competence) {
        this.competence = competence;
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
        hash += (competenceTranslationPK != null ? competenceTranslationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompetenceTranslation)) {
            return false;
        }
        CompetenceTranslation other = (CompetenceTranslation) object;
        if ((this.competenceTranslationPK == null && other.competenceTranslationPK != null) || (this.competenceTranslationPK != null && !this.competenceTranslationPK.equals(other.competenceTranslationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.model.CompetenceTranslation[ competenceTranslationPK=" + competenceTranslationPK + " ]";
    }
    
}
