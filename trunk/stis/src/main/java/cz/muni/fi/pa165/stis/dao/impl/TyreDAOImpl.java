package cz.muni.fi.pa165.stis.dao.impl;

import cz.muni.fi.pa165.stis.dao.TyreDAO;
import cz.muni.fi.pa165.stis.entity.Tyre;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jan Koščák (xkoscak@gmail.com)
 */
public class TyreDAOImpl implements TyreDAO {

    private EntityManagerFactory emf;

    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void create(Tyre tyre) {
        if (tyre == null) {
            throw new IllegalArgumentException("tyre is null");
        }        
        if (tyre.getId() != null) {
            throw new IllegalArgumentException("tyre.id is not null");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(tyre);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Tyre get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        EntityManager em = emf.createEntityManager();
        Tyre result = em.find(Tyre.class, id);
        em.close();
        
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
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(tyre);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void remove(Tyre tyre) {
        if (tyre == null) {
            throw new IllegalArgumentException("tyre is null");
        }
        if (tyre.getId() == null) {
            throw new IllegalArgumentException("tyre.id is null");
        }
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Tyre toRemove = em.find(Tyre.class, tyre.getId());
        if (toRemove == null) {
            throw new IllegalArgumentException("tyre does not exist");
        }
        em.remove(toRemove);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Tyre> findAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Tyre> tq = em.createQuery("FROM Tyre", Tyre.class);
        List<Tyre> result = tq.getResultList();
        em.close();
        
        return result;
    }

    @Override
    public List<Tyre> findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }
        EntityManager em = emf.createEntityManager();
        TypedQuery<Tyre> tq = em.createQuery("SELECT t FROM Tyre t WHERE t.name LIKE :name", Tyre.class);
        tq.setParameter("name", name);
        List<Tyre> result = tq.getResultList();
        
        em.close();
        return result;
    }
}
