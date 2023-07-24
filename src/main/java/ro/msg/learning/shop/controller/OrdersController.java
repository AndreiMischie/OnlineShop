package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.OrdersDto;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.mapper.OrdersMapper;
import ro.msg.learning.shop.service.OrdersService;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @PostMapping
    public ResponseEntity<OrdersDto> postOrder(@RequestBody OrdersDto ordersDto) {
        Orders order = OrdersMapper.INSTANCE.toOrders(ordersDto);
        Orders newOrder = ordersService.handleOrderCreation(order, ordersDto.getProducts());
        OrdersDto newOrdersDto = OrdersMapper.INSTANCE.toOrdersDto(newOrder, ordersDto.getProducts());
        return new ResponseEntity<>(newOrdersDto, HttpStatus.CREATED);
    }
}
