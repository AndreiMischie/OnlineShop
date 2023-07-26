package ro.msg.learning.shop.service;

import ro.msg.learning.shop.entity.Customer;

import java.util.UUID;

public interface CustomerService {
    Customer getCustomer(UUID id);
}
