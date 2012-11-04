package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.dto.OrderTO;
import java.util.List;

/**
 * Service class for orders. Provides basic functionality of CRUD 
 * and some find methods. It uses transfer objects.
 * 
 * @author Peter Mravec
 */
public interface OrderService {
    
    /**
     * Creates order from transfer object.
     * 
     * @param order Order transfer object to create
     * @throws IllegalArgumentException when order is null or its id is not null
     */
    void create(OrderTO order);
    
    /**
     * Returns order (transfer object) that has given id.
     * 
     * @param id Id of the order
     * @return Order with given id
     * @throws IllegalArgumentException when id is null
     */
    OrderTO get(Long id);
    
    /**
     * Updates order from transfer object.
     * 
     * @param order Transfer object of order to update
     * @throws IllegalArgumentException when order is null or its id is null
     */
    void update(OrderTO order);
    
    /**
     * Removes order represented by its transfer object.
     * 
     * @param order Transfer object of order to remove
     * @throws IllegalArgumentException when order is null or its id is null
     */
    void remove(OrderTO order);
    
    /**
     * Returns all orders as a list of transfer objects.
     * 
     * @return List of orders transfer objects
     */
    List<OrderTO> findAll();
    
    /**
     * Returns all orders with given name
     * 
     * @param customer Customer of the service(s) to be found.
     * @return List of orders transfer objects with given name
     * @throws IllegalArgumentException when customer is null
     */
    List<OrderTO> findByCustomer(CustomerTO customer);
}
