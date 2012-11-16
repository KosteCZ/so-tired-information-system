package cz.muni.fi.pa165.stis.dao.impl;

import cz.muni.fi.pa165.stis.dao.TyreDAOLocal;
import cz.muni.fi.pa165.stis.entity.Tyre;
import java.math.BigDecimal;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.TestCase;
import org.apache.openejb.jee.EjbJar;
import org.apache.openejb.jee.StatelessBean;
import org.apache.openejb.jee.jpa.unit.PersistenceUnit;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.junit.Module;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ApplicationComposer.class)
public class TyreDAOImplTest extends TestCase {

    @EJB
    private TyreDAOLocal tyreDAO;
    @Resource
    private UserTransaction userTransaction;
    @PersistenceContext
    private EntityManager entityManager;

    @Module
    public PersistenceUnit persistence() {
        PersistenceUnit pu = new PersistenceUnit("STISPU");
        pu.setProvider(org.hibernate.ejb.HibernatePersistence.class);        
        pu.setProperty("hibernate.connection.url", "jdbc:derby:TestDB;create=true");
        pu.setProperty("hibernate.connection.driver_class", "org.apache.derby.jdbc.EmbeddedDriver");
        pu.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        pu.setProperty("hibernate.cache.provider_class", "org.hibernate.cache.NoCacheProvider");  
        pu.setProperty("hibernate.connection.username", "");
        pu.setProperty("hibernate.connection.password", "");
        pu.getClazz().add(Tyre.class.getName());
        return pu;
    }

    @Module
    public EjbJar beans() {
        EjbJar ejbJar = new EjbJar();
        ejbJar.addEnterpriseBean(new StatelessBean(TyreDAOImpl.class));
        return ejbJar;
    }

    @Test
    public void testCreate() {

        Tyre t1 = createTyre(17D, "MM22", "EZ256", "Michellin", BigDecimal.valueOf(222));
        
        tyreDAO.create(t1);

        Tyre result = tyreDAO.get(t1.getId());

        assertEquals(t1, result);
        assertDeepEquals(t1, result);
        System.out.println("AAAA");
        try {
            tyreDAO.create(null);
            System.out.println("BBBB");
            fail();
        } catch (EJBException e) {
            // ok
        }
       
        //Tyre t1 = new Tyre();
        t1.setId(1L);
        try {
            tyreDAO.create(t1);
            fail();
        } catch (EJBException e) {
        }
       
        Tyre t2 = createTyre(17D, "MM22", "EZ256", "Michellin", BigDecimal.valueOf(222));
        tyreDAO.create(t2);
        System.out.println(t2.toString());
        assertNotNull(t2.getId());
        System.out.println("hello create test");
    }

    private static Tyre createTyre(Double diameter, String name, String type, String vendor, BigDecimal price) {
        Tyre t = new Tyre();
        t.setDiameter(diameter);
        t.setName(name);
        t.setType(type);
        t.setVendor(vendor);
        t.setPrice(price);

        return t;
    }

    private void assertDeepEquals(Tyre t1, Tyre t2) {
        assertEquals(t1 == null, t2 == null);
        if (t1 != null) {
            assertEquals(t1.getId(), t2.getId());
            assertEquals(t1.getDiameter(), t2.getDiameter());
            assertEquals(t1.getName(), t2.getName());
            assertTrue(t1.getPrice().compareTo(t2.getPrice()) == 0);
            assertEquals(t1.getType(), t2.getType());
            assertEquals(t1.getVendor(), t2.getVendor());
        }
    }
}
