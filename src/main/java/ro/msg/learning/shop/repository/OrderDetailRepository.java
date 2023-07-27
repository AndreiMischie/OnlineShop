package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.OrderDetail;
import ro.msg.learning.shop.entity.compositeId.OrderDetailId;

import java.util.UUID;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
}
