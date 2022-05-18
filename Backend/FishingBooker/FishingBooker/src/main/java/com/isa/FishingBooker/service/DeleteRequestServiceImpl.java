package com.isa.FishingBooker.service;

import com.isa.FishingBooker.exceptions.DeleteRequestException;
import com.isa.FishingBooker.exceptions.PendingAppointmentExistException;
import com.isa.FishingBooker.model.*;
import com.isa.FishingBooker.service.interfaces.TutorServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.isa.FishingBooker.exceptions.RequestHasResponseException;
import com.isa.FishingBooker.service.interfaces.DeleteRequestService;
import com.isa.FishingBooker.service.interfaces.UsersService;

import java.util.List;

@org.springframework.stereotype.Service
public class DeleteRequestServiceImpl extends CustomGenericService<DeleteRequest> implements DeleteRequestService {

    @Autowired
    private UsersService userService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private TutorServicesService tutorServicesService;
    @Autowired
    private AppointmentServiceImpl appointmentService;

    @Override
    public void addNew(DeleteRequest item) {
        item.setRequestStatus(Status.PENDING);
        try{
            validateDeletingUser(item.getUser().getId());
        }catch (PendingAppointmentExistException ex){
            throw new DeleteRequestException("Cannot create a delete request because pending appointments exists.");
        }
        super.addNew(item);
    }

    private void validateDeletingUser(int userId) {
        User user = userService.getById(userId);
        if (user instanceof Tutor) {
            List<TutorService> tutorServices = tutorServicesService.getAllValidByTutor(user.getId());
            tutorServices.forEach(tutorService -> {
                if (!appointmentService.getAllPendingByTutorServiceId(tutorService.getId()).isEmpty())
                    throw new PendingAppointmentExistException();
            });
        }
    }

    @Override
    @Transactional
    public void update(DeleteRequest item, String adminResponse, Admin admin) {
        DeleteRequest dbRequest = getById(item.getId());
        if (!dbRequest.getRequestStatus().equals(Status.PENDING)) throw new RequestHasResponseException();
        if (item.getRequestStatus().equals(Status.ADMIN_CONFIRMED))
            this.updateUserProfileStatus(dbRequest.getUser().getId());
        dbRequest.setRequestStatus(item.getRequestStatus());
        dbRequest.setAdminResponded(admin);
        this.update(dbRequest);
        emailService.sendDeleteRequestResponseNotification(userService.getById(item.getId()), item.getRequestStatus(),
                adminResponse);
    }

    private void updateUserProfileStatus(int userId) {
        userService.delete(userId);
    }
}
