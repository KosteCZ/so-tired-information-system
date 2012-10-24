package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.entity.ExtraService;
import java.util.List;

/**
 *
 * @author Dusan Svancara
 */
public interface ExtraServiceService {
    void create(ExtraService extraService);
    ExtraService get(Long id);
    void update(ExtraService extraService);
    void remove(ExtraService extraService);
    
    List<ExtraService> findAll();
    List<ExtraService> findByName(String name);
}
