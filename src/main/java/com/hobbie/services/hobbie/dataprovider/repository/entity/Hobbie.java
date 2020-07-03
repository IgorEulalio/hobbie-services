package com.hobbie.services.hobbie.dataprovider.repository.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "hobbie")
public class Hobbie {

    @Id
    private String id;

    private String category;

    @JsonProperty("sub_category")
    private String subCategory;
}

