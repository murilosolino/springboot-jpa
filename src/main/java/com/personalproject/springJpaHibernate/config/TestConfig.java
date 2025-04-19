package com.personalproject.springJpaHibernate.config;

import com.personalproject.springJpaHibernate.entities.Order;
import com.personalproject.springJpaHibernate.entities.User;
import com.personalproject.springJpaHibernate.entities.enums.OrderStatus;
import com.personalproject.springJpaHibernate.repositories.OrderRepository;
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

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "98888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "97777777", "1234567");

        Order o1 = new Order(null, Instant.now(),u1, OrderStatus.PAID);
        Order o2 = new Order(null, Instant.now(),u2, OrderStatus.SHIPPED);
        Order o3 = new Order(null, Instant.now(),u1, OrderStatus.CANCELED);

        userRepository.saveAll(Arrays.asList(u1,u2));
        orderRepository.saveAll(Arrays.asList(o1,o2,o3));
    }
}
