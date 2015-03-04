
package se.kth.ict.iv1201.controller;

import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import se.kth.ict.iv1201.model.dao.AccountDAO;
import se.kth.ict.iv1201.model.dao.JobDAO;
import se.kth.ict.iv1201.model.dto.CompetenceDTO;
import se.kth.ict.iv1201.model.dto.QueriedApplicationDTO;

/**
 * An enterprise bean with the purpose of providing transaction logic between the presentation layer
 * and the business layer, specifically for any transactions related to job queries.
 * @author Christian Schreil
 */

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class JobController {
    
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
     * 
     * @param competences
     * @param nameSearch
     * @return 
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ArrayList<QueriedApplicationDTO> getQueriedApplications(String[] competences, String nameSearch) {
        return jobDAO.getQueriedApplications(competences, nameSearch);
    }
    
    
    
}
