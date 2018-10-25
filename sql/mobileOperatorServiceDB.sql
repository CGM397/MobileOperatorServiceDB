DROP DATABASE IF EXISTS `mobileOperatorService`;
CREATE DATABASE `mobileOperatorService` DEFAULT CHARACTER SET utf8;
USE `mobileOperatorService`;

DROP TABLE IF EXISTS `Production`;
CREATE TABLE `Production` (
  `pid` VARCHAR(60),
  `pname` VARCHAR(60),
  `pdate` DATE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;