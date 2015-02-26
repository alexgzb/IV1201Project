package se.kth.ict.iv1201.model.dao;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import se.kth.ict.iv1201.model.dto.AccountDTO;
import se.kth.ict.iv1201.model.dto.ApplicationDTO;
import se.kth.ict.iv1201.model.dto.CompetencesDTO;
import se.kth.ict.iv1201.model.entities.Application;
import se.kth.ict.iv1201.model.entities.ApplicationAvailability;
import se.kth.ict.iv1201.model.entities.ApplicationCompetence;
import se.kth.ict.iv1201.model.entities.Competence;
import se.kth.ict.iv1201.model.entities.CompetenceTranslation;
import se.kth.ict.iv1201.model.entities.Language;
import se.kth.ict.iv1201.model.entities.Person;
import se.kth.ict.iv1201.model.entities.User;
import se.kth.ict.iv1201.model.entities.Role;
import se.kth.ict.iv1201.model.entities.UserRole;
import se.kth.ict.iv1201.util.log.Log;

/**
 * AccountDAO class to manage account related data to the database
 *
 */
@Stateless
@Log
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AccountDAO {

    @PersistenceContext(unitName = "rsPU")
    private EntityManager em;

    /**
     * Method that takes the ApplicationDTO and creates an application entity.
     * The entity is then saved to the database, and the dependencies are made.
     *
     * @param data User entered data for the application.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void addApplication(ApplicationDTO data) {
        User user = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", data.getUsername()).getSingleResult();
        Person person = em.createNamedQuery("Person.findByUsername", Person.class).setParameter("username", user).getSingleResult();
        Application application = new Application(person.getPersonID());
        ArrayList<ApplicationAvailability> availability = new ArrayList<ApplicationAvailability>();
        for (int i = 0; i < data.getFromDate().length; i++) {
            ApplicationAvailability a = new ApplicationAvailability();
            a.setFromDate(data.getFromDate()[i]);
            a.setToDate(data.getToDate()[i]);
            a.setPersonID(application);
            availability.add(a);
        }
        application.setApplicationAvailabilityCollection(availability);
        ArrayList<ApplicationCompetence> competence = new ArrayList<ApplicationCompetence>();
        for (int i = 0; i < data.getCompetence().length; i++) {
            ApplicationCompetence c = new ApplicationCompetence(data.getExperiance()[i]);
            c.setCompetenceID(data.getCompetence()[i]);
            c.setPersonID(application);
            competence.add(c);
        }
        application.setApplicationCompetenceCollection(competence);
        person.setApplication(application);
        em.persist(application);
    }

    /**
     * Returns all competences of the selected language form the database, if no
     * result were found null is returned. If data is found it is returned in a
     * DTO.
     *
     * @param langCode The selected language for the competences.
     * @return
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public CompetencesDTO getCompetences(String langCode) {
        Language lang = em.createNamedQuery("Language.findByLanguageCode", Language.class
        ).setParameter("languageCode", langCode).getSingleResult();
        Query query = em.createQuery("select c from CompetenceTranslation c where c.languageCode = :code");

        query.setParameter(
                "code", lang);
        List<CompetenceTranslation> result = query.getResultList();

        if (result.size()
                < 1) {
            return null;
        }
        String[] des = new String[result.size()];
        Competence[] CID = new Competence[result.size()];
        int i = 0;
        for (CompetenceTranslation c : result) {
            des[i] = c.getDescription();
            CID[i] = c.getCompetenceID();
            i++;
        }

        return new CompetencesDTO(des, CID);
    }

    /**
     * Method that takes the AccountDTO to and creates a user and a person. The
     * user is then mapped to its specific role to ensure proper authentication
     * and authorization.
     *
     * @param accountDTO the data to insert to the tables
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void NewAccount(AccountDTO accountDTO) {
        User newUser;
        Person newPerson;
        // Map each user to the Applicant role - CS 20150219
        Role role;
        UserRole userRole;

        String userName = accountDTO.getUsername();
        String password = encryptPassword(accountDTO.getPassword());  //Added encryption algorithm - CS 20150217
        String firstName = accountDTO.getFirstname();
        String lastName = accountDTO.getLastname();
        String email = accountDTO.getEmail();
        String ssn = accountDTO.getSsn();

        newUser = new User(userName, password);
        em.persist(newUser);
        newPerson = new Person(firstName, lastName, ssn, email, newUser);
        em.persist(newPerson);

        //Find the Applicant role and map it to the user by persisting the object to UserRole - CS 20150219
        role = em.createNamedQuery("Role.findByRoleName", Role.class
        ).setParameter("roleName", "Applicant").getSingleResult();
        userRole = new UserRole(userName, role);

        em.persist(userRole);
    }

    /**
     * Method to check the uniqueness of certain columns in the user and person
     * tables
     *
     * @param accountDTO The data to check towards the db
     * @return empty String if everything is OK, otherwise the name of the
     * column that fails
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
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
            user = em.createNamedQuery("User.findByUsername", User.class
            ).setParameter("username", userName).getSingleResult();

            return "username";
        } catch (NoResultException e) {
            // Fall through
        }

        try {
            person = em.createNamedQuery("Person.findByEmail", Person.class
            ).setParameter("email", email).getSingleResult();

            return "email";
        } catch (NoResultException e) {
            // Fall through
        }
        try {
            person = em.createNamedQuery("Person.findBySsn", Person.class
            ).setParameter("ssn", ssn).getSingleResult();

            return "ssn";
        } catch (NoResultException e) {
            // Fall through
        }

        //If all names are available empty string is returned
        return null;
    }

    /**
     * Cryptographic encryption (SHA-256) of the password the user supplied
     * during registration.
     *
     * @param password Password without encryption.
     * @return Encrypted password string.
     */
    private String encryptPassword(String password) {

        MessageDigest messageDigest;
        byte[] hash = null;

        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            hash = messageDigest.digest(password.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            // Fall through
        }

        StringBuilder encryptedString = new StringBuilder();

        for (byte singleByte : hash) {
            encryptedString.append(Integer.toString((singleByte & 0xff) + 0x100, 16).substring(1));
        }

        return encryptedString.toString();
    }

    /**
     * Retrieves a user's specific role.
     *
     * @param username Name of the user that is logging in
     * @return Name of the role that the user belongs to
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public String getUserRole(String username) {

        UserRole userRole = em.createNamedQuery("UserRole.findByUsername", UserRole.class
        ).setParameter("username", username).getSingleResult();
        return userRole.getRoleName()
                .getRoleName();

    }

}
