package cz.muni.fi.pa165.stis.dao;

import cz.muni.fi.pa165.stis.entity.ExtraService;
import java.util.List;

/**
 * Data Access Object interface for ExtraService entity. 
 * It prescribes CRUD methods as well as couple methods for finding specific
 * entities.
 *
 * @author Dusan Svancara
 */
public interface ExtraServiceDAO {
    
    /**
     * Persists ExtraService entity and sets generated ID to the entity. Its ID 
     * must be null before.
     * 
     * @param extraService ExtraService entity to be persisted
     * @throws IllegalArgumentException if extraService is null or 
     *              if extraService has non-null ID
     */
    void create(ExtraService extraService);
    
    /**
     * Gets extra service by its ID.
     * 
     * @param id id of the desired entity
     * @return Desired entity, null if not found
     * @throws IllegalArgumentException if id is null
     */
    ExtraService get(Long id);
    
    /**
     * Updates extra service. Its ID must be non-null.
     * 
     * @param extraService Extra service to be updated
     * @throws IllegalArgumentException if extraService is null or its id 
     *              is null                  
     */
    void update(ExtraService extraService);
    
    /**
     * Removes extra service.
     * 
     * @param extraService Extra service to be removed
     * @throws IllegalArgumentException if extraService is null
     */
    void remove(ExtraService extraService);
    
    /**
     * Finds all extra services.
     * 
     * @return List of all extra services
     */
    List<ExtraService> findAll();
    
    /**
     * Finds all extra services with matching name.
     * 
     * @param name name
     * @return List of extra services with matching name.
     * @throws IllegalArgumentException if name is null.
     */
    List<ExtraService> findByName(String name);
}
