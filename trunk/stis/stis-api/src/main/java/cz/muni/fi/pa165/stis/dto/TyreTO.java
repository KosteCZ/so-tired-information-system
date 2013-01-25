package cz.muni.fi.pa165.stis.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Michal Toth
 */
public class TyreTO implements Serializable {
    private Long id;
    private String type;
    private String name;
    private Double diameter;
    private BigDecimal price;
    private String vendor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getDiameter() {
        return diameter;
    }

    public void setDiameter(Double diameter) {
        this.diameter = diameter;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    @Override
    public int hashCode() {
        int hash = (this.id != null ? this.id.hashCode() : 0);
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
        final TyreTO other = (TyreTO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TyreTO{" + "id=" + id + ", type=" + type + ", name=" + name + ", diameter=" + diameter + ", price=" + price + ", vendor=" + vendor + '}';
    }
}
