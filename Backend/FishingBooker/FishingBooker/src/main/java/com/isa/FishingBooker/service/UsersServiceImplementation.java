package com.isa.FishingBooker.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.dto.LoginInfoDTO;
import com.isa.FishingBooker.dto.LoginReturnDTO;
import com.isa.FishingBooker.dto.RegistrationDTO;
import com.isa.FishingBooker.mapper.RegistrationDTOtoUserMapper;
import com.isa.FishingBooker.mapper.UserToLoginReturnDTOMapper;
import com.isa.FishingBooker.model.Status;
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

	@Override
	public void addNew(User item) {
		item.setStatus(Status.PENDING);
		//dao.addNew(item);
	}

	@Override
	public LoginReturnDTO Login(LoginInfoDTO user) {

		User logTry = repository.findByEmail(user.getEmail());
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
	public String Register(RegistrationDTO userDTO) {
		// TODO Auto-generated method stub
		RegistrationDTOtoUserMapper mapper = new RegistrationDTOtoUserMapper();

		User user = new User();
		user.setStatus(Status.PENDING);
		if(repository.findByEmail(userDTO.getEmail())==null) {
			user=mapper.RegistrationDTOtoUser(userDTO, user);
			repository.save(user);
			return "Successfull registration!";
		}
		return "User with this email already exists.";
		
	}

}
