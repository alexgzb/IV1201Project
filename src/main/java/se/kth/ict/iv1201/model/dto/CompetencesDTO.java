package se.kth.ict.iv1201.model.dto;

import se.kth.ict.iv1201.model.entities.Competence;

/**
 * Contains all necessary data for the user when adding competences to their
 * application, this dto is used to get that data from the database to the
 * view.
 * 
 * @author iv1201
 */
public class CompetencesDTO {
    private String[] description;
    private Competence[] competenceID;

    public CompetencesDTO(String[] description, Competence[] competenceID) {
        this.description = description;
        this.competenceID = competenceID;
    }

    public String[] getDescription() {
        return description;
    }

    public void setDescription(String[] description) {
        this.description = description;
    }

    public Competence[] getCompetenceID() {
        return competenceID;
    }

    public void setCompetenceID(Competence[] competenceID) {
        this.competenceID = competenceID;
    }
    
}
