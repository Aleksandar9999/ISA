package com.isa.FishingBooker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.AppointmentReport;
import com.isa.FishingBooker.model.ReportType;
import com.isa.FishingBooker.model.Status;
import com.isa.FishingBooker.model.User;

@Service
public class AppointmentReportServiceImpl extends CustomServiceAbstract<AppointmentReport>
		implements AppointmentReportService {

	@Autowired
	private EmailService emailService;
	@Autowired
	private UsersService userService;
	
	@Autowired
	private AppointmentService appointmentService;

	private final int PENALTY_COUNT = 1;

	@Override
	public void addBadCommentReport(AppointmentReport report) {
		report.setStatusAndTypeOfReport(Status.PENDING, ReportType.BAD);
		super.addNew(report);
	}

	@Override
	public void addNotShopUpReport(AppointmentReport report) {
		report.setStatusAndTypeOfReport(Status.CONFIRMED, ReportType.CLIENT_NOT_SHOW_UP);
		updateClientPenaltyCountAndNotify(report);
		super.addNew(report);
	}

	@Override
	public void addOkCommentReport(AppointmentReport report) {
		report.setStatusAndTypeOfReport(Status.CONFIRMED, ReportType.GOOD);
		super.addNew(report);
	}

	@Override
	public void acceptBadReport(int reportId) {
		AppointmentReport report = updateReportStatus(reportId, Status.ADMIN_CONFIRMED);
		updateClientPenaltyCountAndNotify(report);
		emailService.sendAppointmentReportAcceptedNotification(report.getAppointment().getOwner());
	}

	private void updateClientPenaltyCountAndNotify(AppointmentReport report) {
		// TRANSFER TO CLIENT CLASS
		Appointment appointment=appointmentService.getById(report.getAppointment().getId());
		User client = appointment.getUser();
		updateClientPenaltyCount(client);
		emailService.sendPenaltyUpdateNotification(client, report.getComment());
	}

	private void updateClientPenaltyCount(User client) {
		client.addPenalty(PENALTY_COUNT);
		userService.update(client);
	}
	
	@Override
	public void rejectBadReport(int reportId, String reason) {
		AppointmentReport report = updateReportStatus(reportId, Status.REJECTED);
		User owner = report.getAppointment().getOwner();
		emailService.sendAppointmentReportRejectedNotification(owner, reason);
	}
	
	private AppointmentReport updateReportStatus(int reportId, Status status) {
		AppointmentReport report = super.getById(reportId);
		report.setStatus(status);
		this.update(report);
		return report;
	}
}
