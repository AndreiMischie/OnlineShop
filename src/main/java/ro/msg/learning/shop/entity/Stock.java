package ro.msg.learning.shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.entity.compositeId.StockId;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(StockId.class)
public class Stock {
    @ManyToOne
    @Id
    private Product product;
    @ManyToOne
    @Id
    private Location location;
    private int quantity;
}
