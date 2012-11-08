package cz.muni.fi.pa165.stis.dao.impl;

import cz.muni.fi.pa165.stis.dao.TyreDAOLocal;
import cz.muni.fi.pa165.stis.entity.Tyre;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;

/**
 *
 * @author michalxo
 */
@RunWith(MockitoJUnitRunner.class)
public class TyreDAOImplTest {

    @Mock
    private EntityManager tyreEM;
    @Mock
    private TypedQuery typedQuery;
    private TyreDAOImpl tyreDAO;
    private Tyre tyre1;
    private Tyre tyre2;
    private Tyre tyre3;

    @Before
    public void setUp() {
        // tyreDAO. set field EntityManager to tyreEM
        // similar to spring's ReflectionTestUtils.setField(..)
        tyreDAO = new TyreDAOImpl();
        Class c = tyreDAO.getClass();
        try {
            Field changeEM = c.getDeclaredField("em");
            changeEM.setAccessible(true);
            changeEM.set(tyreDAO, tyreEM);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TyreDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(TyreDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TyreDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(TyreDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }


        tyre1 = createTyre(17D, "MM21", "EZ256", "Michellin", BigDecimal.valueOf(222));
        tyre2 = createTyre(18D, "MM22", "EZ256", "Michellin", BigDecimal.valueOf(222));
        tyre3 = createTyre(19D, "MM23", "EZ256", "Pirelli", BigDecimal.valueOf(222));
    }

    @Test
    public void testCreate() {
        try {
            tyreDAO.create(null);
            fail("InvalidArgumentException has not been thrown. <null tyre>");
        } catch (IllegalArgumentException e) {
            // ok
        }

        try {
            tyre1.setId(1L);
            tyreDAO.create(tyre1);
            fail("InvalidArgumentException has not been thrown. <tyre.id is null>");
        } catch (IllegalArgumentException e) {
            // ok        
        }

        tyre1.setId(null);
        tyreDAO.create(tyre1);
        Mockito.verify(tyreEM).persist(Mockito.argThat(new BaseMatcher<Tyre>() {
            @Override
            public boolean matches(Object item) {
                if (!(item instanceof Tyre)) {
                    return false;
                }
                final Tyre t = (Tyre) item;
                if (!Objects.equals(t.getDiameter(), tyre1.getDiameter())) {
                    return false;
                }
                if (!Objects.equals(t.getName(), tyre1.getName())) {
                    return false;
                }
                if (!Objects.equals(t.getPrice(), tyre1.getPrice())) {
                    return false;
                }
                if (!Objects.equals(t.getType(), tyre1.getType())) {
                    return false;
                }
                if (!Objects.equals(t.getVendor(), tyre1.getVendor())) {
                    return false;
                }
                return true;
            }

            @Override
            public void describeTo(Description description) {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        }));
    }

    @Test
    public void testGet() {
        tyre2.setId(2L);

        when(tyreDAO.get(2L)).thenReturn(tyre2);
        Tyre tyreNew = tyreDAO.get(2L);
        verify(tyreEM).find(Tyre.class, 2L);
        assertEquals(tyreNew, tyre2);

        try {
            tyreDAO.get(null);
            fail("IllegalArgumentException not thrown. <null id>");
        } catch (IllegalArgumentException e) {
            // ok
        }

        when(tyreEM.find(Tyre.class, 10L)).thenReturn(null);
        assertNull(tyreDAO.get(10L));
    }

    @Test
    public void testUpdate() {
        try {
            tyreDAO.update(null);
            fail("IllegalArgumentException has not been thrown <tyre is null>");
        } catch (IllegalArgumentException e) {
            // ok
        }

        try {
            tyreDAO.update(tyre3);
            fail("IllegalArgumentException has not been thrown <tyre.id is null>");
        } catch (IllegalArgumentException e) {
            // ok
        }

        tyre3.setId(3L);
        tyreDAO.update(tyre3);
        verify(tyreEM).merge(tyre3);
    }

    @Test
    public void testRemove() {
        try {
            tyreDAO.remove(null);
            fail("IllegalArgumentException has not been thrown <tyre is null>");
        } catch (IllegalArgumentException e) {
            // ok
        }

        try {
            tyreDAO.remove(tyre3);
            fail("IllegalArgumentException has not been thrown. <tyre.id is null>");
        } catch (IllegalArgumentException e) {
            // ok
        }

        doThrow(new IllegalArgumentException()).when(tyreEM).find(Tyre.class, null);
        try {
            tyreDAO.remove(tyre3);
            fail("IllegalArgumentException has not been thrown. <tyre not in db>");
        } catch (IllegalArgumentException e) {
            // ok
        }

        tyre1.setId(1L);
        when(tyreEM.find(Tyre.class, tyre1.getId())).thenReturn(tyre1);
        tyreDAO.remove(tyre1);
        verify(tyreEM).remove(tyre1);
    }

    @Test
    public void testFindAll() {
        List<Tyre> tqList = new ArrayList<Tyre>();
        List<Tyre> tyreList = new ArrayList<Tyre>();

        // Test empty result set
        when(tyreEM.createQuery("FROM Tyre", Tyre.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(tqList);
        doReturn(tqList).when(typedQuery).getResultList();
        tyreList = tyreDAO.findAll();
        assertTrue(tyreList.isEmpty() && tqList.isEmpty());


        tyre1.setId(1L);
        tyre2.setId(2L);
        tqList.add(tyre1);
        tqList.add(tyre2);

        tyreList = tyreDAO.findAll();
        assertTrue(tyreList.containsAll(tqList) && tqList.containsAll(tyreList));
    }

    @Test
    public void testFindByName() {

        try {
            tyreDAO.findByName(null);
            fail("IllegalArgumentException has not been thrown. Name is null");
        } catch (IllegalArgumentException e) {
            // ok
        }

        List<Tyre> tyreList;
        List<Tyre> tqList = new ArrayList<Tyre>();
        when(tyreEM.createQuery("SELECT t FROM Tyre t WHERE t.name LIKE :name", Tyre.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(tqList);
        // test empty list  
        tyreList = tyreDAO.findByName("MM21");
        assertTrue(tyreList.isEmpty() && tqList.isEmpty());

        // found in "db"
        tqList.add(tyre1);
        tqList.add(tyre2);
        when(tyreEM.createQuery("SELECT t FROM Tyre t WHERE t.name LIKE :name", Tyre.class)).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(tqList);
        tqList.get(0);
        tyreList = tyreDAO.findByName("MM21");
        assertTrue(tqList.get(0).equals(tyreList.get(0)));

        // not found in "db"   (???)
        tyreList = tyreDAO.findByName("MM23");
        tqList.clear();
        when(typedQuery.getResultList()).thenReturn(tqList);
        //System.out.println("Not Found in DB" + "\n" + tyreList + "\n"+ tqList);        
        assertTrue(tqList.isEmpty() && tyreList.isEmpty());

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
}
