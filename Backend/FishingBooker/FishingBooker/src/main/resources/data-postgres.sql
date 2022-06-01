
insert into address (street, country, city, lat, lng) values ('Maksima Gorkog 15', 'Srbija', 'Novi Sad', 0, 0);
insert into address (street, country, city, lat, lng) values ('Nemanjina 35', 'Srbija', 'Beograd', 0, 0);
insert into address (street, country, city, lat, lng) values ('Balzakova 20', 'Srbija', 'Novi Sad', 0, 0);
insert into address (street, country, city, lat, lng) values ('Amajic', 'Srbija', 'Mali Zvornik', 0, 0);
insert into address (street, country, city, lat, lng) values ('Vrhpolje', 'Srbija', 'Ljubovija', 0, 0);
insert into address (street, country, city, lat, lng) values ('Ledinci', 'Srbija', 'Ledinci', 0, 0);

INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (name) VALUES ('ROLE_TUTOR');
INSERT INTO ROLE (name) VALUES ('ROLE_BOATOWNER');
INSERT INTO ROLE (name) VALUES ('ROLE_RESORTOWNER');

insert into users (dtype, email, password, name, surname, phone_number, status, address_id,penalty_count) values ('User','marko.trifunovic135@gmail.com', 'bojan', 'Blagoje', 'Adzic', '021334563','CONFIRMED',1,0);
insert into users (dtype, email, password, name, surname, phone_number, status, address_id,penalty_count,points) values ('BoatOwner','badzaa@gmail.com', 'bojan', 'Bosko', 'Adzic', '021554263','ADMIN_CONFIRMED',1,0,40);
insert into users (dtype, email, password, name, surname, phone_number, status, address_id,penalty_count,points) values ('ResortOwner','radomBoj@gmail.com', 'bojan', 'Radomir', 'Bojanic', '015553363','ADMIN_CONFIRMED',2,0,50);
insert into users (dtype, email, password, name, surname, phone_number, status, address_id,penalty_count) values ('Tutor','dragoOrasanin@gmail.com', 'bojan', 'Drago', 'Orasanin','015252161','ADMIN_CONFIRMED',2,0);
insert into users (dtype, email, password, name, surname, phone_number, status, address_id,password_changed,penalty_count) values ('Admin','admin@gmail.com', 'admin', 'admin', 'admin','015252161','ADMIN_CONFIRMED',3,'true',0);
insert into users (dtype, email, password, name, surname, phone_number, status, address_id,penalty_count,points) values ('Tutor','abrkljac9@gmail.com', 'bojan', 'Mihailo', 'Radic','015235561','ADMIN_CONFIRMED',1,0,40);
insert into users (dtype, email, password, name, surname, phone_number, status, address_id,penalty_count,points) values ('BoatOwner','fresh.trifunovic@gmail.com', 'bojan', 'Marko', 'Trifunovic', '02166654263','ADMIN_CONFIRMED',1,0,45);
insert into users (dtype, email, password, name, surname, phone_number, status, address_id,penalty_count) values ('ResortOwner','marko.trifunovic@gmail.com', 'bojan', 'Aleksa', 'Stojanovic','016235561','ADMIN_CONFIRMED',1,0);


INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 1); 
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 4); 
INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 5);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (4, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (4, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (5, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (5, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (6, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (6, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (7, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (7, 4);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (8, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (8, 5);

insert into boat (name, type_of_boat, length, engine_id, engine_power, max_speed, navigation_equipment, boat_address_id, description, max_person, rules, fishing_equipment, cancel_percentage, boat_owner_id, rate,status) values ('Vesna','Fishing boat','5 meter','1','15 HP', 80, 'GPS', 1,'Middle size fishing boat.', 7,'No alcohol. No smoking.', '7 fishing rods and decoys.',0 , 2, 5,'CONFIRMED');
insert into boat (name, type_of_boat, length, engine_id, engine_power, max_speed, navigation_equipment, boat_address_id, description, max_person, rules, fishing_equipment, cancel_percentage, boat_owner_id, rate,status) values ('Orkan','Fishing boat','4.5 meter','1','13 HP', 70, 'GPS', 3,'Middle size fishing boat.', 5,'No alcohol. No smoking.', '4 fishing rods and decoys.',0 , 2, 4,'CONFIRMED');

insert into resort (name, description, resort_address_id, num_of_rooms, num_of_beds, rules, resort_owner_id, rate,status) values ('Drinski raj','Resort on coast of Zvornik lake with dock and beach.', 4, 2, 4, 'Forbiden entrance to non checked and authorised persons to resort objects.', 3, 3,'CONFIRMED');
insert into resort (name, description, resort_address_id, num_of_rooms, num_of_beds, rules, resort_owner_id, rate,status) values ('Vila Podrinja','Resort on coast of middle stream of river Drina with dock and beach. Confortable apartments and good service.', 5, 3, 7, 'Forbiden entrance to non checked and authorised persons to resort objects.', 3, 5,'CONFIRMED');
insert into resort (name, description, resort_address_id, num_of_rooms, num_of_beds, rules, resort_owner_id, rate,status) values ('Osacanka','Retro complex on coast of middle stream of river Drina with dock and beach. Confortable apartments, traditional food and good service.', 5, 2, 5, 'Forbiden entrance to non checked and authorised persons to resort objects.', 3, 4,'CONFIRMED');

insert into service_price (number_of_days, price) values (1,1500);
insert into service_price (number_of_days, price) values (5,5000);
insert into service_price (number_of_days, price) values (10,15000);
insert into service_price (number_of_days, price) values (1,1500);

insert into boat_rent_price (number_of_days, price) values (1,1500);
insert into boat_rent_price (number_of_days, price) values (5,5000);
insert into boat_rent_price (number_of_days, price) values (10,15000);
insert into boat_rent_price (number_of_days, price) values (1,1500);

insert into resort_rent_price (number_of_days, price) values (1,1500);
insert into resort_rent_price (number_of_days, price) values (5,5000);
insert into resort_rent_price (number_of_days, price) values (10,15000);


insert into resort_prices (resort_resort_id, prices_resortrent_price_id) values (1,1);
insert into resort_prices (resort_resort_id, prices_resortrent_price_id) values (1,2);
insert into resort_prices (resort_resort_id, prices_resortrent_price_id) values (1,3);

--badzaa
insert into boat_prices (boat_boat_id, prices_boatrent_price_id) values (1,1);
insert into boat_prices (boat_boat_id, prices_boatrent_price_id) values (1,2);
insert into boat_prices (boat_boat_id, prices_boatrent_price_id) values (1,3);

insert into boat_prices (boat_boat_id, prices_boatrent_price_id) values (2,4);



insert into tutor_service (name, description, max_person, rules, fishing_equipment, cancel_procentage, address_id, tutor_id, rate,status,tutor_bio) values ('One-day fishing','One day tour accross lake with fishing on most active fishing localities.', 9, 'No smoking. No alcohol.', 'Clients equipment required', 0, 4, 4, 5,'CONFIRMED','Tutor with 20 years of expirience. Great knower of Danube Backa coast and fish.');
insert into tutor_service (name, description, max_person, rules, fishing_equipment, cancel_procentage, address_id, tutor_id, rate,status,tutor_bio) values ('Three-day fishing','Three day tour accross lake with camping and fishing on most active fishing localities.', 5, 'No smoking. No alcohol.', 'Clients equipment for camping and fishing required', 0, 4, 4, 4,'CONFIRMED','Tutor with 20 years of expirience. Great knower of Danube Backa coast and fish.');
--abrkljac9
insert into tutor_service (name, description, max_person, rules, fishing_equipment, cancel_procentage, address_id, tutor_id, rate,status,tutor_bio) values ('Danube fishing','One day tour accross Danube with fishing on most active fishing spots.', 7, 'No smoking. No alcohol.', 'Clients equipment required', 0, 6, 6, 5,'CONFIRMED','Tutor with 20 years of expirience. Great knower of Danube Backa coast and fish.');
insert into tutor_service (name, description, max_person, rules, fishing_equipment, cancel_procentage, address_id, tutor_id, rate,status,tutor_bio) values ('Danube camping','Three day tour accross Novi Sad Danube coast with camping and fishing on most active fishing spots.', 3, 'No smoking. No alcohol.', 'Clients equipment for camping and fishing required', 0, 6, 6, 4,'CONFIRMED','Tutor with 20 years of expirience. Great knower of Danube Backa coast and fish.');

insert into tutor_service_prices (tutor_service_tutor_service_id, prices_service_price_id) values (1,1);
insert into tutor_service_prices (tutor_service_tutor_service_id, prices_service_price_id) values (1,2);
insert into tutor_service_prices (tutor_service_tutor_service_id, prices_service_price_id) values (1,3);

insert into tutor_service_prices (tutor_service_tutor_service_id, prices_service_price_id) values (3,4);

insert into extras (service, price, boat_id) values('Fishing', '900', 1);
insert into extras (service, price, boat_id) values('Lake drive', '500', 1);
insert into extras (service, price, boat_id) values('Camping', '600', 1);
insert into extras (service, price, boat_id) values('Fishing', '700', 2);
insert into extras (service, price, boat_id) values('Sightseeing', '550', 2);
insert into extras (service, price, boat_id) values('Camping', '800', 2);

insert into period (start_date, end_date) values('Sat Dec 10 10:00:00 GMT 2021','Sun Dec 12 15:00:00 GMT 2021');
insert into period (start_date, end_date) values('Sat Dec 17 10:00:00 GMT 2021','Sun Dec 19 15:00:00 GMT 2021');
insert into period (start_date, end_date) values('Sat Dec 24 10:00:00 GMT 2021','Sun Dec 26 15:00:00 GMT 2021');
insert into period (start_date, end_date) values('Sat Dec 31 10:00:00 GMT 2021','Sun Jan 02 15:00:00 GMT 2021');
insert into period (start_date, end_date) values('Mon Dec 06 10:00:00 GMT 2021','Sat Dec 11 15:00:00 GMT 2021');
insert into period (start_date, end_date) values('Mon Dec 13 10:00:00 GMT 2021','Sat Dec 18 15:00:00 GMT 2021');
insert into period (start_date, end_date) values('Mon Dec 20 10:00:00 GMT 2021','Sat Dec 25 15:00:00 GMT 2021');
insert into period (start_date, end_date) values('Mon Dec 27 10:00:00 GMT 2021','Sat Jan 01 15:00:00 GMT 2021');
insert into period (start_date) values('Mon Dec 27 10:00:00 GMT 2021');


insert into period (start_date, end_date) values('2022-02-01 13:00:00','2022-02-15 13:00:00');
insert into period (start_date, end_date) values('2022-02-03 11:00:00','2022-02-17 13:00:00');
insert into period (start_date, end_date) values('2022-02-04 13:00:00','2022-02-18 13:00:00');
insert into period (start_date, end_date) values('2022-02-04 12:00:00','2022-02-11 13:00:00');
insert into period (start_date, end_date) values('2022-05-12 13:00:00','2022-06-01 13:00:00');
--14

insert into users_available (tutor_id, available_id) values (6,14);
insert into users_available (tutor_id, available_id) values (6,10);
insert into users_available (tutor_id, available_id) values (6,11);
insert into users_available (tutor_id, available_id) values (4,12);
insert into users_available (tutor_id, available_id) values (4,13);





insert into period (start_date, end_date) values('2021-02-01 13:00:00','2021-02-15 13:00:00');
insert into period (start_date, end_date) values('2021-03-03 11:00:00','2021-03-17 13:00:00');
insert into period (start_date, end_date) values('2021-04-04 13:00:00','2021-04-18 13:00:00');
insert into period (start_date, end_date) values('2021-05-04 12:00:00','2021-05-11 13:00:00');
insert into period (start_date, end_date) values('2021-06-15 12:00:00','2021-06-27 13:00:00');
insert into period (start_date, end_date) values('2021-07-01 13:00:00','2021-07-15 13:00:00');
insert into period (start_date, end_date) values('2021-08-03 11:00:00','2021-08-17 13:00:00');
insert into period (start_date, end_date) values('2021-09-04 13:00:00','2021-09-18 13:00:00');
insert into period (start_date, end_date) values('2021-10-04 12:00:00','2021-10-11 13:00:00');
insert into period (start_date, end_date) values('2021-11-15 12:00:00','2021-11-27 13:00:00');
insert into period (start_date, end_date) values('2022-05-27 13:00:00','2022-06-05 13:00:00');
insert into period (start_date, end_date) values('2022-05-01 11:00:00','2022-05-12 13:00:00'); --26
insert into period (start_date, end_date) values('2022-01-04 13:00:00','2022-01-18 13:00:00');
insert into period (start_date, end_date) values('2022-02-04 12:00:00','2022-02-11 13:00:00');
insert into period (start_date, end_date) values('2022-02-15 12:00:00','2022-02-27 13:00:00');


insert into appointment (dtype, period_id, max_person, additional_services, price, appoint_type, address_id, resort_id,user_id,status) values ('ResortAppointment', 15, 7, 'No additional services.', 500.00, 'RESORT', 4, 1,1,'SUCCESSFUL');
insert into appointment (dtype, period_id, max_person, additional_services, price, appoint_type, address_id, tutor_service_id,user_id,status) values ('TutorServiceAppointment', 25, 7, 'No additional services.', 2000.00, 'TUTORSERVICE', 5, 4,1,'SUCCESSFUL');
insert into appointment (dtype, period_id, max_person, additional_services, price, appoint_type, address_id, boat_id,user_id,status) values ('BoatAppointment', 17, 5, 'No additional services.', 21000.00,'BOAT', 4,1, 1,'SUCCESSFUL');
insert into appointment (dtype, period_id, max_person, additional_services, price, appoint_type, address_id, boat_id, user_id,status) values ('BoatAppointment', 18, 8, 'No additional services.', 1200.00, 'BOAT', 5, 2,1,'CANCELED');
insert into appointment (dtype, period_id, max_person, additional_services, price, appoint_type, address_id, resort_id,user_id,status) values ('ResortAppointment', 19, 7, 'No additional services.', 400.00, 'RESORT', 4, 2,1,'SUCCESSFUL');
insert into appointment (dtype, period_id, max_person, additional_services, price, appoint_type, address_id, tutor_service_id,status) values ('TutorServiceAppointment', 20, 7, 'No additional services.', 1900.00, 'TUTORSERVICE', 4, 1,'CANCELED');
insert into appointment (dtype, period_id, max_person, additional_services, price, appoint_type, address_id, boat_id,user_id,status) values ('BoatAppointment', 21, 5, 'No additional services.', 1700.00,'BOAT', 5,2, 1,'CANCELED');
insert into appointment (dtype, period_id, max_person, additional_services, price, appoint_type, address_id, boat_id, user_id,status) values ('BoatAppointment', 22, 8, 'No additional services.', 800.00, 'BOAT', 5, 2,1,'CANCELED');
insert into appointment (dtype, period_id, max_person, additional_services, price, appoint_type, address_id, resort_id, user_id,status) values ('ResortAppointment', 23, 7, 'No additional services.', 500.00, 'RESORT', 4, 1, 1,'SUCCESSFUL');
insert into appointment (dtype, period_id, max_person, additional_services, price, appoint_type, address_id, tutor_service_id, user_id,status) values ('TutorServiceAppointment', 24, 7, 'No additional services.', 900.00, 'TUTORSERVICE', 4, 1, 1,'CANCELED');
insert into appointment (dtype, period_id, max_person, additional_services, price, appoint_type, address_id, resort_id, user_id,status) values ('ResortAppointment', 25, 7, 'No additional services.', 1500.00, 'RESORT', 4, 2, 1,'SUCCESSFUL');
insert into appointment (dtype, period_id, max_person, additional_services, price, appoint_type, address_id, tutor_service_id, user_id,status) values ('TutorServiceAppointment', 26, 7, 'No additional services.', 1400.00, 'TUTORSERVICE', 4, 4, 1,'CANCELED');


insert into objection (appointment_id, objection, creator_id, version) values(12,'Zalba',1,1);

--INSERT INTO completed_appointment(canceled_percentage, owner_percentage, payed_price, system_percentage, appointment_id) VALUES (10.0, 70.0, 2000, 30, 2);
--INSERT INTO completed_appointment(canceled_percentage, owner_percentage, payed_price, system_percentage, appointment_id) VALUES (10.0, 70.0, 1400, 30, 12);
--insert into discount_offer (additional_services, entity_type, max_person, place, price, boat_id, reservation_per_id, validity_per_id) values ('None', 'BOAT', 7, 'Amajic, Zapadna Srbija', 1000.0, 2, 1, 5);-->
--insert into discount_offer (additional_services, entity_type, max_person, place, price, boat_id, reservation_per_id, validity_per_id) values ('None', 'BOAT', 7, 'Vrhpolje, Zapadna Srbija', 1200.0, 1, 2, 6);
--insert into discount_offer (additional_services, entity_type, max_person, place, price, boat_id, reservation_per_id, validity_per_id) values ('None', 'BOAT', 7, 'Mali Zvornik, Zapadna Srbija', 800.0, 2, 3, 7);
--insert into discount_offer (additional_services, entity_type, max_person, place, price, boat_id, reservation_per_id, validity_per_id) values ('None', 'BOAT', 7, 'Perucac, Zapadna Srbija', 800.0, 1, 4, 8);

insert into discount_offer (additional_services, entity_type, max_person, place, price, boat_id, reservation_per_id, validity_per_id) values ('None', 'BOAT', 7, 'Amajic, Zapadna Srbija', 1000.0, 1, 9, 12);
insert into discount_offer (additional_services, entity_type, max_person, place, price, boat_id, reservation_per_id, validity_per_id) values ('None', 'BOAT', 7, 'Vrhpolje, Zapadna Srbija', 700.0, 2, 10, 12);

insert into discount_offer (additional_services, entity_type, max_person, place, price, tutor_service_id, reservation_per_id, validity_per_id) values ('None', 'TUTORSERVICE', 7, 'Amajic, Zapadna Srbija', 1000.0, 2, 9, 12);
insert into discount_offer (additional_services, entity_type, max_person, place, price, tutor_service_id, reservation_per_id, validity_per_id) values ('None', 'TUTORSERVICE', 7, 'Vrhpolje, Zapadna Srbija', 700.0, 1, 10, 12);
insert into discount_offer (additional_services, entity_type, max_person, place, price, tutor_service_id, reservation_per_id, validity_per_id) values ('None', 'TUTORSERVICE', 7, 'Mali Zvornik, Zapadna Srbija', 800.0, 3, 11, 13);
insert into discount_offer (additional_services, entity_type, max_person, place, price, tutor_service_id, reservation_per_id, validity_per_id) values ('None', 'TUTORSERVICE', 7, 'Perucac, Zapadna Srbija', 800.0, 4, 11, 13);

insert into revisions (dtype, comment, rate, status, boat_appointment_id, creator_id) values ('BoatAppointmentRevision','Great expirience.',5, 0, 8,1);

insert into revisions (dtype, comment, rate, status, resort_appointment_id, creator_id) values ('ResortAppointmentRevision','Resort is excelent.',4, 0, 1,1);
insert into revisions (dtype, comment, rate, status, resort_appointment_id, creator_id) values ('ResortAppointmentRevision','Resort is excelent.',5, 1, 1,1);
insert into revisions (dtype, comment, rate, status, resort_appointment_id, creator_id) values ('ResortAppointmentRevision','',1, 4, 1,1);
insert into revisions (dtype, comment, rate, status, resort_appointment_id, creator_id) values ('ResortAppointmentRevision','Resort is bad.',2, 4, 1,1);
insert into revisions (dtype, comment, rate, status, resort_appointment_id, creator_id) values ('ResortAppointmentRevision','Resort is good.',4, 4, 1,1);
insert into revisions (dtype, comment, rate, status, resort_appointment_id, creator_id) values ('ResortAppointmentRevision','Great expirience.',3, 4, 1,1);
insert into revisions (dtype, comment, rate, status, resort_appointment_id, creator_id) values ('ResortAppointmentRevision','Resort is excelent.',4, 4, 1,1);
insert into revisions (dtype, comment, rate, status, resort_appointment_id, creator_id) values ('ResortAppointmentRevision','Resort is excelent.',5, 4, 11,1);
insert into revisions (dtype, comment, rate, status, resort_appointment_id, creator_id) values ('ResortAppointmentRevision','',1, 4, 11,1);
insert into revisions (dtype, comment, rate, status, resort_appointment_id, creator_id) values ('ResortAppointmentRevision','Resort is bad.',2, 4, 11,1);
insert into revisions (dtype, comment, rate, status, resort_appointment_id, creator_id) values ('ResortAppointmentRevision','Resort is good.',4, 4, 9,1);
insert into revisions (dtype, comment, rate, status, resort_appointment_id, creator_id) values ('ResortAppointmentRevision','Great expirience.',5, 4, 11,1);

insert into period (start_date, end_date) values('2021-01-01 00:00:00','2021-12-31 00:00:00');
insert into period (start_date) values('2022-01-01 00:00:00');
insert into system_data (procentage, valid_id) values (5,30);
insert into system_data (procentage, valid_id) values (3,31);

insert into user_category_settings (name, discount_procentage, revenue_procentage, points_margine) values ('Regular',0,50,0);
insert into user_category_settings (name, discount_procentage, revenue_procentage, points_margine) values ('Silver',5,65,40);
insert into user_category_settings (name, discount_procentage, revenue_procentage, points_margine) values ('Gold',10,90,100);
insert into period (start_date) values('2022-01-01 00:00:00');
insert into points_settings (points_for_clients, points_for_owners, valid_id, creator_id,penalty) values (2,3,32,5,1); 

insert into period (start_date, end_date) values('2022-05-30 13:00:00','2022-06-20 13:00:00'); --33
insert into period (start_date, end_date) values('2022-02-01 13:00:00','2022-02-15 13:00:00');
insert into period (start_date, end_date) values('2022-02-03 11:00:00','2022-02-17 13:00:00');
insert into period (start_date, end_date) values('2022-05-01 11:00:00','2022-05-12 13:00:00'); --36

insert into boat_boat_available(boat_boat_id,boat_available_id) values (1,36);
insert into boat_boat_available(boat_boat_id,boat_available_id) values (1,34);
insert into boat_boat_available(boat_boat_id,boat_available_id) values (2,35);

insert into appointment (dtype, period_id, max_person, additional_services, price, appoint_type, address_id, boat_id,user_id,status) values ('BoatAppointment', 33, 5, 'No additional services.', 26000.00,'BOAT', 4, 1,1,'SUCCESSFUL');
insert into resort_resort_available (resort_resort_id, resort_available_id) values (1,13);
