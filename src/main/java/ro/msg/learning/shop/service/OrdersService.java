package ro.msg.learning.shop.service;

import ro.msg.learning.shop.dto.ProductQuantityPair;
import ro.msg.learning.shop.entity.Orders;

import java.util.Collection;
import java.util.List;

public interface OrdersService {
    public abstract Orders createOrders(Orders order);
    public abstract void handleOrderCreation(Orders order, List<ProductQuantityPair> productQuantityPairList);
}
