package cz.muni.fi.pa165.stis.web.wrapper;

import cz.muni.fi.pa165.stis.dto.OrderTO;
import java.util.List;

/**
 *
 * @author Dusan Svancara
 */
public class OrderTOWrapper extends OrderTO {
    private List<Long> extraServiceIds;
    
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
    }

    public List<Long> getExtraServiceIds() {
        return extraServiceIds;
    }

    public void setExtraServiceIds(List<Long> extraServiceIds) {
        this.extraServiceIds = extraServiceIds;
    }
}
