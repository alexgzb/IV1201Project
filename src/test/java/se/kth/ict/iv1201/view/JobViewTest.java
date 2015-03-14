/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.ict.iv1201.view;

import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import se.kth.ict.iv1201.model.dto.QueriedApplicationDTO;

/**
 *
 * @author iv1201
 */
public class JobViewTest {
    
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

    /**
     * Test of loadCompetences method, of class JobView.
     */
    @Test
    public void testLoadCompetences() {
        System.out.println("loadCompetences");
        String languageCode = "";
        JobView instance = new JobView();
        instance.loadCompetences(languageCode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCompetences method, of class JobView.
     */
    @Test
    public void testGetCompetences() {
        System.out.println("getCompetences");
        JobView instance = new JobView();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getCompetences();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelectedCompetences method, of class JobView.
     */
    @Test
    public void testGetSelectedCompetences() {
        System.out.println("getSelectedCompetences");
        JobView instance = new JobView();
        String[] expResult = null;
        String[] result = instance.getSelectedCompetences();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSelectedCompetences method, of class JobView.
     */
    @Test
    public void testSetSelectedCompetences() {
        System.out.println("setSelectedCompetences");
        String[] selectedCompetences = null;
        JobView instance = new JobView();
        instance.setSelectedCompetences(selectedCompetences);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadQueriedApplications method, of class JobView.
     */
    @Test
    public void testLoadQueriedApplications() {
        System.out.println("loadQueriedApplications");
        String dateIntervalErrorHeader = "";
        String dateIntervalErrorStartPrecedesEnd = "";
        String dateIntervalErrorNotComplete = "";
        JobView instance = new JobView();
        instance.loadQueriedApplications(dateIntervalErrorHeader, dateIntervalErrorStartPrecedesEnd, dateIntervalErrorNotComplete);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSelectedApplication method, of class JobView.
     */
    @Test
    public void testGetSelectedApplication() {
        System.out.println("getSelectedApplication");
        JobView instance = new JobView();
        QueriedApplicationDTO expResult = null;
        QueriedApplicationDTO result = instance.getSelectedApplication();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSelectedApplication method, of class JobView.
     */
    @Test
    public void testSetSelectedApplication() {
        System.out.println("setSelectedApplication");
        QueriedApplicationDTO selectedApplication = null;
        JobView instance = new JobView();
        instance.setSelectedApplication(selectedApplication);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireApplicant method, of class JobView.
     */
    @Test
    public void testFireApplicant() {
        System.out.println("fireApplicant");
        String errorMessageHeader = "";
        String errorMessageBody = "";
        String notOKMessageHeader = "";
        String notOKMessageBody = "";
        String okMessageHeader = "";
        String okMessageBody = "";
        JobView instance = new JobView();
        instance.fireApplicant(errorMessageHeader, errorMessageBody, notOKMessageHeader, notOKMessageBody, okMessageHeader, okMessageBody);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hireApplicant method, of class JobView.
     */
    @Test
    public void testHireApplicant() {
        System.out.println("hireApplicant");
        String errorMessageHeader = "";
        String errorMessageBody = "";
        String notOKMessageHeader = "";
        String notOKMessageBody = "";
        String okMessageHeader = "";
        String okMessageBody = "";
        JobView instance = new JobView();
        instance.hireApplicant(errorMessageHeader, errorMessageBody, notOKMessageHeader, notOKMessageBody, okMessageHeader, okMessageBody);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateApplication method, of class JobView.
     */
    @Test
    public void testUpdateApplication() {
        System.out.println("updateApplication");
        JobView instance = new JobView();
        instance.updateApplication();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQueriedApplications method, of class JobView.
     */
    @Test
    public void testGetQueriedApplications() {
        System.out.println("getQueriedApplications");
        JobView instance = new JobView();
        ArrayList<QueriedApplicationDTO> expResult = null;
        ArrayList<QueriedApplicationDTO> result = instance.getQueriedApplications();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNameSearch method, of class JobView.
     */
    @Test
    public void testSetNameSearch() {
        System.out.println("setNameSearch");
        String nameSearch = "";
        JobView instance = new JobView();
        instance.setNameSearch(nameSearch);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNameSearch method, of class JobView.
     */
    @Test
    public void testGetNameSearch() {
        System.out.println("getNameSearch");
        JobView instance = new JobView();
        String expResult = "";
        String result = instance.getNameSearch();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStartDate method, of class JobView.
     */
    @Test
    public void testSetStartDate() {
        System.out.println("setStartDate");
        Date startDate = null;
        JobView instance = new JobView();
        instance.setStartDate(startDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEndDate method, of class JobView.
     */
    @Test
    public void testSetEndDate() {
        System.out.println("setEndDate");
        Date endDate = null;
        JobView instance = new JobView();
        instance.setEndDate(endDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStartDate method, of class JobView.
     */
    @Test
    public void testGetStartDate() {
        System.out.println("getStartDate");
        JobView instance = new JobView();
        Date expResult = null;
        Date result = instance.getStartDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndDate method, of class JobView.
     */
    @Test
    public void testGetEndDate() {
        System.out.println("getEndDate");
        JobView instance = new JobView();
        Date expResult = null;
        Date result = instance.getEndDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
