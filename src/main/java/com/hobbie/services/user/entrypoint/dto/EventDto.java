package com.hobbie.services.user.entrypoint.dto;

import com.hobbie.services.user.usecase.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    @NotNull
    List<Category> categories;
}
