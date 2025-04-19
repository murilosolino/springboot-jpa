package com.personalproject.springJpaHibernate.config;

import com.personalproject.springJpaHibernate.entities.Category;
import com.personalproject.springJpaHibernate.entities.Order;
import com.personalproject.springJpaHibernate.entities.Product;
import com.personalproject.springJpaHibernate.entities.User;
import com.personalproject.springJpaHibernate.entities.enums.OrderStatus;
import com.personalproject.springJpaHibernate.repositories.CategoryRepository;
import com.personalproject.springJpaHibernate.repositories.OrderRepository;
import com.personalproject.springJpaHibernate.repositories.ProductRepository;
import com.personalproject.springJpaHibernate.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration // indica que eh uma classe de configuracao
@Profile("test") // indicando que a configuracao somente no perfil de teste passado no application-test.properties
public class TestConfig implements CommandLineRunner {

    @Autowired // Realiza a injeção de dependência automática, conectando o repositório gerenciado pelo Spring ao atributo.
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        Category c1 = new Category(null, "Eletronics");
        Category c2 = new Category( null, "Books");
        Category c3 = new Category( null, "Computers");

        Product p1 = new Product(null, "The Lord of the Rings", "A classic fantasy book", 49.99, "https://example.com/img1.jpg");
        Product p2 = new Product(null, "MacBook Pro", "Apple laptop with M1 chip", 1299.99, "https://example.com/img2.jpg");
        Product p3 = new Product(null, "Samsung Galaxy S21", "Latest Samsung smartphone", 799.99, "https://example.com/img3.jpg");
        Product p4 = new Product(null, "Sony WH-1000XM4", "Noise-canceling headphones", 349.99, "https://example.com/img4.jpg");
        Product p5 = new Product(null, "Dell XPS 13", "High-performance ultrabook", 999.99, "https://example.com/img5.jpg");

        productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));
        categoryRepository.saveAll(Arrays.asList(c1,c2,c3));

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "98888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "97777777", "1234567");

        Order o1 = new Order(null, Instant.now(),u1, OrderStatus.PAID);
        Order o2 = new Order(null, Instant.now(),u2, OrderStatus.SHIPPED);
        Order o3 = new Order(null, Instant.now(),u1, OrderStatus.CANCELED);


        userRepository.saveAll(Arrays.asList(u1,u2));
        orderRepository.saveAll(Arrays.asList(o1,o2,o3));

    }
}
