package com.hobbie.services.user.repository;

import com.hobbie.services.user.usecase.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
}
