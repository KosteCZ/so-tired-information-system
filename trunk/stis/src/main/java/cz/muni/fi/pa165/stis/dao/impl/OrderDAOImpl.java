package cz.muni.fi.pa165.stis.dao.impl;

import cz.muni.fi.pa165.stis.dao.OrderDAO;
import cz.muni.fi.pa165.stis.entity.Customer;
import cz.muni.fi.pa165.stis.entity.Order;
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
        
        EntityManager em = emf.createEntityManager();
        return em.find(Order.class, id);
    }

    @Override
    public void update(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("order is null");
        }
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(order);
        em.refresh(order);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void remove(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("order is null");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Order toRemove = em.find(Order.class, order.getId());
        if (toRemove == null) {
            throw new IllegalArgumentException("given extraService doesn't exist");
        }
        em.remove(toRemove);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Order> findAll() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT o FROM Order o");
        em.close();
        return (List<Order>) query.getResultList();
    }

    @Override
    public List<Order> findByCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer is null");
        }
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT o FROM Order o WHERE o.customer.id = :id").setParameter("id", customer.getId());
        em.close();
        return (List<Order>) query.getResultList();
    }
}
