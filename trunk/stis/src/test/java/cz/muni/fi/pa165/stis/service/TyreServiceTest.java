package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.dao.TyreDAO;
import cz.muni.fi.pa165.stis.dto.TyreTO;
import cz.muni.fi.pa165.stis.entity.Tyre;
import cz.muni.fi.pa165.stis.service.impl.TyreServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;



/**
 *
 * @author michalxo 
 */

@RunWith(MockitoJUnitRunner.class)
public class TyreServiceTest {

    private TyreServiceImpl service;    
    
    @Mock
    private TyreDAO dao;
    // ==TyreDAO dao = Mockito.mock(TyreDAO.class);
    private DozerBeanMapper mapper;
     
    
    // Is this needed?
    public TyreServiceTest() {
    }
    
    @Before
    public void setUp() {
        mapper = new DozerBeanMapper();
        service = new TyreServiceImpl();
        ReflectionTestUtils.setField(service, "tyreDAO", dao);
        ReflectionTestUtils.setField(service, "mapper", mapper);     
    }
    
    @After
    public void tearDown() {
        service = null;
   //     mapper = null;
    }
    
    @Test
    public void testCreate() {
        Tyre tyre = createTyre(19D, "P Zero", "235/40ZR19", "Pirelli", BigDecimal.valueOf(420));
        tyre.setId(2L);
        TyreTO tto = mapper.map(tyre, TyreTO.class);
        
        service.remove(tto);
        verify(dao).remove(tyre);
    }

    @Test
    public void testGet() {
        Tyre tyre = createTyre(19D, "P Zero", "235/40ZR19", "Pirelli", BigDecimal.valueOf(420));
        tyre.setId(4L);
        TyreTO tto = mapper.map(tyre, TyreTO.class);        
        
        when(dao.get(4L)).thenReturn(tyre);
        TyreTO tyreTO = service.get(4L);
        assertEquals(tyreTO, tto);
    }
    
    
    @Test
    public void testUpdate() {
        Tyre tyre = createTyre(19D, "P Zero", "235/40ZR19", "Pirelli", BigDecimal.valueOf(420));
        tyre.setId(2L);
        TyreTO tto = mapper.map(tyre, TyreTO.class);
        
        service.update(tto);
        verify(dao).update(tyre);        
    }
    
    @Test
    public void testRemove() {
        Tyre tyre = createTyre(19D, "P Zero", "235/40ZR19", "Pirelli", BigDecimal.valueOf(420));
        tyre.setId(2L);
        TyreTO tto = mapper.map(tyre, TyreTO.class);
        
        service.remove(tto);
        verify(dao).remove(tyre);        
    }
    
    
    @Test
    public void testFindAll() {
        Tyre tyre1 = createTyre(19D, "P Zero", "235/40ZR19", "Pirelli", BigDecimal.valueOf(420));
        tyre1.setId(2L);
        
        Tyre tyre2 = createTyre(19D, "Potenza RE050A", " 235/40R19", "Bridgestone", BigDecimal.valueOf(324));
        tyre2.setId(3L);
        
        List<Tyre> tyres = new ArrayList<Tyre>(Arrays.asList(new Tyre[]{tyre1, tyre2}));
        List<TyreTO> tyreTOList = new ArrayList<TyreTO>(Arrays.asList(new TyreTO[]{
            mapper.map(tyre1, TyreTO.class), 
            mapper.map(tyre2, TyreTO.class)
        }));
        
        when(dao.findAll()).thenReturn(tyres);
        List<TyreTO> tyreTOServiceList = service.findAll();
        
        assertTrue(tyreTOServiceList.containsAll(tyreTOList) && tyreTOList.containsAll(tyreTOServiceList));
    }
    
    @Test
    public void testFindByName() {
        Tyre tyre1 = createTyre(19D, "P Zero", "235/40ZR19", "Pirelli", BigDecimal.valueOf(450));
        Tyre tyre2 = createTyre(20D, "P Zero", "240/50R20", "Goodyear", BigDecimal.valueOf(480));
        tyre1.setId(2L);
        tyre2.setId(3L);
        
        List<Tyre> tyres = new ArrayList<Tyre>(Arrays.asList(new Tyre[]{tyre1, tyre2}));
        List<TyreTO> tyreTOList = new ArrayList<TyreTO>(Arrays.asList(new TyreTO[]{
            mapper.map(tyre1, TyreTO.class), 
            mapper.map(tyre2, TyreTO.class)
        }));
        
        when(dao.findByName("P Zero")).thenReturn(tyres);
        List<TyreTO> tyreTOServiceList = service.findByName("P Zero");
        
        assertTrue(tyreTOServiceList.containsAll(tyreTOList) && tyreTOList.containsAll(tyreTOServiceList));
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
