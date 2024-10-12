package com.backend.backend.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "appointments")
@Data
public class AppointmentEntry {
    @Id
    private ObjectId id;
    private String firstName;
    private String lastName;
    private String location;
    private String email;
    private String contactNo;
    private String healthIssue;

}
