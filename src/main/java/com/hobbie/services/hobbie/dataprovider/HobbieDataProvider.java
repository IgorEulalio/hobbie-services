package com.hobbie.services.hobbie.dataprovider;

import com.hobbie.services.hobbie.repository.HobbieRepository;
import com.hobbie.services.user.usecase.model.Event;
import com.hobbie.services.user.usecase.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class HobbieDataProvider {

    private HobbieRepository repository;

    public HobbieDataProvider(HobbieRepository repository) {
        this.repository = repository;
    }

    public List<Event> getEvents(){
        return repository.findAll();
    }

}
