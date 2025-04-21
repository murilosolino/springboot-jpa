package com.personalproject.springJpaHibernate.services;

import com.personalproject.springJpaHibernate.entities.Category;
import com.personalproject.springJpaHibernate.repositories.CategoryRepository;
import com.personalproject.springJpaHibernate.services.exceptions.DataBaseException;
import com.personalproject.springJpaHibernate.services.exceptions.NullDataObjectException;
import com.personalproject.springJpaHibernate.services.exceptions.ResourceNotFundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findById(Long Id){
        Optional<Category> obj = categoryRepository.findById(Id);
        return obj.orElseThrow(() -> new ResourceNotFundException(Id));
    }

    public Category insert(Category obj){
        validateDataObject(obj);
        return categoryRepository.save(obj);
    }

    public void delete(Long id){
        try{
            Optional<Category> o = categoryRepository.findById(id);
            if(o.isEmpty()){
                throw new ResourceNotFundException(id);
            }
            categoryRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
    }

    public Category update(Long id, Category obj){
        try {
            Category entity = categoryRepository.getReferenceById(id);
            updateData(entity, obj);
            validateDataObject(entity);
            return categoryRepository.save(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFundException(id);
        }
    }

    private void updateData(Category entity, Category obj) {
        entity.setName(obj.getName());
    }

    private void validateDataObject(Category category){
        if (category.getName() == null) {
            throw new NullDataObjectException("NAME CANNOT BE NULL");
        }
    }

}
