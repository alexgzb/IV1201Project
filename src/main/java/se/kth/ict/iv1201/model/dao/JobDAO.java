
package se.kth.ict.iv1201.model.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO that handles transactions related to job data
 * @author Christian Schreil
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class JobDAO {
    
    @PersistenceContext(unitName = "rsPU")
    private EntityManager em;
 
    
}
