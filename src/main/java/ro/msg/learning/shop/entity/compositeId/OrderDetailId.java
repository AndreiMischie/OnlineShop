package ro.msg.learning.shop.entity.compositeId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.entity.Product;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailId implements Serializable {
    private Orders orders;
    private Product product;
}
