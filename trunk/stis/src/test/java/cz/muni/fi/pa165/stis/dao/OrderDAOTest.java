package cz.muni.fi.pa165.stis.dao;

import cz.muni.fi.pa165.stis.dao.impl.CustomerDAOImpl;
import cz.muni.fi.pa165.stis.dao.impl.ExtraServiceDAOImpl;
import cz.muni.fi.pa165.stis.dao.impl.OrderDAOImpl;
import cz.muni.fi.pa165.stis.dao.impl.TyreDAOImpl;
import cz.muni.fi.pa165.stis.entity.Customer;
import cz.muni.fi.pa165.stis.entity.ExtraService;
import cz.muni.fi.pa165.stis.entity.Order;
import cz.muni.fi.pa165.stis.entity.Tyre;
import cz.muni.fi.pa165.stis.entity.TyrePosition;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Dusan Svancara
 */
public class OrderDAOTest {
    
    private OrderDAO dao;
    private CustomerDAO customerDAO;
    private TyreDAO tyreDAO;
    private ExtraServiceDAO extraServiceDAO;
    //
    private Customer customer;
    private List<ExtraService> extraServices;
    private Map<TyrePosition, Tyre> tyres;
    
    @Before
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        dao = new OrderDAOImpl();
        ((OrderDAOImpl) dao).setEntityManager(emf);
        customerDAO = new CustomerDAOImpl();
        ((CustomerDAOImpl) customerDAO).setEntityManager(emf);
        tyreDAO = new TyreDAOImpl();
        ((TyreDAOImpl) tyreDAO).setEntityManager(emf);
        extraServiceDAO = new ExtraServiceDAOImpl();
        ((ExtraServiceDAOImpl) extraServiceDAO).setEntityManagerFactory(emf);
        //
        customer = newCustomer("Jozin", "Zbazin", "Baziny 22", "005544");
        customerDAO.create(customer);
        //
        ExtraService es1 = newExtraService("Umytie", "Umytie okien a zrkadiel", BigDecimal.valueOf(22.2));
        extraServiceDAO.create(es1);
        ExtraService es2 = newExtraService("Vysavanie", "Vysavanie auta", BigDecimal.valueOf(122.5));
        extraServiceDAO.create(es2);
        extraServices = Arrays.asList(new ExtraService[]{es1, es2});
        //
        Tyre t1 = newTyre(17D, "MM22", "EZ256", "Michellin", BigDecimal.valueOf(222));
        tyreDAO.create(t1);
        Tyre t2 = newTyre(17D, "MM23", "EZ257", "Michellin", BigDecimal.valueOf(222));
        tyreDAO.create(t2);
        Tyre t3 = newTyre(18D, "PR89", "NT99", "Pirelli", BigDecimal.valueOf(253.4));
        tyreDAO.create(t3);
        tyres = new EnumMap<TyrePosition, Tyre>(TyrePosition.class);
        tyres.put(TyrePosition.FRONT_LEFT, t1);
        tyres.put(TyrePosition.FRONT_RIGHT, t2);
        tyres.put(TyrePosition.REAR_LEFT, t3);
    }
    
    @After
    public void tearDown() {
    }
    
    /**
     * Test of create method, of class OrderDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        // order null test
        Order order = null;
        try {
            dao.create(order);
            fail("Order null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            //ok
        }
        order = newOrder(customer, newDate("22.9.2012 12:13:15"), null, null, extraServices, tyres);
        order.setId(22L);
        try {
            dao.create(order);
            fail("ID not null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            //ok
        }
        order.setId(null);
        dao.create(order);
        assertNotNull("ID is null", order.getId());
        //
        //
        removeAll();
    }

    /**
     * Test of get method, of class OrderDAO.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        //
        Order o = newOrder(customer, null, null, null, extraServices, tyres);
        dao.create(o);
        //
        Long id = null;
        try {
            dao.get(id);
            fail("Id null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            //ok
        }
        Order o2 = dao.get(o.getId());
        assertNotNull("Order is null", o2);
        assertEquals("Orders are not the same", o2, o);
        assertDeepEquals(o2, o);
        //
        //
        removeAll();
    }

    /**
     * Test of update method, of class OrderDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        //
        Long oId;
        Order o = newOrder(customer, null, null, null, extraServices, tyres);
        dao.create(o);
        oId = o.getId();
        o.setId(null);
        //
        try {
            dao.update(null);
            fail("Order null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            //ok
        }
        try {
            dao.update(o);
            fail("Order ID null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            //ok
        }
        //
        o.setId(oId);
        Date d = newDate("22.1.2010 22:15:00");
        o.setOrderNewDate(d);
        dao.update(o);
        //
        Order o2 = dao.get(o.getId());
        assertEquals("Orders are not the same", o2, o);
        assertDeepEquals(o2, o);
        //
        //
        removeAll();
    }

    /**
     * Test of remove method, of class OrderDAO.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        //
        Order o = newOrder(customer, null, null, null, extraServices, tyres);
        dao.create(o);
        //
        try {
            dao.remove(null);
            fail("Order null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            //ok
        }
        try {
            dao.remove(new Order());
            fail("Order id null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        dao.remove(o);
        Order o2 = dao.get(o.getId());
        assertNull("Found order that shouldn't be there", o2);
        //
        //
        removeAll();
    }

    /**
     * Test of findAll method, of class OrderDAO.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        //
        List<Order> orders = dao.findAll();
        assertTrue("Orders should be empty", orders.isEmpty());
        //
        orders = Arrays.asList(new Order[] {
            newOrder(customer, null, null, null, extraServices, null),
            newOrder(customer, null, null, null, null, null),
            newOrder(customer, null, null, null, null, tyres)
        });
        for (Order o : orders) {
            dao.create(o);
        }
        //
        List<Order> all = dao.findAll();
        assertTrue("Size is not the same", all.size() == orders.size());
        //
        Collections.sort(all, orderComparator);
        Collections.sort(orders, orderComparator);
        for (int i = 0; i < all.size(); i++) {
            assertDeepEquals(all.get(i), orders.get(i));
        }
        //
        //
        removeAll();
    }

    /**
     * Test of findByCustomer method, of class OrderDAO.
     */
    @Test
    public void testFindByCustomer() {
        System.out.println("findByCustomer");
        //
        Order o1 = newOrder(customer, null, null, null, extraServices, tyres);
        Order o2 = newOrder(customer, null, null, null, null, tyres);
        List<Order> os = Arrays.asList(new Order[]{o1, o2});
        for (Order o : os) {
            dao.create(o);
        }
        //
        try {
            dao.findByCustomer(null);
            fail("Customer is null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            //ok
        }
        //
        try {
            dao.findByCustomer(new Customer());
            fail("Customer id is null and didn't throw exception");
        } catch (IllegalArgumentException ex) {
            //ok
        }
        //
        List<Order> orders = dao.findByCustomer(customer);
        assertTrue("Didn't find the same lists", orders.size() == os.size());
        Collections.sort(orders, orderComparator);
        Collections.sort(os, orderComparator);
        for (int i = 0; i < os.size(); i++) {
            assertDeepEquals(os.get(i), orders.get(i));
        }
        //
        //
        removeAll();
    }
    
    private static Tyre newTyre(Double diameter, String name, String type, String vendor, BigDecimal price) {
        Tyre t = new Tyre();
        t.setDiameter(diameter);
        t.setName(name);
        t.setType(type);
        t.setVendor(vendor);
        t.setPrice(price);
        
        return t;
    }
    
    private static ExtraService newExtraService(String name, String desc, BigDecimal price) {
        ExtraService es = new ExtraService();
        es.setName(name);
        es.setDescription(desc);
        es.setPrice(price);
        
        return es;
    }
    
    private static Customer newCustomer(String firstName, String lastName, String address, String phone) {
        Customer c = new Customer();
        c.setFirstName(firstName);
        c.setLastName(lastName);
        c.setAddress(address);
        c.setPhone(phone);
        
        return c;
    }
    
    private static Order newOrder(Customer c, Date orderNewDate, Date orderServicedDate, 
            Date orderPaidDate, List<ExtraService> extraServices, Map<TyrePosition, Tyre> tyres) {
        Order o = new Order();
        o.setCustomer(c);
        o.setOrderNewDate(orderNewDate);
        o.setOrderServicedDate(orderServicedDate);
        o.setOrderPaidDate(orderPaidDate);
        o.setTyres(tyres);
        o.setExtraServices(extraServices);
        
        return o;
    }
    
    private static Date newDate(String value) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        try {
            return df.parse(value);
        } catch (ParseException ex) {
            Logger.getLogger(OrderDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private static void assertDeepEquals(Order o1, Order o2) {
        assertEquals(o1.getId(), o2.getId());
        assertEquals(o1.getCustomer(), o2.getCustomer());
        assertEquals(o1.getOrderNewDate(), o2.getOrderNewDate());
        assertEquals(o1.getOrderServicedDate(), o2.getOrderServicedDate());
        assertEquals(o1.getOrderPaidDate(), o2.getOrderPaidDate());
        assertDeepEquals(o1.getTyres(), o2.getTyres());
        assertDeepEquals(o1.getExtraServices(), o2.getExtraServices());
    }
    
    private static void assertDeepEquals(List<ExtraService> es1, List<ExtraService> es2) {
        assertTrue(es1.size() == es2.size());
        for (int i = 0; i < es1.size(); i++) {
            assertEquals(es1.get(i), es2.get(i));
        }
    }
    
    private static void assertDeepEquals(Map<TyrePosition, Tyre> t1, Map<TyrePosition, Tyre> t2) {
        for (TyrePosition tp : TyrePosition.values()) {
            assertEquals(t1.get(tp), t2.get(tp));
        }
    }
    
    private void removeAll() {
        List<Order> orders = dao.findAll();
        for (Order o : orders) {
            dao.remove(o);
        }
    }
    
    private static Comparator<Order> orderComparator = new Comparator<Order>() {

        @Override
        public int compare(Order t, Order t1) {
            return t.getId().compareTo(t1.getId());
        }
    };
}
