CREATE TABLE user_service.role (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,,
  `role_name` varchar(48) NOT NULL,
  PRIMARY KEY (`role_id`)
);

CREATE TABLE user_service.user (
	`user_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `user_name` varchar(48) NOT NULL,
    `password` varchar(48) NOT NULL,
    `email` varchar(48) NOT NULL,
    `register_date` date NOT NULL,
    `account_status` enum('INACTIVE', 'ACTIVE', 'BLOCKED'),
    PRIMARY KEY (`user_id`)
);

CREATE TABLE user_service.user_role (
	`user_role_id` bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id` bigint(20) NOT NULL,
    `role_id` bigint(20) NOT NULL,
    PRIMARY KEY (`user_role_id`),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES user_service.user(user_id),
    CONSTRAINT fk_role FOREIGN KEY (role_id) REFERENCES user_service.role(role_id),
	UNIQUE KEY id_user_role (`user_id`, `role_id`)
);