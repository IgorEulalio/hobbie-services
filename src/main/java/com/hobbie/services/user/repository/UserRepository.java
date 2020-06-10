package com.hobbie.services.user.repository;

import com.hobbie.services.user.entrypoint.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String>{
}
