package cz.muni.fi.pa165.stis.dao.impl;

import cz.muni.fi.pa165.stis.dao.UserDAO;
import cz.muni.fi.pa165.stis.entity.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementation of UserDAO interface for User.
 *
 * @author Michal Toth
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        if (user.getId() != null) {
            throw new IllegalArgumentException("user.id is null");
        }
        em.persist(user);
    }

    @Override
    public void remove(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }
        User user2 = em.find(User.class, user.getId());
        em.remove(user2);
    }

    @Override
    public void update(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }

        em.merge(user);
    }

    @Override
    public User get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        User user = em.find(User.class, id);
        return user;
    }

    @Override
    public boolean availableUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("username is null");
        }

        if (username.equals("") || username.equals(" ")) {
            throw new IllegalArgumentException("username is empty");
        }

        Query query = em.createQuery("SELECT u FROM User u WHERE u.username like :username");
        query.setParameter("username", username);

        return query.getResultList().isEmpty();
    }

    @Override
    public boolean isAdmin(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }

        if (user.getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }

        Query query = em.createQuery("SELECT u FROM User u WHERE u.id = :id");
        query.setParameter("id", user.getId());

        User user2 = (User) query.getSingleResult();
        return user2.getRoleAdmin();
    }

    @Override
    public void makeAdmin(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }

        if (user.getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }

        Query query = em.createQuery("SELECT u FROM User u WHERE u.id = :id");
        query.setParameter("id", user.getId());

        User user2 = (User) query.getSingleResult();
        user2.setRoleAdmin(true);
    }

    @Override
    public List<User> findAll() {
        Query query = em.createQuery("SELECT u FROM User u");
        List<User> results = query.getResultList();

        return results;
    }
}
