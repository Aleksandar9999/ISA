package com.isa.FishingBooker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isa.FishingBooker.dto.DiscountOfferDTO;
import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.AppointmentType;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.DiscountOffer;
import com.isa.FishingBooker.model.ResortAppointment;
import com.isa.FishingBooker.model.TutorServiceAppointment;
import com.isa.FishingBooker.repository.AppointmentRepository;
import com.isa.FishingBooker.repository.DiscountOfferRepository;

public class DiscountOfferServiceImplementation implements DiscountOfferService{
	
	@Autowired
	private DiscountOfferRepository repository;
	@Autowired
	private AppointmentService appointmentService;
	
	@Override
	public void addNew(DiscountOffer item) {
		// TODO Auto-generated method stub
		repository.save(item);
	}

	@Override
	public List<DiscountOffer> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public DiscountOffer getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(DiscountOffer item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DiscountOfferDTO> getAllBoatDiscountOffers() {
		// TODO Auto-generated method stub
		List<DiscountOfferDTO> retList = new ArrayList<DiscountOfferDTO>();
		for(DiscountOffer d : repository.findAll()) {
			for(BoatAppointment a : appointmentService.getAllBoatAppoints()) {
				if(a.getBoat().getId()==d.getBoat().getId()) {
					DiscountOfferDTO dto = new DiscountOfferDTO();
					dto.setAddress(a.getAddress());
					dto.setAppointmentId(a.getId());
					dto.setNewPrice(d.getPrice());
					dto.setPrice(a.getPrice());
					dto.setStart(a.getStart());
					double discount = 1 - d.getPrice()/a.getPrice();
					dto.setDiscount(discount);
					retList.add(dto);
				}
			}
		}
		
		return retList;
	}

	@Override
	public List<DiscountOfferDTO> getAllResortDiscountOffers() {
		// TODO Auto-generated method stub
		List<DiscountOfferDTO> retList = new ArrayList<DiscountOfferDTO>();
		for(DiscountOffer d : repository.findAll()) {
			for(ResortAppointment a : appointmentService.getAllResortAppoints()) {
				if(a.getResort().getId()==d.getResort().getId()) {
					DiscountOfferDTO dto = new DiscountOfferDTO();
					dto.setAddress(a.getAddress());
					dto.setAppointmentId(a.getId());
					dto.setNewPrice(d.getPrice());
					dto.setPrice(a.getPrice());
					dto.setStart(a.getStart());
					double discount = 1 - d.getPrice()/a.getPrice();
					dto.setDiscount(discount);
					retList.add(dto);
				}
			}
		}
		
		return retList;
	}

	@Override
	public List<DiscountOfferDTO> getAllTutorServiceDiscountOffers() {
		// TODO Auto-generated method stub
		List<DiscountOfferDTO> retList = new ArrayList<DiscountOfferDTO>();
		for(DiscountOffer d : repository.findAll()) {
			for(TutorServiceAppointment a : appointmentService.getAllTutorServiceAppoints()) {
				if(a.getTutorService().getId()==d.getTutorService().getId()) {
					DiscountOfferDTO dto = new DiscountOfferDTO();
					dto.setAddress(a.getAddress());
					dto.setAppointmentId(a.getId());
					dto.setNewPrice(d.getPrice());
					dto.setPrice(a.getPrice());
					dto.setStart(a.getStart());
					double discount = 1 - d.getPrice()/a.getPrice();
					dto.setDiscount(discount);
					retList.add(dto);
				}
			}
		}
		
		return retList;
	}


}
