package cz.muni.fi.pa165.stis.service.impl;

import cz.muni.fi.pa165.stis.dao.UserDAO;
import cz.muni.fi.pa165.stis.dto.UserTO;
import cz.muni.fi.pa165.stis.entity.User;
import cz.muni.fi.pa165.stis.service.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer implementation for DAO methods using
 * transfer object - UserTO and mapping it to User
 * 
 * @author Michal Toth
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO dao;
    private CustomerServiceImpl csi;
    @Autowired
    private DozerBeanMapper mapper;

    @PreAuthorize("isAuthenticated()")
    @Transactional
    @Override
    public void create(UserTO user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }

        User user2 = mapper.map(user, User.class);
        dao.create(user2);
        user.setId(user2.getId());
    }

    @Transactional
    @Override
    public void remove(UserTO user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }

        User user2 = mapper.map(user, User.class);
        dao.remove(user2);
    }

    @Transactional
    @Override
    public void update(UserTO user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }
        User user2 = mapper.map(user, User.class);
        dao.update(user2);
    }

    @Transactional(readOnly = true)
    @Override
    public UserTO get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("null id");
        }
        User user = dao.get(id);
        if (user == null) {
            return null;
        }
        
        return mapper.map(user, UserTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean availableUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("username is null");
        }
        if (username.equals("") || username.equals(" ")) {
            throw new IllegalArgumentException("username is empty");
        }
        
        return dao.availableUsername(username);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isAdmin(UserTO user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }
        User user2 = mapper.map(user, User.class);
        
        return dao.isAdmin(user2);
    }

    @Transactional
    @Override
    public void makeAdmin(UserTO user) {
        if (user == null) {
            throw new IllegalArgumentException("user is null");
        }
        if (user.getId() == null) {
            throw new IllegalArgumentException("user.id is null");
        }
        User user2 = mapper.map(user, User.class);
        dao.makeAdmin(user2);
    }
}
