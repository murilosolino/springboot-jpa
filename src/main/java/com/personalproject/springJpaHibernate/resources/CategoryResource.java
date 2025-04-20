package com.personalproject.springJpaHibernate.resources;

import com.personalproject.springJpaHibernate.entities.Category;
import com.personalproject.springJpaHibernate.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody Category obj){
        categoryService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{Id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category obj){
        categoryService.update(id,obj);
        return ResponseEntity.ok().body(obj);
    }

}
