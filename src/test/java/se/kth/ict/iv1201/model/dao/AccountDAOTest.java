package se.kth.ict.iv1201.model.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import se.kth.ict.iv1201.model.dto.AccountDTO;
import se.kth.ict.iv1201.model.dto.ApplicationDTO;
import se.kth.ict.iv1201.model.dto.CompetenceDTO;
import se.kth.ict.iv1201.model.dto.ResponseDTO;
import se.kth.ict.iv1201.model.entities.Application;
import se.kth.ict.iv1201.model.entities.ApplicationAvailability;
import se.kth.ict.iv1201.model.entities.ApplicationCompetence;
import se.kth.ict.iv1201.model.entities.Competence;
import se.kth.ict.iv1201.model.entities.Person;
import se.kth.ict.iv1201.model.entities.User;

/**
 * Test class for the AccountDAO
 *
 */
@RunWith(Arquillian.class)
public class AccountDAOTest {

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addPackage(AccountDAO.class.getPackage())
                .addClasses(AccountDAO.class, Person.class, User.class,
                        Application.class, ApplicationAvailability.class,
                        ApplicationCompetence.class, ApplicationDTO.class,
                        AccountDTO.class, ResponseDTO.class, CompetenceDTO.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(jar.toString(true));
        return jar;
    }

    @Inject
    AccountDAO accountDao;

    @PersistenceContext(unitName = "rsPU")
    EntityManager em;

    @Inject
    UserTransaction utx;

    @Before
    public void preparePersistenceTest() throws Exception {
        clearData();
        insertData();
    }

    private void clearData() throws Exception {
        utx.begin();
        em.joinTransaction();
        em.createNativeQuery("DELETE FROM ApplicationAvailability").executeUpdate();
        em.createNativeQuery("DELETE FROM ApplicationCompetence").executeUpdate();
        em.createNativeQuery("DELETE FROM Application").executeUpdate();
        em.createNativeQuery("DELETE FROM UserRole").executeUpdate();
        em.createNativeQuery("DELETE FROM Person").executeUpdate();
        em.createNativeQuery("DELETE FROM User").executeUpdate();
        utx.commit();
    }

    private void insertData() throws Exception {
        utx.begin();
        em.joinTransaction();
        User newUser;
        Person newPerson;

        String userName = "borg";
        String password = "0123456789";
        String firstName = "Greta";
        String lastName = "Borg";
        String email = "per@strand.kth.se";
        String ssn = "196712121211";

        newUser = new User(userName, password);
        em.persist(newUser);
        newPerson = new Person(firstName, lastName, ssn, email, newUser);
        em.persist(newPerson);
        ApplicationDTO data = new ApplicationDTO(userName, new BigDecimal[]{new BigDecimal(1.2), new BigDecimal(2.5)}, new int[]{1, 2}, new Date[]{new Date(1), new Date(10)}, new Date[]{new Date(5), new Date(15)});
        Application application = new Application(newPerson.getPersonID());
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
            Competence competenceID = em.createNamedQuery("Competence.findByCompetenceID", Competence.class).setParameter("competenceID", data.getCompetence()[i]).getSingleResult();
            ApplicationCompetence c = new ApplicationCompetence(data.getExperiance()[i]);
            c.setCompetenceID(competenceID);
            c.setPersonID(application);
            competence.add(c);
        }
        application.setApplicationCompetenceCollection(competence);
        newPerson.setApplication(application);
        em.persist(application);
        utx.commit();
        // clear the persistence context (first-level cache)
        em.clear();
    }

    /**
     * Test of NewAccount method, of class AccountDAO.
     */
    @Test
    public void testVerifyUniqueAccount_allOK_returnNull() throws Exception {
        utx.begin();
        em.joinTransaction();
        AccountDTO acccount = new AccountDTO("nottaken", "123456789", "Anders", "Borg", "a@b.cd", "1111111111");
        String result = accountDao.VerifyUniqueAccount(acccount);
        String expected = null;
        assertEquals(expected, result);
        utx.commit();
    }

    /**
     * Test of NewAccount method, of class AccountDAO.
     */
    @Test
    public void testVerifyUniqueAccount_illegalCharactersUsername_returnNull() throws Exception {
        utx.begin();
        em.joinTransaction();
        AccountDTO acccount = new AccountDTO("http://google.se", "123456789", "Anders", "Borg", "a@b.cd", "1111111111");
        String result = accountDao.VerifyUniqueAccount(acccount);
        String expected = null;
        assertEquals(expected, result);
        utx.commit();
    }

    /**
     * Test of NewAccount method, of class AccountDAO. Test of the username
     * already taken scenario.
     */
    @Test
    public void testVerifyUniqueAccount_usernameAlreadyTaken_returnStringUsername() throws Exception {
        utx.begin();
        em.joinTransaction();
        AccountDTO acccount = new AccountDTO("borg", "123456789", "Anders", "Borg", "anders@borg.se", "4709202362");
        String result = accountDao.VerifyUniqueAccount(acccount);
        String expected = "username";
        assertEquals(expected, result);
        utx.commit();
    }

    /**
     * Test of VerifyUniqueAccount method, of class AccountDAO. Test of the
     * email already taken scenario
     */
    @Test
    public void testVerifyUniqueAccount_emailAlreadyTaken_returnStringEmail() throws Exception {
        utx.begin();
        em.joinTransaction();
        AccountDTO acccount = new AccountDTO("notTaken", "123456789", "Anders", "Borg", "per@strand.kth.se", "4709202362");
        String result = accountDao.VerifyUniqueAccount(acccount);
        String expected = "email";
        assertEquals(expected, result);
        utx.commit();
    }

    /**
     * Test of VerifyUniqueAccount method, of class AccountDAO. Test of the
     * email already taken scenario
     */
    @Test
    public void testVerifyUniqueAccount_ssnAlreadyTaken_returnStringSsn() throws Exception {
        utx.begin();
        em.joinTransaction();
        AccountDTO acccount = new AccountDTO("notTaken", "123456789", "Anders", "Borg", "per@a.se", "196712121211");
        String result = accountDao.VerifyUniqueAccount(acccount);
        String expected = "ssn";
        assertEquals(expected, result);
        utx.commit();
    }

    /**
     * Tests to make a new account and makes sure all the saved data is
     * accurate.
     */
    @Test
    public void testNewAccount_allOk() throws Exception {
        utx.begin();
        em.joinTransaction();
        accountDao.newAccount(new AccountDTO("notTaken", "0123456789", "Wille", "Magnusson", "will@a.se", "9309113948"));
        utx.commit();
        User user = null;
        Person person = null;
        try {
            user = em.createNamedQuery("User.findByUsername", User.class
            ).setParameter("username", "notTaken").getSingleResult();
            person = em.createNamedQuery("Person.findBySsn", Person.class
            ).setParameter("ssn", "9309113948").getSingleResult();
            assertEquals(AccountDAO.encryptPassword("0123456789"), user.getPassword());
        } catch (NoResultException | NonUniqueResultException e) {
            assertFalse(true);
        }
        assertEquals(user.getPassword(), accountDao.encryptPassword("0123456789"));
        assertEquals(user.getUsername(), "notTaken");
        assertEquals(person.getEmail(), "will@a.se");
        assertEquals(person.getFirstname(), "Wille");
        assertEquals(person.getLastname(), "Magnusson");
        utx.begin();
        em.joinTransaction();
        user = em.createNamedQuery("User.findByUsername", User.class
        ).setParameter("username", "notTaken").getSingleResult();
        person = em.createNamedQuery("Person.findBySsn", Person.class
        ).setParameter("ssn", "9309113948").getSingleResult();
        em.remove(person);
        em.createNativeQuery("DELETE FROM UserRole").executeUpdate();
        em.remove(user);
        utx.commit();

        try {
            user = em.createNamedQuery("User.findByUsername", User.class
            ).setParameter("username", "notTaken").getSingleResult();
            assertFalse(true);
        } catch (NoResultException e) {
        }
        try {
            person = em.createNamedQuery("Person.findBySsn", Person.class
            ).setParameter("ssn", "9309113948").getSingleResult();
            assertFalse(true);
        } catch (NoResultException e) {
        }
    }

    /**
     * Tests to make a new account with missing fields.
     */
    @Test
    public void testNewAccount_missingFields() throws Exception {
        try {
            accountDao.newAccount(new AccountDTO(null, "0123456789", "Wille", "Manusson", "will@a.se", "9309113948"));
            assertFalse(true);
        } catch (Exception e) {
        }
        try {
            accountDao.newAccount(new AccountDTO("notTaken", null, "Wille", "Manusson", "will@a.se", "9309113948"));
            assertFalse(true);
        } catch (Exception e) {
        }
        try {
            accountDao.newAccount(new AccountDTO("notTaken", "0123456789", null, "Manusson", "will@a.se", "9309113948"));
            assertFalse(true);
        } catch (Exception e) {
        }
        try {
            accountDao.newAccount(new AccountDTO("notTaken", "0123456789", "Wille", null, "will@a.se", "9309113948"));
            assertFalse(true);
        } catch (Exception e) {
        }
        try {
            accountDao.newAccount(new AccountDTO("notTaken", "0123456789", "Wille", "Manusson", null, "9309113948"));
            assertFalse(true);
        } catch (Exception e) {
        }
        try {
            accountDao.newAccount(new AccountDTO("notTaken", "0123456789", "Wille", "Manusson", "will@a.se", null));
            assertFalse(true);
        } catch (Exception e) {
        }
    }

    /**
     * Tests to make an application with all valid data.
     *
     * @throws Exception
     */
    @Test
    public void testAddApplication_allOK() throws Exception {
        utx.begin();
        em.joinTransaction();
        User newUser;
        Person newPerson;
        String userName = "abc";
        String password = "0123456789";
        String firstName = "Test";
        String lastName = "Tester";
        String email = "test@kth.se";
        String ssn = "201212121212";
        newUser = new User(userName, password);
        em.persist(newUser);
        newPerson = new Person(firstName, lastName, ssn, email, newUser);
        em.persist(newPerson);
        ApplicationDTO data = new ApplicationDTO(userName, new BigDecimal[]{new BigDecimal(1.2), new BigDecimal(2.5)}, new int[]{1, 2}, new Date[]{new Date(1), new Date(10)}, new Date[]{new Date(5), new Date(15)});
        utx.commit();
        utx.begin();
        em.joinTransaction();
        ResponseDTO response = accountDao.addApplication(data);
        assertTrue(response.isSuccess());
        User user = em.createNamedQuery("User.findByUsername", User.class).setParameter("username", data.getUsername()).getSingleResult();
        Person person = em.createNamedQuery("Person.findByUsername", Person.class).setParameter("username", user).getSingleResult();
        Application application = em.createNamedQuery("Application.findByPersonID", Application.class
        ).setParameter("personID", person.getPersonID()).getSingleResult();
        assertTrue(application.getPersonID().equals(person.getPersonID()));
        utx.commit();
        clearData();
        insertData();
    }

    /**
     * Tests to make an application for a user that does not exists, should
     * fail.
     *
     * @throws Exception
     */
    @Test
    public void testAddApplication_missingUser() throws Exception {
        utx.begin();
        em.joinTransaction();
        ApplicationDTO data = new ApplicationDTO("notTaken", new BigDecimal[]{new BigDecimal(1.2), new BigDecimal(2.5)}, new int[]{1, 2}, new Date[]{new Date(1), new Date(10)}, new Date[]{new Date(5), new Date(15)});
        ResponseDTO response = accountDao.addApplication(data);
        assertFalse(response.isSuccess());
        utx.commit();
    }

    /**
     * Tests to make an application for a user that already has one, should
     * fail.
     *
     * @throws Exception
     */
    @Test
    public void testAddApplication_applicationExists() throws Exception {
        utx.begin();
        em.joinTransaction();
        ApplicationDTO data = new ApplicationDTO("borg", new BigDecimal[]{new BigDecimal(1.2), new BigDecimal(2.5)}, new int[]{1, 2}, new Date[]{new Date(1), new Date(10)}, new Date[]{new Date(5), new Date(15)});
        ResponseDTO response = accountDao.addApplication(data);
        assertFalse(response.isSuccess());
        utx.commit();
    }
}
