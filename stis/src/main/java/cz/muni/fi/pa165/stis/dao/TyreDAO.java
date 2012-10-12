package cz.muni.fi.pa165.stis.dao;

import cz.muni.fi.pa165.stis.entity.Tyre;
import java.util.List;

/**
 * CRUD + find methods
 * 
 * @author xkoscak
 */
public interface TyreDAO {
    
    
    /**
     * Method creates new customer entity using parameter
     * tyre of type Tyre.
     * 
     * @param tyre 
     */
    void create(Tyre tyre);
    
    /**
     * Method finds customer in database using 
     * customer id 
     * 
     * @param id
     * @return tyre
     */
    Tyre get(Long id);
    
    void update(Tyre tyre);
    
    void remove(Tyre tyre);
    
    List<Tyre> findAll();
    
    Tyre findByName(String name);
    
    
}

