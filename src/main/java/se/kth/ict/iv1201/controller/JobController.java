
package se.kth.ict.iv1201.controller;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import se.kth.ict.iv1201.model.Verification;
import se.kth.ict.iv1201.model.dao.JobDAO;

/**
 * An enterprise bean with the purpose of providing transaction logic between the presentation layer
 * and the business layer, specifically for any transactions related to job queries.
 * @author Christian Schreil
 */

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class JobController {
    
    private Verification verification;
    @EJB
    private JobDAO jobDAO;

    @PostConstruct
    public void startUp() {
        verification = new Verification();
    }
    
    
    
}
