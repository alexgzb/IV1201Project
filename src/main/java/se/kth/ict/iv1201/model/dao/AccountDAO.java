package se.kth.ict.iv1201.model.dao;

import java.io.UnsupportedEncodingException;
import java.security.*;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import se.kth.ict.iv1201.model.dto.AccountDTO;
import se.kth.ict.iv1201.model.entities.Person;
import se.kth.ict.iv1201.model.entities.User;
import se.kth.ict.iv1201.model.entities.Role;
import se.kth.ict.iv1201.model.entities.UserRole;
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
     * Method that takes the AccountDTO to and creates a user and a person.
     * The user is then mapped to its specific role to ensure proper authentication
     * and authorization.
     * @param accountDTO the data to insert to the tables
     */
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
        role = em.createNamedQuery("Role.findByRoleName", Role.class).setParameter("roleName", "Applicant").getSingleResult();
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
    
    /**
     * Cryptographic encryption (SHA-256) of the password the user supplied during registration.
     * @param password Password without encryption.
     * @return Encrypted password string.
     */
    private String encryptPassword(String password){
        
        MessageDigest messageDigest;
        byte[] hash = null;
        
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            hash = messageDigest.digest(password.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            // Fall through
        }
        
        StringBuilder encryptedString = new StringBuilder();

        for (byte singleByte : hash){
            encryptedString.append(Integer.toString((singleByte & 0xff) + 0x100, 16).substring(1));
        }

        
        return encryptedString.toString();
    }
    
    /**
     * Retrieves a user's specific role.
     * @param username Name of the user that is logging in
     * @return Name of the role that the user belongs to
     */
    public String getUserRole(String username){
        
        UserRole userRole = em.createNamedQuery("UserRole.findByUsername", UserRole.class).setParameter("username", username).getSingleResult();
        return userRole.getRoleName().getRoleName();
        
    }

}
