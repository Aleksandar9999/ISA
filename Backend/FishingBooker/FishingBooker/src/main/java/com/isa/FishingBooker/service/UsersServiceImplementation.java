package com.isa.FishingBooker.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.dto.LoginInfoDTO;
import com.isa.FishingBooker.dto.RegistrationDTO;
import com.isa.FishingBooker.exceptions.EmailExistException;
import com.isa.FishingBooker.mapper.RegistrationDTOMapper;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.repository.UserRepository;


@Service
public class UsersServiceImplementation extends CustomServiceAbstract<User> implements UsersService {

	@Override
	public String Login(LoginInfoDTO user) {
		User logTry = ((UserRepository)repository).findByEmail(user.getEmail());
		if (logTry == null) {
			return "Bad email or user do not exist.";
		}
		if (logTry.getStatus() != Status.CONFIRMED) {
			return "You must confirm you status first.";
		}

		if (logTry.getPassword().equals(user.getPass())) {
			return "Succesfully loged in.";
		}
		return "Bad password.";
	}


	@Override
	public void addNew(User item) {
		validateEmail(item.getEmail());
		item.setStatus(Status.PENDING);
		super.addNew(item);
	}


	private void validateEmail(String email) {
		if (((UserRepository)repository).findByEmail(email) != null)
			throw new EmailExistException();
	}
	
	@Override
	public Tutor getTutorById(int id) {
		return (Tutor) ((UserRepository)repository).findTutorWithServices(id);
	}


	@Override
	public List<User> search(Status status) {
		return ((UserRepository)repository).search(status);
	}

}
