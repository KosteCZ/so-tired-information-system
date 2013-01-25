package cz.muni.fi.pa165.stis.facade;

import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.dto.CustomerUserTO;
import cz.muni.fi.pa165.stis.dto.UserTO;
import java.util.List;

/**
 * Facade for merging CustomerTO and UserTO into one entity for means of easier
 * entity manipulation.
 *
 * @author Michal Toth
 */
public interface CustomerUserFacade {

    /**
     * Creates new CustomerTO and UserTO object.
     *
     * @param customerTO Customer transfer object
     * @param userTO User transfer object
     */
    void create(CustomerTO customerTO, UserTO userTO);

    /**
     * Method fetches for CustomerUserTO by it's given id.
     *
     * @param id of stored CustomerUserTO
     * @return CustomerUserTO transfer object
     */
    CustomerUserTO getByCustomerId(Long id);

    /**
     * Finds CustomerUserTO by username
     *
     * @param username lookup parameter for finding user
     * @return found CustomerUserTO object with given username
     */
    CustomerUserTO getByUsername(String username);

    /**
     * Removes given CustomerUserTO entity.
     *
     * @param customerUserTO entity to be removed
     */
    void remove(CustomerUserTO customerUserTO);

    /**
     * Updates information of given CustomerUserTO entity.
     *
     * @param customerUserTO entity to be updated
     */
    void update(CustomerUserTO customerUserTO);

    /**
     * Finds and returns in list all CustomerUserTO entities in application.
     *
     * @return List of CustomerUserTO entities
     */
    List<CustomerUserTO> findAll();
}
