package cz.muni.fi.pa165.stis.dao;

import cz.muni.fi.pa165.stis.entity.Customer;
import cz.muni.fi.pa165.stis.entity.Order;
import java.util.List;

/**
 *
 * @author xmravec1
 */
public interface OrderDAO {
    
    /**
     * Method creates new order entity using parameter
     * order of type Order.
     * 
     * @param order 
     */
    void create(Order order);
    
    /**
     * Method finds order in database using 
     * order id.
     * 
     * @param id - unique identification Long number 
     * @return order 
     */
    Order get(Long id);
    
    /**
     * Method updates given order information in database.
     * 
     * @param order - Order type parameter to be updated
     */
    void update(Order order);
    
    /**
     * Removes given order from database.
     * 
     * @param order - Order type parameter to be removed
     */
    void remove(Order order);
    
    /**
     * Method returns all orders from database.
     * 
     * @return Collection of all orders
     */
    List<Order> findAll();
    
    /**
     * Method looks for given customer his orders in database
     * and returns collection of found orders with given customer.
     * 
     * @param name
     * @return Collection of orders with given customer
     */
    List<Order> findByCustomer(Customer customer);
}
