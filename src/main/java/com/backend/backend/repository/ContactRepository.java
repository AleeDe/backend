package com.backend.backend.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.backend.entity.ContactFormEntry;

public interface ContactRepository extends MongoRepository<ContactFormEntry,ObjectId>{
    
}
