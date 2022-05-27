package com.isa.FishingBooker.service.interfaces;

import com.isa.FishingBooker.model.DiscountOffer;

import java.util.List;

import com.isa.FishingBooker.dto.*;

public interface DiscountOfferService extends Service<DiscountOffer> {
	public List<DiscountOffer> getAllBoatDiscountOffers();
	public List<DiscountOfferDTO> getAllResortDiscountOffers();
	public List<DiscountOffer> getAllTutorServiceDiscountOffers();
	public String makeTutorServiceAppointmentOfDiscount(DiscountOffer discountOffer);
	public String makeBoatAppointmentOfDiscount(DiscountOffer discountOffer);
	
}
