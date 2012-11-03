/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
        dao.create(tyre);
    }

    @Override
    public Tyre get(Long id) {
        return dao.get(id);
    }

    @Override
    public void update(Tyre tyre) {
        dao.update(tyre);
    }

    @Override
    public void remove(Tyre tyre) {
        dao.remove(tyre);
    }

    @Override
    public List<Tyre> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Tyre> findByName(String name) {
        return dao.findByName(name);
    }

}
