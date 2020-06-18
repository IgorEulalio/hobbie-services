package com.hobbie.services.hobbie.repository;

import com.hobbie.services.user.usecase.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface HobbieRepository extends MongoRepository<Event, String> {

}
