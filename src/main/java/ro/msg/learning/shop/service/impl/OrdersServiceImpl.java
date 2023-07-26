package ro.msg.learning.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.util.OrderDetailsUtil;
import ro.msg.learning.shop.dto.OrdersDto;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.mapper.OrdersMapper;
import ro.msg.learning.shop.repository.OrdersRepository;
import ro.msg.learning.shop.service.OrdersService;
import ro.msg.learning.shop.strategy.OrderStrategy;
import ro.msg.learning.shop.util.ProductQuantityPairUtil;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrderStrategy orderStrategy;

    private final OrdersRepository ordersRepository;

    public Orders createOrders(Orders order) {
        ordersRepository.save(order);
        return order;
    }

    public OrdersDto handleOrderCreation(Orders order, Set<ProductQuantityPairUtil> productQuantityPairUtilList) {
        Set<OrderDetailsUtil> orderDetailsUtils = orderStrategy.handleOrderCreation(order, productQuantityPairUtilList);
        return OrdersMapper.INSTANCE.toOrdersDto(order, productQuantityPairUtilList);
    }
}