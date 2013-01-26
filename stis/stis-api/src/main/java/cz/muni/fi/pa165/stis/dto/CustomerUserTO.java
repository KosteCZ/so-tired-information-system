package cz.muni.fi.pa165.stis.dto;

import java.io.Serializable;

/**
 *
 * @author Michal Toth
 */
public class CustomerUserTO implements Serializable {
            
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (this.customer != null ? this.customer.hashCode() : 0);
        hash = 83 * hash + (this.user != null ? this.user.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CustomerUserTO other = (CustomerUserTO) obj;
        if (this.customer != other.customer && (this.customer == null || !this.customer.equals(other.customer))) {
            return false;
        }
        if (this.user != other.user && (this.user == null || !this.user.equals(other.user))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CustomerUserTO{" + "customer=" + customer + ", user=" + user + '}';
    }
    
    
}
