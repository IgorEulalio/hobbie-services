package com.hobbie.services.user.usecase.mapper;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.hobbie.services.user.dataprovider.repository.entity.User;
import com.hobbie.services.user.entrypoint.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toModel(UserDTO dto) {
        var user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }
}
