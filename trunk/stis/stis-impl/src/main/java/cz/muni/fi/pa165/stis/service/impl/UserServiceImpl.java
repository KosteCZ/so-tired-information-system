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
 *
 * @author michalxo
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO udao;
    
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
        udao.create(user2);
        user.setId(user2.getId());
        // BLBOST, ID musi byt rovnake s Cusotmer id... ci?
    }

    @Override
    public void remove(UserTO user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(UserTO user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UserTO get(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean availableUsername(String userName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isAdmin(UserTO user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void makeAdmin(UserTO user) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
