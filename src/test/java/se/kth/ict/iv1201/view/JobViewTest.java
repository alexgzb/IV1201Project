package se.kth.ict.iv1201.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJBTransactionRolledbackException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import se.kth.ict.iv1201.controller.JobController;
import se.kth.ict.iv1201.model.dao.JobDAO;
import se.kth.ict.iv1201.model.dto.CompetenceDTO;
import se.kth.ict.iv1201.model.dto.QueriedApplicationDTO;
import se.kth.ict.iv1201.model.entities.User;

@RunWith(Arquillian.class)
public class JobViewTest {

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addPackage(SessionView.class.getPackage())
                .addPackage(JobController.class.getPackage())
                .addPackage(User.class.getPackage())
                .addPackage(JobDAO.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(jar.toString(true));
        return jar;
    }

    @Inject
    JobView jobView;

    @Inject
    JobController controller;

    public JobViewTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testLoadCompetencesWithEngLangCodeGetsResultOfFiveCompetencesSuccess() {
        int expResult = 5;
        ArrayList<CompetenceDTO> competences;
        String languageCode = "eng";
        jobView.loadCompetences(languageCode);
        competences = controller.getCompetences(languageCode);
        ArrayList<String> competencesString = new ArrayList();

        for (CompetenceDTO c : competences) {
            competencesString.add(c.getDescription());
        }
        int result = competencesString.size();

        assertEquals(expResult, result);
    }

    @Test
    public void testLoadCompetencesWithSweLangCodeGetsResultOfFiveCompetencesSuccess() {
        int expResult = 5;
        ArrayList<CompetenceDTO> competences;
        String languageCode = "swe";
        jobView.loadCompetences(languageCode);
        competences = controller.getCompetences(languageCode);
        ArrayList<String> competencesString = new ArrayList();

        for (CompetenceDTO c : competences) {
            competencesString.add(c.getDescription());
        }
        int result = competencesString.size();

        assertEquals(expResult, result);
    }

    @Test(expected = EJBTransactionRolledbackException.class)
    public void testLoadCompetencesWithUnknownLangCodeGetsException() {
        ArrayList<CompetenceDTO> competences;
        String languageCode = "unknown";
        jobView.loadCompetences(languageCode);
        competences = controller.getCompetences(languageCode);
    }


    @Test
    public void testLoadQueriedApplicationsNoResultAssertEquals() {
        String languageCode = "eng";
        String dateIntervalErrorHeader = "End date cannot precede the start date!";
        String dateIntervalErrorStartPrecedesEnd = "End date cannot precede the start date!";
        String dateIntervalErrorNotComplete = "Pick both start and end date to define the interval!";
        String[] selectedCompetences = new String[1];
        selectedCompetences[0] = "Barbequeing sausage";

        jobView.setStartDate(new Date(20L));
        jobView.setEndDate(new Date(25L));
        jobView.setSelectedCompetences(selectedCompetences);
        jobView.setNameSearch("");
        jobView.loadQueriedApplications(languageCode, dateIntervalErrorHeader, dateIntervalErrorStartPrecedesEnd, dateIntervalErrorNotComplete);
        ArrayList<QueriedApplicationDTO> queriedApplications = jobView.getQueriedApplications();
        boolean expResult = true;
        boolean result = queriedApplications.isEmpty();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testLoadQueriedApplicationsReturnOneApplicantDTO() {
        String languageCode = "eng";
        String dateIntervalErrorHeader = "End date cannot precede the start date!";
        String dateIntervalErrorStartPrecedesEnd = "End date cannot precede the start date!";
        String dateIntervalErrorNotComplete = "Pick both start and end date to define the interval!";
        String[] selectedCompetences = new String[1];
        selectedCompetences[0] = "Barbequeing sausage";

        // Test from Date = 2014-02-23 Should return 1 compatible applicant
        jobView.setStartDate(new Date(1393200000));
        jobView.setEndDate(new Date());
        jobView.setSelectedCompetences(selectedCompetences);
        jobView.setNameSearch("");
        jobView.loadQueriedApplications(languageCode, dateIntervalErrorHeader, dateIntervalErrorStartPrecedesEnd, dateIntervalErrorNotComplete);
        ArrayList<QueriedApplicationDTO> queriedApplications = jobView.getQueriedApplications();
        // System.out.println("Size of QueriedApplications " + queriedApplications.size());
        boolean expResult = false;
        boolean result = queriedApplications.isEmpty();
        assertEquals(expResult, result);
    }
    
    
//
//    /**
//     * Test of getSelectedApplication method, of class JobView.
//     */
//    @Test
//    public void testGetSelectedApplication() {
//        System.out.println("getSelectedApplication");
//        JobView instance = new JobView();
//        QueriedApplicationDTO expResult = null;
//        QueriedApplicationDTO result = instance.getSelectedApplication();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setSelectedApplication method, of class JobView.
//     */
//    @Test
//    public void testSetSelectedApplication() {
//        System.out.println("setSelectedApplication");
//        QueriedApplicationDTO selectedApplication = null;
//        JobView instance = new JobView();
//        instance.setSelectedApplication(selectedApplication);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of fireApplicant method, of class JobView.
//     */
//    @Test
//    public void testFireApplicant() {
//        System.out.println("fireApplicant");
//        String errorMessageHeader = "";
//        String errorMessageBody = "";
//        String notOKMessageHeader = "";
//        String notOKMessageBody = "";
//        String okMessageHeader = "";
//        String okMessageBody = "";
//        JobView instance = new JobView();
//        instance.fireApplicant(errorMessageHeader, errorMessageBody, notOKMessageHeader, notOKMessageBody, okMessageHeader, okMessageBody);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of hireApplicant method, of class JobView.
//     */
//    @Test
//    public void testHireApplicant() {
//        System.out.println("hireApplicant");
//        String errorMessageHeader = "";
//        String errorMessageBody = "";
//        String notOKMessageHeader = "";
//        String notOKMessageBody = "";
//        String okMessageHeader = "";
//        String okMessageBody = "";
//        JobView instance = new JobView();
//        instance.hireApplicant(errorMessageHeader, errorMessageBody, notOKMessageHeader, notOKMessageBody, okMessageHeader, okMessageBody);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of updateApplication method, of class JobView.
//     */
//    @Test
//    public void testUpdateApplication() {
//        System.out.println("updateApplication");
//        JobView instance = new JobView();
//        instance.updateApplication();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getQueriedApplications method, of class JobView.
//     */
//    @Test
//    public void testGetQueriedApplications() {
//        System.out.println("getQueriedApplications");
//        JobView instance = new JobView();
//        ArrayList<QueriedApplicationDTO> expResult = null;
//        ArrayList<QueriedApplicationDTO> result = instance.getQueriedApplications();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setNameSearch method, of class JobView.
//     */
//    @Test
//    public void testSetNameSearch() {
//        System.out.println("setNameSearch");
//        String nameSearch = "";
//        JobView instance = new JobView();
//        instance.setNameSearch(nameSearch);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getNameSearch method, of class JobView.
//     */
//    @Test
//    public void testGetNameSearch() {
//        System.out.println("getNameSearch");
//        JobView instance = new JobView();
//        String expResult = "";
//        String result = instance.getNameSearch();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setStartDate method, of class JobView.
//     */
//    @Test
//    public void testSetStartDate() {
//        System.out.println("setStartDate");
//        Date startDate = null;
//        JobView instance = new JobView();
//        instance.setStartDate(startDate);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setEndDate method, of class JobView.
//     */
//    @Test
//    public void testSetEndDate() {
//        System.out.println("setEndDate");
//        Date endDate = null;
//        JobView instance = new JobView();
//        instance.setEndDate(endDate);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getStartDate method, of class JobView.
//     */
//    @Test
//    public void testGetStartDate() {
//        System.out.println("getStartDate");
//        JobView instance = new JobView();
//        Date expResult = null;
//        Date result = instance.getStartDate();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getEndDate method, of class JobView.
//     */
//    @Test
//    public void testGetEndDate() {
//        System.out.println("getEndDate");
//        JobView instance = new JobView();
//        Date expResult = null;
//        Date result = instance.getEndDate();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
