
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
import se.kth.ict.iv1201.model.dto.QueriedApplicationAvailabilityDTO;
import se.kth.ict.iv1201.model.dto.QueriedApplicationCompetenceDTO;
import se.kth.ict.iv1201.model.dto.QueriedApplicationDTO;
import se.kth.ict.iv1201.model.entities.Application;
import se.kth.ict.iv1201.model.entities.Language;

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
    private final boolean isDetailed = false; //Regulates if queried applications should be detailed or not

    /**
     * Retrieves available applications from the database
     * @param competences What competencies should be queried
     * @param nameSearch Explicit name to be queried
     * @param startDate Start date of the interval, if any
     * @param endDate End date of the interval, if any
     * @param languageCode Language code
     * @return An <code>ArrayList</code> of <code>QueriedApplicationDTO</code> objects
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public ArrayList<QueriedApplicationDTO> getQueriedApplications(
            String[] competences,
            String nameSearch,
            Date startDate,
            Date endDate,
            String languageCode) {
        
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

        if(isDetailed){
            Language lang = em.createNamedQuery("Language.findByLanguageCode", Language.class
            ).setParameter("languageCode", languageCode).getSingleResult();

            for (QueriedApplicationDTO q : result) {
                int personid = q.getPersonID();
                query = em.createQuery("SELECT NEW se.kth.ict.iv1201.model.dto.QueriedApplicationAvailabilityDTO(j.applicationAvailabilityID,j.fromDate,j.toDate) FROM Application a JOIN a.applicationAvailabilityCollection j WHERE a.personID = :personid", QueriedApplicationAvailabilityDTO.class);
                query.setParameter("personid", personid);

                ArrayList<QueriedApplicationAvailabilityDTO> availabilites = new ArrayList(query.getResultList());
                query = em.createQuery("SELECT NEW se.kth.ict.iv1201.model.dto.QueriedApplicationCompetenceDTO(k.competenceID,l.description,j.yearsOFExperience) FROM Application a JOIN a.applicationCompetenceCollection j JOIN j.competenceID k JOIN k.competenceTranslationCollection l WHERE a.personID = :personid AND l.languageCode = :lang", QueriedApplicationCompetenceDTO.class);
                query.setParameter("personid", personid);
                query.setParameter("lang", lang);

                ArrayList<QueriedApplicationCompetenceDTO> competencies = new ArrayList(query.getResultList());

                q.setAvailabilites(availabilites);
                q.setCompetencies(competencies);
            }
        }
        
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
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
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
    
    /**
     * Retrieve specific application from the database
     * @param username Name of the user that currently is logged on
     * @param languageCode Language code
     * @return A <code>QueriedApplicationDTO</code> that represents the current state of the application
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public QueriedApplicationDTO getApplication(String username, String languageCode){
        
        Language lang = em.createNamedQuery("Language.findByLanguageCode", Language.class
        ).setParameter("languageCode", languageCode).getSingleResult();
        
        query = em.createQuery("SELECT DISTINCT NEW se.kth.ict.iv1201.model.dto.QueriedApplicationDTO(j.personID,j.firstname, j.lastname, k.registrationDate, k.hired, k.lastModified) FROM User a JOIN a.personCollection j JOIN j.application k WHERE a.username = :user",QueriedApplicationDTO.class);
        query.setParameter("user", username);
        
        QueriedApplicationDTO application = (QueriedApplicationDTO) query.getSingleResult();
        
        if(application == null){
            query = em.createQuery("SELECT NEW se.kth.ict.iv1201.model.dto.QueriedApplicationAvailabilityDTO(j.applicationAvailabilityID,j.fromDate,j.toDate) FROM Application a JOIN a.applicationAvailabilityCollection j WHERE a.personID = :personid", QueriedApplicationAvailabilityDTO.class);
            query.setParameter("personid", application.getPersonID());

            ArrayList<QueriedApplicationAvailabilityDTO> availabilites = new ArrayList(query.getResultList());
            query = em.createQuery("SELECT NEW se.kth.ict.iv1201.model.dto.QueriedApplicationCompetenceDTO(k.competenceID,l.description,j.yearsOFExperience) FROM Application a JOIN a.applicationCompetenceCollection j JOIN j.competenceID k JOIN k.competenceTranslationCollection l WHERE a.personID = :personid AND l.languageCode = :lang", QueriedApplicationCompetenceDTO.class);
            query.setParameter("personid", application.getPersonID());
            query.setParameter("lang", lang);

            ArrayList<QueriedApplicationCompetenceDTO> competencies = new ArrayList(query.getResultList());

            application.setAvailabilites(availabilites);
            application.setCompetencies(competencies);
        }
        
        return application;
        
    }
    
    
}
