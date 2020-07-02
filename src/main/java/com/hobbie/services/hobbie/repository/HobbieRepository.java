package com.hobbie.services.hobbie.repository;

import com.hobbie.services.hobbie.usecase.model.Hobbie;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface HobbieRepository extends MongoRepository<Hobbie, String> {

}
