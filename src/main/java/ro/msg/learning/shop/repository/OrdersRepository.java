package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
