package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.dto.ProductQuantityPairDto;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.entity.Orders;

import java.util.List;

public interface OrderStrategy {
    Orders singleLocationStrategy(Orders order, List<ProductQuantityPairDto> productQuantityPairDtoList, Customer customer);
}
