package com.personalproject.springJpaHibernate.resources;

import com.personalproject.springJpaHibernate.entities.User;
import com.personalproject.springJpaHibernate.repositories.UserRepository;
import com.personalproject.springJpaHibernate.services.UserService;
import com.personalproject.springJpaHibernate.services.exceptions.ResourceNotFundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;
    @Autowired
    private UserRepository userRepository;

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

    @DeleteMapping(value = "/{Id}")
    public ResponseEntity<Void> delete(@PathVariable Long Id){
        Optional<User> obj = userRepository.findById(Id);
        if (obj.isPresent()){
            service.delete(Id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFundException(Id);
        }

    }

    @PutMapping(value = "/{Id}")
    public ResponseEntity<User> update(@PathVariable Long Id, @RequestBody User obj){
        obj = service.update(Id,obj);
        return ResponseEntity.ok().body(obj);
    }

}
