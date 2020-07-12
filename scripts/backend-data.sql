DELETE FROM user_service.user_role;
ALTER TABLE user_service.user_role AUTO_INCREMENT = 1;
DELETE FROM user_service.role;
ALTER TABLE user_service.role AUTO_INCREMENT = 1;
DELETE FROM user_service.user;
ALTER TABLE user_service.user AUTO_INCREMENT = 1;


INSERT INTO `user_service`.`role` (`role_name`) VALUES ('ADMIN');
INSERT INTO `user_service`.`role` (`role_name`) VALUES ('AGENT');
INSERT INTO `user_service`.`role` (`role_name`) VALUES ('USER');


INSERT INTO `user_service`.`user` (`user_name`, `password`, `email`, `register_date`, `account_status`) VALUES ('lupur', '$2a$10$FCcwoobaJpMeDyhvN.G.s.E6dQUFxA2f2ZDEgG3NCol6BnUjL2mg.', 'lupur@gmail.com', '2020-07-12', '1');
INSERT INTO `user_service`.`user` (`user_name`, `password`, `email`, `register_date`, `account_status`) VALUES ('admin', '$2a$10$FCcwoobaJpMeDyhvN.G.s.E6dQUFxA2f2ZDEgG3NCol6BnUjL2mg.', 'admin@gmail.com', '2020-07-12', '1');
INSERT INTO `user_service`.`user` (`user_name`, `password`, `email`, `register_date`, `account_status`) VALUES ('sava', '$2a$10$FCcwoobaJpMeDyhvN.G.s.E6dQUFxA2f2ZDEgG3NCol6BnUjL2mg.', 'sava@gmail.com', '2020-07-12', '1');
INSERT INTO `user_service`.`user` (`user_name`, `password`, `email`, `register_date`, `account_status`) VALUES ('smith', '$2a$10$FCcwoobaJpMeDyhvN.G.s.E6dQUFxA2f2ZDEgG3NCol6BnUjL2mg.', 'smith@gmail.com', '2020-07-12', '1');
INSERT INTO `user_service`.`user` (`user_name`, `password`, `email`, `register_date`, `account_status`) VALUES ('scully', '$2a$10$FCcwoobaJpMeDyhvN.G.s.E6dQUFxA2f2ZDEgG3NCol6BnUjL2mg.', 'scull@gmail.com', '2020-07-12', '1');


INSERT INTO `user_service`.`user_role` (`user_id`, `role_id`) VALUES ('1', '3');
INSERT INTO `user_service`.`user_role` (`user_id`, `role_id`) VALUES ('2', '1');
INSERT INTO `user_service`.`user_role` (`user_id`, `role_id`) VALUES ('3', '1');
INSERT INTO `user_service`.`user_role` (`user_id`, `role_id`) VALUES ('4', '2');
INSERT INTO `user_service`.`user_role` (`user_id`, `role_id`) VALUES ('5', '2');


DELETE FROM vehicle_service.vehicle;
DELETE FROM vehicle_service.model;
DELETE FROM vehicle_service.brand;
DELETE FROM vehicle_service.class_type;
DELETE FROM vehicle_service.collision_damage;
DELETE FROM vehicle_service.discount;
DELETE FROM vehicle_service.fuel_type;
DELETE FROM vehicle_service.image;
DELETE FROM vehicle_service.pricelist;
DELETE FROM vehicle_service.transmission_type;

ALTER TABLE vehicle_service.vehicle AUTO_INCREMENT = 1;
ALTER TABLE vehicle_service.model AUTO_INCREMENT = 1;
ALTER TABLE vehicle_service.brand AUTO_INCREMENT = 1;
ALTER TABLE vehicle_service.class_type AUTO_INCREMENT = 1;
ALTER TABLE vehicle_service.collision_damage AUTO_INCREMENT = 1;
ALTER TABLE vehicle_service.discount AUTO_INCREMENT = 1;
ALTER TABLE vehicle_service.fuel_type AUTO_INCREMENT = 1;
ALTER TABLE vehicle_service.image AUTO_INCREMENT = 1;
ALTER TABLE vehicle_service.pricelist AUTO_INCREMENT = 1;
ALTER TABLE vehicle_service.transmission_type AUTO_INCREMENT = 1;

INSERT INTO `vehicle_service`.`brand` (`brand_name`) VALUES ('Ford');
INSERT INTO `vehicle_service`.`brand` (`brand_name`) VALUES ('Mazda');
INSERT INTO `vehicle_service`.`brand` (`brand_name`) VALUES ('Fiat');
INSERT INTO `vehicle_service`.`brand` (`brand_name`) VALUES ('Audi');

INSERT INTO `vehicle_service`.`model` (`model_name`, `brand_id`) VALUES ('Focus', '1');
INSERT INTO `vehicle_service`.`model` (`model_name`, `brand_id`) VALUES ('Fiesta', '1');
INSERT INTO `vehicle_service`.`model` (`model_name`, `brand_id`) VALUES ('RX8', '2');
INSERT INTO `vehicle_service`.`model` (`model_name`, `brand_id`) VALUES ('CX5', '2');
INSERT INTO `vehicle_service`.`model` (`model_name`, `brand_id`) VALUES ('Punto', '3');
INSERT INTO `vehicle_service`.`model` (`model_name`, `brand_id`) VALUES ('500L', '3');
INSERT INTO `vehicle_service`.`model` (`model_name`, `brand_id`) VALUES ('A6', '4');
INSERT INTO `vehicle_service`.`model` (`model_name`, `brand_id`) VALUES ('Q3', '4');

INSERT INTO `vehicle_service`.`class_type` (`class_type_name`) VALUES ('Class A');
INSERT INTO `vehicle_service`.`class_type` (`class_type_name`) VALUES ('Economy');
INSERT INTO `vehicle_service`.`class_type` (`class_type_name`) VALUES ('Business');

INSERT INTO `vehicle_service`.`collision_damage` (`collision_damage_price`) VALUES ('1000');
INSERT INTO `vehicle_service`.`collision_damage` (`collision_damage_price`) VALUES ('2000');
INSERT INTO `vehicle_service`.`collision_damage` (`collision_damage_price`) VALUES ('3000');
INSERT INTO `vehicle_service`.`collision_damage` (`collision_damage_price`) VALUES ('4000');

INSERT INTO `vehicle_service`.`discount` (`number_of_days`, `percentage`, `start_date`, `end_date`) VALUES ('10', '5', '202-06-10', '2021-06-27');
INSERT INTO `vehicle_service`.`discount` (`number_of_days`, `percentage`, `start_date`, `end_date`) VALUES ('20', '7', '202-06-10', '2021-06-27');
INSERT INTO `vehicle_service`.`discount` (`number_of_days`, `percentage`, `start_date`, `end_date`) VALUES ('30', '10', '202-06-10', '2021-06-27');

INSERT INTO `vehicle_service`.`fuel_type` (`fuel_type_name`) VALUES ('Gasoline');
INSERT INTO `vehicle_service`.`fuel_type` (`fuel_type_name`) VALUES ('Diesel');
INSERT INTO `vehicle_service`.`fuel_type` (`fuel_type_name`) VALUES ('Electric');

INSERT INTO `vehicle_service`.`pricelist` (`daily_price`, `mileage_penalty`) VALUES ('10', '50');
INSERT INTO `vehicle_service`.`pricelist` (`daily_price`, `mileage_penalty`) VALUES ('20', '70');
INSERT INTO `vehicle_service`.`pricelist` (`daily_price`, `mileage_penalty`) VALUES ('30', '90');

INSERT INTO `vehicle_service`.`transmission_type` (`transmission_type_name`) VALUES ('Automatic');
INSERT INTO `vehicle_service`.`transmission_type` (`transmission_type_name`) VALUES ('Manual');

INSERT INTO `vehicle_service`.`vehicle` (`model_id`, `pricelist_id`, `collision_damage_id`, `fuel_type_id`, `transmission_type_id`, `class_type_id`, `user_id`, `milleage`, `milleage_constraint`, `insurance`, `number_of_seats`, `location`, `discount_id`) VALUES ('1', '1', '1', '1', '2', '1', '2', '100', '1000', '0', '3', 'Novi Sad', '1');
INSERT INTO `vehicle_service`.`vehicle` (`model_id`, `pricelist_id`, `collision_damage_id`, `fuel_type_id`, `transmission_type_id`, `class_type_id`, `user_id`, `milleage`, `milleage_constraint`, `insurance`, `number_of_seats`, `location`, `discount_id`) VALUES ('5', '2', '2', '2', '1', '3', '2', '100', '1000', '1', '2', 'Beograd', '1');
INSERT INTO `vehicle_service`.`vehicle` (`model_id`, `pricelist_id`, `collision_damage_id`, `fuel_type_id`, `transmission_type_id`, `class_type_id`, `user_id`, `milleage`, `milleage_constraint`, `insurance`, `number_of_seats`, `location`, `discount_id`) VALUES ('3', '3', '2', '1', '2', '2', '3', '100', '1000', '1', '5', 'Novi Sad', '1');
INSERT INTO `vehicle_service`.`vehicle` (`model_id`, `pricelist_id`, `collision_damage_id`, `fuel_type_id`, `transmission_type_id`, `class_type_id`, `user_id`, `milleage`, `milleage_constraint`, `insurance`, `number_of_seats`, `location`, `discount_id`) VALUES ('4', '2', '3', '2', '1', '1', '3', '100', '1000', '0', '5', 'Kragujevac', '1');
INSERT INTO `vehicle_service`.`vehicle` (`model_id`, `pricelist_id`, `collision_damage_id`, `fuel_type_id`, `transmission_type_id`, `class_type_id`, `user_id`, `milleage`, `milleage_constraint`, `insurance`, `number_of_seats`, `location`, `discount_id`) VALUES ('2', '1', '1', '1', '1', '3', '3', '100', '1000', '0', '3', 'Novi Sad', '1');



