package se.kth.ict.iv1201.model.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * A dto that contains all information for making an application for a user, is
 * used to pass that data around the system.
 *
 * @author Wilhelm
 */
public class ApplicationDTO {
    
    private String username;
    private BigDecimal[] experiance;
    private int[] competence;
    private Date[] fromDate;
    private Date[] toDate;

    public ApplicationDTO(String username, BigDecimal[] experiance, int[] competence, Date[] fromDate, Date[] toDate) {
        this.username = username;
        this.experiance = experiance;
        this.competence = competence;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal[] getExperiance() {
        return experiance;
    }

    public void setExperiance(BigDecimal[] experiance) {
        this.experiance = experiance;
    }

    public int[] getCompetence() {
        return competence;
    }

    public void setCompetence(int[] competence) {
        this.competence = competence;
    }

    public Date[] getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date[] fromDate) {
        this.fromDate = fromDate;
    }

    public Date[] getToDate() {
        return toDate;
    }

    public void setToDate(Date[] toDate) {
        this.toDate = toDate;
    }
    
}
