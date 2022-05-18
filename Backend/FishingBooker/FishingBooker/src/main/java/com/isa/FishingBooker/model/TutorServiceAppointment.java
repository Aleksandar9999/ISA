package com.isa.FishingBooker.model;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.FishingBooker.exceptions.TutorservicePeriodException;

@Entity
public class TutorServiceAppointment extends Appointment {

	@ManyToOne
	@JoinColumn(name = "tutor_service_id")
	private TutorService tutorService;

	public TutorServiceAppointment() {
	}

	public TutorServiceAppointment(int id) {
		super(id);
	}

	@Override
	public double getCancelPercentage() {
		return tutorService.getCancelProcentage();
	}

	public TutorService getTutorService() {
		return tutorService;
	}
	
	public void setTutorService(TutorService tutorService) {
		this.tutorService = tutorService;
	}
	
	@JsonIgnore
	public int getTutorId() {
		return tutorService.getTutor().getId();
	}

	@Override
	public double getPriceCanceled() {
		return this.getPrice()*(tutorService.getCancelProcentage()/100);
	}

	@Override
	public User getOwner() {
		return tutorService.getTutor();
	}

	public void validateNewTutorServiceAppointmentPeriod(Period newPeriod) {
		if (this.getPeriod().getStartDate().before(Timestamp.from(Instant.now()))
				|| this.getPeriod().getEndDate().before(Timestamp.from(Instant.now())))
			throw new TutorservicePeriodException();
		Set<Period> standardPeriods = this.getTutorService().getTutor().getAvailable();
		for (Period period : standardPeriods) {
			period.periodBetweenPeriod(newPeriod);
		}
	}
}
