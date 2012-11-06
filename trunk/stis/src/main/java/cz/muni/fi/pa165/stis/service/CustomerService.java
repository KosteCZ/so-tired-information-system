package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.dto.CustomerTO;
import java.util.List;

/**
 *
 * @author Dusan Svancara
 */
public interface CustomerService {
    
    /**
     * Creates new customer.
     * 
     * @param customer 
     * @throws IllegalArgumentException when customer or customer.id is null
     */
    void create(CustomerTO customer);
    
    /**
     * Finds customer by given id.
     * 
     * @param id - unique identification Long number 
     * @throws IllegalArgumentException when id is null
     * @return customer transfer object
     */
    CustomerTO get(Long id);
    
    /**
     * Updates given customer.
     * 
     * @param customer - CustomerTO type parameter to be updated
     * @throws IllegalArgumentException when customer or customer.id is null
     */
    void update(CustomerTO customer);
    
    /**
     * Removes given customer from database.
     * 
     * @param customer - CustomerTO type parameter to be removed
     * @throws IllegalArgumentException when customer or customer.id is null
     */
    void remove(CustomerTO customer);
    
    /**
     * Returns all customers from database.
     * 
     * @return List of all customers
     */
    List<CustomerTO> findAll();
    
    
    /**
     * Looks for given last and first name of customer in database
     * and returns collection of found customer transfer objects with given name.
     * 
     * @param firstName given name of customer, lastName last name of customer
     * @throws IllegalArgumentException when firstName and lastName are both null
     * @return List of customers with given first and last name.
     */
    List<CustomerTO> findByName(String firstName, String lastName);
}
