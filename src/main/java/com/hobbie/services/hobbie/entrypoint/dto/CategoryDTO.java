package com.hobbie.services.hobbie.entrypoint.dto;

import com.hobbie.services.hobbie.dataprovider.repository.entity.Hobbie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private String type;

    private List<Hobbie> hobbies;
}
