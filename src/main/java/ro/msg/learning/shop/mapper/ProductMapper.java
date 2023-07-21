package ro.msg.learning.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.ProductCategory;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "product.name", target = "name")
    @Mapping(source = "product.description", target = "description")
    @Mapping(source = "product.id", target = "id")
    @Mapping(source = "productCategory.name", target = "productCategoryName")
    @Mapping(source = "productCategory.description", target = "productCategoryDescription")
    @Mapping(source = "productCategory.id", target = "productCategoryId")
    ProductDto toProductDto(Product product, ProductCategory productCategory);

    Product toProduct(ProductDto productDto);
}
