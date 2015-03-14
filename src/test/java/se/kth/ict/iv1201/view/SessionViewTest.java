/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.view;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import se.kth.ict.iv1201.controller.SessionController;
import se.kth.ict.iv1201.model.dao.AccountDAO;
import se.kth.ict.iv1201.model.entities.Role;
import se.kth.ict.iv1201.model.entities.User;
import se.kth.ict.iv1201.model.entities.UserRole;

@RunWith(Arquillian.class)
public class SessionViewTest {

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addPackage(SessionView.class.getPackage())
                .addPackage(SessionController.class.getPackage())
                .addPackage(User.class.getPackage())
                .addPackage(AccountDAO.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(jar.toString(true));
        return jar;
    }

    @Inject
    SessionView sessionView;

    @Inject
    UserTransaction utx;

    @PersistenceContext(unitName = "rsPU")
    EntityManager em;

    @Before
    public void preparePersistance() throws Exception {
        insertData();
    }
    
    @After
    public void resetPersistance() throws Exception {
        clearData();
    }

    private void insertData() throws Exception {
        utx.begin();
        em.joinTransaction();
        User ApplicantUser;
        User RecruiterUser;

        Role applicantRole;
        Role recruiterRole;
        UserRole applicantUserRole;

        String applicantUserName = "applicant";
        String password = "0123456789";
        String encryptedPassword = AccountDAO.encryptPassword(password);
        ApplicantUser = new User(applicantUserName, encryptedPassword);
        em.persist(ApplicantUser);

        //Find the Applicant role and 
        //map it to the user by persisting the object to UserRole
        applicantRole = em.createNamedQuery("Role.findByRoleName", Role.class
        ).setParameter("roleName", "Applicant").getSingleResult();
        applicantUserRole = new UserRole(applicantUserName, applicantRole);
        em.persist(applicantUserRole);

        UserRole recruiterUserRole;
        String recruiterUserName = "recruiter";
        RecruiterUser = new User(recruiterUserName, encryptedPassword);
        em.persist(RecruiterUser);

        //Find the Recruiter role and 
        //map it to the user by persisting the object to UserRole
        recruiterRole = em.createNamedQuery("Role.findByRoleName", Role.class
        ).setParameter("roleName", "Recruiter").getSingleResult();
        recruiterUserRole = new UserRole(recruiterUserName, recruiterRole);
        em.persist(recruiterUserRole);

        utx.commit();
        // clear the persistence context (first-level cache)
        em.clear();
    }

    private void clearData() throws Exception {
        utx.begin();
        em.joinTransaction();

        Role applicantRole;
        Role recruiterRole;
        UserRole applicantUserRole;
        UserRole recruiterUserRole;

        User ApplicantUser;
        User RecruiterUser;

        String applicantUserName = "applicant";
        String recruiterUserName = "recruiter";

        //Find the Recruiter role and 
        //map it to the user by persisting the object to UserRole
        recruiterRole = em.createNamedQuery("Role.findByRoleName", Role.class
        ).setParameter("roleName", "Recruiter").getSingleResult();
        recruiterUserRole = em.createNamedQuery("UserRole.findByUsername",
                UserRole.class).setParameter("username", recruiterUserName).getSingleResult();
        em.remove(recruiterUserRole);

        //Find the Applicant role and 
        //map it to the user by persisting the object to UserRole
        applicantRole = em.createNamedQuery("Role.findByRoleName", Role.class
        ).setParameter("roleName", "Applicant").getSingleResult();
        applicantUserRole = em.createNamedQuery("UserRole.findByUsername",
                UserRole.class).setParameter("username", applicantUserName).getSingleResult();
        em.remove(applicantUserRole);

        //Find and remove the users by the userName
        ApplicantUser = em.createNamedQuery("User.findByUsername", User.class
        ).setParameter("username", "applicant").getSingleResult();
        em.remove(ApplicantUser);

        RecruiterUser = em.createNamedQuery("User.findByUsername", User.class
        ).setParameter("username", "recruiter").getSingleResult();
        em.remove(RecruiterUser);

        utx.commit();
    }

    /**
     * Test of login method, of class SessionView.
     * Currently fall through method due to Arquillian not supporting
     * Embedded Glassfish security realms
     */
    @Test
    public void testLogin() {

        //String expResult = "Applicant";
        String expResult = "loginerror";
        String userName = "applicant";
        String password = "0123456789";
        sessionView.setPassword(password);
        sessionView.setUsername(userName);
        String result = sessionView.login();
        assertEquals(expResult, result);
    }

}
