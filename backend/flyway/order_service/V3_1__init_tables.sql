CREATE TABLE order_service.order_request (
  `request_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `request_status` int NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `owner_id` bigint(20) NOT NULL,
  `created_on` date,
  PRIMARY KEY (`request_id`)
);

CREATE TABLE order_service.vehicle_order (
	`order_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `vehicle_id` bigint(20) NOT NULL,
    `request_id` bigint(20) NOT NULL,
    `total_price` double NOT NULL,
    `pickup_date` date NOT NULL,
    `return_date` date NOT NULL,
    PRIMARY KEY (`order_id`),
    CONSTRAINT fk_order_request FOREIGN KEY (request_id) REFERENCES order_service.order_request(request_id)
);

CREATE TABLE order_service.order_report (
	`report_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `order_id` bigint(20) NOT NULL,
    `distance_traveled` double NOT NULL,
    PRIMARY KEY (`report_id`),
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES order_service.vehicle_order(order_id)
);