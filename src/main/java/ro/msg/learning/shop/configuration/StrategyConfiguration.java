package ro.msg.learning.shop.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repository.OrdersRepository;
import ro.msg.learning.shop.service.*;
import ro.msg.learning.shop.strategy.OrderStrategy;
import ro.msg.learning.shop.strategy.impl.MostAbundantLocationStrategy;
import ro.msg.learning.shop.strategy.impl.SingleLocationStrategy;

@Configuration
public class StrategyConfiguration {

    @ConditionalOnProperty(prefix = "shop", name = "strategy", havingValue = "single")
    @Bean
    public OrderStrategy getSingleLocationStrategy(OrdersRepository ordersRepository, LocationService locationService, ProductService productService, StockService stockService, OrderDetailService orderDetailService, CustomerService customerService) {
        return new SingleLocationStrategy(ordersRepository, locationService, productService, stockService, orderDetailService, customerService);
    }

    @ConditionalOnProperty(prefix = "shop", name = "strategy", havingValue = "most")
    @Bean
    public OrderStrategy getMostAbundantLocationStrategy(OrdersRepository ordersRepository, LocationService locationService, ProductService productService, StockService stockService, OrderDetailService orderDetailService, CustomerService customerService) {
        return new MostAbundantLocationStrategy(ordersRepository, locationService, productService, stockService, orderDetailService, customerService);
    }
}
