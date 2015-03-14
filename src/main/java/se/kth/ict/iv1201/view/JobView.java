
package se.kth.ict.iv1201.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import se.kth.ict.iv1201.controller.JobController;
import se.kth.ict.iv1201.model.dto.CompetenceDTO;
import se.kth.ict.iv1201.model.dto.QueriedApplicationDTO;

/**
 * A managed bean that provides an extension in the presentation layer for recruiters wanting to query job related data.
 * Such transactions can be related to retrieving a list of available jobs, registering applicants as
 * employees and relieving current employees of their contract.
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
    private QueriedApplicationDTO selectedApplication;
    
    private String errorMessageHeader;
    private String errorMessageBody;
    private String notOKMessageHeader;
    private String notOKMessageBody;
    private String okMessageHeader;
    private String okMessageBody;
    private boolean value;
    
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
     * Retrieves fetched competences to the web page
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
     * Set selected competencies
     * @param selectedCompetences An array that contains all selected competencies (by name)
     */
    public void setSelectedCompetences(String[] selectedCompetences){
        this.selectedCompetences = selectedCompetences;
    }
    
    /**
     * Upon having set search parameters (like selected competencies and date interval),
     * applications that match these constraints are loaded into a local variable.
     * @param languageCode Language code
     * @param dateIntervalErrorHeader
     * @param dateIntervalErrorStartPrecedesEnd
     * @param dateIntervalErrorNotComplete
     */
    public void loadQueriedApplications(
            String languageCode,
            String dateIntervalErrorHeader,
            String dateIntervalErrorStartPrecedesEnd,
            String dateIntervalErrorNotComplete){

        if((startDate == null && endDate != null) || (startDate != null && endDate == null)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, dateIntervalErrorHeader, dateIntervalErrorNotComplete));
        } else {
           if((startDate != null && endDate != null) && startDate.after(endDate)){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, dateIntervalErrorHeader, dateIntervalErrorStartPrecedesEnd));
            } else {
               applications = null;
               applications = controller.getQueriedApplications(languageCode,selectedCompetences,nameSearch,startDate,endDate); 
            } 
        } 
    }
    
    /**
     * Retrieve information about the selected application to the web page
     * @return A <code>QueriedApplicationDTO</code> object that represents the application
     */
    public QueriedApplicationDTO getSelectedApplication(){
        return selectedApplication;
    }
    
    /**
     * Defines what application that has been selected in the browser
     * @param selectedApplication A reference to the application that currently is selected
     */
    public void setSelectedApplication(QueriedApplicationDTO selectedApplication){
        this.selectedApplication = selectedApplication;
    }
    
    /**
     * Method to relieve an employee of their current working contract.
     * @param errorMessageHeader Message header of the error displayed if the user already has been relieved of their contract
     * @param errorMessageBody Message body of the error displayed if the user already has been relieved of their contract
     * @param notOKMessageHeader Message header of the error displayed if the update doesn't succeed
     * @param notOKMessageBody Message body of the error displayed if the update doesn't succeed
     * @param okMessageHeader Message header of the information displayed if the update succeeds
     * @param okMessageBody Message body of the information displayed if the update succeeds
     */
    public void fireApplicant(
            String errorMessageHeader,
            String errorMessageBody,
            String notOKMessageHeader,
            String notOKMessageBody,
            String okMessageHeader,
            String okMessageBody){
        
        this.errorMessageHeader = errorMessageHeader;
        this.errorMessageBody = errorMessageBody;
        this.notOKMessageHeader = notOKMessageHeader;
        this.notOKMessageBody = notOKMessageBody;
        this.okMessageHeader = okMessageHeader;
        this.okMessageBody = okMessageBody;
        this.value = false;

        checkIfModified();
    }
    
    /**
     * Checks if applicant has been modified since last fetch
     */
    private void checkIfModified(){
        if(!controller.isModified(this.selectedApplication.getPersonID(), this.selectedApplication.getLastModified())){
                updateApplication();
        } else {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('confirmation').show();");
        }
    }
    
    /**
     * Method to employ an applicant.
     * @param errorMessageHeader Message header of the error displayed if the user already has been relieved of their contract
     * @param errorMessageBody Message body of the error displayed if the user already has been relieved of their contract
     * @param notOKMessageHeader Message header of the error displayed if the update doesn't succeed
     * @param notOKMessageBody Message body of the error displayed if the update doesn't succeed
     * @param okMessageHeader Message header of the information displayed if the update succeeds
     * @param okMessageBody Message body of the information displayed if the update succeeds
     */
    public void hireApplicant(
            String errorMessageHeader,
            String errorMessageBody,
            String notOKMessageHeader,
            String notOKMessageBody,
            String okMessageHeader,
            String okMessageBody){
        
        this.errorMessageHeader = errorMessageHeader;
        this.errorMessageBody = errorMessageBody;
        this.notOKMessageHeader = notOKMessageHeader;
        this.notOKMessageBody = notOKMessageBody;
        this.okMessageHeader = okMessageHeader;
        this.okMessageBody = okMessageBody;
        this.value = true;

        checkIfModified();
    }
    
    /**
     * General method that provides functionality to update an application.
     */
    public void updateApplication(){
        
        boolean isOK = controller.updateApplication(selectedApplication.getPersonID(),value);
        
        if(isOK){
            showMessageInContext(FacesMessage.SEVERITY_INFO, okMessageHeader, okMessageBody);
        } else {
            showMessageInContext(FacesMessage.SEVERITY_ERROR, notOKMessageHeader, notOKMessageBody);
        }
    }
    
    /**
     * General method that provides functionality to display messages on the web page.
     * @param fm Message type
     * @param messageHeader Message header of the information displayed
     * @param messageBody Message body of the information displayed
     */
    private void showMessageInContext(FacesMessage.Severity fm, String messageHeader, String messageBody){
        FacesMessage message = new FacesMessage(fm, messageHeader, messageBody);
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }
    
    /**
     * Retrieves a list of queried applications
     * @return An <code>ArrayList</code> of <code>QueriedApplicationDTO</code> objects, where each
     * instance of the object represents a job application
     */
    public ArrayList<QueriedApplicationDTO> getQueriedApplications(){
        return applications;
    }

    /**
     * Set what name that has been set as a search parameter in the browser
     * @param nameSearch Name, partial or complete
     */
    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    /**
     * Retrieves the name that has been set on the web page
     * @return Name, partial or complete
     */
    public String getNameSearch() {
        return nameSearch;
    }

    /**
     * Sets the start date of the date interval in the browser
     * @param startDate Start date, if any
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the end date of the date interval in the browser
     * @param endDate End date, if any
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Retrieves the start date of the date interval
     * @return Start date, if any
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Retrieves the end date of the date interval
     * @return End date, if any
     */
    public Date getEndDate() {
        return endDate;
    }
    
    
    
    
}
