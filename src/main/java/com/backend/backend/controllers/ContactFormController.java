package com.backend.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.entity.ContactFormEntry;
import com.backend.backend.services.ContactService;

@RestController
@RequestMapping("/contact")
public class ContactFormController {
    @Autowired
    ContactService contactService;

    @PostMapping
    public ResponseEntity<String> saveContactResponse(@RequestBody ContactFormEntry newEntry){
        String response = contactService.saveMessage(newEntry);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
