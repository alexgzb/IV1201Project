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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author iv1201
 */
@Entity
@Table(name = "Employment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employment.findAll", query = "SELECT e FROM Employment e"),
    @NamedQuery(name = "Employment.findByEmploymentID", query = "SELECT e FROM Employment e WHERE e.employmentID = :employmentID"),
    @NamedQuery(name = "Employment.findByFromDate", query = "SELECT e FROM Employment e WHERE e.fromDate = :fromDate"),
    @NamedQuery(name = "Employment.findByToDate", query = "SELECT e FROM Employment e WHERE e.toDate = :toDate")})
public class Employment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EmploymentID")
    private Integer employmentID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FromDate")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ToDate")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    @JoinColumn(name = "PersonID", referencedColumnName = "PersonID")
    @ManyToOne(optional = false)
    private Person personID;
    @JoinColumn(name = "JobID", referencedColumnName = "JobID")
    @ManyToOne(optional = false)
    private Job jobID;

    public Employment() {
    }

    public Employment(Integer employmentID) {
        this.employmentID = employmentID;
    }

    public Employment(Integer employmentID, Date fromDate, Date toDate) {
        this.employmentID = employmentID;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Integer getEmploymentID() {
        return employmentID;
    }

    public void setEmploymentID(Integer employmentID) {
        this.employmentID = employmentID;
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

    public Person getPersonID() {
        return personID;
    }

    public void setPersonID(Person personID) {
        this.personID = personID;
    }

    public Job getJobID() {
        return jobID;
    }

    public void setJobID(Job jobID) {
        this.jobID = jobID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employmentID != null ? employmentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employment)) {
            return false;
        }
        Employment other = (Employment) object;
        if ((this.employmentID == null && other.employmentID != null) || (this.employmentID != null && !this.employmentID.equals(other.employmentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.temp.Employment[ employmentID=" + employmentID + " ]";
    }
    
}
