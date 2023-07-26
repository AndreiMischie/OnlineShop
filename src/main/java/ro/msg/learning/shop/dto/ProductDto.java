package ro.msg.learning.shop.dto;


import lombok.Data;

import java.util.UUID;

@Data
public class ProductDto {
    private UUID id;
    private UUID productCategoryId;
    private String productCategoryName;
    private String productCategoryDescription;
    private String name;
    private String description;
    private float price;
    private double weight;
    private String imageURL;

}
