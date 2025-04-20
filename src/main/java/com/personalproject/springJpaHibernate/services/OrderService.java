package com.personalproject.springJpaHibernate.services;

import com.personalproject.springJpaHibernate.entities.Order;
import com.personalproject.springJpaHibernate.repositories.OrderRepository;
import com.personalproject.springJpaHibernate.services.exceptions.DataBaseException;
import com.personalproject.springJpaHibernate.services.exceptions.ResourceNotFundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        return obj.orElseThrow(() -> new ResourceNotFundException(Id));
    }

    public Order insert(Order obj){
        return orderRepository.save(obj);
    }

    public void delete(Long id){
        try{
            Optional<Order> o = orderRepository.findById(id);
            if(o.isEmpty()){
                throw new ResourceNotFundException(id);
            }
            orderRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
    }

    public Order update(Long id, Order obj){
        try {
            Order entity = orderRepository.getReferenceById(id);
            updateData(entity, obj);
            return orderRepository.save(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFundException(id);
        }
    }

    private void updateData(Order entity, Order obj) {
        entity.setOrderStatus(obj.getOrderStatus());
    }
}
