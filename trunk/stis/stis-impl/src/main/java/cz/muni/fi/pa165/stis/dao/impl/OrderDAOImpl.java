package cz.muni.fi.pa165.stis.dao.impl;

import cz.muni.fi.pa165.stis.dao.OrderDAO;
import cz.muni.fi.pa165.stis.entity.Customer;
import cz.muni.fi.pa165.stis.entity.Order;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Peter Mravec
 */
@Repository
public class OrderDAOImpl implements OrderDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("order is null");
        }
        if (order.getId() != null) {
            throw new IllegalArgumentException("order.id is not null");
        }
        entityManager.persist(order);
    }

    @Override
    public Order get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        return entityManager.find(Order.class, id);
    }

    @Override
    public void update(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("order is null");
        }
        if (order.getId() == null) {
            throw new IllegalArgumentException("order.id is null");
        }

        entityManager.merge(order);
    }

    @Override
    public void remove(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("order is null");
        }
        if (order.getId() == null) {
            throw new IllegalArgumentException("order.id is null");
        }

        Order toRemove = entityManager.find(Order.class, order.getId());
        if (toRemove == null) {
            throw new IllegalArgumentException("given extraService doesn't exist");
        }
        entityManager.remove(toRemove);
    }

    @Override
    public List<Order> findAll() {
        TypedQuery<Order> tq = entityManager.createQuery("FROM Order", Order.class);
        return tq.getResultList();
    }

    @Override
    public List<Order> findByCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer is null");
        }
        if (customer.getId() == null) {
            throw new IllegalArgumentException("customer's id is null");
        }

        TypedQuery<Order> tq = entityManager.createQuery("SELECT o FROM Order o WHERE o.customer.id = :id", Order.class);
        tq.setParameter("id", customer.getId());

        return tq.getResultList();
    }
}
