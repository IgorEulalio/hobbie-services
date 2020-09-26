package com.hobbie.services.user.dataprovider;

import com.hobbie.services.user.dataprovider.repository.entity.User;
import com.hobbie.services.user.dataprovider.repository.UserRepository;
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

    public User getUserById(String id) {

        Optional<User> byId = repository.findById(id);

        byId.orElseThrow(() -> new EntityNotFoundException("Usuario com o id " + id + " não encontrado"));

        return byId.get();

    }

    public List<User> getUsers() {
        return repository.findAll();
    }

    public String addUser(User user) {
        User userDatabase = repository.save(user);
        return userDatabase.getId();
    }
}
