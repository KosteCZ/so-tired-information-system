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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *
 * @author Dusan Svancara
 */
public class OrderDAOTest {
    
//    private OrderDAO dao;
//    private CustomerDAO customerDAO;
//    private TyreDAO tyreDAO;
//    @Autowired
//    private ExtraServiceDAO extraServiceDAO;
//    //
//    private Customer customer;
//    private Set<ExtraService> extraServices;
//    private Map<TyrePosition, Tyre> tyres;
//    //
//    private EntityManagerFactory emf = null;
//
//    @Before
//    public void setUp() {
//        emf = Persistence.createEntityManagerFactory("TestPU");
//        dao = new OrderDAOImpl();
//        ((OrderDAOImpl) dao).setEntityManagerFactory(emf);
//        customerDAO = new CustomerDAOImpl();
//        ((CustomerDAOImpl) customerDAO).setEntityManagerFactory(emf);
//        tyreDAO = new TyreDAOImpl();
//        ((TyreDAOImpl) tyreDAO).setEntityManagerFactory(emf);
////        extraServiceDAO = new ExtraServiceDAOImpl();
////        ((ExtraServiceDAOImpl) extraServiceDAO).setEntityManagerFactory(emf);
//        
//        customer = newCustomer("Jozin", "Zbazin", "Baziny 22", "005544");
//        customerDAO.create(customer);
//        //
//        ExtraService es1 = newExtraService("Umytie", "Umytie okien a zrkadiel", BigDecimal.valueOf(22.2));
//        extraServiceDAO.create(es1);
//        ExtraService es2 = newExtraService("Vysavanie", "Vysavanie auta", BigDecimal.valueOf(122.5));
//        extraServiceDAO.create(es2);
//        extraServices = new HashSet<ExtraService>(Arrays.asList(new ExtraService[]{es1, es2}));
//        //
//        Tyre t1 = newTyre(17D, "MM22", "EZ256", "Michellin", BigDecimal.valueOf(222));
//        tyreDAO.create(t1);
//        Tyre t2 = newTyre(17D, "MM23", "EZ257", "Michellin", BigDecimal.valueOf(222));
//        tyreDAO.create(t2);
//        Tyre t3 = newTyre(18D, "PR89", "NT99", "Pirelli", BigDecimal.valueOf(253.4));
//        tyreDAO.create(t3);
//        tyres = new EnumMap<TyrePosition, Tyre>(TyrePosition.class);
//        tyres.put(TyrePosition.FRONT_LEFT, t1);
//        tyres.put(TyrePosition.FRONT_RIGHT, t2);
//        tyres.put(TyrePosition.REAR_LEFT, t3);
//    }
//    
//    @After
//    public void tearDown() {
//        removeAll();
//        if (emf != null) {
//            emf.close();
//        }
//    }
//    
//    /**
//     * Test of create method, of class OrderDAO.
//     */
//    @Test
//    public void testCreate() {
//        Order order = null;
//        try {
//            dao.create(order);
//            fail("Order null and didn't throw exception");
//        } catch (IllegalArgumentException ex) {
//            //ok
//        } catch (Exception ex) {
//            fail("Order null and didn't throw appropriate exception");
//        }
//        order = newOrder(customer, newDate("22.9.2012 12:13:15"), null, null, extraServices, tyres, BigDecimal.valueOf(15.23));
//        order.setId(22L);
//        try {
//            dao.create(order);
//            fail("ID not null and didn't throw exception");
//        } catch (IllegalArgumentException ex) {
//            //ok
//        } catch (Exception ex) {
//            fail("ID not null and didn't throw appropriate exception");
//        }
//        order.setId(null);
//        dao.create(order);
//        assertNotNull("ID is null", order.getId());
//    }
//
//    /**
//     * Test of get method, of class OrderDAO.
//     */
//    @Test
//    public void testGet() {
//        Order o = newOrder(customer, null, null, null, extraServices, tyres, BigDecimal.valueOf(124.66));
//        dao.create(o);
//        //
//        Long id = null;
//        try {
//            dao.get(id);
//            fail("Id null and didn't throw exception");
//        } catch (IllegalArgumentException ex) {
//            //ok
//        } catch (Exception ex) {
//            fail("Id null and didn't throw appropriate exception");
//        }
//        Order o2 = dao.get(o.getId());
//        assertNotNull("Order is null", o2);
//        assertEquals("Orders are not the same", o2, o);
//        assertDeepEquals(o2, o);
//        //
//        Order o3 = dao.get(o.getId() + 1); // shouldn't exist
//        assertNull("Order is not null", o3);
//    }
//
//    /**
//     * Test of update method, of class OrderDAO.
//     */
//    @Test
//    public void testUpdate() {
//        Long oId;
//        Order o = newOrder(customer, null, null, null, extraServices, tyres, BigDecimal.valueOf(2690.9));
//        dao.create(o);
//        oId = o.getId();
//        o.setId(null);
//        //
//        try {
//            dao.update(null);
//            fail("Order null and didn't throw exception");
//        } catch (IllegalArgumentException ex) {
//            //ok
//        } catch (Exception ex) {
//            fail("Order null and didn't throw appropriate exception");
//        }
//        try {
//            dao.update(o);
//            fail("Order ID null and didn't throw exception");
//        } catch (IllegalArgumentException ex) {
//            //ok
//        } catch (Exception ex) {
//            fail("Order ID null and didn't throw appropriate exception");
//        }
//        //
//        o.setId(oId);
//        Date d = newDate("22.1.2010 22:15:00");
//        o.setOrderNewDate(d);
//        dao.update(o);
//        //
//        Order o2 = dao.get(o.getId());
//        assertEquals("Orders are not the same", o2, o);
//        assertDeepEquals(o2, o);
//        //
//        // test tyre remove and add
//        o.getTyres().remove(TyrePosition.FRONT_LEFT);
//        Tyre t = newTyre(12D, "AJ22", "BRPL", "Matador", BigDecimal.valueOf(77.7));
//        tyreDAO.create(t);
//        o.getTyres().put(TyrePosition.REAR_RIGHT, t);
//        dao.update(o);
//        //
//        Order o3 = dao.get(o.getId());
//        assertEquals("Orders are not the same", o3, o);
//        assertDeepEquals(o3, o);
//        //
//        // test extra services remove and add
//        o.getExtraServices().remove(o.getExtraServices().iterator().next());
//        ExtraService es = newExtraService("Baking pastery", "You wouldn't drive hungry!", BigDecimal.ZERO);
//        extraServiceDAO.create(es);
//        o.getExtraServices().add(es);
//        dao.update(o);
//        //
//        Order o4 = dao.get(o.getId());
//        assertEquals("Orders are not the same", o4, o);
//        assertDeepEquals(o4, o);
//    }
//
//    /**
//     * Test of remove method, of class OrderDAO.
//     */
//    @Test
//    public void testRemove() {
//        Order o = newOrder(customer, null, null, null, extraServices, tyres, BigDecimal.valueOf(21.4));
//        dao.create(o);
//        //
//        try {
//            dao.remove(null);
//            fail("Order null and didn't throw exception");
//        } catch (IllegalArgumentException ex) {
//            //ok
//        } catch (Exception ex) {
//            fail("Order null and didn't throw appropriate exception");
//        }
//        try {
//            dao.remove(new Order());
//            fail("Order ID null and didn't throw exception");
//        } catch (IllegalArgumentException ex) {
//            // ok
//        } catch (Exception ex) {
//            fail("Order ID null and didn't throw appropriate exception");
//        }
//        try {
//            Order ord = new Order();
//            ord.setId(-1L);
//            dao.remove(ord);
//            fail("Shouldn't remove non-existent entity");
//        } catch (IllegalArgumentException ex) {
//            //ok
//        } catch (Exception ex) {
//            fail("Non existent order - should throw appropriate exception");
//        }
//        //
//        dao.remove(o);
//        Order o2 = dao.get(o.getId());
//        assertNull("Found order that shouldn't be there", o2);
//    }
//
//    /**
//     * Test of findAll method, of class OrderDAO.
//     */
//    @Test
//    public void testFindAll() {
//        List<Order> orders = dao.findAll();
//        assertTrue("Orders should be empty", orders.isEmpty());
//        //
//        orders = Arrays.asList(new Order[] {
//            newOrder(customer, null, null, null, extraServices, new EnumMap<TyrePosition, Tyre>(TyrePosition.class), BigDecimal.valueOf(2123)),
//            newOrder(customer, null, null, null, new HashSet<ExtraService>(), new EnumMap<TyrePosition, Tyre>(TyrePosition.class), BigDecimal.valueOf(355.4)),
//            newOrder(customer, null, null, null, new HashSet<ExtraService>(), tyres, BigDecimal.valueOf(34.32))
//        });
//        for (Order o : orders) {
//            dao.create(o);
//        }
//        //
//        List<Order> all = dao.findAll();
//        assertTrue("Size is not the same", all.size() == orders.size());
//        //
//        Collections.sort(all, orderComparator);
//        Collections.sort(orders, orderComparator);
//        for (int i = 0; i < all.size(); i++) {
//            assertDeepEquals(all.get(i), orders.get(i));
//        }
//    }
//
//    /**
//     * Test of findByCustomer method, of class OrderDAO.
//     */
//    @Test
//    public void testFindByCustomer() {
//        Order o1 = newOrder(customer, null, null, null, extraServices, tyres, BigDecimal.valueOf(223));
//        Order o2 = newOrder(customer, null, null, null, new HashSet<ExtraService>(), tyres, BigDecimal.valueOf(221.5));
//        List<Order> os = Arrays.asList(new Order[]{o1, o2});
//        for (Order o : os) {
//            dao.create(o);
//        }
//        //
//        try {
//            dao.findByCustomer(null);
//            fail("Customer is null and didn't throw exception");
//        } catch (IllegalArgumentException ex) {
//            //ok
//        } catch (Exception ex) {
//            fail("Customer is null and didn't throw appropriate exception");
//        }
//        //
//        try {
//            dao.findByCustomer(new Customer());
//            fail("Customer id is null and didn't throw exception");
//        } catch (IllegalArgumentException ex) {
//            //ok
//        } catch (Exception ex) {
//            fail("Customer ID is null and didn't throw appropriate exception");
//        }
//        //
//        List<Order> orders = dao.findByCustomer(customer);
//        assertTrue("Didn't find the same lists", orders.size() == os.size());
//        Collections.sort(orders, orderComparator);
//        Collections.sort(os, orderComparator);
//        for (int i = 0; i < os.size(); i++) {
//            assertDeepEquals(os.get(i), orders.get(i));
//        }
//    }
//    
//    private static Tyre newTyre(Double diameter, String name, String type, String vendor, BigDecimal price) {
//        Tyre t = new Tyre();
//        t.setDiameter(diameter);
//        t.setName(name);
//        t.setType(type);
//        t.setVendor(vendor);
//        t.setPrice(price);
//        
//        return t;
//    }
//    
//    private static ExtraService newExtraService(String name, String desc, BigDecimal price) {
//        ExtraService es = new ExtraService();
//        es.setName(name);
//        es.setDescription(desc);
//        es.setPrice(price);
//        
//        return es;
//    }
//    
//    private static Customer newCustomer(String firstName, String lastName, String address, String phone) {
//        Customer c = new Customer();
//        c.setFirstName(firstName);
//        c.setLastName(lastName);
//        c.setAddress(address);
//        c.setPhone(phone);
//        
//        return c;
//    }
//    
//    private static Order newOrder(Customer c, Date orderNewDate, Date orderServicedDate, 
//            Date orderPaidDate, Set<ExtraService> extraServices, Map<TyrePosition, Tyre> tyres, BigDecimal totalPrice) {
//        Order o = new Order();
//        o.setCustomer(c);
//        o.setOrderNewDate(orderNewDate);
//        o.setOrderServicedDate(orderServicedDate);
//        o.setOrderPaidDate(orderPaidDate);
//        o.setTyres(tyres);
//        o.setExtraServices(extraServices);
//        o.setTotalPrice(totalPrice);
//        
//        return o;
//    }
//    
//    private static Date newDate(String value) {
//        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
//        df.setTimeZone(TimeZone.getTimeZone("GMT+1"));
//        try {
//            return df.parse(value);
//        } catch (ParseException ex) {
//            Logger.getLogger(OrderDAOTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//    
//    private static boolean equalDates(Date d1, Date d2) {
//        if (d1 == null || d2 == null) {
//            return d1 == null && d2 == null;
//        }
//        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
//        df.setTimeZone(TimeZone.getTimeZone("GMT+1"));
//        
//        return df.format(d1).equals(df.format(d2));
//    }
//    
//    private static void assertDeepEquals(Order o1, Order o2) {
//        assertEquals(o1.getId(), o2.getId());
//        assertEquals(o1.getCustomer(), o2.getCustomer());
//        assertTrue(equalDates(o1.getOrderNewDate(), o2.getOrderNewDate()));
//        assertTrue(equalDates(o1.getOrderServicedDate(), o2.getOrderServicedDate()));
//        assertTrue(equalDates(o1.getOrderPaidDate(), o2.getOrderPaidDate()));
//        assertTrue((o1.getTotalPrice() != null && o1.getTotalPrice().compareTo(o2.getTotalPrice()) == 0) || 
//                (o1.getTotalPrice() == null && o2.getTotalPrice() == null));
//        assertDeepEquals(o1.getTyres(), o2.getTyres());
//        List<ExtraService> es1 = new ArrayList<ExtraService>(o1.getExtraServices());
//        List<ExtraService> es2 = new ArrayList<ExtraService>(o2.getExtraServices());
//        Collections.sort(es1, esComparator);
//        Collections.sort(es2, esComparator);
//        assertDeepEquals(es1, es2);
//    }
//    
//    private static void assertDeepEquals(List<ExtraService> es1, List<ExtraService> es2) {
//        assertEquals(es1 == null, es2 == null);
//        if (es1 != null) {
//            assertTrue(es1.size() == es2.size());
//            for (int i = 0; i < es1.size(); i++) {
//                assertEquals(es1.get(i), es2.get(i));
//            }
//        }
//    }
//    
//    private static void assertDeepEquals(Map<TyrePosition, Tyre> t1, Map<TyrePosition, Tyre> t2) {
//        assertEquals(t1 == null, t2 == null);
//        if (t1 != null) {
//            for (TyrePosition tp : TyrePosition.values()) {
//                assertEquals(t1.get(tp), t2.get(tp));
//            }
//        }
//    }
//    
//    private void removeAll() {
//        List<Order> orders = dao.findAll();
//        for (Order o : orders) {
//            dao.remove(o);
//        }
//        List<Customer> customers = customerDAO.findAll();
//        for (Customer c : customers) {
//            customerDAO.remove(c);
//        }
//        List<Tyre> ts = tyreDAO.findAll();
//        for (Tyre t : ts) {
//            tyreDAO.remove(t);
//        }
//        List<ExtraService> ess = extraServiceDAO.findAll();
//        for (ExtraService es : ess) {
//            extraServiceDAO.remove(es);
//        }
//    }
//    
//    private static Comparator<Order> orderComparator = new Comparator<Order>() {
//
//        @Override
//        public int compare(Order t, Order t1) {
//            return t.getId().compareTo(t1.getId());
//        }
//    };
//    
//    private static Comparator<ExtraService> esComparator = new Comparator<ExtraService>() {
//
//        @Override
//        public int compare(ExtraService t, ExtraService t1) {
//            return t.getId().compareTo(t1.getId());
//        }
//    };
}
