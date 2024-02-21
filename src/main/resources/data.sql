

INSERT INTO `flight` (`flight_number`, `created_at`, `created_by`, `updated_at`, `updated_by`, `departure_time`, `fare`, `flight_from`, `flight_name`, `flight_to`, `total_seats`)
VALUES
	(11709117, '2024-02-21 01:14:24.697262', 'Anonymous', '2024-02-21 13:28:00.852453', 'Anonymous Update', '2024-06-05 12:59:11.332000', 6000, 'Goa', 'Vistara', 'Bangalore', 6),
	(18077483, '2024-02-21 15:48:56.648823', 'Anonymous', NULL, NULL, '2024-04-05 12:59:11.332000', 8000, 'West Bengal', 'Indigo', 'Kerela', 10);

INSERT INTO `user_entity` (`email`, `created_at`, `created_by`, `updated_at`, `updated_by`, `address`, `contact_number`, `user_name`)
 VALUES
	('patrik@gmail.com', '2024-02-21 01:14:10.406884', 'Anonymous', NULL, NULL, 'Gujarat', '9843123411', 'Patrik'),
	('rigsby@gmail.com', '2024-02-21 12:26:31.640964', 'Anonymous', NULL, NULL, 'Bangalore', '8714567921', 'Rigsby'),
	('sneh@gmail.com', '2024-02-21 15:47:54.280933', 'Anonymous', NULL, NULL, 'Sikkim', '9816278654', 'Sneh'),
	('teresa@gmail.com', '2024-02-21 11:15:34.373216', 'Anonymous', NULL, NULL, 'Goa', '9843193411', 'teresa'),
	('vanpelt@gmail.com', '2024-02-21 12:24:54.320053', 'Anonymous', '2024-02-21 15:47:04.736955', 'UpdatedByAnonymous', 'Kerela', '8877612398', 'Vanpelt');

INSERT INTO `booking` (`booking_number`, `created_at`, `created_by`, `updated_at`, `updated_by`, `flight_number`, `email`)
VALUES
	(10582538, '2024-02-21 01:14:42.026821', 'Anonymous', NULL, NULL, 11709117, 'patrik@gmail.com'),
	(11008290, '2024-02-21 12:26:54.303563', 'Anonymous', NULL, NULL, 11709117, 'rigsby@gmail.com'),
	(13546269, '2024-02-21 15:50:03.168457', 'Anonymous', NULL, NULL, 18077483, 'sneh@gmail.com'),
	(16876707, '2024-02-21 12:25:26.314891', 'Anonymous', NULL, NULL, 11709117, 'vanpelt@gmail.com');
