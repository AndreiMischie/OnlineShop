package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.entity.StockId;

import java.util.UUID;

public interface StockRepository extends JpaRepository<Stock, StockId> {
}
