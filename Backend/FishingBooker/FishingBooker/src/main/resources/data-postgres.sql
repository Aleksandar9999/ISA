insert into clients (dtype, email, password, name, surname, bio, address, city, country, phone_number, status) values ('User','badzaic@gmail.com', 'bojan', 'Blagoje', 'Adzic', '', 'Maksima Gorkog 5', 'Novi Sad', 'Srbija','021334563','CONFIRMED');
insert into clients (dtype, email, password, name, surname, bio, address, city, country, phone_number, status) values ('BoatOwner','badzaa@gmail.com', 'bojan', 'Bosko', 'Adzic', '', 'Maksima Gorkog 33', 'Novi Sad', 'Srbija','021554263','CONFIRMED');
insert into clients (dtype, email, password, name, surname, bio, address, city, country, phone_number, status) values ('ResortOwner','radomBoj@gmail.com', 'bojan', 'Radomir', '', 'Bojanic', 'Zivojina Misica', 'Ljubovija', 'Srbija','015553363','CONFIRMED');
insert into clients (dtype, email, password, name, surname, bio, address, city, country, phone_number, status) values ('Tutor','dragoOrasanin@gmail.com', 'bojan', 'Drago', 'Orasanin', 'Tutor with 20 years of expirience. Great knower of Drina coast and wildlife.', 'Zivojina Misica', 'Ljubovija', 'Srbija','015252161','CONFIRMED');

insert into address (street, country, city, lat, lng) values ('Maksima Gorkog 15', 'Srbija', 'Novi Sad', 0, 0);
insert into address (street, country, city, lat, lng) values ('Nemanjina 35', 'Srbija', 'Beograd', 0, 0);
insert into address (street, country, city, lat, lng) values ('Balzakova 20', 'Srbija', 'Novi Sad', 0, 0);
insert into address (street, country, city, lat, lng) values ('Amajic', 'Srbija', 'Mali Zvornik', 0, 0);
insert into address (street, country, city, lat, lng) values ('Vrhpolje', 'Srbija', 'Ljubovija', 0, 0);


insert into boat (name, type_of_boat, length, engine_id, engine_power, max_speed, navigation_equipment, address_id, description, max_person, rules, fishing_equipment, cancel_percentage, boat_owner_id, rate) values ('Vesna','Fishing boat','5 meter','1','15 HP', 80, 'GPS', 3,'Middle size fishing boat.', 7,'No alcohol. No smoking.', '7 fishing rods and decoys.',0 , 2, 5);
insert into boat (name, type_of_boat, length, engine_id, engine_power, max_speed, navigation_equipment, address_id, description, max_person, rules, fishing_equipment, cancel_percentage, boat_owner_id, rate) values ('Orkan','Fishing boat','4.5 meter','1','13 HP', 70, 'GPS', 3,'Middle size fishing boat.', 5,'No alcohol. No smoking.', '4 fishing rods and decoys.',0 , 2, 4);

insert into resort (name, description, address_id, num_of_rooms, num_of_beds, rules, resort_owner_id, rate) values ('Drinski raj','Resort on coast of Zvornik lake with dock and beach.', 4, 2, 4, 'Forbiden entrance to non checked and authorised persons to resort objects.', 3, 3);
insert into resort (name, description, address_id, num_of_rooms, num_of_beds, rules, resort_owner_id, rate) values ('Vila Podrinja','Resort on coast of middle stream of river Drina with dock and beach. Confortable apartments and good service.', 5, 3, 7, 'Forbiden entrance to non checked and authorised persons to resort objects.', 3, 5);
insert into resort (name, description, address_id, num_of_rooms, num_of_beds, rules, resort_owner_id, rate) values ('Osacanka','Retro complex on coast of middle stream of river Drina with dock and beach. Confortable apartments, traditional food and good service.', 5, 2, 5, 'Forbiden entrance to non checked and authorised persons to resort objects.', 3, 4);

insert into tutor_service (name, description, max_person, rules, fishing_equipment, cancel_procentage, address_id, tutor_id, rate) values ('One-day fishing','One day tour accross lake with fishing on most active fishing localities.', 9, 'No smoking. No alcohol.', 'Clients equipment required', 0, 4, 4, 5);
insert into tutor_service (name, description, max_person, rules, fishing_equipment, cancel_procentage, address_id, tutor_id, rate) values ('Three-day fishing','Three day tour accross lake with camping and fishing on most active fishing localities.', 5, 'No smoking. No alcohol.', 'Clients equipment for camping and fishing required', 0, 4, 4, 4);

insert into photo (title, url) values ('Three-day fishing','Three day tour accross lake with camping and fishing on most active fishing localities.');

insert into appoinment (dtype, start, duration, max_person, additional_services, price, address_id, user_id, resort_id) values ('ResortAppointment', 'Sat Nov 27 18:04:41 GMT 2021',3.0, 7, 'No additional services.', 500.00, 4, 1, 1);

