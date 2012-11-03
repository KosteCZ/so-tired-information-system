package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.entity.Tyre;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author xsvanca2
 */
@Local
public interface TyreServiceLocal {
    
    void create(Tyre tyre);
    Tyre get(Long id);
    void update(Tyre tyre);
    void remove(Tyre tyre);
    List<Tyre> findAll();
    List<Tyre> findByName(String name);
}
