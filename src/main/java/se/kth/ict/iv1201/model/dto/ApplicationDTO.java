package se.kth.ict.iv1201.model.dto;

import java.math.BigDecimal;
import java.util.Date;
import se.kth.ict.iv1201.model.entities.Competence;

/**
 * A dto that contains all information for making an application for a user.   
 *
 * @author Wilhelm
 */
public class ApplicationDTO {
    
    private String username;
    private BigDecimal[] experiance;
    private Competence[] competence;
    private Date[] fromDate;
    private Date[] toDate;

    public ApplicationDTO(String username, BigDecimal[] experiance, Competence[] competence, Date[] fromDate, Date[] toDate) {
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

    public Competence[] getCompetence() {
        return competence;
    }

    public void setCompetence(Competence[] competence) {
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
