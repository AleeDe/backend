package com.backend.backend.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.backend.entity.UserEntry;


public interface UsersRepository extends MongoRepository<UserEntry,ObjectId>{
    UserEntry findByEmail(String email);
    UserEntry findByContactNo(String contactNo);
    UserEntry findByPassword(String password);
}

