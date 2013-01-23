package cz.muni.fi.pa165.stis.service.impl;

import cz.muni.fi.pa165.stis.dao.UserDAO;
import cz.muni.fi.pa165.stis.dto.UserTO;
import cz.muni.fi.pa165.stis.entity.UserEntity;
import cz.muni.fi.pa165.stis.service.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service layer implementation for DAO methods using
 * transfer object - UserTO and mapping it to UserEntity
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

        UserEntity user2 = mapper.map(user, UserEntity.class);
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

        UserEntity user2 = mapper.map(user, UserEntity.class);
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
        UserEntity user2 = mapper.map(user, UserEntity.class);
        dao.update(user2);
    }

    @Transactional(readOnly = true)
    @Override
    public UserTO get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("null id");
        }
        UserEntity user = dao.get(id);
        if (user == null) {
            return null;
        }
        
        return mapper.map(user, UserTO.class);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean availableUsername(String userName) {
        if (userName == null) {
            throw new IllegalArgumentException("userName is null");
        }
        if (userName.equals("") || userName.equals(" ")) {
            throw new IllegalArgumentException("userName is empty");
        }
        
        return dao.availableUsername(userName);
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
        UserEntity user2 = mapper.map(user, UserEntity.class);
        
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
        UserEntity user2 = mapper.map(user, UserEntity.class);
        dao.makeAdmin(user2);
    }
}
