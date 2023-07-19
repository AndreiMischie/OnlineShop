package ro.msg.learning.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data @NoArgsConstructor @AllArgsConstructor
public class OrderDetailId implements Serializable {
    private Orders Orders;
    private Product Product;
    private Location Location;
}
