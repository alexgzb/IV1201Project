package se.kth.ict.iv1201.model.dto;

/**
 * A DTO for saving a competence selected by the user in application xhtml.
 * @author Wilhelm
 */
public class CompsDTO {
    
private double experiance;
private CompetenceDTO competence;

    public CompsDTO(double experiance, CompetenceDTO competence) {
        this.experiance = experiance;
        this.competence = competence;
    }

    public double getExperiance() {
        return experiance;
    }

    public void setExperiance(double experiance) {
        this.experiance = experiance;
    }

    public CompetenceDTO getCompetence() {
        return competence;
    }

    public void setCompetence(CompetenceDTO competence) {
        this.competence = competence;
    }
    
    

}
