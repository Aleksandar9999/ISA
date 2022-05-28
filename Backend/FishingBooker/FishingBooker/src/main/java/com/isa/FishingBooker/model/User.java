package com.isa.FishingBooker.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.isa.FishingBooker.exceptions.UnexpectedUserStatusExcpetion;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String password;
	private String name;
	private String surname;
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	private String phoneNumber;
	@Enumerated(EnumType.STRING)
	private Status status;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles;
	@ElementCollection(targetClass = Integer.class)
	private Set<Integer> reservationsList;
	private Double penaltyCount;
	private Double points;
	private Date passwordResetTimestamp;
	private String registrationReason;
	public User() {
	}

	public void updateUserInfo(User user) {
		this.name = user.getName();
		this.surname = user.getSurname();
		this.address = user.getAddress();
		this.phoneNumber = user.getPhoneNumber();
		this.updatePassword(user.getPassword());
	}

	private void updatePassword(String newPassword) {
		if(newPassword!=null &&!newPassword.isBlank() && !newPassword.equals(this.password)) {
			this.password=newPassword;
			this.passwordResetTimestamp=Date.from(Instant.now());
		}
	}

	public User addPoints(Double points) {
		if (this.points == null)
			this.points = 0.0;
		this.points += points;
		return this;
	}

	@JsonIgnore
	public List<Role> getRoles() {
		return roles;
	}

	public void setRolesNames() {
		this.setRoleName(Role.USER_ROLE);
	}

	protected void setRoleName(String name) {
		if (this.roles == null)
			this.roles = new ArrayList<Role>();
		this.roles.add(new Role(name));
	}

	public User(int id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		validateNewStatus(status);
		this.status=status;
	}

	private void validateNewStatus(Status status) {
		if (this.status==null) return;
		if (this.status.equals(Status.ADMIN_CONFIRMED) && status.equals(Status.DELETED)) return;
		if (this.status.equals(Status.PENDING) && status.equals(Status.CONFIRMED)) return;
		throw new UnexpectedUserStatusExcpetion();
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@JsonIgnore
	@Override
	public String getUsername() {
		return this.getEmail();
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		return this.status.equals(Status.CONFIRMED);
	}

	@JsonIgnore
	public Date getLastPasswordResetDate() {
		return passwordResetTimestamp;
	}

	public Set<Integer> getReservationsList() {
		return reservationsList;
	}

	public void setReservationsList(Set<Integer> reservationsList) {
		this.reservationsList = reservationsList;
	}

	public Double getPenaltyCount() {
		return penaltyCount != null ? penaltyCount : 0.0;
	}

	public void addPenalty(Double count) {
		this.penaltyCount += count;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	public Double getPoints() {
		return points != null ? points : 0.0;
	}

	public void setPoints(Double points) {
		this.points = points;
	}

	public String getRegistrationReason() {
		return registrationReason;
	}

	public void setRegistrationReason(String registrationReason) {
		this.registrationReason = registrationReason;
	}

}
