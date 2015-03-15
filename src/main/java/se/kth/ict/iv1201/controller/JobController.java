
package se.kth.ict.iv1201.controller;

import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import se.kth.ict.iv1201.model.Verification;
import se.kth.ict.iv1201.model.dao.AccountDAO;
import se.kth.ict.iv1201.model.dao.JobDAO;
import se.kth.ict.iv1201.model.dto.CompetenceDTO;
import se.kth.ict.iv1201.model.dto.QueriedApplicationDTO;
import se.kth.ict.iv1201.util.log.Log;

/**
 * An enterprise bean with the purpose of providing transaction logic between the presentation layer
 * and the business layer, specifically for any transactions related to job queries.
 * @author Christian Schreil
 */

@Stateless
@Log
@TransactionManagement(TransactionManagementType.CONTAINER)
public class JobController {
    
    private Verification verification;
    @EJB
    private JobDAO jobDAO;
    @EJB
    private AccountDAO accountDAO;
    
    /**
     * Method to retrieve available comptences from the database
     * @param langaugeCode Code of the language the page has been loaded in
     * @return A list of competences
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ArrayList<CompetenceDTO> getCompetences(String langaugeCode) {
        return accountDAO.getCompetences(langaugeCode);
    }

    /**
     * Retrieves available applications from the database
     * @param languageCode Language code
     * @param competences What competencies should be queried
     * @param nameSearch Explicit name to be queried
     * @param startDate Start date of the interval, if any
     * @param endDate End date of the interval, if any
     * @return An <code>ArrayList</code> of <code>QueriedApplicationDTO</code> objects
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ArrayList<QueriedApplicationDTO> getQueriedApplications(
            String languageCode,
            String[] competences,
            String nameSearch,
            Date startDate,
            Date endDate) {
        return jobDAO.getQueriedApplications(competences, nameSearch, startDate, endDate,languageCode);
    }

    /**
     * Updates an applicants status to whatever data given by the parameter <code>value</code>.
     * @param personID ID of the applicant to be updated
     * @param value True of false, depending on whether the applicant is employed or fired
     * @return True if the transactions succeeds, false if the transaction fails
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean updateApplication(int personID, boolean value) {
        return jobDAO.updateApplication(personID, value);
    }
    
    /**
     * Checks if applicant has been modified since last fetch
     * @param personID ID of the applicant to be checked
     * @param dateTimeModified Date/time of when the applicant last was modified
     * @return True if the applicant has been modified, false if not
     */
    public boolean isModified(int personID, Date dateTimeModified){
        return jobDAO.isModified(personID, dateTimeModified);
    }
    
    /**
     * Retrieve specific application from the database
     * @param username Name of the user that currently is logged on
     * @param languageCode Language code
     * @return A <code>QueriedApplicationDTO</code> that represents the current state of the application
     */
    public QueriedApplicationDTO getApplication(String username, String languageCode){
        return jobDAO.getApplication(username, languageCode);
    }
    
    
}
