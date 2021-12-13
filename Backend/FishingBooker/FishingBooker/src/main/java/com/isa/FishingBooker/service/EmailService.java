package com.isa.FishingBooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.dto.RegistrationDTO;
import com.isa.FishingBooker.mapper.RegistrationDTOMapper;
import com.isa.FishingBooker.model.User;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private Environment env;
	
	
	public void sendRegisterConfirmationMail(User user) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Fishingbooker: Confirm your account");
		mail.setText("Your id is: " +user.getId() + "\nCheck link below and insert given id number to a forma in it to confirm your registration:\n\n http://localhost:4000/confirmation");
		javaMailSender.send(mail);
	}
	
	public void sendRejectedConfirmationMail(User user, String comment) {
		String subject="Registration rejected";
		String text=String.format("Dear %s,\n%s\nBest regards,\n FishingBooker App Team", user.getName(),comment);
		javaMailSender.send(this.createMail(user.getEmail(), subject, text));
	}
	
	public void sendConfirmConfirmationMail(User user) {
		String subject="Registration confirmation";
		String text=String.format("Dear %s,\nOur admin team confirmed your registration.\nBest regards,\n FishingBooker App Team", user.getName());
		javaMailSender.send(this.createMail(user.getEmail(), subject, text));
	}
	
	private SimpleMailMessage createMail(String email,String subject, String text) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject(subject);
		mail.setText(text);
		return mail;
	}
}
