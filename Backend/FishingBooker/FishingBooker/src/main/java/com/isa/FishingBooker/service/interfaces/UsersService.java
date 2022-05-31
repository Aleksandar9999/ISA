package com.isa.FishingBooker.service.interfaces;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.isa.FishingBooker.model.BoatOwner;
import com.isa.FishingBooker.model.ResortOwner;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.Tutor;
import com.isa.FishingBooker.model.User;

public interface UsersService extends Service<User>, UserDetailsService {
	public void updateProfileStatusByAdmin(User userInfo, int userId,String comment);
	public List<User> getAllClients();
	public Tutor getTutorById(int id);
	public BoatOwner getBoatOwnerById(int id);
	public ResortOwner getResortOwnerById(int id);
	public List<User> search(Status status);
	public String confirmAccount(Integer id);
	public User EditUser(User user);
	public User getUserProfileData();
	public void resetAdminPassword(User user);
	public void updateUserInfo(User item);
}
