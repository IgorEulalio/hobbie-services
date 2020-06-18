package com.hobbie.services.user.usecase.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Feedback {

    @JsonProperty("nota")
    Double nota;

    @JsonProperty("avaliacoes")
    Integer avaliacoes;
}
