package cz.muni.fi.pa165.stis.service.impl;

import cz.muni.fi.pa165.stis.dao.TyreDAOLocal;
import cz.muni.fi.pa165.stis.entity.Tyre;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import javax.persistence.EntityManager;

/**
 *
 * @author michalxo
 */
@RunWith(MockitoJUnitRunner.class)
public class TyreServiceImplTest {
    
    /*
     * Test using mockito (see spring testing)
     */
    private TyreServiceImpl service;    
    
    @Mock
    private TyreDAOLocal dao;
    private Tyre tyre1;
    private Tyre tyre2;
    private Tyre tyre3;
    
    @Before
    public void setUp() {
        service = new TyreServiceImpl();
        Class c = service.getClass();
        try {
            Field changeDAO = c.getDeclaredField("dao");
            changeDAO.setAccessible(true);
            changeDAO.set(service, dao);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(TyreServiceImplTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TyreServiceImplTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(TyreServiceImplTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(TyreServiceImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tyre1 = createTyre(17D, "MM21", "EZ256", "Michellin", BigDecimal.valueOf(222));
        tyre2 = createTyre(18D, "MM22", "EZ256", "Michellin", BigDecimal.valueOf(222));
        tyre3 = createTyre(19D, "MM23", "EZ256", "Pirelli", BigDecimal.valueOf(222));
    }
    
    @After
    public void tearDown() {
        service = null;
    }
    
    @Test
    public void testExceptions() {
        try {            
            service.create(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {                        
            service.update(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            service.get(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            service.remove(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            service.findByName(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        
        Tyre t = createTyre(19D, "P Zero", "235/40ZR19", "Pirelli", BigDecimal.valueOf(450));
        //TyreTO tto = mapper.map(t, TyreTO.class);
        try {
            service.update(t);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            service.remove(t);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        t.setId(1L);
        try {
            service.create(t);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
             //ok
        }
    }
    
    @Test
    public void testCreate() {
        try {
            service.create(null);
            fail("InvalidArgumentException has not been thrown. <null tyre>");
        } catch (IllegalArgumentException e) {
            // ok
        }

        try {
            tyre1.setId(1L);
            service.create(tyre1);
            fail("InvalidArgumentException has not been thrown. <tyre.id is null>");
        } catch (IllegalArgumentException e) {
            // ok        
        }
        
        tyre1.setId(null);
        service.create(tyre1);
        
        Mockito.verify(dao).create(Mockito.argThat(new BaseMatcher<Tyre>() {
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
        tyre2.setId(11L);
        when(service.get(11L)).thenReturn(tyre2);
        Tyre tyre = service.get(11L);
        verify(dao, times(1)).get(11L);
        assertEquals(tyre2, tyre);
    }

    @Test
    public void testUpdate() {
        tyre3.setId(22L);
        service.update(tyre3);
        verify(dao).update(tyre3);
    }

    @Test
    public void testRemove() {
        tyre1.setId(1L);
        when(dao.get(tyre1.getId())).thenReturn(tyre1);
        service.remove(tyre1);
        verify(dao).remove(tyre1);
    }

    @Test
    public void testFindAll() {
        List<Tyre> tyreList = new ArrayList<Tyre>();
        List<Tyre> serviceList = new ArrayList<Tyre>();
        
        tyre1.setId(1L);
        tyre2.setId(2L);
        tyreList.add(tyre1);
        tyreList.add(tyre2);
        
        when(dao.findAll()).thenReturn(tyreList);
        serviceList = service.findAll();
        assertTrue(serviceList.containsAll(tyreList) && tyreList.containsAll(serviceList));
    }

    @Test
    public void testFindByName() {
        List<Tyre> tyreList = new ArrayList<Tyre>();
        List<Tyre> serviceList = new ArrayList<Tyre>();
        
        tyreList.add(tyre1);
        tyreList.add(tyre2);
        
        when(dao.findAll()).thenReturn(tyreList);
        when(dao.findByName("MM21")).thenReturn(tyreList);
        serviceList = service.findByName("MM21");
        assertTrue(tyreList.get(0).equals(serviceList.get(0)));
        
        serviceList = service.findByName("MM29");
        tyreList.clear();
        assertTrue(serviceList.isEmpty() && tyreList.isEmpty());
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
