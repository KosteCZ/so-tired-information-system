package cz.muni.fi.pa165.stis.dao;

import cz.muni.fi.pa165.stis.entity.Tyre;
import java.util.List;

/**
 * This is Data Access Object interface for Tyre entity. 
 * It describes CRUD methods and also few methods for finding specific
 * entities.
 *
 * @author Jan Koščák (xkoscak@gmail.com)
 */
public interface TyreDAO {

    /*
     * This constructor creates new persist tyre entity using parameter tyre of
     * type Tyre. Also sets generated ID to the entity.
     *
     * @param tyre Tyre entity to be persisted
     * @throws IllegalArgumentException if tyre is null and/or if tyre has null
     * ID
     */
    void create(Tyre tyre);

    /**
     * Finds tyre in database using tyre id.
     *
     * @param id id of entity
     * @return Certain tyre or null if not found
     * @throws IllegalArgumentException if id is null
     */
    Tyre get(Long id);

    /**
     * Updates extra tyre information. Its ID must be non-null.
     * 
     * @param tyre Tyre information to be updated
     * @throws IllegalArgumentException if tyre is null and/or its id is null                  
     */
    void update(Tyre tyre);

    /**
     * Removes given tyre from our database.
     * 
     * @param tyre Certain tyre to be removed
     */
    void remove(Tyre tyre);

    /**
     * This method finds all tyres in our database.
     * 
     * @return List of all tyres
     */
    List<Tyre> findAll();

     /**
     * Finds all tyres with same name.
     * 
     * @param name Name of tyre
     * @return List of tyres with same name
     * @throws IllegalArgumentException if name is null
     */
    List<Tyre> findByName(String name);
}
