package com.isa.FishingBooker.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public abstract class Appointment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    private Period period;
    private int maxPerson;
    private String additionalServices;
    private double price;
    @Enumerated(EnumType.STRING)
    private AppointmentType appointType;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    public Appointment() {
        this.status = AppointmentStatus.PENDING;
    }

    public Appointment(int id) {
        this.id = id;
    }

    public void setDuration(Period duration) {
        this.period = duration;
    }
    public abstract double getCancelPercentage();
    public abstract User getOwner();
    public abstract double getPriceCanceled();
    public Period getPeriod() {
        return period;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getMaxPerson() {
        return maxPerson;
    }

    public void setMaxPerson(int maxPerson) {
        this.maxPerson = maxPerson;
    }

    public String getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonIgnore
    public boolean inPeriod(LocalDateTime date) {
        LocalDateTime end = period.getEndDate().toLocalDateTime();
        LocalDateTime startLocal = period.getStartDate().toLocalDateTime();
        return (startLocal.isBefore(date) && end.isAfter(date)) || startLocal.toLocalDate().isEqual(date.toLocalDate())
                || end.isEqual(date);
    }

    public AppointmentType getType() {
        return appointType;
    }

    @JsonIgnore
    public Timestamp getStart() {
        return this.period.getStartDate();
    }

    public int getDuration() {
        return (int) this.period.getDurationInDays();
    }

    public void setType(AppointmentType appointType) {
        this.appointType = appointType;
    }

    public AppointmentType getAppointType() {
        return appointType;
    }

    public void setAppointType(AppointmentType appointType) {
        this.appointType = appointType;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
