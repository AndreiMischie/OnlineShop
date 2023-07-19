package ro.msg.learning.shop.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Embeddable
public class Address {
    private String Country;
    private String City;
    private String County;
    private String StreetAddress;
}
