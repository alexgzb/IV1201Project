/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.model.dao;

import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import se.kth.ict.iv1201.model.dto.AccountDTO;
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
                .addClasses(AccountDAO.class, Person.class, User.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(jar.toString(true));
        return jar;
    }

    @Inject
    AccountDAO accountDao;

    /**
     * Test of NewAccount method, of class AccountDAO.
     */
    @Test
    public void testVerifyUniqueAccount_allOK_returnNull() {
        AccountDTO acccount = new AccountDTO("nottaken", "123456789", "Anders", "Borg", "a@b.cd", "1111111111");
        String result = accountDao.VerifyUniqueAccount(acccount);
        String expected = null;
        assertEquals(expected, result);
    }
    
        /**
     * Test of NewAccount method, of class AccountDAO.
     */
    @Test
    public void testVerifyUniqueAccount_illegalCharactersUsername_returnNull() {
        AccountDTO acccount = new AccountDTO("http://google.se", "123456789", "Anders", "Borg", "a@b.cd", "1111111111");
        String result = accountDao.VerifyUniqueAccount(acccount);
        String expected = null;
        assertEquals(expected, result);
    }

    /**
     * Test of NewAccount method, of class AccountDAO.
     */
    @Test
    public void testVerifyUniqueAccount_usernameAlreadyTaken_returnStringUsername() {
        AccountDTO acccount = new AccountDTO("borg", "123456789", "Anders", "Borg", "anders@borg.se", "4709202362");
        String result = accountDao.VerifyUniqueAccount(acccount);
        String expected = "username";
        assertEquals(expected, result);
    }

    /**
     * Test of VerifyUniqueAccount method, of class AccountDAO. Test of the
     * email already taken scenario
     */
    @Test
    public void testVerifyUniqueAccount_emailAlreadyTaken_returnStringEmail() {
        AccountDTO acccount = new AccountDTO("notTaken", "123456789", "Anders", "Borg", "per@strand.kth.se", "4709202362");
        String result = accountDao.VerifyUniqueAccount(acccount);
        String expected = "email";
        assertEquals(expected, result);
    }

    /**
     * Test of VerifyUniqueAccount method, of class AccountDAO. Test of the
     * email already taken scenario
     */
    @Test
    public void testVerifyUniqueAccount_ssnAlreadyTaken_returnStringSsn() {
        AccountDTO acccount = new AccountDTO("notTaken", "123456789", "Anders", "Borg", "per@a.se", "196712121211");
        String result = accountDao.VerifyUniqueAccount(acccount);
        String expected = "ssn";
        assertEquals(expected, result);
    }

}
