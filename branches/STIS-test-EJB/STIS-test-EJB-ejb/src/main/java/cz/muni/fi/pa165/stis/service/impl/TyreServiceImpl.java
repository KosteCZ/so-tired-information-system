package cz.muni.fi.pa165.stis.service.impl;

import cz.muni.fi.pa165.stis.dao.TyreDAOLocal;
import cz.muni.fi.pa165.stis.entity.Tyre;
import cz.muni.fi.pa165.stis.service.TyreServiceLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author xsvanca2
 */
@Stateless
public class TyreServiceImpl implements TyreServiceLocal {
    
    @EJB
    private TyreDAOLocal dao;

    @Override
    public void create(Tyre tyre) {
        if (tyre == null) {
            throw new IllegalArgumentException("tyre is null");
        }
        
        if (tyre.getId() != null) {
            throw new IllegalArgumentException("tyre.id is not null");
        }
        dao.create(tyre);
    }

    @Override
    public Tyre get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Tyre.Id is null");
        }
        return dao.get(id);
    }

    @Override
    public void update(Tyre tyre) {
        if (tyre == null) {
            throw new IllegalArgumentException("Tyre is null");
        }        
        if (tyre.getId() == null) {
            throw new IllegalArgumentException("Tyre.id is null");
        }
        dao.update(tyre);
    }

    @Override
    public void remove(Tyre tyre) {
        if (tyre == null) {
            throw new IllegalArgumentException("Tyre is null");
        }        
        if (tyre.getId() == null) {
            throw new IllegalArgumentException("Tyre.id is null");
        }
        dao.remove(tyre);
    }

    @Override
    public List<Tyre> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Tyre> findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("null name");
        }
        return dao.findByName(name);
    }

}
