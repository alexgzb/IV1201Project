package se.kth.ict.iv1201.model.dto;

/**
 * Contains all the needed data to identify and describe a competence, this is
 * used to pass that data around the system from the user to the database and
 * back.
 * 
 * @author Wilhelm
 */
public class CompetenceDTO {
    private String description;
    private int id;

    public CompetenceDTO(String description, int id) {
        this.description = description;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return description;
    }
    
}
