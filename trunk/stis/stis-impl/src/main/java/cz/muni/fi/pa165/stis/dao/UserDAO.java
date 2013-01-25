package cz.muni.fi.pa165.stis.dao;

import cz.muni.fi.pa165.stis.entity.User;
import java.util.List;

/**
 * Data Object Access interface for User entity. Basic CRUD functionality
 * methods for User database User operation.
 *
 * @author Michal Toth, Peter Mravec
 */
public interface UserDAO {

    /**
     * Method creates new user entity using parameter user of type User.
     *
     * @param user
     */
    void create(User user);

    /**
     * Removes given user from database.
     *
     * @param user - User type parameter to be removed
     */
    void remove(User user);

    /**
     * Method updates given user information in database.
     *
     * @param user - User type parameter to be updated
     */
    void update(User user);

    /**
     * Method finds order in database using user id.
     *
     * @param id - unique identification Long number
     * @return user
     */
    User get(Long id);

    /**
     * Return User with given username
     *
     * @param username find user with given username
     * @return user with given username
     */
    User getByUsername(String username);
    
    /**
     * Method returns all users from database.
     *
     * @return Collection of all users
     */
    List<User> findAll();

    /**
     * Method check username in database. If it is not in database return true
     * else false.
     *
     * @param username - User's login name
     * @return
     */
    boolean availableUsername(String username);

    /**
     * Method check user. If he is admin return true else false.
     *
     * @param user - Checked user.
     * @return true or false if user is/is not admin
     */
    boolean isAdmin(User user);

    /**
     * Method gives user admin right.
     *
     * @param user - User which become admin
     */
    void makeAdmin(User user);
}
