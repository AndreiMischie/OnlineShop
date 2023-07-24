package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.mapper.ProductMapper;
import ro.msg.learning.shop.service.ProductService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> getProductData(@PathVariable("id") UUID id) {
        Product product = productService.getProduct(id);
        ProductCategory productCategory = product.getCategory();
        return new ResponseEntity<>(ProductMapper.INSTANCE.toProductDto(product, productCategory), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Collection<ProductDto>> getProducts() {
        Collection<Product> products = productService.getProducts();
        Collection<ProductDto> productDtos = products.stream()
                .map(product -> ProductMapper.INSTANCE.toProductDto(product, product.getCategory()))
                .collect(Collectors.toCollection(ArrayList::new));
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted!", HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> putProduct(@PathVariable("id") UUID id, @RequestBody ProductDto productDto) {
        Product product = ProductMapper.INSTANCE.toProduct(productDto);
        productService.updateProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<Object> postProduct(@RequestBody ProductDto productDto) {
        Product product = ProductMapper.INSTANCE.toProduct(productDto);
        productService.createProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
}
