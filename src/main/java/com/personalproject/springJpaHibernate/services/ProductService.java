package com.personalproject.springJpaHibernate.services;

import com.personalproject.springJpaHibernate.entities.Category;
import com.personalproject.springJpaHibernate.entities.Product;
import com.personalproject.springJpaHibernate.entities.User;
import com.personalproject.springJpaHibernate.repositories.ProductRepository;
import com.personalproject.springJpaHibernate.services.exceptions.DataBaseException;
import com.personalproject.springJpaHibernate.services.exceptions.NullDataObjectException;
import com.personalproject.springJpaHibernate.services.exceptions.ResourceNotFundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        validateDataObject(obj);
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
            validateDataObject(entity);
            return productRepository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFundException(id);
        }
    }

    public void addCategory(Category category, Long id){
        Product p = productRepository.getReferenceById(id);
        p.getCategories().add(category);
        productRepository.save(p);
    }

    public void deleteCategories(Long id, Category categorie) {
        Product product = productRepository.getReferenceById(id);
        product.getCategories().remove(categorie);
        productRepository.save(product);
    }

    private void updateData(Product entity, Product obj) {
        entity.setName(obj.getName());
        entity.setDescription(obj.getDescription());
        entity.setImgUrl(obj.getImgUrl());
        entity.setPrice(obj.getPrice());
    }

    private void validateDataObject(Product product){
        if (product.getName() == null) {
            throw new NullDataObjectException("NAME CANNOT BE NULL");
        } else if (product.getDescription() == null){
            throw new NullDataObjectException("DESCRIPTION CANNOT BE NULL");
        } else if (product.getImgUrl() == null) {
            throw new NullDataObjectException("imgURL CANNOT BE NULL");
        } else if (product.getPrice() == null){
            throw new NullDataObjectException("PRICE CANNOT BE NULL");
        }
    }

}
