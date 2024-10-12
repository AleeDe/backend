package com.backend.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.backend.entity.AppointmentEntry;
import com.backend.backend.services.AppointmentService;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @PostMapping
    public ResponseEntity<String> saveUserAppointment(@RequestBody AppointmentEntry newEntry){
        String response = appointmentService.saveAppointment(newEntry);
        if (response != null) {
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
}
