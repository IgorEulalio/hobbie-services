package com.hobbie.services.user.entrypoint;

import com.hobbie.services.user.dataprovider.UserDataProvider;
import com.hobbie.services.user.entrypoint.dto.FeedbackDto;
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
    private UserUseCase userUseCase;

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userUseCase.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return ResponseEntity.ok().body(userUseCase.getUserById(id));
    }

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody @Valid User user) {
        userUseCase.addUser(user);

        return ResponseEntity.status(201).build();
    }

    @PostMapping("/{id}/feedback")
    public ResponseEntity<User> addFeedback(@PathVariable String id,
                                            @RequestBody @Valid FeedbackDto dto) {

        userUseCase.addFeedback(id, dto);

        return ResponseEntity.status(204).build();
    }
}
