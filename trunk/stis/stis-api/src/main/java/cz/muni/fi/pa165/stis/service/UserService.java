package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.dto.UserTO;

/**
 * User service layer for CRUD and other methods 
 * manipulating User entity using User transfer objects
 * 
 * @author Michal Toth
 */
public interface UserService {

    void create(UserTO user);

    void remove(UserTO user);

    void update(UserTO user);

    UserTO get(Long id);

    UserTO getByUsername(String username);

    boolean availableUsername(String username);

    boolean isAdmin(UserTO user);

    void makeAdmin(UserTO user);
}
