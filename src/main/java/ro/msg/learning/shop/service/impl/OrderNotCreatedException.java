package ro.msg.learning.shop.service.impl;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OrderNotCreatedException extends RuntimeException {
    public OrderNotCreatedException(String message) {
        super(message);
    }
}

