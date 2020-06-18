package com.hobbie.services.user.entrypoint;

import com.hobbie.services.user.dataprovider.UserDataProvider;
import com.hobbie.services.user.usecase.model.User;
import com.hobbie.services.user.usecase.UserUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserDataProvider userDataProvider;

    @GetMapping()
    public ResponseEntity<List<User>> getUsers(){
        return  ResponseEntity.ok().body(userDataProvider.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {

        Optional<User> userById = userDataProvider.getUserById(id);

        if (userById.isPresent()) return  ResponseEntity.ok().body(userDataProvider.getUserById(id).get());

        else return ResponseEntity.status(404).build();
    }

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody @Valid User user){
        userDataProvider.addUser(user);

        return ResponseEntity.status(201).build();
    }
}
