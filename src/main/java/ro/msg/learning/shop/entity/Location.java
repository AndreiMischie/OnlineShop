package ro.msg.learning.shop.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor @AllArgsConstructor
public class Location extends BaseClass{
    private String Name;
    @Embedded
    private Address Address;
    @OneToMany(mappedBy = "Location")
    private Set<Stock> Stocks;
    @OneToMany(mappedBy = "ShippedFrom")
    private Set<OrderDetail> OrdersDetails;
}
