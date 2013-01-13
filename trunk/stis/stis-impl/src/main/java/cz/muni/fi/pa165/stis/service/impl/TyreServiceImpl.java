package cz.muni.fi.pa165.stis.service.impl;

import cz.muni.fi.pa165.stis.dao.TyreDAO;
import cz.muni.fi.pa165.stis.dto.TyreTO;
import cz.muni.fi.pa165.stis.entity.Tyre;
import cz.muni.fi.pa165.stis.service.TyreService;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public void create(TyreTO tyre) {
        if (tyre == null) {
            throw new IllegalArgumentException("tyre is null");
        }
        
        if (tyre.getId() != null) {
            throw new IllegalArgumentException("tyre.id is not null");
        }
        Tyre tm = mapper.map(tyre, Tyre.class);
        tyreDAO.create(tm);
        tyre.setId(tm.getId());
    }

    @Transactional(readOnly = true)
    @Override
    public TyreTO get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Tyre.Id is null");
        }
        
        Tyre tyre = tyreDAO.get(id);
        if (tyre == null) {
            return null;
        }        
        return mapper.map(tyre, TyreTO.class);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public void update(TyreTO tyre) {
        if (tyre == null) {
            throw new IllegalArgumentException("Tyre is null");
        }        
        if (tyre.getId() == null) {
            throw new IllegalArgumentException("Tyre.id is null");
        }
        Tyre tm = mapper.map(tyre, Tyre.class);
        tyreDAO.update(tm);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    @Override
    public void remove(TyreTO tyre) {
        if (tyre == null) {
            throw new IllegalArgumentException("Tyre is null");
        }        
        if (tyre.getId() == null) {
            throw new IllegalArgumentException("Tyre.id is null");
        }
        Tyre tm = mapper.map(tyre, Tyre.class);
        tyreDAO.remove(tm);
    }

    @Transactional(readOnly= true)
    @Override
    public List<TyreTO> findAll() {
        List<Tyre> result = tyreDAO.findAll();
        List<TyreTO> tyreTOList = new ArrayList<>();
        
        for (Tyre t : result) {
            tyreTOList.add(mapper.map(t, TyreTO.class));
        }
        return tyreTOList;
    }

    @Transactional(readOnly= true)
    @Override
    public List<TyreTO> findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("null name");
        }       
        
        List<Tyre> result = tyreDAO.findByName(name);
        List<TyreTO> tyreTOList = new ArrayList<>();
        
        for (Tyre t : result) {
            tyreTOList.add(mapper.map(t, TyreTO.class));
        }
        return tyreTOList;
    }
}
