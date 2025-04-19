package com.personalproject.springJpaHibernate.resources;

import com.personalproject.springJpaHibernate.entities.Category;
import com.personalproject.springJpaHibernate.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List> findAll(){
        List<Category> list = categoryService.findAll();
        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value = "/{Id}")
    public ResponseEntity<Category> findById(@PathVariable Long Id){
       Category obj = categoryService.findById(Id);
        return ResponseEntity.ok().body(obj);
    }

}
