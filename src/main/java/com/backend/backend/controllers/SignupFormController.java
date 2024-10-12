package com.backend.backend.controllers;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> getAllEntries() {
        List<UserEntry> allEntries = signupService.getAll();
        if (allEntries != null && !allEntries.isEmpty()) {
            return new ResponseEntity<>(allEntries,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<?> getEntryByID(@PathVariable ObjectId id) {
        Optional<UserEntry> user = signupService.findEntryByID(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserEntry newUser ){
        String response = signupService.saveEntry(newUser);
        if (response.equals("SignUp Successful!")) {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/id/{id}")
    public ResponseEntity<UserEntry> updateEntry(@PathVariable ObjectId id, @RequestBody UserEntry newEntry) {
        UserEntry oldEntry = signupService.findEntryByID(id).orElse(null);
        if (oldEntry != null) {
            oldEntry.setFirstName(newEntry.getFirstName() != null && !newEntry.getFirstName().equals("") ? newEntry.getFirstName() : oldEntry.getFirstName());
            oldEntry.setLastName(newEntry.getLastName() != null && !newEntry.getLastName().equals("") ? newEntry.getLastName() : oldEntry.getLastName());

            oldEntry.setEmail(newEntry.getEmail() != null && !newEntry.getEmail().equals("") && signupService.findEntryByEmail(newEntry.getEmail()) == null ? newEntry.getEmail() : oldEntry.getEmail());
            
            oldEntry.setPassword(newEntry.getPassword() != null && !newEntry.getPassword().equals("") && signupService.findEntryByPassword(newEntry.getPassword()) == null ? newEntry.getPassword() : oldEntry.getPassword());
            
            oldEntry.setContactNo(newEntry.getContactNo() != null && !newEntry.getContactNo().equals("") && signupService.findEntryByContactNo(newEntry.getContactNo()) == null ? newEntry.getContactNo(): oldEntry.getContactNo());

            oldEntry.setAddress(newEntry.getAddress() != null && !newEntry.getAddress().equals("") ? newEntry.getAddress() : oldEntry.getAddress());
            oldEntry.setCountry(newEntry.getCountry() != null && !newEntry.getCountry().equals("") ? newEntry.getCountry() : oldEntry.getCountry());

            signupService.updateEntry(oldEntry);
            return new ResponseEntity<>(oldEntry,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(oldEntry,HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId id){
        signupService.deleteEntryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
