package com.isa.FishingBooker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.isa.FishingBooker.exceptions.PeriodOverlapException;

@Entity
public class Tutor extends User {

	private static final long serialVersionUID = -4342071307816418991L;
	@OneToMany(mappedBy = "tutor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<TutorService> services = new HashSet<TutorService>();
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Period> available;

	public Tutor() {
		super();
	}

	@Override
	public void setRolesNames() {
		super.setRolesNames();
		this.setRoleName(Role.TUTOR_ROLE);
	}

	public Tutor(String bio, Set<TutorService> services) {
		this();
		this.services = services;
	}

	public Tutor(Integer idtutor) {
		this();
		this.setId(idtutor);
	}

	public void updateStandardPeriod(Period takenPeriodOfAppointment) {
		for (Period tutorPeriod : available) {
			try {
				tutorPeriod.overlap(takenPeriodOfAppointment);
			} catch (PeriodOverlapException ex) {
				if (Period.isSameDate(tutorPeriod.getStartDate(), takenPeriodOfAppointment.getStartDate())) {
					tutorPeriod.setStartDate(takenPeriodOfAppointment.getEndDate());
				} else {
					Period newBeforePeriod = new Period(tutorPeriod.getStartDate(),
							takenPeriodOfAppointment.getStartDate());
					Period newAfterPeriod = new Period(takenPeriodOfAppointment.getEndDate(), tutorPeriod.getEndDate());
					available.remove(tutorPeriod);
					available.add(newBeforePeriod);
					available.add(newAfterPeriod);
				}
				break;
			}
		}
	}

	public void setServices(Set<TutorService> services) {
		this.services = services;
	}

	public void addService(TutorService service) {
		services.add(service);
	}

	public Set<TutorService> getServices() {
		return services;
	}

	public Set<Period> getAvailable() {
		return available;
	}

	public void setAvailable(Set<Period> available) {
		this.available = available;
	}

	public void addAvailablePeriod(Period period) {
		if (this.available == null)
			this.available = new HashSet<Period>();
		this.available.add(period);
	}

	@Override
	public boolean isEnabled() {
		return this.getStatus().equals(Status.ADMIN_CONFIRMED);
	}
}
