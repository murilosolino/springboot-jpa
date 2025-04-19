package com.personalproject.springJpaHibernate.resources;

import com.personalproject.springJpaHibernate.entities.Product;
import com.personalproject.springJpaHibernate.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
