package ro.msg.learning.shop.service;

import ro.msg.learning.shop.dto.ProductQuantityPairDto;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.entity.Orders;

import java.util.List;
import java.util.UUID;

public interface OrdersService {
    Orders createOrders(Orders order);

    Orders handleOrderCreation(Orders order, List<ProductQuantityPairDto> productQuantityPairDtoList, Customer customer);

    Customer getCustomer(UUID id);
}
