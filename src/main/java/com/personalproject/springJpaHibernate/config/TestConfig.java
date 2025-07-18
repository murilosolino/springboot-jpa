package com.personalproject.springJpaHibernate.config;

import com.personalproject.springJpaHibernate.entities.*;
import com.personalproject.springJpaHibernate.entities.enums.OrderStatus;
import com.personalproject.springJpaHibernate.repositories.*;
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

    @Autowired
    private OrderItemRepository orderItemRepository;

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

        p1.getCategories().add(c2);
        p2.getCategories().addAll(Arrays.asList(c1,c3));
        p3.getCategories().add(c1);
        p4.getCategories().add(c1);
        p5.getCategories().addAll(Arrays.asList(c3,c1));

        productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "98888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "97777777", "1234567");

        Order o1 = new Order(null, Instant.now(),u1, OrderStatus.PAID);
        Order o2 = new Order(null, Instant.now(),u2, OrderStatus.SHIPPED);
        Order o3 = new Order(null, Instant.now(),u1, OrderStatus.CANCELED);


        userRepository.saveAll(Arrays.asList(u1,u2));
        orderRepository.saveAll(Arrays.asList(o1,o2,o3));

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 1, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1,oi2,oi3,oi4));

        Payment pay1 = new Payment(null, Instant.parse("2025-04-19T21:53:00Z"), o1);
        o1.setPayment(pay1);

        orderRepository.save(o1);
    }
}
