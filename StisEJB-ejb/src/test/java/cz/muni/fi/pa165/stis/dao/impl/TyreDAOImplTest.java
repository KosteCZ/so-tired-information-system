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
    private TyreDAOImpl tyreDAO;
    private Tyre tyre1;
    private Tyre tyre2;
    private Tyre tyre3;

    @Before
    public void setUp() {
        // tyreDAO. set field ENtityManager to tyreEM
        // similar to spring's ReflectionTestUtils.setField(..)
        tyreDAO = new TyreDAOImpl();
        Class c = tyreDAO.getClass();
        try {
            Field changeEM = c.getDeclaredField("em");
            changeEM.setAccessible(true);
            changeEM.set(tyreDAO, tyreEM);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(TyreDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TyreDAOImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }


        tyre1 = createTyre(17D, "MM22", "EZ256", "Michellin", BigDecimal.valueOf(222));
        tyre2 = createTyre(18D, "MM22", "EZ256", "Michellin", BigDecimal.valueOf(222));
        tyre3 = createTyre(19D, "MM22", "EZ256", "Pirelli", BigDecimal.valueOf(222));
    }

    @Test
    public void testCreate() throws Exception {
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
        /*
         * Mock query & play with em.createQuery
         */
        List<Tyre> tyreList = new ArrayList<Tyre>();
        List<Tyre> tyreList2;
        
//        tyreList2 = tyreDAO.findAll();        
//        assertNull(tyreList2);
        
        tyre1.setId(1L);
        tyre2.setId(2L);
        tyreList.add(tyre1);
        tyreList.add(tyre2);
        
//        tyreList2 = tyreDAO.findAll();
//        verify(tyreDAO, times(2)).findAll();
//       assertTrue(tyreList.size() == tyreList2.size());
//        assertTrue(tyreList.containsAll(tyreList2) && tyreList2.containsAll(tyreList));
    }

    @Test
    public void testFindByName() {
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
