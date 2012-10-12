package cz.muni.fi.pa165.stis.entity;

import java.util.Date;

/**
 * type = 255/40ZR19
 * vendor = Michelin
 * size = 27"
 * car = BMW M5
 * 
 * @author xmravec1
 */
public class Order {
    private Long id;
    private Customer customer;
    private String car;
    private Tyre tyre;
    private Date orderDate;

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

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public Tyre getTyre() {
        return tyre;
    }

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
