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
  `maxCall` INT(10),
  `maxMessage` INT(10),
  `maxLocalData` DOUBLE(32,2),
  `maxNationalData` DOUBLE(32,2),
  `balance` DOUBLE(32,2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `monthlyBill`;
CREATE TABLE `monthlyBill` (
  `cid` VARCHAR(60),
  `billYear` VARCHAR(60),
  `billMonth` VARCHAR(60),
  `cost` DOUBLE(32,2),
  PRIMARY KEY (`cid`, `billYear`, `billMonth`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
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

INSERT INTO package(pid,pcost,maxCall,maxMessage,maxLocalData,maxNationalData) VALUES ('pid-001',50,100,200,2048,0);
INSERT INTO package(pid,pcost,maxCall,maxMessage,maxLocalData,maxNationalData) VALUES ('pid-002',60,100,200,0,2048);
INSERT INTO package(pid,pcost,maxCall,maxMessage,maxLocalData,maxNationalData) VALUES ('pid-003',30,0,0,0,2048);

INSERT INTO client(cid,leftCall,leftMessage,leftLocalData,leftNationalData,maxCall,maxMessage,maxLocalData,maxNationalData,balance) VALUES ('cid-001',100,200,2048,0,100,200,2048,0,1000);
INSERT INTO client(cid,leftCall,leftMessage,leftLocalData,leftNationalData,maxCall,maxMessage,maxLocalData,maxNationalData,balance) VALUES ('cid-002',100,200,0,2048,100,200,0,2048,1000);
INSERT INTO client(cid,leftCall,leftMessage,leftLocalData,leftNationalData,maxCall,maxMessage,maxLocalData,maxNationalData,balance) VALUES ('cid-003',100,200,2048,2048,100,200,2048,2048,1000);
INSERT INTO client(cid,leftCall,leftMessage,leftLocalData,leftNationalData,maxCall,maxMessage,maxLocalData,maxNationalData,balance) VALUES ('cid-004',0,0,0,0,0,0,0,0,1000);

INSERT INTO `orders` (oid, pid, cid, startDate, endDate) VALUES ('oid-001','pid-001','cid-001','2018-10-01','1970-01-01');
INSERT INTO `orders` (oid, pid, cid, startDate, endDate) VALUES ('oid-002','pid-002','cid-002','2018-10-01','1970-01-01');
INSERT INTO `orders` (oid, pid, cid, startDate, endDate) VALUES ('oid-003','pid-001','cid-003','2018-09-01','1970-01-01');
INSERT INTO `orders` (oid, pid, cid, startDate, endDate) VALUES ('oid-004','pid-003','cid-003','2018-10-01','1970-01-01');

INSERT INTO monthlyBill (cid, billYear, billMonth, cost) VALUES ('cid-001','2018','10',50);
INSERT INTO monthlyBill (cid, billYear, billMonth, cost) VALUES ('cid-002','2018','10',60);
INSERT INTO monthlyBill (cid, billYear, billMonth, cost) VALUES ('cid-003','2018','09',50);
INSERT INTO monthlyBill (cid, billYear, billMonth, cost) VALUES ('cid-003','2018','10',80);
INSERT INTO monthlyBill (cid, billYear, billMonth, cost) VALUES ('cid-004','2018','10',0);