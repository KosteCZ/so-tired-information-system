package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.dao.TyreDAO;
import cz.muni.fi.pa165.stis.service.impl.TyreServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
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
     
    
    public TyreServiceTest() {
    }
    
    @Before
    public void setUp() throws Exception {
        service = new TyreServiceImpl();
        ReflectionTestUtils.setField(service, "tyreDAO", dao);
        // what is that?
    }
    
    @Test
    public void testCreate() {
        
    }

    @After
    public void tearDown() throws Exception {
        service = null;
    }

}
