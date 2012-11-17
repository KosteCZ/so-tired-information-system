package cz.muni.fi.pa165.stis.dao;

import cz.muni.fi.pa165.stis.entity.Customer;
import java.util.List;

/**
 * Data Object Access interface for Customer entity.
 * Basic CRUD functionality methods for Customer database Customer operation.
 * 
 * @author Michal Toth
 */
public interface CustomerDAO {
    
    
    /**
     * Method creates new customer entity using parameter
     * customer of type Customer.
     * 
     * @param customer 
     * @throws IllegalArgumentException when customer or customer.id is null
     */
    void create(Customer customer);
    
    /**
     * Method finds customer in database using 
     * customer id.
     * 
     * @param id - unique identification Long number 
     * @throws IllegalArgumentException when id is null
     * @return customer 
     */
    Customer get(Long id);
    
    /**
     * Method updates given customer information in database.
     * 
     * @param customer - Customer type parameter to be updated
     * @throws IllegalArgumentException when customer or customer.id is null
     */
    void update(Customer customer);
    
    /**
     * Removes given customer from database.
     * 
     * @param customer - Customer type parameter to be removed
     * @throws IllegalArgumentException when customer or customer.id is null
     */
    void remove(Customer customer);
    
    /**
     * Method returns all customers from database.
     * 
     * @return List of all customers
     */
    List<Customer> findAll();
    
    
    /**
     * Method looks for given last and first name of customer in database
     * and returns collection of found customers with given name.
     * 
     * @param firstName given name of customer, lastName last name of customer
     * @throws IllegalArgumentException when firstName and lastName are both null
     * @return List of customers with given first and last name.
     */
    List<Customer> findByName(String firstName, String lastName);
    
}
