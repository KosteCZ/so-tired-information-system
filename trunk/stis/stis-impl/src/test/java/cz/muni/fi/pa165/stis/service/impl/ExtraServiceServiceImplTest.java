package cz.muni.fi.pa165.stis.service.impl;

import cz.muni.fi.pa165.stis.dao.ExtraServiceDAO;
import cz.muni.fi.pa165.stis.dto.ExtraServiceTO;
import cz.muni.fi.pa165.stis.entity.ExtraService;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import org.dozer.DozerBeanMapper;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.argThat;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author Dusan Svancara
 */
@RunWith(MockitoJUnitRunner.class)
public class ExtraServiceServiceImplTest {
    
    private ExtraServiceServiceImpl service;
    @Mock
    private ExtraServiceDAO dao;
    private DozerBeanMapper mapper;
    
    @Before
    public void setUp() {
        mapper = new DozerBeanMapper();
        service = new ExtraServiceServiceImpl();
        ReflectionTestUtils.setField(service, "extraServiceDAO", dao);
        ReflectionTestUtils.setField(service, "mapper", mapper);
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
        
        ExtraService es = newService("Window cleaning", "Thorough window and mirror cleaning", BigDecimal.valueOf(22.2));
        ExtraServiceTO esto = mapper.map(es, ExtraServiceTO.class);
        try {
            service.update(esto);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            service.remove(esto);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        esto.setId(1L);
        try {
            service.create(esto);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
    }

    @Test
    public void testCreate() {
        final ExtraService es = newService("Window cleaning", "Thorough window and mirror cleaning", BigDecimal.valueOf(22.2));
        ExtraServiceTO esto = mapper.map(es, ExtraServiceTO.class);
        //
        service.create(esto);
        verify(dao).create(argThat(new BaseMatcher<ExtraService>() {

            @Override
            public boolean matches(Object item) {
                if (!(item instanceof ExtraService)) {
                    return false;
                }
                final ExtraService other = (ExtraService) item;
                if (!Objects.equals(es.getId(), other.getId())) {
                    return false;
                }
                if (!Objects.equals(es.getName(), other.getName())) {
                    return false;
                }
                if (!Objects.equals(es.getDescription(), other.getDescription())) {
                    return false;
                }
                if (es.getPrice().compareTo(other.getPrice()) != 0) {
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
    public void testUpdate() {
        ExtraService es = newService("Window cleaning", "Thorough window and mirror cleaning", BigDecimal.valueOf(22.2));
        es.setId(2L);
        ExtraServiceTO esto = mapper.map(es, ExtraServiceTO.class);
        //
        service.update(esto);
        verify(dao).update(es);
    }
    
    @Test
    public void testGet() {
        ExtraService es = newService("Window cleaning", "Thorough window and mirror cleaning", BigDecimal.valueOf(22.2));
        es.setId(2L);
        ExtraServiceTO esto = mapper.map(es, ExtraServiceTO.class);
        //
        when(dao.get(2L)).thenReturn(es);
        ExtraServiceTO estoNew = service.get(2L);
        //
        assertEquals(estoNew, esto);
    }

    @Test
    public void testRemove() {
        ExtraService es = newService("Window cleaning", "Thorough window and mirror cleaning", BigDecimal.valueOf(22.2));
        es.setId(2L);
        ExtraServiceTO esto = mapper.map(es, ExtraServiceTO.class);
        //
        service.remove(esto);
        verify(dao).remove(es);
    }

    @Test
    public void testFindAll() {
        ExtraService es1 = newService("1", "2", BigDecimal.ZERO);
        es1.setId(2L);
        ExtraService es2 = newService("3", "5", BigDecimal.ONE);
        es2.setId(3L);
        List<ExtraService> extraServices = Arrays.asList(new ExtraService[]{es1, es2});
        List<ExtraServiceTO> esTOs = Arrays.asList(new ExtraServiceTO[]{
            mapper.map(es1, ExtraServiceTO.class), 
            mapper.map(es2, ExtraServiceTO.class)
        });
        //
        when(dao.findAll()).thenReturn(extraServices);
        List<ExtraServiceTO> ess = service.findAll();
        //
        assertTrue(ess.containsAll(esTOs) && esTOs.containsAll(ess));
    }

    @Test
    public void testFindByName() {
        ExtraService es1 = newService("as1", "2", BigDecimal.ZERO);
        es1.setId(2L);
        ExtraService es2 = newService("as1", "5", BigDecimal.ONE);
        es2.setId(3L);
        List<ExtraService> extraServices = Arrays.asList(new ExtraService[]{es1, es2});
        List<ExtraServiceTO> esTOs = Arrays.asList(new ExtraServiceTO[]{
            mapper.map(es1, ExtraServiceTO.class), 
            mapper.map(es2, ExtraServiceTO.class)
        });
        //
        when(dao.findByName("as1")).thenReturn(extraServices);
        List<ExtraServiceTO> ess = service.findByName("as1");
        //
        assertTrue(ess.containsAll(esTOs) && esTOs.containsAll(ess));
    }
    
    private static ExtraService newService(String name, String description, BigDecimal price) {
        ExtraService es = new ExtraService();
        es.setName(name);
        es.setDescription(description);
        es.setPrice(price);
        
        return es;
    }
}
