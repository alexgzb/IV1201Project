package se.kth.ict.iv1201.model.dto;

import java.util.Date;

/**
 * A DTO for storing a date period for adding to an application.
 * @author Wilhelm
 */
public class DatesDTO {
    
    private Date toDate;
    private Date fromDate;

    public DatesDTO(Date toDate, Date fromDate) {
        this.toDate = toDate;
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }
    
    
    
}
