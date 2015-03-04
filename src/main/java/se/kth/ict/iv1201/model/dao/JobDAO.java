
package se.kth.ict.iv1201.model.dao;

import java.util.ArrayList;
import java.util.Arrays;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import se.kth.ict.iv1201.model.dto.QueriedApplicationDTO;

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
     * 
     * @param competences
     * @param nameSearch 
     * @return 
     */
    public ArrayList<QueriedApplicationDTO> getQueriedApplications(String[] competences, String nameSearch) {
        
        query = em.createQuery("SELECT DISTINCT NEW se.kth.ict.iv1201.model.dto.QueriedApplicationDTO(j.firstname, j.lastname, k.registrationDate, k.hired, k.lastModified) FROM Person j JOIN j.application k JOIN k.applicationCompetenceCollection l JOIN l.competenceID m JOIN m.competenceTranslationCollection n WHERE n.description IN :selectedcompetences AND CONCAT(j.firstname,' ',j.lastname) LIKE :name",QueriedApplicationDTO.class);
        query.setParameter("selectedcompetences", Arrays.asList(competences));
        
        if(nameSearch.equals("")){
            query.setParameter("name", "%");
        } else {
            query.setParameter("name", "%" + nameSearch + "%");
        }
        
        
        ArrayList<QueriedApplicationDTO> result = new ArrayList(query.getResultList());

        return result;
    }
    
    
    
}
