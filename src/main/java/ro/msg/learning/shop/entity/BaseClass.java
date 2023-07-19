package ro.msg.learning.shop.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

@MappedSuperclass
public class BaseClass {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID Id;
}
