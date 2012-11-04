package cz.muni.fi.pa165.stis.service.impl;

import cz.muni.fi.pa165.stis.dao.TyreDAO;
import cz.muni.fi.pa165.stis.dto.TyreTO;
import cz.muni.fi.pa165.stis.entity.Tyre;
import cz.muni.fi.pa165.stis.service.TyreService;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: Michal Toth Date: 10/31/12 Time: 1:31 PM
 */
@Service
public class TyreServiceImpl implements TyreService {

    @Autowired
    private TyreDAO tyreDAO;
    @Autowired
    private DozerBeanMapper mapper;

    @Transactional
    public void create(TyreTO tyre) {
        Tyre tm = mapper.map(tyre, Tyre.class);
        tyreDAO.create(tm);
    }

    @Transactional(readOnly = true)
    public TyreTO get(Long id) {
        return mapper.map(tyreDAO.get(id), TyreTO.class);
    }

    @Transactional
    public void update(TyreTO tyre) {
        Tyre tm = mapper.map(tyre, Tyre.class);
        tyreDAO.update(tm);
    }

    @Transactional
    public void remove(TyreTO tyre) {
        Tyre tm = mapper.map(tyre, Tyre.class);
        tyreDAO.remove(tm);
    }

    @Transactional(readOnly= true)
    @Override
    public List<TyreTO> findAll() {
        List<Tyre> result = tyreDAO.findAll();
        List<TyreTO> tyreTOList = new ArrayList<TyreTO>();
        
        for (Tyre t : result) {
            tyreTOList.add(mapper.map(t, TyreTO.class));
        }
        return tyreTOList;
    }

    @Transactional(readOnly= true)
    public List<TyreTO> findByName(String name) {
        List<Tyre> result = tyreDAO.findByName(name);
        List<TyreTO> tyreTOList = new ArrayList<TyreTO>();
        
        for (Tyre t : result) {
            tyreTOList.add(mapper.map(t, TyreTO.class));
        }
        return tyreTOList;
    }
}
