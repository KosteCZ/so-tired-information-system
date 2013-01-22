package cz.muni.fi.pa165.stis.dao.impl;

import cz.muni.fi.pa165.stis.dao.UserDAO;
import cz.muni.fi.pa165.stis.entity.UserEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 * Implementation of UserDAO interface for UserEntity.
 * 
 * @author michalxo
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(UserEntity user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }
        em.merge(user);
    }

    @Override
    public void remove(UserEntity user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }
        UserEntity user2 = em.find(UserEntity.class, user.getId());
        em.remove(user2);
    }

    @Override
    public void update(UserEntity user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }

        em.merge(user);
    }

    @Override
    public UserEntity get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        UserEntity user = em.find(UserEntity.class, id);
        return user;
    }

    @Override
    public boolean availableUsername(String userName) {
        if (userName == null) {
            throw new IllegalArgumentException("userName is null");
        }

        Query query = em.createQuery("SELECT u FROM UserEntity u WHERE u.username like :userName");
        query.setParameter("userName", userName);
        
        return query.getResultList().isEmpty();
    }

    @Override
    public boolean isAdmin(UserEntity user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        
        if (user.getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }

        Query query = em.createQuery("SELECT u FROM UserEntity u WHERE u.id = :id");
        query.setParameter("id", user.getId());
        
        UserEntity user2 = (UserEntity) query.getSingleResult();        
        return user2.getRoleAdmin();
    }

    @Override
    public void makeAdmin(UserEntity user) {
         if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        
        if (user.getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }

        Query query = em.createQuery("SELECT u FROM UserEntity u WHERE u.id = :id");
        query.setParameter("id", user.getId());
        
        UserEntity user2 = (UserEntity) query.getSingleResult();
        user2.setRoleAdmin(true);        
    }
            
}
