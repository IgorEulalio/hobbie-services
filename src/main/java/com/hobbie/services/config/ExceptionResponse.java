package com.hobbie.services.config;

import lombok.*;

@Builder
@Data
public class ExceptionResponse {

    String message;

    public ExceptionResponse(String message){
        this.message = message;
    }


}
