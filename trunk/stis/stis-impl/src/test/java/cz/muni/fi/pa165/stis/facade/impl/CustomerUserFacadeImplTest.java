package cz.muni.fi.pa165.stis.facade.impl;

import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.dto.CustomerUserTO;
import cz.muni.fi.pa165.stis.dto.UserTO;
import cz.muni.fi.pa165.stis.entity.Customer;
import cz.muni.fi.pa165.stis.entity.User;
import cz.muni.fi.pa165.stis.service.CustomerService;
import cz.muni.fi.pa165.stis.service.UserService;
import java.util.ArrayList;
import java.util.List;
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
    @Mock
    private CustomerUserTO cutoMock;
    @Mock
    private CustomerTO ctoMock;
    
    
    
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
        //user.setId(5L);
        userTO = mapper.map(user, UserTO.class);

        customer = createCustomer(null, "Adam", "Petrik", "Burzoazna 12", "112567");
        //customer.setId(4L);
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

        customerTO.setId(4L);
        try {
            facade.create(customerTO, userTO);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        customerTO.setId(null);
        userTO.setId(4L);
        try {
            facade.create(customerTO, userTO);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        userTO.setId(null);

        facade.create(customerTO, userTO);
        verify(cservice).create(customerTO);
    }

    @Test
    public void testGetByCustomerId() {
        user = createUser("mrkvicka", "345sac", false);
        user.setId(5L);
        userTO = mapper.map(user, UserTO.class);        
        customer = createCustomer(4L, "Adam", "Petrik", "Burzoazna 12", "112567");
        customerTO = mapper.map(customer, CustomerTO.class);        
        CustomerUserTO customerUserTO = new CustomerUserTO(customerTO, userTO);
        customerTO.setUser(userTO);  
        
        when(ctoMock.getUser()).thenReturn(userTO);        
        when(cservice.get(4L)).thenReturn(customerTO);                      

        CustomerUserTO cuto = facade.getByCustomerId(4L);
        assertEquals(customerUserTO, cuto);
    }

    @Test
    public void testRemove() {
        user = createUser("mrkvicka", "345sac", false);
        user.setId(5L);
        userTO = mapper.map(user, UserTO.class);        
        customer = createCustomer(4L, "Adam", "Petrik", "Burzoazna 12", "112567");
        customerTO = mapper.map(customer, CustomerTO.class);        
        CustomerUserTO customerUserTO = new CustomerUserTO(customerTO, userTO);
        customerTO.setUser(userTO);  
        
        facade.remove(customerUserTO);
        verify(cservice).remove(customerTO);
        verify(uservice).remove(userTO);
    }

    @Test
    public void testUpdate() {
        user = createUser("mrkvicka", "345sac", false);
        user.setId(5L);
        userTO = mapper.map(user, UserTO.class);        
        customer = createCustomer(4L, "Adam", "Petrik", "Burzoazna 12", "112567");
        customerTO = mapper.map(customer, CustomerTO.class);        
        CustomerUserTO customerUserTO = new CustomerUserTO(customerTO, userTO);
        customerTO.setUser(userTO);  
        
        //facade.create(customerTO, userTO);
        
        facade.update(customerUserTO);
        verify(cservice).update(customerTO);
        verify(uservice).update(userTO);
        
    }

    @Test
    public void testFindAll() {
        user = createUser("mrkvicka", "345sac", false);        
        userTO = mapper.map(user, UserTO.class);        
        customer = createCustomer(null, "Adam", "Petrik", "Burzoazna 12", "112567");
        customerTO = mapper.map(customer, CustomerTO.class);        
        CustomerUserTO customerUserTO = new CustomerUserTO(customerTO, userTO);
        customerTO.setUser(userTO);  
        
        
        User user2 = createUser("ferko22", "bak!s$#", false);
        UserTO userTO2 = mapper.map(user2, UserTO.class);        
        Customer customer2 = createCustomer(null, "Petko", "Mravcek", "Teplicka nad Vahom 142", "772222222");
        CustomerTO customerTO2 = mapper.map(customer2, CustomerTO.class);        
        CustomerUserTO customerUserTO2 = new CustomerUserTO(customerTO2, userTO2);
        customerTO2.setUser(userTO2);  
        
        List<CustomerTO> customerList = new ArrayList<>();
        customerList.add(customerTO);
        customerList.add(customerTO2);
        
        List<CustomerUserTO> cutoList = new ArrayList<>();        
        cutoList.add(customerUserTO);
        cutoList.add(customerUserTO2);
                
        when(cservice.findAll()).thenReturn(customerList);
        
        List<CustomerUserTO> cutoL = facade.findAll();        
        verify(cservice).findAll();
        assertTrue(cutoList.containsAll(cutoL) && cutoL.containsAll(cutoList));        
    }
    
    @Test
    public void testGetByUsername() {
        user = createUser("mrkvicka", "345sac", false);        
        userTO = mapper.map(user, UserTO.class);        
        customer = createCustomer(null, "Adam", "Petrik", "Burzoazna 12", "112567");
        customerTO = mapper.map(customer, CustomerTO.class);        
        CustomerUserTO customerUserTO = new CustomerUserTO(customerTO, userTO);
        customerTO.setUser(userTO);  
        
        when(uservice.getByUsername("mrkvicka")).thenReturn(userTO);
        when(cservice.getByUsername("mrkvicka")).thenReturn(customerTO);
        CustomerUserTO cuto = facade.getByUsername("mrkvicka");
               
        verify(uservice).getByUsername("mrkvicka");
        verify(cservice).getByUsername("mrkvicka");
        assertTrue(cuto.equals(customerUserTO));
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
