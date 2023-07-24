package ro.msg.learning.shop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders extends BaseClass {
    @ManyToOne
    private Customer customer;
    private LocalDateTime createdAt;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "orders", cascade = CascadeType.PERSIST)
    private Set<OrderDetail> ordersDetails;
}
