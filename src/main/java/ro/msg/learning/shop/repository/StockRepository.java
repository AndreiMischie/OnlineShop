package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.entity.compositeId.StockId;

public interface StockRepository extends JpaRepository<Stock, StockId> {
}
