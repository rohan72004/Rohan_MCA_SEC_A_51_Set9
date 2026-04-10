CREATE TABLE members (
 member_id INT PRIMARY KEY AUTO_INCREMENT,
 name VARCHAR(100),
 phone VARCHAR(15),
 plan ENUM('monthly','quarterly','annual')
);
CREATE TABLE lockers (
 locker_id INT PRIMARY KEY AUTO_INCREMENT,
 locker_no VARCHAR(10) UNIQUE,
 member_id INT DEFAULT NULL,
 assigned_on DATE DEFAULT NULL,
 FOREIGN KEY (member_id) REFERENCES members(member_id)
);
