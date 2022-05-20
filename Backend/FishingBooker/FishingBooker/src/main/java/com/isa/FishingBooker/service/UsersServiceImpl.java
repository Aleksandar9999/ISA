package com.isa.FishingBooker.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.isa.FishingBooker.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.exceptions.EmailExistException;
import com.isa.FishingBooker.exceptions.EmailNotConfirmedException;
import com.isa.FishingBooker.repository.UserRepository;
import com.isa.FishingBooker.security.auth.TokenBasedAuthentication;
import com.isa.FishingBooker.service.interfaces.RoleService;
import com.isa.FishingBooker.service.interfaces.UsersService;

import javax.transaction.Transactional;

@Service
public class UsersServiceImpl extends CustomGenericService<User> implements UsersService {

	@Autowired
	private RoleService roleService;

	@Autowired
	private EmailService emailService;

	public final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	@Override
	public void update(User item) {
		super.update(item);
	}

	@Override
	public void updateUserInfo(User item){
		User user = this.getById(item.getId());
		user.updateUserInfo(item);
		this.update(user);
	}

	@Override
	public void addNew(User item) {
		validateEmail(item.getEmail());
		setUserRoles(item);
		item.setStatus(Status.PENDING);
		super.addNew(item);
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
	@Transactional
	@Override
	public void delete(Integer id) {
		User user = this.getById(id);
		deleteServices(user);
		user.setStatus(Status.DELETED);
		super.update(user);
	}

	@Transactional
	private void deleteServices(User user) {
		if(user instanceof Tutor){
			((Tutor)user).getServices().forEach(ts->{ts.setStatus(Status.DELETED);});
		}
	}

	@Override
	public User EditUser(User user) {
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
		this.update(admin);
	}

	private void setUserRoles(User item) {
		item.getRoles().forEach(role -> role.setId(roleService.findRoleIdByName(role.getName())));
	}

	private void validateEmail(String email) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
		if (!matcher.find())
			throw new EmailExistException("Email address problem. Please check your email.");

		if (((UserRepository) repository).findByEmail(email) != null)
			throw new EmailExistException("Email already exist");
	}

	@Override
	public void updateProfileStatusByAdmin(User userInfo, int userId,String comment) {
		User user=this.getById(userId);
		if(!user.getStatus().equals(Status.CONFIRMED)) throw new EmailNotConfirmedException();
		this.update(userInfo);
		this.sendNotificationEmail(userInfo.getStatus(), user,comment);
	}
	
	private void sendNotificationEmail(Status status, User user,String comment) {
		if(status.equals(Status.REJECTED)) {
			emailService.sendRejectedConfirmationMail(user, comment);
		}else {
			emailService.sendConfirmConfirmationMail(user);
		}
	}

	@Override
	public BoatOwner getBoatOwnerById(int id) {
		return (BoatOwner) ((UserRepository) repository).findBoatOwnerWithBoats(id);
	}
	
}
