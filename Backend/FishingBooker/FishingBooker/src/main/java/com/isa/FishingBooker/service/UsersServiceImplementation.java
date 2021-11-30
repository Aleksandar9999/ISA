package com.isa.FishingBooker.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.dto.LoginInfoDTO;
import com.isa.FishingBooker.dto.RegistrationDTO;
import com.isa.FishingBooker.exceptions.EmailExistException;
import com.isa.FishingBooker.mapper.RegistrationDTOtoUserMapper;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.repository.UserRepository;

@Service
public class UsersServiceImplementation implements UsersService {

	@Autowired
	private UserRepository repository;

	@Override
	@Transactional
	public List<User> getAll() {
		return repository.findAll();
	}

	@Override
	public User getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(User item) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
	}

	// TODO: Refaktorisati jer se ova metoda ne koristi
	@Override
	public void addNew(User item) {
		item.setStatus(Status.PENDING);
		repository.save(item);
	}

	@Override
	public String Login(LoginInfoDTO user) {
		User logTry = repository.findByEmail(user.getEmail());
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
	public User Register(RegistrationDTO userDTO) {
		RegistrationDTOtoUserMapper mapper = new RegistrationDTOtoUserMapper();
		validateEmail(userDTO.getEmail());
		User user = mapper.RegistrationDTOtoUser(userDTO);
		user.setStatus(Status.PENDING);
		return repository.save(user);
	}

	private void validateEmail(String email) {
		if (repository.findByEmail(email) != null)
			throw new EmailExistException();
	}
	
	@Override
	public Tutor getTutorById(int id) {
		return (Tutor) repository.findTutorWithServices(id);
	}
}
