package cz.muni.fi.pa165.stis.dao.impl;

import cz.muni.fi.pa165.stis.dao.ExtraServiceDAO;
import cz.muni.fi.pa165.stis.entity.ExtraService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dusan Svancara
 */
@Repository
public class ExtraServiceDAOImpl implements ExtraServiceDAO {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(ExtraService extraService) {
        if (extraService == null) {
            throw new IllegalArgumentException("extraService is null");
        }
        if (extraService.getId() != null) {
            throw new IllegalArgumentException("extraService has non-null id");
        }
        em.persist(extraService);
        em.flush();
    }

    @Override
    public ExtraService get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        return em.find(ExtraService.class, id);
    }

    @Override
    public void update(ExtraService extraService) {
        if (extraService == null) {
            throw new IllegalArgumentException("extraService is null");
        }
        if (extraService.getId() == null) {
            throw new IllegalArgumentException("extraService.id is null");
        }
        
        em.merge(extraService);
        em.flush();
    }

    @Override
    public void remove(ExtraService extraService) {
        if (extraService == null) {
            throw new IllegalArgumentException("extraService is null");
        }
        if (extraService.getId() == null) {
            throw new IllegalArgumentException("extraService.id is null");
        }
        ExtraService toRemove = em.find(ExtraService.class, extraService.getId());
        if (toRemove == null) {
            throw new IllegalArgumentException("given extraService doesn't exist");
        }
        em.remove(toRemove);
        em.flush();
    }

    @Override
    public List<ExtraService> findAll() {
        TypedQuery<ExtraService> tq = em.createQuery("FROM ExtraService", ExtraService.class);
        return tq.getResultList();
    }

    @Override
    public List<ExtraService> findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }
        TypedQuery<ExtraService> tq = em.createQuery("SELECT s FROM ExtraService s WHERE s.name LIKE :name", ExtraService.class);
        tq.setParameter("name", name);

        return tq.getResultList();
    }
    
}
