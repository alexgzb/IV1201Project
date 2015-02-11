/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.temp;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author iv1201
 */
@Entity
@Table(name = "Application")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Application.findAll", query = "SELECT a FROM Application a"),
    @NamedQuery(name = "Application.findByPersonID", query = "SELECT a FROM Application a WHERE a.personID = :personID")})
public class Application implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PersonID")
    private Integer personID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personID")
    private Collection<ApplicationAvailability> applicationAvailabilityCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personID")
    private Collection<ApplicationCompetence> applicationCompetenceCollection;
    @JoinColumn(name = "PersonID", referencedColumnName = "PersonID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person person;

    public Application() {
    }

    public Application(Integer personID) {
        this.personID = personID;
    }

    public Integer getPersonID() {
        return personID;
    }

    public void setPersonID(Integer personID) {
        this.personID = personID;
    }

    @XmlTransient
    public Collection<ApplicationAvailability> getApplicationAvailabilityCollection() {
        return applicationAvailabilityCollection;
    }

    public void setApplicationAvailabilityCollection(Collection<ApplicationAvailability> applicationAvailabilityCollection) {
        this.applicationAvailabilityCollection = applicationAvailabilityCollection;
    }

    @XmlTransient
    public Collection<ApplicationCompetence> getApplicationCompetenceCollection() {
        return applicationCompetenceCollection;
    }

    public void setApplicationCompetenceCollection(Collection<ApplicationCompetence> applicationCompetenceCollection) {
        this.applicationCompetenceCollection = applicationCompetenceCollection;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personID != null ? personID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Application)) {
            return false;
        }
        Application other = (Application) object;
        if ((this.personID == null && other.personID != null) || (this.personID != null && !this.personID.equals(other.personID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.temp.Application[ personID=" + personID + " ]";
    }
    
}
