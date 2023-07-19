package ro.msg.learning.shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor @AllArgsConstructor
public class Product extends BaseClass{
    private String Name;
    private String Description;
    private float Price;
    private double Weight;
    @ManyToOne
    private ProductCategory Category;
    private String ImageURL;
    @OneToMany(mappedBy = "Product")
    private Set<Stock> Stocks;
    @OneToMany(mappedBy = "Product")
    private Set<Stock> OrdersDetails;
}
