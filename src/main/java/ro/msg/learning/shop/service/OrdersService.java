package ro.msg.learning.shop.service;

import ro.msg.learning.shop.dto.ProductQuantityPairDto;
import ro.msg.learning.shop.entity.Orders;

import java.util.List;

public interface OrdersService {
    Orders createOrders(Orders order);

    Orders handleOrderCreation(Orders order, List<ProductQuantityPairDto> productQuantityPairDtoList);


}
