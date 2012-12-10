package cz.muni.fi.pa165.stis.dao.impl;

import cz.muni.fi.pa165.stis.dao.CustomerDAO;
import cz.muni.fi.pa165.stis.entity.Customer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author xmravec1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles("test")
@Transactional
public class CustomerDAOImplTest {
    
    private Customer customer;
    @Autowired
    private CustomerDAO customerDAO;
    
    @Before
    public void setUp() {
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
        } catch (DataAccessException e) {
            // ok
        } catch (Exception e) {
            fail("Customer is null and didn't throw appropriate exception.");
        }

        customer = newCustomer(null, null, null, null);
        customer.setId(11L);
        try {
            customerDAO.create(customer);
            fail("ID not null and didn't throw exception");
        } catch (DataAccessException ex) {
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
        Long id = null;
        try {
            customerDAO.get(id);
            fail("Id null and didn't throw exception");
        } catch (DataAccessException ex) {
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
        } catch (DataAccessException ex) {
            //ok
        } catch (Exception ex) {
            fail("Customer is null and didn't throw appropriate exception");
        }
        try {
            customerDAO.update(cust);
            fail("Customer is ID null and didn't throw exception");
        } catch (DataAccessException ex) {
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
        } catch (DataAccessException ex) {
            //ok
        } catch (Exception ex) {
            fail("Customer is null and didn't throw appropriate exception");
        }
        try {
            customerDAO.remove(new Customer());
            fail("Customer ID is null and didn't throw exception");
        } catch (DataAccessException ex) {
            // ok
        } catch (Exception ex) {
            fail("Customer ID is null and didn't throw appropriate exception");
        }
        try {
            Customer cust2 = new Customer();
            cust2.setId(-1L);
            customerDAO.remove(cust2);
            fail("Shouldn't remove non-existent entity");
        } catch (DataAccessException ex) {
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
        List<Customer> customers = customerDAO.findAll();
        assertTrue("Customers should be empty", customers.isEmpty());
        customers = Arrays.asList(new Customer[] {
            newCustomer("Bruce", "Willis", null, null),
            newCustomer("Jan", "Hrach", null, null),
            newCustomer("Peter", "Mravec", null, null)
        });
        for (Customer cust : customers) {
            customerDAO.create(cust);
        }
        List<Customer> all = customerDAO.findAll();
        assertTrue("Size is not the same", all.size() == customers.size());
        Collections.sort(all, customerComparator);
        Collections.sort(customers, customerComparator);
        for (int i = 0; i < all.size(); i++) {
            assertDeepEquals(all.get(i), customers.get(i));
        }
    }

    /**
     * Test of findByName method, of class CustomerDAO.
     */
    @Test
    public void testFindByName() {
        Customer cust1 = newCustomer("Laco", "Springel", "Ulica1 22, Dolny Kubin", "+421912345678");
        customerDAO.create(cust1);
        Customer cust2 = newCustomer("Peter", "Janosciak", "Ulica2 33, Brezno", "+421911222333");
        customerDAO.create(cust2);
        
        String firstName;
        String lastName;

        try {
            customerDAO.findByName(null, null);
            fail("String firstName and lastNameis null");
        } catch (DataAccessException e) {
            // ok
        } catch (Exception e) {
            fail("String name is null - should have been thrown another exception");
        }

        firstName = "Lacy";
        lastName = "Springel";
        List<Customer> customers = customerDAO.findByName(firstName, lastName);
        assertTrue("ExtraService is not in DB", customers.isEmpty());

        firstName = "Laco";
        lastName = "Springel";
        customers = customerDAO.findByName(firstName, lastName);
        assertEquals(cust1.getId(), customers.get(0).getId());
        assertDeepEquals(cust1, customers.get(0));

        Customer cust3 = newCustomer("Jakub", "Kmet", "Ulica3 44, Zilina", "+421911111111");
        customerDAO.create(cust3);
        Customer cust4 = newCustomer("Dusan", "Gorcak", "Ulica4 55, Lokca", "+421977777777");
        customerDAO.create(cust4);

        List<Customer> customerList = Arrays.asList(new Customer[]{cust1, cust2, cust3, cust4});

        customers = customerDAO.findByName(firstName, lastName);
        assertTrue("Customer lists are not the same size.", customers.size() == customerList.size() - 3);
        customers = customerDAO.findByName(null, lastName);
        assertTrue("Customer lists are not the same size.", customers.size() == customerList.size() - 3);
        customers = customerDAO.findByName(firstName, null);
        assertTrue("Customer lists are not the same size.", customers.size() == customerList.size() - 3);
        Collections.sort(customers, customerComparator);
        Collections.sort(customerList, customerComparator);

        int i;
        for (Customer cust : customers) {
            if (customerList.contains(cust)) {
                i = customerList.indexOf(cust);
                assertDeepEquals(cust, customerList.get(i));
            }
        }
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
    
    private static Comparator<Customer> customerComparator = new Comparator<Customer>() {
        @Override
        public int compare(Customer t, Customer t1) {
            return t.getId().compareTo(t1.getId());
        }
    };
}
