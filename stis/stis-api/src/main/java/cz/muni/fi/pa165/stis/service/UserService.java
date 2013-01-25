package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.dto.UserTO;

/**
 * User service layer for CRUD and other methods 
 * manipulating User entity using User transfer objects
 * 
 * @author Michal Toth
 */
public interface UserService {

    /**
     * Creates new UserTO object
     * 
     * @param user object to be created
     */
    void create(UserTO user);

    /**
     * Removes userTO object.
     * 
     * @param user object to be removed
     */
    void remove(UserTO user);

    /**
     *  Update userTO object
     * 
     * @param user object to be updated
     */
    void update(UserTO user);

    /**
     * Returns UserTO object with given ID
     * 
     * @param id return UserTO with given ID
     * @return UserTO object
     */
    UserTO get(Long id);

    /**
     * Searches and returns UserTO entity with
     * provided username.
     * 
     * @param username find UserTO with given username
     * @return UserTO with given username
     */
    UserTO getByUsername(String username);

    /**
     * Check for available/free username.
     * Username is unique parameter in User entity.
     * 
     * @return true if username is free for use
     */
    boolean availableUsername(String username);

    /**
     * Check whether user is admin or not
     * 
     * @param user to be checked for admin yes/no
     * @return true if user is admin
     */
    boolean isAdmin(UserTO user);

    /**
     * Make user an admin
     * 
     * @param user to have change role to admin
     */
    void makeAdmin(UserTO user);
}
