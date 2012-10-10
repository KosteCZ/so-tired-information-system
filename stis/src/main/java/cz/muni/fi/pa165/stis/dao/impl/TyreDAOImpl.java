package cz.muni.fi.pa165.stis.dao.impl;

import cz.muni.fi.pa165.stis.dao.TyreDAO;
import cz.muni.fi.pa165.stis.entity.Tyre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author xkoscak
 */
public class TyreDAOImpl implements TyreDAO {

    private EntityManagerFactory emf;

    public void setEntityManager(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void create(Tyre tyre) {
        if (tyre == null) {
            throw new IllegalArgumentException("tyre");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(tyre);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Tyre get(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Tyre tyre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(Tyre tyre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Tyre> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Tyre findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
