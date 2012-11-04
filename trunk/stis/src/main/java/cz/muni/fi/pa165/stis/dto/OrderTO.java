package cz.muni.fi.pa165.stis.dto;

import cz.muni.fi.pa165.stis.entity.Customer;
import cz.muni.fi.pa165.stis.entity.ExtraService;
import cz.muni.fi.pa165.stis.entity.Tyre;
import cz.muni.fi.pa165.stis.entity.TyrePosition;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Peter Mravec
 */
public class OrderTO implements Serializable {
    private Long id;
    private Customer customer;
    private String carType;
    private Map<TyrePosition, Tyre> tyres;
    private Set<ExtraService> extraServices;
    private Date orderNewDate;
    private Date orderServicedDate;
    private Date orderPaidDate;
    private BigDecimal totalPrice;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Map<TyrePosition, Tyre> getTyres() {
        return tyres;
    }

    public void setTyres(Map<TyrePosition, Tyre> tyres) {
        this.tyres = tyres;
    }

    public Set<ExtraService> getExtraServices() {
        return extraServices;
    }

    public void setExtraServices(Set<ExtraService> extraServices) {
        this.extraServices = extraServices;
    }

    public Date getOrderNewDate() {
        return orderNewDate;
    }

    public void setOrderNewDate(Date orderNewDate) {
        this.orderNewDate = orderNewDate;
    }

    public Date getOrderServicedDate() {
        return orderServicedDate;
    }

    public void setOrderServicedDate(Date orderServicedDate) {
        this.orderServicedDate = orderServicedDate;
    }

    public Date getOrderPaidDate() {
        return orderPaidDate;
    }

    public void setOrderPaidDate(Date orderPaidDate) {
        this.orderPaidDate = orderPaidDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderTO{" + "id=" + id + ", customer=" + customer + ", carType=" + carType + ", tyres=" + tyres + ", extraServices=" + extraServices + ", orderNewDate=" + orderNewDate + ", orderServicedDate=" + orderServicedDate + ", orderPaidDate=" + orderPaidDate + ", totalPrice=" + totalPrice + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OrderTO other = (OrderTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
