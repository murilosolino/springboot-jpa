package com.personalproject.springJpaHibernate.services;

import com.personalproject.springJpaHibernate.entities.User;
import com.personalproject.springJpaHibernate.repositories.UserRepository;
import com.personalproject.springJpaHibernate.services.exceptions.DataBaseException;
import com.personalproject.springJpaHibernate.services.exceptions.NullDataObjectException;
import com.personalproject.springJpaHibernate.services.exceptions.ResourceNotFundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService{

    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long Id){
        Optional<User> obj = repository.findById(Id);
        return obj.orElseThrow(() -> new ResourceNotFundException(Id));
    }

    public User insert(User obj){
        validateDataObject(obj);
        return repository.save(obj);
    }

    public void delete(Long id){
        try {
            Optional<User> user = repository.findById(id);
            if (user.isEmpty()){
                throw new ResourceNotFundException(id);
            }
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e ){
            throw new DataBaseException(e.getMessage());
        }
    }

    public User update(Long id, User obj){
        try{
            User entity = repository.getReferenceById(id);
            updateData(entity, obj);
            validateDataObject(entity);
            return repository.save(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFundException(id);
        }
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }

    private void validateDataObject(User user){
        if (user.getName() == null) {
            throw new NullDataObjectException("NAME CANNOT BE NULL");
        } else if (user.getEmail() == null){
            throw new NullDataObjectException("EMAIL CANNOT BE NULL");
        } else if (user.getPhone() == null) {
            throw new NullDataObjectException("PHONE CANNOT BE NULL");
        } else if (user.getPassword() == null){
            throw new NullDataObjectException("PASSWORD CANNOT BE NULL");
        }
    }

}
