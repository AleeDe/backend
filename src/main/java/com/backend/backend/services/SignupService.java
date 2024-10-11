package com.backend.backend.services;

import java.util.Optional;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.backend.entity.UserEntry;
import com.backend.backend.repository.UsersRepository;

@Component
public class SignupService {
    @Autowired
    private UsersRepository usersRepository;

    public String saveEntry(UserEntry newEntry){
        if (usersRepository.findByEmail(newEntry.getEmail())!= null) {
            return "Email already taken";
        }
        else if (usersRepository.findByContactNo(newEntry.getContactNo()) != null) {
            return "Contact Number already exists";
        }
        else if (usersRepository.findByPassword(newEntry.getPassword()) != null) {
            return "Password already exists";
        }
        else{
            usersRepository.save(newEntry);
            return "SignUp Successful!";
        }
    }
    public void updateEntry(UserEntry newEntry){
        usersRepository.save(newEntry);
    }
    public List<UserEntry> getAll(){
        return usersRepository.findAll();
    }
    public Optional<UserEntry> findEntryByID(ObjectId id){ 
        return usersRepository.findById(id);
    }
    public UserEntry findEntryByEmail(String email){ 
        return usersRepository.findByEmail(email);
    }
    public UserEntry findEntryByContactNo(String contactNumber){ 
        return usersRepository.findByContactNo(contactNumber);
    }
    public UserEntry findEntryByPassword(String password){ 
        return usersRepository.findByPassword(password);
    }
    public boolean deleteEntryById(ObjectId id){
        usersRepository.deleteById(id);
        return true;
    }
}
