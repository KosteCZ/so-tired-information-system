package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.dao.ExtraServiceDAO;
import cz.muni.fi.pa165.stis.entity.ExtraService;
import cz.muni.fi.pa165.stis.service.impl.ExtraServiceServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 *
 * @author Dusan Svancara
 */
@RunWith(MockitoJUnitRunner.class)
public class ExtraServiceServiceTest {
    
    private ExtraServiceServiceImpl service;
    @Mock
    private ExtraServiceDAO dao;
    
    public ExtraServiceServiceTest() {
    }
    
    @Before
    public void setUp() {
        service = new ExtraServiceServiceImpl();
        ReflectionTestUtils.setField(service, "extraServiceDAO", dao);
    }
    
    @After
    public void tearDown() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateExisting() {
        ExtraService es = newService("Window cleaning", "Thorough window and mirror cleaning", BigDecimal.valueOf(22.2));
        es.setId(1L);
        //
        doThrow(new IllegalArgumentException()).when(dao).create(es);
        service.create(es);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNull() {
        doThrow(new IllegalArgumentException()).when(dao).create(null);
        service.create(null);
    }

    @Test
    public void testCreateOk() {
        ExtraService es = newService("Window cleaning", "Thorough window and mirror cleaning", BigDecimal.valueOf(22.2));
        es.setId(2L);
        //
        service.create(es);
        verify(dao).create(es);
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testRemove() {
    }

    @Test
    public void testFindAll() {
        ExtraService es1 = newService("1", "2", BigDecimal.ZERO);
        es1.setId(2L);
        ExtraService es2 = newService("3", "5", BigDecimal.ONE);
        es2.setId(3L);
        List<ExtraService> extraServices = new ArrayList<ExtraService>(Arrays.asList(new ExtraService[]{es1, es2}));
        //
        when(dao.findAll()).thenReturn(extraServices);
        List<ExtraService> ess = service.findAll();
        //
        assertTrue(ess.size() == extraServices.size());
        ess.removeAll(extraServices);
        assertTrue(ess.isEmpty());
    }

    @Test
    public void testFindByName() {
    }
    
    private static ExtraService newService(String name, String description, BigDecimal price) {
        ExtraService es = new ExtraService();
        es.setName(name);
        es.setDescription(description);
        es.setPrice(price);
        
        return es;
    }
}
