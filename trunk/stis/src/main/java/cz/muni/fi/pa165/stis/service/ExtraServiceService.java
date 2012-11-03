package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.dto.ExtraServiceTO;
import java.util.List;

/**
 *
 * @author Dusan Svancara
 */
public interface ExtraServiceService {
    void create(ExtraServiceTO extraService);
    ExtraServiceTO get(Long id);
    void update(ExtraServiceTO extraService);
    void remove(ExtraServiceTO extraService);
    
    List<ExtraServiceTO> findAll();
    List<ExtraServiceTO> findByName(String name);
}
