package cz.muni.fi.pa165.stis.dto;

/**
 *
 * @author michalxo
 */
public class CustomerUserTO {
            
    private CustomerTO customer;
    private UserTO user;
    
    public CustomerUserTO(CustomerTO customer, UserTO user) {
        this.customer = customer;
        this.user = user;
    }           
    
}
