
package se.kth.ict.iv1201.model.dto;

import java.math.BigDecimal;

/**
 * An instance of this object represents a competence among one or more competences in an application
 * @author Christian Schreil
 */
public class QueriedApplicationCompetenceDTO {
    
    private int id;
    private String description;
    private BigDecimal yearsOfExperience;

    /**
     * 
     * @param id Unique identifier
     * @param description Description
     * @param yearsOfExperience How many years of experience the applicant has for this specific competence
     */
    public QueriedApplicationCompetenceDTO(int id, String description, BigDecimal yearsOfExperience) {
        this.id = id;
        this.description = description;
        this.yearsOfExperience = yearsOfExperience;
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
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @return 
     */
    public BigDecimal getYearsOfExperience() {
        return yearsOfExperience;
    }

    /**
     * 
     * @param yearsOfExperience 
     */
    public void setYearsOfExperience(BigDecimal yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
    
    
    
}
