package cz.muni.fi.pa165.stis.dao.impl;

import cz.muni.fi.pa165.stis.dao.CustomerDAO;
import cz.muni.fi.pa165.stis.entity.Customer;
import cz.muni.fi.pa165.stis.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * Class implements CustomerDAO interface functionality.
 *
 * @author Michal Toth
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer is null");
        }
        if (customer.getId() != null) {
            throw new IllegalArgumentException("customer.id is not null");
        }

        em.persist(customer);
    }

    @Override
    public Customer get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("null id");
        }
        Customer customer = em.find(Customer.class, id);
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

        em.merge(customer);
    }

    @Override
    public void remove(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer is null");
        }
        if (customer.getId() == null) {
            throw new IllegalArgumentException("customer.id is null");
        }
        Customer c = em.find(Customer.class, customer.getId());
        em.remove(c);
    }

    @Override
    public List<Customer> findAll() {
        Query query = em.createQuery("SELECT c FROM Customer c");
        List<Customer> results = query.getResultList();

        return results;
    }

    @Override
    public List<Customer> findByName(String firstName, String lastName) {
        boolean firstNameNull = (firstName == null);
        boolean lastNameNull = (lastName == null);
        if (lastNameNull && firstNameNull) {
            throw new IllegalArgumentException("null lastName and firstName");
        }

        Query query;
        if (firstNameNull) {
            // firstName is null, query for lastName only
            query = em.createQuery("SELECT c FROM Customer c WHERE c.lastName like :lastName");
            query.setParameter("lastName", lastName);
        } else if (lastNameNull) {
            query = em.createQuery("SELECT c FROM Customer c WHERE c.firstName like :firstName");
            query.setParameter("firstName", firstName);
        } else {
            query = em.createQuery("SELECT c FROM Customer c WHERE c.firstName like :firstName AND c.lastName like :lastName");
            query.setParameter("lastName", lastName);
            query.setParameter("firstName", firstName);
        }

        List<Customer> results = query.getResultList();
        return results;
    }

    @Override
    public User getUser(Customer customer) {
        Query query;
        query = em.createQuery("SELECT u FROM UserEntity u WHERE u.id = :id ");
        query.setParameter("id", customer.getUser().getId());

        User user = (User) query.getSingleResult();
        return user;

    }

//    @Override
//    public void getUser(Customer customer, User user) {
//        Query query;
//        query = em.createQuery("");
//    }
}