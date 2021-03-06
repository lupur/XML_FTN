CREATE TABLE vehicle_service.brand (
  `brand_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(48) NOT NULL,
  PRIMARY KEY (`brand_id`)
);

CREATE TABLE vehicle_service.pricelist (
  `pricelist_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `daily_price` double NOT NULL,
  `mileage_penalty` double NOT NULL,
  PRIMARY KEY (`pricelist_id`)
);

CREATE TABLE vehicle_service.collision_damage (
  `collision_damage_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `collision_damage_price` double NOT NULL,
  PRIMARY KEY (`collision_damage_id`)
);

CREATE TABLE vehicle_service.fuel_type (
	`fuel_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `fuel_type_name` varchar(48) NOT NULL,
    PRIMARY KEY (`fuel_type_id`)
);

CREATE TABLE vehicle_service.transmission_type (
	`transmission_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `transmission_type_name` varchar(48) NOT NULL,
    PRIMARY KEY (`transmission_type_id`)
);

CREATE TABLE vehicle_service.class_type (
	`class_type_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `class_type_name` varchar(48) NOT NULL,
    PRIMARY KEY (`class_type_id`)
);

CREATE TABLE vehicle_service.discount (
  `discount_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number_of_days` int(3) NOT NULL,
  `percentage` int(3) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  PRIMARY KEY (`discount_id`)
);

CREATE TABLE vehicle_service.model (
	`model_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `model_name` varchar(48) NOT NULL,
    `brand_id` bigint(20) NOT NULL,
    PRIMARY KEY (`model_id`),
    CONSTRAINT fk_brand FOREIGN KEY (brand_id) REFERENCES vehicle_service.brand(brand_id)
);

CREATE TABLE vehicle_service.vehicle (
  `vehicle_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `model_id` bigint(20) NOT NULL,
  `pricelist_id` bigint(20) NOT NULL,
  `collision_damage_id` bigint(20) NOT NULL,
  `fuel_type_id` bigint(20) NOT NULL,
  `transmission_type_id` bigint(20) NOT NULL,
  `class_type_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `milleage` double NOT NULL,
  `milleage_constraint` double,
  `insurance` boolean,
  `number_of_seats` int(2),
  `rating` float,
  `location` varchar(128),
  `discount_id` bigint(20),
  PRIMARY KEY (`vehicle_id`),
  CONSTRAINT fk_model FOREIGN KEY (model_id) REFERENCES vehicle_service.model(model_id),
  CONSTRAINT fk_pricelist FOREIGN KEY (pricelist_id) REFERENCES vehicle_service.pricelist(pricelist_id),
  CONSTRAINT fk_collision_damage FOREIGN KEY (collision_damage_id) REFERENCES vehicle_service.collision_damage(collision_damage_id),
  CONSTRAINT fk_fuel_type FOREIGN KEY (fuel_type_id) REFERENCES vehicle_service.fuel_type(fuel_type_id),
  CONSTRAINT fk_transmission_type FOREIGN KEY (transmission_type_id) REFERENCES vehicle_service.transmission_type(transmission_type_id),
  CONSTRAINT fk_class_type FOREIGN KEY (class_type_id) REFERENCES vehicle_service.class_type(class_type_id),
  CONSTRAINT fk_discount FOREIGN KEY (discount_id) REFERENCES vehicle_service.discount(discount_id)
);

CREATE TABLE vehicle_service.image (
  `image_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `image_uri` varchar(48) NOT NULL,
  `vehicle_id` bigint(20) NOT NULL,
  PRIMARY KEY (`image_id`),
  CONSTRAINT fk_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicle_service.vehicle(vehicle_id)
);