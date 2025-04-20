package com.personalproject.springJpaHibernate.services;

import com.personalproject.springJpaHibernate.entities.Product;
import com.personalproject.springJpaHibernate.entities.User;
import com.personalproject.springJpaHibernate.repositories.ProductRepository;
import com.personalproject.springJpaHibernate.services.exceptions.DataBaseException;
import com.personalproject.springJpaHibernate.services.exceptions.ResourceNotFundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product findById(Long Id){
        Optional<Product> obj = productRepository.findById(Id);
        return obj.orElseThrow(() -> new ResourceNotFundException(Id));
    }

    public Product insert(Product obj){
        return productRepository.save(obj);
    }

    public void delete(Long id){

        try{
            Optional<Product> p = productRepository.findById(id);
            if (p.isEmpty()){
                throw new ResourceNotFundException(id);
            }
            productRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw  new DataBaseException(e.getMessage());
        }
    }

    public Product update(Long id, Product obj){
        try{
            Product entity = productRepository.getReferenceById(id);
            updateData(entity, obj);
            return productRepository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFundException(id);
        }
    }

    private void updateData(Product entity, Product obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setImgUrl(obj.getImgUrl());
        entity.setPrice(obj.getPrice());
    }

}
