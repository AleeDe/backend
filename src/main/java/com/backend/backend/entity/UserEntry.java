package com.backend.backend.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "users")
@Data
public class UserEntry {
    @Id
    private ObjectId id;
    private String firstName;
    private String lastName;
    private String address;
    private String contactNo;
    private String password;
    private String email;
    private String country;

}
