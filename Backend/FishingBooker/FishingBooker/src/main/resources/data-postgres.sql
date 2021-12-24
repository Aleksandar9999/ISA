
insert into address (street, country, city, lat, lng) values ('Maksima Gorkog 15', 'Srbija', 'Novi Sad', 0, 0);
insert into address (street, country, city, lat, lng) values ('Nemanjina 35', 'Srbija', 'Beograd', 0, 0);
insert into address (street, country, city, lat, lng) values ('Balzakova 20', 'Srbija', 'Novi Sad', 0, 0);
insert into address (street, country, city, lat, lng) values ('Amajic', 'Srbija', 'Mali Zvornik', 0, 0);
insert into address (street, country, city, lat, lng) values ('Vrhpolje', 'Srbija', 'Ljubovija', 0, 0);
insert into address (street, country, city, lat, lng) values ('Ledinci', 'Srbija', 'Ledinci', 0, 0);

INSERT INTO ROLE (name) VALUES ('ROLE_USER');
INSERT INTO ROLE (name) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (name) VALUES ('ROLE_TUTOR');

insert into clients (dtype, email, password, name, surname, bio, phone_number, status, address_id) values ('User','badzaic@gmail.com', 'bojan', 'Blagoje', 'Adzic', '','021334563','ADMIN_CONFIRMED',1);
insert into clients (dtype, email, password, name, surname, bio, phone_number, status, address_id) values ('BoatOwner','badzaa@gmail.com', 'bojan', 'Bosko', 'Adzic', '','021554263','ADMIN_CONFIRMED',1);
insert into clients (dtype, email, password, name, surname, bio, phone_number, status, address_id) values ('ResortOwner','radomBoj@gmail.com', 'bojan', 'Radomir', 'Bojanic', '','015553363','ADMIN_CONFIRMED',2);
insert into clients (dtype, email, password, name, surname, bio, phone_number, status, address_id) values ('Tutor','dragoOrasanin@gmail.com', 'bojan', 'Drago', 'Orasanin', 'Tutor with 20 years of expirience. Great knower of Drina coast and wildlife.','015252161','ADMIN_CONFIRMED',2);
insert into clients (dtype, email, password, name, surname, bio, phone_number, status, address_id, password_changed) values ('Admin','admin@gmail.com', 'admin', 'admin', 'admin','','015252161','ADMIN_CONFIRMED',3,'true');
insert into clients (dtype, email, password, name, surname, bio, phone_number, status, address_id) values ('Tutor','abrkljac9@gmail.com', 'bojan', 'Mihailo', 'Radic', 'Tutor with 20 years of expirience. Great knower of Danube Backa coast and fish.','015235561','ADMIN_CONFIRMED',1);

INSERT INTO USER_ROLE (user_id, role_id) VALUES (1, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (2, 1); 
INSERT INTO USER_ROLE (user_id, role_id) VALUES (3, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (4, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (4, 3);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (5, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (5, 2);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (6, 1);
INSERT INTO USER_ROLE (user_id, role_id) VALUES (6, 3);

insert into boat (name, type_of_boat, length, engine_id, engine_power, max_speed, navigation_equipment, address_id, description, max_person, rules, fishing_equipment, cancel_percentage, boat_owner_id, rate) values ('Vesna','Fishing boat','5 meter','1','15 HP', 80, 'GPS', 3,'Middle size fishing boat.', 7,'No alcohol. No smoking.', '7 fishing rods and decoys.',0 , 2, 5);
insert into boat (name, type_of_boat, length, engine_id, engine_power, max_speed, navigation_equipment, address_id, description, max_person, rules, fishing_equipment, cancel_percentage, boat_owner_id, rate) values ('Orkan','Fishing boat','4.5 meter','1','13 HP', 70, 'GPS', 3,'Middle size fishing boat.', 5,'No alcohol. No smoking.', '4 fishing rods and decoys.',0 , 2, 4);

insert into resort (name, description, address_id, num_of_rooms, num_of_beds, rules, resort_owner_id, rate) values ('Drinski raj','Resort on coast of Zvornik lake with dock and beach.', 4, 2, 4, 'Forbiden entrance to non checked and authorised persons to resort objects.', 3, 3);
insert into resort (name, description, address_id, num_of_rooms, num_of_beds, rules, resort_owner_id, rate) values ('Vila Podrinja','Resort on coast of middle stream of river Drina with dock and beach. Confortable apartments and good service.', 5, 3, 7, 'Forbiden entrance to non checked and authorised persons to resort objects.', 3, 5);
insert into resort (name, description, address_id, num_of_rooms, num_of_beds, rules, resort_owner_id, rate) values ('Osacanka','Retro complex on coast of middle stream of river Drina with dock and beach. Confortable apartments, traditional food and good service.', 5, 2, 5, 'Forbiden entrance to non checked and authorised persons to resort objects.', 3, 4);

insert into service_price (number_of_days, price) values (1,1500);
insert into service_price (number_of_days, price) values (5,5000);
insert into service_price (number_of_days, price) values (10,15000);

insert into tutor_service (name, description, max_person, rules, fishing_equipment, cancel_procentage, address_id, tutor_id, rate,status) values ('One-day fishing','One day tour accross lake with fishing on most active fishing localities.', 9, 'No smoking. No alcohol.', 'Clients equipment required', 0, 4, 4, 5,'CONFIRMED');
insert into tutor_service (name, description, max_person, rules, fishing_equipment, cancel_procentage, address_id, tutor_id, rate,status) values ('Three-day fishing','Three day tour accross lake with camping and fishing on most active fishing localities.', 5, 'No smoking. No alcohol.', 'Clients equipment for camping and fishing required', 0, 4, 4, 4,'CONFIRMED');
insert into tutor_service (name, description, max_person, rules, fishing_equipment, cancel_procentage, address_id, tutor_id, rate,status) values ('Danube fishing','One day tour accross Danube with fishing on most active fishing spots.', 7, 'No smoking. No alcohol.', 'Clients equipment required', 0, 6, 6, 5,'CONFIRMED');
insert into tutor_service (name, description, max_person, rules, fishing_equipment, cancel_procentage, address_id, tutor_id, rate,status) values ('Danube camping','Three day tour accross Novi Sad Danube coast with camping and fishing on most active fishing spots.', 3, 'No smoking. No alcohol.', 'Clients equipment for camping and fishing required', 0, 6, 6, 4,'CONFIRMED');

insert into tutor_service_prices (tutor_service_tutor_service_id, prices_service_price_id) values (1,1);
insert into tutor_service_prices (tutor_service_tutor_service_id, prices_service_price_id) values (1,2);
insert into tutor_service_prices (tutor_service_tutor_service_id, prices_service_price_id) values (1,3);

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

insert into appointment (dtype, start, duration, max_person, additional_services, price, address_id, resort_id) values ('ResortAppointment', 'Sat Jan 22 18:00:00 GMT 2022',3.0, 7, 'No additional services.', 500.00, 4, 1);
insert into appointment (dtype, start, duration, max_person, additional_services, price, address_id, tutor_service_id) values ('TutorServiceAppointment', 'Sat Jan 29 18:00:00 GMT 2022',3.0, 7, 'No additional services.', 900.00, 4, 1);
insert into appointment (dtype, start, duration, max_person, additional_services, price, address_id, boat_id) values ('BoatAppointment', 'Sat Jan 22 14:00:00 GMT 2022',3.0, 5, 'No additional services.', 1500.00, 4, 1);
insert into appointment (dtype, start, duration, max_person, additional_services, price, address_id, boat_id) values ('BoatAppointment', 'Sat Jan 29 11:00:00 GMT 2022',3.0, 8, 'No additional services.', 1200.00, 5, 2);

insert into discount_offer (additional_services, max_person, place, price, boat_id, reservation_per_id, validity_per_id) values ('None',7, 'Amajic, Zapadna Srbija', 1400.0, 2, 1, 5);
insert into discount_offer (additional_services, max_person, place, price, boat_id, reservation_per_id, validity_per_id) values ('None',7, 'Vrhpolje, Zapadna Srbija', 1200.0, 1, 2, 6);
insert into discount_offer (additional_services, max_person, place, price, boat_id, reservation_per_id, validity_per_id) values ('None',7, 'Mali Zvornik, Zapadna Srbija', 2500.0, 2, 3, 7);
insert into discount_offer (additional_services, max_person, place, price, boat_id, reservation_per_id, validity_per_id) values ('None',7, 'Perucac, Zapadna Srbija', 1900.0, 1, 4, 8);

insert into revisions (dtype, comment, rate, status, boat_appointment_id) values ('BoatAppointmentRevision','Boat is excelent.',4.00, 4, 3);
insert into revisions (dtype, comment, rate, status, boat_appointment_id) values ('BoatAppointmentRevision','Boat is excelent.',5.00, 4, 3);
insert into revisions (dtype, comment, rate, status, boat_appointment_id) values ('BoatAppointmentRevision','',3.50, 4, 3);
insert into revisions (dtype, comment, rate, status, boat_appointment_id) values ('BoatAppointmentRevision','Boat is bad.',2.00, 4, 4);
insert into revisions (dtype, comment, rate, status, boat_appointment_id) values ('BoatAppointmentRevision','Boat is good.',4.00, 4, 4);
insert into revisions (dtype, comment, rate, status, boat_appointment_id) values ('BoatAppointmentRevision','Great expirience.',5.00, 4, 4);


insert into system_data (id, procentage, revenue) values (1,5,0);
