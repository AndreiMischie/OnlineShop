package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @PostMapping
    public ResponseEntity<Orders> postOrder(@RequestBody OrdersDto ordersDto){
        Customer customer = ordersService.getCustomer(ordersDto.getCustomerId());
        Orders order = OrdersMapper.INSTANCE.toOrders(ordersDto,customer);
        Orders newOrder = ordersService.handleOrderCreation(order,ordersDto.getProducts(), customer);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }
}
