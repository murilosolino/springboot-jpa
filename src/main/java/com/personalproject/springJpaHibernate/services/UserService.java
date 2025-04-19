package com.personalproject.springJpaHibernate.services;

import com.personalproject.springJpaHibernate.entities.User;
import com.personalproject.springJpaHibernate.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long Id){
        Optional<User> obj = repository.findById(Id);
        return obj.get();
    }

}
