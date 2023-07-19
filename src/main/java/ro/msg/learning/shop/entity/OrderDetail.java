package ro.msg.learning.shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class OrderDetail {
    @ManyToOne @Id
    private Orders Orders;
    @ManyToOne @Id
    private Product Product;
    @ManyToOne @Id
    private Location ShippedFrom;
    private int Quantity;
}
