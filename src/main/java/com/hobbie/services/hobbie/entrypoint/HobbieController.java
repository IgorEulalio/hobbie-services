package com.hobbie.services.hobbie.entrypoint;

import com.hobbie.services.hobbie.dataprovider.HobbieDataProvider;
import com.hobbie.services.hobbie.usecase.HobbieUseCase;
import com.hobbie.services.user.dataprovider.UserDataProvider;
import com.hobbie.services.user.entrypoint.dto.EventDto;
import com.hobbie.services.user.usecase.model.Event;
import com.hobbie.services.user.usecase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class HobbieController {

    @Autowired
    private HobbieDataProvider dataProvider;

    @Autowired
    private HobbieUseCase useCase;

    @Autowired
    private UserDataProvider UserDataProvider;

    @GetMapping("/hobbies")
    public ResponseEntity<List<Event>> getEvents(){
        return ResponseEntity.status(200).body(dataProvider.getEvents());
    }

    @PostMapping("/{user_id}/hobbies")
    public ResponseEntity<List<Event>> addEventsInUser(
            @PathVariable(required = true) String user_id,
            @RequestBody @Valid EventDto event){

        User user = UserDataProvider.getUserById(user_id);

        useCase.addCategoryInUser(user, event);
        return ResponseEntity.status(204).build();

    }
}
