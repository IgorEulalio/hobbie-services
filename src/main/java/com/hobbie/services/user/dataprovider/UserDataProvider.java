package com.hobbie.services.user.dataprovider;

import com.hobbie.services.user.usecase.model.User;
import com.hobbie.services.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserDataProvider {

    private UserRepository repository;

    public UserDataProvider(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> getUserById(String id){
        return repository.findById(id);
    }

    public List<User> getUsers(){
        return repository.findAll();
    }

    public void addUser(User user){
        repository.save(user);
    }
}
