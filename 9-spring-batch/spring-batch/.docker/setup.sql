-- create the databases
CREATE DATABASE IF NOT EXISTS appxpto;

-- create the users for each database
CREATE USER 'adm'@'%' IDENTIFIED BY 'root';
GRANT CREATE, ALTER, INDEX, LOCK TABLES, REFERENCES, UPDATE, DELETE, DROP, SELECT, INSERT ON `appxpto`.* TO 'adm'@'%';

FLUSH PRIVILEGES;

USE appxpto;

DROP TABLE IF EXISTS `characters`;
CREATE TABLE `characters` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `skinColor` varchar(50) DEFAULT NULL,
  `eyeColor` varchar(50) DEFAULT NULL,
  `hairColor` varchar(50) DEFAULT NULL,
  `birthYear` varchar(50) DEFAULT NULL,
  `gender` varchar(50) DEFAULT NULL,
  `homeworld` varchar(50) DEFAULT NULL,
  `species` varchar(50) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `height` int DEFAULT NULL,
  `mass` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8;