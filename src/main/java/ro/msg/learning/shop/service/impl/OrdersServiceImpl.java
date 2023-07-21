package ro.msg.learning.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.ProductQuantityPair;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.repository.OrdersRepository;
import ro.msg.learning.shop.service.*;

import java.util.*;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private LocationService locationService;
    @Autowired
    private ProductService productService;
    @Autowired
    private StockService stockService;
    @Autowired
    private OrderDetailService orderDetailService;

    public Orders createOrders(Orders order) {
        ordersRepository.save(order);
        return order;
    }

    public boolean isProductOnLocation(Location location, Product product, int quantity) {
        StockId id = new StockId(product, location);
        Stock stock = stockService.getStock(id);
        if (stock.getQuantity() >= quantity) {
            return true;
        }
        return false;
    }

    public Product getProductFromPair(ProductQuantityPair pair) {
        UUID productId = pair.getProductId();
        Product product = productService.getProduct(productId);
        return product;
    }

    public Location verifyStock(List<ProductQuantityPair> productQuantityPairList) {
        Collection<Location> locationList = locationService.getLocations();
        Collection<Location> filteredLocations = new ArrayList<Location>();
        ProductQuantityPair pair = productQuantityPairList.get(0);
        Product product = getProductFromPair(pair);
        for (Iterator<Location> iter = locationList.iterator(); iter.hasNext(); ) {
            Location location = iter.next();
            if (isProductOnLocation(location, product, pair.getQuantity())) {
                filteredLocations.add(location);
            }
        }
        for (Iterator<ProductQuantityPair> productQuantityPairIterator = productQuantityPairList.iterator(); productQuantityPairIterator.hasNext(); ) {
            productQuantityPairIterator.next();
            pair = productQuantityPairIterator.next();
            product = getProductFromPair(pair);
            for (Iterator<Location> locationIterator = locationList.iterator(); locationIterator.hasNext(); ) {
                Location location = locationIterator.next();
                if (!isProductOnLocation(location, product, pair.getQuantity())) {
                    filteredLocations.remove(location);
                }
            }
        }

        Optional<Location> optionalLocation = filteredLocations.stream().findFirst();
        return optionalLocation.orElse(null);
    }

    public void substractStock(Location location, ProductQuantityPair productQuantityPair) {
        Product product = getProductFromPair(productQuantityPair);
        StockId id = new StockId(product, location);
        Stock stock = stockService.getStock(id);
        Stock updatedStock = new Stock(product, location, stock.getQuantity() - productQuantityPair.getQuantity());
        stockService.updateStock(updatedStock);
    }

    public void substractAllStocks(Location location, List<ProductQuantityPair> productQuantityPairList) {
        ProductQuantityPair pair;
        for (Iterator<ProductQuantityPair> productQuantityPairIterator = productQuantityPairList.iterator(); productQuantityPairIterator.hasNext(); ) {
            pair = productQuantityPairIterator.next();
            substractStock(location, pair);
        }
    }
    public void createOrderDetails(Orders order, Location location, List<ProductQuantityPair> productQuantityPairList) {
        ProductQuantityPair pair;
        for (Iterator<ProductQuantityPair> productQuantityPairIterator = productQuantityPairList.iterator(); productQuantityPairIterator.hasNext(); ) {
            pair = productQuantityPairIterator.next();
            OrderDetail orderDetail = new OrderDetail(order, productService.getProduct(pair.getProductId()),location,pair.getQuantity());
            orderDetailService.createOrderDetail(orderDetail);
        }
    }

    public void handleOrderCreation(Orders order, List<ProductQuantityPair> productQuantityPairList) {
        Location location = verifyStock(productQuantityPairList);
        if (location != null) {
            Orders orders = createOrders(order);
            createOrderDetails(orders, location, productQuantityPairList);
            substractAllStocks(location, productQuantityPairList);
        } else {
            throw new RuntimeException();
        }
    }
}