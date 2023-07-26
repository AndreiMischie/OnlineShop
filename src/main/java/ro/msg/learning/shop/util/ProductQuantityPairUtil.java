package ro.msg.learning.shop.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductQuantityPairUtil {
    private UUID productId;
    private int quantity;
}
