package com.hobbie.services.hobbie.usecase;

import com.hobbie.services.hobbie.dataprovider.HobbieDataProvider;
import com.hobbie.services.hobbie.usecase.model.Hobbie;
import com.hobbie.services.user.dataprovider.UserDataProvider;
import com.hobbie.services.user.entrypoint.dto.EventDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HobbieUseCase {

    @Autowired
    private UserDataProvider userDataProvider;

    @Autowired
    HobbieDataProvider hobbieDataProvider;

    public void addCategoryInUser(String id, EventDto event) {

        var user = userDataProvider.getUserById(id);
        user.setCategories(event.getCategories());
        userDataProvider.addUser(user);
    }

    public List<Hobbie> getEvents() {
        return hobbieDataProvider.getHobbies();
    }
}