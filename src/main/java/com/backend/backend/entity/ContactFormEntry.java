package com.backend.backend.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "contacts")
@Data
public class ContactFormEntry {
    @Id
    private ObjectId id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String message;

}
