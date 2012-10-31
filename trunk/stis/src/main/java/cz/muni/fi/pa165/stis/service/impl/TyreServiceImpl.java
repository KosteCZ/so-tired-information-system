package cz.muni.fi.pa165.stis.service.impl;

import cz.muni.fi.pa165.stis.dao.TyreDAO;
import cz.muni.fi.pa165.stis.entity.Tyre;
import cz.muni.fi.pa165.stis.service.TyreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: Michal Toth
 * Date: 10/31/12
 * Time: 1:31 PM
 */

@Service
public class TyreServiceImpl implements TyreService {

    @Autowired
    private TyreDAO tyreDAO;

    @Override
    public void create(Tyre tyre) {
          tyreDAO.create(tyre);
    }

    @Override
    public Tyre get(Long id) {
        return tyreDAO.get(id);
    }

    @Override
    public void update(Tyre tyre) {
        tyreDAO.update(tyre);
    }

    @Override
    public void remove(Tyre tyre) {
        tyreDAO.remove(tyre);
    }

    @Override
    public List<Tyre> findAll() {
        return tyreDAO.findAll();
    }

    @Override
    public List<Tyre> findByName(String name) {
        return tyreDAO.findByName(name);
    }
}
