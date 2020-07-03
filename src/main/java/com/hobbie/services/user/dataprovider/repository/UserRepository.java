package com.hobbie.services.user.dataprovider.repository;

import com.hobbie.services.user.dataprovider.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
}

