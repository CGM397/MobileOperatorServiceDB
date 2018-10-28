DROP DATABASE IF EXISTS `mobileOperatorService`;
CREATE DATABASE `mobileOperatorService` DEFAULT CHARACTER SET utf8;
USE `mobileOperatorService`;

DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `cid` VARCHAR(60) PRIMARY KEY,
  `leftCall` INT(10),
  `leftMessage` INT(10),
  `leftLocalData` DOUBLE(32,2),
  `leftNationalData` DOUBLE(32,2),
  `balance` DOUBLE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `monthlyBill`;
CREATE TABLE `monthlyBill` (
  `cid` VARCHAR(60),
  `billYear` VARCHAR(60),
  `billMonth` VARCHAR(60),
  `cost` DOUBLE,
  PRIMARY KEY (`cid`, `billYear`, `billMonth`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `oid` VARCHAR(60) PRIMARY KEY,
  `pid` VARCHAR(60),
  `cid` VARCHAR(60),
  `startDate` DATE,
  `endDate` DATE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `package`;
CREATE TABLE `package` (
  `pid` VARCHAR(60) PRIMARY KEY,
  `pcost` DOUBLE(32,2),
  `maxCall` INT(10),
  `maxMessage` INT(10),
  `maxLocalData` DOUBLE(32,2),
  `maxNationalData` DOUBLE(32,2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;