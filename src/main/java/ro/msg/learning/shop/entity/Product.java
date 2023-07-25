package ro.msg.learning.shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseClass {
    private String name;
    private String description;
    private float price;
    private double weight;
    @ManyToOne
    private ProductCategory category;
    private String imageURL;
    @OneToMany(mappedBy = "product")
    private Set<Stock> stocks;
    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> ordersDetails;
}
