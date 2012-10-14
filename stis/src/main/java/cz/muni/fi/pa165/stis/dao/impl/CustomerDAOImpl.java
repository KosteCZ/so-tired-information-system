package cz.muni.fi.pa165.stis.dao.impl;

import cz.muni.fi.pa165.stis.dao.CustomerDAO;
import cz.muni.fi.pa165.stis.entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 * Class implements CustomerDAO interface functionality.
 *
 * @author Michal Toth
 */
public class CustomerDAOImpl implements CustomerDAO {

    private EntityManagerFactory emf;

    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void create(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer is null");
        }
        if (customer.getId() != null) {
            throw new IllegalArgumentException("customer.id is not null");
        }
        
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Customer get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("null id");
        }
        EntityManager em = emf.createEntityManager();
        Customer customer = em.find(Customer.class, id);
        em.close();
        return customer;

    }

    @Override
    public void update(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer is null");
        }
        if (customer.getId() == null) {
            throw new IllegalArgumentException("customer.id is null");
        }

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //em.merge(em.find(Customer.class, customer));
        em.merge(customer);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void remove(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer is null");
        }
        if (customer.getId() == null) {
            throw new IllegalArgumentException("customer.id is null");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(em.find(Customer.class, customer.getId()));
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Customer> findAll() {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT c FROM Customer c");
        List<Customer> results = query.getResultList();
        em.close();
        return results;
    }

    @Override
    public List<Customer> findByName(String lastName, String firstName) {
        boolean fn;
        boolean ln;
        if ((ln = (lastName == null)) || (fn = (firstName == null))) {
//        if ((ln = (lastName == null || lastName.equals(""))) || (fn = (firstName == null || firstName.equals("")))) {
            throw new IllegalArgumentException("null lastName and firstName");
        }

        Query query;
        EntityManager em = emf.createEntityManager();
        if (fn) {
            // firstName is null, query for lastName only
            query = em.createQuery("SELECT c FROM Customer c WHERE c.lastName like :lastName");
            query.setParameter("lastName", lastName);
        } else if (ln) {
            query = em.createQuery("SELECT c FROM Customer c WHERE c.firstName like :firstName");
            query.setParameter("firstName", firstName);
        } else {
            query = em.createQuery("SELECT c FROM Customer c WHERE c.firstName like :firstName AND c.lastName like :lastName");
            query.setParameter("lastName", lastName);
            query.setParameter("firstName", firstName);
        }

        List<Customer> results = query.getResultList();
        em.close();
        return results;
    }
}