package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.msg.learning.shop.entity.Location;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<Location, UUID> {
}
