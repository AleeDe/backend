package com.backend.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.backend.entity.ContactFormEntry;
import com.backend.backend.repository.ContactRepository;

@Component
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public String saveMessage(ContactFormEntry newEntry){
        contactRepository.save(newEntry);
        return "Message Sent Successfully";
    }
}
