package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Orders;

import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Orders, UUID> {
}
