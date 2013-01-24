/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.stis.facade.impl;

import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.dto.UserTO;
import cz.muni.fi.pa165.stis.entity.Customer;
import cz.muni.fi.pa165.stis.entity.User;
import cz.muni.fi.pa165.stis.service.CustomerService;
import cz.muni.fi.pa165.stis.service.UserService;
import org.dozer.DozerBeanMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Michal Toth
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerUserFacadeImplTest {

    private CustomerUserFacadeImpl facade;
    private Customer customer;
    private CustomerTO customerTO;
    private User user;
    private UserTO userTO;
    private DozerBeanMapper mapper;
    @Mock
    private CustomerService cservice;
    @Mock
    private UserService uservice;
    

    @Before
    public void setUp() {
        mapper = new DozerBeanMapper();
        //cservice = new CustomerServiceImpl();
        //uservice = new UserServiceImpl();
        // preco tu je toto??
        facade = new CustomerUserFacadeImpl();
        
        ReflectionTestUtils.setField(facade, "uservice", uservice);
        ReflectionTestUtils.setField(facade, "cservice", cservice);
        //ReflectionTestUtils.setField(facade, "mapper", mapper);
    }

    @After
    public void tearDown() {
        cservice = null;
        uservice = null;
        mapper = null;
    }

    @Test
    public void testCreate() {
        user = createUser("mrkvicka", "345sac", false);
        user.setId(5L);
        userTO = mapper.map(user, UserTO.class);

        customer = createCustomer(null, "Adam", "Petrik", "Burzoazna 12", "112567");
        customer.setId(4L);
        customerTO = mapper.map(customer, CustomerTO.class);

        try {
            facade.create(null, null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }

        try {
            facade.create(customerTO, null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }

        try {
            facade.create(null, userTO);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }

        customerTO.setId(null);
        try {
            facade.create(customerTO, userTO);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        customerTO.setId(4L);
        userTO.setId(null);
        try {
            facade.create(customerTO, userTO);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        userTO.setId(5L);
        
        facade.create(customerTO, userTO);
        verify(cservice).create(customerTO);
    }
    
    @Test
    public void testGet() {
        
    }
    
    @Test
    public void testRemove() {
        
    }
    
    @Test
    public void testUpdate() {
        
    }
    
    @Test
    public void testFindAll() {
        
    }
    
    private static Customer createCustomer(Long id, String firstName, String lastName, String address, String phone) {
        Customer c = new Customer();
        c.setId(id);
        c.setFirstName(firstName);
        c.setLastName(lastName);
        c.setAddress(address);
        c.setPhone(phone);
        return c;
    }

    private static User createUser(String username, String password, boolean roleAdmin) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoleAdmin(roleAdmin);
        return user;
    }
}
