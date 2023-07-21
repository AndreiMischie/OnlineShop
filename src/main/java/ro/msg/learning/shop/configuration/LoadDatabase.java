package ro.msg.learning.shop.configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.repository.ProductCategoryRepository;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class LoadDatabase {
//    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
//
//    @Bean
//    CommandLineRunner initDatabase(ProductCategoryRepository repository) {
//        Set<Product> set = new HashSet<Product>();
//        return args -> {
//            log.info("Preloading " + repository.save(new ProductCategory("Computers","Computers and laptops",set)));
//        };
//    }
}
