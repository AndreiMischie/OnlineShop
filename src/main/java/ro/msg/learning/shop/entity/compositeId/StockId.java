package ro.msg.learning.shop.entity.compositeId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockId implements Serializable {
    private Product product;
    private Location location;
}
