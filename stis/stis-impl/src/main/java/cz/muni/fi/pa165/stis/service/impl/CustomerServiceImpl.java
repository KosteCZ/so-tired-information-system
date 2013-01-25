package cz.muni.fi.pa165.stis.service.impl;

import cz.muni.fi.pa165.stis.dao.CustomerDAO;
import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.entity.Customer;
import cz.muni.fi.pa165.stis.service.CustomerService;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dusan Svancara
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO dao;
    @Autowired
    private DozerBeanMapper mapper;

    @PreAuthorize("isAuthenticated()")
    @Transactional
    @Override
    public void create(CustomerTO customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer is null");
        }
        if (customer.getId() != null) {
            throw new IllegalArgumentException("customer.id is not null");
        }

        Customer c = mapper.map(customer, Customer.class);
        dao.create(c);
        customer.setId(c.getId());
    }

    @PostAuthorize("hasRole('ROLE_ADMIN') or "
    + "(hasRole('ROLE_USER') and returnObject != null and principal.customer.id == returnObject.id)")
    @Transactional(readOnly = true)
    @Override
    public CustomerTO get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("null id");
        }
        Customer customer = dao.get(id);
        if (customer == null) {
            return null;
        }
        return mapper.map(customer, CustomerTO.class);
    }    

    @PreAuthorize("hasRole('ROLE_ADMIN') or "
    + "(hasRole('ROLE_USER') and principal.customer.id == #customer.id)")
    @Transactional
    @Override
    public void update(CustomerTO customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer is null");
        }
        if (customer.getId() == null) {
            throw new IllegalArgumentException("customer.id is null");
        }

        Customer c = mapper.map(customer, Customer.class);
        dao.update(c);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or "
    + "(hasRole('ROLE_USER') and principal.customer.id == #customer.id)")
    @Transactional
    @Override
    public void remove(CustomerTO customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer is null");
        }
        if (customer.getId() == null) {
            throw new IllegalArgumentException("customer.id is null");
        }
        Customer c = mapper.map(customer, Customer.class);
        dao.remove(c);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerTO> findAll() {
        List<Customer> res = dao.findAll();
        List<CustomerTO> retVal = new ArrayList<>();
        for (Customer c : res) {
            retVal.add(mapper.map(c, CustomerTO.class));
        }

        return retVal;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CustomerTO> findByName(String firstName, String lastName) {
        boolean firstNameNull = (firstName == null);
        boolean lastNameNull = (lastName == null);
        if (lastNameNull && firstNameNull) {
            throw new IllegalArgumentException("null lastName and firstName");
        }

        List<Customer> res = dao.findByName(firstName, lastName);
        List<CustomerTO> retVal = new ArrayList<>();
        for (Customer c : res) {
            retVal.add(mapper.map(c, CustomerTO.class));
        }

        return retVal;
    }
    
    @Transactional
    @Override
    public CustomerTO getByUsername(String username) {
        if (username == null || username.equals("")) {
            throw new IllegalArgumentException("null or empty username");
        }
        Customer customer = dao.getByUsername(username);
        CustomerTO customerTO = mapper.map(customer, CustomerTO.class);
        return customerTO;
    }
}
