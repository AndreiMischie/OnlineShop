package ro.msg.learning.shop.service;

import ro.msg.learning.shop.entity.Product;

import java.util.Collection;
import java.util.UUID;

public interface ProductService {
    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(UUID id);

    Collection<Product> getProducts();

    Product getProduct(UUID id);
}
