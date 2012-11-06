package cz.muni.fi.pa165.stis.service;

import cz.muni.fi.pa165.stis.dao.OrderDAO;
import cz.muni.fi.pa165.stis.dao.OrderDAOTest;
import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.dto.OrderTO;
import cz.muni.fi.pa165.stis.entity.Customer;
import cz.muni.fi.pa165.stis.entity.ExtraService;
import cz.muni.fi.pa165.stis.entity.Order;
import cz.muni.fi.pa165.stis.entity.Tyre;
import cz.muni.fi.pa165.stis.entity.TyrePosition;
import cz.muni.fi.pa165.stis.service.impl.OrderServiceImpl;
import cz.muni.fi.pa165.stis.util.TyreEnumMapBuilder;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.FieldsMappingOptions;
import org.dozer.loader.api.TypeMappingOptions;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;
import org.junit.Before;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author Peter Mravec
 */
@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    
    private OrderServiceImpl orderService;
    @Mock
    private OrderDAO dao;
    private DozerBeanMapper mapper;
    
    private Customer customer;
    private Set<ExtraService> extraServices;
    private Map<TyrePosition, Tyre> tyres;
    private Order order;
    private OrderTO orderTO;
    
    @Before
    public void setUp() {
        mapper = new DozerBeanMapper();
        mapper.addMapping(new TyreEnumMapBuilder());
        //
        orderService = new OrderServiceImpl();
        ReflectionTestUtils.setField(orderService, "orderDAO", dao);
        ReflectionTestUtils.setField(orderService, "mapper", mapper);
        
        customer = newCustomer("Jozin", "Zbazin", "Baziny 22", "005544");
        customer.setId(1L);
        //
        ExtraService es1 = newExtraService("Umytie", "Umytie okien a zrkadiel", BigDecimal.valueOf(22.2));
        es1.setId(1L);
        ExtraService es2 = newExtraService("Vysavanie", "Vysavanie auta", BigDecimal.valueOf(122.5));
        es2.setId(2L);
        extraServices = new HashSet<ExtraService>(Arrays.asList(new ExtraService[]{es1, es2}));
        //
        Tyre t1 = newTyre(17D, "MM22", "EZ256", "Michellin", BigDecimal.valueOf(222));
        t1.setId(1L);
        Tyre t2 = newTyre(17D, "MM23", "EZ257", "Michellin", BigDecimal.valueOf(222));
        t2.setId(2L);
        Tyre t3 = newTyre(18D, "PR89", "NT99", "Pirelli", BigDecimal.valueOf(253.4));
        t3.setId(3L);
        tyres = new EnumMap<TyrePosition, Tyre>(TyrePosition.class);
        tyres.put(TyrePosition.FRONT_LEFT, t1);
        tyres.put(TyrePosition.FRONT_RIGHT, t2);
        tyres.put(TyrePosition.REAR_LEFT, t3);
        order = newOrder(customer, newDate("22.9.2012 12:13:15"), null, null, extraServices, tyres, BigDecimal.ZERO);
        orderTO = mapper.map(order, OrderTO.class);
    }
    
    @After
    public void tearDown() {
        orderService = null;
    }
    
    @Test
    public void testExceptions() {
        try {
            orderService.create(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            orderService.update(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            orderService.get(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            orderService.remove(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            orderService.findByCustomer(null);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        
        try {
            orderService.update(orderTO);
            fail("exception should be thrown A");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        try {
            orderService.remove(orderTO);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
        orderTO.setId(1L);
        try {
            orderService.create(orderTO);
            fail("exception should be thrown");
        } catch (IllegalArgumentException ex) {
            // ok
        }
    }
    
    @Test
    public void testCreate() {
        orderService.create(orderTO);
        final Order o = order;
        verify(dao).create(argThat(new BaseMatcher<Order>() {

            @Override
            public boolean matches(Object item) {
                if (!(item instanceof Order)) {
                    return false;
                }
                
                Order no = (Order) item;
                if (!Objects.equals(no.getCarType(), o.getCarType())) {
                    return false;
                }
                if (!Objects.equals(no.getCustomer(), o.getCustomer())) {
                    return false;
                }
                if (!Objects.equals(no.getOrderNewDate(), o.getOrderNewDate())) {
                    return false;
                }
                if (!Objects.equals(no.getOrderPaidDate(), o.getOrderPaidDate())) {
                    return false;
                }
                if (!Objects.equals(no.getOrderServicedDate(), o.getOrderServicedDate())) {
                    return false;
                }
                if (!Objects.equals(no.getTotalPrice(), o.getTotalPrice())) {
                    return false;
                }
                if (!(no.getExtraServices().containsAll(o.getExtraServices()) && o.getExtraServices().containsAll(no.getExtraServices()))) {
                    return false;
                }
                if (no.getTyres() == null || o.getTyres() == null) {
                    if (no.getTyres() != null || o.getTyres() != null) {
                        return false;
                    }
                }
                if (no.getTyres().size() != o.getTyres().size()) {
                    return false;
                }
                for (Map.Entry<TyrePosition, Tyre> me : no.getTyres().entrySet()) {
                    Tyre t = o.getTyres().get(me.getKey());
                    if (!Objects.equals(t, me.getValue())) {
                        return false;
                    }
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
        order.setId(1L);
        orderTO.setId(1L);
        when(dao.get(1L)).thenReturn(order);
        OrderTO ot = orderService.get(1L);
        
        assertEquals(ot, orderTO);
    }

    @Test
    public void testUpdate() {
        orderTO.setId(1L);
        order.setId(1L);
        
        orderService.update(orderTO);
        verify(dao).update(order);
    }

    @Test
    public void testRemove() {
        orderTO.setId(1L);
        order.setId(1L);
        
        orderService.remove(orderTO);
        verify(dao).remove(order);
    }
    
    @Test
    public void testFindAll() {
        Customer c = newCustomer("Istvan", "Lelkes", "Hajovna 12", "54544");
        c.setId(22L);
        Order o = newOrder(c, null, null, null, extraServices, tyres, BigDecimal.ZERO);
        order.setId(1L);
        orderTO.setId(1L);
        
        List<Order> orders = Arrays.asList(new Order[] {order, o});
        List<OrderTO> ctos = Arrays.asList(new OrderTO[] {orderTO, mapper.map(o, OrderTO.class)});
        when(dao.findAll()).thenReturn(orders);
        
        List<OrderTO> res = orderService.findAll();
        
        assertTrue(res.containsAll(ctos) && ctos.containsAll(res));
    }
    
    @Test
    public void testFindByCustomer() {
        Order o = newOrder(customer, null, null, null, extraServices, tyres, BigDecimal.valueOf(223));
        order.setId(1L);
        orderTO.setId(1L);
        
        List<Order> orders = Arrays.asList(new Order[] {o});
        List<OrderTO> otos = Arrays.asList(new OrderTO[] {mapper.map(o, OrderTO.class)});
        when(dao.findByCustomer(customer)).thenReturn(orders);
        
        List<OrderTO> res = orderService.findByCustomer(mapper.map(customer, CustomerTO.class));
        
        assertTrue(res.containsAll(otos) && otos.containsAll(res));
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
            Date orderPaidDate, Set<ExtraService> extraServices, Map<TyrePosition, Tyre> tyres, BigDecimal totalPrice) {
        Order o = new Order();
        o.setCustomer(c);
        o.setOrderNewDate(orderNewDate);
        o.setOrderServicedDate(orderServicedDate);
        o.setOrderPaidDate(orderPaidDate);
        o.setTyres(tyres);
        o.setExtraServices(extraServices);
        o.setTotalPrice(totalPrice);
        
        return o;
    }
    
    private static Date newDate(String value) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        df.setTimeZone(TimeZone.getTimeZone("GMT+1"));
        try {
            return df.parse(value);
        } catch (ParseException ex) {
            Logger.getLogger(OrderDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }    
}
