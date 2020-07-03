package com.hobbie.services.hobbie.dataprovider;

import com.hobbie.services.hobbie.dataprovider.repository.HobbieRepository;
import com.hobbie.services.hobbie.dataprovider.repository.entity.Hobbie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HobbieDataProvider {

    private HobbieRepository repository;

    public HobbieDataProvider(HobbieRepository repository) {
        this.repository = repository;
    }

    public List<Hobbie> getHobbies(){
        return repository.findAll();
    }
}
