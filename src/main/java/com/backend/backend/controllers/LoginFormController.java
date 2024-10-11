package com.backend.backend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.entity.LoginEntry;
import com.backend.backend.services.LoginService;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;






@RestController
@RequestMapping("/login")
public class LoginFormController {

    @Autowired
    private LoginService loginService;
    
    @PostMapping
    public ResponseEntity<String> isValidUser(@RequestBody LoginEntry entry) {
        String response = loginService.login(entry.getEmail(),entry.getPassword());
        if (response.equals("Login Successful!")) {
            return ResponseEntity.ok(response);
        }
        else{
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PutMapping("/{id}")
    public String ResetPassword(@PathVariable ObjectId id, @RequestBody LoginEntry updatedEntry) {
        return loginService.ForgotPassword(updatedEntry.getEmail(), updatedEntry.getPassword());
    }
    
}
