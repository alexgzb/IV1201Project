package se.kth.ict.iv1201.model.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import se.kth.ict.iv1201.model.dto.AccountDTO;
import se.kth.ict.iv1201.model.dto.CompetencesDTO;
import se.kth.ict.iv1201.model.entities.CompetenceTranslation;
import se.kth.ict.iv1201.model.entities.Language;
import se.kth.ict.iv1201.model.entities.Person;
import se.kth.ict.iv1201.model.entities.User;

/**
 * AccountDAO class to manage account related data to the database
 *
 */
@Stateless
public class AccountDAO {

    @PersistenceContext(unitName = "rsPU")
    private EntityManager em;

    // Temp method to test Persistance Unit
    // Remove later
    public String test(AccountDTO account) {

        System.out.println("DAO out!");
        User tempUser = new User("Wille", "pass");
        em.persist(tempUser);
        tempUser = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", "borg").getSingleResult();
        return tempUser.getUsername();
    }

    /**
     * Returns all competences of the selected language form the database, if no
     * result were found null is returned. If data is found it is returned in a
     * DTO.
     * 
     * @param langCode The selected language for the competences.
     * @return 
     */
    public CompetencesDTO getCompetences(String langCode) {
        Language lang = em.createNamedQuery("Language.findByLanguageCode", Language.class).setParameter("languageCode", langCode).getSingleResult();
        Query query = em.createQuery("select c from CompetenceTranslation c where c.languageCode = :code");
        query.setParameter("code", lang);
        List<CompetenceTranslation> result = query.getResultList();
        if (result.size() < 1){
            return null;
        }
        String[] des = new String[result.size()];
        int[] CID = new int[result.size()];
        int i = 0;
        for (CompetenceTranslation c : result){
            des[i] = c.getDescription();
            CID[i] = c.getCompetenceID().hashCode();
            i++;
        }
        return new CompetencesDTO(des, CID);
    }

    /**
     * Method that takes the AccountDTO to and creates a user and a person
     *
     * @param accountDTO the data to insert to the tables
     */
    public void NewAccount(AccountDTO accountDTO) {
        User newUser;
        Person newPerson;

        String userName = accountDTO.getUsername();
        String password = accountDTO.getPassword();
        String firstName = accountDTO.getFirstname();
        String lastName = accountDTO.getLastname();
        String email = accountDTO.getEmail();
        String ssn = accountDTO.getSsn();

        newUser = new User(userName, password);
        em.persist(newUser);
        newPerson = new Person(firstName, lastName, ssn, email, newUser);
        em.persist(newPerson);

    }

    /**
     * Method to check the uniqueness of certain columns in the user and person
     * tables
     *
     * @param accountDTO The data to check towards the db
     * @return empty String if everything is OK, otherwise the name of the
     * column that fails
     */
    public String VerifyUniqueAccount(AccountDTO accountDTO) {

        String userName = accountDTO.getUsername();
        String password = accountDTO.getPassword();
        String firstName = accountDTO.getFirstname();
        String lastName = accountDTO.getLastname();
        String email = accountDTO.getEmail();
        String ssn = accountDTO.getSsn();
        User user = null;
        Person person = null;

        try {
            user = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", userName).getSingleResult();
            return "username";
        } catch (NoResultException e) {
            // Fall through
        }

        try {
            person = em.createNamedQuery("Person.findByEmail", Person.class).setParameter("email", email).getSingleResult();
            return "email";
        } catch (NoResultException e) {
            // Fall through
        }
        try {
            person = em.createNamedQuery("Person.findBySsn", Person.class).setParameter("ssn", ssn).getSingleResult();
            return "ssn";
        } catch (NoResultException e) {
            // Fall through
        }

        //If all names are available empty string is returned
        return null;
    }

}
