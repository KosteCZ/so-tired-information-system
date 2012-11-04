package cz.muni.fi.pa165.stis.service.impl;

import cz.muni.fi.pa165.stis.dao.OrderDAO;
import cz.muni.fi.pa165.stis.dto.OrderTO;
import cz.muni.fi.pa165.stis.entity.Customer;
import cz.muni.fi.pa165.stis.entity.Order;
import cz.muni.fi.pa165.stis.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
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
    
    @Autowired
    private DozerBeanMapper mapper;

    @Transactional
    public void create(OrderTO order) {
        Order ord = mapper.map(order, Order.class);
        orderDAO.create(ord);
    }

    @Transactional(readOnly=true)
    public OrderTO get(Long id) {
        return mapper.map(orderDAO.get(id), OrderTO.class);
    }

    @Transactional
    public void update(OrderTO order) {
        Order ord = mapper.map(order, Order.class);
        orderDAO.update(ord);
    }

    @Transactional
    public void remove(OrderTO order) {
        Order ord = mapper.map(order, Order.class);
        orderDAO.remove(ord);
    }

    @Transactional(readOnly=true)
    public List<OrderTO> findAll() {
        List<Order> result = orderDAO.findAll();
        List<OrderTO> ret = new ArrayList<OrderTO>();
        for (Order ord : result) {
            ret.add(mapper.map(ord, OrderTO.class));
        }
        return ret;
    }

    @Transactional(readOnly=true)
    public List<OrderTO> findByCustomer(Customer customer) {
        List<Order> result = orderDAO.findByCustomer(customer);
        List<OrderTO> ret = new ArrayList<OrderTO>();
        for (Order ord : result) {
            ret.add(mapper.map(ord, OrderTO.class));
        }
        return ret;
    }
    
}