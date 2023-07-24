package ro.msg.learning.shop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.ProductQuantityPairDto;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.entity.compositeId.StockId;
import ro.msg.learning.shop.repository.OrdersRepository;
import ro.msg.learning.shop.service.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    private final LocationService locationService;

    private final ProductService productService;

    private final StockService stockService;

    private final OrderDetailService orderDetailService;

    private final CustomerService customerService;

    public Orders createOrders(Orders order) {
        ordersRepository.save(order);
        return order;
    }

    public boolean isProductOnLocation(Location location, Product product, int quantity) {
        StockId id = new StockId(product, location);
        Stock stock = stockService.getStock(id);
        if (stock != null) {
            return stock.getQuantity() >= quantity;
        }
        return false;
    }

    public Product getProductFromPair(ProductQuantityPairDto pair) {
        UUID productId = pair.getProductId();
        return productService.getProduct(productId);
    }

    public Stock getStock(Product product, Location location) {
        StockId id = new StockId(product, location);
        return stockService.getStock(id);
    }

    public Location verifyStock(List<ProductQuantityPairDto> productQuantityPairDtoList) {
        Collection<Location> locationList = locationService.getLocations();
        for (ProductQuantityPairDto productQuantityPairDto : productQuantityPairDtoList) {
            Product product = getProductFromPair(productQuantityPairDto);
            locationList.removeIf(location -> !isProductOnLocation(location, product, productQuantityPairDto.getQuantity()));
        }
        Optional<Location> optionalLocation = locationList.stream().findFirst();
        return optionalLocation.orElse(null);
    }

    public void subtractStock(Location location, ProductQuantityPairDto productQuantityPairDto) {
        Product product = getProductFromPair(productQuantityPairDto);
        Stock stock = getStock(product, location);
        int quantity = stock.getQuantity() - productQuantityPairDto.getQuantity();
        Stock updatedStock = new Stock(product, location, quantity);
        stockService.updateStock(updatedStock);
    }

    public void subtractAllStocks(Location location, List<ProductQuantityPairDto> productQuantityPairDtoList) {
        for (ProductQuantityPairDto productQuantityPairDto : productQuantityPairDtoList) {
            subtractStock(location, productQuantityPairDto);
        }
    }

    public Set<OrderDetail> createOrderDetails(Orders order, Location location, List<ProductQuantityPairDto> productQuantityPairDtoList) {
        Set<OrderDetail> orderDetails = new HashSet<>();
        for (ProductQuantityPairDto productQuantityPairDto : productQuantityPairDtoList) {
            Product product = getProductFromPair(productQuantityPairDto);
            int quantity = productQuantityPairDto.getQuantity();
            OrderDetail orderDetail = new OrderDetail(order, product, location, quantity);
            orderDetailService.createOrderDetail(orderDetail);
            orderDetails.add(orderDetail);
        }
        return orderDetails;
    }

    public Orders handleOrderCreation(Orders order, List<ProductQuantityPairDto> productQuantityPairDtoList) {
        Customer customer = customerService.getCustomer(order.getCustomer().getId());
        Location location = verifyStock(productQuantityPairDtoList);
        if (location != null) {
            order.setCustomer(customer);
            Orders newOrder = createOrders(order);
            Set<OrderDetail> orderDetails = createOrderDetails(newOrder, location, productQuantityPairDtoList);
            subtractAllStocks(location, productQuantityPairDtoList);
            Set<Orders> orders = customer.getOrdersList();
            orders.add(order);
            customer.setOrdersList(orders);
            newOrder.setOrdersDetails(orderDetails);
            newOrder.setCustomer(customer);
            return ordersRepository.save(newOrder);
        } else {
            throw new OrderNotCreatedException("your order was not created!");
        }
    }
}