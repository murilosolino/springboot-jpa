package com.personalproject.springJpaHibernate.resources;

import com.personalproject.springJpaHibernate.entities.Product;
import com.personalproject.springJpaHibernate.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "products")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List> findAll(){
        List<Product> list = productService.findAll();
        return ResponseEntity.ok().body(list);

    }

    @GetMapping(value = "/{Id}")
    public ResponseEntity<Product> findById(@PathVariable Long Id){
       Product obj = productService.findById(Id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody Product obj){
        obj = productService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{Id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{Id}")
    public ResponseEntity<Void> delete(@PathVariable Long Id){
        productService.delete(Id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{Id}")
    public ResponseEntity<Product> update(@PathVariable Long Id, @RequestBody Product obj){
        obj = productService.update(Id,obj);
        return ResponseEntity.ok().body(obj);
    }

}
