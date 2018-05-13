CREATE DATABASE `jobsdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `jobsdb`.`applicant` (
  `idapplicant` INT NOT NULL AUTO_INCREMENT,
  `submissiondate` DATE NOT NULL,
  `firstname` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  'objectif' VARCHAR(300) NULL,
  `mobile` VARCHAR(15) NULL,
  `email` VARCHAR(100) NULL,
  `address` VARCHAR(200) NULL,
  `city` VARCHAR(45) NULL,
  'language' VARCHAR(100) NULL,
  `postalcode` VARCHAR(10) NULL,
  `country` VARCHAR(45) NULL,
  'links' VARCHAR(300) NULL, 
  PRIMARY KEY (`idapplicant`, `submissiondate`))
COMMENT = 'Table for job applicant';


CREATE TABLE `applicanteducation` (
  `ideducation` INT NOT NULL AUTO_INCREMENT,
  `idapplicant` int NOT NULL,
  `from` int(4) DEFAULT NULL,
  `to` int(4) DEFAULT NULL,
  `school` varchar(45) DEFAULT NULL,
  `desc` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`ideducation`,`idapplicant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `applicantexperience` (
  `idexp` INT NOT NULL AUTO_INCREMENT,
  `idapplicant` int NOT NULL,
  `from` int(4) DEFAULT NULL,
  `to` int(4) DEFAULT NULL,
  `jobtitle` varchar(45) DEFAULT NULL,
  `companyname` varchar(100) DEFAULT NULL,
  `desc` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idexp`,`idapplicant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `applicantdocument` (
  `idapplicant` int(11) NOT NULL,
  `document` blob,
  PRIMARY KEY (`idapplicant`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Table to store document.';




