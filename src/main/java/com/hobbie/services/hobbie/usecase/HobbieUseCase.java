package com.hobbie.services.hobbie.usecase;

import com.hobbie.services.user.dataprovider.UserDataProvider;
import com.hobbie.services.user.entrypoint.dto.EventDto;
import com.hobbie.services.user.usecase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class HobbieUseCase {

    @Autowired
    private UserDataProvider dataProvider;


    public void addCategoryInUser(User user, EventDto event) {

        user.setCategories(event.getCategories());
        dataProvider.addUser(user);
    }
}