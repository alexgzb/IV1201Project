package se.kth.ict.iv1201.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import se.kth.ict.iv1201.controller.AccountController;
import se.kth.ict.iv1201.model.dto.AccountDTO;
import se.kth.ict.iv1201.model.dto.ApplicationDTO;
import se.kth.ict.iv1201.model.dto.CompetenceDTO;
import se.kth.ict.iv1201.model.dto.ResponseDTO;
import se.kth.ict.iv1201.util.log.Log;

/**
 * AccountView is a statefull session bean connected to a single user. It
 * handles all calls from the users web interface regarding the users account
 * details and forwards the requests to an AccountController.
 *
 * @author Wilhelm
 */
@Log
@Named("accountView")
@RequestScoped
public class AccountView {
    
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String Email;
    private String ssn;
    private String requestResponse;
    private String errorMessage;
    private ArrayList<CompetenceDTO> competences;
    private BigDecimal[] experiance;
    private Date[] fromDate;
    private Date[] toDate;
    private int[] applicationCompetences;
    @Inject
    private AccountController controller;

    /**
     * The method newAccount takes the data from the users form and constructs a
     * DTO containing that information. This information is then passed to the
     * controller for further actions to be taken.
     *
     * @author Wilhelm
     */
    public void newAccount() {
        try {
            ResponseDTO newAccount = controller.newAccount(new AccountDTO(username, password, firstname,
                    lastname, Email, ssn));
            requestResponse = newAccount.getStatusMessage();
            if (!newAccount.isSuccess()) {
                errorMessage = newAccount.getErrorMessage();
            }
        } catch (Exception e) {
            requestResponse = "unknownEror";
            errorMessage = "unknownEror";
        }
    }

    /**
     * The method getCompetences will send a request to the account controller
     * for the currently available competences in the database for the currently
     * selected language on the web site and save it to the bean for viewing on
     * the site.
     *
     * @author Wilhelm
     * @param langCode The code for the selected language for the site
     */
    public void getCompetences(String langCode) {
        try {
            ArrayList<CompetenceDTO> data = controller.getCompetences(langCode);
            if (data.isEmpty()) {
                errorMessage = "competencesError";
                competences = null;
            } else {
                competences = data;
            }
        } catch (Exception e) {
            requestResponse = "unknownEror";
            errorMessage = "unknownEror";
        }
    }

    /**
     * The method addApplication takes the data from the users form and
     * constructs a DTO containing that information. This information is then
     * passed to the controller for further actions to be taken when adding it
     * ti the account.
     *
     * @param username The username for the account that will get the added
     * application
     */
    public void addApplication(String username) {
        try {
            ResponseDTO application = controller.addApplication(new ApplicationDTO(username, experiance, applicationCompetences, fromDate, toDate));
            requestResponse = application.getStatusMessage();
            if (!application.isSuccess()) {
                errorMessage = application.getErrorMessage();
            }
        } catch (Exception e) {
            requestResponse = "unknownEror";
            errorMessage = "unknownEror";
        }
    }
    
    public ArrayList<CompetenceDTO> getCompetences() {
        return competences;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getFirstname() {
        return firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public String getLastname() {
        return lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
    public String getEmail() {
        return Email;
    }
    
    public void setEmail(String Email) {
        this.Email = Email;
    }
    
    public String getSsn() {
        return ssn;
    }
    
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
    
    public String getRequestResponse() {
        return requestResponse;
    }
    
    public void setRequestResponse(String requestResponse) {
        this.requestResponse = requestResponse;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public BigDecimal[] getExperiance() {
        return experiance;
    }
    
    public void setExperiance(BigDecimal[] experiance) {
        this.experiance = experiance;
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
    
    public int[] getApplicationCompetences() {
        return applicationCompetences;
    }
    
    public void setApplicationCompetences(int[] applicationCompetences) {
        this.applicationCompetences = applicationCompetences;
    }
}
