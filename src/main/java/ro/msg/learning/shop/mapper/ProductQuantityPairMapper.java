package ro.msg.learning.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.dto.ProductQuantityPairDto;
import ro.msg.learning.shop.util.ProductQuantityPairUtil;

@Mapper
public interface ProductQuantityPairMapper {
    ProductQuantityPairMapper INSTANCE = Mappers.getMapper(ProductQuantityPairMapper.class);

    ProductQuantityPairUtil toProductQuantityPair(ProductQuantityPairDto productQuantityPairDto);
    ProductQuantityPairDto toProductQuantityPairDto(ProductQuantityPairUtil productQuantityPairUtil);


}
