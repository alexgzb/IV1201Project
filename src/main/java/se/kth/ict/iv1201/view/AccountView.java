package se.kth.ict.iv1201.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import se.kth.ict.iv1201.controller.AccountController;
import se.kth.ict.iv1201.controller.JobController;
import se.kth.ict.iv1201.model.dto.AccountDTO;
import se.kth.ict.iv1201.model.dto.ApplicationDTO;
import se.kth.ict.iv1201.model.dto.CompetenceDTO;
import se.kth.ict.iv1201.model.dto.CompsDTO;
import se.kth.ict.iv1201.model.dto.DatesDTO;
import se.kth.ict.iv1201.model.dto.QueriedApplicationAvailabilityDTO;
import se.kth.ict.iv1201.model.dto.QueriedApplicationCompetenceDTO;
import se.kth.ict.iv1201.model.dto.QueriedApplicationDTO;
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
@SessionScoped
public class AccountView implements Serializable {

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String Email;
    private String ssn;
    private String requestResponse;
    private String errorMessage;
    private ArrayList<CompetenceDTO> competences;
    private ArrayList<BigDecimal> experiance;
    private ArrayList<Date> fromDate;
    private ArrayList<Date> toDate;
    private ArrayList<Integer> applicationCompetences;
    private Date tempFrom;
    private Date tempTo;
    private List<DatesDTO> dates;
    private List<DatesDTO> savedDates;
    private CompetenceDTO competence;
    private double tempExperiance;
    private List<CompsDTO> comps;
    private List<CompsDTO> savedComps;
    private List<QueriedApplicationAvailabilityDTO> savedAvailability;
    private List<QueriedApplicationCompetenceDTO> savedCompetence;
    private Date modified;
    private Date register;
    private boolean hired;
    @Inject
    private AccountController controller;
    @Inject
    private JobController jobController;

    @PostConstruct
    public void startUp() {
        fromDate = new ArrayList<Date>();
        toDate = new ArrayList<Date>();
        dates = new ArrayList<DatesDTO>();
        tempFrom = new Date();
        tempTo = new Date();
        applicationCompetences = new ArrayList<Integer>();
        experiance = new ArrayList<BigDecimal>();
        comps = new ArrayList<CompsDTO>();
        savedComps = new ArrayList<CompsDTO>();
        savedDates = new ArrayList<DatesDTO>();
        savedAvailability = new ArrayList<QueriedApplicationAvailabilityDTO>();
        savedCompetence = new ArrayList<QueriedApplicationCompetenceDTO>();
    }

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
            errorMessage = null;
            if (!newAccount.isSuccess()) {
                errorMessage = newAccount.getErrorMessage();
            }
        } catch (Exception e) {
            requestResponse = "unknownEror";
            errorMessage = null;
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
            errorMessage = null;
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
            ResponseDTO application = controller.addApplication(new ApplicationDTO(username, getExperiance(), getApplicationCompetences(), fromDate.toArray(new Date[fromDate.size()]), toDate.toArray(new Date[toDate.size()])));
            requestResponse = application.getStatusMessage();
            errorMessage = null;
            if (!application.isSuccess()) {
                errorMessage = application.getErrorMessage();
            }
        } catch (Exception e) {
            requestResponse = "unknownEror";
            errorMessage = null;
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
        return experiance.toArray(new BigDecimal[experiance.size()]);
    }

    public void setExperiance(BigDecimal[] experiance) {
        this.experiance = new ArrayList<BigDecimal>(Arrays.asList(experiance));
    }

    public Date[] getFromDate() {
        if (fromDate == null) {
            return null;
        }
        return fromDate.toArray(new Date[fromDate.size()]);
    }

    public void setFromDate(Date[] fromDate) {
        this.fromDate = new ArrayList(Arrays.asList(fromDate));
    }

    public Date[] getToDate() {
        if (toDate == null) {
            return null;
        }
        return toDate.toArray(new Date[toDate.size()]);
    }

    public void setToDate(Date[] toDate) {
        this.toDate = new ArrayList(Arrays.asList(toDate));
    }

    /**
     * Converts ApplicationCompetences to an int[] and returns it.
     *
     * @return
     */
    public int[] getApplicationCompetences() {
        int[] temp = new int[applicationCompetences.size()];
        for (int i = 0; i < applicationCompetences.size(); i++) {
            temp[i] = applicationCompetences.get(i).intValue();
        }
        return temp;
    }

    /**
     * Takes an int[] and converts it to an Integer[] and sets
     * ApplicationCompatanse to that value.
     * 
     * @param applicationCompetences 
     */
    public void setApplicationCompetences(int[] applicationCompetences) {
        Integer[] temp = new Integer[applicationCompetences.length];
        for (int i = 0; i < applicationCompetences.length; i++) {
            temp[i] = new Integer(applicationCompetences[i]);
        }
        this.applicationCompetences = new ArrayList<Integer>(Arrays.asList(temp));
    }

    public Date getTempFrom() {
        return tempFrom;
    }

    public void setTempFrom(Date tempFrom) {
        this.tempFrom = tempFrom;
    }

    public Date getTempTo() {
        return tempTo;
    }

    public void setTempTo(Date tempTo) {
        this.tempTo = tempTo;
    }

    /**
     * Adds the selected dates from application xhtml and adds them to the
     * selected list.
     */
    public void addDates() {
        fromDate.add(tempFrom);
        toDate.add(tempTo);
        dates.add(new DatesDTO(tempTo, tempFrom));
    }

    /**
     * Adds the selected competences from application xhtml and adds them to the
     * selected list.
     */
    public void addComps() {

        applicationCompetences.add(competence.getId());
        experiance.add(new BigDecimal(tempExperiance));
        comps.add(new CompsDTO(tempExperiance, competence));
    }

    /**
     * Removes the selected dates from application xhtml and removes them from
     * the selected list.
     */
    public void removeDates() {
        fromDate.remove(fromDate.size() - 1);
        toDate.remove(toDate.size() - 1);
        dates.remove(dates.size() - 1);
    }

    /**
     * Removes the selected competences from application xhtml and removes them
     * from the selected list.
     */
    public void removeComps() {
        applicationCompetences.remove(applicationCompetences.size() - 1);
        experiance.remove(experiance.size() - 1);
        comps.remove(comps.size() - 1);
    }

    public List<DatesDTO> getDates() {
        return dates;
    }

    public void setDates(List<DatesDTO> dates) {
        this.dates = dates;
    }

    public CompetenceDTO getCompetence() {
        return competence;
    }

    /**
     * Finds the right ID for the competence description and sets competence
     * to a new competence with that value.
     * 
     * @param competence 
     */
    public void setCompetence(CompetenceDTO competence) {
        for (int i = 0; i < competences.size(); i++) {
            if (competences.get(i).toString().equals(competence.toString())) {
                competence.setId(competences.get(i).getId());
            }
        }
        this.competence = competence;
    }

    public double getTempExperiance() {
        return tempExperiance;
    }

    public void setTempExperiance(double tempExperiance) {
        this.tempExperiance = tempExperiance;
    }

    public List<CompsDTO> getComps() {
        return comps;
    }

    public void setComps(List<CompsDTO> comps) {
        this.comps = comps;
    }

    public List<QueriedApplicationAvailabilityDTO> getSavedAvailability() {
        return savedAvailability;
    }

    public void setSavedAvailability(List<QueriedApplicationAvailabilityDTO> savedAvailability) {
        this.savedAvailability = savedAvailability;
    }

    public List<QueriedApplicationCompetenceDTO> getSavedCompetence() {
        return savedCompetence;
    }

    public void setSavedCompetence(List<QueriedApplicationCompetenceDTO> savedCompetence) {
        this.savedCompetence = savedCompetence;
    }

    /**
     * Tests if the user that logged in has an application, if so it gets logged
     * in to the view variables.
     *
     * @param username Username of person that is logged in.
     * @param ccode The language code for the selected code.
     * @return
     */
    public boolean hasApplication(String username, String code) {
        savedComps = new ArrayList<CompsDTO>();
        savedDates = new ArrayList<DatesDTO>();
        QueriedApplicationDTO app = jobController.getApplication(username, code);
        if (app == null) {
            return false;
        }
        for (QueriedApplicationAvailabilityDTO a : app.getAvailabilites()) {
            tempFrom = a.getFromDate();
            tempTo = a.getToDate();
            savedDates.add(new DatesDTO(tempTo, tempFrom));
        }
        for (QueriedApplicationCompetenceDTO c : app.getCompetencies()) {
            competence = new CompetenceDTO(c.getDescription(), 0);
            tempExperiance = c.getYearsOfExperience().doubleValue();
            savedComps.add(new CompsDTO(tempExperiance, competence));
        }
        firstname = app.getFirstname();
        lastname = app.getLastname();
        modified = app.getLastModified();
        register = app.getRegistrationDate();
        hired = app.isHired();
        return true;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getRegister() {
        return register;
    }

    public void setRegister(Date register) {
        this.register = register;
    }

    public boolean isHired() {
        return hired;
    }

    public void setHired(boolean hired) {
        this.hired = hired;
    }

    public List<DatesDTO> getSavedDates() {
        return savedDates;
    }

    public void setSavedDates(List<DatesDTO> savedDates) {
        this.savedDates = savedDates;
    }

    public List<CompsDTO> getSavedComps() {
        return savedComps;
    }

    public void setSavedComps(List<CompsDTO> savedComps) {
        this.savedComps = savedComps;
    }

}
