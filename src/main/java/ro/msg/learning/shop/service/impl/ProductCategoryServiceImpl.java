package ro.msg.learning.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.service.ProductCategoryService;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory getProductCategory(UUID Id) {
        Optional<ProductCategory> optionalProductCategory = productCategoryRepository.findById(Id);
        return optionalProductCategory.orElse(null);
    }
}
