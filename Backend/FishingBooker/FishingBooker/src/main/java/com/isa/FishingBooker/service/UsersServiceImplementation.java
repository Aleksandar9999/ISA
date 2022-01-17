package com.isa.FishingBooker.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.dto.LoginInfoDTO;
import com.isa.FishingBooker.dto.LoginReturnDTO;
import com.isa.FishingBooker.dto.RegistrationDTO;
import com.isa.FishingBooker.exceptions.EmailExistException;
import com.isa.FishingBooker.mapper.UserToLoginReturnDTOMapper;
import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.Role;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.User;
import com.isa.FishingBooker.repository.UserRepository;
import com.isa.FishingBooker.security.auth.TokenBasedAuthentication;

@Service
public class UsersServiceImplementation extends CustomServiceAbstract<User> implements UsersService {

	@Autowired
	private RoleService roleService;
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	@Override
	public void update(User item) {
		User user = this.getById(item.getId());
		user.updateUserInfo(item);
		super.update(user);
	}

	@Override
	public void addNew(User item) {
		validateEmail(item.getEmail());
		setUserRoles(item);
		item.setStatus(Status.PENDING);
		super.addNew(item);
	}

	private void setUserRoles(User item) {
		item.getRoles().forEach(role -> role.setId(roleService.findRoleIdByName(role.getName())));
	}

	private void validateEmail(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		if(!matcher.find()) throw new EmailExistException("Email address problem. Please check your email.");
	
		if (((UserRepository) repository).findByEmail(email) != null)
			throw new EmailExistException("Email already exist");
	}

	@Override
	public Tutor getTutorById(int id) {
		return (Tutor) ((UserRepository) repository).findTutorWithServices(id);
	}

	@Override
	public List<User> search(Status status) {
		return ((UserRepository) repository).search(status);
	}

	@Override
	public String confirmAccount(Integer id) {
		if (repository.getById(id) != null) {
			User u = repository.getById(id);
			u.setStatus(Status.CONFIRMED);
			repository.save(u);
			return "Success";
		}
		return "Bad id";
	}

	@Override
	public List<User> getAllClients() {
		return ((UserRepository) repository).findAllClients();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = ((UserRepository) repository).findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
		} else {
			return user;
		}
	}

	@Override
	public void delete(Integer id) {
		User user = this.getById(id);
		user.setStatus(Status.DELETED);
		super.update(user);
		;
	}

	@Override
	public User EditUser(User user) {
		// TODO Auto-generated method stub
		return repository.save(user);
	}

	@Override
	public User getUserProfileData() {
		TokenBasedAuthentication aut = (TokenBasedAuthentication) SecurityContextHolder.getContext()
				.getAuthentication();
		User usr = (User) aut.getPrincipal();
		return this.getById(usr.getId());
	}

	@Override
	public void resetAdminPassword(User user) {
		Admin admin = (Admin) this.getById(user.getId());
		admin.resetPassword(user.getPassword());
		repository.save(admin);
	}

}
