package com.personalproject.springJpaHibernate.services;

import com.personalproject.springJpaHibernate.entities.Category;
import com.personalproject.springJpaHibernate.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return obj.get();
    }

}
