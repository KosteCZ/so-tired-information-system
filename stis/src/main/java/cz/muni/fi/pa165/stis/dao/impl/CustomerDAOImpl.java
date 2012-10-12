package cz.muni.fi.pa165.stis.dao.impl;

import cz.muni.fi.pa165.stis.dao.CustomerDAO;
import cz.muni.fi.pa165.stis.entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *  Class implements CustomerDAO interface functionality.
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
        if ((customer == null) || !(customer instanceof Customer)) {
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
        if ((id == null) || !(id instanceof Long)) {// (|| id. NOT IN DB!?
            throw new IllegalArgumentException("id");
        }
        
        EntityManager em = emf.createEntityManager();
        return em.find(Customer.class, id); 
        
    }

    @Override
    public void update(Customer customer) {
        // OR CUSTOMER is NOT in DB!!? -- WHEN HE HAS NO ID?
        if ((customer == null) || !(customer instanceof Customer)) {
            throw new IllegalArgumentException("customer");
        }
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(customer);
        em.refresh(customer);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void remove(Customer customer) {
        if ((customer == null) || !(customer instanceof Customer)) {
            throw new IllegalArgumentException("customer");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(customer);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Customer> findAll() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT c FROM Customer c");
        em.close();
        return (List<Customer>) query.getResultList();
    }

    @Override
    public List<Customer> findByName(String lastName) {
        if (lastName == null || lastName.equals("")) {
            throw new IllegalArgumentException("lastName");
        }
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.lastName like :lastName ");
        em.close();
        return (List<Customer>) query.getResultList();
    }
    
     @Override
    public List<Customer> findByName(String lastName, String firstName) {
        if (lastName == null || lastName.equals("")) {
            throw new IllegalArgumentException("lastName");
        }
        
        if (firstName == null || firstName.equals("")) {
            throw new IllegalArgumentException("firstName");
        }
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.lastname like :lastName AND c.firstName like :firstName");
        em.close();
        return (List<Customer>) query.getResultList();
    }
    
}