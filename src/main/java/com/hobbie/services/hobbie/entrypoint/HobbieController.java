package com.hobbie.services.hobbie.entrypoint;

import com.hobbie.services.hobbie.entrypoint.dto.HobbiesDTO;
import com.hobbie.services.hobbie.usecase.HobbieUseCase;
import com.hobbie.services.hobbie.dataprovider.repository.entity.Hobbie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HobbieController {

    Logger logger = LoggerFactory.getLogger(HobbieController.class);

    @Autowired
    private HobbieUseCase useCase;

    @GetMapping("/hobbies")
    public ResponseEntity<HobbiesDTO> getEvents() {
        return ResponseEntity.status(200).body(useCase.getEvents());
    }

    @PostMapping("users/{user_id}/hobbies")
    public ResponseEntity<List<Hobbie>> addEventsInUser(
            @PathVariable(required = true) String user_id,
            @RequestBody @Valid List<Hobbie> hobbies) {

        useCase.addCategoryInUser(user_id, hobbies);


        return ResponseEntity.status(204).build();

    }
}
