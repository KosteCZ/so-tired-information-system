package cz.muni.fi.pa165.stis.dao;

import cz.muni.fi.pa165.stis.dao.impl.TyreDAOImpl;
import cz.muni.fi.pa165.stis.entity.Tyre;
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
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Michal Toth
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
@ActiveProfiles("test")
@Transactional
public class TyreDAOTest {

    @Autowired
    private TyreDAO tyreDAO;
    private Tyre tyre;

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        removeAll();
    }

      /**
     * Test of get method, of class TyreDAO.
     */
    @Test
    public void testGet() {
        Tyre t1 = createTyre(17D, "MM22", "EZ256", "Michellin", BigDecimal.valueOf(222));
        tyreDAO.create(t1);
        Long id = null;

        try {
            tyreDAO.get(id);
            fail("Getting tyre with null id");
        } catch (DataAccessException e) {
            // ok
        } catch (Exception e) {
            fail("Incorrect exception has been thrown while getting tyre by null id.");
        }

        Tyre t2 = tyreDAO.get(t1.getId());
        assertNotNull("Tyre is null", t2);
        assertEquals("Tyres are not equal", t1, t2);
        assertDeepEquals(t1, t2);

        Tyre t3 = tyreDAO.get(t2.getId() + 1);
        assertNull("Tyre should not exists", t3);
    }
    
    /**
     * Test of create method, of class TyreDAO.
     */
    @Test
    public void testCreate() {
        tyre = null;

        try {
            tyreDAO.create(tyre);
            fail("Tyre is null and didn't throw exception.");
        } catch (DataAccessException e) {
            // ok
        } catch (Exception e) {
            fail("Tyre is null and didn't throw appropriate exception.");
        }

        // TYRE.ID IS NULL
        tyre = createTyre(19D, "Potenza RE050A", " 235/40R19", "Bridgestone", BigDecimal.valueOf(324));
        tyre.setId(2L);
        try {
            tyreDAO.create(tyre);
            fail("Tyre has to have null ID before creation.");
        } catch (DataAccessException e) {
            // ok
        } catch (Exception e) {
            fail("Tyre has null id and hasn't been thrown appropriate exception. (DataAccessException).");
        }
        tyre.setId(null);
        tyreDAO.create(tyre);
        assertNotNull("ID is null", tyre.getId());
    }
  

    /**
     * Test of update method, of class TyreDAO.
     */
    @Test
    public void testUpdate() {
        tyre = createTyre(19D, "P Zero", "235/40ZR19", "Pirelli", BigDecimal.valueOf(420));
        tyreDAO.create(tyre);
        Long tyreOldId = tyre.getId();

        try {
            tyreDAO.update(null);
            fail("Tyre is null");
        } catch (DataAccessException e) {
            // ok
        } catch (Exception e) {
            fail("Tyre is null, should throw DataAccessException.");
        }

        tyre.setId(null);
        try {
            tyreDAO.update(tyre);
            fail("Tyre.id is null");
        } catch (DataAccessException e) {
            // ok correct exception caught
        } catch (Exception e) {
            fail("Tyre.id is null, should be thrown another exception");
        }

        tyre.setId(tyreOldId);
        tyre.setPrice(BigDecimal.valueOf(440));
        tyreDAO.update(tyre);
        Tyre tyre2 = tyreDAO.get(tyre.getId());
        assertEquals("Tyres should be same.", tyre, tyre2);
        assertDeepEquals(tyre, tyre2);
    }

    /**
     * Test of remove method, of class TyreDAO.
     */
    @Test
    public void testRemove() {
        tyre = createTyre(19D, "P Zero", "235/40ZR19", "Pirelli", BigDecimal.valueOf(420));
        //tyreDAO.create(tyre);

        try {
            tyreDAO.remove(null);
            fail("Tyre is null");
        } catch (DataAccessException e) {
            // ok
        } catch (Exception e) {
            fail("Tyre remove - should have been thrown another exception.");
        }

        Long tOldId = tyre.getId();
        tyre.setId(null);
        try {
            tyreDAO.remove(tyre);
            fail("Tyre.id is null.");
        } catch (DataAccessException e) {
            // ok 
        } catch (Exception e) {
            fail("Remove tyre - tyre.id is null - should have been thrown another exception.");
        }

        try {
            tyreDAO.remove(tyre);
            fail("Tyre is not in database");
        } catch (DataAccessException e) {
            // ok
        } catch (Exception e) {
            fail("Tyre is not in DB - Should have been thrown another exception.");
        }

        tyre.setId(tOldId);
        tyreDAO.create(tyre);
        tyreDAO.remove(tyre);
        Tyre tyre2 = tyreDAO.get(tyre.getId());
        assertNull(tyre2);
    }

    /**
     * Test of findAll method, of class TyreDAO.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        List<Tyre> tyres = tyreDAO.findAll();
        assertEquals("Tyre list is not empty.", tyres.size(), 0);
        assertTrue(tyres.isEmpty());

        List<Tyre> tyresList = Arrays.asList(new Tyre[]{
                    createTyre(19D, "P Zero", "235/40ZR19", "Pirelli", BigDecimal.valueOf(410)),
                    createTyre(17D, "MM22", "EZ256", "Michellin", BigDecimal.valueOf(222)),
                    createTyre(19D, "SP Sport Maxx 050", "235/40R19", "Dunlop", BigDecimal.valueOf(316))
                });

        for (Tyre t : tyresList) {
            tyreDAO.create(t);
        }

        tyres = tyreDAO.findAll();
        assertEquals("Tyre lists don't have the same size.", tyres.size(), tyresList.size());

        Collections.sort(tyres, tyreComparator);
        Collections.sort(tyresList, tyreComparator);

        int i = 0;
        for (Tyre t : tyresList) {
            assertDeepEquals(tyres.get(i), tyresList.get(i));
            i++;
        }
    }

    /**
     * Test of findByName method, of class TyreDAO.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        Tyre t1 = createTyre(19D, "P Zero", "235/40ZR19", "Pirelli", BigDecimal.valueOf(410));
        tyreDAO.create(t1);
        Tyre t2 = createTyre(17D, "MM22", "EZ256", "Michellin", BigDecimal.valueOf(222));
        tyreDAO.create(t2);
        String name;

        try {
            tyreDAO.findByName(null);
            fail("String name is null");
        } catch (DataAccessException e) {
            // ok
        } catch (Exception e) {
            fail("String name is null - should have been thrown another exception");
        }

        name = "PZero";
        List<Tyre> tyres = tyreDAO.findByName(name);
        assertTrue("Tyre is not in DB", tyres.isEmpty());

        name = "P Zero";
        tyres = tyreDAO.findByName(name);
        assertEquals(t1.getId(), tyres.get(0).getId());
        assertDeepEquals(t1, tyres.get(0));

        Tyre t3 = createTyre(19D, "P Zero", "235/40ZR19", "Pirelli", BigDecimal.valueOf(450));
        Tyre t4 = createTyre(20D, "P Zero", "240/50R20", "Goodyear", BigDecimal.valueOf(480));
        tyreDAO.create(t3);
        tyreDAO.create(t4);

        List<Tyre> tyreList = Arrays.asList(new Tyre[]{t1, t2, t3, t4});

        tyres = tyreDAO.findByName(name);
        System.out.println(tyres.size() + " == " + (tyreList.size() - 1));
        assertTrue("Tyre lists are not the same size.", tyres.size() == tyreList.size() - 1);
        Collections.sort(tyres, tyreComparator);
        Collections.sort(tyreList, tyreComparator);

        int i;
        for (Tyre t : tyres) {
            if (tyreList.contains(t)) {
                i = tyreList.indexOf(t);
                //System.out.println(t + " vs " + tyreList.get(i));
                assertDeepEquals(t, tyreList.get(i));
            }
        }
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

    private void removeAll() {
        List<Tyre> ts = tyreDAO.findAll();
        for (Tyre t : ts) {
            tyreDAO.remove(t);
        }
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
    
    private static Comparator<Tyre> tyreComparator = new Comparator<Tyre>() {
        @Override
        public int compare(Tyre t1, Tyre t2) {
            return t1.getId().compareTo(t2.getId());
        }
    };
}
