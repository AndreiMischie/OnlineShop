package ro.msg.learning.shop.service;

import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.entity.compositeId.StockId;

public interface StockService {
    Stock getStock(StockId id);

    Stock updateStock(Stock stock);
}
