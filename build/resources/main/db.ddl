CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(64) NOT NULL unique,
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) DEFAULT NULL,
  `password`varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(64) NOT NULL UNIQUE,
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) DEFAULT NULL,
  `password` varchar(64) NOT NULL,
  `role` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `role` (
  `role` varchar(64) NOT NULL,
  PRIMARY KEY (`role`)
);

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role` varchar(64) NOT NULL,
  PRIMARY KEY (`user_id`,`role`)
);

drop table  `user_role`;

CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `year` varchar(8) NOT NULL,
  `semester` varchar(8) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_id` int(11) NOT NULL,
  `date` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `subject_schedule` (
  `subject_id` int(11) NOT NULL,
  `schedule_id` int(11) NOT NULL,
  PRIMARY KEY (`subject_id`,`schedule_id`)
);


CREATE TABLE `presence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `date` varchar(64) NOT NULL,
  `status` int(3) NOT NULL,

  PRIMARY KEY (`id`)
);

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL unique,
  `first_name` varchar(64) NOT NULL,
  `last_name` varchar(64) DEFAULT NULL,
  `password`varchar(64) NOT NULL,
  `role` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `role` (
  `role` varchar(64) NOT NULL,
  PRIMARY KEY (`role`)
);

CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role` varchar(64) NOT NULL,
  PRIMARY KEY (`user_id`,`role`)
);

CREATE TABLE `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `year` varchar(8) NOT NULL,
  `semester` varchar(8) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject_id` int(11) NOT NULL,
  `date` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `presence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `student_id` int(11) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `date` varchar(64) NOT NULL,
  `status` int(3) NOT NULL,

  PRIMARY KEY (`id`)
);

CREATE TABLE `subject_user` (
  `subject_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`subject_id`,`user_id`)
);

CREATE TABLE `schedule_presence_window` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `schedule_id` int(11) NOT NULL,
  `start_date` varchar(64) NOT NULL,
  `end_date` varchar(64) NOT NULL,
  `security_code` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

