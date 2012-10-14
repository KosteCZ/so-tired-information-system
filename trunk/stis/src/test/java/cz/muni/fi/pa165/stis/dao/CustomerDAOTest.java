package cz.muni.fi.pa165.stis.dao;

import cz.muni.fi.pa165.stis.dao.impl.CustomerDAOImpl;
import cz.muni.fi.pa165.stis.entity.Customer;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author xmravec1
 */
public class CustomerDAOTest {
    
    private Customer customer;
    private CustomerDAO customerDAO;
    
    @Before
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        customerDAO = new CustomerDAOImpl();
        ((CustomerDAOImpl) customerDAO).setEntityManagerFactory(emf);
    }
    
    @After
    public void tearDown() {
        removeAll();
    }

    /**
     * Test of create method, of class CustomerDAO.
     */
    @Test
    public void testCreate() {        
        customer = null;

        try {
            customerDAO.create(customer);
            fail("Customer is null and didn't throw exception.");
        } catch (IllegalArgumentException e) {
            // ok
        } catch (Exception e) {
            fail("Customer is null and didn't throw appropriate exception.");
        }

        customer = newCustomer(null, null, null, null);
        customer.setId(11L);
        try {
            customerDAO.create(customer);
            fail("ID not null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            //ok
        } catch (Exception ex) {
            fail("ID not null and didn't throw appropriate exception");
        }
        customer.setId(null);
        customerDAO.create(customer);
        assertNotNull("ID is null", customer.getId());
    }

    /**
     * Test of get method, of class CustomerDAO.
     */
    @Test
    public void testGet() {
        Customer cust = newCustomer(null, null, null, null);
        customerDAO.create(cust);
        //ExtraService e = newExtraService(null, null, BigDecimal.ZERO);
        //extraServiceDAO.create(e);
        Long id = null;
        try {
            customerDAO.get(id);
            fail("Id null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            //ok
        } catch (Exception ex) {
            fail("Id null and didn't throw appropriate exception");
        }
        Customer cust2 = customerDAO.get(cust.getId());
        assertNotNull("Customer is null", cust2);
        assertEquals("Customer are not the same", cust2, cust);
        assertDeepEquals(cust2, cust);
        Customer cust3 = customerDAO.get(cust.getId() + 1); // shouldn't exist
        assertNull("ExtraService is not null", cust3);
        removeAll();
    }

    /**
     * Test of update method, of class CustomerDAO.
     */
    @Test
    public void testUpdate() {
        Customer cust = newCustomer(null, null, null, null);
        customerDAO.create(cust);
        Long custId = cust.getId();
        cust.setId(null);
        //
        try {
            customerDAO.update(null);
            fail("Customer is null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            //ok
        } catch (Exception ex) {
            fail("Customer is null and didn't throw appropriate exception");
        }
        try {
            customerDAO.update(cust);
            fail("Customer is ID null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            //ok
        } catch (Exception ex) {
            fail("Customer is ID null and didn't throw appropriate exception");
        }
        //
        cust.setId(custId);
        customerDAO.update(cust);
        //
        Customer cust2 = customerDAO.get(cust.getId());
        assertEquals("Customer are not the same", cust2, cust);
        assertDeepEquals(cust2, cust);
    }

    /**
     * Test of remove method, of class CustomerDAO.
     */
    @Test
    public void testRemove() {
        Customer cust = newCustomer(null, null, null, null);
        customerDAO.create(cust);
        //
        try {
            customerDAO.remove(null);
            fail("Customer is null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            //ok
        } catch (Exception ex) {
            fail("Customer is null and didn't throw appropriate exception");
        }
        try {
            customerDAO.remove(new Customer());
            fail("Customer ID is null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            // ok
        } catch (Exception ex) {
            fail("Customer ID is null and didn't throw appropriate exception");
        }
        try {
            Customer cust2 = new Customer();
            cust2.setId(-1L);
            customerDAO.remove(cust2);
            fail("Shouldn't remove non-existent entity");
        } catch (IllegalArgumentException ex) {
            //ok
        } catch (Exception ex) {
            fail("Non existent es (extra service) - should throw appropriate exception");
        }
        //
        customerDAO.remove(cust);
        Customer cust3 = customerDAO.get(cust.getId());
        assertNull("Found extraService that shouldn't be there", cust3);
        removeAll();
    }

    /**
     * Test of findAll method, of class CustomerDAO.
     */
    @Test
    public void testFindAll() {
//        System.out.println("findAll");
//        CustomerDAO instance = new CustomerDAOImpl();
//        List expResult = null;
//        List result = instance.findAll();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class CustomerDAO.
     */
    @Test
    public void testFindByName_String() {
//        System.out.println("findByName");
//        String lastName = "";
//        CustomerDAO instance = new CustomerDAOImpl();
//        List expResult = null;
//        List result = instance.findByName(lastName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class CustomerDAO.
     */
    @Test
    public void testFindByName_String_String() {
//        System.out.println("findByName");
//        String lastName = "";
//        String firstName = "";
//        CustomerDAO instance = new CustomerDAOImpl();
//        List expResult = null;
//        List result = instance.findByName(lastName, firstName);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
    private void removeAll() {
        List<Customer> ts = customerDAO.findAll();
        for (Customer t : ts) {
            customerDAO.remove(t);
        }
    }
    
    private static Customer newCustomer(String firstName, String lastName, String address, String phone) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddress(address);
        customer.setPhone(phone);

        return customer;
    }
    
    private void assertDeepEquals(Customer cust1, Customer cust2) {
        assertEquals(cust1 == null, cust2 == null);
        if (cust1 != null) {
            assertEquals(cust1.getId(), cust2.getId());
            assertEquals(cust1.getFirstName(), cust2.getFirstName());
            assertEquals(cust1.getLastName(), cust2.getLastName());
            assertEquals(cust1.getAddress(), cust2.getAddress());
        }
    }
}
