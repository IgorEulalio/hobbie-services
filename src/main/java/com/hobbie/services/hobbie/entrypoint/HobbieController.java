package com.hobbie.services.hobbie.entrypoint;

import com.hobbie.services.hobbie.entrypoint.dto.HobbieDTO;
import com.hobbie.services.hobbie.entrypoint.dto.HobbiesDTO;
import com.hobbie.services.hobbie.entrypoint.mapper.HobbieMapper;
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

    private HobbieUseCase useCase;

    private HobbieMapper mapper;

    public HobbieController(HobbieUseCase useCase, HobbieMapper mapper) {
        this.useCase = useCase;
        this.mapper = mapper;
    }

    @GetMapping("/hobbies")
    public ResponseEntity<HobbiesDTO> getEvents() {
        return ResponseEntity.status(200).body(useCase.getEvents());
    }

    @PostMapping("users/{user_id}/hobbies")
    public ResponseEntity<Object> addEventsInUser(
            @PathVariable(required = true) String user_id,
            @RequestBody @Valid List<HobbieDTO> hobbies) {

        useCase.addCategoryInUser(user_id, mapper.toModels(hobbies));

        return ResponseEntity.status(204).build();
    }
}
