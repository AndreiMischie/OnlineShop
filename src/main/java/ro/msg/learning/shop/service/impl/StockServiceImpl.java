package ro.msg.learning.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.entity.StockId;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.StockService;

import java.util.Collection;
import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepository stockRepository;

    @Override
    public Collection<Stock> getStocks() {
        return stockRepository.findAll();
    }

    @Override
    public Stock getStock(StockId id) {
        Optional<Stock> stock = stockRepository.findById(id);
        return stock.orElse(null);
    }
    public Stock updateStock(Stock stock){
        stockRepository.save(stock);
        return stock;
    }
}
