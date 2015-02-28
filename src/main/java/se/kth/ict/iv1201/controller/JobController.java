
package se.kth.ict.iv1201.controller;

import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import se.kth.ict.iv1201.model.dao.JobDAO;
import se.kth.ict.iv1201.model.dto.JobDTO;

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

    /**
     * Retrieves all available jobs in the database, by joining key values with translations in two different tables.
     * @param langaugeCode Specifies what language the jobs should be retrieved in
     * @return An <code>ArrayList</code> of <code>JobDTO</code> objects, where each instance of the DTO represents a job
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ArrayList<JobDTO> getAvailableJobs(String langaugeCode) {
        return jobDAO.getAvailableJobs(langaugeCode);
    }
    
    
    
}
