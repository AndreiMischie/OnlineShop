package ro.msg.learning.shop.service;

import ro.msg.learning.shop.entity.ProductCategory;

import java.util.Collection;
import java.util.UUID;

public interface ProductCategoryService {
//      void createProductCategory(ProductCategory product);
      ProductCategory getProductCategory(UUID Id);
//      Collection<ProductCategory> getProductCategories();
}
