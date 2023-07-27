package ro.msg.learning.shop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders extends BaseClass {
    @ManyToOne
    @JsonBackReference
    private Customer customer;
    private LocalDateTime createdAt;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "orders")
    @JsonManagedReference
    private Set<OrderDetail> ordersDetails;
}
