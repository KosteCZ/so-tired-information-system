package cz.muni.fi.pa165.stis.dao.impl;

import cz.muni.fi.pa165.stis.dao.OrderDAO;
import cz.muni.fi.pa165.stis.entity.Customer;
import cz.muni.fi.pa165.stis.entity.Order;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author xmravec1
 */
public class OrderDAOImpl implements OrderDAO{
    
    private EntityManagerFactory emf;
    
    public void setEntityManager(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void create(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("order is null");
        }
        if (order.getId() != null) {
            throw new IllegalArgumentException("order.id is not null");
        }
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Order get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        Order result = null;
        
        EntityManager em = emf.createEntityManager();
        result = em.find(Order.class, id);
        em.close();
        
        return result;
    }

    @Override
    public void update(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("order is null");
        }
        if (order.getId() == null) {
            throw new IllegalArgumentException("order.id is null");
        }
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(order);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void remove(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("order is null");
        }
        if (order.getId() == null) {
            throw new IllegalArgumentException("order.id is null");
        }
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Order toRemove = em.find(Order.class, order.getId());
        if (toRemove == null) {
            throw new IllegalArgumentException("given order doesn't exist");
        }
        em.remove(toRemove);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Order> findAll() {
        List<Order> result = new ArrayList<Order>();
        //
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT o FROM Order o");
        result = (List<Order>) query.getResultList();
        
        em.close();
        
        return result;
    }

    @Override
    public List<Order> findByCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer is null");
        }
        if (customer.getId() == null) {
            throw new IllegalArgumentException("customer's id is null");
        }
        List<Order> result = new ArrayList<Order>();
        //
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT o FROM Order o WHERE o.customer.id = :id").setParameter("id", customer.getId());
        result = (List<Order>) query.getResultList();
        em.close();
        
        return result;
    }
}
