package cz.muni.fi.pa165.stis.web;

import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.dto.ExtraServiceTO;
import cz.muni.fi.pa165.stis.dto.OrderTO;
import cz.muni.fi.pa165.stis.dto.TyrePosition;
import cz.muni.fi.pa165.stis.dto.TyreTO;
import cz.muni.fi.pa165.stis.service.CustomerService;
import cz.muni.fi.pa165.stis.service.ExtraServiceService;
import cz.muni.fi.pa165.stis.service.OrderService;
import cz.muni.fi.pa165.stis.service.TyreService;
import cz.muni.fi.pa165.stis.web.wrapper.CustomerTOWrapper;
import cz.muni.fi.pa165.stis.web.wrapper.OrderTOWrapper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.integration.spring.SpringBean;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidateNestedProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Peter Mravec
 */
@UrlBinding("/order/{$event}/")
public class OrderActionBean implements ActionBean {
    private static final Logger logger = LoggerFactory.getLogger(OrderActionBean.class);
    private ActionBeanContext context;
    
    @SpringBean
    private OrderService service;    
    @SpringBean
    private CustomerService custService;
    @SpringBean
    private TyreService tyreService;
    @SpringBean
    private ExtraServiceService esService;
    
    @ValidateNestedProperties(value = {
            @Validate(on = {"create", "save"}, field = "customer.id", required = true),
            @Validate(on = {"create", "save"}, field = "carType", required = true, minvalue = 1)
    })
    private OrderTOWrapper order;
    
    private List<CustomerTO> customers = new ArrayList<CustomerTO>();
    private List<CustomerTOWrapper> customers1 = new ArrayList<CustomerTOWrapper>();
    
    public List<CustomerTOWrapper> getAllCustomers(){
        customers = custService.findAll();
        customers1.clear();
        for (CustomerTO c : customers){
            CustomerTOWrapper c1 = new CustomerTOWrapper();
            
            c1.setId(c.getId());
            c1.setFullName(c.getFirstName() + " " + c.getLastName());
            
            customers1.add(c1);
        }
        
        return customers1;
    }
    
    public List<TyreTO> getAllTyres(){
        return tyreService.findAll();
    }
    
    public TyrePosition[] getTyrePositions() {
        return TyrePosition.values();
    }
    
    public List<ExtraServiceTO> getAllExtraServices(){
        return esService.findAll();
    }

    @DefaultHandler
    public Resolution list() {
        logger.info("listing");
        return new ForwardResolution("/order/list.jsp");
    }
    
    public List<OrderTO> getAllOrders() {
        logger.debug("getting all orders");
        return service.findAll();
    }
    
    public Resolution create() {
        Date date = new Date();
        order.setOrderNewDate(date);
        processBeforeSave(order);
        logger.debug("create() {}", order);
        service.create(order);
        //
        return new RedirectResolution(OrderActionBean.class, "list");
    }
    
    public Resolution edit() {
        logger.debug("edit() order={}", order);
        return new ForwardResolution("/order/edit.jsp");
    }

    public Resolution save() {
        processBeforeSave(order);
        logger.debug("save() order={}", order);
        service.update(order);
        return new RedirectResolution(this.getClass(), "list");
    }
    
    public Resolution delete() {
        Long id = Long.parseLong(context.getRequest().getParameter("order.id"));
        OrderTO ot = service.get(id);
        logger.debug("delete() {}", ot);
        //
        service.remove(ot);
        //
        return new RedirectResolution(OrderActionBean.class, "list");
    }
    
    public Resolution done() {
        Date date = new Date();
        Long id = Long.parseLong(context.getRequest().getParameter("order.id"));
        OrderTO ot = service.get(id);
        ot.setOrderServicedDate(date);
        logger.debug("done() {}", ot);
        service.update(ot);
        //
        return new RedirectResolution(OrderActionBean.class, "list");
    }

    public Resolution paid() {
        Date date = new Date();
        Long id = Long.parseLong(context.getRequest().getParameter("order.id"));
        OrderTO ot = service.get(id);
        ot.setOrderPaidDate(date);
        logger.debug("paid() {}", ot);
        service.update(ot);
        //
        return new RedirectResolution(OrderActionBean.class, "list");
    }

    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
    public void loadOrderFromDatabase() {
        String ids = context.getRequest().getParameter("order.id");
        if (ids != null) {
            order = new OrderTOWrapper(service.get(Long.parseLong(ids)));
        }
    }

    public OrderTOWrapper getOrder() {
        return order;
    }

    public void setOrder(OrderTOWrapper order) {
        this.order = order;
    }
    
    @Override
    public void setContext(ActionBeanContext abc) {
        this.context = abc;
    }

    @Override
    public ActionBeanContext getContext() {
        return context;
    }
    
    private void processBeforeSave(OrderTOWrapper wrapper) {
        BigDecimal price = BigDecimal.ZERO;
        //
        Set<ExtraServiceTO> exSs = new HashSet<>();
        if (wrapper.getExtraServiceIds() != null) {
            for (Long l : order.getExtraServiceIds()) {
                ExtraServiceTO esto = esService.get(l);
                if (esto != null && esto.getPrice() != null) {
                    price = price.add(esto.getPrice());
                    exSs.add(esto);
                }
            }
        }
        
        Map<Long, TyreTO> tyres = new HashMap<>();
        for (Map.Entry<TyrePosition, TyreTO> em : wrapper.getTyres().entrySet()) {
            if (em.getValue() == null) {
                continue;
            }
            Long id = em.getValue().getId();
            if (id != null) {
                TyreTO tto = tyres.get(id);
                if (tto == null) {
                    tto = tyreService.get(id);
                    tyres.put(id, tto);
                }
                if (tto != null && tto.getPrice() != null) {
                    price = price.add(tto.getPrice());
                }
            }
        }
        wrapper.setExtraServices(exSs);
        wrapper.setTotalPrice(price);
    }
    
}
