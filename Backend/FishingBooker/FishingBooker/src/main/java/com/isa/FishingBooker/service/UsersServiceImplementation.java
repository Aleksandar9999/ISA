package com.isa.FishingBooker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.isa.FishingBooker.dto.LoginInfoDTO;
import com.isa.FishingBooker.dto.LoginReturnDTO;
import com.isa.FishingBooker.exceptions.EmailExistException;
import com.isa.FishingBooker.mapper.UserToLoginReturnDTOMapper;

import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.repository.UserRepository;

@Service
public class UsersServiceImplementation extends CustomServiceAbstract<User> implements UsersService {

	@Override
	public LoginReturnDTO Login(LoginInfoDTO user) {
		User logTry = ((UserRepository)repository).findByEmail(user.getEmail());
		LoginReturnDTO returnDTO = new LoginReturnDTO();
		UserToLoginReturnDTOMapper mapper = new UserToLoginReturnDTOMapper();
		
		if(logTry==null) {
			return null;
		}
		if(logTry.getStatus()!=Status.CONFIRMED) {
			return null;
		}
		
		if(logTry.getPassword().equals(user.getPass())) {
			return mapper.mapUserToLoginReturnDTO(logTry, returnDTO);
		}	
		return null;
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


	@Override
	public String confirmAccount(Integer id) {
		// TODO Auto-generated method stub
		if(repository.getById(id)!=null) {
			User u = repository.getById(id);
			u.setStatus(Status.CONFIRMED);
			repository.save(u);
			return "Success";
		}
		return "Bad id";
	}
}
