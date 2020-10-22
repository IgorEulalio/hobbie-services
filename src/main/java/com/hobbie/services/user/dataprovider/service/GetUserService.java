package com.hobbie.services.user.dataprovider.service;

import com.hobbie.services.config.infrastructure.LoggingRequestInterceptor;
import com.hobbie.services.user.dataprovider.repository.entity.User;
import com.hobbie.services.user.entrypoint.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class GetUserService {

    public List<UserDTO> getUser() {

        RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

        interceptors.add(new LoggingRequestInterceptor());

        restTemplate.setInterceptors(interceptors);

        ResponseEntity<UserDTO[]> userReceived = restTemplate.getForEntity("http://localhost:3030/users/", UserDTO[].class);

        UserDTO[] dtos = userReceived.getBody();

        List<UserDTO> users = Arrays.asList(dtos);
        return users;
    }
}
