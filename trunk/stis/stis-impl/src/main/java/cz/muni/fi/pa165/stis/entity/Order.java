package cz.muni.fi.pa165.stis.entity;

import cz.muni.fi.pa165.stis.dto.TyrePosition;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author xmravec1
 */
@Entity
@Table(name = "CUSTOMER_ORDER")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //
    @ManyToOne
    private Customer customer;
    private String carType;
    @ManyToMany(fetch = FetchType.EAGER)
    private Map<TyrePosition, Tyre> tyres;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<ExtraService> extraServices;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date orderNewDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date orderServicedDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
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
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
        if ((this.id == null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", customer=" + customer + ", carType=" + carType + ", tyres=" + tyres + ", extraServices=" + extraServices + ", orderNewDate=" + orderNewDate + ", orderServicedDate=" + orderServicedDate + ", orderPaidDate=" + orderPaidDate + ", totalPrice=" + totalPrice + '}';
    }

}
