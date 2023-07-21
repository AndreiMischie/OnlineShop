package ro.msg.learning.shop.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor @AllArgsConstructor
public class Orders extends BaseClass{
    @ManyToOne
    private Customer customer;
    private LocalDateTime createdAt;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "orders")
    private Set<OrderDetail> ordersDetails;
}
