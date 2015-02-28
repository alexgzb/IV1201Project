
package se.kth.ict.iv1201.model.dao;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import se.kth.ict.iv1201.model.dto.JobDTO;
import se.kth.ict.iv1201.model.entities.Language;
import javax.persistence.Query;;

/**
 * DAO that handles transactions related to job data
 * @author Christian Schreil
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class JobDAO {
    
    @PersistenceContext(unitName = "rsPU")
    private EntityManager em;
    
    private Query query;

    /**
     * Retrieves all available jobs in the database, by joining key values with translations in two different tables.
     * @param langaugeCode Specifies what language the jobs should be retrieved in
     * @return An <code>ArrayList</code> of <code>JobDTO</code> objects, where each instance of the DTO represents a job
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ArrayList<JobDTO> getAvailableJobs(String langaugeCode) {
        Language lang = em.createNamedQuery("Language.findByLanguageCode", Language.class
        ).setParameter("languageCode", langaugeCode).getSingleResult();
        
        query = em.createQuery("SELECT NEW se.kth.ict.iv1201.model.dto.JobDTO(j.jobID,k.name,k.description,j.fromDate,j.toDate) FROM Job j JOIN j.jobTranslationCollection k WHERE k.languageCode = :lang",JobDTO.class);
        query.setParameter("lang", lang);
        
        ArrayList<JobDTO> result = new ArrayList(query.getResultList());
        
        return result;
        
    }
    
}
