
package se.kth.ict.iv1201.model.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import se.kth.ict.iv1201.model.dto.QueriedApplicationDTO;
import se.kth.ict.iv1201.model.entities.Application;

/**
 * DAO that handles transactions related to job queries.
 * @author Christian Schreil
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class JobDAO {
    
    @PersistenceContext(unitName = "rsPU")
    private EntityManager em;
    
    private Query query;

    /**
     * Retrieves available applications from the database
     * @param competences What competencies should be queried
     * @param nameSearch Explicit name to be queried
     * @param startDate Start date of the interval, if any
     * @param endDate End date of the interval, if any
     * @return An <code>ArrayList</code> of <code>QueriedApplicationDTO</code> objects
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ArrayList<QueriedApplicationDTO> getQueriedApplications(
            String[] competences,
            String nameSearch,
            Date startDate,
            Date endDate) {
        
        Date localStartDate;
        Date localEndDate;
        
        if(startDate == null && endDate == null){
            query = em.createQuery("SELECT MIN(j.fromDate) FROM ApplicationAvailability j", Date.class);
            localStartDate = (Date) query.getSingleResult();
            query = em.createQuery("SELECT MAX(j.toDate) FROM ApplicationAvailability j", Date.class);
            localEndDate = (Date) query.getSingleResult();
        } else {
            localStartDate = startDate;
            localEndDate = endDate;
        }
        
        query = em.createQuery("SELECT DISTINCT NEW se.kth.ict.iv1201.model.dto.QueriedApplicationDTO(j.personID,j.firstname, j.lastname, k.registrationDate, k.hired, k.lastModified) FROM Person j JOIN j.application k JOIN k.applicationCompetenceCollection l JOIN l.competenceID m JOIN m.competenceTranslationCollection n JOIN k.applicationAvailabilityCollection o WHERE n.description IN :selectedcompetences AND CONCAT(j.firstname,' ',j.lastname) LIKE :name AND o.fromDate >= :startdate AND o.toDate <= :enddate",QueriedApplicationDTO.class);
        query.setParameter("selectedcompetences", Arrays.asList(competences));
        
        if(nameSearch.equals("")){
            query.setParameter("name", "%");
        } else {
            query.setParameter("name", "%" + nameSearch + "%");
        }
        
        query.setParameter("startdate", localStartDate);
        query.setParameter("enddate", localEndDate);
        
        ArrayList<QueriedApplicationDTO> result = new ArrayList(query.getResultList());

        return result;
    }

    /**
     * Updates an applicants status to whatever data given by the parameter <code>value</code>.
     * @param personID ID of the applicant to be updated
     * @param value True of false, depending on whether the applicant is employed or fired
     * @return True if the transactions succeeds, false if the transaction fails
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean updateApplication(int personID, boolean value) {
        
        try{
            
            Application application = (Application) em.find(Application.class, personID);

            application.setHired(value);
            application.setLastModified(new Date());
            
            em.flush();

        } catch (Exception e){
            return false;
        }
        
        return true;
    }
    
    /**
     * Checks if applicant has been modified since last fetch
     * @param personID ID of the applicant to be checked
     * @param dateTimeModified Date/time of when the applicant last was modified
     * @return True if the applicant has been modified, false if not
     */
    public boolean isModified(int personID, Date dateTimeModified){
        Application application = (Application) em.find(Application.class, personID);
        
        Date modifiedDate;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try{
            modifiedDate = sdf.parse(sdf.format(application.getLastModified()));
        } catch (ParseException e){
            modifiedDate = application.getLastModified();
        }
        
        return dateTimeModified.compareTo(modifiedDate) < 0;
                
    }
    
    
    
}
