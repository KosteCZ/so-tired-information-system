package cz.muni.fi.pa165.stis.service.impl;

import cz.muni.fi.pa165.stis.dao.ExtraServiceDAO;
import cz.muni.fi.pa165.stis.dto.ExtraServiceTO;
import cz.muni.fi.pa165.stis.entity.ExtraService;
import cz.muni.fi.pa165.stis.service.ExtraServiceService;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
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
    
    @Autowired
    private DozerBeanMapper mapper;

    @Transactional
    public void create(ExtraServiceTO extraService) {
        ExtraService es = mapper.map(extraService, ExtraService.class);
        extraServiceDAO.create(es);
    }

    @Transactional(readOnly = true)
    public ExtraServiceTO get(Long id) {
        return mapper.map(extraServiceDAO.get(id), ExtraServiceTO.class);
    }

    @Transactional
    public void update(ExtraServiceTO extraService) {
        ExtraService es = mapper.map(extraService, ExtraService.class);
        extraServiceDAO.update(es);
    }

    @Transactional
    public void remove(ExtraServiceTO extraService) {
        ExtraService es = mapper.map(extraService, ExtraService.class);
        extraServiceDAO.remove(es);
    }

    @Transactional(readOnly = true)
    public List<ExtraServiceTO> findAll() {
        List<ExtraService> result = extraServiceDAO.findAll();
        List<ExtraServiceTO> ret = new ArrayList<ExtraServiceTO>();
        for (ExtraService es : result) {
            ret.add(mapper.map(es, ExtraServiceTO.class));
        }
        
        return ret;
    }

    @Transactional(readOnly = true)
    public List<ExtraServiceTO> findByName(String name) {
        List<ExtraService> result = extraServiceDAO.findByName(name);
        List<ExtraServiceTO> ret = new ArrayList<ExtraServiceTO>();
        for (ExtraService es : result) {
            ret.add(mapper.map(es, ExtraServiceTO.class));
        }
        
        return ret;
    }
    
}
