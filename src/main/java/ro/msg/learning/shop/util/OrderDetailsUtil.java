package ro.msg.learning.shop.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;

@Data
@AllArgsConstructor
public class OrderDetailsUtil {
    private Location shippedFrom;
    private Product product;
    private int quantity;
}
