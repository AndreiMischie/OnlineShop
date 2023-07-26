package ro.msg.learning.shop.service;

import ro.msg.learning.shop.dto.OrdersDto;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.util.ProductQuantityPairUtil;

import java.util.Set;

public interface OrdersService {
    Orders createOrders(Orders order);

    OrdersDto handleOrderCreation(Orders order, Set<ProductQuantityPairUtil> productQuantityPairUtilList);


}
