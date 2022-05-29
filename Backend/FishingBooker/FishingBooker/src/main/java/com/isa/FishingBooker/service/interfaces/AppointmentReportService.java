package com.isa.FishingBooker.service.interfaces;

import com.isa.FishingBooker.model.Admin;
import com.isa.FishingBooker.model.AppointmentReport;
public interface AppointmentReportService extends Service<AppointmentReport>{
	public void addBadCommentReport(AppointmentReport report, int appointmentId);
	public void addNotShopUpReport(int appointmentId);
	public void addOkCommentReport(int appointmentId,String comment);
	public void acceptBadReport(int reportId,Admin admin);
	public void rejectBadReport(int reportId, String reason, Admin admin);
	public AppointmentReport getByAppointmentId(int id);
}
