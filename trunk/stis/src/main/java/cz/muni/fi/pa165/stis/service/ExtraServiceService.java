package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.dto.ExtraServiceTO;
import java.util.List;

/**
 * Service class for extra services. Provides basic functionality of CRUD 
 * and some find methods. It uses transfer objects.
 *
 * @author Dusan Svancara
 */
public interface ExtraServiceService {
    
    /**
     * Creates extra service from transfer object.
     * 
     * @param extraService Extra service transfer object to create
     * @throws IllegalArgumentException when extraService is null or its id is not null
     */
    void create(ExtraServiceTO extraService);
    
    /**
     * Returns extra service (transfer object) that has given id.
     * 
     * @param id Id of the extra service
     * @return Extra service with given id
     * @throws IllegalArgumentException when id is null
     */
    ExtraServiceTO get(Long id);
    
    /**
     * Updates extra service from transfer object.
     * 
     * @param extraService Transfer object of extra service to update
     * @throws IllegalArgumentException when extraService is null or its id is null
     */
    void update(ExtraServiceTO extraService);
    
    /**
     * Removes extra service represented by its transfer object.
     * 
     * @param extraService Transfer object of extra service to remove
     * @throws IllegalArgumentException when extraService is null or its id is null
     */
    void remove(ExtraServiceTO extraService);
    
    /**
     * Returns all extra services as a list of transfer objects.
     * 
     * @return List of extra service transfer objects
     */
    List<ExtraServiceTO> findAll();
    
    /**
     * Returns all extra services with given name
     * 
     * @param name Name of the service(s) to be found.
     * @return List of extra service transfer objects with given name
     * @throws IllegalArgumentException when name is null
     */
    List<ExtraServiceTO> findByName(String name);
}
