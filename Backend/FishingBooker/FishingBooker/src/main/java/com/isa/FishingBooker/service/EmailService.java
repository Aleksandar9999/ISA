package com.isa.FishingBooker.service;

import com.isa.FishingBooker.model.Objection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.TutorService;
import com.isa.FishingBooker.model.User;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private Environment env;

	@Async
	public void sendDeleteRequestResponseNotification(User user, Status status, String additionalResponse) {
		String accoutStatus = "";
		if (status.equals(Status.REJECTED))
			accoutStatus = "reject";
		else
			accoutStatus = "confirmed";
		String mailContent = String.format("Dear %s,\nOur admin team %s your delete request.", user.getName(),
				accoutStatus);
		if (additionalResponse!=null)
			mailContent += "\nAdmin response: " + additionalResponse;
		this.sendCustomEmail(user.getEmail(), "Delete request review", mailContent);
	}
@Async
	public void sendObjectionResponseNotification(String email, String objection, String response) {
		String mailContent = String.format("Objection: %s\nAdmin response: %s", objection, response);
		this.sendCustomEmail(email, "Objection response", mailContent);
	}

	@Async
	public void sendPenaltyUpdateNotification(User client, String reason) {
		String mailContent = String.format("Dear %s,\nYou got new penalty.\nNew number of penalties: %s.\n\nReason:%s",
				client.getName(), client.getPenaltyCount(), reason);
		this.sendCustomEmail(client.getEmail(), "Penalty count update", mailContent);
	}

	@Async
	public void sendAppointmentReportAcceptedNotification(User user) {
		String mailContent = String.format(
				"Dear %s,\nWe accepted your report. User penalty count updated.\nBest regards,\nFishingBooker App Team",
				user.getName());
		this.sendCustomEmail(user.getEmail(), "Penalty count update", mailContent);
	}

	@Async
	public void sendAppointmentReportRejectedNotification(User user, String reason) {
		String mailContent = String.format(
				"Dear %s,\nWe rejected your report.\n\nReason:%s\nBest regards,\nFishingBooker App Team",
				user.getName(),reason);
		this.sendCustomEmail(user.getEmail(), "Appointment report rejected", mailContent);
	}

	@Async
	public void sendRegisterConfirmationMail(User user) {
		String text = "Your id is: " + user.getId()
				+ "\nCheck link below and insert given id number to a forma in it to confirm your registration:\n\n http://localhost:4000/confirmation";
		SimpleMailMessage mail = createMail(user.getEmail(), "Fishingbooker: Confirm your account", text);
		this.sendEmail(mail);
	}

	@Async
	public void sendDiscountNotificationEmail(User user, TutorService service) {
		String subject = "NEW DISCOUNT OFFER";
		String text = String.format("Dear %s,\nNew discount offer has been created for %s.", user.getName(),
				service.getName());
		this.sendEmail(this.createMail(user.getEmail(), subject, text));
	}

	@Async
	public void sendRejectedConfirmationMail(User user, String comment) {
		String subject = "Registration rejected";
		String text = String.format("Dear %s,\n%s\nBest regards,\nFishingBooker App Team", user.getName(), comment);
		this.sendEmail(this.createMail(user.getEmail(), subject, text));
	}

	@Async
	public void sendConfirmConfirmationMail(User user) {
		String subject = "Registration confirmation";
		String text = String.format(
				"Dear %s,\nOur admin team confirmed your registration.\nBest regards,\nFishingBooker App Team",
				user.getName());
		this.sendEmail(this.createMail(user.getEmail(), subject, text));
	}

	private SimpleMailMessage createMail(String email, String subject, String text) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email);
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject(subject);
		mail.setText(text);
		return mail;
	}

	@Async
	private void sendEmail(SimpleMailMessage massage) {
		javaMailSender.send(massage);
	}

	@Async
	public void sendCustomEmail(String email, String subject, String text) {
		this.sendEmail(this.createMail(email, subject, text));
	}

	@Async
	public void sendReservationMail(User user) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(env.getProperty("spring.mail.username"));
		mail.setSubject("Fishingbooker: You maked an reservation");
		mail.setText("Thank you for making reservation of one of our services. \nBest wishes, your Fishingbooker.");
		javaMailSender.send(mail);
	}
}
