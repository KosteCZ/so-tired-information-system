package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.entity.Customer;
import cz.muni.fi.pa165.stis.entity.Order;
import java.util.List;

/**
 *
 * @author Peter Mravec
 */
public interface OrderService {
    
    void create(Order order);
    Order get(Long id);
    void update(Order order);
    void remove(Order order);
    List<Order> findAll();
    List<Order> findByCustomer(Customer customer);
}
