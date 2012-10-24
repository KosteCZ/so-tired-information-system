package cz.muni.fi.pa165.stis.service.impl;

import cz.muni.fi.pa165.stis.dao.ExtraServiceDAO;
import cz.muni.fi.pa165.stis.entity.ExtraService;
import cz.muni.fi.pa165.stis.service.ExtraServiceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dusan Svancara
 */
@Service
public class ExtraServiceServiceImpl implements ExtraServiceService {
    
    @Autowired
    private ExtraServiceDAO extraServiceDAO;

    @Transactional
    public void create(ExtraService extraService) {
        extraServiceDAO.create(extraService);
    }

    @Transactional(readOnly = true)
    public ExtraService get(Long id) {
        return extraServiceDAO.get(id);
    }

    @Transactional
    public void update(ExtraService extraService) {
        extraServiceDAO.update(extraService);
    }

    @Transactional
    public void remove(ExtraService extraService) {
        extraServiceDAO.remove(extraService);
    }

    @Transactional(readOnly = true)
    public List<ExtraService> findAll() {
        return extraServiceDAO.findAll();
    }

    @Transactional(readOnly = true)
    public List<ExtraService> findByName(String name) {
        return extraServiceDAO.findByName(name);
    }
    
}
