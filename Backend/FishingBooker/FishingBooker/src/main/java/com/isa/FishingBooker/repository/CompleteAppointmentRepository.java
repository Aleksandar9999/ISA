package com.isa.FishingBooker.repository;

import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.TutorServiceAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.CompletedAppointment;

import java.sql.Date;
import java.util.List;

@Repository
public interface CompleteAppointmentRepository extends JpaRepository<CompletedAppointment, Integer>{

    @Query(value="select * from completed_appointment ca "
            + "INNER JOIN appointment app on ca.appointment_id=app.id "
            + "INNER JOIN tutor_service ts on app.tutor_service_id=ts.tutor_service_id "
            + "INNER JOIN period pe on app.period_id=pe.id "
            + "WHERE app.dtype='TutorServiceAppointment' and ts.tutor_id=?1 and "
            + "(pe.start_date, pe.end_date) OVERLAPS (CAST(?2 as date), CAST(?3 as date))",nativeQuery = true)
    public List<CompletedAppointment> getAllInPeriodByTutorId(int tutorId, Date start, Date end);
    
    @Query(value="select * from completed_appointment ca "
            + "INNER JOIN appointment app on ca.appointment_id=app.id "
            + "INNER JOIN boat b on app.boat_id=b.boat_id "
            + "INNER JOIN period pe on app.period_id=pe.id "
            + "WHERE app.dtype='BoatAppointment' and b.boat_owner_id=?1 and "
            + "(pe.start_date, pe.end_date) OVERLAPS (CAST(?2 as date), CAST(?3 as date))",nativeQuery = true)
    public List<CompletedAppointment> getAllInPeriodByBoatOwnerId(int tutorId, Date start, Date end);



    @Query(value="select * from completed_appointment ca "
            + "INNER JOIN appointment app on ca.appointment_id=app.id "
            + "INNER JOIN period pe on app.period_id=pe.id "
            + "where (pe.start_date, pe.end_date) OVERLAPS (CAST(?1 as date), CAST(?2 as date))",nativeQuery = true)
    public List<CompletedAppointment> getAllInPeriod(Date start, Date end);

}
