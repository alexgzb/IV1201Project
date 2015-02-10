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
import javax.validation.constraints.Size;

/**
 *
 * @author iv1201
 */
@Embeddable
public class PersonPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "PersonID")
    private int personID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Username")
    private String username;

    /**
     *
     */
    public PersonPK() {
    }

    /**
     *
     * @param personID
     * @param username
     */
    public PersonPK(int personID, String username) {
        this.personID = personID;
        this.username = username;
    }

    /**
     *
     * @return
     */
    public int getPersonID() {
        return personID;
    }

    /**
     *
     * @param personID
     */
    public void setPersonID(int personID) {
        this.personID = personID;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) personID;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonPK)) {
            return false;
        }
        PersonPK other = (PersonPK) object;
        if (this.personID != other.personID) {
            return false;
        }
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.model.PersonPK[ personID=" + personID + ", username=" + username + " ]";
    }
    
}
