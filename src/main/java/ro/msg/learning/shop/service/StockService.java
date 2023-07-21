package ro.msg.learning.shop.service;

import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.entity.StockId;

import java.util.Collection;

public interface StockService {
    public abstract Collection<Stock> getStocks();
    public abstract Stock getStock(StockId id);
    public abstract Stock updateStock(Stock stock);
}
