package ro.msg.learning.shop.service;

import ro.msg.learning.shop.entity.ProductCategory;

import java.util.UUID;

public interface ProductCategoryService {
    ProductCategory getProductCategory(UUID Id);
}
