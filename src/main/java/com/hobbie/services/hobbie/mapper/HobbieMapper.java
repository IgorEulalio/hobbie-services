package com.hobbie.services.hobbie.mapper;

import com.hobbie.services.hobbie.entrypoint.dto.CategoryDTO;
import com.hobbie.services.hobbie.entrypoint.dto.HobbiesDTO;
import com.hobbie.services.hobbie.usecase.model.Hobbie;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class HobbieMapper {


    public HobbiesDTO toDTO(List<Hobbie> hobbies) {

        Map<String, List<Hobbie>> map = hobbies.stream().collect(Collectors.groupingBy(h -> h.getCategory()));

        List<CategoryDTO> categories = new ArrayList();

        map.forEach(
                (k, v) -> {
                    var dto = CategoryDTO.builder()
                            .type(k)
                            .hobbies(v)
                            .build();

                    categories.add(dto);
                }
        );

        HobbiesDTO dto = HobbiesDTO.builder()
                .categories(categories)
                .build();
        return dto;
    }
}
