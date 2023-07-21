package ro.msg.learning.shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@IdClass(OrderDetailId.class)
public class OrderDetail {
    @ManyToOne @Id
    private Orders orders;
    @ManyToOne @Id
    private Product product;
    @ManyToOne @Id
    private Location shippedFrom;
    private int quantity;
}
