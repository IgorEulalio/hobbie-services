package com.hobbie.services.hobbie.entrypoint.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HobbieDTO {

    @NotNull(message = "Obrigatório preenchimento do id.")
    private String id;

    @NotNull(message = "Obrigatório preenchimento da category.")
    private String category;

    @NotNull(message = "Obrigatório preenchimento da sub_category.")
    private String sub_category;
}
