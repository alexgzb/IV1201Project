/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.model.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author iv1201
 */
@Entity
@Table(name = "ApplicationAvailability")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ApplicationAvailability.findAll", query = "SELECT a FROM ApplicationAvailability a"),
    @NamedQuery(name = "ApplicationAvailability.findByApplicationAvailabilityID", query = "SELECT a FROM ApplicationAvailability a WHERE a.applicationAvailabilityID = :applicationAvailabilityID"),
    @NamedQuery(name = "ApplicationAvailability.findByFromDate", query = "SELECT a FROM ApplicationAvailability a WHERE a.fromDate = :fromDate"),
    @NamedQuery(name = "ApplicationAvailability.findByToDate", query = "SELECT a FROM ApplicationAvailability a WHERE a.toDate = :toDate")})
public class ApplicationAvailability implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "ApplicationAvailabilityID")
    private Integer applicationAvailabilityID;
    @Column(name = "FromDate")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Column(name = "ToDate")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    @JoinColumn(name = "PersonID", referencedColumnName = "PersonID")
    @ManyToOne(optional = false)
    private Application personID;

    public ApplicationAvailability() {
    }

    public Integer getApplicationAvailabilityID() {
        return applicationAvailabilityID;
    }

    public void setApplicationAvailabilityID(Integer applicationAvailabilityID) {
        this.applicationAvailabilityID = applicationAvailabilityID;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Application getPersonID() {
        return personID;
    }

    public void setPersonID(Application personID) {
        this.personID = personID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationAvailabilityID != null ? applicationAvailabilityID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationAvailability)) {
            return false;
        }
        ApplicationAvailability other = (ApplicationAvailability) object;
        if ((this.applicationAvailabilityID == null && other.applicationAvailabilityID != null) || (this.applicationAvailabilityID != null && !this.applicationAvailabilityID.equals(other.applicationAvailabilityID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.temp.ApplicationAvailability[ applicationAvailabilityID=" + applicationAvailabilityID + " ]";
    }

}
