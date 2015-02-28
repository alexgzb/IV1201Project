
package se.kth.ict.iv1201.model.dto;

import java.util.Date;

/**
 * A DTO that represents one full set of data related to a job item. Much like the usage of a DTO implies,
 * an instance of this object provides an interface to one or more entities that can be used for internal
 * transactions in the application.
 * @author Christian Schreil
 */
public class JobDTO {
    
    private Integer jobID;
    private String name;
    private String description;
    private Date fromDate;
    private Date toDate;

    /**
     * Empty constructor for the DTO.
     */
    public JobDTO() {
    }

    /**
     * Constructor with parameters for the DTO.
     * @param jobID An <code>Integer</code> containing the key identifier for the job
     * @param name The name of the job
     * @param description A description of the job
     * @param fromDate Period start date of the job
     * @param toDate Period end date of the job
     */
    public JobDTO(Integer jobID, String name, String description, Date fromDate, Date toDate) {
        this.jobID = jobID;
        this.name = name;
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
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
     * @return
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     *
     * @return
     */
    public Integer getJobID() {
        return jobID;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
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
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @param jobID
     */
    public void setJobID(Integer jobID) {
        this.jobID = jobID;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param toDate
     */
    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return jobID + ", " + name + ", " + description + ", " + fromDate.toString() + ", " + toDate.toString();
    }
    
    
    
}
