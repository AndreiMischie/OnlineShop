package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.OrdersDto;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.entity.Orders;
import ro.msg.learning.shop.mapper.OrdersMapper;
import ro.msg.learning.shop.service.CustomerService;
import ro.msg.learning.shop.service.OrdersService;

@RestController
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/orders")
    public ResponseEntity<Object> postOrder(@RequestBody OrdersDto ordersDto){
        Customer customer = customerService.getCustomer(ordersDto.getCustomerId());
        Orders order = OrdersMapper.INSTANCE.toOrders(ordersDto,customer);
        ordersService.handleOrderCreation(order,ordersDto.getProducts());
        return new ResponseEntity<>("Order Created!", HttpStatus.OK);
    }
}
