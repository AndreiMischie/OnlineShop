package ro.msg.learning.shop.service;

import ro.msg.learning.shop.entity.Product;

import java.util.Collection;
import java.util.UUID;

public interface ProductService {
    public abstract Product createProduct(Product product);

    public abstract Product updateProduct(Product product);

    public abstract void deleteProduct(UUID id);

    public abstract Collection<Product> getProducts();

    public abstract Product getProduct(UUID id);
}
