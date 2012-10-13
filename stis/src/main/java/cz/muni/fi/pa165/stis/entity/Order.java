package cz.muni.fi.pa165.stis.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 * type = 255/40ZR19
 * vendor = Michelin
 * size = 27"
 * car = BMW M5
 * 
 * @author xmravec1
 */
@Entity
public class Order implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //
    @ManyToOne
    private Customer customer;
    private String carType;
    @ManyToMany
    private List<ExtraService> extraServices;
    @ManyToMany
    private Map<TyrePosition, Tyre> tyres;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date orderNewDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date orderServicedDate;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date orderPaidDate;

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

    public List<ExtraService> getExtraServices() {
        return extraServices;
    }

    public void setExtraServices(List<ExtraService> extraServices) {
        this.extraServices = extraServices;
    }

    public Map<TyrePosition, Tyre> getTyres() {
        return tyres;
    }

    public void setTyres(Map<TyrePosition, Tyre> tyres) {
        this.tyres = tyres;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Order other = (Order) obj;
        if ((this.id == null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", customer=" + customer + ", carType=" + carType + ", extraServices=" + extraServices + ", tyres=" + tyres + ", orderNewDate=" + orderNewDate + ", orderServicedDate=" + orderServicedDate + ", orderPaidDate=" + orderPaidDate + '}';
    }

}