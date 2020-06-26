package com.hobbie.services.user.usecase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hobbie.services.hobbie.usecase.model.Category;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "user")
public class User {

    @Id
    String id;

    @JsonProperty("first_name")
    String firstName;

    @JsonProperty("last_name")
    String lastName;

    @JsonProperty("email")
    String email;

    @JsonProperty("feedback")
    Feedback feedback;

    private List<Category> categories;
}
