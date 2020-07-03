package com.hobbie.services.hobbie.dataprovider.repository;

import com.hobbie.services.hobbie.dataprovider.repository.entity.Hobbie;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface HobbieRepository extends MongoRepository<Hobbie, String> {

}
