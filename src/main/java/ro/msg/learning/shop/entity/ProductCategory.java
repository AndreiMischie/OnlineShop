package ro.msg.learning.shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends BaseClass {
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private Set<Product> products;
}
