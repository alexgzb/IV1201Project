
package se.kth.ict.iv1201.view;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import se.kth.ict.iv1201.controller.JobController;
import se.kth.ict.iv1201.model.dto.CompetenceDTO;
import se.kth.ict.iv1201.model.dto.QueriedApplicationDTO;

/**
 * A managed bean that provides an extension in the presentation layer for recruiters wanting to query job related data.
 * Such transactions can be related to retrieving a list of available jobs, adding new jobs and registering applicants as
 * employees.
 * @author Christian Schreil
 */

@Named("jobView")
@SessionScoped
public class JobView implements Serializable {
    
    @Inject
    private JobController controller;
    
    private ArrayList<CompetenceDTO> competences;
    private ArrayList<String> competencesString;
    private String[] selectedCompetences;
    private ArrayList<QueriedApplicationDTO> applications;
    private String nameSearch = "";
    private Date startDate;
    private Date endDate;
    
    /**
     * Retrieves available competences from the database on page load. Triggered by
     * f:event in the JSF-file.
     * @param languageCode Code of the language the page has been loaded in.
     */
    public void loadCompetences(String languageCode){
        competences = controller.getCompetences(languageCode);
        competencesString = new ArrayList();
        
        for(CompetenceDTO c : competences){
            competencesString.add(c.getDescription());
        }

    }
    
    /**
     * Retrieves fetched competences to the webpage
     * @return A list of competences
     */
    public ArrayList<String> getCompetences(){
        return competencesString;
    }
    
    /**
     * Retrieves what competences the user has selected in the view.
     * @return A list of selected competences
     */
    public String[] getSelectedCompetences(){
        return selectedCompetences;
    }
    
    /**
     * Set selected competences
     * @param selectedCompetences 
     */
    public void setSelectedCompetences(String[] selectedCompetences){
        this.selectedCompetences = selectedCompetences;
    }
    
    public void loadQueriedApplications(){

        applications = controller.getQueriedApplications(selectedCompetences,nameSearch);

    }
    
    public ArrayList<QueriedApplicationDTO> getQueriedApplications(){
        return applications;
    }

    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    }

    
    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    public String getNameSearch() {
        return nameSearch;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
    
    
    
    
}
