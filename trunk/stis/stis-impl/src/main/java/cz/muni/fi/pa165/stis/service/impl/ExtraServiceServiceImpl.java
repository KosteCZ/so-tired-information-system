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
    @Override
    public void create(ExtraServiceTO extraService) {
        if (extraService == null) {
            throw new IllegalArgumentException("extraService is null");
        }
        if (extraService.getId() != null) {
            throw new IllegalArgumentException("extraService.id is not null");
        }
        
        ExtraService es = mapper.map(extraService, ExtraService.class);
        extraServiceDAO.create(es);
        extraService.setId(es.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public ExtraServiceTO get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        
        ExtraService es = extraServiceDAO.get(id);
        if (es == null) {
            return null;
        }
        
        return mapper.map(es, ExtraServiceTO.class);
    }

    @Transactional
    @Override
    public void update(ExtraServiceTO extraService) {
        if (extraService == null) {
            throw new IllegalArgumentException("extraService is null");
        }
        if (extraService.getId() == null) {
            throw new IllegalArgumentException("extraService.id is null");
        }
        
        ExtraService es = mapper.map(extraService, ExtraService.class);
        extraServiceDAO.update(es);
    }

    @Transactional
    @Override
    public void remove(ExtraServiceTO extraService) {
        if (extraService == null) {
            throw new IllegalArgumentException("extraService is null");
        }
        if (extraService.getId() == null) {
            throw new IllegalArgumentException("extraService.id is null");
        }
        
        ExtraService es = mapper.map(extraService, ExtraService.class);
        extraServiceDAO.remove(es);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ExtraServiceTO> findAll() {
        List<ExtraService> result = extraServiceDAO.findAll();
        List<ExtraServiceTO> ret = new ArrayList<ExtraServiceTO>();
        for (ExtraService es : result) {
            ret.add(mapper.map(es, ExtraServiceTO.class));
        }
        
        return ret;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ExtraServiceTO> findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }
        
        List<ExtraService> result = extraServiceDAO.findByName(name);
        List<ExtraServiceTO> ret = new ArrayList<ExtraServiceTO>();
        for (ExtraService es : result) {
            ret.add(mapper.map(es, ExtraServiceTO.class));
        }
        
        return ret;
    }
    
}
