package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.dao.TyreDAO;
import cz.muni.fi.pa165.stis.service.impl.TyreServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;



/**
 * Created with IntelliJ IDEA.
 * User: michalxo
 * Date: 10/31/12
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */

@RunWith(MockitoJUnitRunner.class)
public class TyreServiceTest {

    private TyreServiceImpl service;

    @Mock
    private TyreDAO dao;

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
