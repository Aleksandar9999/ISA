package com.isa.FishingBooker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.ResortAppointment;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.model.TutorServiceAppointment;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.repository.AppointmentRepository;
import com.isa.FishingBooker.repository.TutorServiceRepository;
import com.isa.FishingBooker.security.auth.TokenBasedAuthentication;

@Service
public class AppointmentServiceImplementation extends CustomServiceAbstract<Appointment> implements AppointmentService {
	
	@Autowired
	private TutorServiceRepository tutorServiceRepository;
	@Autowired
	private EmailService emailService;
	@Autowired
	private UsersService userService;
	
	public List<ResortAppointment> getResortApointments(){
		List<ResortAppointment> list = ((AppointmentRepository)repository).getAllResortAppoints();
		List<ResortAppointment> returnList = new ArrayList<ResortAppointment>();
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
		User usr = (User) aut.getPrincipal();
		
		if(list!=null) {
		for(ResortAppointment ra : list) {
			if(ra.getUser()==null && checkReservationList(ra.getId(), usr)) {
				returnList.add(ra);		
				}			
			}
		}
		return returnList;
	}
	
	public List<BoatAppointment> getBoatApointments(){
		List<BoatAppointment> list = ((AppointmentRepository)repository).getAllBoatAppoints();
		List<BoatAppointment> returnList = new ArrayList<BoatAppointment>();
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
		User usr = (User) aut.getPrincipal();
		
		if(list!=null) {
		for(BoatAppointment ba : list) {
			if(ba.getUser()==null && checkReservationList(ba.getId(), usr)) {
				returnList.add(ba);		
				}			
			}
		}
		return returnList;
	}
	
	public List<TutorServiceAppointment> getTutorServiceApointments(){
		List<TutorServiceAppointment> list = ((AppointmentRepository)repository).getAllTutorServiceAppointments();
		List<TutorServiceAppointment> returnList = new ArrayList<TutorServiceAppointment>();
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
		User usr = (User) aut.getPrincipal();
		
		if(list!=null) {
		for(TutorServiceAppointment tsa : list) {
			if(tsa.getUser()==null && checkReservationList(tsa.getId(), usr)) {
				returnList.add(tsa);		
				}			
			}
		}
		return returnList;
	}
	
	public boolean checkReservationList(Integer id, User u) {
		if(u.getReservationsList()!=null) {
			for(Integer oldAppointIds : u.getReservationsList()) {
				if(id==oldAppointIds) {
					return false;
				}
			}
			}
		return true;
	}
	
	@Override
	public List<TutorServiceAppointment> getAllTutorServiceAppointmentsByTutor(int id) {
		return ((AppointmentRepository)repository).getAllAppointmentsByTutor(id);
	}

	@Override
	public void addNewTutorServiceAppointment(TutorServiceAppointment app) {
		TutorService tutorService=tutorServiceRepository.getById(app.getTutorService().getId());
		app.setPrice(tutorService.calculatePrice((int) app.getDuration()));
		super.addNew(app);
	}

	@Override
	public List<Appointment> getPendingApointments() {
		List<Appointment> appointments = new ArrayList<Appointment>();		
		Date thisMoment = new Date(System.currentTimeMillis());
		
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
		User usr = (User) aut.getPrincipal();
		String email= usr.getEmail();
		for(BoatAppointment a : ((AppointmentRepository)repository).getAllBoatAppoints()) {
			if(a.getUser().getEmail().equals(email)) {
				if(a.getStart().compareTo(thisMoment)>0) {					
					appointments.add(a);
				}
			}
		}
		for(ResortAppointment a : ((AppointmentRepository)repository).getAllResortAppoints()) {
			if(a.getUser().getEmail().equals(email)) {
				if(a.getStart().compareTo(thisMoment)>0) {					
					appointments.add(a);
				}
			}
		}
		for(TutorServiceAppointment a : ((AppointmentRepository)repository).getAllTutorServiceAppointments()) {
			if(a.getUser().getEmail().equals(email)) {
				if(a.getStart().compareTo(thisMoment)>0) {					
					appointments.add(a);
				}
			}
		}
		return appointments;
	}

	@Override
	public void deleteResortAppointments(int resortId) {
		((AppointmentRepository)repository).deleteResortAppointments(resortId);
		
	}

	@Override
	public String makeBoatReservation(BoatAppointment appointment) {
		// TODO Auto-generated method stub
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
		User usr = (User) aut.getPrincipal();
		appointment.setUser(usr);
		((AppointmentRepository)repository).save(appointment);
		emailService.sendReservationMail(usr);
		return null;
	}

	@Override
	public String makeResortReservation(ResortAppointment appointment) {
		// TODO Auto-generated method stub
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
		User usr = (User) aut.getPrincipal();
		appointment.setUser(usr);
		((AppointmentRepository)repository).save(appointment);
		emailService.sendReservationMail(usr);
		return null;
	}

	@Override
	public String makeTutorServiceReservation(TutorServiceAppointment appointment) {
		// TODO Auto-generated method stub
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext().getAuthentication();
		User usr = (User) aut.getPrincipal();
		appointment.setUser(usr);
		((AppointmentRepository)repository).save(appointment);
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
		thisMoment=cal.getTime();
		
		for(BoatAppointment a : ((AppointmentRepository)repository).getAllBoatAppoints()) {
			if(a.getId()==id) {
				if(a.getStart().compareTo(thisMoment)>0) {
					a.setUser(null);
					User u = a.getUser();
					u.getReservationsList().add(id);
					userService.update(u);
				} else return "You can not cancel reservation in last 3 days.";				
			}
		}
		for(ResortAppointment a : ((AppointmentRepository)repository).getAllResortAppoints()) {
			if(a.getId()==id) {
				if(a.getStart().compareTo(thisMoment)>0) {
					a.setUser(null);
					User u = a.getUser();
					u.getReservationsList().add(id);
					userService.update(u);
				} else return "You can not cancel reservation in last 3 days.";
			}				
		}
		for(TutorServiceAppointment a : ((AppointmentRepository)repository).getAllTutorServiceAppointments()) {
			if(a.getId()==id) {
				if(a.getStart().compareTo(thisMoment)>0) {
					a.setUser(null);
					User u = a.getUser();
					u.getReservationsList().add(id);
					userService.update(u);
				} else return "You can not cancel reservation in last 3 days.";
			}
		}		
		return "Reservation canceled.";
	}

}
