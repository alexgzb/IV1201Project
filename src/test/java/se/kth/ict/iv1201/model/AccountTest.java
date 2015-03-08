//To test ENTITY NOT READY YET
//
//package se.kth.ict.iv1201.model;
//
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.UserTransaction;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.runner.RunWith;
//import se.kth.ict.iv1201.model.dao.AccountDAO;
//import se.kth.ict.iv1201.model.dto.AccountDTO;
//import se.kth.ict.iv1201.model.entities.Person;
//import se.kth.ict.iv1201.model.entities.User;
//
///**
// * Test class for the AccountDAO
// *
// */
//@RunWith(Arquillian.class)
//public class AccountTest {
//
//    public AccountTest() {
//    }
//
//    @Deployment
//    public static JavaArchive createDeployment() {
//        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
//                .addClass(AccountDAO.class)
//                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//        System.out.println(jar.toString(true));
//        return jar;
//    }
//
//    private static final AccountDTO TestAccountDataDTO = new AccountDTO("strand",
//            "c71e2d9d4d0b64ae7d8ab581d3ae821051887ff4f4ea0eda0aaee7add12c90db",
//            "Per", "Strand", "per@strand.kth.se", "196712121211");
//
//    @Inject
//    AccountDAO accountDao;
//
//    @PersistenceContext(unitName = "rsPU")
//    EntityManager em;
//
//    @Inject
//    UserTransaction utx;
//
//    @Before
//    public void preparePersistenceTest() throws Exception {
//        clearData();
//        insertData();
//        startTransaction();
//    }
//
//    private void clearData() throws Exception {
//        utx.begin();
//        em.joinTransaction();
//        System.out.println("Dumping old records...");
//        em.createQuery("delete from Person and User").executeUpdate();
//        utx.commit();
//    }
//
//    private void insertData() throws Exception {
//        utx.begin();
//        em.joinTransaction();
//        System.out.println("Inserting records...");
//        User newUser;
//        Person newPerson;
//
//        String userName = TestAccountDataDTO.getUsername();
//        String password = TestAccountDataDTO.getPassword();
//        String firstName = TestAccountDataDTO.getFirstname();
//        String lastName = TestAccountDataDTO.getLastname();
//        String email = TestAccountDataDTO.getEmail();
//        String ssn = TestAccountDataDTO.getSsn();
//
//        newUser = new User(userName, password);
//        em.persist(newUser);
//        newPerson = new Person(firstName, lastName, ssn, email);
//        em.persist(newPerson);
//        utx.commit();
//        // clear the persistence context (first-level cache)
//        em.clear();
//    }
//
//    private void startTransaction() throws Exception {
//        utx.begin();
//        em.joinTransaction();
//    }
//
//    @After
//    public void commitTransaction() throws Exception {
//        utx.commit();
//    }
//
//    /**
//     * Test of NewAccount method, of class AccountDAO.
//     */
//    @Test
//    //@Ignore("Vänta emd denna")
//    public void shouldFindAllPersonsUsingJpqlQuery() throws Exception {
//        // given
//        String fetchingAllPersonsInJpql = "select p from Person p order by g.id";
//
//        // when
//        System.out.println("Selecting (using JPQL)...");
//        List<Person> persons = em.createQuery(fetchingAllPersonsInJpql, Person.class).getResultList();
//
//        // then
//        System.out.println("Found " + persons.size() + " persons (using JPQL):");
//        assertContainsAllPersons(persons);
//        
//    }
//    
//    private static void assertContainsAllPersons(Collection<Person> retrievedPersons) {
//    final Set<String> retrievedPersonsEmail = new HashSet<String>();
//    for (Person person : retrievedPersons) {
//        System.out.println("* " + person);
//        retrievedPersonsEmail.add(person.getEmail());
//    }
//    Assert.assertTrue(retrievedPersonsEmail.containsAll(Arrays.asList(TestAccountDataDTO)));
//}
//
//}
