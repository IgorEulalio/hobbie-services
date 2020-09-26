package com.hobbie.services.user.dataprovider.repository.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.OptionalDouble;

@Data
@Builder
@Getter
@Setter
public class Feedback {

    @JsonProperty("nota")
    List<Double> nota;

    public double mediaNota;

    public int avaliacoes;

    public List<Double> getNota() {
        return nota;
    }

    public double getMediaNota() {
        return nota.stream().mapToDouble(a -> a).average().orElse(5.00);
    }
}
