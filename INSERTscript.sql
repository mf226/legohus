use legohouse;
INSERT INTO `users` (`email`, `password`, `role`)
VALUES
('employee@test.com', '123', 'employee'),
('customer@test.com', '123', 'customer');

INSERT into `orders` (`email_FK`, `length`, `width`, `height`, `shipped`)
VALUES
('employee@test.com', '6', '4', '2', '1'),
('customer@test.com', '4', '4', '4', '0');