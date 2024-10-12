package com.backend.backend.services;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.backend.entity.UserEntry;
import com.backend.backend.repository.UsersRepository;

@Component
public class LoginService {
    @Autowired
    private UsersRepository userRepository;

    public String login(String email, String password){
        UserEntry user = userRepository.findByEmail(email);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                return "Login Successful!";
            }
            else{
                return "Invalid Password";
            }
        }
        return "User not found!";
    }
    public String ForgotPassword(ObjectId id,String email,String newPassword){
        UserEntry user = userRepository.findById(id).orElse(null);
        if (user != null && user.getEmail().equals(email)) {
            user.setPassword(newPassword);
            userRepository.save(user);
            return "New Password set successfully";
        }
        else{
            return "Please SignUp";
        }
    }
}
