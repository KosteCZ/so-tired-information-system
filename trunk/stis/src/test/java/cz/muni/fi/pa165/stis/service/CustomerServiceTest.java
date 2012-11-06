package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.dao.CustomerDAO;
import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.entity.Customer;
import cz.muni.fi.pa165.stis.service.impl.CustomerServiceImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.dozer.DozerBeanMapper;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author Dusan Svancara
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
    
    private CustomerServiceImpl service;
    @Mock
    private CustomerDAO dao;
    private DozerBeanMapper mapper;
    
    private Customer customer;
    private CustomerTO customerTO;
    
    @Before
    public void setUp() {
        mapper = new DozerBeanMapper();
        service = new CustomerServiceImpl();
        ReflectionTestUtils.setField(service, "dao", dao);
        ReflectionTestUtils.setField(service, "mapper", mapper);
        //
        customer = createCustomer(null, "Adam", "Petrik", "Burzoazna 12", "112567");
        customerTO = mapper.map(customer, CustomerTO.class);
    }
    
    @After
    public void tearDown() {
        service = null;
    }
    
    @Test
    public void testExceptions() {
        try {
            service.create(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            service.update(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            service.get(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            service.remove(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            service.findByName(null, null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        
        try {
            service.update(customerTO);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            service.remove(customerTO);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        customerTO.setId(1L);
        try {
            service.create(customerTO);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
    }

    @Test
    public void testCreate() {
        service.create(customerTO);
        final Customer c = customer;
        verify(dao).create(argThat(new BaseMatcher<Customer>() {

            @Override
            public boolean matches(Object item) {
                if (!(item instanceof Customer)) {
                    return false;
                }
                final Customer other = (Customer) item;
                if (!Objects.equals(c.getId(), other.getId())) {
                    return false;
                }
                if (!Objects.equals(c.getFirstName(), other.getFirstName())) {
                    return false;
                }
                if (!Objects.equals(c.getLastName(), other.getLastName())) {
                    return false;
                }
                if (!Objects.equals(c.getAddress(), other.getAddress())) {
                    return false;
                }
                if (!Objects.equals(c.getPhone(), other.getPhone())) {
                    return false;
                }
                
                return true;
            }

            @Override
            public void describeTo(Description description) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }));
    }

    @Test
    public void testGet() {
        customer.setId(1L);
        customerTO.setId(1L);
        when(dao.get(1L)).thenReturn(customer);
        CustomerTO ct = service.get(1L);
        
        assertEquals(ct, customerTO);
    }

    @Test
    public void testUpdate() {
        customerTO.setId(1L);
        customer.setId(1L);
        
        service.update(customerTO);
        verify(dao).update(customer);
    }

    @Test
    public void testRemove() {
        customerTO.setId(1L);
        customer.setId(1L);
        
        service.remove(customerTO);
        verify(dao).remove(customer);
    }

    @Test
    public void testFindAll() {
        Customer c = createCustomer(2L, "Istvan", "Lelkes", "Hajovna 12", "54544");
        customer.setId(1L);
        customerTO.setId(1L);
        
        List<Customer> customers = Arrays.asList(new Customer[] {customer, c});
        List<CustomerTO> ctos = Arrays.asList(new CustomerTO[] {customerTO, mapper.map(c, CustomerTO.class)});
        when(dao.findAll()).thenReturn(customers);
        
        List<CustomerTO> res = service.findAll();
        
        assertTrue(res.containsAll(ctos) && ctos.containsAll(res));
    }

    @Test
    public void testFindByName() {
        Customer c = createCustomer(2L, "Istvan", "Lelkes", "Hajovna 12", "54544");
        customer.setId(1L);
        customerTO.setId(1L);
        
        List<Customer> customers = Arrays.asList(new Customer[] {c});
        List<CustomerTO> ctos = Arrays.asList(new CustomerTO[] {mapper.map(c, CustomerTO.class)});
        when(dao.findByName("Istvan", "Lelkes")).thenReturn(customers);
        
        List<CustomerTO> res = service.findByName("Istvan", "Lelkes");
        
        assertTrue(res.containsAll(ctos) && ctos.containsAll(res));
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
}
