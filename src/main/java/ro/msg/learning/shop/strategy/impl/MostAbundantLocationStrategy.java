package ro.msg.learning.shop.strategy.impl;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.util.OrderDetailsUtil;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.entity.compositeId.StockId;
import ro.msg.learning.shop.exceptions.OrderNotCreatedException;
import ro.msg.learning.shop.mapper.OrderDetailsMapper;
import ro.msg.learning.shop.repository.OrdersRepository;
import ro.msg.learning.shop.service.*;
import ro.msg.learning.shop.strategy.OrderStrategy;
import ro.msg.learning.shop.util.ProductQuantityPairUtil;

import java.util.*;

@RequiredArgsConstructor
public class MostAbundantLocationStrategy implements OrderStrategy {

    private final OrdersRepository ordersRepository;

    private final LocationService locationService;

    private final ProductService productService;

    private final StockService stockService;

    private final OrderDetailService orderDetailService;

    private final CustomerService customerService;

    private Product getProductFromPair(ProductQuantityPairUtil pair) {
        UUID productId = pair.getProductId();
        return productService.getProduct(productId);
    }

    private Stock getStock(Product product, Location location) {
        StockId id = new StockId(product, location);
        return stockService.getStock(id);
    }

    private Stock findMaxStock(Product product, Collection<Location> locationList) {
        int max = 0;
        Location firstLocation = locationList.stream().findFirst().orElse(null);
        Stock maxStock = getStock(product, firstLocation);
        for (Location location : locationList) {
            Stock stock = getStock(product, location);
            if (stock != null) {
                if (stock.getQuantity() > max) {
                    max = stock.getQuantity();
                    maxStock = stock;
                }
            }
        }
        return maxStock;
    }

    private OrderDetail toOrderDetailFromStock(Stock stock, Orders order, int quantity) {
        OrderDetail orderDetail = new OrderDetail(order, stock.getProduct(), stock.getLocation(), quantity);
        return orderDetailService.createOrderDetail(orderDetail);
    }

    private Set<OrderDetail> createOrderDetails(Set<Stock> stocks, Orders order, Set<ProductQuantityPairUtil> productQuantityPairUtilList) {
        Set<OrderDetail> orderDetails = new HashSet<>();
        Iterator<Stock> stockIterator = stocks.iterator();
        Iterator<ProductQuantityPairUtil> productQuantityPairIterator = productQuantityPairUtilList.iterator();

        while (stockIterator.hasNext() && productQuantityPairIterator.hasNext()) {

            OrderDetail orderDetail = toOrderDetailFromStock(stockIterator.next(), order, productQuantityPairIterator.next().getQuantity());
            orderDetails.add(orderDetail);

        }
        return orderDetails;
    }

    private void subtractStock(Set<Stock> stocks, Set<ProductQuantityPairUtil> productQuantityPairUtilList) {
        Iterator<Stock> stockIterator = stocks.iterator();
        Iterator<ProductQuantityPairUtil> productQuantityPairIterator = productQuantityPairUtilList.iterator();

        while (stockIterator.hasNext() && productQuantityPairIterator.hasNext()) {
            Stock stockIteratorValue = stockIterator.next();
            int quantity = stockIteratorValue.getQuantity() - productQuantityPairIterator.next().getQuantity();
            stockIteratorValue.setQuantity(quantity);
            stockService.updateStock(stockIteratorValue);
        }
    }

    private Set<Stock> verifyStock(Collection<Location> locationList, Set<ProductQuantityPairUtil> productQuantityPairUtilList) {
        Set<Stock> stocks = new HashSet<>();
        for (ProductQuantityPairUtil productQuantityPairUtil : productQuantityPairUtilList) {
            Product product = getProductFromPair(productQuantityPairUtil);
            Stock maxStock = findMaxStock(product, locationList);
            if (maxStock == null) {
                throw new OrderNotCreatedException("Your order was not created!");
            }
            if (maxStock.getQuantity() < productQuantityPairUtil.getQuantity()) {
                return null;
            }
            stocks.add(maxStock);
        }
        return stocks;
    }

    @Override
    public Set<OrderDetailsUtil> handleOrderCreation(Orders order, Set<ProductQuantityPairUtil> productQuantityPairUtilList) {
        Customer customer = customerService.getCustomer(order.getCustomer().getId());
        order.setCustomer(customer);
        Orders newOrder = ordersRepository.save(order);

        Set<OrderDetailsUtil> orderDetailsUtilSet = new HashSet<>();
        Collection<Location> locationList = locationService.getLocations();

        Set<Stock> stocks = verifyStock(locationList, productQuantityPairUtilList);
        if (stocks == null) {
            throw new OrderNotCreatedException("Your order was not created!");
        }

        Set<OrderDetail> orderDetails = createOrderDetails(stocks, order, productQuantityPairUtilList);
        newOrder.setOrdersDetails(orderDetails);

        subtractStock(stocks, productQuantityPairUtilList);

        Set<Orders> orders = customer.getOrdersList();
        orders.add(order);
        customer.setOrdersList(orders);
        newOrder.setCustomer(customer);

        for (OrderDetail orderDetail : orderDetails) {
            orderDetailsUtilSet.add(OrderDetailsMapper.INSTANCE.toOrderDetailsDto(orderDetail));
        }

        ordersRepository.save(newOrder);
        return orderDetailsUtilSet;
    }


}
