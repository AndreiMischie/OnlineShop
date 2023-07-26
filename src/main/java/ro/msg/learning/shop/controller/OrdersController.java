package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.OrdersDto;
import ro.msg.learning.shop.dto.ProductQuantityPairDto;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.mapper.OrdersMapper;
import ro.msg.learning.shop.mapper.ProductQuantityPairMapper;
import ro.msg.learning.shop.service.OrdersService;
import ro.msg.learning.shop.util.ProductQuantityPairUtil;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping
    public ResponseEntity<OrdersDto> postOrder(@RequestBody OrdersDto ordersDto) {
        Orders order = OrdersMapper.INSTANCE.toOrders(ordersDto);
        Set<ProductQuantityPairUtil> productQuantityPairUtils = new HashSet<>();
        for (ProductQuantityPairDto productQuantityPairDto : ordersDto.getProducts()) {
            ProductQuantityPairUtil productQuantityPairUtil = ProductQuantityPairMapper.INSTANCE.toProductQuantityPair(productQuantityPairDto);
            productQuantityPairUtils.add(productQuantityPairUtil);
        }
        OrdersDto orderDto = ordersService.handleOrderCreation(order, productQuantityPairUtils);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }
}
