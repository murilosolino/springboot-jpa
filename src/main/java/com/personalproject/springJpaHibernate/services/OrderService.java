package com.personalproject.springJpaHibernate.services;

import com.personalproject.springJpaHibernate.entities.Order;
import com.personalproject.springJpaHibernate.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long Id){
        Optional<Order> obj = orderRepository.findById(Id);
        return obj.get();
    }
}
