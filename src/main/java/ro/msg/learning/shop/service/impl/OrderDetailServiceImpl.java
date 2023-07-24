package ro.msg.learning.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.OrderDetail;
import ro.msg.learning.shop.repository.OrderDetailRepository;
import ro.msg.learning.shop.service.OrderDetailService;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetail createOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
        return orderDetail;
    }
}

