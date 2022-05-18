package com.isa.FishingBooker.dto;

import java.util.List;
import java.util.stream.Stream;

import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.AppointmentStatus;
import com.isa.FishingBooker.model.CompletedAppointment;

public class BusinessReportDTO {

    protected List<CompletedAppointment> completedAppointments;
    protected List<Appointment> allAppointments;

    public BusinessReportDTO(List<CompletedAppointment> completedAppointments, List allAppointments) {
        this.completedAppointments = completedAppointments;
        this.allAppointments=allAppointments;
    }

    public List<?> getCompletedAppointments() {
        return completedAppointments;
    }

    public List<?> getAllAppointments() {
        return allAppointments;
    }

    public double getAvgPriceOfAppointment() {
        return allAppointments.stream().mapToDouble(Appointment::getPrice).average().getAsDouble();
    }

    public double getTotalOwnerRevenue() {
        double sum = filerCompletedAppointmentsByStatus(AppointmentStatus.SUCCESSFUL).mapToDouble(CompletedAppointment::getOwnerRevenue).sum();
        sum += filerCompletedAppointmentsByStatus(AppointmentStatus.CANCELED).mapToDouble(CompletedAppointment::getCanceledAppointmentOwnerRevenue).sum();
        return sum;
    }

    public double getTotalSystemRevenue() {
        double sum = filerCompletedAppointmentsByStatus(AppointmentStatus.SUCCESSFUL).mapToDouble(CompletedAppointment::getSystemRevenue).sum();
        return sum;
    }

    public long getNumberOfCancelledAppointments() {
        return filerCompletedAppointmentsByStatus(AppointmentStatus.CANCELED).count();
    }

    public long getNumberOfSuccessfulAppointments() {
        return filerCompletedAppointmentsByStatus(AppointmentStatus.SUCCESSFUL).count();
    }

    public long getTotalNumberOfAppointments() {
        return allAppointments.stream().count();
    }

    private Stream<CompletedAppointment> filerCompletedAppointmentsByStatus(AppointmentStatus status) {
        return completedAppointments.stream().filter(app -> app.getAppointment().getStatus().equals(status));
    }
}
