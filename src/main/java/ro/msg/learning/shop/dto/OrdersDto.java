package ro.msg.learning.shop.dto;

import lombok.Data;
import ro.msg.learning.shop.entity.Address;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrdersDto {
    private UUID customerId;
    private LocalDateTime createdAt;
    private Address address;
    private List<ProductQuantityPair> products;
}
