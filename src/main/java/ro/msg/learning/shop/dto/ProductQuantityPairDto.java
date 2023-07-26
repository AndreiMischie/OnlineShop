package ro.msg.learning.shop.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductQuantityPairDto {
    private UUID productId;
    private int quantity;
}
