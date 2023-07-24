package ro.msg.learning.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.dto.OrdersDto;
import ro.msg.learning.shop.dto.ProductQuantityPairDto;
import ro.msg.learning.shop.entity.Orders;

import java.util.List;


@Mapper
public interface OrdersMapper {
    OrdersMapper INSTANCE = Mappers.getMapper(OrdersMapper.class);

    @Mapping(source = "ordersDto.id", target = "id")
    @Mapping(source = "ordersDto.customerId", target = "customer.id")
    Orders toOrders(OrdersDto ordersDto);


    @Mapping(source = "order.customer.id", target = "customerId")
    OrdersDto toOrdersDto(Orders order, List<ProductQuantityPairDto> products);
}

