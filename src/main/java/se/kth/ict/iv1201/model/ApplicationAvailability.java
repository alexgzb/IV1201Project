/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.model;

import java.io.Serializable;
import java.util.Date;
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
    @NamedQuery(name = "ApplicationAvailability.findByApplicationAvailabilityID", query = "SELECT a FROM ApplicationAvailability a WHERE a.applicationAvailabilityPK.applicationAvailabilityID = :applicationAvailabilityID"),
    @NamedQuery(name = "ApplicationAvailability.findByFromDate", query = "SELECT a FROM ApplicationAvailability a WHERE a.fromDate = :fromDate"),
    @NamedQuery(name = "ApplicationAvailability.findByToDate", query = "SELECT a FROM ApplicationAvailability a WHERE a.toDate = :toDate"),
    @NamedQuery(name = "ApplicationAvailability.findByPersonID", query = "SELECT a FROM ApplicationAvailability a WHERE a.applicationAvailabilityPK.personID = :personID")})
public class ApplicationAvailability implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @EmbeddedId
    protected ApplicationAvailabilityPK applicationAvailabilityPK;
    @Column(name = "FromDate")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Column(name = "ToDate")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    @JoinColumn(name = "PersonID", referencedColumnName = "PersonID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Application application;

    /**
     *
     */
    public ApplicationAvailability() {
    }

    /**
     *
     * @param applicationAvailabilityPK
     */
    public ApplicationAvailability(ApplicationAvailabilityPK applicationAvailabilityPK) {
        this.applicationAvailabilityPK = applicationAvailabilityPK;
    }

    /**
     *
     * @param applicationAvailabilityID
     * @param personID
     */
    public ApplicationAvailability(int applicationAvailabilityID, int personID) {
        this.applicationAvailabilityPK = new ApplicationAvailabilityPK(applicationAvailabilityID, personID);
    }

    /**
     *
     * @return
     */
    public ApplicationAvailabilityPK getApplicationAvailabilityPK() {
        return applicationAvailabilityPK;
    }

    /**
     *
     * @param applicationAvailabilityPK
     */
    public void setApplicationAvailabilityPK(ApplicationAvailabilityPK applicationAvailabilityPK) {
        this.applicationAvailabilityPK = applicationAvailabilityPK;
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
    public Application getApplication() {
        return application;
    }

    /**
     *
     * @param application
     */
    public void setApplication(Application application) {
        this.application = application;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (applicationAvailabilityPK != null ? applicationAvailabilityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationAvailability)) {
            return false;
        }
        ApplicationAvailability other = (ApplicationAvailability) object;
        if ((this.applicationAvailabilityPK == null && other.applicationAvailabilityPK != null) || (this.applicationAvailabilityPK != null && !this.applicationAvailabilityPK.equals(other.applicationAvailabilityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.ict.iv1201.model.ApplicationAvailability[ applicationAvailabilityPK=" + applicationAvailabilityPK + " ]";
    }
    
}
