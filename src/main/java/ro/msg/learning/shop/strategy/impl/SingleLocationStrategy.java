package ro.msg.learning.shop.strategy.impl;

import lombok.RequiredArgsConstructor;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.entity.compositeId.StockId;
import ro.msg.learning.shop.exceptions.OrderNotCreatedException;
import ro.msg.learning.shop.mapper.OrderDetailsMapper;
import ro.msg.learning.shop.repository.OrdersRepository;
import ro.msg.learning.shop.service.*;
import ro.msg.learning.shop.strategy.OrderStrategy;
import ro.msg.learning.shop.util.OrderDetailsUtil;
import ro.msg.learning.shop.util.ProductQuantityPairUtil;

import java.util.*;

@RequiredArgsConstructor
public class SingleLocationStrategy implements OrderStrategy {

    private final OrdersRepository ordersRepository;

    private final LocationService locationService;

    private final ProductService productService;

    private final StockService stockService;

    private final OrderDetailService orderDetailService;

    private final CustomerService customerService;


    private boolean isProductOnLocation(Location location, Product product, int quantity) {
        StockId id = new StockId(product, location);
        Stock stock = stockService.getStock(id);
        if (stock != null) {
            return stock.getQuantity() >= quantity;
        }
        return false;
    }

    private Product getProductFromPair(ProductQuantityPairUtil pair) {
        UUID productId = pair.getProductId();
        return productService.getProduct(productId);
    }

    private Stock getStock(Product product, Location location) {
        StockId id = new StockId(product, location);
        return stockService.getStock(id);
    }

    private Location verifyStock(Set<ProductQuantityPairUtil> productQuantityPairUtilList) {
        Collection<Location> locationList = locationService.getLocations();
        for (ProductQuantityPairUtil productQuantityPairUtil : productQuantityPairUtilList) {
            Product product = getProductFromPair(productQuantityPairUtil);
            locationList.removeIf(location -> !isProductOnLocation(location, product, productQuantityPairUtil.getQuantity()));
        }
        Optional<Location> optionalLocation = locationList.stream().findFirst();
        return optionalLocation.orElse(null);
    }

    private void subtractStock(Location location, ProductQuantityPairUtil productQuantityPairUtil) {
        Product product = getProductFromPair(productQuantityPairUtil);
        Stock stock = getStock(product, location);
        int quantity = stock.getQuantity() - productQuantityPairUtil.getQuantity();
        Stock updatedStock = new Stock(product, location, quantity);
        stockService.updateStock(updatedStock);
    }

    private void subtractAllStocks(Location location, Set<ProductQuantityPairUtil> productQuantityPairUtilList) {
        for (ProductQuantityPairUtil productQuantityPairUtil : productQuantityPairUtilList) {
            subtractStock(location, productQuantityPairUtil);
        }
    }

    private Set<OrderDetail> createOrderDetails(Orders order, Location location, Set<ProductQuantityPairUtil> productQuantityPairUtilList) {
        Set<OrderDetail> orderDetails = new HashSet<>();
        for (ProductQuantityPairUtil productQuantityPairUtil : productQuantityPairUtilList) {
            Product product = getProductFromPair(productQuantityPairUtil);
            int quantity = productQuantityPairUtil.getQuantity();
            OrderDetail orderDetail = new OrderDetail(order, product, location, quantity);
            orderDetailService.createOrderDetail(orderDetail);
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }

    @Override
    public Set<OrderDetailsUtil> handleOrderCreation(Orders order, Set<ProductQuantityPairUtil> productQuantityPairUtilList) {
        Customer customer = customerService.getCustomer(order.getCustomer().getId());
        Location location = verifyStock(productQuantityPairUtilList);
        Set<OrderDetailsUtil> orderDetailsUtilSet = new HashSet<>();

        if (location != null) {
            order.setCustomer(customer);
            Orders newOrder = ordersRepository.save(order);

            Set<OrderDetail> orderDetails = createOrderDetails(newOrder, location, productQuantityPairUtilList);
            newOrder.setOrdersDetails(orderDetails);

            subtractAllStocks(location, productQuantityPairUtilList);

            Set<Orders> orders = customer.getOrdersList();
            orders.add(order);
            customer.setOrdersList(orders);

            newOrder.setCustomer(customer);
            for (OrderDetail orderDetail : orderDetails) {
                orderDetailsUtilSet.add(OrderDetailsMapper.INSTANCE.toOrderDetailsDto(orderDetail));
            }
            ordersRepository.save(newOrder);
            return orderDetailsUtilSet;
        } else {
            throw new OrderNotCreatedException("Your order was not created!");
        }
    }
}
