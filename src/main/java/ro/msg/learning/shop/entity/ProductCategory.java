package ro.msg.learning.shop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor @AllArgsConstructor
public class ProductCategory extends BaseClass{
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
    private Set<Product> products;
}
