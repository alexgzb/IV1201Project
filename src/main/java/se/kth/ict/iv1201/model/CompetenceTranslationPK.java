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
public class CompetenceTranslationPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CompetenceTranslationID")
    private int competenceTranslationID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CompetenceID")
    private int competenceID;

    /**
     *
     */
    public CompetenceTranslationPK() {
    }

    /**
     *
     * @param competenceTranslationID
     * @param competenceID
     */
    public CompetenceTranslationPK(int competenceTranslationID, int competenceID) {
        this.competenceTranslationID = competenceTranslationID;
        this.competenceID = competenceID;
    }

    /**
     *
     * @return
     */
    public int getCompetenceTranslationID() {
        return competenceTranslationID;
    }

    /**
     *
     * @param competenceTranslationID
     */
    public void setCompetenceTranslationID(int competenceTranslationID) {
        this.competenceTranslationID = competenceTranslationID;
    }

    /**
     *
     * @return
     */
    public int getCompetenceID() {
        return competenceID;
    }

    /**
     *
     * @param competenceID
     */
    public void setCompetenceID(int competenceID) {
        this.competenceID = competenceID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) competenceTranslationID;
        hash += (int) competenceID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompetenceTranslationPK)) {
            return false;
        }
        CompetenceTranslationPK other = (CompetenceTranslationPK) object;
        if (this.competenceTranslationID != other.competenceTranslationID) {
            return false;
        }
        if (this.competenceID != other.competenceID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.model.CompetenceTranslationPK[ competenceTranslationID=" + competenceTranslationID + ", competenceID=" + competenceID + " ]";
    }
    
}
