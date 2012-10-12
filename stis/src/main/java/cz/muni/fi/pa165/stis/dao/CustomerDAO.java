package cz.muni.fi.pa165.stis.dao;

import cz.muni.fi.pa165.stis.entity.Customer;
import java.util.List;

/**
 * Basic CRUD functionality methods for Customer database Customer operation.
 * 
 * @author michalxo
 */
public interface CustomerDAO {
    
    
    /**
     * Method creates new customer entity using parameter
     * customer of type Customer.
     * 
     * @param customer 
     */
    void create(Customer customer);
    
    /**
     * Method finds customer in database using 
     * customer id.
     * 
     * @param id - unique identification Long number 
     * @return customer 
     */
    Customer get(Long id);
    
    /**
     * Method updates given customer information in database.
     * 
     * @param customer - Customer type parameter to be updated
     */
    void update(Customer customer);
    
    /**
     * Removes given customer from database.
     * 
     * @param customer - Customer type parameter to be removed
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
     * @param firstName, lastName
     * @return List of customers with given first and last name.
     */
    List<Customer> findByName(String lastName, String firstName);
    
}
