-- create the databases
CREATE DATABASE IF NOT EXISTS appxpto;

-- create the users for each database
CREATE USER 'adm'@'%' IDENTIFIED BY 'root';
GRANT CREATE, ALTER, INDEX, LOCK TABLES, REFERENCES, UPDATE, DELETE, DROP, SELECT, INSERT ON `appxpto`.* TO 'adm'@'%';

FLUSH PRIVILEGES;

USE appxpto;

DROP TABLE IF EXISTS `covid`;
CREATE TABLE `covid` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Date_reported` datetime DEFAULT NULL,
  `Country_code` varchar(2) DEFAULT NULL,
  `Country` varchar(100) DEFAULT NULL,
  `WHO_region` varchar(50) DEFAULT NULL,
  `New_cases` int DEFAULT NULL,
  `Cumulative_cases` int DEFAULT NULL,
  `New_deaths` int DEFAULT NULL,
  `Cumulative_deaths` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=armscii8;