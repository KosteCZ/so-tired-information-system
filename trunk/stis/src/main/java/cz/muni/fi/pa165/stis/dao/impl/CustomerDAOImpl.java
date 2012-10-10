package cz.muni.fi.pa165.stis.dao.impl;

import cz.muni.fi.pa165.stis.dao.CustomerDAO;
import cz.muni.fi.pa165.stis.entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author michalxo
 */
public class CustomerDAOImpl implements CustomerDAO {
    private EntityManagerFactory emf;
    
    public void setEntityManager(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    @Override
    public void create(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Customer get(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Customer> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Customer findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
