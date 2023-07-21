package ro.msg.learning.shop.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductQuantityPair {
    private UUID productId;
    private int quantity;
}
