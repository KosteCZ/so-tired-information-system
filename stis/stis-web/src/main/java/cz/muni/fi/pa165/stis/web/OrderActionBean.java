package cz.muni.fi.pa165.stis.web;

import cz.muni.fi.pa165.stis.dto.CustomerTO;
import cz.muni.fi.pa165.stis.dto.ExtraServiceTO;
import cz.muni.fi.pa165.stis.dto.OrderTO;
import cz.muni.fi.pa165.stis.dto.TyreTO;
import cz.muni.fi.pa165.stis.service.CustomerService;
import cz.muni.fi.pa165.stis.service.ExtraServiceService;
import cz.muni.fi.pa165.stis.service.OrderService;
import cz.muni.fi.pa165.stis.service.TyreService;
import java.util.ArrayList;
import java.util.List;
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
    
    private OrderTO order;
    
    private List<CustomerTO> customers = new ArrayList<CustomerTO>();
    private List<CustomerTOWrap> customers1 = new ArrayList<CustomerTOWrap>();
    
    public List<CustomerTOWrap> getAllCustomers(){
        customers = custService.findAll();
        for(CustomerTO c : customers){
            CustomerTOWrap c1 = new CustomerTOWrap();
            c1.setId(c.getId());
            c1.setFullName(c.getFirstName()+" "+c.getLastName());
            customers1.add(c1);
        }
        return customers1;
    }
    
    public List<TyreTO> getAllTyres(){
        return tyreService.findAll();
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
        logger.debug("create() {}", order);
        service.create(order);
        //
        return new RedirectResolution(OrderActionBean.class, "list");
    }

//    @Before(stages = LifecycleStage.BindingAndValidation, on = {"edit", "save"})
//    public void loadESFromDatabase() {
//        String ids = context.getRequest().getParameter("order.id");
//        if (ids != null) {
//            order = service.get(Long.parseLong(ids));
//        }
//    }

    public OrderTO getOrder() {
        return order;
    }

    public void setOrder(OrderTO order) {
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
    
    public class CustomerTOWrap{
        private Long id;
        private String fullName;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }
    }
}
