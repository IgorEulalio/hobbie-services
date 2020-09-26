package com.hobbie.services.hobbie.usecase;

import com.hobbie.services.hobbie.dataprovider.HobbieDataProvider;
import com.hobbie.services.hobbie.entrypoint.dto.HobbiesDTO;
import com.hobbie.services.hobbie.usecase.mapper.HobbieConverter;
import com.hobbie.services.hobbie.dataprovider.repository.entity.Hobbie;
import com.hobbie.services.user.dataprovider.UserDataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HobbieUseCase {

    @Autowired
    private UserDataProvider userDataProvider;

    @Autowired
    private HobbieConverter mapper;

    @Autowired
    HobbieDataProvider hobbieDataProvider;

    public void addCategoryInUser(String id, List<Hobbie> hobbies) {

        var user = userDataProvider.getUserById(id);
        user.setHobbies(hobbies);
        userDataProvider.addUser(user);
    }

    public HobbiesDTO getEvents() {
        var hobbies = hobbieDataProvider.getHobbies();
        return mapper.toDTO(hobbies);
    }
}