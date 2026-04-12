CREATE DATABASE IF NOT EXISTS gym_locker;
USE gym_locker;
CREATE TABLE members (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL UNIQUE,
    plan VARCHAR(20) NOT NULL
);
CREATE TABLE lockers (
    locker_id INT AUTO_INCREMENT PRIMARY KEY,
    locker_no VARCHAR(10) UNIQUE,
    member_id INT NULL,
    assigned_on DATE,
    FOREIGN KEY (member_id) REFERENCES members(member_id)
);
INSERT INTO lockers (locker_no) VALUES
('L1'),
('L2'),
('L3');
