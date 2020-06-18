package com.hobbie.services.user.usecase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Category {

    @NotNull
    private String title;

    @NotNull
    @JsonProperty("sub_categories")
    private List<SubCategory> subCategories;

    @NotNull
    private int type;

}
