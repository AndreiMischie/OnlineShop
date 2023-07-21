package ro.msg.learning.shop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.dto.OrdersDto;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.entity.Orders;

@Mapper
public interface OrdersMapper {
    OrdersMapper INSTANCE = Mappers.getMapper(OrdersMapper.class);

    @Mapping(source = "customer",target = "customer")
    @Mapping(source = "ordersDto.createdAt",target = "createdAt")
    @Mapping(source = "ordersDto.address",target = "address")
    Orders toOrders(OrdersDto ordersDto, Customer customer);
}
