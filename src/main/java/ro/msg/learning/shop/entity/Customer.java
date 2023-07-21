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
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String emailAddress;
    @OneToMany(mappedBy = "customer")
    private Set<Orders> ordersList;
}
