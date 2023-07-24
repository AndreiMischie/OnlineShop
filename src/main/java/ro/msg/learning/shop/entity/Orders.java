package ro.msg.learning.shop.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders extends BaseClass {
    @ManyToOne
    private Customer customer;
    private LocalDateTime createdAt;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "orders")
    private Set<OrderDetail> ordersDetails;
}
