package com.isa.FishingBooker.model;

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
	private int penaltyCount;

	public User() {
	}

	public void updateUserInfo(User user) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.password = user.getPassword() == null ? this.password : user.getPassword();
		this.name = user.getName();
		this.surname = user.getSurname();
		this.address = user.getAddress();
		this.phoneNumber = user.getPhoneNumber();
		this.status = user.getStatus();
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
		this.status = status;
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
		return null;
	}

	public Set<Integer> getReservationsList() {
		return reservationsList;
	}

	public void setReservationsList(Set<Integer> reservationsList) {
		this.reservationsList = reservationsList;
	}

	public int getPenaltyCount() {
		return penaltyCount;
	}

	public void addPenalty(int count) {
		this.penaltyCount += count;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

}
