package cz.muni.fi.pa165.stis.facade.impl;

import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.dto.CustomerUserTO;
import cz.muni.fi.pa165.stis.dto.UserTO;
import cz.muni.fi.pa165.stis.facade.CustomerUserFacade;
import cz.muni.fi.pa165.stis.service.CustomerService;
import cz.muni.fi.pa165.stis.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Facade implementation for CustomerTO and UserTO entities
 * using CustomerUserTO and their appropriate services.
 * 
 * @author Michal Toth
 */
@Service
public class CustomerUserFacadeImpl implements CustomerUserFacade {

    @Autowired
    private CustomerService cservice;   
    @Autowired
    private UserService uservice;
    
    @Transactional
    @Override
    public void create(CustomerTO customerTO, UserTO userTO) {
        if (customerTO == null) {
            throw new IllegalArgumentException("customer is null");
        }
        if (userTO == null) {
            throw new IllegalArgumentException("user is null");
        }                
        if (customerTO.getId() != null) {
            throw new IllegalArgumentException("customerTO.id is null");
        }
        if (userTO.getId() != null) {
            throw new IllegalArgumentException("userTO.id is null");
        }        
        
        uservice.create(userTO);
        customerTO.setUser(userTO);
        cservice.create(customerTO);
    }
    
    @Transactional(readOnly=true)
    @Override
    public CustomerUserTO getByCustomerId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        CustomerTO cto = cservice.get(id);
        UserTO uto = cto.getUser();        
        return new CustomerUserTO(cto, uto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or "
    + "(hasRole('ROLE_USER') and principal.username == #customerUserTO.user.username)")
    @Transactional
    @Override
    public void remove(CustomerUserTO customerUserTO) {
        if (customerUserTO == null) {
            throw new IllegalArgumentException("customerUserTO is null");
        }
        
        if (customerUserTO.getCustomer().getId() == null) {
            throw new IllegalArgumentException("customer.id is null");
        }
        if (customerUserTO.getUser().getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }
        
        uservice.remove(customerUserTO.getUser());
        cservice.remove(customerUserTO.getCustomer());        
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or "
    + "(hasRole('ROLE_USER') and principal.username == #customerUserTO.user.username)")
    @Transactional
    @Override
    public void update(CustomerUserTO customerUserTO) {
        if (customerUserTO == null) {
            throw new IllegalArgumentException("customerUserTO is null");
        }
        
        if (customerUserTO.getCustomer().getId() == null) {
            throw new IllegalArgumentException("customer.id is null");
        }
        if (customerUserTO.getUser().getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }
        
        UserTO userTO = customerUserTO.getUser();        
        CustomerTO customerTO = customerUserTO.getCustomer();
        customerTO.setUser(userTO);
        
        uservice.update(userTO);
        cservice.update(customerTO);
    }

    @Transactional(readOnly=true)
    @Override
    public List<CustomerUserTO> findAll() {
        List<CustomerTO> customerTOList = cservice.findAll();
        List<CustomerUserTO> cuTOList = new ArrayList<>();
        for (CustomerTO c : customerTOList) {
            cuTOList.add(new CustomerUserTO(c, c.getUser()));
        }
        return cuTOList;
    }

    @Override
    public CustomerUserTO getByUsername(String username) {
        UserTO userTO = uservice.getByUsername(username);        
        CustomerTO customerTO = cservice.getByUsername(username);
        
        return new CustomerUserTO(customerTO, userTO);
    }
   
    
}
