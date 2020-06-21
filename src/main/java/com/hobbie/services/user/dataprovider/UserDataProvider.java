package com.hobbie.services.user.dataprovider;

import com.hobbie.services.user.usecase.model.User;
import com.hobbie.services.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Component
public class UserDataProvider {

    private UserRepository repository;

    public UserDataProvider(UserRepository repository) {
        this.repository = repository;
    }

    public User getUserById(String id){

        Optional<User> byId = repository.findById(id);

        byId.orElseThrow(() -> new EntityNotFoundException("Usuario com o id " + id + " não encontrado"));

        return byId.get();

    }

    public List<User> getUsers(){
        return repository.findAll();
    }

    public void addUser(User user){
        repository.save(user);
    }
}
