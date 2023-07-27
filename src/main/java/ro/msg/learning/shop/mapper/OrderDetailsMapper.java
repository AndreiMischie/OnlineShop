package ro.msg.learning.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.util.OrderDetailsUtil;
import ro.msg.learning.shop.entity.OrderDetail;

@Mapper
public interface OrderDetailsMapper {
    OrderDetailsMapper INSTANCE = Mappers.getMapper(OrderDetailsMapper.class);

    @Mapping(source = "orderDetail.shippedFrom", target = "shippedFrom")
    OrderDetailsUtil toOrderDetailsDto(OrderDetail orderDetail);
}
