package se.kth.ict.iv1201.model.dao;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import se.kth.ict.iv1201.model.dto.AccountDTO;
import se.kth.ict.iv1201.model.entities.Person;
import se.kth.ict.iv1201.model.entities.User;

/**
 * AccountDAO class to manage account related data to the database
 *
 */
//@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class AccountDAO {

    @PersistenceContext(unitName = "rsPU")
    private EntityManager em;
    
    // Temp method to test Persistance Unit
    // Remove later
    public String  test(AccountDTO account) {
        
        
        System.out.println("DAO out!");
        User tempUser = new User("Wille", "pass");
        em.persist(tempUser);
        tempUser = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", "borg").getSingleResult();
        return tempUser.getUsername();
    }

    /**
     * Method that takes the AccountDTO to and creates a user and a person
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
        Long ssn = accountDTO.getSsn();

        newUser = new User(userName, password);
        em.persist(newUser);
        newPerson = new Person(firstName, lastName, ssn, email);
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
        Long ssn = accountDTO.getSsn();
        User user = null;
        Person person = null;
        
        //Checking if username is already used, if so return "username"
        user = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", userName).getSingleResult();
        if (user != null) {
            return "username";
        }

        //Checking if email is already used, if so return "email"
        person = em.createNamedQuery("Person.findByEmail", Person.class).setParameter("email", email).getSingleResult();
        if (person != null) {
            return "email";
        }

        //Checking if ssn is already used, if so return "ssn"
        person = em.createNamedQuery("Person.findBySsn", Person.class).setParameter("ssn", ssn).getSingleResult();
        if (person != null) {
            return "ssn";
        }

        //If columns are available empty string is returned
        return null;
    }

}
