/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
    @NamedQuery(name = "Employment.findByEmploymentID", query = "SELECT e FROM Employment e WHERE e.employmentPK.employmentID = :employmentID"),
    @NamedQuery(name = "Employment.findByPersonID", query = "SELECT e FROM Employment e WHERE e.employmentPK.personID = :personID"),
    @NamedQuery(name = "Employment.findByJobID", query = "SELECT e FROM Employment e WHERE e.employmentPK.jobID = :jobID"),
    @NamedQuery(name = "Employment.findByFromDate", query = "SELECT e FROM Employment e WHERE e.fromDate = :fromDate"),
    @NamedQuery(name = "Employment.findByToDate", query = "SELECT e FROM Employment e WHERE e.toDate = :toDate")})
public class Employment implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @EmbeddedId
    protected EmploymentPK employmentPK;
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
    @JoinColumn(name = "PersonID", referencedColumnName = "PersonID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Person person;
    @JoinColumn(name = "JobID", referencedColumnName = "JobID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Job job;

    /**
     *
     */
    public Employment() {
    }

    /**
     *
     * @param employmentPK
     */
    public Employment(EmploymentPK employmentPK) {
        this.employmentPK = employmentPK;
    }

    /**
     *
     * @param employmentPK
     * @param fromDate
     * @param toDate
     */
    public Employment(EmploymentPK employmentPK, Date fromDate, Date toDate) {
        this.employmentPK = employmentPK;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    /**
     *
     * @param employmentID
     * @param personID
     * @param jobID
     */
    public Employment(int employmentID, int personID, int jobID) {
        this.employmentPK = new EmploymentPK(employmentID, personID, jobID);
    }

    /**
     *
     * @return
     */
    public EmploymentPK getEmploymentPK() {
        return employmentPK;
    }

    /**
     *
     * @param employmentPK
     */
    public void setEmploymentPK(EmploymentPK employmentPK) {
        this.employmentPK = employmentPK;
    }

    /**
     *
     * @return
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     *
     * @param fromDate
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     *
     * @return
     */
    public Date getToDate() {
        return toDate;
    }

    /**
     *
     * @param toDate
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    /**
     *
     * @return
     */
    public Person getPerson() {
        return person;
    }

    /**
     *
     * @param person
     */
    public void setPerson(Person person) {
        this.person = person;
    }

    /**
     *
     * @return
     */
    public Job getJob() {
        return job;
    }

    /**
     *
     * @param job
     */
    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (employmentPK != null ? employmentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employment)) {
            return false;
        }
        Employment other = (Employment) object;
        if ((this.employmentPK == null && other.employmentPK != null) || (this.employmentPK != null && !this.employmentPK.equals(other.employmentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.model.Employment[ employmentPK=" + employmentPK + " ]";
    }
    
}
