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
            throw new IllegalArgumentException("order");
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
            throw new IllegalArgumentException("id");
        }
        
        EntityManager em = emf.createEntityManager();
        return em.find(Order.class, id);
    }

    @Override
    public void update(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("order");
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
            throw new IllegalArgumentException("order");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(order);
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Order findByOrderNumber(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
