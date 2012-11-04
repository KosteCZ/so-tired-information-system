package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.dto.OrderTO;
import cz.muni.fi.pa165.stis.entity.Customer;
import java.util.List;

/**
 *
 * @author Peter Mravec
 */
public interface OrderService {
    
    void create(OrderTO order);
    OrderTO get(Long id);
    void update(OrderTO order);
    void remove(OrderTO order);
    List<OrderTO> findAll();
    List<OrderTO> findByCustomer(Customer customer);
}
