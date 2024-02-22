INSERT INTO `roles` (`role_id`, `role_name`) VALUES
	(1, 'ADMIN'),
	(2, 'USER');


INSERT INTO `user_entity` (`email`, `created_at`, `created_by`, `updated_at`, `updated_by`, `address`, `contact_number`, `pwd`, `user_name`, `role_id`) VALUES
	('admin@gmail.com', '2024-02-22 11:34:42.685386', 'Anonymous', NULL, NULL, 'Goa', '8819276123', '$2a$10$d8aKKPcXjw9bGz9IjKd3xux5Ttg86DqZHzb5hxJY80oEKXql9tXvu', 'Admin', 1),
	('akhil@gmail.com', '2024-02-22 00:16:08.057526', 'Anonymous', NULL, NULL, 'Ahmedabad', '8719276542', '$2a$10$chxbtfMr0mVwPXotZmkHC.yEk0299ggOID.LRLaA8Pg7Tg//eYhVq', 'Akhil', 2),
	('khushi@gmail.com', '2024-02-22 00:30:51.029904', 'Anonymous', NULL, NULL, 'Ahmedabad', '8719276542', '$2a$10$CwPWUtOGPpW3A/DNNLWT9e07YzHs3qoln07l0WI5dWlYxtskhWRbS', 'Khushi', 2),
	('sneh@gmail.com', '2024-02-22 11:26:17.840828', 'Anonymous', '2024-02-22 11:26:36.328429', 'UpdatedByAnonymous', 'Kerela', '8877612398', '$2a$10$RN/56Ks1O1ZLBsAIwoIazOeHESCrW3Iun5OZ2Cekeus9ixYeyEDs6', 'Sneh', 2),
	('tirth@gmail.com', '2024-02-22 11:27:20.849178', 'Anonymous', NULL, NULL, 'Goa', '9819276123', '$2a$10$TJinkOuAcwVg72JG7fYGj.EYG2wBrOxSkvIdnYkIGJ9SL7FgpIEEC', 'Tirth', 2);


INSERT INTO `flight` (`flight_number`, `created_at`, `created_by`, `updated_at`, `updated_by`, `departure_time`, `fare`, `flight_from`, `flight_name`, `flight_to`, `total_seats`) VALUES
	(12934260, '2024-02-21 21:53:40.314677', 'Anonymous', NULL, NULL, '2024-03-05 12:59:11.332000', 8000, 'Delhi', 'Vistara', 'Bangalore', 10),
	(14376576, '2024-02-22 11:28:36.731552', 'Anonymous', '2024-02-22 11:28:58.744936', 'Anonymous Update', '2024-06-05 12:59:11.332000', 7000, 'Goa', 'Indigo', 'Bangalore', 20),
	(15683985, '2024-02-21 21:38:36.926947', 'Anonymous', NULL, NULL, '2024-04-05 12:59:11.332000', 8000, 'West Bengal', 'Indigo', 'Kerela', 10),
	(17696793, '2024-02-22 11:28:02.971857', 'Anonymous', NULL, NULL, '2024-03-05 12:59:11.332000', 7000, 'Delhi', 'Akasa Air', 'Bangalore', 10);

INSERT INTO `booking` (`booking_number`, `created_at`, `created_by`, `updated_at`, `updated_by`, `flight_number`, `email`) VALUES
	(12934081, '2024-02-22 00:35:10.942197', 'Anonymous', NULL, NULL, 12934260, 'khushi@gmail.com'),
	(18336428, '2024-02-22 11:36:26.371470', 'Anonymous', NULL, NULL, 12934260, 'tirth@gmail.com');




