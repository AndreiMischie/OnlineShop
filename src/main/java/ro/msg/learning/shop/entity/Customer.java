package ro.msg.learning.shop.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data @NoArgsConstructor @AllArgsConstructor
public class Customer extends BaseClass{
    private String FirstName;
    private String LastName;
    private String Username;
    private String Password;
    private String EmailAddress;
    @OneToMany(mappedBy = "Customer")
    private Set<Orders> ordersList;
}
