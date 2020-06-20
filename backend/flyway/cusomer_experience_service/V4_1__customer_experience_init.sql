CREATE TABLE customer_experience_service.message (
  `message_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author_id` bigint(20) NOT NULL,
  `author_name` varchar(48) NOT NULL,
  `order_request_id` bigint(20) NOT NULL,
  `content` varchar(128),
  `creation_date` date NOT NULL,
  PRIMARY KEY (`message_id`)
);

CREATE TABLE customer_experience_service.review (
	`review_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `author_id` bigint(20) NOT NULL,
    `author_name` varchar(48) NOT NULL,
    `vehicle_id` bigint(20) NOT NULL,
    `vehicle_order_id` bigint(20) NOT NULL,
    `comment` varchar(128),
    `rating` int(11) NOT NULL,
    `creation_date` date NOT NULL,
    `status` int(11) NOT NULL,
    PRIMARY KEY (`review_id`)
);