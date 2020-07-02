-- -- CLIENT COPY
-- insert into client_copy(email, city, company_name, company_registration_number, is_banned, is_removed, name, surname, password, role, state, street, street_number)
-- values("pera@maildrop.cc", null, null, null, false, false, null, null, null, null, null, null, null);
-- insert into client_copy(email, city, company_name, company_registration_number, is_banned, is_removed, name, surname, password, role, state, street, street_number)
-- values("mika@maildrop.cc", null, null, null, false, false, null, null, null, null, null, null, null);

-- CAR BRAND
insert into car_brand(id, name, is_removed) values(1, "BMW", FALSE);
insert into car_brand(id, name, is_removed) values(2, "AUDI", FALSE);
insert into car_brand(id, name, is_removed) values(3, "PORSCHE", FALSE);
insert into car_brand(id, name, is_removed) values(4, "YUGO", FALSE);
insert into car_brand(id, name, is_removed) values(5, "LAMBORGHINI", FALSE);
insert into car_brand(id, name, is_removed) values(6, "TOYOTA", FALSE);
insert into car_brand(id, name, is_removed) values(7, "FERRARI", FALSE);
insert into car_brand(id, name, is_removed) values(8, "FIAT", FALSE);
insert into car_brand(id, name, is_removed) values(9, "GOLF", FALSE);

-- CAR MODEL
insert into car_model(id, model_name, is_removed) values(1, "R8", FALSE);
insert into car_model(id, model_name, is_removed) values(2, "MACAN", FALSE);
insert into car_model(id, model_name, is_removed) values(3, "PUNTO", FALSE);
insert into car_model(id, model_name, is_removed) values(4, "5", FALSE);

-- CAR MODEL_BRAND
insert into car_brand_car_model_set(car_brand_id, car_model_set_id) values(8,3);
insert into car_brand_car_model_set(car_brand_id, car_model_set_id) values(2,1);
insert into car_brand_car_model_set(car_brand_id, car_model_set_id) values(9,4);

-- CAR FUEL
insert into car_fuel_type(id, fuel_type,is_removed) values(1,"DIESEL",FALSE);
insert into car_fuel_type(id, fuel_type,is_removed) values(2,"PETROL",FALSE);
insert into car_fuel_type(id, fuel_type,is_removed) values(3,"PETROL-HYBRID",FALSE);
insert into car_fuel_type(id, fuel_type,is_removed) values(4,"DIESEL-HYBRID",FALSE);
insert into car_fuel_type(id, fuel_type,is_removed) values(5,"FULL-ELECTRIC",FALSE);
insert into car_fuel_type(id, fuel_type,is_removed) values(6,"HYDROGEN",FALSE);
insert into car_fuel_type(id, fuel_type,is_removed) values(7,"OTHER",FALSE);

-- CAR CLASS
insert into car_class(id, class_name, is_removed) values(1, "S", FALSE);
insert into car_class(id, class_name, is_removed) values(2, "A", FALSE);
insert into car_class(id, class_name, is_removed) values(3, "B", FALSE);

-- CAR TRANSMISSION
insert into car_transmission_type(id, transmission_type,is_removed) values(1,"MANUAL",FALSE);
insert into car_transmission_type(id, transmission_type,is_removed) values(2,"AUTOMATIC",FALSE);

-- CONCRETE CAR
-- insert into concrete_car(id, car_brand_id, car_class_id, car_fuel_type_id, car_model_id, car_transmission_type_id, children_sit_number, name_of_car, travel_distance_constraint)
-- values
-- 	(101, 1, 1, 1, 1, 1, 0, 'car one', 300000.0),
-- 	(102, 2, 2, 2, 2, 2, 0, 'car one', 300000.0),
-- 	(103, 3, 3, 3, 3, 3, 0, 'car one', 300000.0);

-- OWNERS CAR
-- insert into owners_car (id, children_sit_number,client_email, concrete_car_id, is_insurance, travel_distance_constraint , traveled_unit, mileage)
-- values(201, 3, 'pera@maildrop.cc', 101, true, 250, 0, 2000.0);
-- insert into owners_car (id, children_sit_number,client_email, concrete_car_id, is_insurance, travel_distance_constraint , traveled_unit, mileage)
-- values(202, 3, 'pera@maildrop.cc', 102, false, 360, 0, 20054654.33);
-- insert into owners_car (id, children_sit_number,client_email, concrete_car_id, is_insurance, travel_distance_constraint , traveled_unit, mileage)
-- values(203, 3, 'pera@maildrop.cc', 103, false, null, 0, 180000.55);

--  PRICE
-- insert into price(id, discount, distance_overflow_price, insurance_price, is_removed,name, price_per_day)
-- values(301, null, null, 25, false, "SUPER CENA 1", 25);

--  ADVERT
-- insert into advert(id, description, end_of_advert, is_active, start_of_advert, client_email, client_id, owners_car_id, price_id, profile_picture)
-- values(501, "THE BEST", "2021-05-13 00:00:00", true, "2020-05-13 00:00:00", null, null, 201, 301, "data:image/jpeg;base64,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");
-- insert into advert(id, description, end_of_advert, is_active, start_of_advert, client_email, client_id, owners_car_id, price_id, profile_picture)
-- values(502, "THE BEST", "2021-05-13 00:00:00", false, "2020-05-13 00:00:00", null, null, 201, 301, null);

-- PICTURES
-- insert into picture(id, is_deleted, advert_id, picture)
-- values (601, false, 501, "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD//gA7Q1JFQVRPUjogZ2QtanBlZyB2MS4wICh1c2luZyBJSkcgSlBFRyB2ODApLCBxdWFsaXR5ID0gOTAK/9sAQwADAgIDAgIDAwMDBAMDBAUIBQUEBAUKBwcGCAwKDAwLCgsLDQ4SEA0OEQ4LCxAWEBETFBUVFQwPFxgWFBgSFBUU/9sAQwEDBAQFBAUJBQUJFA0LDRQUFBQU"),
-- (602, false, 501, "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD//gA7Q1JFQVRPUjogZ2QtanBlZyB2MS4wICh1c2luZyBJSkcgSlBFRyB2ODApLCBxdWFsaXR5ID0gOTAK/9sAQwADAgIDAgIDAwMDBAMDBAUIBQUEBAUKBwcGCAwKDAwLCgsLDQ4SEA0OEQ4LCxAWEBETFBUVFQwPFxgWFBgSFBUU/9sAQwEDBAQFBAUJBQUJFA0LDRQUFBQU"),
-- (603, true, 501, "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEAYABgAAD//gA7Q1JFQVRPUjogZ2QtanBlZyB2MS4wICh1c2luZyBJSkcgSlBFRyB2ODApLCBxdWFsaXR5ID0gOTAK/9sAQwADAgIDAgIDAwMDBAMDBAUIBQUEBAUKBwcGCAwKDAwLCgsLDQ4SEA0OEQ4LCxAWEBETFBUVFQwPFxgWFBgSFBUU/9sAQwEDBAQFBAUJBQUJFA0LDRQUFBQU");
