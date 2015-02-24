package se.kth.ict.iv1201.model.dto;

/**
 * Contains all necessary data for the user when adding competences to their
 * application, this dto is used to get that data from the database to the
 * view.
 * 
 * @author iv1201
 */
public class CompetencesDTO {
    private String[] description;
    private int[] competenceID;

    public CompetencesDTO(String[] description, int[] competenceID) {
        this.description = description;
        this.competenceID = competenceID;
    }

    public String[] getDescription() {
        return description;
    }

    public void setDescription(String[] description) {
        this.description = description;
    }

    public int[] getCompetenceID() {
        return competenceID;
    }

    public void setCompetenceID(int[] competenceID) {
        this.competenceID = competenceID;
    }
    
}
