package com.personalproject.springJpaHibernate.config;

import com.personalproject.springJpaHibernate.entities.User;
import com.personalproject.springJpaHibernate.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration // indica que eh uma classe de configuracao
@Profile("test") // indicando que a configuracao somente no perfil de teste passado no application-test.properties
public class TestConfig implements CommandLineRunner {

    @Autowired //// Realiza a injeção de dependência automática, conectando o repositório gerenciado pelo Spring ao atributo.
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "98888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "97777777", "1234567");


        userRepository.saveAll(Arrays.asList(u1,u2));
    }
}
