package com.isa.FishingBooker.service;

import com.isa.FishingBooker.service.interfaces.AppointmentService;
import com.isa.FishingBooker.service.interfaces.TutorServicesService;
import com.isa.FishingBooker.service.interfaces.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesServiceHelper {
    @Autowired
    public TutorServicesService tutorServicesService;
    @Autowired
    public AppointmentService appointmentService;
    @Autowired
    public UsersService usersService;
    @Autowired
    public EmailService emailService;

    UserServicesServiceHelper(){}
}
