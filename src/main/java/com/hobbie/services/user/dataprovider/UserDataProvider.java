package com.hobbie.services.user.dataprovider;

import com.hobbie.services.user.dataprovider.repository.entity.User;
import com.hobbie.services.user.dataprovider.repository.UserRepository;
import com.hobbie.services.user.dataprovider.service.GetUserService;
import com.hobbie.services.user.entrypoint.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserDataProvider {

    private UserRepository repository;
    private GetUserService service;

    public UserDataProvider(UserRepository repository, GetUserService service) {
        this.service = service;
        this.repository = repository;
    }

    public User getUserById(String id) {

        try{
            List<UserDTO> users = service.getUser();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

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
