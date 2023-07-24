package ro.msg.learning.shop.service;

import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.entity.compositeId.StockId;

public interface StockService {
    public abstract Stock getStock(StockId id);

    public abstract Stock updateStock(Stock stock);
}
