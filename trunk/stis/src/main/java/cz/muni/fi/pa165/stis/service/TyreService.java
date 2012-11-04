package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.dto.TyreTO;
import java.util.List;


/**
  * Service layer interface for Tyre CRUD and find functionality 
  * using transfer object.
  * 
  * @author Michal Toth
  */
public interface TyreService {

        /*
        * Creates tyre service from tyre transfer object.
        *
        * @param tyre Tyre transfer object to create
        */
        void create(TyreTO tyre);

        /**
         * Returns tyre tranfer object using tyre id.
         *
         * @param id Id of the tyre service
         * @return Tyre transfer object with given id
         */
        TyreTO get(Long id);

        /**
         * Updates tyre service information from transfer object.
         *
         * @param tyre Tyre transfer object to be updated
         */
        void update(TyreTO tyre);

        /**
         * Removes given tyre transfer object from database.
         *
         * @param tyre Tyre transfer object to be removed
         */
        void remove(TyreTO tyre);

        /**
         * Method returns list of all tyre transfer objects.
         *
         * @return List of all tyre transfer objects.
         */
        List<TyreTO> findAll();

        /**
         * Finds all tyre transfer objects with same name.
         *
         * @param name Name of tyre
         * @return List of tyre transfer objects with same name
         */
        List<TyreTO> findByName(String name);
}
