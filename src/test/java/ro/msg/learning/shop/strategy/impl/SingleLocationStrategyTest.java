package ro.msg.learning.shop.strategy.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.entity.compositeId.StockId;
import ro.msg.learning.shop.repository.OrdersRepository;
import ro.msg.learning.shop.service.*;
import ro.msg.learning.shop.util.OrderDetailsUtil;
import ro.msg.learning.shop.util.ProductQuantityPairUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SingleLocationStrategyTest {

    @Mock
    private OrdersRepository ordersRepository;
    @Mock
    private LocationService locationService;
    @Mock
    private ProductService productService;
    @Mock
    private StockService stockService;
    @Mock
    private OrderDetailService orderDetailService;
    @Mock
    private CustomerService customerService;

    @InjectMocks
    private SingleLocationStrategy singleLocationStrategy;

    @Test
    void handleOrderCreation() {
        final Customer customer = new Customer();
        customer.setOrdersList(new HashSet<>());
        customer.setId(UUID.fromString("1484b5ef-a00d-4e4e-aebe-4ec17633574a"));

        when(customerService.getCustomer(any())).thenReturn(customer);

        final Set<Location> locations = new HashSet<>();

        final Location location1 = new Location();
        final Location location2 = new Location();

        location1.setName("Depo1");
        location2.setName("Depo2");
        location1.setId(UUID.fromString("35d144fb-15e2-4325-ab81-da14998a2b97"));
        location2.setId(UUID.fromString("f803c14f-3369-4524-a0b8-e9eb8b769cb6"));

        locations.add(location1);
        locations.add(location2);

        when(locationService.getLocations()).thenReturn(locations);

        final Product product1 = new Product();
        final Product product2 = new Product();

        product1.setName("fork");
        product1.setId(UUID.fromString("2ad1c473-be9a-451e-882b-0c4297352222"));
        product2.setName("spoon");
        product2.setId(UUID.fromString("655fa72c-0035-4c32-9d3b-cb139598a46f"));

        when(productService.getProduct(UUID.fromString("2ad1c473-be9a-451e-882b-0c4297352222"))).thenReturn(product1);
        when(productService.getProduct(UUID.fromString("655fa72c-0035-4c32-9d3b-cb139598a46f"))).thenReturn(product2);


        final Stock stockp1l2 = new Stock();
        final Stock stockp2l2 = new Stock();


        stockp1l2.setProduct(product1);

        stockp2l2.setProduct(product2);

        stockp1l2.setLocation(location2);
        stockp2l2.setLocation(location2);


        stockp1l2.setQuantity(10);

        stockp2l2.setQuantity(10);


        final StockId stockIdp1l2 = new StockId(product1, location2);
        final StockId stockIdp2l1 = new StockId(product2, location1);
        final StockId stockIdp2l2 = new StockId(product2, location2);


        when(stockService.getStock(stockIdp1l2)).thenReturn(stockp1l2);
        when(stockService.getStock(stockIdp2l1)).thenReturn(null);
        when(stockService.getStock(stockIdp2l2)).thenReturn(stockp2l2);

        final Orders order = new Orders();
        order.setId(UUID.fromString("06ac5183-7538-4820-b465-7354231031e2"));
        order.setCustomer(customer);

        when(ordersRepository.save(any())).thenReturn(order);

        final ProductQuantityPairUtil productQuantityPairUtil1 = new ProductQuantityPairUtil(product1.getId(), 2);
        final ProductQuantityPairUtil productQuantityPairUtil2 = new ProductQuantityPairUtil(product2.getId(), 2);

        final Set<ProductQuantityPairUtil> productQuantityPairUtilList = new HashSet<>();
        productQuantityPairUtilList.add(productQuantityPairUtil1);
        productQuantityPairUtilList.add(productQuantityPairUtil2);

        final Orders placedOrder = new Orders();
        placedOrder.setCustomer(customer);

        Set<OrderDetailsUtil> expectedOrderDetailsUtilSet = new HashSet<>();
        OrderDetailsUtil orderDetailsUtil1 = new OrderDetailsUtil(location2, product1, 2);
        OrderDetailsUtil orderDetailsUtil2 = new OrderDetailsUtil(location2, product2, 2);
        expectedOrderDetailsUtilSet.add(orderDetailsUtil1);
        expectedOrderDetailsUtilSet.add(orderDetailsUtil2);

        Set<OrderDetailsUtil> orderDetailsUtilSet = singleLocationStrategy.handleOrderCreation(placedOrder, productQuantityPairUtilList);

        assertEquals(orderDetailsUtilSet, expectedOrderDetailsUtilSet);

    }
}