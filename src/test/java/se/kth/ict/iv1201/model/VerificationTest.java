package se.kth.ict.iv1201.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import se.kth.ict.iv1201.model.dao.AccountDAO;
import se.kth.ict.iv1201.model.dto.AccountDTO;
import se.kth.ict.iv1201.model.dto.ApplicationDTO;
import se.kth.ict.iv1201.model.dto.CompetenceDTO;
import se.kth.ict.iv1201.model.dto.ResponseDTO;
import se.kth.ict.iv1201.model.entities.Application;
import se.kth.ict.iv1201.model.entities.ApplicationAvailability;
import se.kth.ict.iv1201.model.entities.ApplicationCompetence;
import se.kth.ict.iv1201.model.entities.Person;
import se.kth.ict.iv1201.model.entities.User;

@RunWith(Arquillian.class)
public class VerificationTest {

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addPackage(Verification.class.getPackage())
                .addClasses(Verification.class, Person.class, User.class,
                        Application.class, ApplicationAvailability.class,
                        ApplicationCompetence.class, ApplicationDTO.class,
                        AccountDTO.class, ResponseDTO.class, CompetenceDTO.class,
                        AccountDAO.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(jar.toString(true));
        return jar;
    }

    @Inject
    Verification verification;

    /**
     * Tests to make a new account and makes sure all the saved data is
     * accurate.
     */
    @Test
    public void testNewAccount_allOk() throws Exception {
        assertEquals(verification.verifyAccount(new AccountDTO("notTaken", "0123456789", "Wille", "Magnusson", "will@a.se", "9309113948")).getStatusMessage(), "accountDataValid");
        assertEquals(verification.verifyAccount(new AccountDTO("notTaken2", "abcoridko", "123@3*¨++", "kcwe023##%6s", "will@a.se", "9409213948")).getStatusMessage(), "accountDataValid");
    }

    /**
     * Tests to make a new account with missing fields.
     */
    @Test
    public void testNewAccount_missingFields() throws Exception {
        assertEquals(verification.verifyAccount(new AccountDTO(null, "0123456789", "Wille", "Magnusson", "will@a.se", "9309113948")).getStatusMessage(), "fieldVerFaild");
        assertEquals(verification.verifyAccount(new AccountDTO("notTaken", null, "Wille", "Magnusson", "will@a.se", "9309113948")).getStatusMessage(), "passwordVerFaild");
        assertEquals(verification.verifyAccount(new AccountDTO("notTaken", "0123456789", null, "Magnusson", "will@a.se", "9309113948")).getStatusMessage(), "fieldVerFaild");
        assertEquals(verification.verifyAccount(new AccountDTO("notTaken", "0123456789", "Wille", null, "will@a.se", "9309113948")).getStatusMessage(), "fieldVerFaild");
        assertEquals(verification.verifyAccount(new AccountDTO("notTaken", "0123456789", "Wille", "Magnusson", null, "9309113948")).getStatusMessage(), "fieldVerFaild");
        assertEquals(verification.verifyAccount(new AccountDTO("notTaken", "0123456789", "Wille", "Magnusson", "will@a.se", null)).getStatusMessage(), "fieldVerFaild");
    }

    /**
     * Tests to make an account with already used data and data that is not
     * allowed, should be stopped from doing this.
     *
     * @throws Exception
     */
    @Test
    public void testNewAccount_faultyData_usedData() throws Exception {
        assertEquals(verification.verifyAccount(new AccountDTO("notTaken", "0123", "Wille", "Magnusson", "will@a.se", "9309113948")).getStatusMessage(), "passwordVerFaild");
    }

    /**
     * Tests to make an application with all valid data.
     *
     * @throws Exception
     */
    @Test
    public void testAddApplication_allOK() throws Exception {
        assertEquals(verification.verifyApplication(new ApplicationDTO("abc", new BigDecimal[]{new BigDecimal(2.5)}, new int[]{2}, new Date[]{new Date(1)}, new Date[]{new Date(5)})).getStatusMessage(), "applicationDataValid");
        assertEquals(verification.verifyApplication(new ApplicationDTO("abc", new BigDecimal[]{new BigDecimal(1.2), new BigDecimal(2.5)}, new int[]{1, 2}, new Date[]{new Date(1), new Date(10)}, new Date[]{new Date(5), new Date(15)})).getStatusMessage(), "applicationDataValid");
        assertEquals(verification.verifyApplication(new ApplicationDTO("abc", new BigDecimal[]{new BigDecimal(1.2), new BigDecimal(2.5), new BigDecimal(3.5), new BigDecimal(2.5), new BigDecimal(2.5)}, new int[]{1, 2, 3, 4, 5}, new Date[]{new Date(1), new Date(10), new Date(20), new Date(30), new Date(40)}, new Date[]{new Date(5), new Date(15), new Date(25), new Date(35), new Date(45)})).getStatusMessage(), "applicationDataValid"); 
    }

    /**
     * Tests to make an application with faulty data, should fail.
     *
     * @throws Exception
     */
    @Test
    public void testAddApplication_missingUser() throws Exception {
        assertEquals(verification.verifyApplication(new ApplicationDTO("abc", new BigDecimal[]{new BigDecimal(2.5)}, new int[]{2}, new Date[]{new Date(1)}, new Date[]{})).getStatusMessage(), "applicationDateFail");
        assertEquals(verification.verifyApplication(new ApplicationDTO("abc", new BigDecimal[]{new BigDecimal(1.2), new BigDecimal(2.5)}, new int[]{2}, new Date[]{new Date(1), new Date(10)}, new Date[]{new Date(5), new Date(15)})).getStatusMessage(), "applicationDateFail");
        assertEquals(verification.verifyApplication(new ApplicationDTO("abc", new BigDecimal[]{new BigDecimal(1.2), new BigDecimal(2.5), new BigDecimal(3.5), new BigDecimal(2.5), new BigDecimal(2.5)}, new int[]{1, 2, 3, 4, 5}, new Date[]{new Date(1), new Date(10), new Date(20), new Date(35), new Date(40)}, new Date[]{new Date(5), new Date(15), new Date(25), new Date(30), new Date(45)})).getStatusMessage(), "applicationDateFail"); 
        assertEquals(verification.verifyApplication(new ApplicationDTO("abc", new BigDecimal[]{new BigDecimal(1.2), new BigDecimal(2.5), new BigDecimal(3.5), new BigDecimal(2.5), new BigDecimal(2.5)}, new int[]{1, 2, 3, 4, 5}, new Date[]{new Date(1), new Date(10), new Date(20), new Date(30), new Date(40)}, new Date[]{new Date(5), new Date(15), new Date(25), new Date(45), new Date(45)})).getStatusMessage(), "applicationDateFail"); 
    }
}

