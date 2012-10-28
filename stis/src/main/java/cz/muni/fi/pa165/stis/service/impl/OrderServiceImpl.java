package cz.muni.fi.pa165.stis.service.impl;

import cz.muni.fi.pa165.stis.dao.OrderDAO;
import cz.muni.fi.pa165.stis.entity.Customer;
import cz.muni.fi.pa165.stis.entity.Order;
import cz.muni.fi.pa165.stis.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Peter Mravec
 */
@Service
public class OrderServiceImpl implements OrderService{
    
    @Autowired
    private OrderDAO orderDAO;

    @Transactional
    public void create(Order order) {
        orderDAO.create(order);
    }

    @Transactional(readOnly=true)
    public Order get(Long id) {
        return orderDAO.get(id);
    }

    @Transactional
    public void update(Order order) {
        orderDAO.update(order);
    }

    @Transactional
    public void remove(Order order) {
        orderDAO.remove(order);
    }

    @Transactional(readOnly=true)
    public List<Order> findAll() {
        return orderDAO.findAll();
    }

    @Transactional(readOnly=true)
    public List<Order> findByCustomer(Customer customer) {
        return orderDAO.findByCustomer(customer);
    }
    
}
