package cz.muni.fi.pa165.stis.dao.impl;

import cz.muni.fi.pa165.stis.dao.TyreDAO;
import cz.muni.fi.pa165.stis.entity.Tyre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jan Koščák (xkoscak@gmail.com)
 */
@Repository
public class TyreDAOImpl implements TyreDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Tyre tyre) {
        if (tyre == null) {
            throw new IllegalArgumentException("tyre is null");
        }        
        if (tyre.getId() != null) {
            throw new IllegalArgumentException("tyre.id is not null");
        }
        em.persist(tyre);
    }

    @Override
    public Tyre get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        Tyre result = em.find(Tyre.class, id);
        return result;
    }

    @Override
    public void update(Tyre tyre) {
        if (tyre == null) {
            throw new IllegalArgumentException("tyre is null");
        }
        if (tyre.getId() == null) {
            throw new IllegalArgumentException("tyre id is null");
        }
        em.merge(tyre);
    }

    @Override
    public void remove(Tyre tyre) {
        if (tyre == null) {
            throw new IllegalArgumentException("tyre is null");
        }
        if (tyre.getId() == null) {
            throw new IllegalArgumentException("tyre.id is null");
        }
        Tyre toRemove = em.find(Tyre.class, tyre.getId());
        if (toRemove == null) {
            throw new IllegalArgumentException("tyre does not exist");
        }
        em.remove(toRemove);
    }

    @Override
    public List<Tyre> findAll() {
        TypedQuery<Tyre> tq = em.createQuery("FROM Tyre", Tyre.class);
        List<Tyre> result = tq.getResultList();
        
        return result;
    }

    @Override
    public List<Tyre> findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }
        TypedQuery<Tyre> tq = em.createQuery("SELECT t FROM Tyre t WHERE t.name LIKE :name", Tyre.class);
        tq.setParameter("name", name);
        List<Tyre> result = tq.getResultList();
        
        return result;
    }
}
