package com.backend.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.entity.UserEntry;
import com.backend.backend.services.SignupService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/signup")
public class SignupFormController {
    @Autowired
    private SignupService signupService;

    @GetMapping
    public List<UserEntry> getAllEntries() {
        return signupService.getAll();
    }
    @GetMapping("/id/{id}")
    public Optional<UserEntry> getEntryByID(@PathVariable ObjectId id) {
        return signupService.findEntryByID(id);
    }
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserEntry newUser ){
        String response = signupService.saveEntry(newUser);
        if (response.equals("SignUp Successful!")) {
            return ResponseEntity.ok(response);
        }
        else{
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PutMapping("/id/{id}")
    public UserEntry updateEntry(@PathVariable ObjectId id, @RequestBody UserEntry newEntry) {
        UserEntry oldEntry = signupService.findEntryByID(id).orElse(null);
        if (oldEntry != null) {
            oldEntry.setFirstName(newEntry.getFirstName() != null && !newEntry.getFirstName().equals("") ? newEntry.getFirstName() : oldEntry.getFirstName());
            oldEntry.setLastName(newEntry.getLastName() != null && !newEntry.getLastName().equals("") ? newEntry.getLastName() : oldEntry.getLastName());

            oldEntry.setEmail(newEntry.getEmail() != null && !newEntry.getEmail().equals("") && signupService.findEntryByEmail(newEntry.getEmail()) == null ? newEntry.getEmail() : oldEntry.getEmail());
            
            oldEntry.setPassword(newEntry.getPassword() != null && !newEntry.getPassword().equals("") && signupService.findEntryByPassword(newEntry.getPassword()) == null ? newEntry.getPassword() : oldEntry.getPassword());
            
            oldEntry.setContactNo(newEntry.getContactNo() != null && !newEntry.getContactNo().equals("") && signupService.findEntryByContactNo(newEntry.getContactNo()) == null ? newEntry.getContactNo(): oldEntry.getContactNo());

            oldEntry.setAddress(newEntry.getAddress() != null && !newEntry.getAddress().equals("") ? newEntry.getAddress() : oldEntry.getAddress());
            oldEntry.setCountry(newEntry.getCountry() != null && !newEntry.getCountry().equals("") ? newEntry.getCountry() : oldEntry.getCountry());
        }
        signupService.updateEntry(oldEntry);
        return oldEntry;
    }
    @DeleteMapping("/id/{id}")
    public boolean deleteEntry(@PathVariable ObjectId id){
        signupService.deleteEntryById(id);
        return true;
    }
}
