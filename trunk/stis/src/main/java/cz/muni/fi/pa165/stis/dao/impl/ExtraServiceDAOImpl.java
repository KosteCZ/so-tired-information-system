package cz.muni.fi.pa165.stis.dao.impl;

import cz.muni.fi.pa165.stis.dao.ExtraServiceDAO;
import cz.muni.fi.pa165.stis.entity.ExtraService;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Dusan Svancara
 */
public class ExtraServiceDAOImpl implements ExtraServiceDAO {
    
    private EntityManagerFactory emf;
    
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void create(ExtraService extraService) {
        if (extraService == null) {
            throw new IllegalArgumentException("extraService is null");
        }
        if (extraService.getId() != null) {
            throw new IllegalArgumentException("extraService has non-null id");
        }
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(extraService);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public ExtraService get(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            return em.find(ExtraService.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(ExtraService extraService) {
        if (extraService == null) {
            throw new IllegalArgumentException("extraService is null");
        }
        if (extraService.getId() == null) {
            throw new IllegalArgumentException("extraService.id is null");
        }
        
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(extraService);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void remove(ExtraService extraService) {
        if (extraService == null) {
            throw new IllegalArgumentException("extraService is null");
        }
        if (extraService.getId() == null) {
            throw new IllegalArgumentException("extraService.id is null");
        }
        
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            ExtraService toRemove = em.find(ExtraService.class, extraService.getId());
            if (toRemove == null) {
                throw new IllegalArgumentException("given extraService doesn't exist");
            }
            em.remove(toRemove);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<ExtraService> findAll() {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            TypedQuery<ExtraService> tq = em.createQuery("FROM ExtraService", ExtraService.class);
            
            return tq.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<ExtraService> findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name is null");
        }
        EntityManager em = null;
        
        try {
            em = emf.createEntityManager();
            TypedQuery<ExtraService> tq = em.createQuery("SELECT s FROM ExtraService s WHERE s.name LIKE :name", ExtraService.class);
            tq.setParameter("name", name);
            
            return tq.getResultList();
        } finally {
            em.close();
        }
    }
    
}
