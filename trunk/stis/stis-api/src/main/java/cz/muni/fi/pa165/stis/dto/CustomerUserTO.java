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

    public CustomerTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerTO customer) {
        this.customer = customer;
    }

    public UserTO getUser() {
        return user;
    }

    public void setUser(UserTO user) {
        this.user = user;
    }
    
    
}
