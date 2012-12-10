package cz.muni.fi.pa165.stis.web.wrapper;

import cz.muni.fi.pa165.stis.dto.ExtraServiceTO;
import cz.muni.fi.pa165.stis.dto.OrderTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dusan Svancara
 */
public class OrderTOWrapper extends OrderTO {
    private List<Long> extraServiceIds;
    
    public OrderTOWrapper() {
        
    }
    
    public OrderTOWrapper(OrderTO order) {
        setCustomer(order.getCustomer());
        setCarType(order.getCarType());
        setExtraServices(order.getExtraServices());
        setId(order.getId());
        setOrderNewDate(order.getOrderNewDate());
        setOrderPaidDate(order.getOrderPaidDate());
        setOrderServicedDate(order.getOrderServicedDate());
        setTyres(order.getTyres());
        setTotalPrice(order.getTotalPrice());
        if (this.getExtraServices() != null) {
            List<Long> ids = new ArrayList<>();
            for (ExtraServiceTO esto : this.getExtraServices()) {
                ids.add(esto.getId());
            }
            this.extraServiceIds = ids;
        }
    }

    public List<Long> getExtraServiceIds() {
        return extraServiceIds;
    }

    public void setExtraServiceIds(List<Long> extraServiceIds) {
        this.extraServiceIds = extraServiceIds;
    }
    
    @Override
    public String toString() {
        return "OrderTOWrapper{" + "extraServiceIds=" + extraServiceIds + '}' + super.toString();
    }
}
