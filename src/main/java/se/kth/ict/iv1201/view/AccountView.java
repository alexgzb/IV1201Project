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
import se.kth.ict.iv1201.model.dto.AccountDTO;
import se.kth.ict.iv1201.model.dto.ApplicationDTO;
import se.kth.ict.iv1201.model.dto.CompetenceDTO;
import se.kth.ict.iv1201.model.dto.CompsDTO;
import se.kth.ict.iv1201.model.dto.DatesDTO;
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
    private CompetenceDTO competence;
    private double tempExperiance;
    private List<CompsDTO> comps;
    @Inject
    private AccountController controller;

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
            if (!newAccount.isSuccess()) {
                errorMessage = newAccount.getErrorMessage();
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public int[] getApplicationCompetences() {
        int[] temp = new int[applicationCompetences.size()];
        for (int i = 0; i < applicationCompetences.size(); i++) {
            temp[i] = applicationCompetences.get(i).intValue();
        }
        return temp;
    }

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

}
