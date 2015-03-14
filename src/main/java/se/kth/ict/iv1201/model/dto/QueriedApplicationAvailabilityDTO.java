
package se.kth.ict.iv1201.model.dto;

import java.util.Date;

/**
 * An instance of this object represents an availability among one or more availabilities in an application
 * @author Christian Schreil
 */
public class QueriedApplicationAvailabilityDTO {
    
    private int id;
    private Date fromDate;
    private Date toDate;

    /**
     * Default constructor
     * @param id Unique identifier
     * @param fromDate From date
     * @param toDate To date
     */
    public QueriedApplicationAvailabilityDTO(int id, Date fromDate, Date toDate) {
        this.id = id;
        this.fromDate = fromDate;
        this.toDate = toDate;
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
     * @param id
     */
    public void setId(int id) {
        this.id = id;
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
    public int getId() {
        return id;
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
     * @return
     */
    public Date getFromDate() {
        return fromDate;
    }
    
    
    
}
