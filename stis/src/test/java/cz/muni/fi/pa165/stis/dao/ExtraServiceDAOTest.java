package cz.muni.fi.pa165.stis.dao;

import cz.muni.fi.pa165.stis.entity.ExtraService;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan Koščák (xkoscak@gmail.com)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles("test")
@Transactional
public class ExtraServiceDAOTest {

    private ExtraService extraService;
    @Autowired
    private ExtraServiceDAO extraServiceDAO;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        removeAll();
    }

    /**
     * Test of create method of class ExtraServiceDAO.
     */
    @Test
    public void testCreate() {
        extraService = null;

        try {
            extraServiceDAO.create(extraService);
            fail("ExtraService is null and didn't throw exception.");
        } catch (IllegalArgumentException e) {
            // ok
        } catch (Exception e) {
            fail("ExtraService is null and didn't throw appropriate exception.");
        }

        extraService = newExtraService(null, null, BigDecimal.ZERO);
        extraService.setId(22L);
        try {
            extraServiceDAO.create(extraService);
            fail("ID not null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            //ok
        } catch (Exception ex) {
            fail("ID not null and didn't throw appropriate exception");
        }
        extraService.setId(null);
        extraServiceDAO.create(extraService);
        assertNotNull("ID is null", extraService.getId());
    }

    /**
     * Test of get method of class ExtraServiceDAO.
     */
    @Test
    public void testGet() {
        ExtraService e = newExtraService(null, null, BigDecimal.ZERO);
        extraServiceDAO.create(e);
        Long id = null;
        try {
            extraServiceDAO.get(id);
            fail("Id null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            //ok
        } catch (Exception ex) {
            fail("Id null and didn't throw appropriate exception");
        }
        ExtraService e2 = extraServiceDAO.get(e.getId());
        assertNotNull("ExtraService is null", e2);
        assertEquals("ExtraServices are not the same", e2, e);
        assertDeepEquals(e2, e);
        ExtraService e3 = extraServiceDAO.get(e.getId() + 1); // shouldn't exist
        assertNull("ExtraService is not null", e3);
        removeAll();
    }
    
        /**
     * Test of update method of class ExtraServiceDAO.
     */
    @Test
    public void testUpdate() {
        ExtraService e = newExtraService(null, null, BigDecimal.ZERO);
        extraServiceDAO.create(e);
        Long eId = e.getId();
        e.setId(null);
        //
        try {
            extraServiceDAO.update(null);
            fail("ExtraService is null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            //ok
        } catch (Exception ex) {
            fail("ExtraService is null and didn't throw appropriate exception");
        }
        try {
            extraServiceDAO.update(e);
            fail("ExtraService is ID null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            //ok
        } catch (Exception ex) {
            fail("ExtraService is ID null and didn't throw appropriate exception");
        }
        //
        e.setId(eId);
        extraServiceDAO.update(e);
        //
        ExtraService e2 = extraServiceDAO.get(e.getId());
        assertEquals("ExtraServices are not the same", e2, e);
        assertDeepEquals(e2, e);
    }

    /**
     * Test of remove method of class ExtraServiceDAO.
     */
    @Test
    public void testRemove() {
        ExtraService e = newExtraService(null, null, BigDecimal.TEN);
        extraServiceDAO.create(e);
        //
        try {
            extraServiceDAO.remove(null);
            fail("ExtraService is null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            //ok
        } catch (Exception ex) {
            fail("ExtraService is null and didn't throw appropriate exception");
        }
        try {
            extraServiceDAO.remove(new ExtraService());
            fail("ExtraService ID is null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            // ok
        } catch (Exception ex) {
            fail("ExtraService ID is null and didn't throw appropriate exception");
        }
        try {
            ExtraService es = new ExtraService();
            es.setId(-1L);
            extraServiceDAO.remove(es);
            fail("Shouldn't remove non-existent entity");
        } catch (IllegalArgumentException ex) {
            //ok
        } catch (Exception ex) {
            fail("Non existent es (extra service) - should throw appropriate exception");
        }
        //
        extraServiceDAO.remove(e);
        ExtraService e2 = extraServiceDAO.get(e.getId());
        assertNull("Found extraService that shouldn't be there", e2);
        removeAll();
    }

    /**
     * Test of findAll method of class ExtraServiceDAO.
     */
    @Test
    public void testFindAll() {
        List<ExtraService> extraServices = extraServiceDAO.findAll();
        assertTrue("ExtraServices should be empty", extraServices.isEmpty());
        extraServices = Arrays.asList(new ExtraService[] {
            newExtraService(null, null, BigDecimal.ZERO),
            newExtraService(null, null, BigDecimal.ONE),
            newExtraService(null, null, BigDecimal.TEN)
        });
        for (ExtraService e : extraServices) {
            extraServiceDAO.create(e);
        }
        List<ExtraService> all = extraServiceDAO.findAll();
        assertTrue("Size is not the same", all.size() == extraServices.size());
        Collections.sort(all, extraServiceComparator);
        Collections.sort(extraServices, extraServiceComparator);
        for (int i = 0; i < all.size(); i++) {
            assertDeepEquals(all.get(i), extraServices.get(i));
        }
    }
    
    /**
     * Test of findByName method of class ExtraServiceDAO.
     */
    @Test
    public void testFindByName() {
        //System.out.println("findByName");
        ExtraService e1 = newExtraService("Tyre cleaning", "Proffesional tyre cleaning", BigDecimal.valueOf(325));
        extraServiceDAO.create(e1);
        ExtraService e2 = newExtraService("Tyre fixing", "Proffesional tyre fixing", BigDecimal.valueOf(490));
        extraServiceDAO.create(e2);
        String name;

        try {
            extraServiceDAO.findByName(null);
            fail("String name is null");
        } catch (IllegalArgumentException e) {
            // ok
        } catch (Exception e) {
            fail("String name is null - should have been thrown another exception");
        }

        name = "TyreCleaning";
        List<ExtraService> extraServices = extraServiceDAO.findByName(name);
        assertTrue("ExtraService is not in DB", extraServices.isEmpty());

        name = "Tyre cleaning";
        extraServices = extraServiceDAO.findByName(name);
        assertEquals(e1.getId(), extraServices.get(0).getId());
        assertDeepEquals(e1, extraServices.get(0));

        ExtraService e3 = newExtraService("Tyre cleaning", "Proffesional tyre cleaning", BigDecimal.valueOf(260));
        ExtraService e4 = newExtraService("Tyre cleaning", "Proffesional tyre cleaning", BigDecimal.valueOf(355));
        extraServiceDAO.create(e3);
        extraServiceDAO.create(e4);

        List<ExtraService> extraServiceList = Arrays.asList(new ExtraService[]{e1, e2, e3, e4});

        extraServices = extraServiceDAO.findByName(name);
        System.out.println(extraServices.size() + " == " + (extraServiceList.size() - 1));
        assertTrue("ExtraService lists are not the same size.", extraServices.size() == extraServiceList.size() - 1);
        Collections.sort(extraServices, extraServiceComparator);
        Collections.sort(extraServiceList, extraServiceComparator);

        int i;
        for (ExtraService es : extraServices) {
            if (extraServiceList.contains(es)) {
                i = extraServiceList.indexOf(es);
                //System.out.println(t + " vs " + extraServiceList.get(i));
                assertDeepEquals(es, extraServiceList.get(i));
            }
        }
    }


    private static ExtraService newExtraService(String name, String desc, BigDecimal price) {
        ExtraService es = new ExtraService();
        es.setName(name);
        es.setDescription(desc);
        es.setPrice(price);

        return es;
    }

    private void removeAll() {
        List<ExtraService> es = extraServiceDAO.findAll();
        for (ExtraService e : es) {
            extraServiceDAO.remove(e);
        }
    }

    private void assertDeepEquals(ExtraService e1, ExtraService e2) {
        assertEquals(e1 == null, e2 == null);
        if (e1 != null) {
            assertEquals(e1.getId(), e2.getId());
            assertEquals(e1.getName(), e2.getName());
            assertTrue(e1.getPrice().compareTo(e2.getPrice()) == 0);
        }
    }
    private static Comparator<ExtraService> extraServiceComparator = new Comparator<ExtraService>() {
        @Override
        public int compare(ExtraService t, ExtraService t1) {
            return t.getId().compareTo(t1.getId());
        }
    };
}
