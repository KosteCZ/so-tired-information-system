package cz.muni.fi.pa165.stis.facade;

import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.dto.CustomerUserTO;
import cz.muni.fi.pa165.stis.dto.UserTO;
import java.util.List;

/**
 *
 * @author michalxo
 */
public interface CustomerUserFacade {
    
    public void create(CustomerTO customerTO, UserTO userTO);
    
    public CustomerUserTO get(Long id);    
        
    public void remove(CustomerUserTO customerUserTO);
    
    public void update(CustomerUserTO customerUserTO);
    
    public List<CustomerUserTO> findAll();
    
}
