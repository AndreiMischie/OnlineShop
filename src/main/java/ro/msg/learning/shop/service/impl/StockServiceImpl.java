package ro.msg.learning.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.entity.compositeId.StockId;
import ro.msg.learning.shop.repository.StockRepository;
import ro.msg.learning.shop.service.StockService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public Stock getStock(StockId id) {
        Optional<Stock> stock = stockRepository.findById(id);
        return stock.orElse(null);
    }

    public Stock updateStock(Stock stock) {
        stockRepository.save(stock);
        return stock;
    }
}
