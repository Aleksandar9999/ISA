package com.isa.FishingBooker.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.exceptions.PeriodOverlapException;
import com.isa.FishingBooker.exceptions.TutorservicePeriodException;
import com.isa.FishingBooker.exceptions.UserAppointmentInProgressException;
import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.AppointmentStatus;
import com.isa.FishingBooker.model.AppointmentType;
import com.isa.FishingBooker.model.Boat;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.CompletedAppointment;
import com.isa.FishingBooker.model.Period;
import com.isa.FishingBooker.model.ResortAppointment;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.model.TutorServiceAppointment;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.repository.AppointmentRepository;
import com.isa.FishingBooker.repository.CompleteAppointmentRepository;
import com.isa.FishingBooker.security.auth.TokenBasedAuthentication;
import com.isa.FishingBooker.service.interfaces.AppointmentService;
import com.isa.FishingBooker.service.interfaces.BoatsService;
import com.isa.FishingBooker.service.interfaces.SystemDataService;
import com.isa.FishingBooker.service.interfaces.TutorServicesService;
import com.isa.FishingBooker.service.interfaces.UserCategorySettingsService;
import com.isa.FishingBooker.service.interfaces.UsersService;

@Service
public class AppointmentServiceImpl extends CustomGenericService<Appointment> implements AppointmentService {

	@Autowired
	private CompleteAppointmentRepository completeAppointmentRepository;

	@Autowired
	private TutorServicesService tutorServicesService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UsersService userService;
	@Autowired
	private UserCategorySettingsService userCategoryService;
	@Autowired
	private SystemDataService systemDataService;
	@Autowired
	private BoatsService boatsService;

	@Override
	public List<TutorServiceAppointment> getAllTutorServiceAppointmentsByTutor(int id) {
		return ((AppointmentRepository) repository).getAllAppointmentsByTutor(id);
	}


	@Transactional
	@Override
	public void finishAppointment(Integer id) {
		Appointment appointment = this.getById(id);
		appointment.setStatus(AppointmentStatus.SUCCESSFUL);
		this.update(appointment);
		saveCompletedAppointment(appointment);
	}

	@Override
	public List<CompletedAppointment> getAllCompletedAppointmentsInPeriod(java.sql.Date start, java.sql.Date end) {
		return completeAppointmentRepository.getAllInPeriod(start,end);
	}

	@Override
	public List<CompletedAppointment> getAllCompletedAppointmentsInPeriodByTutorId(int tutorId, java.sql.Date start, java.sql.Date end) {
		return completeAppointmentRepository.getAllInPeriodByTutorId(tutorId,start,end);
	}

	@Transactional
	private void saveCompletedAppointment(Appointment appointment) {
		double ownerRevenuePercentage = this.userCategoryService
				.findOwnerRevenueProcentage(appointment.getOwner().getPoints());

		CompletedAppointment completedAppointment = new CompletedAppointment(appointment, ownerRevenuePercentage,
				appointment.getPrice(), systemDataService.findCurrentlyActive().getProcentage(),appointment.getCancelPercentage());

		completeAppointmentRepository.save(completedAppointment);
	}

	@Override
	@Transactional
	public void addNewTutorServiceAppointment(TutorServiceAppointment app) {
		TutorService tutorService = tutorServicesService.getById(app.getTutorService().getId());
		tutorService.validateMaxNumberOfPersons(app.getMaxPerson());
		app.setTutorService(tutorService);
		app.setAddress(tutorService.getAddress());
		app.setType(AppointmentType.TUTORSERVICE);
		try {
			app.validateNewTutorServiceAppointmentPeriod(app.getPeriod());
		} catch (PeriodOverlapException ex) {
			double priceWithoutDiscount = tutorService.calculatePrice((int) app.getPeriod().getDurationInDays());
			double procentageForClient = userCategoryService
					.findDiscountProcentage(userService.getById(app.getUser().getId()).getPoints());
			app.setPrice(priceWithoutDiscount - (priceWithoutDiscount * procentageForClient / 100));
			updateTutorAvailablePeriods(tutorService, app);
			super.addNew(app);
			emailService.sendReservationMail(userService.getById(app.getUser().getId()));
			return;
		}
		throw new TutorservicePeriodException();
	}

	@Transactional
	private void updateTutorAvailablePeriods(TutorService ts, TutorServiceAppointment app) {
		ts.getTutor().updateStandardPeriod(app.getPeriod());
		tutorServicesService.update(ts);
	}
	

	@Override
	public void addNewTutorServiceAppointmentByTutor(TutorServiceAppointment app, boolean validateUser) {
		if (!validateUseCurrentAppointment(app.getUser().getId(), app.getTutorService().getId()))
			throw new UserAppointmentInProgressException();
		this.addNewTutorServiceAppointment(app);
	}

	private boolean validateUseCurrentAppointment(Integer userid, int tutorServiceId) {
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		Period currentPeriod = new Period(currentDate, currentDate);
		TutorService tutorService = tutorServicesService.getById(tutorServiceId);
		for (TutorServiceAppointment appointment : ((AppointmentRepository) repository)
				.getAllByTutorAndUserBeforeCurrentDate(userid, tutorService.getTutor().getId())) {
			try {
				appointment.getPeriod().overlap(currentPeriod);
			} catch (PeriodOverlapException e) {
				return true;
			}
		}
		return false;
	}

	public void addNewTutorServiceAppointmentFromDiscount(TutorServiceAppointment app) {
		TutorService tutorService = tutorServicesService.getById(app.getTutorService().getId());
		app.setTutorService(tutorService);
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext()
				.getAuthentication();
		User usr = (User) aut.getPrincipal();
		app.setUser(usr);
		app.setAddress(tutorService.getAddress());
		app.setType(AppointmentType.TUTORSERVICE);
		super.addNew(app);
		emailService.sendReservationMail(userService.getById(app.getUser().getId()));
	}
	// TODO:REFACTOR PLEASE
	public List<Appointment> getPendingApointments() {
		List<Appointment> appointments = new ArrayList<Appointment>();
		Date thisMoment = new Date(System.currentTimeMillis());

		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext()
				.getAuthentication();
		User usr = (User) aut.getPrincipal();
		String email = usr.getEmail();
		for (BoatAppointment a : ((AppointmentRepository) repository).getAllBoatAppoints()) {
			if (a.getUser() != null) {
				if (a.getUser().getEmail().equals(email)) {
					if (a.getStart().compareTo(thisMoment) > 0) {
						appointments.add(a);
					}
				}
			}
		}
		for (ResortAppointment a : ((AppointmentRepository) repository).getAllResortAppoints()) {
			if (a.getUser() != null) {
				if (a.getUser().getEmail().equals(email)) {
					if (a.getStart().compareTo(thisMoment) > 0) {
						appointments.add(a);
					}
				}
			}
		}
		for (TutorServiceAppointment a : ((AppointmentRepository) repository).getAllTutorServiceAppointments()) {
			if (a.getUser() != null) {
				if (a.getUser().getEmail().equals(email)) {
					if (a.getStart().compareTo(thisMoment) > 0) {
						appointments.add(a);
					}
				}
			}
		}
		return appointments;
	}

	@Override
	public void deleteResortAppointments(int resortId) {
		((AppointmentRepository) repository).deleteResortAppointments(resortId);
	}

	@Override
	public List<TutorServiceAppointment> getAllByTutorAndPeriod(int tutorId, java.sql.Date start, java.sql.Date end) {
		return ((AppointmentRepository) repository).getAllByTutorAndPeriod(tutorId, start, end);
	}

	@Override
	@Transactional
	public String makeBoatReservation(BoatAppointment appointment) {
		// TODO Auto-generated method stub
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext()
				.getAuthentication();
		User usr = (User) aut.getPrincipal();
		appointment.setUser(usr);
		((AppointmentRepository) repository).save(appointment);
		emailService.sendReservationMail(usr);
		return null;
	}

	@Override
	@Transactional
	public String makeResortReservation(ResortAppointment appointment) {
		// TODO Auto-generated method stub
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext()
				.getAuthentication();
		User usr = (User) aut.getPrincipal();
		appointment.setUser(usr);
		((AppointmentRepository) repository).save(appointment);
		emailService.sendReservationMail(usr);
		return null;
	}

	@Override
	@Transactional
	public String makeTutorServiceReservation(TutorServiceAppointment appointment) {
		// TODO Auto-generated method stub
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext()
				.getAuthentication();
		User usr = (User) aut.getPrincipal();
		appointment.setUser(usr);
		((AppointmentRepository) repository).save(appointment);
		emailService.sendReservationMail(usr);
		return null;
	}

	@Override
	public String cancelReservation(Integer id) {
		// TODO Auto-generated method stub
		Date thisMoment = new Date(System.currentTimeMillis());
		Calendar cal = Calendar.getInstance();
		cal.setTime(thisMoment);
		cal.add(Calendar.DATE, 3);
		thisMoment = cal.getTime();

		for (BoatAppointment a : ((AppointmentRepository) repository).getAllBoatAppoints()) {
			if (a.getId() == id) {
				if (a.getStart().compareTo(thisMoment) > 0) {
					User u = a.getUser();
					a.setUser(null);
					if (u.getReservationsList() == null) {
						Set<Integer> reservList = new HashSet<Integer>();
						reservList.add(id);
						u.setReservationsList(reservList);
						userService.update(u);
					} else {
						u.getReservationsList().add(id);
						userService.update(u);
					}
				} else
					return "You can not cancel reservation in last 3 days.";
			}
		}

		for (ResortAppointment a : ((AppointmentRepository) repository).getAllResortAppoints()) {
			if (a.getId() == id) {
				if (a.getStart().compareTo(thisMoment) > 0) {
					User u = a.getUser();
					a.setUser(null);
					if (u.getReservationsList() == null) {
						Set<Integer> reservList = new HashSet<Integer>();
						reservList.add(id);
						u.setReservationsList(reservList);
						userService.update(u);
					} else {
						u.getReservationsList().add(id);
						userService.update(u);
					}
				} else
					return "You can not cancel reservation in last 3 days.";
			}
		}

		for (TutorServiceAppointment a : ((AppointmentRepository) repository).getAllTutorServiceAppointments()) {
			if (a.getId() == id) {
				if (a.getStart().compareTo(thisMoment) > 0) {
					User u = a.getUser();
					a.setUser(null);
					if (u.getReservationsList() == null) {
						Set<Integer> reservList = new HashSet<Integer>();
						reservList.add(id);
						u.setReservationsList(reservList);
						userService.update(u);
					} else {
						u.getReservationsList().add(id);
						userService.update(u);
					}
				} else
					return "You can not cancel reservation in last 3 days.";
			}
		}
		return "Reservation canceled.";
	}

	public List<BoatAppointment> getAllBoatAppoints() {
		return ((AppointmentRepository) repository).getAllBoatAppoints();
	}

	public List<ResortAppointment> getAllResortAppoints() {
		return ((AppointmentRepository) repository).getAllResortAppoints();
	}

	public List<TutorServiceAppointment> getAllTutorServiceAppoints() {
		return ((AppointmentRepository) repository).getAllTutorServiceAppointments();
	}

	@Override
	@Transactional
	public String makeQuickReservation(Integer id) {
		// TODO Auto-generated method stub
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext()
				.getAuthentication();
		User usr = (User) aut.getPrincipal();
		Appointment appointment = repository.getById(id);

		if (appointment.getType() == AppointmentType.BOAT) {
			BoatAppointment ba = new BoatAppointment();
			ba = this.mapAppointmentToBoatAppointment(appointment, ba);
			ba.setUser(usr);
			((AppointmentRepository) repository).save(ba);
		} else if (appointment.getType() == AppointmentType.RESORT) {
			ResortAppointment ra = new ResortAppointment();
			ra = this.mapAppointmentToResortAppointment(appointment, ra);
			ra.setUser(usr);
			((AppointmentRepository) repository).save(ra);
		} else {
			Appointment a = repository.getById(id);
			TutorServiceAppointment tsa = new TutorServiceAppointment();
			tsa = this.mapAppointmentToTutorServiceAppointment(appointment, tsa);
			tsa.setUser(usr);
			((AppointmentRepository) repository).save(a);
		}
		emailService.sendReservationMail(usr);
		return null;
	}

	@Override
	public List<Appointment> getOldAppointments() {
		// TODO Auto-generated method stub
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext()
				.getAuthentication();
		User usr = (User) aut.getPrincipal();

		List<BoatAppointment> boatLista = ((AppointmentRepository) repository).getAllBoatAppoints();
		List<ResortAppointment> resortLista = ((AppointmentRepository) repository).getAllResortAppoints();
		List<TutorServiceAppointment> tutorServiceLista = ((AppointmentRepository) repository)
				.getAllTutorServiceAppointments();
		List<Appointment> retLista = new ArrayList<Appointment>();

		if (boatLista.size() > 0) {
			for (BoatAppointment ap : boatLista) {
				if (ap.getUser() != null) {
					if (ap.getUser().getId() == usr.getId()) {
						retLista.add(ap);
					}
				}
			}
		}

		if (resortLista.size() > 0) {
			for (ResortAppointment ap : resortLista) {
				if (ap.getUser() != null) {
					if (ap.getUser().getId() == usr.getId()) {
						retLista.add(ap);
					}
				}
			}
		}

		if (tutorServiceLista.size() > 0) {
			for (TutorServiceAppointment ap : tutorServiceLista) {
				if (ap.getUser() != null) {
					if (ap.getUser().getId() == usr.getId()) {
						retLista.add(ap);
					}
				}
			}
		}

		return retLista;
	}

	@Override
	public List<String> getAdditionalServicesForBoat(Integer boatId) {
		// TODO Auto-generated method stub
		List<String> addServices = new ArrayList<>();
		List<BoatAppointment> appointments = new ArrayList<BoatAppointment>();
		Date thisMoment = new Date(System.currentTimeMillis());
		for (BoatAppointment a : ((AppointmentRepository) repository).getAllBoatAppoints()) {
			if (a.getStart().compareTo(thisMoment) > 0) {
				appointments.add(a);
			}
		}
		for (BoatAppointment b : appointments) {
			if (b.getBoat().getId() == boatId) {
				addServices.add(b.getAdditionalServices());
			}
		}
		return addServices;
	}

	@Override
	public List<Date> getBoatPeriods(Integer boatId) {
		// TODO Auto-generated method stub
		List<Date> retList = new ArrayList<Date>();
		List<BoatAppointment> appointments = new ArrayList<BoatAppointment>();
		Date thisMoment = new Date(System.currentTimeMillis());
		for (BoatAppointment a : ((AppointmentRepository) repository).getAllBoatAppoints()) {
			if (a.getStart().compareTo(thisMoment) > 0) {
				appointments.add(a);
			}
		}
		for (BoatAppointment b : appointments) {
			if (b.getBoat().getId() == boatId) {
				retList.add(b.getStart());
			}
		}

		return retList;
	}

	private BoatAppointment mapAppointmentToBoatAppointment(Appointment appointment, BoatAppointment ba) {
		ba.setId(appointment.getId());
		ba.setAdditionalServices(appointment.getAdditionalServices());
		ba.setAddress(appointment.getAddress());
		ba.setDuration(appointment.getPeriod());
		ba.setMaxPerson(appointment.getMaxPerson());
		ba.setPrice(appointment.getPrice());
		// ba.setStart(appointment.getStart());
		ba.setType(appointment.getType());
		return ba;
	}

	private ResortAppointment mapAppointmentToResortAppointment(Appointment appointment, ResortAppointment ra) {
		ra.setId(appointment.getId());
		ra.setAdditionalServices(appointment.getAdditionalServices());
		ra.setAddress(appointment.getAddress());
		ra.setDuration(appointment.getPeriod());
		ra.setMaxPerson(appointment.getMaxPerson());
		ra.setPrice(appointment.getPrice());
		// ra.setStart(appointment.getStart());
		ra.setType(appointment.getType());
		return ra;
	}

	private TutorServiceAppointment mapAppointmentToTutorServiceAppointment(Appointment appointment,
			TutorServiceAppointment ta) {
		ta.setId(appointment.getId());
		ta.setAdditionalServices(appointment.getAdditionalServices());
		ta.setAddress(appointment.getAddress());
		ta.setDuration(appointment.getPeriod());
		ta.setMaxPerson(appointment.getMaxPerson());
		ta.setPrice(appointment.getPrice());
		// ta.setStart(appointment.getStart());
		ta.setType(appointment.getType());
		return ta;
	}

	// non reserved appointments
	public List<ResortAppointment> getResortApointments() {
		List<ResortAppointment> list = ((AppointmentRepository) repository).getAllResortAppoints();
		List<ResortAppointment> returnList = new ArrayList<ResortAppointment>();
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext()
				.getAuthentication();
		User usr = (User) aut.getPrincipal();

		if (list != null) {
			for (ResortAppointment ra : list) {
				if (ra.getUser() == null && checkReservationList(ra.getId(), usr)) {
					returnList.add(ra);
				}
			}
		}
		return returnList;
	}

	// non reserved appointments
	public List<BoatAppointment> getBoatApointments() {
		List<BoatAppointment> list = ((AppointmentRepository) repository).getAllBoatAppoints();
		List<BoatAppointment> returnList = new ArrayList<BoatAppointment>();
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext()
				.getAuthentication();
		User usr = (User) aut.getPrincipal();

		if (list != null) {
			for (BoatAppointment ba : list) {
				if (ba.getUser() == null && checkReservationList(ba.getId(), usr)) {
					returnList.add(ba);
				}
			}
		}
		return returnList;
	}

	// non reserved appointments
	public List<TutorServiceAppointment> getTutorServiceApointments() {
		List<TutorServiceAppointment> list = ((AppointmentRepository) repository).getAllTutorServiceAppointments();
		List<TutorServiceAppointment> returnList = new ArrayList<TutorServiceAppointment>();
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext()
				.getAuthentication();
		User usr = (User) aut.getPrincipal();

		if (list != null) {
			for (TutorServiceAppointment tsa : list) {
				if (tsa.getUser() == null && checkReservationList(tsa.getId(), usr)) {
					returnList.add(tsa);
				}
			}
		}
		return returnList;
	}

	public boolean checkReservationList(Integer id, User u) {
		if (u.getReservationsList() != null) {
			for (Integer oldAppointIds : u.getReservationsList()) {
				if (id == oldAppointIds) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public List<Appointment> getAllInPeriod(java.sql.Date start, java.sql.Date end) {
		return ((AppointmentRepository) repository).getAllInPeriod(start, end);
	}

	@Override
	public List<TutorServiceAppointment> getAllPendingByTutorServiceId(int id) {
		return ((AppointmentRepository) repository).getAllPendingByTutorServiceId(id);
	}


	@Override
	public List<BoatAppointment> getAllBoatAppointmentsByBoatOwner(int id) {
		return ((AppointmentRepository) repository).getAllAppointmentsByBoatOwner(id);
	}


	@Override
	public void addNewBoatAppointment(BoatAppointment app) {
		Boat boat = boatsService.getById(app.getBoat().getId());
		boat.validateMaxNumberOfPersons(app.getMaxPerson());
		app.setBoat(boat);
		app.setAddress(boat.getBoatAddress());
		app.setType(AppointmentType.BOAT);
		try {
			app.validateNewBoatAppointmentPeriod(app.getPeriod());
		} catch (PeriodOverlapException ex) {
			double priceWithoutDiscount = boat.calculatePrice((int) app.getPeriod().getDurationInDays());
			double procentageForClient = userCategoryService
					.findDiscountProcentage(userService.getById(app.getUser().getId()).getPoints());
			app.setPrice(priceWithoutDiscount - (priceWithoutDiscount * procentageForClient / 100));
			updateBoatAvailablePeriods(boat, app);
			super.addNew(app);
			emailService.sendReservationMail(userService.getById(app.getUser().getId()));
			return;
		}
		throw new TutorservicePeriodException();
		
	}
	
	@Transactional
	private void updateBoatAvailablePeriods(Boat b, BoatAppointment app) {
		b.updateStandardPeriod(app.getPeriod());
		boatsService.update(b);
	}


	@Override
	public void addNewBoatAppointmentByBoatOwner(BoatAppointment app, boolean validateUser) {
		if (!validateUseCurrentAppointment(app.getUser().getId(), app.getBoat().getId()))
			throw new UserAppointmentInProgressException();
		this.addNewBoatAppointment(app);
		
	}


	@Override
	public void addNewBoatAppointmentFromDiscount(BoatAppointment app) {
		Boat boat = boatsService.getById(app.getBoat().getId());
		app.setBoat(boat);
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext()
				.getAuthentication();
		User usr = (User) aut.getPrincipal();
		app.setUser(usr);
		app.setAddress(boat.getBoatAddress());
		app.setType(AppointmentType.TUTORSERVICE);
		super.addNew(app);
		emailService.sendReservationMail(userService.getById(app.getUser().getId()));		
	}


	@Override
	public List<BoatAppointment> getAllByBoatOwnerAndPeriod(int boatOwnerId, java.sql.Date start, java.sql.Date end) {
		return ((AppointmentRepository) repository).getAllByBoatOwnerAndPeriod(boatOwnerId, start, end);
	}


	@Override
	public List<BoatAppointment> getAllPendingByBoatId(int id) {
		return ((AppointmentRepository) repository).getAllPendingByBoatId(id);
	}

	

}
