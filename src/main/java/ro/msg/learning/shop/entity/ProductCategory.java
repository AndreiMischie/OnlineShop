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
    @Id
    private int Id;
    private String Name;
    private String Description;
    @OneToMany(mappedBy = "Category")
    private Set<Product> Products;
}
