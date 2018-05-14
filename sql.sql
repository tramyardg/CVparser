CREATE DATABASE `jobsdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `jobsdb`.`applicant` (
  `idapplicant` INT NOT NULL AUTO_INCREMENT,
  `submissiondate` DATE NOT NULL,
  `name` VARCHAR(150) NULL,
  'objective' VARCHAR(300) NULL,
  `phonenumber` VARCHAR(200) NULL,
  `email` VARCHAR(100) NULL,
  `address` VARCHAR(200) NULL,
  `links` VARCHAR(300) NULL, 
  PRIMARY KEY (`idapplicant`, `submissiondate`))
COMMENT = 'Table for job applicant';


CREATE TABLE `applicanteducation` (
  `ideducation` INT NOT NULL AUTO_INCREMENT,
  `idapplicant` int NOT NULL,
  `fromto` varchar(100) DEFAULT NULL,
  `school` varchar(45) DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`ideducation`,`idapplicant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `applicantexperience` (
  `idexp` INT NOT NULL AUTO_INCREMENT,
  `idapplicant` int NOT NULL,
  `fromto` varchar(100) DEFAULT NULL,
  `jobtitle` varchar(45) DEFAULT NULL,
  `companyname` varchar(100) DEFAULT NULL,
  `description` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idexp`,`idapplicant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `applicantdocument` (
  `idapplicant` int(11) NOT NULL,
  `document` blob,
  PRIMARY KEY (`idapplicant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table to store document.';



CREATE TABLE `applicantdocument` (
  `idapplicant` int NOT NULL,
  `document` blob,
  PRIMARY KEY (`idapplicant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table to store document.';
