package com.backend.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.backend.backend.entity.AppointmentEntry;
import com.backend.backend.entity.UserEntry;
import com.backend.backend.repository.AppointmentRepository;
import com.backend.backend.repository.UsersRepository;

@Component
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private UsersRepository usersRepository;

    public String saveAppointment(AppointmentEntry newEntry){
        UserEntry user = usersRepository.findByEmail(newEntry.getEmail());
        System.out.println(user);
        if (user != null) {
            appointmentRepository.save(newEntry);
            return "Appointment Booked Successfully";
        }
        else{
            return "SignUp Required";
        }
    }
}
