package com.isa.FishingBooker.repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.isa.FishingBooker.model.Appointment;
import com.isa.FishingBooker.model.BoatAppointment;
import com.isa.FishingBooker.model.ResortAppointment;
import com.isa.FishingBooker.model.TutorServiceAppointment;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment ,Integer> {
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.id=?1")
	public TutorServiceAppointment getResortAppointmentById(Integer id);
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.id=?1")
	public ResortAppointment getTutorServiceAppointmentById(Integer id);
	
	@Query("select a from Appointment a where TYPE(a)=BoatAppointment and a.id=?1")
	public BoatAppointment getBoatAppointmentById(Integer id);
	
	//Resorts
	
	@Query("select a from Appointment a where TYPE(a)=ResortAppointment")
	public List<ResortAppointment> getAllResortAppoints();
	
	@Query("select a from Appointment a where TYPE(a)=ResortAppointment and a.resort.id=?1")
	public List<ResortAppointment> getAllAppointmentsByResort(int resortId);
	
	@Query("select a from Appointment a where TYPE(a)=ResortAppointment and a.resort.resortowner.id=?1 and a.period.startDate > CURRENT_DATE")
	public List<ResortAppointment> getAllInCommingAppointmentsByResortOwner(int resortownerId);
	
	@Query("select a from Appointment a where TYPE(a)=ResortAppointment and a.user.id=?1")
	public List<ResortAppointment> getAllResortAppointmentsByUser(int userid);
	
	@Query("select a from Appointment a where TYPE(a)=ResortAppointment and a.user.id=?1 and a.resort.resortowner.id=?2 and a.period.startDate < CURRENT_DATE")
	public List<ResortAppointment> getAllByResortOwnerAndUserBeforeCurrentDate(int userid, int resortownerId);
	
	@Query("select a from Appointment a where TYPE(a)=ResortAppointment and a.resort.id=?1 and a.status='PENDING'")
	public List<ResortAppointment> getAllPendingByResortId(int id);
	
	@Query(value="select dtype, a.id, a.period_id, ps.end_date as endDate,a.status, appoint_type, additional_services,a.max_person, price, ps.start_date, a.address_id, user_id, tutor_service_id, boat_id, r.resort_owner_id,r.resort_id, "
			+ "    r.name from Appointment a INNER JOIN resort as r on a.resort_id=r.resort_id "
			+ "INNER JOIN Period ps on ps.id=a.period_id "
			+ "where a.dtype='ResortAppointment' and r.resort_id=?1 and "
			+ "(ps.start_date, ps.end_date) OVERLAPS (CAST(?2 as date), CAST(?3 as date))",nativeQuery = true)
	public List<ResortAppointment> getAllByResortAndPeriod(int resortId, Date start,Date end);
	
	@Query("select a from Appointment a where TYPE(a)=ResortAppointment and a.resort.resortowner.id=?1")
	public List<ResortAppointment> getAllResortAppointmentsByResortOwner(int resortOwnerId);
	
	@Query(value="select dtype, a.id, a.period_id, ps.end_date as endDate,a.status, appoint_type, additional_services,a.max_person price, ps.start_date, a.address_id, user_id, tutor_service_id, boat_id, r.resort_owner_id,r.resort_id,  "
			+ "    r.name from Appointment a INNER JOIN  resort as r on a.resort_id=r.resort_id"
			+ "INNER JOIN Period ps on ps.id=a.period_id "
			+ "where a.dtype='ResortAppointment' and r.resort_owner_id=?1 and "
			+ "(ps.start_date, ps.end_date) OVERLAPS (CAST(?2 as date), CAST(?3 as date))",nativeQuery = true)
	public List<ResortAppointment> getAllByResortOwnerAndPeriod(int resortOwnerId, Date start,Date end);
	
	@Query(value="select count(a) "
			+ "from   Appointment a INNER JOIN resort as b on a.resort_id=b.resort_id "
			+ "INNER JOIN Period as ps on ps.id=a.period_id "
			+ "where  b.resort_owner_id=:id and b.resort_id=:resortid and "
			+ "((ps.start_date >= :date and (ps.end_date <= NOW())) OR (ps.start_date >= :date and ps.start_date <= NOW() and (ps.end_date >= NOW()))  ) ",nativeQuery = true)
	public Integer getNumberOfAppointmentsByResortOwner(@Param("id")int id,@Param("date") LocalDateTime date,@Param("resortid")int resortid);
	
	
	
	
	//BOATS
	@Query("select a from Appointment a where TYPE(a)=BoatAppointment")
	public List<BoatAppointment> getAllBoatAppoints();
	
	@Query("select a from Appointment a where TYPE(a)=BoatAppointment and a.boat.id=?1")
	public List<BoatAppointment> getAllAppointmentsByBoat(int boatId);
	
	@Query("select a from Appointment a where TYPE(a)=BoatAppointment and a.boat.boatowner.id=?1 and a.period.startDate > CURRENT_DATE")
	public List<BoatAppointment> getAllInCommingAppointmentsByBoatOwner(int boatownerId);
	
	@Query("select a from Appointment a where TYPE(a)=BoatAppointment and a.user.id=?1")
	public List<BoatAppointment> getAllBoatAppointmentsByUser(int userid);
	
	@Query("select a from Appointment a where TYPE(a)=BoatAppointment and a.user.id=?1 and a.boat.boatowner.id=?2 and a.period.startDate < CURRENT_DATE")
	public List<BoatAppointment> getAllByBoatOwnerAndUserBeforeCurrentDate(int userid, int boatownerId);
	
	@Query("select a from Appointment a where TYPE(a)=BoatAppointment and a.boat.id=?1 and a.status='PENDING'")
	public List<BoatAppointment> getAllPendingByBoatId(int id);
	
	@Query(value="select dtype, a.id, a.period_id, ps.end_date as endDate,a.status, appoint_type, additional_services, b.max_person, price, ps.start_date, a.address_id, user_id, tutor_service_id, resort_id, b.boat_owner_id,b.boat_id, "
			+ "    b.name from Appointment a INNER JOIN boat as b on a.boat_id=b.boat_id "
			+ "INNER JOIN Period ps on ps.id=a.period_id "
			+ "where a.dtype='BoatAppointment' and b.boat_id=?1 and "
			+ "(ps.start_date, ps.end_date) OVERLAPS (CAST(?2 as date), CAST(?3 as date))",nativeQuery = true)
	public List<BoatAppointment> getAllByBoatAndPeriod(int boatId, Date start,Date end);
	
	@Query("select a from Appointment a where TYPE(a)=BoatAppointment and a.boat.boatowner.id=?1")
	public List<BoatAppointment> getAllBoatAppointmentsByBoatOwner(int boatOwnerId);

	@Query(value="select count(a) "
			+ "from   Appointment a INNER JOIN boat as b on a.boat_id=b.boat_id "
			+ "INNER JOIN Period as ps on ps.id=a.period_id "
			+ "where  b.boat_owner_id=:id and b.boat_id=:boatid and "
			+ "((ps.start_date >= :date and (ps.end_date <= NOW())) OR (ps.start_date >= :date and ps.start_date <= NOW() and (ps.end_date >= NOW()))  ) ",nativeQuery = true)
	public Integer getNumberOfAppointmentsByBoatOwner(@Param("id")int id,@Param("date") LocalDateTime date,@Param("boatid")int boatid);

//	
//	@Query(value="select count(a) "
//			+ "from   Appointment a INNER JOIN boat as b on a.boat_id=b.boat_id "
//			+ "INNER JOIN Period ps on ps.id=a.period_id "
//			+ "where  b.boat_owner_id=?1 and "
//			+ "(ps.start_date, ps.end_date) OVERLAPS (CAST(?2 as timestamp), CAST(?3 as timestamp))",nativeQuery = true)
//	public Integer getNumberOfAppointmentsByBoatOwner(int boatOwnerId, LocalDateTime start,LocalDateTime end);
	
	@Query(value="select dtype, a.id, a.period_id, ps.end_date as endDate,a.status, appoint_type, additional_services, b.max_person, price, ps.start_date, a.address_id, user_id, tutor_service_id, resort_id, b.boat_owner_id,b.boat_id,  "
			+ "    b.name from Appointment a INNER JOIN boat as b on a.boat_id=b.boat_id "
			+ "INNER JOIN Period ps on ps.id=a.period_id "
			+ "where a.dtype='BoatAppointment' and b.boat_owner_id=?1 and "
			+ "(ps.start_date, ps.end_date) OVERLAPS (CAST(?2 as date), CAST(?3 as date))",nativeQuery = true)
	public List<BoatAppointment> getAllByBoatOwnerAndPeriod(int boatOwnerId, Date start,Date end);
	
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment")
	public List<TutorServiceAppointment> getAllTutorServiceAppointments();
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.tutorService.tutor.id=?1")
	public List<TutorServiceAppointment> getAllAppointmentsByTutor(int tutorId);
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.tutorService.tutor.id=?1 and a.period.startDate > CURRENT_DATE")
	public List<TutorServiceAppointment> getAllInCommingAppointmentsByTutor(int tutorId);
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.user.id=?1")
	public List<TutorServiceAppointment> getAllTutorServiceAppointmentsByUser(int userid);
	
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.user.id=?1 and a.tutorService.tutor.id=?2 and a.period.startDate < CURRENT_DATE")
	public List<TutorServiceAppointment> getAllByTutorAndUserBeforeCurrentDate(int userid, int tutorid);
	
	
	@Query(value="select dtype, a.id, a.period_id, ps.end_date as endDate,a.status, appoint_type, additional_services, ts.max_person, price, ps.start_date, ts.address_id, user_id, boat_id, resort_id, ts.tutor_id,ts.tutor_service_id, "
			+ "    ts.name from Appointment a INNER JOIN tutor_service as ts on a.tutor_service_id=ts.tutor_service_id "
			+ "INNER JOIN Period ps on ps.id=a.period_id "
			+ "where a.dtype='TutorServiceAppointment' and ts.tutor_id=?1 and "
			+ "(ps.start_date, ps.end_date) OVERLAPS (CAST(?2 as date), CAST(?3 as date))",nativeQuery = true)
	public List<TutorServiceAppointment> getAllByTutorAndPeriod(int tutorId, Date start,Date end);

	@Query(value="select * from appointment app "
			+ "INNER JOIN period pe on app.period_id=pe.id  "
			+ "where (pe.start_date, pe.end_date) OVERLAPS (CAST(?1 as date), CAST(?2 as date))",nativeQuery = true)
	public List<Appointment> getAllInPeriod(Date start,Date end);
	@Query("select a from Appointment a where TYPE(a)=TutorServiceAppointment and a.tutorService.id=?1 and a.status='PENDING'")
	public List<TutorServiceAppointment> getAllPendingByTutorServiceId(int id);
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM Appointment a WHERE a.resort_id=?1",nativeQuery = true)
	public void deleteResortAppointments(int resortId);
	
	@Lock(LockModeType.PESSIMISTIC_READ)
	public Appointment save(Appointment a);
}
