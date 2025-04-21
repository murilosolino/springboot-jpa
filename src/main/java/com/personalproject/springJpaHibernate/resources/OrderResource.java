package com.personalproject.springJpaHibernate.resources;

import com.personalproject.springJpaHibernate.entities.Order;
import com.personalproject.springJpaHibernate.entities.OrderItem;
import com.personalproject.springJpaHibernate.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List> findAll(){
        List<Order> list = orderService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{Id}")
    public ResponseEntity<Order> findById(@PathVariable Long Id){
        Order obj = orderService.findById(Id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Order> insert(@RequestBody Order obj){
        orderService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{Id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order obj){
        orderService.update(id,obj);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "{id}/items")
    @PostMapping
    public ResponseEntity<Void> addOrderItem(@PathVariable Long id, @RequestBody OrderItem item){
        orderService.addOrderItem(id,item);
        return ResponseEntity.ok().build();
    }

}
