package com.isa.FishingBooker.service;

import com.isa.FishingBooker.model.AppointmentReport;
public interface AppointmentReportService extends Service<AppointmentReport>{
	public void addBadCommentReport(AppointmentReport report);
	public void addNotShopUpReport(AppointmentReport report);
	public void addOkCommentReport(AppointmentReport report);
	public void acceptBadReport(int reportId);
	public void rejectBadReport(int reportId, String reason);
	public AppointmentReport getByAppointmentId(int id);
}
