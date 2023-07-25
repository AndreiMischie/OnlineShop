package ro.msg.learning.shop.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location extends BaseClass {
    private String name;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "location")
    private Set<Stock> stocks;
    @OneToMany(mappedBy = "shippedFrom")
    private Set<OrderDetail> ordersDetails;
}
