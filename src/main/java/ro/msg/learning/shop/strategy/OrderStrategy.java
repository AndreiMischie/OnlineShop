package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.util.OrderDetailsUtil;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.util.ProductQuantityPairUtil;

import java.util.Set;

public interface OrderStrategy {
    Set<OrderDetailsUtil> handleOrderCreation(Orders order, Set<ProductQuantityPairUtil> productQuantityPairUtilList);
}
