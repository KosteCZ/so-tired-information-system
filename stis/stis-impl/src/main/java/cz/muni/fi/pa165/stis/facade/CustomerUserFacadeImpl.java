/*
  
 public CustomerUserTO get(Long id) {
        
    }
    
    
 */
package cz.muni.fi.pa165.stis.facade;

import cz.muni.fi.pa165.stis.dao.CustomerDAO;
import cz.muni.fi.pa165.stis.dao.UserDAO;
import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.dto.CustomerUserTO;
import cz.muni.fi.pa165.stis.dto.UserTO;
import cz.muni.fi.pa165.stis.entity.Customer;
import cz.muni.fi.pa165.stis.entity.User;
import cz.muni.fi.pa165.stis.service.CustomerService;
import cz.muni.fi.pa165.stis.service.UserService;
import java.util.List;

/**
 *
 * @author michalxo
 */
public class CustomerUserFacadeImpl implements CustomerUserFacade {

    private CustomerService cservice;    
    private UserService uservice;
    
    @Override
    public void create(CustomerTO customerTO, UserTO userTO) {
        if (customerTO == null) {
            throw new IllegalArgumentException("customer is null");
        }
        if (userTO == null) {
            throw new IllegalArgumentException("user is null");
        }        
        
        cservice.create(customerTO);
        uservice.create(userTO);        
    }

    
    @Override
    public CustomerUserTO get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        CustomerTO cto = cservice.get(id);
        UserTO uto = cto.getUser();        
        return new CustomerUserTO(cto, uto);
    }

    @Override
    public void remove(CustomerUserTO customerUserTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(CustomerUserTO customerUserTO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<CustomerUserTO> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
