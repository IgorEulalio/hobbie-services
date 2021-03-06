package com.hobbie.services.user.entrypoint;

import com.hobbie.services.user.entrypoint.dto.FeedbackDto;
import com.hobbie.services.user.dataprovider.repository.entity.User;
import com.hobbie.services.user.entrypoint.dto.UserDTO;
import com.hobbie.services.user.usecase.UserUseCase;
import com.hobbie.services.user.usecase.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserUseCase userUseCase;

    private UserMapper mapper;

    public UserController(UserUseCase userUseCase, UserMapper mapper) {
        this.userUseCase = userUseCase;
        this.mapper = mapper;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userUseCase.getUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return ResponseEntity.ok().body(userUseCase.getUserById(id));
    }

    @PostMapping()
    public ResponseEntity<User> addUser(@RequestBody @Valid UserDTO dto) {

        var location = userUseCase.addUser(mapper.toModel(dto));
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(location).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{id}/feedback")
    public ResponseEntity<User> addFeedback(@PathVariable String id,
                                            @RequestBody @Valid FeedbackDto dto) {
        userUseCase.addFeedback(id, dto);

        return ResponseEntity.status(204).build();
    }
}
