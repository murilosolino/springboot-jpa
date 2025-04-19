package com.personalproject.springJpaHibernate.resources;

import com.personalproject.springJpaHibernate.entities.User;
import com.personalproject.springJpaHibernate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List> findAll(){
        List<User> list  = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{Id}")
    public ResponseEntity<User> findById(@PathVariable Long Id){
        User user = service.findById(Id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{Id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

}
