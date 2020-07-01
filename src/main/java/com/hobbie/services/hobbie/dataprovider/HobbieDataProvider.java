package com.hobbie.services.hobbie.dataprovider;

import com.hobbie.services.hobbie.repository.HobbieRepository;
import com.hobbie.services.hobbie.usecase.model.Hobbie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HobbieDataProvider {

    private HobbieRepository repository;

    public HobbieDataProvider(HobbieRepository repository) {
        this.repository = repository;
    }

    public List<Hobbie> getEvents(){
        return repository.findAll();
    }

}
