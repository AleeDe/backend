package com.backend.backend.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.backend.entity.AppointmentEntry;

public interface AppointmentRepository extends MongoRepository<AppointmentEntry,ObjectId>{
    
}
