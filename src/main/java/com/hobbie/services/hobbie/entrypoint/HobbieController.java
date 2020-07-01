package com.hobbie.services.hobbie.entrypoint;

import com.hobbie.services.hobbie.dataprovider.HobbieDataProvider;
import com.hobbie.services.hobbie.usecase.HobbieUseCase;
import com.hobbie.services.user.dataprovider.UserDataProvider;
import com.hobbie.services.user.entrypoint.dto.EventDto;
import com.hobbie.services.hobbie.usecase.model.Hobbie;
import com.hobbie.services.user.usecase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HobbieController {

    @Autowired
    private HobbieUseCase useCase;

    @GetMapping("/hobbies")
    public ResponseEntity<List<Hobbie>> getEvents() {
        return ResponseEntity.status(200).body(useCase.getEvents());
    }

    @PostMapping("/{user_id}/hobbies")
    public ResponseEntity<List<Hobbie>> addEventsInUser(
            @PathVariable(required = true) String user_id,
            @RequestBody @Valid EventDto event) {

        useCase.addCategoryInUser(user_id, event);
        return ResponseEntity.status(204).build();

    }
}
