# ************************************************************
# Sequel Pro SQL dump
# Version 4500
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.6.25)
# Database: church_cradle_web_google
# Generation Time: 2016-11-13 11:19:01 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table accounts
# ------------------------------------------------------------

DROP TABLE IF EXISTS `accounts`;

CREATE TABLE `accounts` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  `church_id` int(11) NOT NULL,
  `modified_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modified_by` int(11) NOT NULL,
  `arreas` double NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;

INSERT INTO `accounts` (`id`, `balance`, `church_id`, `modified_ts`, `modified_by`, `arreas`, `status`)
VALUES
	(1,400,1,'2015-08-01 08:16:29',1,0,'A'),
	(2,0,1,'2015-08-01 08:16:30',1,0,'A'),
	(3,0,1,'2015-08-06 08:19:08',1,0,'A'),
	(4,0,1,'2015-08-06 08:24:24',1,0,'A'),
	(5,0,1,'2015-08-06 08:46:23',1,0,'A'),
	(6,300,1,'2015-08-25 22:15:43',1,0,'A'),
	(7,100,1,'2015-08-25 22:15:56',1,0,'A'),
	(8,50,1,'2015-08-25 22:16:10',1,0,'A'),
	(9,600,1,'2015-08-25 22:16:27',1,0,'A'),
	(10,0,1,'2015-09-06 10:31:23',1,0,'A'),
	(11,0,1,'2015-09-06 10:32:23',1,0,'A'),
	(12,0,1,'2015-09-14 10:03:23',1,0,'A');

/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table annoymous_donations
# ------------------------------------------------------------

DROP TABLE IF EXISTS `annoymous_donations`;

CREATE TABLE `annoymous_donations` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `church_id` int(11) NOT NULL,
  `amount` double NOT NULL,
  `created_date` datetime NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `annoymous_donations` WRITE;
/*!40000 ALTER TABLE `annoymous_donations` DISABLE KEYS */;

INSERT INTO `annoymous_donations` (`id`, `church_id`, `amount`, `created_date`, `status`)
VALUES
	(1,1,20,'2015-10-10 19:00:38','A'),
	(2,1,56,'2015-10-11 10:31:38','A'),
	(3,1,12,'2015-10-12 00:29:04','A'),
	(4,1,10,'2015-10-12 01:14:13','A'),
	(5,1,12,'2015-10-12 01:15:52','A'),
	(6,1,10,'2015-10-12 01:21:35','A'),
	(7,0,23,'2015-10-15 01:18:13','A'),
	(8,0,54,'2015-10-15 01:22:58','A'),
	(9,0,11,'2015-10-15 01:26:19','A'),
	(10,0,10,'2015-10-15 01:30:05','A');

/*!40000 ALTER TABLE `annoymous_donations` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table approve_modules
# ------------------------------------------------------------

DROP TABLE IF EXISTS `approve_modules`;

CREATE TABLE `approve_modules` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `module_id` int(11) NOT NULL,
  `created_ts` datetime NOT NULL,
  `modified_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `church_id` int(11) NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `approve_modules` WRITE;
/*!40000 ALTER TABLE `approve_modules` DISABLE KEYS */;

INSERT INTO `approve_modules` (`id`, `module_id`, `created_ts`, `modified_ts`, `church_id`, `status`)
VALUES
	(1,2,'2015-07-28 00:00:00','2015-07-28 08:48:37',1,'A'),
	(2,3,'2015-07-28 00:00:00','2015-07-28 08:48:55',1,'A'),
	(3,1,'2015-07-28 00:00:00','2015-07-28 08:49:15',1,'A'),
	(4,4,'2015-07-30 00:00:00','2015-07-30 23:10:34',1,'A'),
	(5,5,'2015-07-30 00:00:00','2015-07-30 23:11:08',1,'A'),
	(6,6,'2015-07-30 00:00:00','2015-07-30 23:11:25',1,'A'),
	(7,7,'2015-07-30 00:00:00','2015-07-30 23:11:46',1,'A'),
	(8,8,'2015-07-30 00:00:00','2015-07-30 23:12:02',1,'A'),
	(9,9,'2015-07-30 00:00:00','2015-07-30 23:12:18',1,'A'),
	(10,10,'2015-07-30 00:00:00','2015-07-30 23:12:39',1,'A'),
	(11,11,'2015-07-30 00:00:00','2015-09-15 08:44:41',1,'A');

/*!40000 ALTER TABLE `approve_modules` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table bible_readings
# ------------------------------------------------------------

DROP TABLE IF EXISTS `bible_readings`;

CREATE TABLE `bible_readings` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `description` text NOT NULL,
  `theme` text NOT NULL,
  `created_by` int(11) NOT NULL,
  `modified_by` int(11) NOT NULL,
  `church_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `created_ts` datetime NOT NULL,
  `approved_by` int(11) NOT NULL,
  `modified_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_editable` char(1) NOT NULL DEFAULT 'T',
  `status` char(1) NOT NULL DEFAULT 'P',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `bible_readings` WRITE;
/*!40000 ALTER TABLE `bible_readings` DISABLE KEYS */;

INSERT INTO `bible_readings` (`id`, `name`, `description`, `theme`, `created_by`, `modified_by`, `church_id`, `date`, `created_ts`, `approved_by`, `modified_ts`, `is_editable`, `status`)
VALUES
	(1,'Dr. Frank Gaize','Genesis 1:1-09','Getting closer to God and all it righteousness that follows',1,0,1,'2015-09-02 00:00:00','2015-08-12 00:00:00',1,'2015-08-26 14:58:04','F','A'),
	(2,'Samuel Anim','2 Corinthians 13:11','Having Inner Peace',1,0,1,'2015-09-09 00:00:00','2015-08-12 00:00:00',1,'2015-08-08 19:38:56','F','A'),
	(3,'Rev. Asante','Matthew 5:1-4','Love',1,1,1,'2015-09-09 00:00:00','2015-08-12 00:00:00',1,'2015-08-28 16:06:45','F','A'),
	(4,'Dr. Luke Asamoah','1 Samuel 2:1-10','Obedience',1,0,1,'2015-09-02 00:00:00','2015-08-19 00:00:00',1,'2015-08-28 16:06:49','F','A'),
	(5,'Dr. Max Payne Jnr','Genesis 3:8-12','Creation',1,0,1,'2015-09-02 00:00:00','2015-08-19 00:00:00',1,'2015-08-28 16:08:57','F','A'),
	(6,'Mrs. Sarah Barnes','Proverb 3:3-9','Worship',1,0,1,'2015-09-12 00:00:00','2015-08-19 00:00:00',1,'2015-08-28 16:12:47','F','A'),
	(7,'Pastor Ezra Richter','Exodus 4:5-7','Understanding',1,0,1,'2015-09-03 00:00:00','2015-08-19 00:00:00',1,'2015-08-28 16:14:56','F','A'),
	(8,'Dr. Frank Moses','Number 2:8-10','Waiting upon the Lord',1,0,1,'2015-09-01 00:00:00','2015-08-20 00:00:00',1,'2015-08-28 16:17:36','F','A'),
	(9,'Mrs. Shelle','1 John 1:1','In the beginning',1,0,1,'2015-09-02 00:00:00','2015-08-21 00:00:00',1,'2015-08-28 16:19:17','F','A'),
	(10,'Menase','John 1:1','Beginning is everything.',1,1,1,'2015-12-02 00:00:00','2015-09-16 17:12:01',1,'2015-09-16 17:12:25','F','A');

/*!40000 ALTER TABLE `bible_readings` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table billings
# ------------------------------------------------------------

DROP TABLE IF EXISTS `billings`;

CREATE TABLE `billings` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `balance` double NOT NULL,
  `church_id` int(11) NOT NULL,
  `modified_by` int(11) NOT NULL,
  `modified_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `billings` WRITE;
/*!40000 ALTER TABLE `billings` DISABLE KEYS */;

INSERT INTO `billings` (`id`, `balance`, `church_id`, `modified_by`, `modified_ts`, `status`)
VALUES
	(1,1200,1,1,'2015-08-26 13:09:32','A'),
	(2,1000,1,1,'2015-08-26 13:09:36','A'),
	(3,500,1,1,'2015-08-26 13:09:41','A'),
	(4,100,1,1,'2015-08-26 13:09:43','A'),
	(5,700,1,1,'2015-08-26 13:09:45','A'),
	(8,0,1,1,'2015-08-27 09:12:21','A'),
	(11,0,1,1,'2015-08-27 19:49:25','A'),
	(12,0,1,1,'2015-08-28 09:36:03','A'),
	(13,0,1,1,'2015-08-31 20:33:27','A'),
	(14,0,1,1,'2015-09-06 10:37:00','A'),
	(15,0,1,1,'2015-09-13 09:29:43','A'),
	(16,0,1,1,'2015-10-06 18:40:05','A'),
	(17,0,1,1,'2015-10-11 13:54:09','A'),
	(18,0,1,1,'2015-10-11 15:00:19','A'),
	(19,0,1,1,'2015-10-14 14:24:40','A'),
	(20,0,1,1,'2015-10-20 02:04:12','A'),
	(21,0,1,1,'2015-10-20 14:11:58','A'),
	(22,0,1,1,'2015-10-23 18:52:47','A'),
	(23,0,1,1,'2015-10-23 19:00:59','A'),
	(24,0,1,1,'2015-10-26 07:34:05','A'),
	(25,0,1,1,'2015-10-30 14:44:43','A'),
	(26,0,1,1,'2015-11-12 16:49:07','A'),
	(27,0,1,1,'2015-12-01 09:11:47','A'),
	(28,0,1,1,'2015-12-01 09:14:42','A'),
	(29,0,1,1,'2016-08-23 08:40:24','A'),
	(30,0,1,1,'2016-10-19 18:21:45','A'),
	(31,0,1,1,'2016-10-22 09:54:47','A'),
	(32,0,1,1,'2016-10-22 17:10:11','A'),
	(33,0,1,1,'2016-11-10 15:39:02','A');

/*!40000 ALTER TABLE `billings` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table birthday_messages
# ------------------------------------------------------------

DROP TABLE IF EXISTS `birthday_messages`;

CREATE TABLE `birthday_messages` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `church_id` int(11) NOT NULL,
  `message` text NOT NULL,
  `created_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `birthday_messages` WRITE;
/*!40000 ALTER TABLE `birthday_messages` DISABLE KEYS */;

INSERT INTO `birthday_messages` (`id`, `member_id`, `church_id`, `message`, `created_ts`, `status`)
VALUES
	(1,1,1,'Happy Birthday','2015-10-16 17:59:50','A'),
	(2,1,1,'Mt. Olivet and the entire congregation wishes you a Happy Birthday. God richly bless and strengthens you for more years to come.','2015-10-20 00:59:30','A');

/*!40000 ALTER TABLE `birthday_messages` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table birthday_push_verifier
# ------------------------------------------------------------

DROP TABLE IF EXISTS `birthday_push_verifier`;

CREATE TABLE `birthday_push_verifier` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `church_id` int(11) NOT NULL,
  `sms_count` double NOT NULL,
  `created_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `birthday_push_verifier` WRITE;
/*!40000 ALTER TABLE `birthday_push_verifier` DISABLE KEYS */;

INSERT INTO `birthday_push_verifier` (`id`, `church_id`, `sms_count`, `created_ts`, `status`)
VALUES
	(1,0,0,'2015-10-30 05:34:19','A'),
	(2,0,0,'2015-10-31 05:34:28','A'),
	(3,0,0,'2015-11-01 05:34:28','A'),
	(4,0,0,'2015-11-02 05:34:36','A'),
	(5,0,0,'2015-11-03 05:34:36','A'),
	(6,0,0,'2015-11-04 05:32:23','A'),
	(7,0,0,'2015-11-05 05:32:31','A'),
	(8,0,0,'2015-11-06 05:32:38','A'),
	(9,0,0,'2015-11-07 05:32:38','A'),
	(10,0,0,'2015-11-08 05:32:46','A'),
	(11,0,0,'2015-11-09 05:32:47','A'),
	(12,0,0,'2015-11-10 05:32:55','A'),
	(13,0,0,'2015-11-11 05:33:02','A'),
	(14,0,0,'2015-11-12 05:33:12','A'),
	(15,0,0,'2015-11-13 06:45:42','A'),
	(16,0,0,'2015-11-14 06:45:42','A'),
	(17,0,0,'2015-11-15 06:45:43','A'),
	(18,0,0,'2015-11-16 06:45:43','A'),
	(19,0,0,'2015-11-17 06:45:47','A'),
	(20,0,0,'2015-11-18 06:45:54','A'),
	(21,0,0,'2015-11-19 06:45:55','A'),
	(22,0,0,'2015-11-20 06:22:21','A'),
	(23,0,0,'2015-11-21 06:22:22','A'),
	(24,0,0,'2015-11-22 06:22:22','A'),
	(25,0,0,'2015-11-23 06:22:23','A'),
	(26,0,0,'2015-11-24 06:22:24','A'),
	(27,0,0,'2015-11-25 06:22:34','A'),
	(28,0,0,'2015-11-26 06:22:35','A'),
	(29,0,0,'2015-11-27 06:22:35','A'),
	(30,0,0,'2015-11-28 06:22:36','A'),
	(31,0,0,'2015-11-29 06:22:36','A'),
	(32,0,0,'2015-11-30 06:22:37','A'),
	(33,0,0,'2015-12-01 06:22:38','A'),
	(34,0,0,'2015-12-02 06:22:38','A'),
	(35,0,0,'2015-12-03 06:22:39','A');

/*!40000 ALTER TABLE `birthday_push_verifier` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table churches
# ------------------------------------------------------------

DROP TABLE IF EXISTS `churches`;

CREATE TABLE `churches` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `display` text NOT NULL,
  `modules` text NOT NULL,
  `approve_module_limit` int(11) NOT NULL DEFAULT '1',
  `member_limit` double NOT NULL,
  `payment_id` int(11) NOT NULL,
  `annual_fee` double NOT NULL,
  `price_per_sms` double NOT NULL,
  `sms_left` double NOT NULL,
  `expire_date` date NOT NULL,
  `package` char(1) NOT NULL DEFAULT 'F',
  `renewal_date` date NOT NULL,
  `birth_push` char(1) NOT NULL DEFAULT 'Y',
  `tithe_push` char(1) NOT NULL DEFAULT 'Y',
  `modified_by` int(11) NOT NULL,
  `status` char(1) DEFAULT 'A',
  `default_group_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `churches` WRITE;
/*!40000 ALTER TABLE `churches` DISABLE KEYS */;

INSERT INTO `churches` (`id`, `name`, `display`, `modules`, `approve_module_limit`, `member_limit`, `payment_id`, `annual_fee`, `price_per_sms`, `sms_left`, `expire_date`, `package`, `renewal_date`, `birth_push`, `tithe_push`, `modified_by`, `status`, `default_group_id`)
VALUES
	(1,'Mt. Olivet Methodist Society','mt. olivet','1,2,3,4,5,6,7,8,9,10',10,10000000,1,2000,0.12,0,'2016-12-29','F','2015-10-29','N','N',1,'A',17);

/*!40000 ALTER TABLE `churches` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table classes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `classes`;

CREATE TABLE `classes` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `leader_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `church_id` int(11) NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;

INSERT INTO `classes` (`id`, `leader_id`, `account_id`, `church_id`, `status`)
VALUES
	(1,1,6,1,'A');

/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table dollar_rates
# ------------------------------------------------------------

DROP TABLE IF EXISTS `dollar_rates`;

CREATE TABLE `dollar_rates` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `created_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cedis` double NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `dollar_rates` WRITE;
/*!40000 ALTER TABLE `dollar_rates` DISABLE KEYS */;

INSERT INTO `dollar_rates` (`id`, `created_ts`, `cedis`, `status`)
VALUES
	(5,'2015-10-29 03:46:02',3.828197,'A');

/*!40000 ALTER TABLE `dollar_rates` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table donations
# ------------------------------------------------------------

DROP TABLE IF EXISTS `donations`;

CREATE TABLE `donations` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `description` text NOT NULL,
  `amount` double NOT NULL,
  `church_id` int(11) NOT NULL,
  `modified_by` int(11) NOT NULL,
  `created_ts` datetime NOT NULL,
  `created_by` int(11) NOT NULL,
  `approved_by` int(11) NOT NULL,
  `modified_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` char(1) NOT NULL DEFAULT 'P',
  `is_editable` char(1) NOT NULL DEFAULT 'T',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `donations` WRITE;
/*!40000 ALTER TABLE `donations` DISABLE KEYS */;

INSERT INTO `donations` (`id`, `member_id`, `description`, `amount`, `church_id`, `modified_by`, `created_ts`, `created_by`, `approved_by`, `modified_ts`, `status`, `is_editable`)
VALUES
	(1,1,'Someone generously gave to help the church',1000,1,0,'2015-08-01 00:00:00',1,1,'2015-08-08 20:46:03','A','F');

/*!40000 ALTER TABLE `donations` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table e_announcements
# ------------------------------------------------------------

DROP TABLE IF EXISTS `e_announcements`;

CREATE TABLE `e_announcements` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `time` text NOT NULL,
  `date` datetime NOT NULL,
  `venue` text NOT NULL,
  `church_id` int(11) NOT NULL,
  `approved_by` int(11) NOT NULL,
  `modified_by` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_ts` datetime NOT NULL,
  `modified_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` char(1) NOT NULL DEFAULT 'P',
  `is_editable` char(1) NOT NULL DEFAULT 'T',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `e_announcements` WRITE;
/*!40000 ALTER TABLE `e_announcements` DISABLE KEYS */;

INSERT INTO `e_announcements` (`id`, `description`, `time`, `date`, `venue`, `church_id`, `approved_by`, `modified_by`, `created_by`, `created_ts`, `modified_ts`, `status`, `is_editable`)
VALUES
	(1,'Games competitions. Featured games, thug of war and etc','12pm','2015-08-12 00:00:00','Methodist University Park',1,0,1,1,'2015-08-01 00:00:00','2015-08-28 11:34:19','D','T'),
	(2,'Car wash and petit party','10am','2015-09-01 00:00:00','Olivet Grounds',1,1,0,1,'2015-08-01 00:00:00','2015-08-28 11:29:15','P','T'),
	(3,'Blood Donation and health screening','9am','2015-10-07 00:00:00','Olivet Grounds',1,1,0,1,'2015-08-01 00:00:00','2015-08-06 08:57:55','A','F'),
	(4,'Come and let\'s all have fun','12 pm','2015-12-24 00:00:00','Methodist park',1,1,1,1,'2015-08-29 01:17:56','2015-08-29 01:20:14','A','F'),
	(5,'Keeping fit for our God. Let\'s exercise to the glory of God.','12 pm','2015-12-25 00:00:00','Dansoman keepfit club',1,1,1,1,'2015-08-29 01:26:14','2015-08-29 01:26:26','A','F'),
	(6,'CAR WASH','8:00am','2015-10-17 00:00:00','Mt. olivet',1,1,1,1,'2015-10-11 14:09:22','2015-10-11 14:09:54','A','F'),
	(7,'Fun Games','10:00am','2015-11-30 00:00:00','Mucg',1,1,1,1,'2015-10-11 15:14:22','2015-10-11 15:15:56','A','F');

/*!40000 ALTER TABLE `e_announcements` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table education_list
# ------------------------------------------------------------

DROP TABLE IF EXISTS `education_list`;

CREATE TABLE `education_list` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` text,
  `value` text,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `education_list` WRITE;
/*!40000 ALTER TABLE `education_list` DISABLE KEYS */;

INSERT INTO `education_list` (`id`, `name`, `value`, `status`)
VALUES
	(1,'Junior Secondary School','JSS','A'),
	(2,'Senior Secondary School','SSS','A'),
	(3,'Diploma','DIP','A'),
	(4,'Higher National Diploma','HND','A'),
	(5,'Bachelors','BAC','A'),
	(6,'Masters','MASTERS','A'),
	(7,'Philosopher','PhD','A'),
	(8,'Professor','PROF','A');

/*!40000 ALTER TABLE `education_list` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table entry_modules
# ------------------------------------------------------------

DROP TABLE IF EXISTS `entry_modules`;

CREATE TABLE `entry_modules` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `module_id` int(11) NOT NULL,
  `created_ts` datetime NOT NULL,
  `modified_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `church_id` int(11) NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `entry_modules` WRITE;
/*!40000 ALTER TABLE `entry_modules` DISABLE KEYS */;

INSERT INTO `entry_modules` (`id`, `module_id`, `created_ts`, `modified_ts`, `church_id`, `status`)
VALUES
	(1,1,'2015-08-27 00:00:00','2015-08-27 10:32:10',1,'A'),
	(2,2,'2015-08-27 00:00:00','2015-08-27 10:32:19',1,'A'),
	(4,4,'2015-08-27 00:00:00','2015-08-27 10:32:41',1,'A'),
	(5,5,'2015-08-27 00:00:00','2015-08-27 10:32:51',1,'A'),
	(6,6,'2015-08-27 00:00:00','2015-08-27 10:33:00',1,'A'),
	(7,7,'2015-08-27 00:00:00','2015-08-27 10:33:09',1,'A'),
	(8,8,'2015-08-27 00:00:00','2015-08-27 10:33:17',1,'A'),
	(9,9,'2015-08-27 00:00:00','2015-08-27 10:33:26',1,'A'),
	(10,10,'2015-08-27 00:00:00','2015-08-27 10:33:36',1,'A'),
	(11,11,'2015-08-27 00:00:00','2015-09-15 08:53:09',1,'A');

/*!40000 ALTER TABLE `entry_modules` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table f_announcements
# ------------------------------------------------------------

DROP TABLE IF EXISTS `f_announcements`;

CREATE TABLE `f_announcements` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `description` text NOT NULL,
  `age` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `time` text NOT NULL,
  `venue` text NOT NULL,
  `church_id` int(11) NOT NULL,
  `created_ts` datetime NOT NULL,
  `modified_by` int(11) NOT NULL,
  `approved_by` int(11) NOT NULL,
  `avatar` mediumblob,
  `modified_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` char(1) NOT NULL DEFAULT 'P',
  `is_editable` char(1) NOT NULL DEFAULT 'T',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table forgot_passwords
# ------------------------------------------------------------

DROP TABLE IF EXISTS `forgot_passwords`;

CREATE TABLE `forgot_passwords` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `msisdn` text NOT NULL,
  `code` text NOT NULL,
  `created_ts` datetime NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `forgot_passwords` WRITE;
/*!40000 ALTER TABLE `forgot_passwords` DISABLE KEYS */;

INSERT INTO `forgot_passwords` (`id`, `member_id`, `msisdn`, `code`, `created_ts`, `status`)
VALUES
	(1,1,'+233244960321','3','2015-10-08 11:13:47','A'),
	(2,1,'+233244960321','3','2015-10-08 11:15:59','A'),
	(3,1,'+233244960321','q','2015-10-08 11:20:36','A'),
	(4,1,'+233244960321','nmm2ashjco','2015-10-08 11:24:42','A'),
	(5,1,'+233244960321','37a2ivps','2015-10-08 11:26:29','A'),
	(6,1,'+233244960321','h8hce9vc','2015-10-08 11:41:57','A'),
	(7,1,'+233244960321','fg52kfqs','2015-10-08 11:49:20','A'),
	(8,1,'+233244960321','tcl61kuq','2015-10-08 11:50:48','A'),
	(9,1,'+233244960321','mt4uvlbj','2015-10-08 11:54:50','A'),
	(10,1,'+233244960321','ital0vsb','2015-10-08 11:55:54','A'),
	(11,1,'+233244960321','m0ktl623','2015-10-08 12:12:28','A'),
	(13,1,'233244960321','mkcnk9uc','2015-10-14 15:01:08','A'),
	(14,1,'233244960321','39vai8j8','2015-10-14 15:08:29','A'),
	(15,1,'233244960321','cupkkgln','2015-10-20 21:15:42','A');

/*!40000 ALTER TABLE `forgot_passwords` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table m_announcements
# ------------------------------------------------------------

DROP TABLE IF EXISTS `m_announcements`;

CREATE TABLE `m_announcements` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `description` text NOT NULL,
  `m_name` text NOT NULL,
  `f_name` text NOT NULL,
  `m_occupation` text NOT NULL,
  `f_occupation` text NOT NULL,
  `time` text NOT NULL,
  `church_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `m_avatar` mediumblob,
  `f_avatar` mediumblob,
  `approved_by` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_ts` datetime NOT NULL,
  `modified_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `venue` text NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'P',
  `is_editable` char(1) NOT NULL DEFAULT 'T',
  `modified_by` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



# Dump of table members
# ------------------------------------------------------------

DROP TABLE IF EXISTS `members`;

CREATE TABLE `members` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fname` text NOT NULL,
  `lname` text NOT NULL,
  `email` text NOT NULL,
  `msisdn` text NOT NULL,
  `avatar` mediumblob,
  `password` text NOT NULL,
  `church_id` int(11) NOT NULL,
  `billing_id` int(11) NOT NULL,
  `is_admin` char(1) NOT NULL DEFAULT 'N',
  `can_view_payments` char(1) DEFAULT 'N',
  `can_sms` char(1) NOT NULL DEFAULT 'N',
  `created_ts` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modified_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `approved_by` int(11) NOT NULL,
  `modified_by` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `entry_modules` text NOT NULL,
  `approve_modules` text NOT NULL,
  `class_id` int(11) NOT NULL,
  `organisations` text NOT NULL,
  `tithes` text NOT NULL,
  `occupation` text NOT NULL,
  `address` text NOT NULL,
  `dob` date NOT NULL,
  `gender` char(1) NOT NULL DEFAULT '',
  `prefs` text,
  `employer` text NOT NULL,
  `commence_year` text,
  `nationality` text,
  `region` text,
  `kin_msisdn` text,
  `kin` text,
  `relation_status` char(1) DEFAULT 'S',
  `spouse` text,
  `hometown` text,
  `baptism_date` text,
  `baptism_location` text,
  `baptism_minister` text,
  `previous_church` text,
  `edu_level` text,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;

INSERT INTO `members` (`id`, `fname`, `lname`, `email`, `msisdn`, `avatar`, `password`, `church_id`, `billing_id`, `is_admin`, `can_view_payments`, `can_sms`, `created_ts`, `modified_ts`, `approved_by`, `modified_by`, `created_by`, `entry_modules`, `approve_modules`, `class_id`, `organisations`, `tithes`, `occupation`, `address`, `dob`, `gender`, `prefs`, `employer`, `commence_year`, `nationality`, `region`, `kin_msisdn`, `kin`, `relation_status`, `spouse`, `hometown`, `baptism_date`, `baptism_location`, `baptism_minister`, `previous_church`, `edu_level`, `status`)
VALUES
	(1,'Fetz','Selby','fetz.selby@gmail.com','+233244960321',X'414D496676393641324C63723635424F613676324F6361354B6F345570444E61714374565F41717957485057626866776B64304462464834514F65304F50496C6E64454E6269594F5946797765506A376C4E494E387A77433658686A4D4530492D6873504B4B797172616F504B362D51466F4A7176743362683575786D6F573567525F535365784758535F453767697A413130647A50456C63696E357733596774387435506A5066786542484C7A536A7476787A623230','245d08dd66a52f59d05b9c46046d7699dc79cdffdd1657517d6bd3c78c6b388a',1,1,'Y','Y','Y','2015-12-02 08:14:45','2015-12-02 08:14:45',1,1,1,'1,4,5,6,7,8,9,11','2,3,4,5,6,7,8,9,11',1,'1,2,3,4,17','','Software Engineer','No.1, 2nd Mama Kweku Link, East Airport','1985-12-25','M','He\'s a good person','NSA','2010',NULL,NULL,NULL,NULL,'S',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'A'),
	(19,'Rt. Rev John','A.Y Adubah','','+233208347635',X'414D49667639374966435231365134574C71742D6F4F78776232534952555134357262374A6976717A4E61343277436538484D5043743665545852664743776234507736533763666965664C4F3742624C5A7944566734386C554A757A3567777374516D5A30496E7251506B38637970436C48476E7A30714D6B535A4C754A5431326C796755396D4170706268463770483965715657556E7879516732782D344742515A30464A49414D6F51745F616D485A7655724459','9410525c57bbfd2de93d6cfbe9769524532eed8dc6487a404202ace76237d4b0',1,23,'Y','Y','Y','2015-12-01 08:33:37','2015-12-01 08:33:37',1,1,1,'','2,3,4,5,6,7,8,9,11',1,'1,2,3,4,14,15,16,17','','Rev minister','Dansoman','1953-10-23','M','','Methodist church ghana','2000',NULL,NULL,NULL,NULL,'S',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'A'),
	(20,'Ed','Selby','','+233244387718',X'414D4966763937585F746F324C585064765F783847797A4F786C5234437252575177644A79567A5A524130526E6957535A474A52596E7A2D6452396E4B4854457A77324B6D6B4B637769705A77755F33512D64554C4F315F32494657306E5650526B6348744E6F6154486D3765426F4F4C4944446E5F4E4C4B674C34515337394F31475A666C344B3437596C746E754F426B7744576F625A65594C48456838447876424E456E347443617A6C4979397035633054723341','3e97341f1b8c2c05f616fb3fe9dd38902a627b49a6dc4fa9296d1b229b9eaf83',1,26,'N','N','N','2015-12-01 08:33:46','2015-12-01 08:33:46',1,1,1,'','',1,'1,3,17','','Engineer','McCarthy Hill','1990-10-26','M','Joined the church since 2010','','1999',NULL,NULL,NULL,NULL,'S',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'A'),
	(21,'Nas','Sels','','+233244000000',X'414D496676393452316339424A3839584A4344456B7A41644A7351743868706C59596E523941457039304A50594D694569546252425A38497A4467427A62354B6952514B5767707246654C74647972646455344F4C4B57494E725171724B6734756467435137524E737262715344704335414B5A4B6B5A554376784C506B495139353377364D3241743932464E345251356855464C353644314150617647305755465F49532D715534774D6C4F5F614B55426637516173','403056269b8eb7678865ed16dc78c024da68219c437f2c04cd302ab732588fe3',1,27,'N','N','N','2015-12-01 09:13:02','2015-12-01 09:13:02',0,1,1,'','',1,'17','','','Dansoman','2001-12-01','M','','','1999',NULL,NULL,NULL,NULL,'S',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'D'),
	(22,'Dan','Francis','','+233504298056',X'414D49667639353164484A3769774C61787A69495A6F424F45734F545671375A2D654C3351532D2D6C426C4859546F4D54554B7663556A476644304A784E36594878326B307438786A382D35377963634D72676247684E7452466656486B7558765F75686B53434D50434F2D355667565242595853306F554E6355472D53417756687732716931626336694872326A756741687033736F49556D6B72684475655937576F4E4E6B31666B61485837433944475461767A51','9e1c27ad609c4bf83691d5aadfd705a596d6d95a1dab612cab5d8cc72966d37e',1,28,'N','N','N','2015-12-01 09:15:32','2015-12-01 09:15:32',1,1,1,'','',1,'17','','','Airport','1980-12-06','M','','','2003',NULL,NULL,NULL,NULL,'S',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'A'),
	(23,'Justin','Mac','justin@gmail.com','+233244960323',X'416B485F5F6E694735304D5836316276485955764877','66f1eeb8e0614640ae0234dff234cc86abab9039e61b02d070dbc2a2cc6b9f3d',1,29,'N','N','N','2016-08-23 08:40:24','2016-08-23 08:40:24',0,1,1,'6','4',1,'2,17','','Engineer','Dansoman','1990-12-12','M','He\'s very good','Aviat','2016',NULL,NULL,NULL,NULL,'S',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'P'),
	(24,'Ben','Frank','','+23324567890',X'4F5F4D5F526E723434395A616250766D6A3947627077','d2c79d5daab9add73b8465afc9b701b779bfaba72aaac2d3d2b3450f46754b11',1,30,'N','N','N','2016-10-19 18:23:02','2016-10-19 18:23:02',1,1,1,'','',1,'1,17','','Engineer','Dansoman','1990-12-12','M','Nothing to enter','Mtn','2015',NULL,NULL,NULL,NULL,'S',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'A'),
	(25,'Justin','Blake','jus@gmail.com','+23324567785',X'687474703A2F2F302E302E302E303A383838382F5F61682F696D672F4557484F594D64476B454478646E7A696C4E666535413D73333530','211f0834ba50cc9f46959dc862d167cbddbe8dc147824a04ce3f97fcf69ff1bd',1,31,'N','N','N','2016-10-22 09:54:47','2016-10-22 09:54:47',0,1,1,'','',1,'1,17','','Teacher','Airport Residential','1980-12-12','M','','University College','2012',NULL,NULL,NULL,NULL,'S',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'P'),
	(26,'Dude','Mac','','+23345667789',X'687474703A2F2F302E302E302E303A383838382F5F61682F696D672F332D4450714A7A794C5F5867746E50387A4D4D4558513D73333530','41558e77621f201c93fde6cb6dab1ea9a79f5e0b540d201b3e84e62e2b415df5',1,32,'N','N','N','2016-10-22 17:10:11','2016-10-22 17:10:11',0,1,1,'','',1,'1,17','','Lecturer','Asokwa','1990-12-12','M','','Gtuc','1990',NULL,NULL,NULL,NULL,'S',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'P');

/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table modules
# ------------------------------------------------------------

DROP TABLE IF EXISTS `modules`;

CREATE TABLE `modules` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `created_ts` datetime NOT NULL,
  `modified_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `expire_date` date NOT NULL,
  `church_id` int(11) NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `modules` WRITE;
/*!40000 ALTER TABLE `modules` DISABLE KEYS */;

INSERT INTO `modules` (`id`, `name`, `created_ts`, `modified_ts`, `expire_date`, `church_id`, `status`)
VALUES
	(1,'MARRIAGE ANNOUNCEMENTS','2015-07-27 00:00:00','2015-07-30 23:07:17','2017-07-27',1,'A'),
	(2,'LIBRARY','2015-07-27 00:00:00','2015-10-12 13:58:08','2015-10-10',1,'A'),
	(3,'SMS','2015-07-27 00:00:00','2015-07-28 08:47:43','2017-07-27',1,'A'),
	(4,'MEMBERS','2015-07-28 00:00:00','2015-07-28 08:47:37','2017-07-28',1,'A'),
	(5,'OFFERINGS','2015-07-30 00:00:00','2015-07-30 23:04:58','2017-07-30',1,'A'),
	(6,'TITHES','2015-07-30 00:00:00','2015-07-30 23:05:58','2017-07-30',1,'A'),
	(7,'BIBLE READINGS','2015-07-30 00:00:00','2015-07-30 23:06:53','2017-07-30',1,'A'),
	(8,'FUNERAL ANNOUNCEMENTS','2015-07-30 00:00:00','2015-07-30 23:07:52','2017-07-30',1,'A'),
	(9,'EVENTS ANNOUNCEMENTS','2015-07-30 00:00:00','2015-07-30 23:08:45','2017-07-30',1,'A'),
	(10,'DONATIONS','2015-07-30 00:00:00','2015-10-12 14:51:41','2015-10-10',1,'A'),
	(11,'SPECIAL OFFERINGS','2015-07-30 00:00:00','2015-09-15 08:43:53','2017-07-30',1,'A');

/*!40000 ALTER TABLE `modules` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table my_offerings
# ------------------------------------------------------------

DROP TABLE IF EXISTS `my_offerings`;

CREATE TABLE `my_offerings` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `church_id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  `amount` double NOT NULL,
  `created_date` date NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `my_offerings` WRITE;
/*!40000 ALTER TABLE `my_offerings` DISABLE KEYS */;

INSERT INTO `my_offerings` (`id`, `church_id`, `member_id`, `amount`, `created_date`, `status`)
VALUES
	(1,1,1,2000,'2015-10-07','A');

/*!40000 ALTER TABLE `my_offerings` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table offerings
# ------------------------------------------------------------

DROP TABLE IF EXISTS `offerings`;

CREATE TABLE `offerings` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `description` text,
  `amount` double NOT NULL,
  `church_id` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `approved_by` int(11) NOT NULL,
  `created_ts` datetime NOT NULL,
  `modified_by` int(11) NOT NULL,
  `modified_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` char(1) NOT NULL DEFAULT 'P',
  `is_editable` char(1) NOT NULL DEFAULT 'T',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `offerings` WRITE;
/*!40000 ALTER TABLE `offerings` DISABLE KEYS */;

INSERT INTO `offerings` (`id`, `description`, `amount`, `church_id`, `created_by`, `approved_by`, `created_ts`, `modified_by`, `modified_ts`, `status`, `is_editable`)
VALUES
	(1,'Akwasidai Harvest',12000,1,1,1,'2015-08-02 00:00:00',1,'2015-08-06 08:55:39','A','F'),
	(2,'Women Harvest',800,1,1,0,'2015-10-02 00:00:00',1,'2015-08-28 11:33:49','D','T'),
	(3,'Children Service',1500,1,1,0,'2015-08-22 00:00:00',1,'2015-08-01 08:20:03','P','T'),
	(4,'Adult Service',2200,1,1,1,'2015-08-22 00:00:00',1,'2015-08-06 08:56:41','A','F'),
	(5,'Building Funds',10000,1,1,0,'2015-08-22 00:00:00',1,'2015-08-01 08:20:05','P','T'),
	(6,'Aseda Kese',2300,1,1,1,'2015-08-29 09:42:18',1,'2015-08-29 09:43:42','A','F'),
	(7,'Mens Harvest',3000,1,1,1,'2015-10-29 07:45:49',1,'2015-10-29 07:46:15','A','F');

/*!40000 ALTER TABLE `offerings` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table online_donation_payments
# ------------------------------------------------------------

DROP TABLE IF EXISTS `online_donation_payments`;

CREATE TABLE `online_donation_payments` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `token` text NOT NULL,
  `created_ts` datetime NOT NULL,
  `church_id` int(11) NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `online_donation_payments` WRITE;
/*!40000 ALTER TABLE `online_donation_payments` DISABLE KEYS */;

INSERT INTO `online_donation_payments` (`id`, `amount`, `token`, `created_ts`, `church_id`, `status`)
VALUES
	(1,20,'test_b5a038ad0b','2015-10-10 19:00:38',1,'A'),
	(2,56,'test_320fe3148c','2015-10-11 10:31:38',1,'A'),
	(3,12,'test_8056dfc95f','2015-10-12 00:29:04',1,'A'),
	(4,10,'test_d416238161','2015-10-12 01:14:13',1,'A'),
	(5,12,'test_e67cbd9335','2015-10-12 01:15:52',1,'A'),
	(6,10,'test_04ca63cd15','2015-10-12 01:21:35',1,'A'),
	(7,23,'test_5221f2be11','2015-10-15 01:18:13',0,'A'),
	(8,54,'test_5ef690cd17','2015-10-15 01:22:58',0,'A'),
	(9,11,'test_f32b93db52','2015-10-15 01:26:19',0,'A'),
	(10,10,'test_e8c633bb72','2015-10-15 01:30:05',0,'A');

/*!40000 ALTER TABLE `online_donation_payments` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table online_offering_payments
# ------------------------------------------------------------

DROP TABLE IF EXISTS `online_offering_payments`;

CREATE TABLE `online_offering_payments` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `amount` double DEFAULT '0',
  `token` text,
  `created_ts` datetime NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  `church_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `online_offering_payments` WRITE;
/*!40000 ALTER TABLE `online_offering_payments` DISABLE KEYS */;

INSERT INTO `online_offering_payments` (`id`, `member_id`, `amount`, `token`, `created_ts`, `status`, `church_id`)
VALUES
	(1,1,2000,NULL,'2015-10-07 09:27:03','A',1),
	(2,1,12,'test_4fc461fddc','2015-10-12 00:30:24','A',1),
	(3,1,12,'test_89115f7728','2015-10-12 01:16:47','A',1),
	(4,1,54,'test_03f4738baa','2015-10-12 01:22:28','A',1),
	(6,1,20,'test_7d80ee680a','2015-10-15 01:32:08','A',1);

/*!40000 ALTER TABLE `online_offering_payments` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table online_tithe_payments
# ------------------------------------------------------------

DROP TABLE IF EXISTS `online_tithe_payments`;

CREATE TABLE `online_tithe_payments` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `token` text,
  `created_ts` datetime NOT NULL,
  `amount` double DEFAULT '0',
  `status` char(1) NOT NULL DEFAULT 'A',
  `church_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `online_tithe_payments` WRITE;
/*!40000 ALTER TABLE `online_tithe_payments` DISABLE KEYS */;

INSERT INTO `online_tithe_payments` (`id`, `member_id`, `token`, `created_ts`, `amount`, `status`, `church_id`)
VALUES
	(4,1,'test_918225cfc1','2015-10-07 10:01:19',100,'A',1),
	(5,1,'test_fb6f27dd4f','2015-10-07 10:02:51',120,'A',1),
	(6,1,'test_c488806293','2015-10-12 00:29:51',12,'A',1),
	(7,1,'test_96d01a1c7e','2015-10-15 01:33:33',56,'A',1),
	(8,1,'test_df8f17ca55','2015-10-17 22:43:16',30,'A',1);

/*!40000 ALTER TABLE `online_tithe_payments` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table organisations
# ------------------------------------------------------------

DROP TABLE IF EXISTS `organisations`;

CREATE TABLE `organisations` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `account_id` int(11) NOT NULL,
  `leader_id` int(11) NOT NULL,
  `church_id` int(11) NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `organisations` WRITE;
/*!40000 ALTER TABLE `organisations` DISABLE KEYS */;

INSERT INTO `organisations` (`id`, `name`, `account_id`, `leader_id`, `church_id`, `status`)
VALUES
	(1,'Mens Fellowship',2,1,1,'A'),
	(2,'Choir',3,1,1,'A'),
	(3,'Olivet Voices',4,1,1,'A'),
	(4,'Multimedia',5,1,1,'A'),
	(14,'Singing Band',1,1,1,'A'),
	(15,'Women fellowship',10,1,1,'A'),
	(16,'Boys\' and girls\' brigade',11,1,1,'A'),
	(17,'All Members',0,0,1,'A');

/*!40000 ALTER TABLE `organisations` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table owner_payment_details
# ------------------------------------------------------------

DROP TABLE IF EXISTS `owner_payment_details`;

CREATE TABLE `owner_payment_details` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mp_master_key` text NOT NULL,
  `mp_private_key` text NOT NULL,
  `mp_public_key` text NOT NULL,
  `mp_token` text NOT NULL,
  `address` text NOT NULL,
  `msisdn` text NOT NULL,
  `tagline` text NOT NULL,
  `name` text NOT NULL,
  `website` text NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `owner_payment_details` WRITE;
/*!40000 ALTER TABLE `owner_payment_details` DISABLE KEYS */;

INSERT INTO `owner_payment_details` (`id`, `mp_master_key`, `mp_private_key`, `mp_public_key`, `mp_token`, `address`, `msisdn`, `tagline`, `name`, `website`, `status`)
VALUES
	(1,'f0ee61c1-1639-4431-bc57-4b8c4a682291','live_private_gAZ2En7uvRB4kK9dDtbPoS1XVOI','live_public_NfiJBT9bl9yP8EGbZKlfuMGjb6g','7937460f2e9443422337','No.1 2nd Mama Kweku Street, East - Airport','0244960321','Sels Labs. churchcradle-lite.appspot.com','Church Cradle | Web Application','churchcradle-lite.appspot.com','A');

/*!40000 ALTER TABLE `owner_payment_details` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table owner_payment_tracker
# ------------------------------------------------------------

DROP TABLE IF EXISTS `owner_payment_tracker`;

CREATE TABLE `owner_payment_tracker` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `church_id` int(11) NOT NULL,
  `created_ts` datetime NOT NULL,
  `type` char(1) NOT NULL DEFAULT '',
  `token` text NOT NULL,
  `payee_id` int(11) NOT NULL,
  `external_token` text NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'P',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `owner_payment_tracker` WRITE;
/*!40000 ALTER TABLE `owner_payment_tracker` DISABLE KEYS */;

INSERT INTO `owner_payment_tracker` (`id`, `amount`, `church_id`, `created_ts`, `type`, `token`, `payee_id`, `external_token`, `status`)
VALUES
	(11,4.2,1,'2015-10-28 19:30:56','S','cgf9eqo3cdrimk9gif1rl87ujm',1,'','P'),
	(12,4.2,1,'2015-10-28 19:31:22','S','8ui10muboma12vsj071kh7qa13',1,'test_940f974954','A'),
	(13,2.4,1,'2015-10-28 19:36:26','S','tp08huudd0588qhsejdr0ugoqm',1,'test_fac9814c6b','A'),
	(14,3.84,1,'2015-10-28 19:43:28','S','qcq93fq0ecut7nihm0av68n9p9',1,'test_01cba7a87a','A'),
	(15,1142,1,'2015-10-28 23:09:18','R','iaua5rtgpi48qhcv4halinh6sm',1,'','P'),
	(16,1142,1,'2015-10-28 23:20:28','R','528p92j89tnnqfs7h7uel9a2a4',1,'test_998dafecd0','A'),
	(17,1142,1,'2015-10-28 23:37:15','R','krm0v1s7oi3pg95fnqev75c59m',1,'test_cac830df4d','A'),
	(18,1142,1,'2015-10-28 23:56:17','R','a2ctbri1pt74ds3jhjg82jc6bj',1,'test_41883c9085','A'),
	(19,1142,1,'2015-10-29 00:05:47','R','65or02a3ms2msvsf684v99ute4',1,'test_c7f1dfd0ea','A'),
	(20,1148,1,'2015-10-29 02:30:56','R','ndof8clpm0oiqn1dgcsbs45r3e',1,'test_e92c5663a6','A'),
	(21,4,1,'2015-10-29 02:36:38','S','edt736k8enu0fg3345ehuvsk41',1,'test_f3dad1a42b','A'),
	(22,0,1,'2015-10-29 02:42:32','S','a8aufsjvrug0ckdsnhpp389h0a',1,'','P'),
	(23,1.4,1,'2015-10-29 02:53:31','S','ldqughcmg4dn4d8lha05b58iv4',1,'','P'),
	(24,1.4,1,'2015-10-29 02:53:59','S','lrrog0l6iav1r0citqqtr2mvdm',1,'','P'),
	(25,1.4,1,'2015-10-29 02:53:59','S','7bcg1nad7kkq4r3f39fb0gl8q5',1,'','P'),
	(26,3.6,1,'2015-10-29 02:55:31','S','cn71gjg32mcfuhc1efjip6lmf1',1,'','P'),
	(27,1148,1,'2015-10-29 03:00:55','R','rgri3ldr960lkdn744ktph95q8',1,'','P'),
	(28,1148,1,'2015-10-29 03:01:12','R','hkou2a0a3dlgmv1qdg96ccodp0',1,'test_e46dae4f10','A'),
	(29,3.84,1,'2015-10-29 03:02:00','S','5lian56vns73glfo1rs0799nqm',1,'test_6f44072467','A'),
	(30,1.2,1,'2015-10-29 03:42:09','S','gind5k4st5e7hs63ggj19eovkn',1,'','P'),
	(31,3.6,1,'2015-10-29 03:45:38','S','ft16q0k68dcgumvbufqp941e4n',1,'test_0267768501','A'),
	(32,1436,1,'2015-10-29 03:55:21','R','jero3r69e148d0p6m5mstsbj3d',1,'test_c6e2902716','A'),
	(33,1436,1,'2015-10-29 08:07:38','R','3kq3pfcocv3b7vmpa20se4n99d',1,'','P'),
	(34,1436,1,'2015-10-29 08:07:57','R','10dmhfaprhi52jen24t9gci1rf',1,'','P'),
	(35,1436,1,'2015-10-29 11:45:32','R','ltdk405e26s3cj1ub4mg8356qt',1,'test_21330aa49b','A');

/*!40000 ALTER TABLE `owner_payment_tracker` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table payment_accounts
# ------------------------------------------------------------

DROP TABLE IF EXISTS `payment_accounts`;

CREATE TABLE `payment_accounts` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mp_master_key` text NOT NULL,
  `mp_private_key` text NOT NULL,
  `mp_public_key` text NOT NULL,
  `mp_token` text NOT NULL,
  `name` text NOT NULL,
  `tag_line` text NOT NULL,
  `msisdn` text NOT NULL,
  `address` text NOT NULL,
  `website` text NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `payment_accounts` WRITE;
/*!40000 ALTER TABLE `payment_accounts` DISABLE KEYS */;

INSERT INTO `payment_accounts` (`id`, `mp_master_key`, `mp_private_key`, `mp_public_key`, `mp_token`, `name`, `tag_line`, `msisdn`, `address`, `website`, `status`)
VALUES
	(1,'f0ee61c1-1639-4431-bc57-4b8c4a682291','live_private_gAZ2En7uvRB4kK9dDtbPoS1XVOI','live_public_NfiJBT9bl9yP8EGbZKlfuMGjb6g','7937460f2e9443422337','Mt. Olivet Methodist Society','Dansoman Society, Ghana','050 429 8056','Rice St. Dansoman','http://www.mtolivetmethodistsociety.com','A');

/*!40000 ALTER TABLE `payment_accounts` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table payments_tracker
# ------------------------------------------------------------

DROP TABLE IF EXISTS `payments_tracker`;

CREATE TABLE `payments_tracker` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `type` char(1) NOT NULL,
  `member_id` int(11) NOT NULL,
  `church_id` int(11) NOT NULL,
  `created_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `token` text NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  `amount` double DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `payments_tracker` WRITE;
/*!40000 ALTER TABLE `payments_tracker` DISABLE KEYS */;

INSERT INTO `payments_tracker` (`id`, `type`, `member_id`, `church_id`, `created_ts`, `token`, `status`, `amount`)
VALUES
	(1,'T',1,1,'2015-10-06 10:38:30','b3otuplravuk89b3fo2bijbr0u','A',0),
	(2,'T',1,1,'2015-10-06 10:38:32','b5n9sh3loncjh3di69nob68ofv','A',0),
	(3,'O',1,1,'2015-10-06 17:43:42','c7n9mueeuckvlutfa35mbq0ft','A',0),
	(4,'O',1,1,'2015-10-06 17:46:57','4pv6va1g8s0i74id67lt406s14','A',0),
	(5,'O',1,1,'2015-10-06 18:54:07','a581m7pl09ra9fpcuutijgof69','A',45454),
	(6,'T',1,1,'2015-10-07 09:11:08','25e66b5op1ka8qcpnriqefi3t4','A',1200),
	(7,'T',1,1,'2015-10-07 09:25:07','98saceu15khf7nu893g47vvpgc','D',4000),
	(8,'T',1,1,'2015-10-07 09:24:50','40ard0nfvcp2d4hcvqc15lni7v','A',4000),
	(9,'O',1,1,'2015-10-07 09:27:03','7hk2rutgi7lg4b32jak4ssd18h','D',2000),
	(10,'T',1,1,'2015-10-07 09:43:07','im1lsfji9jinvnuhjfo6pds703','A',4000),
	(11,'T',1,1,'2015-10-07 09:43:09','402aniu5qk150hu1tossoh1umh','A',4000),
	(12,'T',1,1,'2015-10-07 09:45:51','dvuahehlbpo2jekieq3b5mtrnk','A',200),
	(13,'T',1,1,'2015-10-07 09:49:59','vf9m22p9lcdsb4t112capfqgal','A',100),
	(14,'T',1,1,'2015-10-07 09:51:56','i26f6g287bhd8t4nm3pc1htgl5','D',10),
	(15,'T',1,1,'2015-10-07 09:51:41','6f59ltdkfv77otrk1ocacjmuvc','A',10),
	(16,'T',1,1,'2015-10-07 09:56:37','tp8nq2nv9tr2nfacm1802hkj7n','A',100),
	(17,'T',1,1,'2015-10-07 09:56:58','5c78ahmnhr602aos0kqo3q4lih','D',100),
	(18,'T',1,1,'2015-10-07 09:59:16','8pmqb1iq8i20bq7d3mqf26njeq','D',100),
	(19,'T',1,1,'2015-10-07 10:01:19','jgv6i6qhp307ere373can23td7','D',100),
	(20,'T',1,1,'2015-10-07 10:02:51','vb9j0vfmqplhd01okube2l0vc0','D',120),
	(21,'A',0,1,'2015-10-10 18:22:16','9qlh83lgh5423k70ha68mmf6o5','A',400),
	(22,'D',0,1,'2015-10-10 18:57:50','4rmqel641tkdmr4td1v433j5l8','A',10),
	(23,'D',0,1,'2015-10-10 18:57:58','ev4324rmmfpenvblujl9dckaha','A',10),
	(24,'D',0,1,'2015-10-10 18:58:00','jiqm49b64g9s6db5o8ij51tdme','A',10),
	(25,'D',0,1,'2015-10-10 19:00:38','e7otmjhg3h62s4b72quqm4pk4d','D',20),
	(26,'D',0,1,'2015-10-11 10:31:38','1d1u7f0p4tujia0letn4nt6mnu','D',56),
	(27,'D',0,1,'2015-10-11 14:44:01','8qd47btssnb8pqbeh6gr3qtacl','A',50),
	(28,'D',0,1,'2015-10-11 16:07:08','8e3sarmohppihomomn92nbqbfa','A',450),
	(29,'D',0,1,'2015-10-11 16:09:33','qklq8sl5mukru0jrnmngs65kho','A',400),
	(30,'D',0,1,'2015-10-12 00:29:04','jhfbqc0i6i9vl7jf6cc6h6hqp3','D',12),
	(31,'T',1,1,'2015-10-12 00:29:51','rct9vmomf9uo7eojpopgpqaifa','D',12),
	(32,'O',1,1,'2015-10-12 00:30:24','jtug7t680e90spr7jbm63a2pob','D',12),
	(33,'D',0,1,'2015-10-12 00:38:29','dihfqsgalevlm567m9famj113f','A',50),
	(34,'T',1,1,'2015-10-12 00:41:04','g1734d82rocflsaid087bnv4oc','A',56),
	(35,'D',0,1,'2015-10-12 00:53:39','jihhhdheo0rejiqp879fdr4gbq','A',34),
	(36,'T',1,1,'2015-10-12 00:54:59','e27vq62or103urfbvelv891ssc','A',32),
	(37,'D',0,1,'2015-10-12 00:59:18','imuaqb2h9h12pdplfedk1tutfm','A',54),
	(38,'D',0,1,'2015-10-12 01:01:43','386s6tinjcc39bur7o1t2lnmh','A',33),
	(39,'D',0,1,'2015-10-12 01:03:29','2kocd44bh6vmpqleg7a6ml5vg','A',11),
	(40,'D',0,1,'2015-10-12 01:11:08','48kr0uhdqv5elhpak46jifsg94','A',11),
	(41,'D',0,1,'2015-10-12 01:14:13','87tg1emordltqqg7nenc37dphr','D',10),
	(42,'D',0,1,'2015-10-12 01:15:52','nill41fm040p93br9qt9q1avca','D',12),
	(43,'O',1,1,'2015-10-12 01:16:47','uld0d6i8gbrhudifqgkrdrqi13','D',12),
	(44,'D',0,1,'2015-10-12 01:21:35','nh00an7rt5qqe9m5pl5uc2rc0c','D',10),
	(45,'O',1,1,'2015-10-12 01:22:28','1djffkjtdn7gcond59ouc3if90','D',54),
	(47,'D',0,1,'2015-10-15 00:51:29','etbcete1msgtbh5cctgta8p3jr','D',12),
	(48,'D',0,1,'2015-10-15 00:58:28','7uhmr0j2m9ee44iuptclp11pca','D',12),
	(49,'D',0,1,'2015-10-15 01:03:51','d3jcrd92hclvhm87mvjthm0gq9','D',78),
	(50,'D',0,0,'2015-10-15 01:18:13','c3q6v8dm66u3i7rdl16bqm9ht','D',23),
	(51,'D',0,0,'2015-10-15 01:22:58','2096scc1dedgefk99evfkhg9uv','D',54),
	(52,'D',0,0,'2015-10-15 01:26:19','ammgaod8p35qrv94cqm90b72j1','D',11),
	(53,'D',0,0,'2015-10-15 01:30:05','j2m6kgqvl7q7hvr7ceeauo63st','D',10),
	(54,'O',1,1,'2015-10-15 01:32:08','dc3v8kcjg73pdbgeain028ru0t','D',20),
	(55,'T',1,1,'2015-10-15 01:33:33','upht98cg9rb7rgb7eo8movjb7c','D',56),
	(56,'T',1,1,'2015-10-17 12:34:59','geo0cenl4nal0u15ls4sdieivc','A',12),
	(57,'T',1,1,'2015-10-17 22:43:16','72g9cqu9uf87de8oepl2h71qaj','D',30),
	(58,'D',0,0,'2015-10-19 15:13:00','926j8e23pe4n6ljs66obthhiaf','A',34),
	(59,'T',1,1,'2015-10-23 19:10:51','ao6da1u3ilejklo6msv9vkm8k7','A',1000),
	(60,'T',1,1,'2015-10-23 19:16:28','ujt0i2bjmene9i3pmisj1es3vh','A',1000),
	(61,'T',1,1,'2015-10-26 07:45:19','3dn53utfqm73bg1883j8pk3duq','A',112),
	(62,'D',0,0,'2015-10-31 07:08:29','sqn1139snj2cm8o2jsfrhm5f37','A',300),
	(63,'D',0,0,'2015-10-31 07:09:16','bgc04g9g20662jimbm8gi463kc','A',342),
	(64,'D',0,1,'2015-10-31 08:11:21','iuklg7g8cmhj95rvqoevvstk5k','A',23),
	(65,'D',0,1,'2015-10-31 08:15:32','k2tvbst0t2ef77eulrj0ngs1s','A',50),
	(66,'O',1,1,'2015-10-31 09:07:35','ldfche0unfvlakkuuvfft9f461','A',23),
	(67,'T',1,1,'2015-10-31 09:08:21','6opopboi9o6um9efdk3erqgsmb','A',23),
	(68,'T',1,1,'2015-10-31 09:08:46','78tj920nbrjm1h8v0g53tmqs24','A',23),
	(69,'O',1,1,'2015-10-31 09:09:17','mhcglhf9osnj53bj1f3k450c60','A',32),
	(70,'T',1,1,'2015-10-31 09:10:32','ikd9ch09bl5ggdct84g1ms4u19','A',24),
	(71,'O',1,1,'2015-10-31 09:11:13','i3gm8i6k32ga42rdm8tg28tol1','A',43),
	(72,'T',1,1,'2015-10-31 15:04:59','bkfl84sre4f155s5ne7st1n8a5','A',34),
	(73,'T',1,1,'2015-11-13 20:23:59','suo7bvc6utddv3lsnii9rd8ogh','A',200),
	(74,'T',1,1,'2015-11-29 11:54:42','8s1msm89kdp3iu4dpjn5a34ior','A',343),
	(75,'T',1,1,'2015-12-02 08:20:15','rlbitb1iii2uoj7ide0k0eptp5','A',13),
	(76,'D',0,1,'2015-12-02 08:22:59','tcpktscaji71k0voplcejh1c3i','A',4000);

/*!40000 ALTER TABLE `payments_tracker` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table power_leaders
# ------------------------------------------------------------

DROP TABLE IF EXISTS `power_leaders`;

CREATE TABLE `power_leaders` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `church_id` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `power_leaders` WRITE;
/*!40000 ALTER TABLE `power_leaders` DISABLE KEYS */;

INSERT INTO `power_leaders` (`id`, `member_id`, `church_id`, `created_by`, `created_ts`, `status`)
VALUES
	(2,1,1,1,'2015-10-16 12:34:06','D'),
	(3,1,1,1,'2015-10-21 08:27:43','D');

/*!40000 ALTER TABLE `power_leaders` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table power_users
# ------------------------------------------------------------

DROP TABLE IF EXISTS `power_users`;

CREATE TABLE `power_users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `church_id` int(11) NOT NULL,
  `member_id` int(11) DEFAULT NULL,
  `msisdn` text NOT NULL,
  `created_ts` datetime DEFAULT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `power_users` WRITE;
/*!40000 ALTER TABLE `power_users` DISABLE KEYS */;

INSERT INTO `power_users` (`id`, `church_id`, `member_id`, `msisdn`, `created_ts`, `status`)
VALUES
	(1,1,1,'+233244960321','2015-10-10 15:22:00','A');

/*!40000 ALTER TABLE `power_users` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table regions
# ------------------------------------------------------------

DROP TABLE IF EXISTS `regions`;

CREATE TABLE `regions` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` text,
  `code` text,
  `status` char(1) DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `regions` WRITE;
/*!40000 ALTER TABLE `regions` DISABLE KEYS */;

INSERT INTO `regions` (`id`, `name`, `code`, `status`)
VALUES
	(1,'Ashanti Region','AS','A'),
	(2,'Brong Ahafo Region','BA','A'),
	(3,'Central Region','CR','A'),
	(4,'Eastern Region','ER','A'),
	(5,'Greater Accra Region','GR','A'),
	(6,'Northern Region','NR','A'),
	(7,'Upper East Region','UE','A'),
	(8,'Upper West Region','UW','A'),
	(9,'Volta Region','VR','A'),
	(10,'Western Region','WR','A');

/*!40000 ALTER TABLE `regions` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sent_sms
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sent_sms`;

CREATE TABLE `sent_sms` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `church_id` int(11) NOT NULL,
  `sms_count` double NOT NULL,
  `created_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `sent_sms` WRITE;
/*!40000 ALTER TABLE `sent_sms` DISABLE KEYS */;

INSERT INTO `sent_sms` (`id`, `church_id`, `sms_count`, `created_ts`, `status`)
VALUES
	(3,1,2,'2015-10-14 20:04:43','A'),
	(5,1,1,'2015-10-15 01:32:12','A'),
	(6,1,1,'2015-10-15 01:33:34','A'),
	(7,1,1,'2015-10-15 22:12:11','A'),
	(8,1,1,'2015-10-15 22:20:36','A'),
	(9,1,1,'2015-10-15 22:26:44','A'),
	(10,1,1,'2015-10-15 22:38:22','A'),
	(11,1,1,'2015-10-15 22:40:27','A'),
	(12,1,1,'2015-10-15 22:45:41','A'),
	(13,1,1,'2015-10-15 22:48:07','A'),
	(14,1,1,'2015-10-15 22:57:53','A'),
	(15,1,1,'2015-10-15 23:00:55','A'),
	(16,1,1,'2015-10-15 23:01:24','A'),
	(17,1,3,'2015-10-15 23:11:33','A'),
	(18,1,1,'2015-10-15 23:11:33','A'),
	(23,1,1,'2015-10-16 19:16:23','A'),
	(25,1,1,'2015-10-17 12:46:39','A'),
	(29,1,1,'2015-10-17 20:51:44','A'),
	(30,1,1,'2015-10-17 20:53:39','A'),
	(31,1,1,'2015-10-17 21:06:54','A'),
	(32,1,1,'2015-10-17 21:09:23','A'),
	(33,1,1,'2015-10-17 21:41:17','A'),
	(34,1,1,'2015-10-17 21:41:18','A'),
	(35,1,1,'2015-10-17 22:04:15','A'),
	(36,1,1,'2015-10-17 22:05:49','A'),
	(37,1,1,'2015-10-17 22:16:04','A'),
	(38,1,1,'2015-10-17 22:17:25','A'),
	(43,1,1,'2015-10-17 22:24:16','A'),
	(44,1,1,'2015-10-17 22:28:16','A'),
	(46,1,1,'2015-10-17 22:34:10','A'),
	(47,1,1,'2015-10-17 22:40:55','A'),
	(49,1,1,'2015-10-17 22:43:17','A'),
	(50,1,1,'2015-10-17 23:02:03','A'),
	(54,1,1,'2015-10-18 19:50:32','A'),
	(55,1,1,'2015-10-18 20:46:00','A'),
	(59,1,1,'2015-10-19 07:54:20','A'),
	(60,1,1,'2015-10-19 08:16:55','A'),
	(61,1,1,'2015-10-19 08:16:55','A'),
	(62,1,1,'2015-10-19 08:18:20','A'),
	(63,1,1,'2015-10-19 08:20:09','A'),
	(64,1,1,'2015-10-19 08:31:19','A'),
	(67,1,1,'2015-10-19 12:27:12','A'),
	(68,1,1,'2015-10-19 12:27:16','A'),
	(69,1,1,'2015-10-20 02:04:45','A'),
	(71,1,1,'2015-10-20 14:12:27','A'),
	(72,1,1,'2015-10-20 20:48:27','A'),
	(73,1,1,'2015-10-20 21:15:42','A'),
	(79,1,1,'2015-10-23 18:54:12','A'),
	(80,1,1,'2015-10-23 19:01:29','A'),
	(81,1,3,'2015-10-23 19:06:08','A'),
	(87,1,1,'2015-10-31 08:30:30','A'),
	(88,1,1,'2015-11-08 09:25:57','A'),
	(89,1,1,'2015-11-12 16:50:50','A'),
	(90,1,1,'2015-11-12 16:52:48','A'),
	(91,1,1,'2015-11-15 16:46:24','A'),
	(92,1,1,'2015-11-15 16:46:25','A'),
	(93,1,1,'2015-11-15 16:46:26','A'),
	(94,1,1,'2015-11-15 16:46:26','A'),
	(95,1,1,'2015-11-15 16:46:27','A'),
	(96,1,1,'2015-11-15 16:46:27','A'),
	(97,1,1,'2015-11-15 16:46:27','A'),
	(98,1,1,'2015-11-15 16:46:33','A'),
	(99,1,1,'2015-11-15 16:46:33','A'),
	(100,1,1,'2015-11-15 16:46:34','A'),
	(101,1,1,'2015-11-15 16:46:34','A'),
	(102,1,1,'2015-11-15 16:46:35','A'),
	(103,1,1,'2015-11-15 16:46:35','A'),
	(104,1,1,'2015-11-15 16:46:36','A'),
	(105,1,0,'2015-12-01 09:15:33','A');

/*!40000 ALTER TABLE `sent_sms` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sms
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sms`;

CREATE TABLE `sms` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `message` text NOT NULL,
  `display` text NOT NULL,
  `church_id` int(11) NOT NULL,
  `created_ts` datetime NOT NULL,
  `created_by` int(11) NOT NULL,
  `modified_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `approved_by` int(11) NOT NULL,
  `modified_by` int(11) NOT NULL,
  `is_editable` char(1) NOT NULL DEFAULT 'T',
  `is_bulk` char(1) NOT NULL DEFAULT 'N',
  `sms_length` int(11) NOT NULL DEFAULT '1',
  `type` char(1) NOT NULL DEFAULT 'G',
  `msisdn` text,
  `groups` text,
  `status` char(1) NOT NULL DEFAULT 'P',
  `orig` char(1) DEFAULT 'W',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `sms` WRITE;
/*!40000 ALTER TABLE `sms` DISABLE KEYS */;

INSERT INTO `sms` (`id`, `message`, `display`, `church_id`, `created_ts`, `created_by`, `modified_ts`, `approved_by`, `modified_by`, `is_editable`, `is_bulk`, `sms_length`, `type`, `msisdn`, `groups`, `status`, `orig`)
VALUES
	(12,'God bless you.','mt. olivet',1,'2015-10-14 14:19:46',1,'2015-10-14 14:30:46',1,1,'F','Y',1,'M','+233504298056,+233244960321','','A','W'),
	(13,'hello testing','mt. olivet',1,'2015-10-14 14:26:21',1,'2015-10-14 14:51:15',1,1,'F','Y',1,'G','','2','A','W'),
	(15,'test','mt. olivet',1,'2015-10-14 19:56:47',1,'2015-10-14 19:56:56',1,1,'F','Y',1,'M','233244960321,233504298056','','A','W'),
	(16,'test','mt. olivet',1,'2015-10-14 20:01:32',1,'2015-10-14 20:01:44',1,1,'F','Y',1,'M','233244960321,233504298056','','A','W'),
	(17,'still testing','mt. olivet',1,'2015-10-14 20:04:32',1,'2015-10-14 20:04:40',1,1,'F','Y',1,'M','233244960321,233504298056','','A','W'),
	(19,'hello','mt. olivet',1,'2015-10-17 12:47:41',1,'2015-10-17 13:41:31',1,1,'F','N',1,'M','233244960321','','A','W'),
	(20,'hello sir','mt. olivet',1,'2015-10-20 20:46:32',1,'2015-10-20 20:49:10',0,1,'F','N',1,'M','+233504298056','','D','W'),
	(21,'God bless you','mt. olivet',1,'2015-10-20 20:47:28',1,'2015-10-20 20:48:27',1,1,'F','N',1,'M','+233504298056','','A','W'),
	(22,'Good evening Elders','mt. olivet',1,'2015-10-23 19:05:47',1,'2015-10-23 19:06:08',1,1,'F','Y',1,'M','233244264760,233209636610,233244173072','','A','W'),
	(23,'hello','mt. olivet',1,'2015-10-26 07:50:40',1,'2015-10-26 07:50:52',1,1,'F','N',1,'M','233244960321','','A','W'),
	(24,'hello Sels','mt. olivet',1,'2015-10-31 08:30:10',1,'2015-10-31 08:30:29',1,1,'F','N',1,'M','233244960321','','A','W'),
	(25,'Happy Sunday','mt. olivet',1,'2015-11-08 09:25:32',1,'2015-11-08 09:25:57',1,1,'F','N',1,'M','233244960321','','A','W');

/*!40000 ALTER TABLE `sms` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sms_gateway_messages
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sms_gateway_messages`;

CREATE TABLE `sms_gateway_messages` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `message_id` text NOT NULL,
  `from_msisdn` text NOT NULL,
  `message` text NOT NULL,
  `posted_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` char(1) DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `sms_gateway_messages` WRITE;
/*!40000 ALTER TABLE `sms_gateway_messages` DISABLE KEYS */;

INSERT INTO `sms_gateway_messages` (`id`, `message_id`, `from_msisdn`, `message`, `posted_ts`, `status`)
VALUES
	(1,'8269128','MPower','MTN Mobile Money will be interrupted on Oct. 16 and restored by Oct. 19. Alternatively pay with MPower via bank cards, Airtel Money, Tigo Cash and tokens.','1970-01-17 05:21:33','A'),
	(2,'8256133','uniBank','Dear Valued Customer,\r\nHave you run out of units on your ECG prepaid meter and need to top up or are you an ECG vendor. \r\nVisit our Osu Main, Oxford Street, Accra Main, Makola, GIMPA, Madina, and East Legon branches to buy your ECG prepaid units if your l','1970-01-17 05:21:21','A'),
	(3,'8231348','mt. olivet','Thank you, Fetz. Mt. Olivet Society has acknowledged 56.0 GHS as tithe paid in October. \n Visit the website or Text TITHE,SERMON,EVENT or INFO to +233244960321 for more information. God richly bless you!','1970-01-17 05:21:12','A'),
	(4,'8231334','mt. olivet','Thank you, Fetz. Mt. Olivet Society has received 20.0 GHS as offering to the Lord. God richly bless you!','1970-01-17 05:21:12','A'),
	(5,'8231306','C Cradle','10.0 GHS has been made as a donation to the church. Thank you!.\n2015-10-15','1970-01-17 05:21:12','A'),
	(6,'8231263','C Cradle','11.0 GHS has been made as a donation to the church. Thank you!.\n2015-10-15','1970-01-17 05:21:12','A'),
	(7,'8219687','mt. olivet','hello','1970-01-17 05:19:48','A'),
	(8,'8217475','weber','test','1970-01-17 05:20:27','A'),
	(9,'8217450','web','test','1970-01-17 05:20:27','A'),
	(10,'8211465','Surfline','Dear Customer, your main data bundle on 233255062021 will expire in 5 days( 19/10/2015 11:52:18).Please log on to MySurfline to buy a new bundle to ensure uninterrupted browsing.Thank You','1970-01-17 05:20:06','A'),
	(11,'8206258','web','test','1970-01-17 05:18:48','A'),
	(12,'8206232','web','test','1970-01-17 05:18:48','A'),
	(13,'8205708','+233209423311','Aaaww thnk u dear','1970-01-17 05:18:44','A'),
	(14,'8205642','EVD','You have successfully transferred amount 30.00 GHC to 233546706099 on Oct 13, 2015. Your updated balance is 28.04 GHC.Transaction ID: 2015101320192574404944390','1970-01-17 05:18:44','A'),
	(15,'8205611','+121','You have transferred GHc30.00000 credit to 233209423311. Your own account balance is GHc12.57040.','1970-01-17 05:18:44','A'),
	(16,'8205599','555','Your main balance is GHc 42.62040 valid until 11-01-2016. Yee Twi Kor promo is live! Win free cash or airtime instantly. Just top up with a recharge card today.','1970-01-17 05:18:44','A'),
	(17,'8205598','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','1970-01-17 05:18:44','A'),
	(18,'8198086','MTN','Click to see some of the amazing things on earth! http://bit.ly/1Ligzwn with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','1970-01-17 05:18:21','A'),
	(19,'8186949','555','Dear Customer, your Browser Mini Double internet package has expired. Reactivate now to enjoy our internet packages. Vodafone, power to you.','1970-01-17 05:18:38','A'),
	(20,'8185687','Surfline','Dear Customer, your data bundle expires in 5 days (18/10/2015). Please ensure you have sufficient airtime on 233255062021 for auto renewal to occur. ','1970-01-17 05:18:31','A'),
	(21,'8183374','+233244245345','Have you recieved it?','1970-01-17 05:17:22','A'),
	(22,'8168979','+233244755702','Ok','1970-01-17 05:17:26','A'),
	(23,'8159663','+233209154458','Can I start coming?','1970-01-17 05:15:42','A'),
	(24,'8156827','+233244245345','Sent you the account.','1970-01-17 05:16:01','A'),
	(25,'8156821','Surfline','Dear Valued Customer, You have   1.992GB remaining on 233255062021 . This is valid until 19/10/2015. Kindly log on to MySurfline for more information. Thank you','1970-01-17 05:16:01','A'),
	(26,'8146198','+233500190529','He who does what is sinful is of the devil, because the devil has been sinning from the beginning. The reason the son of God appeared was to destroy the devil&#039;s work.','1970-01-17 05:14:29','A'),
	(27,'8145290','+233244245345','I can create it and credit it but we dont have any credit on the main account so messages wont deliver.   ','1970-01-17 05:14:26','A'),
	(28,'8140579','+233500190529','Please pick my God.','1970-01-17 05:12:58','A'),
	(29,'8130014','MTN','Super Hilarious! Click to watch the most weirdest &amp; funniest auditions http://bit.ly/1Z4ahd8 with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','1970-01-17 05:13:10','A'),
	(30,'8124149','Vodafone','Calling Nigeria &amp; South Africa is cheapest on Vodafone! For only 99pesewas, you get 4mins to call family and friends! Just dial *125*32# today','1970-01-17 05:13:02','A'),
	(31,'8122276','EVD','You have successfully transferred amount 5.00 GHC to 233240020588 on Oct 9, 2015. Your updated balance is 29.34 GHC.Transaction ID: 2015100908324008204278469','1970-01-17 05:12:59','A'),
	(32,'8122080','555','Your main balance is GHc 110.52040 valid until 07-01-2016. Talk for 2 whole days. Dial 5544 to enjoy 250mins for Vodafone calls. 12mins for other networks and 50MB data for only GHS 2.','1970-01-17 05:12:59','A'),
	(33,'8122062','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','1970-01-17 05:12:59','A'),
	(34,'8121586','Esoko','hello','1970-01-17 05:12:58','A'),
	(35,'8121548','Esoko','hello','1970-01-17 05:12:58','A'),
	(36,'8121546','Esoko','hello','1970-01-17 05:12:58','A'),
	(37,'8121500','555','Your main balance is GHc 80.52040 valid until 07-01-2016. Talk for 2 whole days. Dial 5544 to enjoy 250mins for Vodafone calls. 12mins for other networks and 50MB data for only GHS 2.','1970-01-17 05:12:58','A'),
	(38,'8121495','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','1970-01-17 05:12:58','A'),
	(39,'8121427','555','Your main balance is GHc 50.52040 valid until 07-01-2016. Talk for 2 whole days. Dial 5544 to enjoy 250mins for Vodafone calls. 12mins for other networks and 50MB data for only GHS 2.','1970-01-17 05:12:58','A'),
	(40,'8110312','MPower','Beat the stress! Top up &amp; pay your bills at www.mpower.com.gh or dial *714*44#. Buy your Surfline bundle, airtime on all networks, pay ECG Postpaid &amp; DSTV bills','1970-01-17 05:11:17','A'),
	(41,'8089759','+233240591662','Thnks Nana. Much appreciated','1970-01-17 05:10:04','A'),
	(42,'8089730','+233240591662','Aryt wil add u soon','1970-01-17 05:10:03','A'),
	(43,'8089722','+121','You have transferred GHc10.00000 credit to 233208392598. Your own account balance is GHc20.77040.','1970-01-17 05:10:03','A'),
	(44,'8089664','+233240591662','Haaha. Re u nt on watsap dear?','1970-01-17 05:10:03','A'),
	(45,'8089642','+233240591662','Oooooh dear! Its u.','1970-01-17 05:10:03','A'),
	(46,'8089611','+233240591662','Pls  i dnt know u. Can tell me where you got my contact dear?','1970-01-17 05:10:03','A'),
	(47,'8089574','+233240591662','Thank you. Buh name pls?','1970-01-17 05:10:03','A'),
	(48,'8089478','555','Your main balance is GHc 30.82040 valid until 05-01-2016. Talk for 2 whole days. Dial 5544 to enjoy 250mins for Vodafone calls. 12mins for other networks and 50MB data for only GHS 2.','1970-01-17 05:10:02','A'),
	(49,'8089476','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','1970-01-17 05:10:02','A'),
	(50,'8089172','Esoko','hello','1970-01-17 05:10:02','A'),
	(51,'8089171','EVD','You have received 30.00 GHC from 0240968100/233240968100. a/c balance is 62.36 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015100719164907102413918','1970-01-17 05:10:02','A'),
	(52,'8083227','MTN','Its The MTN GH Stands in Worship concert. Grab ur ticket @ MTN Osu, Graphic Road, Accra Mall or West Hills mall for Ghc50 VIP &amp; Ghc30 Regular. Dial 1355 to download ur favourite callertunez','1970-01-17 05:09:42','A'),
	(53,'8060563','Surfline','Dear Customer, the recent service interruptions have been resolved. We apologize for any inconvenience caused. Surfline... It&#039;s about time','1970-01-17 05:08:22','A'),
	(54,'8060375','Surfline','Dear Customer, the recent service interruptions have been resolved. We apologize for any inconvenience caused. Surfline... It&#039;s about time','1970-01-17 05:08:22','A'),
	(55,'8058326','5200','Calling USA,Canada,China and UK landlines  is cheapest on Vodafone! Get 50 minutes to call for only GHc5. Just dial *125*30# to subscribe &amp; get 10 extra minutes to Vodafone free!','1970-01-17 05:08:19','A'),
	(56,'8057296','+233209154458','Boss , sorry yu haven&#039;t heard from me for some time now .I know it&#039;s very unserious of me but travelled to my Uncle&#039;s place wasn&#039;t gettin a stable network back in town . Please set the date I could come around','1970-01-17 05:08:19','A'),
	(57,'8057241','Vodafone','Calling Nigeria &amp; South Africa is cheapest on Vodafone! For only GhC 3.9, you get 16mins to call &amp; 2 SMS to text over the next 7 days! Just dial *125*32# today','1970-01-17 05:08:18','A'),
	(58,'8057045','Surfline','Dear customer, we apologize for interruptions in our services today. Our team is working to rectify this in the shortest possible time. Thank you.','1970-01-17 05:08:18','A'),
	(59,'8053764','Surfline','Dear customer, we apologize for interruptions in our services today. Our team is working to rectify this in the shortest possible time. Thank you.','1970-01-17 05:08:14','A'),
	(60,'8051675','MTN','They love &amp; live magic! Click to share in the fun http://bit.ly/1Q1Birk with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','1970-01-17 05:08:53','A'),
	(61,'8023155','+233244387718','0000  P NPP:2601   NDC:2001 CPP:100a','1970-01-17 05:06:50','A'),
	(62,'8023089','+233244387718','0000  P NPP:2601   NDC:2001 CPP:1004','1970-01-17 05:06:50','A'),
	(63,'8022978','+233244387718','0000  P NPP:2601   NDC:2001 CPP:1001','1970-01-17 05:06:50','A'),
	(64,'8022743','+233244387718','0000  P NPP:2601   NDC:2001 CPP:1001','1970-01-17 05:06:50','A'),
	(65,'8022681','+233244387718','0000  P NPP:2601   NDC:2001 CPP:1001','1970-01-17 05:06:49','A'),
	(66,'8022598','+233244387718','0020 P NPP:2601 NDC:2001 CPP:1001','1970-01-17 05:06:49','A'),
	(67,'8011335','+233244387718','0020 P NPP:2601 NDC:2001 CPP:1001','1970-01-17 05:07:21','A'),
	(68,'8011293','+233244387718','0020 P NPP:2600 NDC:2000 CPP:1000','1970-01-17 05:07:20','A'),
	(69,'8011248','+233244387718','0020 P NPP:2600 NDC:2000 CPP:1000','1970-01-17 05:07:20','A'),
	(70,'8011205','+233244387718','0000 P NPP : 2600 NDC:2000 CPP:1000','1970-01-17 05:07:20','A'),
	(71,'8011135','+233244387718','0000 P NPP: 2600 NDC:2000 CPP:1000','1970-01-17 05:07:20','A'),
	(72,'8011104','+233244387718','0000 P NPP:2600 NDC:2000 CPP:1000','1970-01-17 05:07:20','A'),
	(73,'8011087','+233244387718','0000 P NPP2600 NDC:2000 CPP:1000','1970-01-17 05:07:20','A'),
	(74,'8010667','+233244387718','0000 P NPP2600 NDC:2000 CPP:1000','1970-01-17 05:07:19','A'),
	(75,'8010597','+233244387718','0000 P NPP2600 NDC: 2000 CPP:1000','1970-01-17 05:07:19','A'),
	(76,'8010550','+233244387718','0000 PNPP:2600 NDC: 2000 CPP:1000','1970-01-17 05:07:19','A'),
	(77,'8010492','+233244387718','0000 P NPP:2600 NDC :2000 CPP:1000','1970-01-17 05:07:19','A'),
	(78,'8010433','+233244387718','0000 P NPP:2600 NDC: 2000 CPP:1000','1970-01-17 05:07:19','A'),
	(79,'8010302','+233244387718','0000 P NPP:2600 NDC:2000 CPP:1000','1970-01-17 05:07:19','A'),
	(80,'8010115','555','Service Request Handled Succesfully\r\n','1970-01-17 05:07:19','A'),
	(81,'8010069','555','Welcome to MTN Voice Mail Service. Please text &#039;START&#039; or &#039;STOP&#039; to activate or deactivate service.','1970-01-17 05:07:19','A'),
	(82,'8010022','555','Welcome to MTN Voice Mail Service. Please text &#039;START&#039; or &#039;STOP&#039; to activate or deactivate service.','1970-01-17 05:07:18','A'),
	(83,'8010016','555','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','1970-01-01 12:00:00','A'),
	(84,'8010011','555','Welcome to MTN Voice Mail Service. Please text &#039;START&#039; or &#039;STOP&#039; to activate or deactivate service.','1970-01-17 05:07:18','A'),
	(85,'8009985','555','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','1970-01-01 12:00:00','A'),
	(86,'8009983','EVD','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','1970-01-01 12:00:00','A'),
	(87,'8009958','+121','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','1970-01-01 12:00:00','A'),
	(88,'8009956','EVD','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','1970-01-01 12:00:00','A'),
	(89,'8009955','EVD','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','1970-01-01 12:00:00','A'),
	(90,'8009954','EVD','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','1970-01-01 12:00:00','A'),
	(91,'8009762','+256205462012','i have a crazy plan for today and i want to share it with you, call me  +256205462012','1970-01-17 05:07:18','A'),
	(92,'8009711','EVD','You have successfully transferred amount 1.00 GHC to 233244387718 on Oct 5, 2015. Your updated balance is 3.30 GHC.Transaction ID: 2015100509475620701250029','1970-01-17 05:07:18','A'),
	(93,'8007831','EVD','You have successfully transferred amount 2.00 GHC to 233240020588 on Oct 5, 2015. Your updated balance is 4.40 GHC.Transaction ID: 2015100508533817601192051','1970-01-17 05:07:15','A'),
	(94,'7987030','EVD','You have received 30.00 GHC from 0240968100/233240968100. a/c balance is 56.91 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015100319291069502507580','1970-01-17 05:05:34','A'),
	(95,'7987029','Esoko','hello','1970-01-17 05:05:34','A'),
	(96,'7987027','1330','esoko : 30.00 GHC top-up credit sent from esoko TXN ID: 6237248 Send money, top-up or pay with txtnpay. Help line:0302902981','1970-01-17 05:05:34','A'),
	(97,'7978640','+233500190529','Please lets text','1970-01-17 05:04:23','A'),
	(98,'7977960','+121','You have transferred GHc15.00000 credit to 233209425151. Your own account balance is GHc28.78040.','1970-01-17 05:04:18','A'),
	(99,'7977936','+121','You have transferred GHc20.00000 credit to 233209423311. Your own account balance is GHc43.83040.','1970-01-17 05:04:18','A'),
	(100,'7977911','Esoko','hello','1970-01-17 05:04:18','A'),
	(101,'7977902','EVD','You have successfully transferred amount 15.00 GHC to 233546706099 on Oct 3, 2015. Your updated balance is 26.71 GHC.Transaction ID: 2015100319352336003001951','1970-01-17 05:04:17','A'),
	(102,'7977892','Esoko','hello','1970-01-17 05:04:17','A'),
	(103,'7977874','555','Your main balance is GHc 83.93040 valid until 01-01-2016. Talk for 2 whole days. Dial 5544 to enjoy 250mins for Vodafone calls. 12mins for other networks and 50MB data for only GHS 2.','1970-01-17 05:04:17','A'),
	(104,'7977873','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','1970-01-17 05:04:17','A'),
	(105,'7977865','Esoko','hello','1970-01-17 05:04:17','A'),
	(106,'7970067','+233271210210','0000 m Npp :290 ndc:190 cpp:120','1970-01-17 05:04:28','A'),
	(107,'7969313','+233271210210','0000 m Npp:290 ndc:190 cpp:120','1970-01-17 05:04:27','A'),
	(108,'7968988','+233271210210','Help','1970-01-17 05:04:27','A'),
	(109,'7968890','+233271210210','info','1970-01-17 05:04:27','A'),
	(110,'7963711','+233240020588','Pls am in church.i will call u wen i close.hope u gud','1970-01-17 05:04:20','A'),
	(111,'7925839','MTN','Super hilarious! Click to have the best laugh today! http://bit.ly/1JzSKho .Enjoy more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','1970-01-17 05:03:09','A'),
	(112,'7924554','uniBank','Participate in the uniBank Super 360 Promo. Deposit GHS100 to join and maintain a minimum balance of GHS200. You stand the chance of winning GHS15K in the 3rd draw and GHS360K etc. in the ultimate draw. HURRY, Promo ends 30th October 2015. Terms and Condi','1970-01-17 05:03:07','A'),
	(113,'7922611','+233208392598','Mwaah wo 10ghc di3 wo boa koraa','1970-01-17 05:03:02','A'),
	(114,'7922448','+121','You have transferred GHc10.00000 credit to 233208392598. Your own account balance is GHc60.06040.','1970-01-17 05:03:02','A'),
	(115,'7908436','Esoko','hello','1970-01-17 05:01:37','A'),
	(116,'7906663','+121','You have transferred GHc10.00000 credit to 233209137263. Your own account balance is GHc70.69040.','1970-01-17 05:01:32','A'),
	(117,'7906610','555','Your main balance is GHc 80.74040 valid until 30-12-2015. Talk for 2 whole days. Dial 5544 to enjoy 250mins for Vodafone calls. 12mins for other networks and 50MB data for only GHS 2.','1970-01-17 05:01:32','A'),
	(118,'7905525','+233243342729','Am trying to reach u but ur line isn&#039;t going through, I just wanted to know how u are doing. ','1970-01-17 05:01:30','A'),
	(119,'7878791','Vodafone','Vodafone appstore is live! Visit appstore.vodafone.com.gh,register &amp; download any app of your choice and stand a chance to win free 100MB data.','1970-01-17 05:01:02','A'),
	(120,'7840794','5200','FREE INTERNET! Simply dial *700*24# to buy 30MB for only GHs1. Vodafone will add extra 30MB for free on Ghana&#039;s fastest 3G+ network. http://opr.as/e2Y','1970-01-17 04:59:39','A'),
	(121,'7823363','+233244960321','Agent: annan\nPolling Station: Lebanon Club (a)\nConstituency: Bantama\n','1970-01-17 04:59:56','A'),
	(122,'7823334','+233504298056','Info','1970-01-17 04:59:56','A'),
	(123,'7823054','+233504298056','Info','1970-01-17 04:59:56','A'),
	(124,'7822777','+233244960321','Agent: annan\nPolling Station: Lebanon Club (a)\nConstituency: Bantama\n','1970-01-17 04:59:56','A'),
	(125,'7822734','+233504298056','Info','1970-01-17 04:59:56','A'),
	(126,'7822292','+233504298056','Info','1970-01-17 04:59:55','A'),
	(127,'7807169','MTN','Super fun tricks! http://bit.ly/1ODNpgT .Click to enjoy more with your 300MB YouTube Bundle! @ GHC 3 only. Dial *138*1*6#','1970-01-17 04:58:27','A'),
	(128,'7803685','+233242112511','doctorkrow@yahoo.com','1970-01-17 04:58:22','A'),
	(129,'7775139','+233244387718','Help','1970-01-17 04:58:33','A'),
	(130,'7775096','+233244387718','Info','1970-01-17 04:58:33','A'),
	(131,'7775035','+233244387718','Info','1970-01-17 04:58:33','A'),
	(132,'7774967','+233244387718','Agent: edd\nPolling Station: Anglican Prim Sch Tepa (a)\nConstituency: Ahafo Ano North\n','1970-01-01 12:00:00','A'),
	(133,'7774959','+233244387718','Info','1970-01-17 04:58:33','A'),
	(134,'7774865','+233244387718','Info','1970-01-17 04:58:33','A'),
	(135,'7774685','+233244387718','Info','1970-01-17 04:58:32','A'),
	(136,'7774335','+233244387718','Info','1970-01-17 04:58:32','A'),
	(137,'7770063','138','Internet Usage Flash, you have 294.00MB remaining on your assigned 1GB. Your 1GB will be renewed when you run out. To stop renewal, SMS STOP to 138.','1970-01-17 04:58:26','A'),
	(138,'7767381','Esoko','hello','1970-01-17 04:57:16','A'),
	(139,'7767380','1330','esoko : 15.00 GHC top-up credit sent from esoko TXN ID: 6227529 Send money, top-up or pay with txtnpay. Help line:0302902981','1970-01-17 04:57:16','A'),
	(140,'7767371','Esoko','hello','1970-01-17 04:57:15','A'),
	(141,'7767365','Esoko','hello','1970-01-17 04:57:15','A'),
	(142,'7767364','1330','esoko : 30.00 GHC top-up credit sent from esoko TXN ID: 6227527 Send money, top-up or pay with txtnpay. Help line:0302902981','1970-01-17 04:57:15','A'),
	(143,'7767363','EVD','You have received 30.00 GHC from 0240968100/233240968100. a/c balance is 34.84 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015092822213081502097330','1970-01-17 04:57:15','A'),
	(144,'7767361','555','Your main balance is GHc 51.15170 valid until 27-12-2015. Its talk time. Dial 7171 &amp; enjoy free calls to any Vodafone number plus double bonus credit on GHs 2 top ups or more for GHS 3.99 weekly','1970-01-17 04:57:15','A'),
	(145,'7767360','Esoko','hello','1970-01-17 04:57:15','A'),
	(146,'7767353','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','1970-01-17 04:57:15','A'),
	(147,'7739471','MTNPay4Me','The number 233544295582 tried to reach you, but you did not answer the call','1970-01-17 04:55:38','A'),
	(148,'7739441','MTNPay4Me','The number 233544295582 tried to reach you, but you did not answer the call','1970-01-17 04:55:38','A'),
	(149,'7733624','+233544295582','I dont know','1970-01-17 04:55:58','A'),
	(150,'7732729','+233544295582','Seriously. Will you go to the hospital? So sorry. God should mercifully and kindly grant you strength, it is not easy.','1970-01-17 04:55:50','A'),
	(151,'7691538','+233244387718','0000 P NPP:2600 NDC:2000 CPP:1000','1970-01-17 04:52:54','A'),
	(152,'7691530','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','1970-01-17 04:52:54','A'),
	(153,'7691526','+233244387718','help','1970-01-17 04:52:54','A'),
	(154,'7691511','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','1970-01-17 04:52:54','A'),
	(155,'7691502','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','1970-01-17 04:52:54','A'),
	(156,'7689025','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','1970-01-17 04:52:43','A'),
	(157,'7684907','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','1970-01-17 04:52:38','A'),
	(158,'7684729','+233244387718','help','1970-01-17 04:52:38','A'),
	(159,'7683817','0000','help','1970-01-17 04:52:37','A'),
	(160,'7683576','0000','help','1970-01-17 04:52:37','A'),
	(161,'7680090',' 233244960321','help','1970-01-01 12:00:00','A'),
	(162,'7677517','+233244960321','just a test. Hello!','1970-01-17 04:52:31','A'),
	(163,'7675433','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','1970-01-17 04:52:27','A'),
	(164,'7674905','0000','01234 P GOOG:12 APPL:13 YHOO:45 NYSE:56','1970-01-17 04:52:27','A'),
	(165,'7674373','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','1970-01-17 04:52:26','A'),
	(166,'7674182','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','1970-01-17 04:52:26','A'),
	(167,'7672825','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','1970-01-17 04:52:24','A'),
	(168,'7671149','MTN','Young &amp; free! http://bit.ly/1KKcIeB .Watch more with your 300MB YouTube Bundle! @ GHC 3 only. Dial *138*1*6#','1970-01-17 04:53:05','A'),
	(169,'7667872','0000','01234 m GOOG:12 APPL:13 YHOO:45 NYSE:56','1970-01-17 04:52:54','A'),
	(170,'7667374','+233242679330','Feeling gr8,i woke up some few mins ago so that should tell u how well i slept :)','1970-01-17 04:52:50','A'),
	(171,'7667287','+233242679330','Good morning naughty :)','1970-01-17 04:52:49','A'),
	(172,'7664369','0000','0012 P GOOG:12 APPL:13 YHOO:45 NYSE:56','1970-01-17 04:52:26','A'),
	(173,'7664268','0000','01234 V GOOG:12 APPL:13 YHOO:45 NYSE:56','1970-01-17 04:52:25','A'),
	(174,'7664263','0000','01234 P GOOG:12 APPL:13 YHOO:45 NYSE:56','1970-01-17 04:52:25','A'),
	(175,'7664255','0000','0012 M GOOG:12 APPL:13 YHOO:45 NYSE:56','1970-01-17 04:52:25','A'),
	(176,'7664244','0000','01234 M JFK:68 GOOG:89 APPL:90','1970-01-17 04:52:25','A'),
	(177,'7653811','2131','We congratulate you! It is our duty to reward loyal customers like you with special offers: 3 FREE Kitchen tips! To claim it, reply OK.\r\n','1970-01-17 04:51:39','A'),
	(178,'7637777','+233209154458','How long would it take to build a website for &quot;campaign &quot; and how much ?','1970-01-17 04:51:16','A'),
	(179,'7632137','Esoko','hello','1970-01-17 04:50:05','A'),
	(180,'7632133','1330','esoko : 30.00 GHC top-up credit sent from esoko TXN ID: 6219355 Send money, top-up or pay with txtnpay. Help line:0302902981','1970-01-17 04:50:05','A'),
	(181,'7632126','EVD','You have received 30.00 GHC from 0240968100/233240968100. a/c balance is 44.11 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015092322530139302301253','1970-01-17 04:50:05','A'),
	(182,'8273782','+233244387718','Hello','1970-01-17 05:21:43','A'),
	(183,'8273830','+233244387718','Hello','2015-10-15 22:20:36','A'),
	(184,'8273833','+233244387718','Tithe','2015-10-15 22:22:03','A'),
	(185,'8273865','+233244387718','Tithe','2015-10-15 22:26:42','A'),
	(186,'8274909','+233244387718','Tithe','2015-10-15 22:38:21','A'),
	(187,'8274927','+233244387718','Tithe','2015-10-15 22:40:26','A'),
	(188,'8275007','+233244387718','Tithe','2015-10-15 22:45:40','A'),
	(189,'8275058','+233244387718','Tithe','2015-10-15 22:48:06','A'),
	(190,'8275070','+233244387718','Class','2015-10-15 22:49:28','A'),
	(191,'8275105','+233244387718','Class','2015-10-15 22:55:02','A'),
	(192,'8275118','+233244387718','Class','2015-10-15 22:57:52','A'),
	(193,'8275130','+233244387718','Help','2015-10-15 23:00:54','A'),
	(194,'8275138','+233244387718','Help','2015-10-15 23:01:24','A'),
	(195,'8276199','+233244387718','Set announce hello everybody','2015-10-15 23:11:32','A'),
	(196,'8276202','mt. olivet','hello everybody','2015-10-15 23:11:48','A'),
	(197,'8296743','Surfline','Enjoy 5% discount on your Surfline bundle with Slydepay!\r\nPay using the app or visit www.airty.me. Click this link for more details: http://goo.gl/rA0fRL\r\n','2015-10-16 18:38:18','A'),
	(198,'8287103','MTN','Cycling can be awesome too! http://bit.ly/1Qnd5fk. Enjoy more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-10-16 18:38:19','A'),
	(199,'8285041','Vodafone','Get 10 extra minutes free! Simply call family and friends in Nigeria. Talk for 50mins or more and get 10 extra minutes to call Vodafone numbers the next day.','2015-10-16 18:38:19','A'),
	(200,'8299540','mt. olivet','You are 30 years today Fetz.\nMt. Olivet and the entire congregation wishes you a Happy Birthday. God richly bless and strengthens you for more years to come.','2015-10-16 19:16:48','A'),
	(201,'8318948','VFCash','Register for Vodafone Cash, the world  s number #1 mobile money service. Please visit our agents or retail shops with a valid photo ID now.','2015-10-17 20:41:08','A'),
	(202,'8318035','mt. olivet','hello','2015-10-17 20:41:18','A'),
	(203,'8316021','Google','Your Google verification code is 861052','2015-10-17 20:41:26','A'),
	(204,'8330261','+233244387718','Class','2015-10-17 20:51:43','A'),
	(205,'8330271','+233244387718','Tithe','2015-10-17 20:53:38','A'),
	(206,'8330309','+233244387718','Help','2015-10-17 21:06:53','A'),
	(207,'8330322','+233244387718','Set birthday happy birthday to you, and God richly bless you.','2015-10-17 21:09:22','A'),
	(208,'8330630','+233244387718','Cls','2015-10-17 21:41:17','A'),
	(209,'8330628','+233244387718','Clasa','2015-10-17 21:41:17','A'),
	(210,'8330680','+233244387718','Cls','2015-10-17 22:04:15','A'),
	(211,'8330692','+233244387718','Tithe','2015-10-17 22:05:48','A'),
	(212,'8330698','+233244387718','Help','2015-10-17 22:16:04','A'),
	(213,'8330739','+233244387718','Class','2015-10-17 22:17:24','A'),
	(214,'8330753','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-10-17 22:22:08','A'),
	(215,'8330754','555','Your main balance is GHc 30.10040 valid until 15-01-2016. Yee Twi Kor promo is live! Win free cash or airtime instantly. Just top up with a recharge card today.','2015-10-17 22:22:20','A'),
	(216,'8330757','Esoko','hello','2015-10-17 22:23:18','A'),
	(217,'8330756','EVD','You have received 30.00 GHC from 0240968100/233240968100. a/c balance is 34.06 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015101722220540402595967','2015-10-17 22:23:18','A'),
	(218,'8330760','+233244387718','Tithes','2015-10-17 22:24:15','A'),
	(219,'8330768','+233244387718','Tit','2015-10-17 22:28:16','A'),
	(220,'8330796','Esoko','hello','2015-10-17 22:32:15','A'),
	(221,'8330804','+233244387718','Class','2015-10-17 22:34:09','A'),
	(222,'8330842','mt. olivet','Thank you, Fetz. Mt. Olivet Society has acknowledged 200.0 GHS as tithe paid in October. \n Visit the website or Text TITHE,SERMON,EVENT or INFO to +233244960321 for more information. God richly bless you!','2015-10-17 22:41:34','A'),
	(223,'8330918','+233244387718','Tithes','2015-10-17 23:02:02','A'),
	(224,'8339629','Surfline','Dear Customer, you have successfully renewed your  1GB bundle on 233255062021, valid for 30 days . Please log on to MySurfline to track your data usage.','2015-10-18 19:52:14','A'),
	(225,'8338018','Surfline','Dear Customer, your main data bundle on 233255062021  will expire in the next 24hours ( 19/10/2015 11:52:18). Please log on to MySurfline to buy a new bundle to ensure uninterrupted browsing.','2015-10-18 19:52:24','A'),
	(226,'8336888','EVD','You have successfully transferred amount 3.00 GHC to 233240020588 on Oct 17, 2015. Your updated balance is 4.06 GHC.Transaction ID: 2015101711331391601248617','2015-10-18 19:52:32','A'),
	(227,'8335590','+233244264760','Hi bro hope you great, this is Nhyira Ba hope u hv not forgetting dat u promised me Game of thrones se 5 and some series. Will bring my ipad n ex HDD to church.','2015-10-18 19:52:39','A'),
	(228,'8349073','+233244387718','Tithes','2015-10-18 20:45:59','A'),
	(229,'8349010','EVD','You have successfully transferred amount 5.00 GHC to 233240020588 on Oct 18, 2015. Your updated balance is 28.27 GHC.Transaction ID: 2015101820364366504897317','2015-10-18 20:46:00','A'),
	(230,'8350502','+233244387718','Cls','2015-10-19 00:32:33','A'),
	(231,'8349833','C Cradle','[Get] Worked!','2015-10-19 00:32:34','A'),
	(232,'8349719','+233244387718','Cls','2015-10-19 00:32:34','A'),
	(233,'8349193','+233244387718','Cls','2015-10-19 00:32:34','A'),
	(234,'8349169','+233244387718','Class','2015-10-19 00:32:35','A'),
	(235,'8349109','+233244387718','Tithes','2015-10-19 00:32:35','A'),
	(236,'8349084','+233244387718','Class','2015-10-19 00:32:35','A'),
	(237,'8353000','+233244387718','Class','2015-10-19 07:12:34','A'),
	(238,'8353074','+233244387718','Tithes','2015-10-19 07:21:20','A'),
	(239,'8353149','+233244387718','Class','2015-10-19 07:27:19','A'),
	(240,'8353225','+233244387718','Tithes','2015-10-19 07:33:01','A'),
	(241,'8353284','+233244387718','Class','2015-10-19 07:36:48','A'),
	(242,'8353890','+233244387718','Tithes','2015-10-19 07:43:55','A'),
	(243,'8354457','+233244387718','Cls','2015-10-19 07:47:51','A'),
	(244,'8354768','+233244387718','Tithes','2015-10-19 07:56:08','A'),
	(245,'8354939','+233244387718','Classes','2015-10-19 08:16:54','A'),
	(246,'8354841','+233244387718','Cls','2015-10-19 08:16:55','A'),
	(247,'8354948','+233244387718','Tithe','2015-10-19 08:18:19','A'),
	(248,'8354961','+233244387718','Help','2015-10-19 08:20:08','A'),
	(249,'8354967','+233244348020','Tithe','2015-10-19 08:21:37','A'),
	(250,'8355031','+233244348020','Tithe','2015-10-19 08:30:27','A'),
	(251,'8355035','+233244387718','Tithe','2015-10-19 08:31:18','A'),
	(252,'8355031','+233244348020','Tithe','2015-10-19 08:31:19','A'),
	(253,'8355036','+233244348020','Tithe','2015-10-19 08:32:08','A'),
	(254,'8355066','+233244348020','Classes','2015-10-19 08:37:45','A'),
	(255,'8355197','+233244348020','Classes','2015-10-19 08:50:55','A'),
	(256,'8355264','+233244348020','Classes','2015-10-19 08:55:29','A'),
	(257,'8355293','+233244348020','Classes','2015-10-19 08:59:17','A'),
	(258,'8355316','+233244348020','Classes','2015-10-19 09:01:24','A'),
	(259,'8355341','+233244348020','Classes','2015-10-19 09:03:25','A'),
	(260,'8355388','+233244348020','Classes','2015-10-19 09:06:30','A'),
	(261,'8357772','wish','test msg from sandeep-route:gh3','2015-10-19 11:22:13','A'),
	(262,'8359459','webed','test','2015-10-19 11:52:15','A'),
	(263,'8359512','web','test2','2015-10-19 11:53:34','A'),
	(264,'8362772','+233504298056','Tithe','2015-10-19 12:27:11','A'),
	(265,'8362778','+233504298056','Tithe','2015-10-19 12:27:15','A'),
	(266,'8362819','mt. olivet','Hello Java, this is your tithe report for 2015\n\nJanuary: 0.0 GHS\nFebruary: 0.0 GHS\nMarch: 0.0 GHS\nApril: 0.0 GHS\nMay: 0.0 GHS\nJune: 0.0 GHS\nJuly: 0.0 GHS\nAugust: 0.0 GHS\nSeptember: 0.0 GHS\nOctober: 0.0 GHS\n\nGod richly bless you.','2015-10-19 12:27:38','A'),
	(267,'8362836','mt. olivet','Hello Java, this is your tithe report for 2015\n\nJanuary: 0.0 GHS\nFebruary: 0.0 GHS\nMarch: 0.0 GHS\nApril: 0.0 GHS\nMay: 0.0 GHS\nJune: 0.0 GHS\nJuly: 0.0 GHS\nAugust: 0.0 GHS\nSeptember: 0.0 GHS\nOctober: 0.0 GHS\n\nGod richly bless you.','2015-10-19 12:27:47','A'),
	(268,'8374049','EVD','You have successfully transferred amount 10.00 GHC to 233248242859 on Oct 19, 2015. Your updated balance is 18.08 GHC.Transaction ID: 2015101915325907704449763','2015-10-19 15:34:10','A'),
	(269,'8377888','EVD','You have successfully transferred amount 5.00 GHC to 233244642666 on Oct 19, 2015. Your updated balance is 12.98 GHC.Transaction ID: 2015101916430331304520309','2015-10-19 16:44:25','A'),
	(270,'8390342','TigoCash','Successful Tigo Cash withdrawal: collect GHC 98.00 from Agent DANIEL EGYIR - 0278798007. New Tigo Cash balance: GHC 0.61. 10/19/15 08:33 PM. Ref No 640006365. Thank you','2015-10-19 20:50:56','A'),
	(271,'8397576','mt. olivet','Congratulations Ruby Akorfa. You&#039;ve been successfully added to Mt. Olivet Society. Your password is +233504298056. Please visit www.churchcradle.com, and use your mobile number as both username and password. You can pay and view your tithes, announce','2015-10-20 04:34:59','A'),
	(272,'8413789','+233244348020','Classes','2015-10-20 11:29:15','A'),
	(273,'8415750','Vodafone','Calling Nigeria &amp; South Africa is cheapest on Vodafone! For only GhC 3.9, you get 16mins to call &amp; 2 SMS to text over the next 7 days! Just dial *125*32# today.','2015-10-20 12:19:03','A'),
	(274,'8423557','451','Congratulations Mr Emmanuel Tabi, the Winner of GHS10 000 with MTN Dream Number draw! SMS WIN to 451 and change your life today! SMS WIN to 451 NOW. 60p/day','2015-10-20 14:33:43','A'),
	(275,'8427125','5200','Get 10 extra minutes free! Simple dial *125*30# to get 50mins to call USA, Canada, China and UK landlines for only Ghc5 and get 10 extra minutes to call Vodafone numbers!','2015-10-20 16:00:41','A'),
	(276,'8437515','mt. olivet','Your Church Cradle verification code is cupkkgln','2015-10-20 21:16:03','A'),
	(277,'8456243','+233244348020','Classes','2015-10-21 11:09:55','A'),
	(278,'8497057','Surfline','Dear Customer, our call centre is currently unavailable. You can connect with us on Facebook at SurflineGH and Twitter @SurflineGH ','2015-10-22 10:03:27','A'),
	(279,'8497778','Surfline','Dear Customer, our call centre is currently unavailable. You can connect with us on Facebook at SurflineGH and Twitter @SurflineGH ','2015-10-22 10:20:34','A'),
	(280,'8499655','Surfline','Dear customer, access to our call centre has been fully restored. Thank you!','2015-10-22 12:07:34','A'),
	(281,'8523667','Surfline','Dear customer, access to our call centre has been fully restored. Thank you!','2015-10-23 04:35:32','A'),
	(282,'8530467','Esoko','hello','2015-10-23 07:46:04','A'),
	(283,'8530465','Esoko','hello','2015-10-23 07:46:04','A'),
	(284,'8554841','+233209137263','I.ve sent you an InstaVoice message. To listen, download InstaVoice http://goo.gl/JbqrFo or dial *0* free. ','2015-10-24 06:35:51','A'),
	(285,'8565482','+233500190529','Please pick my call','2015-10-24 20:42:26','A'),
	(286,'8554841','+233209137263','I.ve sent you an InstaVoice message. To listen, download InstaVoice http://goo.gl/JbqrFo or dial *0* free. ','2015-10-24 20:42:27','A'),
	(287,'8567732','Surfline','Dear Valued Customer, You have   1.943GB remaining on 233255062021 . This is valid until 17/11/2015. Kindly log on to MySurfline for more information. Thank you','2015-10-24 21:07:32','A'),
	(288,'8585612','+233500190529','Please i need to talk to you','2015-10-25 17:21:14','A'),
	(289,'8586589','+233500190529','Thank you.','2015-10-25 17:47:27','A'),
	(290,'8591379','mt. olivet','Congratulations John Doe. You&#039;ve been successfully added to Bread Of Life. Your password is +233504298056. Please visit http://churchcradle-1101.appspot.com, and use your mobile number as both username and password. You can pay and view your tithes, ','2015-10-26 03:13:31','A'),
	(291,'8594122','MPower','Deposits and withdrawals on MPower via MTN Mobile Money remain unstable. MTN engineers are working to resolve. All other services are stable. Have a great week.','2015-10-26 08:27:44','A'),
	(292,'8596491','GloGh','Sorry, your account is inactive. Please refill your account before 20/12/2015.','2015-10-26 09:45:26','A'),
	(293,'8596518','GLO','Dear Glo subscriber, you are about to receive settings. Please use PIN 1111 to install the settings. Stand Proud, Glo!','2015-10-26 09:46:35','A'),
	(294,'8596523','GLO','Dear Glo subscriber, if you have saved the settings, \r\nyou can send and receive pictures and use the internet. \r\nCall 121 for more Information. Stand Proud, Glo','2015-10-26 09:46:47','A'),
	(295,'8596529','GloGh','Sorry, your account is inactive. Please refill your account before 20/12/2015.','2015-10-26 09:46:58','A'),
	(296,'8596528','GloGh','Sorry, your account is inactive. Please refill your account before 20/12/2015.','2015-10-26 09:46:58','A'),
	(297,'8596534','GloGh','Sorry, your account is inactive. Please refill your account before 20/12/2015.','2015-10-26 09:47:24','A'),
	(298,'8596644','GloGh','You have been credited with Ghc 2.000 &amp; your main a/c is Ghc 4.008.','2015-10-26 09:53:26','A'),
	(299,'8596643','GloGh','You have earned a Recharge bonus of Ghc 0.600 valid until 29/10/2015.','2015-10-26 09:53:26','A'),
	(300,'8596727','5151','Dear Customer the confirmation code for password reset of your Esoko Account  is 109507.Please do not share the confirmation code and it expires in 30 minutes.','2015-10-26 09:55:40','A'),
	(301,'8597284','Surfline','Dear Valued Customer, You have   0.970GB remaining on 233255062021. This is valid until 17/11/2015 . Kindly log on to MySurfline for more information. Thank you','2015-10-26 10:19:51','A'),
	(302,'8597902','Esoko','hello','2015-10-26 10:35:45','A'),
	(303,'8597941','Esoko','hello','2015-10-26 10:36:33','A'),
	(304,'8597940','EVD','You have received 30.00 GHC from 0240968100/233240968100. a/c balance is 30.19 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015102610351502302759549','2015-10-26 10:36:33','A'),
	(305,'8598387','Esoko','hello','2015-10-26 10:48:07','A'),
	(306,'8598390','EVD','You have received 37.00 GHC from 0240968100/233240968100. a/c balance is 67.19 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015102610465658402769940','2015-10-26 10:48:23','A'),
	(307,'8598402','Esoko','hello','2015-10-26 10:49:09','A'),
	(308,'8598401','EVD','You have received 37.00 GHC from 0240968100/233240968100. a/c balance is 104.19 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015102610475151102770712','2015-10-26 10:49:09','A'),
	(309,'8598475','EVD','You have received 37.00 GHC from 0240968100/233240968100. a/c balance is 141.19 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015102610485021702771562','2015-10-26 10:49:59','A'),
	(310,'8598478','Esoko','hello','2015-10-26 10:50:04','A'),
	(311,'8598521','Esoko','hello','2015-10-26 10:50:48','A'),
	(312,'8598520','EVD','You have received 37.00 GHC from 0240968100/233240968100. a/c balance is 178.19 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015102610493486702772219','2015-10-26 10:50:48','A'),
	(313,'8598665','Esoko','hello','2015-10-26 10:56:30','A'),
	(314,'8598678','Esoko','hello','2015-10-26 10:57:26','A'),
	(315,'8599223','555','Your main balance is GHc 125.29040 valid until 24-01-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-10-26 11:15:29','A'),
	(316,'8599802','138','Internet Usage Flash: Credit 437.50MB, Time Left 9 day(s).','2015-10-26 11:31:10','A'),
	(317,'8639474','EVD','You have successfully transferred amount 5.00 GHC to 233240020588 on Oct 27, 2015. Your updated balance is 173.04 GHC.Transaction ID: 2015102712492006502929103','2015-10-27 12:50:35','A'),
	(318,'8647391','MTN','Being awesome is a lifestyle http://bit.ly/1k6BSKg .Choose to be awesome .Enjoy more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-10-27 15:02:58','A'),
	(319,'8660872','+233546706099','A beg u fit hala me some crdts on my vodafone line i want take do bundle my number dis ( 0209520106)','2015-10-27 20:17:48','A'),
	(320,'8660932','+233546706099','School is stressing me out tho ','2015-10-27 20:19:21','A'),
	(321,'8660967','+233546706099','Pls lemme know when u pls forward me the cdts','2015-10-27 20:20:18','A'),
	(322,'8661020','+121','You have transferred GHc5.00000 credit to 233209520106. Your own account balance is GHc120.12040.','2015-10-27 20:21:40','A'),
	(323,'8669132','MPower','Avoid the end of month traffic! Pay all your bills at www.mpower.com.gh or dial *714*44#. Buy airtime credits, Surfline bundle; pay DSTV &amp; ECG Postpaid bills','2015-10-28 09:16:47','A'),
	(324,'8669148','MPower','Avoid the end of month traffic! Pay all your bills at www.mpower.com.gh or dial *714*44#. Buy airtime credits, Surfline bundle; pay DSTV &amp; ECG Postpaid bills','2015-10-28 09:20:00','A'),
	(325,'8703877','1303','Do you want to express yourself? Dial 130330 and sing that song in your head. Record and MTN will rate your performance. To cancel send STOP to 1303.','2015-10-29 16:04:15','A'),
	(326,'8708477','+121','You have transferred GHc15.00000 credit to 233209137263. Your own account balance is GHc84.83040.','2015-10-29 18:07:30','A'),
	(327,'8745148','mt. olivet','hello Sels','2015-10-31 08:30:49','A'),
	(328,'8717586','MTN','Unleash the real you! Try something different! http://bit.ly/1kL6n8Z .Unleash more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-10-31 08:30:50','A'),
	(329,'8802281','+233240020588','I.ve sent you an InstaVoice message. To listen, download InstaVoice http://goo.gl/JbqrFo or dial *0* free. ','2015-11-01 04:36:05','A'),
	(330,'8745148','mt. olivet','hello Sels','2015-11-01 04:36:05','A'),
	(331,'8717586','MTN','Unleash the real you! Try something different! http://bit.ly/1kL6n8Z .Unleash more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-01 04:36:05','A'),
	(332,'8708477','+121','You have transferred GHc15.00000 credit to 233209137263. Your own account balance is GHc84.83040.','2015-11-01 04:36:05','A'),
	(333,'8703877','1303','Do you want to express yourself? Dial 130330 and sing that song in your head. Record and MTN will rate your performance. To cancel send STOP to 1303.','2015-11-01 04:36:05','A'),
	(334,'8669148','MPower','Avoid the end of month traffic! Pay all your bills at www.mpower.com.gh or dial *714*44#. Buy airtime credits, Surfline bundle; pay DSTV &amp; ECG Postpaid bills','2015-11-01 04:36:05','A'),
	(335,'8669132','MPower','Avoid the end of month traffic! Pay all your bills at www.mpower.com.gh or dial *714*44#. Buy airtime credits, Surfline bundle; pay DSTV &amp; ECG Postpaid bills','2015-11-01 04:36:05','A'),
	(336,'8661020','+121','You have transferred GHc5.00000 credit to 233209520106. Your own account balance is GHc120.12040.','2015-11-01 04:36:05','A'),
	(337,'8660967','+233546706099','Pls lemme know when u pls forward me the cdts','2015-11-01 04:36:05','A'),
	(338,'8660932','+233546706099','School is stressing me out tho ','2015-11-01 04:36:05','A'),
	(339,'8660872','+233546706099','A beg u fit hala me some crdts on my vodafone line i want take do bundle my number dis ( 0209520106)','2015-11-01 04:36:05','A'),
	(340,'8647391','MTN','Being awesome is a lifestyle http://bit.ly/1k6BSKg .Choose to be awesome .Enjoy more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-01 04:36:05','A'),
	(341,'8639474','EVD','You have successfully transferred amount 5.00 GHC to 233240020588 on Oct 27, 2015. Your updated balance is 173.04 GHC.Transaction ID: 2015102712492006502929103','2015-11-01 04:36:05','A'),
	(342,'8599802','138','Internet Usage Flash: Credit 437.50MB, Time Left 9 day(s).','2015-11-01 04:36:05','A'),
	(343,'8599223','555','Your main balance is GHc 125.29040 valid until 24-01-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-11-01 04:36:05','A'),
	(344,'8598678','Esoko','hello','2015-11-01 04:36:05','A'),
	(345,'8598665','Esoko','hello','2015-11-01 04:36:05','A'),
	(346,'8598521','Esoko','hello','2015-11-01 04:36:05','A'),
	(347,'8598520','EVD','You have received 37.00 GHC from 0240968100/233240968100. a/c balance is 178.19 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015102610493486702772219','2015-11-01 04:36:05','A'),
	(348,'8598478','Esoko','hello','2015-11-01 04:36:05','A'),
	(349,'8598475','EVD','You have received 37.00 GHC from 0240968100/233240968100. a/c balance is 141.19 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015102610485021702771562','2015-11-01 04:36:06','A'),
	(350,'8598402','Esoko','hello','2015-11-01 04:36:06','A'),
	(351,'8598401','EVD','You have received 37.00 GHC from 0240968100/233240968100. a/c balance is 104.19 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015102610475151102770712','2015-11-01 04:36:06','A'),
	(352,'8598390','EVD','You have received 37.00 GHC from 0240968100/233240968100. a/c balance is 67.19 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015102610465658402769940','2015-11-01 04:36:06','A'),
	(353,'8598387','Esoko','hello','2015-11-01 04:36:06','A'),
	(354,'8597941','Esoko','hello','2015-11-01 04:36:06','A'),
	(355,'8597940','EVD','You have received 30.00 GHC from 0240968100/233240968100. a/c balance is 30.19 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015102610351502302759549','2015-11-01 04:36:06','A'),
	(356,'8597902','Esoko','hello','2015-11-01 04:36:06','A'),
	(357,'8597284','Surfline','Dear Valued Customer, You have   0.970GB remaining on 233255062021. This is valid until 17/11/2015 . Kindly log on to MySurfline for more information. Thank you','2015-11-01 04:36:06','A'),
	(358,'8596727','5151','Dear Customer the confirmation code for password reset of your Esoko Account  is 109507.Please do not share the confirmation code and it expires in 30 minutes.','2015-11-01 04:36:06','A'),
	(359,'8596644','GloGh','You have been credited with Ghc 2.000 &amp; your main a/c is Ghc 4.008.','2015-11-01 04:36:06','A'),
	(360,'8596643','GloGh','You have earned a Recharge bonus of Ghc 0.600 valid until 29/10/2015.','2015-11-01 04:36:06','A'),
	(361,'8596534','GloGh','Sorry, your account is inactive. Please refill your account before 20/12/2015.','2015-11-01 04:36:06','A'),
	(362,'8596529','GloGh','Sorry, your account is inactive. Please refill your account before 20/12/2015.','2015-11-01 04:36:06','A'),
	(363,'8596528','GloGh','Sorry, your account is inactive. Please refill your account before 20/12/2015.','2015-11-01 04:36:06','A'),
	(364,'8596523','GLO','Dear Glo subscriber, if you have saved the settings, \r\nyou can send and receive pictures and use the internet. \r\nCall 121 for more Information. Stand Proud, Glo','2015-11-01 04:36:06','A'),
	(365,'8596518','GLO','Dear Glo subscriber, you are about to receive settings. Please use PIN 1111 to install the settings. Stand Proud, Glo!','2015-11-01 04:36:06','A'),
	(366,'8596491','GloGh','Sorry, your account is inactive. Please refill your account before 20/12/2015.','2015-11-01 04:36:06','A'),
	(367,'8594122','MPower','Deposits and withdrawals on MPower via MTN Mobile Money remain unstable. MTN engineers are working to resolve. All other services are stable. Have a great week.','2015-11-01 04:36:06','A'),
	(368,'8591379','mt. olivet','Congratulations John Doe. You&#039;ve been successfully added to Bread Of Life. Your password is +233504298056. Please visit http://churchcradle-1101.appspot.com, and use your mobile number as both username and password. You can pay and view your tithes, ','2015-11-01 04:36:06','A'),
	(369,'8586589','+233500190529','Thank you.','2015-11-01 04:36:06','A'),
	(370,'8585612','+233500190529','Please i need to talk to you','2015-11-01 04:36:06','A'),
	(371,'8567732','Surfline','Dear Valued Customer, You have   1.943GB remaining on 233255062021 . This is valid until 17/11/2015. Kindly log on to MySurfline for more information. Thank you','2015-11-01 04:36:06','A'),
	(372,'8565482','+233500190529','Please pick my call','2015-11-01 04:36:07','A'),
	(373,'8554841','+233209137263','I.ve sent you an InstaVoice message. To listen, download InstaVoice http://goo.gl/JbqrFo or dial *0* free. ','2015-11-01 04:36:07','A'),
	(374,'8530467','Esoko','hello','2015-11-01 04:36:07','A'),
	(375,'8530465','Esoko','hello','2015-11-01 04:36:07','A'),
	(376,'8523667','Surfline','Dear customer, access to our call centre has been fully restored. Thank you!','2015-11-01 04:36:07','A'),
	(377,'8499655','Surfline','Dear customer, access to our call centre has been fully restored. Thank you!','2015-11-01 04:36:07','A'),
	(378,'8497778','Surfline','Dear Customer, our call centre is currently unavailable. You can connect with us on Facebook at SurflineGH and Twitter @SurflineGH ','2015-11-01 04:36:07','A'),
	(379,'8497057','Surfline','Dear Customer, our call centre is currently unavailable. You can connect with us on Facebook at SurflineGH and Twitter @SurflineGH ','2015-11-01 04:36:07','A'),
	(380,'8456243','+233244348020','Classes','2015-11-01 04:36:07','A'),
	(381,'8437515','mt. olivet','Your Church Cradle verification code is cupkkgln','2015-11-01 04:36:07','A'),
	(382,'8427125','5200','Get 10 extra minutes free! Simple dial *125*30# to get 50mins to call USA, Canada, China and UK landlines for only Ghc5 and get 10 extra minutes to call Vodafone numbers!','2015-11-01 04:36:07','A'),
	(383,'8423557','451','Congratulations Mr Emmanuel Tabi, the Winner of GHS10 000 with MTN Dream Number draw! SMS WIN to 451 and change your life today! SMS WIN to 451 NOW. 60p/day','2015-11-01 04:36:07','A'),
	(384,'8415750','Vodafone','Calling Nigeria &amp; South Africa is cheapest on Vodafone! For only GhC 3.9, you get 16mins to call &amp; 2 SMS to text over the next 7 days! Just dial *125*32# today.','2015-11-01 04:36:07','A'),
	(385,'8413789','+233244348020','Classes','2015-11-01 04:36:07','A'),
	(386,'8397576','mt. olivet','Congratulations Ruby Akorfa. You&#039;ve been successfully added to Mt. Olivet Society. Your password is +233504298056. Please visit www.churchcradle.com, and use your mobile number as both username and password. You can pay and view your tithes, announce','2015-11-01 04:36:07','A'),
	(387,'8390342','TigoCash','Successful Tigo Cash withdrawal: collect GHC 98.00 from Agent DANIEL EGYIR - 0278798007. New Tigo Cash balance: GHC 0.61. 10/19/15 08:33 PM. Ref No 640006365. Thank you','2015-11-01 04:36:07','A'),
	(388,'8377888','EVD','You have successfully transferred amount 5.00 GHC to 233244642666 on Oct 19, 2015. Your updated balance is 12.98 GHC.Transaction ID: 2015101916430331304520309','2015-11-01 04:36:07','A'),
	(389,'8374049','EVD','You have successfully transferred amount 10.00 GHC to 233248242859 on Oct 19, 2015. Your updated balance is 18.08 GHC.Transaction ID: 2015101915325907704449763','2015-11-01 04:36:07','A'),
	(390,'8362836','mt. olivet','Hello Java, this is your tithe report for 2015\n\nJanuary: 0.0 GHS\nFebruary: 0.0 GHS\nMarch: 0.0 GHS\nApril: 0.0 GHS\nMay: 0.0 GHS\nJune: 0.0 GHS\nJuly: 0.0 GHS\nAugust: 0.0 GHS\nSeptember: 0.0 GHS\nOctober: 0.0 GHS\n\nGod richly bless you.','2015-11-01 04:36:07','A'),
	(391,'8362819','mt. olivet','Hello Java, this is your tithe report for 2015\n\nJanuary: 0.0 GHS\nFebruary: 0.0 GHS\nMarch: 0.0 GHS\nApril: 0.0 GHS\nMay: 0.0 GHS\nJune: 0.0 GHS\nJuly: 0.0 GHS\nAugust: 0.0 GHS\nSeptember: 0.0 GHS\nOctober: 0.0 GHS\n\nGod richly bless you.','2015-11-01 04:36:07','A'),
	(392,'8362778','+233504298056','Tithe','2015-11-01 04:36:07','A'),
	(393,'8362772','+233504298056','Tithe','2015-11-01 04:36:07','A'),
	(394,'8359512','web','test2','2015-11-01 04:36:08','A'),
	(395,'8359459','webed','test','2015-11-01 04:36:08','A'),
	(396,'8357772','wish','test msg from sandeep-route:gh3','2015-11-01 04:36:08','A'),
	(397,'8355388','+233244348020','Classes','2015-11-01 04:36:08','A'),
	(398,'8355341','+233244348020','Classes','2015-11-01 04:36:08','A'),
	(399,'8355316','+233244348020','Classes','2015-11-01 04:36:08','A'),
	(400,'8355293','+233244348020','Classes','2015-11-01 04:36:08','A'),
	(401,'8355264','+233244348020','Classes','2015-11-01 04:36:08','A'),
	(402,'8355197','+233244348020','Classes','2015-11-01 04:36:08','A'),
	(403,'8355066','+233244348020','Classes','2015-11-01 04:36:08','A'),
	(404,'8355036','+233244348020','Tithe','2015-11-01 04:36:08','A'),
	(405,'8355035','+233244387718','Tithe','2015-11-01 04:36:08','A'),
	(406,'8355031','+233244348020','Tithe','2015-11-01 04:36:08','A'),
	(407,'8354967','+233244348020','Tithe','2015-11-01 04:36:08','A'),
	(408,'8354961','+233244387718','Help','2015-11-01 04:36:08','A'),
	(409,'8354948','+233244387718','Tithe','2015-11-01 04:36:08','A'),
	(410,'8354939','+233244387718','Classes','2015-11-01 04:36:08','A'),
	(411,'8354841','+233244387718','Cls','2015-11-01 04:36:08','A'),
	(412,'8354768','+233244387718','Tithes','2015-11-01 04:36:08','A'),
	(413,'8354457','+233244387718','Cls','2015-11-01 04:36:08','A'),
	(414,'8353890','+233244387718','Tithes','2015-11-01 04:36:08','A'),
	(415,'8353284','+233244387718','Class','2015-11-01 04:36:08','A'),
	(416,'8353225','+233244387718','Tithes','2015-11-01 04:36:08','A'),
	(417,'8353149','+233244387718','Class','2015-11-01 04:36:08','A'),
	(418,'8353074','+233244387718','Tithes','2015-11-01 04:36:09','A'),
	(419,'8353000','+233244387718','Class','2015-11-01 04:36:09','A'),
	(420,'8350502','+233244387718','Cls','2015-11-01 04:36:09','A'),
	(421,'8349833','C Cradle','[Get] Worked!','2015-11-01 04:36:09','A'),
	(422,'8349719','+233244387718','Cls','2015-11-01 04:36:09','A'),
	(423,'8349193','+233244387718','Cls','2015-11-01 04:36:09','A'),
	(424,'8349169','+233244387718','Class','2015-11-01 04:36:09','A'),
	(425,'8349109','+233244387718','Tithes','2015-11-01 04:36:09','A'),
	(426,'8349084','+233244387718','Class','2015-11-01 04:36:09','A'),
	(427,'8349073','+233244387718','Tithes','2015-11-01 04:36:09','A'),
	(428,'8349010','EVD','You have successfully transferred amount 5.00 GHC to 233240020588 on Oct 18, 2015. Your updated balance is 28.27 GHC.Transaction ID: 2015101820364366504897317','2015-11-01 04:36:09','A'),
	(429,'8339629','Surfline','Dear Customer, you have successfully renewed your  1GB bundle on 233255062021, valid for 30 days . Please log on to MySurfline to track your data usage.','2015-11-01 04:36:09','A'),
	(430,'8338018','Surfline','Dear Customer, your main data bundle on 233255062021  will expire in the next 24hours ( 19/10/2015 11:52:18). Please log on to MySurfline to buy a new bundle to ensure uninterrupted browsing.','2015-11-01 04:36:09','A'),
	(431,'8336888','EVD','You have successfully transferred amount 3.00 GHC to 233240020588 on Oct 17, 2015. Your updated balance is 4.06 GHC.Transaction ID: 2015101711331391601248617','2015-11-01 04:36:09','A'),
	(432,'8335590','+233244264760','Hi bro hope you great, this is Nhyira Ba hope u hv not forgetting dat u promised me Game of thrones se 5 and some series. Will bring my ipad n ex HDD to church.','2015-11-01 04:36:09','A'),
	(433,'8330918','+233244387718','Tithes','2015-11-01 04:36:09','A'),
	(434,'8330842','mt. olivet','Thank you, Fetz. Mt. Olivet Society has acknowledged 200.0 GHS as tithe paid in October. \n Visit the website or Text TITHE,SERMON,EVENT or INFO to +233244960321 for more information. God richly bless you!','2015-11-01 04:36:09','A'),
	(435,'8330804','+233244387718','Class','2015-11-01 04:36:09','A'),
	(436,'8330796','Esoko','hello','2015-11-01 04:36:09','A'),
	(437,'8330768','+233244387718','Tit','2015-11-01 04:36:09','A'),
	(438,'8330760','+233244387718','Tithes','2015-11-01 04:36:09','A'),
	(439,'8330757','Esoko','hello','2015-11-01 04:36:09','A'),
	(440,'8330756','EVD','You have received 30.00 GHC from 0240968100/233240968100. a/c balance is 34.06 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015101722220540402595967','2015-11-01 04:36:09','A'),
	(441,'8330754','555','Your main balance is GHc 30.10040 valid until 15-01-2016. Yee Twi Kor promo is live! Win free cash or airtime instantly. Just top up with a recharge card today.','2015-11-01 04:36:09','A'),
	(442,'8330753','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-01 04:36:10','A'),
	(443,'8330739','+233244387718','Class','2015-11-01 04:36:10','A'),
	(444,'8330698','+233244387718','Help','2015-11-01 04:36:10','A'),
	(445,'8330692','+233244387718','Tithe','2015-11-01 04:36:10','A'),
	(446,'8330680','+233244387718','Cls','2015-11-01 04:36:10','A'),
	(447,'8330630','+233244387718','Cls','2015-11-01 04:36:10','A'),
	(448,'8330628','+233244387718','Clasa','2015-11-01 04:36:10','A'),
	(449,'8330322','+233244387718','Set birthday happy birthday to you, and God richly bless you.','2015-11-01 04:36:10','A'),
	(450,'8330309','+233244387718','Help','2015-11-01 04:36:10','A'),
	(451,'8330271','+233244387718','Tithe','2015-11-01 04:36:10','A'),
	(452,'8330261','+233244387718','Class','2015-11-01 04:36:10','A'),
	(453,'8318948','VFCash','Register for Vodafone Cash, the world  s number #1 mobile money service. Please visit our agents or retail shops with a valid photo ID now.','2015-11-01 04:36:10','A'),
	(454,'8318035','mt. olivet','hello','2015-11-01 04:36:10','A'),
	(455,'8316021','Google','Your Google verification code is 861052','2015-11-01 04:36:10','A'),
	(456,'8299540','mt. olivet','You are 30 years today Fetz.\nMt. Olivet and the entire congregation wishes you a Happy Birthday. God richly bless and strengthens you for more years to come.','2015-11-01 04:36:10','A'),
	(457,'8296743','Surfline','Enjoy 5% discount on your Surfline bundle with Slydepay!\r\nPay using the app or visit www.airty.me. Click this link for more details: http://goo.gl/rA0fRL\r\n','2015-11-01 04:36:10','A'),
	(458,'8287103','MTN','Cycling can be awesome too! http://bit.ly/1Qnd5fk. Enjoy more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-01 04:36:10','A'),
	(459,'8285041','Vodafone','Get 10 extra minutes free! Simply call family and friends in Nigeria. Talk for 50mins or more and get 10 extra minutes to call Vodafone numbers the next day.','2015-11-01 04:36:10','A'),
	(460,'8276202','mt. olivet','hello everybody','2015-11-01 04:36:10','A'),
	(461,'8276199','+233244387718','Set announce hello everybody','2015-11-01 04:36:11','A'),
	(462,'8275138','+233244387718','Help','2015-11-01 04:36:11','A'),
	(463,'8275130','+233244387718','Help','2015-11-01 04:36:11','A'),
	(464,'8275118','+233244387718','Class','2015-11-01 04:36:11','A'),
	(465,'8275105','+233244387718','Class','2015-11-01 04:36:11','A'),
	(466,'8275070','+233244387718','Class','2015-11-01 04:36:11','A'),
	(467,'8275058','+233244387718','Tithe','2015-11-01 04:36:11','A'),
	(468,'8275007','+233244387718','Tithe','2015-11-01 04:36:11','A'),
	(469,'8274927','+233244387718','Tithe','2015-11-01 04:36:11','A'),
	(470,'8274909','+233244387718','Tithe','2015-11-01 04:36:11','A'),
	(471,'8273865','+233244387718','Tithe','2015-11-01 04:36:11','A'),
	(472,'8273833','+233244387718','Tithe','2015-11-01 04:36:11','A'),
	(473,'8273830','+233244387718','Hello','2015-11-01 04:36:11','A'),
	(474,'8273782','+233244387718','Hello','2015-11-01 04:36:11','A'),
	(475,'8269128','MPower','MTN Mobile Money will be interrupted on Oct. 16 and restored by Oct. 19. Alternatively pay with MPower via bank cards, Airtel Money, Tigo Cash and tokens.','2015-11-01 04:36:11','A'),
	(476,'8256133','uniBank','Dear Valued Customer,\r\nHave you run out of units on your ECG prepaid meter and need to top up or are you an ECG vendor. \r\nVisit our Osu Main, Oxford Street, Accra Main, Makola, GIMPA, Madina, and East Legon branches to buy your ECG prepaid units if your l','2015-11-01 04:36:11','A'),
	(477,'8231348','mt. olivet','Thank you, Fetz. Mt. Olivet Society has acknowledged 56.0 GHS as tithe paid in October. \n Visit the website or Text TITHE,SERMON,EVENT or INFO to +233244960321 for more information. God richly bless you!','2015-11-01 04:36:11','A'),
	(478,'8231334','mt. olivet','Thank you, Fetz. Mt. Olivet Society has received 20.0 GHS as offering to the Lord. God richly bless you!','2015-11-01 04:36:11','A'),
	(479,'8231306','C Cradle','10.0 GHS has been made as a donation to the church. Thank you!.\n2015-10-15','2015-11-01 04:36:11','A'),
	(480,'8231263','C Cradle','11.0 GHS has been made as a donation to the church. Thank you!.\n2015-10-15','2015-11-01 04:36:11','A'),
	(481,'8219687','mt. olivet','hello','2015-11-01 04:36:11','A'),
	(482,'8217475','weber','test','2015-11-01 04:36:11','A'),
	(483,'8217450','web','test','2015-11-01 04:36:11','A'),
	(484,'8211465','Surfline','Dear Customer, your main data bundle on 233255062021 will expire in 5 days( 19/10/2015 11:52:18).Please log on to MySurfline to buy a new bundle to ensure uninterrupted browsing.Thank You','2015-11-01 04:36:12','A'),
	(485,'8206258','web','test','2015-11-01 04:36:12','A'),
	(486,'8206232','web','test','2015-11-01 04:36:12','A'),
	(487,'8205708','+233209423311','Aaaww thnk u dear','2015-11-01 04:36:12','A'),
	(488,'8205642','EVD','You have successfully transferred amount 30.00 GHC to 233546706099 on Oct 13, 2015. Your updated balance is 28.04 GHC.Transaction ID: 2015101320192574404944390','2015-11-01 04:36:12','A'),
	(489,'8205611','+121','You have transferred GHc30.00000 credit to 233209423311. Your own account balance is GHc12.57040.','2015-11-01 04:36:12','A'),
	(490,'8205599','555','Your main balance is GHc 42.62040 valid until 11-01-2016. Yee Twi Kor promo is live! Win free cash or airtime instantly. Just top up with a recharge card today.','2015-11-01 04:36:12','A'),
	(491,'8205598','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-01 04:36:12','A'),
	(492,'8198086','MTN','Click to see some of the amazing things on earth! http://bit.ly/1Ligzwn with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-01 04:36:12','A'),
	(493,'8186949','555','Dear Customer, your Browser Mini Double internet package has expired. Reactivate now to enjoy our internet packages. Vodafone, power to you.','2015-11-01 04:36:12','A'),
	(494,'8185687','Surfline','Dear Customer, your data bundle expires in 5 days (18/10/2015). Please ensure you have sufficient airtime on 233255062021 for auto renewal to occur. ','2015-11-01 04:36:12','A'),
	(495,'8183374','+233244245345','Have you recieved it?','2015-11-01 04:36:12','A'),
	(496,'8168979','+233244755702','Ok','2015-11-01 04:36:12','A'),
	(497,'8159663','+233209154458','Can I start coming?','2015-11-01 04:36:12','A'),
	(498,'8156827','+233244245345','Sent you the account.','2015-11-01 04:36:12','A'),
	(499,'8156821','Surfline','Dear Valued Customer, You have   1.992GB remaining on 233255062021 . This is valid until 19/10/2015. Kindly log on to MySurfline for more information. Thank you','2015-11-01 04:36:12','A'),
	(500,'8146198','+233500190529','He who does what is sinful is of the devil, because the devil has been sinning from the beginning. The reason the son of God appeared was to destroy the devil&#039;s work.','2015-11-01 04:36:12','A'),
	(501,'8145290','+233244245345','I can create it and credit it but we dont have any credit on the main account so messages wont deliver.   ','2015-11-01 04:36:12','A'),
	(502,'8140579','+233500190529','Please pick my God.','2015-11-01 04:36:12','A'),
	(503,'8130014','MTN','Super Hilarious! Click to watch the most weirdest &amp; funniest auditions http://bit.ly/1Z4ahd8 with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-01 04:36:12','A'),
	(504,'8124149','Vodafone','Calling Nigeria &amp; South Africa is cheapest on Vodafone! For only 99pesewas, you get 4mins to call family and friends! Just dial *125*32# today','2015-11-01 04:36:12','A'),
	(505,'8122276','EVD','You have successfully transferred amount 5.00 GHC to 233240020588 on Oct 9, 2015. Your updated balance is 29.34 GHC.Transaction ID: 2015100908324008204278469','2015-11-01 04:36:12','A'),
	(506,'8122080','555','Your main balance is GHc 110.52040 valid until 07-01-2016. Talk for 2 whole days. Dial 5544 to enjoy 250mins for Vodafone calls. 12mins for other networks and 50MB data for only GHS 2.','2015-11-01 04:36:12','A'),
	(507,'8122062','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-01 04:36:12','A'),
	(508,'8121586','Esoko','hello','2015-11-01 04:36:12','A'),
	(509,'8121548','Esoko','hello','2015-11-01 04:36:13','A'),
	(510,'8121546','Esoko','hello','2015-11-01 04:36:13','A'),
	(511,'8121500','555','Your main balance is GHc 80.52040 valid until 07-01-2016. Talk for 2 whole days. Dial 5544 to enjoy 250mins for Vodafone calls. 12mins for other networks and 50MB data for only GHS 2.','2015-11-01 04:36:13','A'),
	(512,'8121495','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-01 04:36:13','A'),
	(513,'8121427','555','Your main balance is GHc 50.52040 valid until 07-01-2016. Talk for 2 whole days. Dial 5544 to enjoy 250mins for Vodafone calls. 12mins for other networks and 50MB data for only GHS 2.','2015-11-01 04:36:13','A'),
	(514,'8110312','MPower','Beat the stress! Top up &amp; pay your bills at www.mpower.com.gh or dial *714*44#. Buy your Surfline bundle, airtime on all networks, pay ECG Postpaid &amp; DSTV bills','2015-11-01 04:36:13','A'),
	(515,'8089759','+233240591662','Thnks Nana. Much appreciated','2015-11-01 04:36:13','A'),
	(516,'8089730','+233240591662','Aryt wil add u soon','2015-11-01 04:36:13','A'),
	(517,'8089722','+121','You have transferred GHc10.00000 credit to 233208392598. Your own account balance is GHc20.77040.','2015-11-01 04:36:13','A'),
	(518,'8089664','+233240591662','Haaha. Re u nt on watsap dear?','2015-11-01 04:36:13','A'),
	(519,'8089642','+233240591662','Oooooh dear! Its u.','2015-11-01 04:36:13','A'),
	(520,'8089611','+233240591662','Pls  i dnt know u. Can tell me where you got my contact dear?','2015-11-01 04:36:13','A'),
	(521,'8089574','+233240591662','Thank you. Buh name pls?','2015-11-01 04:36:13','A'),
	(522,'8089478','555','Your main balance is GHc 30.82040 valid until 05-01-2016. Talk for 2 whole days. Dial 5544 to enjoy 250mins for Vodafone calls. 12mins for other networks and 50MB data for only GHS 2.','2015-11-01 04:36:13','A'),
	(523,'8089476','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-01 04:36:13','A'),
	(524,'8089172','Esoko','hello','2015-11-01 04:36:13','A'),
	(525,'8089171','EVD','You have received 30.00 GHC from 0240968100/233240968100. a/c balance is 62.36 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015100719164907102413918','2015-11-01 04:36:13','A'),
	(526,'8083227','MTN','Its The MTN GH Stands in Worship concert. Grab ur ticket @ MTN Osu, Graphic Road, Accra Mall or West Hills mall for Ghc50 VIP &amp; Ghc30 Regular. Dial 1355 to download ur favourite callertunez','2015-11-01 04:36:13','A'),
	(527,'8060563','Surfline','Dear Customer, the recent service interruptions have been resolved. We apologize for any inconvenience caused. Surfline... It&#039;s about time','2015-11-01 04:36:13','A'),
	(528,'8060375','Surfline','Dear Customer, the recent service interruptions have been resolved. We apologize for any inconvenience caused. Surfline... It&#039;s about time','2015-11-01 04:36:13','A'),
	(529,'8058326','5200','Calling USA,Canada,China and UK landlines  is cheapest on Vodafone! Get 50 minutes to call for only GHc5. Just dial *125*30# to subscribe &amp; get 10 extra minutes to Vodafone free!','2015-11-01 04:36:13','A'),
	(530,'8057296','+233209154458','Boss , sorry yu haven&#039;t heard from me for some time now .I know it&#039;s very unserious of me but travelled to my Uncle&#039;s place wasn&#039;t gettin a stable network back in town . Please set the date I could come around','2015-11-01 04:36:13','A'),
	(531,'8057241','Vodafone','Calling Nigeria &amp; South Africa is cheapest on Vodafone! For only GhC 3.9, you get 16mins to call &amp; 2 SMS to text over the next 7 days! Just dial *125*32# today','2015-11-01 04:36:13','A'),
	(532,'8057045','Surfline','Dear customer, we apologize for interruptions in our services today. Our team is working to rectify this in the shortest possible time. Thank you.','2015-11-01 04:36:13','A'),
	(533,'8053764','Surfline','Dear customer, we apologize for interruptions in our services today. Our team is working to rectify this in the shortest possible time. Thank you.','2015-11-01 04:36:14','A'),
	(534,'8051675','MTN','They love &amp; live magic! Click to share in the fun http://bit.ly/1Q1Birk with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-01 04:36:14','A'),
	(535,'8023155','+233244387718','0000  P NPP:2601   NDC:2001 CPP:100a','2015-11-01 04:36:14','A'),
	(536,'8023089','+233244387718','0000  P NPP:2601   NDC:2001 CPP:1004','2015-11-01 04:36:14','A'),
	(537,'8022978','+233244387718','0000  P NPP:2601   NDC:2001 CPP:1001','2015-11-01 04:36:14','A'),
	(538,'8022743','+233244387718','0000  P NPP:2601   NDC:2001 CPP:1001','2015-11-01 04:36:14','A'),
	(539,'8022681','+233244387718','0000  P NPP:2601   NDC:2001 CPP:1001','2015-11-01 04:36:14','A'),
	(540,'8022598','+233244387718','0020 P NPP:2601 NDC:2001 CPP:1001','2015-11-01 04:36:14','A'),
	(541,'8011335','+233244387718','0020 P NPP:2601 NDC:2001 CPP:1001','2015-11-01 04:36:14','A'),
	(542,'8011293','+233244387718','0020 P NPP:2600 NDC:2000 CPP:1000','2015-11-01 04:36:14','A'),
	(543,'8011248','+233244387718','0020 P NPP:2600 NDC:2000 CPP:1000','2015-11-01 04:36:14','A'),
	(544,'8011205','+233244387718','0000 P NPP : 2600 NDC:2000 CPP:1000','2015-11-01 04:36:14','A'),
	(545,'8011135','+233244387718','0000 P NPP: 2600 NDC:2000 CPP:1000','2015-11-01 04:36:14','A'),
	(546,'8011104','+233244387718','0000 P NPP:2600 NDC:2000 CPP:1000','2015-11-01 04:36:14','A'),
	(547,'8011087','+233244387718','0000 P NPP2600 NDC:2000 CPP:1000','2015-11-01 04:36:14','A'),
	(548,'8010667','+233244387718','0000 P NPP2600 NDC:2000 CPP:1000','2015-11-01 04:36:14','A'),
	(549,'8010597','+233244387718','0000 P NPP2600 NDC: 2000 CPP:1000','2015-11-01 04:36:14','A'),
	(550,'8010550','+233244387718','0000 PNPP:2600 NDC: 2000 CPP:1000','2015-11-01 04:36:14','A'),
	(551,'8010492','+233244387718','0000 P NPP:2600 NDC :2000 CPP:1000','2015-11-01 04:36:14','A'),
	(552,'8010433','+233244387718','0000 P NPP:2600 NDC: 2000 CPP:1000','2015-11-01 04:36:14','A'),
	(553,'8010302','+233244387718','0000 P NPP:2600 NDC:2000 CPP:1000','2015-11-01 04:36:14','A'),
	(554,'8010115','555','Service Request Handled Succesfully\r\n','2015-11-01 04:36:14','A'),
	(555,'8010069','555','Welcome to MTN Voice Mail Service. Please text &#039;START&#039; or &#039;STOP&#039; to activate or deactivate service.','2015-11-01 04:36:14','A'),
	(556,'8010022','555','Welcome to MTN Voice Mail Service. Please text &#039;START&#039; or &#039;STOP&#039; to activate or deactivate service.','2015-11-01 04:36:14','A'),
	(557,'8010016','555','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','2015-11-01 04:36:15','A'),
	(558,'8010011','555','Welcome to MTN Voice Mail Service. Please text &#039;START&#039; or &#039;STOP&#039; to activate or deactivate service.','2015-11-01 04:36:15','A'),
	(559,'8009985','555','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','2015-11-01 04:36:15','A'),
	(560,'8009983','EVD','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','2015-11-01 04:36:15','A'),
	(561,'8009958','+121','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','2015-11-01 04:36:15','A'),
	(562,'8009956','EVD','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','2015-11-01 04:36:15','A'),
	(563,'8009955','EVD','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','2015-11-01 04:36:15','A'),
	(564,'8009954','EVD','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','2015-11-01 04:36:15','A'),
	(565,'8009762','+256205462012','i have a crazy plan for today and i want to share it with you, call me  +256205462012','2015-11-01 04:36:15','A'),
	(566,'8009711','EVD','You have successfully transferred amount 1.00 GHC to 233244387718 on Oct 5, 2015. Your updated balance is 3.30 GHC.Transaction ID: 2015100509475620701250029','2015-11-01 04:36:15','A'),
	(567,'8007831','EVD','You have successfully transferred amount 2.00 GHC to 233240020588 on Oct 5, 2015. Your updated balance is 4.40 GHC.Transaction ID: 2015100508533817601192051','2015-11-01 04:36:15','A'),
	(568,'7987030','EVD','You have received 30.00 GHC from 0240968100/233240968100. a/c balance is 56.91 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015100319291069502507580','2015-11-01 04:36:15','A'),
	(569,'7987029','Esoko','hello','2015-11-01 04:36:15','A'),
	(570,'7987027','1330','esoko : 30.00 GHC top-up credit sent from esoko TXN ID: 6237248 Send money, top-up or pay with txtnpay. Help line:0302902981','2015-11-01 04:36:15','A'),
	(571,'7978640','+233500190529','Please lets text','2015-11-01 04:36:15','A'),
	(572,'7977960','+121','You have transferred GHc15.00000 credit to 233209425151. Your own account balance is GHc28.78040.','2015-11-01 04:36:15','A'),
	(573,'7977936','+121','You have transferred GHc20.00000 credit to 233209423311. Your own account balance is GHc43.83040.','2015-11-01 04:36:15','A'),
	(574,'7977911','Esoko','hello','2015-11-01 04:36:15','A'),
	(575,'7977902','EVD','You have successfully transferred amount 15.00 GHC to 233546706099 on Oct 3, 2015. Your updated balance is 26.71 GHC.Transaction ID: 2015100319352336003001951','2015-11-01 04:36:15','A'),
	(576,'7977892','Esoko','hello','2015-11-01 04:36:15','A'),
	(577,'7977874','555','Your main balance is GHc 83.93040 valid until 01-01-2016. Talk for 2 whole days. Dial 5544 to enjoy 250mins for Vodafone calls. 12mins for other networks and 50MB data for only GHS 2.','2015-11-01 04:36:15','A'),
	(578,'7977873','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-01 04:36:15','A'),
	(579,'7977865','Esoko','hello','2015-11-01 04:36:15','A'),
	(580,'7970067','+233271210210','0000 m Npp :290 ndc:190 cpp:120','2015-11-01 04:36:15','A'),
	(581,'7969313','+233271210210','0000 m Npp:290 ndc:190 cpp:120','2015-11-01 04:36:16','A'),
	(582,'7968988','+233271210210','Help','2015-11-01 04:36:16','A'),
	(583,'7968890','+233271210210','info','2015-11-01 04:36:16','A'),
	(584,'7963711','+233240020588','Pls am in church.i will call u wen i close.hope u gud','2015-11-01 04:36:16','A'),
	(585,'7925839','MTN','Super hilarious! Click to have the best laugh today! http://bit.ly/1JzSKho .Enjoy more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-01 04:36:16','A'),
	(586,'7924554','uniBank','Participate in the uniBank Super 360 Promo. Deposit GHS100 to join and maintain a minimum balance of GHS200. You stand the chance of winning GHS15K in the 3rd draw and GHS360K etc. in the ultimate draw. HURRY, Promo ends 30th October 2015. Terms and Condi','2015-11-01 04:36:16','A'),
	(587,'7922611','+233208392598','Mwaah wo 10ghc di3 wo boa koraa','2015-11-01 04:36:16','A'),
	(588,'7922448','+121','You have transferred GHc10.00000 credit to 233208392598. Your own account balance is GHc60.06040.','2015-11-01 04:36:16','A'),
	(589,'7908436','Esoko','hello','2015-11-01 04:36:16','A'),
	(590,'7906663','+121','You have transferred GHc10.00000 credit to 233209137263. Your own account balance is GHc70.69040.','2015-11-01 04:36:16','A'),
	(591,'7906610','555','Your main balance is GHc 80.74040 valid until 30-12-2015. Talk for 2 whole days. Dial 5544 to enjoy 250mins for Vodafone calls. 12mins for other networks and 50MB data for only GHS 2.','2015-11-01 04:36:16','A'),
	(592,'7905525','+233243342729','Am trying to reach u but ur line isn&#039;t going through, I just wanted to know how u are doing. ','2015-11-01 04:36:16','A'),
	(593,'7878791','Vodafone','Vodafone appstore is live! Visit appstore.vodafone.com.gh,register &amp; download any app of your choice and stand a chance to win free 100MB data.','2015-11-01 04:36:16','A'),
	(594,'7840794','5200','FREE INTERNET! Simply dial *700*24# to buy 30MB for only GHs1. Vodafone will add extra 30MB for free on Ghana&#039;s fastest 3G+ network. http://opr.as/e2Y','2015-11-01 04:36:16','A'),
	(595,'7823363','+233244960321','Agent: annan\nPolling Station: Lebanon Club (a)\nConstituency: Bantama\n','2015-11-01 04:36:16','A'),
	(596,'7823334','+233504298056','Info','2015-11-01 04:36:16','A'),
	(597,'7823054','+233504298056','Info','2015-11-01 04:36:16','A'),
	(598,'7822777','+233244960321','Agent: annan\nPolling Station: Lebanon Club (a)\nConstituency: Bantama\n','2015-11-01 04:36:16','A'),
	(599,'7822734','+233504298056','Info','2015-11-01 04:36:16','A'),
	(600,'7822292','+233504298056','Info','2015-11-01 04:36:16','A'),
	(601,'7807169','MTN','Super fun tricks! http://bit.ly/1ODNpgT .Click to enjoy more with your 300MB YouTube Bundle! @ GHC 3 only. Dial *138*1*6#','2015-11-01 04:36:16','A'),
	(602,'7803685','+233242112511','doctorkrow@yahoo.com','2015-11-01 04:36:16','A'),
	(603,'7775139','+233244387718','Help','2015-11-01 04:36:16','A'),
	(604,'7775096','+233244387718','Info','2015-11-01 04:36:16','A'),
	(605,'7775035','+233244387718','Info','2015-11-01 04:36:17','A'),
	(606,'7774967','+233244387718','Agent: edd\nPolling Station: Anglican Prim Sch Tepa (a)\nConstituency: Ahafo Ano North\n','2015-11-01 04:36:17','A'),
	(607,'7774959','+233244387718','Info','2015-11-01 04:36:17','A'),
	(608,'7774865','+233244387718','Info','2015-11-01 04:36:17','A'),
	(609,'7774685','+233244387718','Info','2015-11-01 04:36:17','A'),
	(610,'7774335','+233244387718','Info','2015-11-01 04:36:17','A'),
	(611,'7770063','138','Internet Usage Flash, you have 294.00MB remaining on your assigned 1GB. Your 1GB will be renewed when you run out. To stop renewal, SMS STOP to 138.','2015-11-01 04:36:17','A'),
	(612,'7767381','Esoko','hello','2015-11-01 04:36:17','A'),
	(613,'7767380','1330','esoko : 15.00 GHC top-up credit sent from esoko TXN ID: 6227529 Send money, top-up or pay with txtnpay. Help line:0302902981','2015-11-01 04:36:17','A'),
	(614,'7767371','Esoko','hello','2015-11-01 04:36:17','A'),
	(615,'7767365','Esoko','hello','2015-11-01 04:36:17','A'),
	(616,'7767364','1330','esoko : 30.00 GHC top-up credit sent from esoko TXN ID: 6227527 Send money, top-up or pay with txtnpay. Help line:0302902981','2015-11-01 04:36:17','A'),
	(617,'7767363','EVD','You have received 30.00 GHC from 0240968100/233240968100. a/c balance is 34.84 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015092822213081502097330','2015-11-01 04:36:17','A'),
	(618,'7767361','555','Your main balance is GHc 51.15170 valid until 27-12-2015. Its talk time. Dial 7171 &amp; enjoy free calls to any Vodafone number plus double bonus credit on GHs 2 top ups or more for GHS 3.99 weekly','2015-11-01 04:36:17','A'),
	(619,'7767360','Esoko','hello','2015-11-01 04:36:17','A'),
	(620,'7767353','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-01 04:36:17','A'),
	(621,'7739471','MTNPay4Me','The number 233544295582 tried to reach you, but you did not answer the call','2015-11-01 04:36:17','A'),
	(622,'7739441','MTNPay4Me','The number 233544295582 tried to reach you, but you did not answer the call','2015-11-01 04:36:17','A'),
	(623,'7733624','+233544295582','I dont know','2015-11-01 04:36:17','A'),
	(624,'7732729','+233544295582','Seriously. Will you go to the hospital? So sorry. God should mercifully and kindly grant you strength, it is not easy.','2015-11-01 04:36:17','A'),
	(625,'7691538','+233244387718','0000 P NPP:2600 NDC:2000 CPP:1000','2015-11-01 04:36:17','A'),
	(626,'7691530','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','2015-11-01 04:36:17','A'),
	(627,'7691526','+233244387718','help','2015-11-01 04:36:17','A'),
	(628,'7691511','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','2015-11-01 04:36:17','A'),
	(629,'7691502','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','2015-11-01 04:36:17','A'),
	(630,'7689025','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','2015-11-01 04:36:18','A'),
	(631,'7684907','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','2015-11-01 04:36:18','A'),
	(632,'7684729','+233244387718','help','2015-11-01 04:36:18','A'),
	(633,'7683817','0000','help','2015-11-01 04:36:18','A'),
	(634,'7683576','0000','help','2015-11-01 04:36:18','A'),
	(635,'7680090',' 233244960321','help','2015-11-01 04:36:18','A'),
	(636,'7677517','+233244960321','just a test. Hello!','2015-11-01 04:36:18','A'),
	(637,'7675433','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','2015-11-01 04:36:18','A'),
	(638,'7674905','0000','01234 P GOOG:12 APPL:13 YHOO:45 NYSE:56','2015-11-01 04:36:18','A'),
	(639,'7674373','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','2015-11-01 04:36:18','A'),
	(640,'7674182','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','2015-11-01 04:36:18','A'),
	(641,'7672825','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','2015-11-01 04:36:18','A'),
	(642,'7671149','MTN','Young &amp; free! http://bit.ly/1KKcIeB .Watch more with your 300MB YouTube Bundle! @ GHC 3 only. Dial *138*1*6#','2015-11-01 04:36:18','A'),
	(643,'7667872','0000','01234 m GOOG:12 APPL:13 YHOO:45 NYSE:56','2015-11-01 04:36:18','A'),
	(644,'7667374','+233242679330','Feeling gr8,i woke up some few mins ago so that should tell u how well i slept :)','2015-11-01 04:36:18','A'),
	(645,'7667287','+233242679330','Good morning naughty :)','2015-11-01 04:36:18','A'),
	(646,'7664369','0000','0012 P GOOG:12 APPL:13 YHOO:45 NYSE:56','2015-11-01 04:36:18','A'),
	(647,'7664268','0000','01234 V GOOG:12 APPL:13 YHOO:45 NYSE:56','2015-11-01 04:36:18','A'),
	(648,'7664263','0000','01234 P GOOG:12 APPL:13 YHOO:45 NYSE:56','2015-11-01 04:36:18','A'),
	(649,'7664255','0000','0012 M GOOG:12 APPL:13 YHOO:45 NYSE:56','2015-11-01 04:36:18','A'),
	(650,'7664244','0000','01234 M JFK:68 GOOG:89 APPL:90','2015-11-01 04:36:18','A'),
	(651,'7653811','2131','We congratulate you! It is our duty to reward loyal customers like you with special offers: 3 FREE Kitchen tips! To claim it, reply OK.\r\n','2015-11-01 04:36:18','A'),
	(652,'7637777','+233209154458','How long would it take to build a website for &quot;campaign &quot; and how much ?','2015-11-01 04:36:19','A'),
	(653,'7632137','Esoko','hello','2015-11-01 04:36:19','A'),
	(654,'7632133','1330','esoko : 30.00 GHC top-up credit sent from esoko TXN ID: 6219355 Send money, top-up or pay with txtnpay. Help line:0302902981','2015-11-01 04:36:19','A'),
	(655,'7632126','EVD','You have received 30.00 GHC from 0240968100/233240968100. a/c balance is 44.11 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015092322530139302301253','2015-11-01 04:36:19','A'),
	(656,'8844239','Surfline','Dear Customer, you have exhausted your main bundle on  233255062021. Please log on to MySurfline to buy a new bundle. Thank you for being a Surfline customer','2015-11-02 21:56:49','A'),
	(657,'8823998','138','Yello, your expiring internet bundle has been renewed for GHC20 and valid until 02 Dec 2015. Keep topping up your account and carry your remaining bundle from your previous purchase.','2015-11-02 21:56:49','A'),
	(658,'8850117','MTN','Congratulations! You have gained 37141 points! Dial *482# to redeem your reward. Talk, Text &amp; Browse more to gain more points and get more rewards from MTN.','2015-11-03 04:36:49','A'),
	(659,'8851186','MPower','4 Days To Go!! GHs100 up for grabs this Friday. Complete at least 2 transactions this week and you could receive a cool GHs100 to spend, send or transfer.','2015-11-03 06:40:18','A'),
	(660,'8855364','MTN Loyalty','Congratulation on your 12 year anniversary on MTN. You are rewarded 90 minutes free airtime for on-net calls.','2015-11-03 11:08:28','A'),
	(661,'8856866','MTN','Hilarious falls http://bit.ly/1izTsVN .Enjoy more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-03 12:01:08','A'),
	(662,'8878062','EVD','You have successfully transferred amount 10.00 GHC to 233244642666 on Nov 4, 2015. Your updated balance is 110.86 GHC.Transaction ID: 2015110408371879702191988','2015-11-04 08:39:01','A'),
	(663,'8878142','+121','You have transferred GHc15.00000 credit to 233205006658. Your own account balance is GHc23.55640.','2015-11-04 08:40:45','A'),
	(664,'8878768','555','Your main balance is GHc 58.39440 valid until 02-02-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-11-04 09:19:06','A'),
	(665,'8878767','555','You have received GHc35.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-04 09:19:06','A'),
	(666,'8878779','555','Your main balance is GHc 93.39440 valid until 02-02-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-11-04 09:19:56','A'),
	(667,'8878777','555','You have received GHc35.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-04 09:19:57','A'),
	(668,'8878804','EVD','You have received 35.00 GHC from 0240968100/233240968100. a/c balance is 180.86 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015110409211405202238216','2015-11-04 09:22:37','A'),
	(669,'8878832','555','Your main balance is GHc 163.39440 valid until 02-02-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-11-04 09:26:15','A'),
	(670,'8878852','555','Your main balance is GHc 198.39440 valid until 02-02-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-11-04 09:28:01','A'),
	(671,'8878851','555','You have received GHc35.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-04 09:28:01','A'),
	(672,'8878879','555','Your main balance is GHc 233.39440 valid until 02-02-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-11-04 09:30:45','A'),
	(673,'8878894','EVD','You have received 35.00 GHC from 0240968100/233240968100. a/c balance is 250.86 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015110409302717602247438','2015-11-04 09:31:45','A'),
	(674,'8878943','EVD','You have successfully transferred amount 20.00 GHC to 233548856722 on Nov 4, 2015. Your updated balance is 265.76 GHC.Transaction ID: 2015110409345528101241023','2015-11-04 09:36:14','A'),
	(675,'8884189','7777','Your number 244960321 was chosen to meet Cristiano Ronaldo and to win CASH and TRIPS to MADRID to see him playing (0.20/day)! Reply with YES','2015-11-04 14:25:49','A'),
	(676,'8886987','+233500190529','Please  pick my call','2015-11-04 16:46:00','A'),
	(677,'8904783','EVD','You have successfully transferred amount 5.00 GHC to 233240020588 on Nov 5, 2015. Your updated balance is 245.19 GHC.Transaction ID: 2015110510565111303250887','2015-11-05 10:58:33','A'),
	(678,'8917633','MTN_CC','Y&#039;ello! MTN is giving you the best deals to talk,browse and send sms to all networks. Simply dial *575# and choose your preferred bundle now.','2015-11-05 16:34:33','A'),
	(679,'8922432','TigoCash','Successful Tigo Cash deposit: GHC 150.00 at Agent ASAREV ENT (53800) on 05/11/15 05:37 PM. New Tigo Cash balance: GHC 150.61. Ref No 664244309.','2015-11-05 17:54:26','A'),
	(680,'8924192','TigoCash','Successful Tigo Cash withdrawal: collect GHC 140.00 from Agent DANIEL EGYIR - 0278798007. New Tigo Cash balance: GHC 7.81. 11/05/15 06:22 PM. Ref No 664318977. Thank you','2015-11-05 18:22:57','A'),
	(681,'8983054','+233240020588','I.ve sent you an InstaVoice message. To listen, download InstaVoice http://goo.gl/JbqrFo or dial *0* free. ','2015-11-07 04:34:58','A'),
	(682,'8995860','TigoCash','Withdrawal failed. 0278798007 does not have sufficient funds to complete this transaction. Your balance is 7.81. Reference No. 666600064','2015-11-07 16:17:53','A'),
	(683,'8995896','TigoCash','Successful Tigo Cash withdrawal: collect GHC 5.00 from Agent DANIEL EGYIR - 0278798007. New Tigo Cash balance: GHC 2.01. 11/07/15 04:18 PM. Ref No 666603830. Thank you','2015-11-07 16:19:22','A'),
	(684,'9010726','+121','You have transferred GHc10.00000 credit to 233500190529. Your own account balance is GHc257.86441.','2015-11-08 07:27:02','A'),
	(685,'8991466','BlueCrestGH','Do you have an Innovative idea?You have an opportunity to win a LAPTOP,SMART PHONE or a TAB.Visit www.innofest.bluecrest.edu.gh.For details Whatsapp 0572761960.','2015-11-08 07:27:02','A'),
	(686,'8961829','MTN','Amazing stunts http://bit.ly/1WvWSGu .Watch more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-08 07:27:02','A'),
	(687,'9013133','MTN Loyalty','It&#039;s a super deal to reward your loyatly. For the next 4 weeks, spend GHC 50 from Monday to Friday, and get 25% back for your weekend calls on-net.','2015-11-08 12:47:09','A'),
	(688,'9018835','+233246746980','0244262729','2015-11-08 18:41:22','A'),
	(689,'9018954','EVD','You have successfully transferred amount 15.00 GHC to 233244262729 on Nov 8, 2015. Your updated balance is 229.73 GHC.Transaction ID: 2015110818483357201832469','2015-11-08 18:49:03','A'),
	(690,'9018991','EVD','You have successfully transferred amount 10.00 GHC to 233246746980 on Nov 8, 2015. Your updated balance is 219.63 GHC.Transaction ID: 2015110818505010901837100','2015-11-08 18:51:22','A'),
	(691,'9027257','+2348103668927','I kno u will  be worried but am very fine.....love you boo&lt;3&lt;3 have a lovely day..','2015-11-09 06:44:00','A'),
	(692,'9055282','+233248242859','I ll call u bk later. I ve to finish d questions this evenin ','2015-11-09 20:50:17','A'),
	(693,'9027243','+2348103668927','Good morning love,how was ur sleep?our car broke down yday and we were robbed,no1 was hurt just that some of the passengers insisted we go report at the station cos we were almost at our destination.','2015-11-09 20:50:18','A'),
	(694,'9064531','451','GHS10 000 can be yours with MTN Dream Number daily draw!Mr Emmanuel Tabi was a winner you too can!Reply Yes &amp; change your life today!SMS WIN to 451 NOW.60p/day','2015-11-10 10:09:48','A'),
	(695,'9062921','+233244387718','PASSWORD space M/P space RESULTS. Thank you!','2015-11-10 10:09:48','A'),
	(696,'9062920','+233244387718','PASSWORD space M/P space RESULTS. Thank you!','2015-11-10 10:09:48','A'),
	(697,'9062919','+233244387718','PASSWORD space M/P space RESULTS. Thank you!','2015-11-10 10:09:49','A'),
	(698,'9062918','+233244387718','PASSWORD space M/P space RESULTS. Thank you!','2015-11-10 10:09:49','A'),
	(699,'9062917','+233244387718','PASSWORD space M/P space RESULTS. Thank you!','2015-11-10 10:09:49','A'),
	(700,'9066324','Surfline','Missing out! Activate a Surfline bundle now and get bonus data instantly. Visit MySurfline or any of our outlets. It&#039;s about time!','2015-11-10 12:11:16','A'),
	(701,'9068500','MTN','Enjoy this inspirational tune My Desire(Lord you are Beautiful) by Boadiwaa http://bit.ly/1MlLus3 with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-10 14:14:54','A'),
	(702,'9086078','+233248242859','Am goin to bed ve a gud 9t','2015-11-10 20:51:42','A'),
	(703,'9101477','MPower','2 Days To Go! Nine GHs100 MPower Tokens up for grabs. Simply complete 2 transactions by Friday and you could receive GHs100 in your account. #PayWithMPower','2015-11-11 15:11:54','A'),
	(704,'9138527','+233244387718','Tithe','2015-11-12 16:02:06','A'),
	(705,'9094314','Surfline','Dear Customer, your data bundle expires in 5 days (16/11/2015). Please ensure you have sufficient airtime on 233255062021 for auto renewal to occur. ','2015-11-12 16:02:06','A'),
	(706,'9144420','+233244387718','Tithe','2015-11-12 16:52:48','A'),
	(707,'9147356','TigoCash','Successful Tigo Cash withdrawal: collect GHC 40.00 from Agent DANIEL EGYIR - 0278798007. New Tigo Cash balance: GHC 1.21. 11/12/15 05:56 PM. Ref No 673321558. Thank you','2015-11-12 17:57:07','A'),
	(708,'9181262','Vodafone','Get tips on how to combine the trendiest male African costumes. Be proud to be an African. Send STARTM to 3050 to subscribe @ 12p.','2015-11-13 13:51:38','A'),
	(709,'9207345','+233240020588','Sorry, I am busy. Call back later','2015-11-14 09:31:06','A'),
	(710,'9208815','2174','Pray without ceasing. Prayer is the food for the soul. Dial *706*14# now for your daily prayer support!','2015-11-14 11:42:44','A'),
	(711,'9236751','+233541536449','Ok cool','2015-11-15 16:46:16','A'),
	(712,'9208815','2174','Pray without ceasing. Prayer is the food for the soul. Dial *706*14# now for your daily prayer support!','2015-11-15 16:46:23','A'),
	(713,'9207345','+233240020588','Sorry, I am busy. Call back later','2015-11-15 16:46:23','A'),
	(714,'9181262','Vodafone','Get tips on how to combine the trendiest male African costumes. Be proud to be an African. Send STARTM to 3050 to subscribe @ 12p.','2015-11-15 16:46:23','A'),
	(715,'9147356','TigoCash','Successful Tigo Cash withdrawal: collect GHC 40.00 from Agent DANIEL EGYIR - 0278798007. New Tigo Cash balance: GHC 1.21. 11/12/15 05:56 PM. Ref No 673321558. Thank you','2015-11-15 16:46:23','A'),
	(716,'9144420','+233244387718','Tithe','2015-11-15 16:46:23','A'),
	(717,'9138527','+233244387718','Tithe','2015-11-15 16:46:24','A'),
	(718,'9101477','MPower','2 Days To Go! Nine GHs100 MPower Tokens up for grabs. Simply complete 2 transactions by Friday and you could receive GHs100 in your account. #PayWithMPower','2015-11-15 16:46:25','A'),
	(719,'9094314','Surfline','Dear Customer, your data bundle expires in 5 days (16/11/2015). Please ensure you have sufficient airtime on 233255062021 for auto renewal to occur. ','2015-11-15 16:46:25','A'),
	(720,'9086078','+233248242859','Am goin to bed ve a gud 9t','2015-11-15 16:46:25','A'),
	(721,'9068500','MTN','Enjoy this inspirational tune My Desire(Lord you are Beautiful) by Boadiwaa http://bit.ly/1MlLus3 with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-15 16:46:25','A'),
	(722,'9066324','Surfline','Missing out! Activate a Surfline bundle now and get bonus data instantly. Visit MySurfline or any of our outlets. It&#039;s about time!','2015-11-15 16:46:25','A'),
	(723,'9064531','451','GHS10 000 can be yours with MTN Dream Number daily draw!Mr Emmanuel Tabi was a winner you too can!Reply Yes &amp; change your life today!SMS WIN to 451 NOW.60p/day','2015-11-15 16:46:25','A'),
	(724,'9062921','+233244387718','PASSWORD space M/P space RESULTS. Thank you!','2015-11-15 16:46:25','A'),
	(725,'9062920','+233244387718','PASSWORD space M/P space RESULTS. Thank you!','2015-11-15 16:46:26','A'),
	(726,'9062919','+233244387718','PASSWORD space M/P space RESULTS. Thank you!','2015-11-15 16:46:26','A'),
	(727,'9062918','+233244387718','PASSWORD space M/P space RESULTS. Thank you!','2015-11-15 16:46:27','A'),
	(728,'9062917','+233244387718','PASSWORD space M/P space RESULTS. Thank you!','2015-11-15 16:46:27','A'),
	(729,'9055282','+233248242859','I ll call u bk later. I ve to finish d questions this evenin ','2015-11-15 16:46:27','A'),
	(730,'9027257','+2348103668927','I kno u will  be worried but am very fine.....love you boo&lt;3&lt;3 have a lovely day..','2015-11-15 16:46:28','A'),
	(731,'9027243','+2348103668927','Good morning love,how was ur sleep?our car broke down yday and we were robbed,no1 was hurt just that some of the passengers insisted we go report at the station cos we were almost at our destination.','2015-11-15 16:46:28','A'),
	(732,'9018991','EVD','You have successfully transferred amount 10.00 GHC to 233246746980 on Nov 8, 2015. Your updated balance is 219.63 GHC.Transaction ID: 2015110818505010901837100','2015-11-15 16:46:28','A'),
	(733,'9018954','EVD','You have successfully transferred amount 15.00 GHC to 233244262729 on Nov 8, 2015. Your updated balance is 229.73 GHC.Transaction ID: 2015110818483357201832469','2015-11-15 16:46:28','A'),
	(734,'9018835','+233246746980','0244262729','2015-11-15 16:46:28','A'),
	(735,'9013133','MTN Loyalty','It&#039;s a super deal to reward your loyatly. For the next 4 weeks, spend GHC 50 from Monday to Friday, and get 25% back for your weekend calls on-net.','2015-11-15 16:46:28','A'),
	(736,'9010726','+121','You have transferred GHc10.00000 credit to 233500190529. Your own account balance is GHc257.86441.','2015-11-15 16:46:28','A'),
	(737,'8995896','TigoCash','Successful Tigo Cash withdrawal: collect GHC 5.00 from Agent DANIEL EGYIR - 0278798007. New Tigo Cash balance: GHC 2.01. 11/07/15 04:18 PM. Ref No 666603830. Thank you','2015-11-15 16:46:28','A'),
	(738,'8995860','TigoCash','Withdrawal failed. 0278798007 does not have sufficient funds to complete this transaction. Your balance is 7.81. Reference No. 666600064','2015-11-15 16:46:28','A'),
	(739,'8991466','BlueCrestGH','Do you have an Innovative idea?You have an opportunity to win a LAPTOP,SMART PHONE or a TAB.Visit www.innofest.bluecrest.edu.gh.For details Whatsapp 0572761960.','2015-11-15 16:46:28','A'),
	(740,'8983054','+233240020588','I.ve sent you an InstaVoice message. To listen, download InstaVoice http://goo.gl/JbqrFo or dial *0* free. ','2015-11-15 16:46:28','A'),
	(741,'8961829','MTN','Amazing stunts http://bit.ly/1WvWSGu .Watch more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-15 16:46:28','A'),
	(742,'8924192','TigoCash','Successful Tigo Cash withdrawal: collect GHC 140.00 from Agent DANIEL EGYIR - 0278798007. New Tigo Cash balance: GHC 7.81. 11/05/15 06:22 PM. Ref No 664318977. Thank you','2015-11-15 16:46:28','A'),
	(743,'8922432','TigoCash','Successful Tigo Cash deposit: GHC 150.00 at Agent ASAREV ENT (53800) on 05/11/15 05:37 PM. New Tigo Cash balance: GHC 150.61. Ref No 664244309.','2015-11-15 16:46:28','A'),
	(744,'8917633','MTN_CC','Y&#039;ello! MTN is giving you the best deals to talk,browse and send sms to all networks. Simply dial *575# and choose your preferred bundle now.','2015-11-15 16:46:28','A'),
	(745,'8904783','EVD','You have successfully transferred amount 5.00 GHC to 233240020588 on Nov 5, 2015. Your updated balance is 245.19 GHC.Transaction ID: 2015110510565111303250887','2015-11-15 16:46:28','A'),
	(746,'8886987','+233500190529','Please  pick my call','2015-11-15 16:46:28','A'),
	(747,'8884189','7777','Your number 244960321 was chosen to meet Cristiano Ronaldo and to win CASH and TRIPS to MADRID to see him playing (0.20/day)! Reply with YES','2015-11-15 16:46:28','A'),
	(748,'8878943','EVD','You have successfully transferred amount 20.00 GHC to 233548856722 on Nov 4, 2015. Your updated balance is 265.76 GHC.Transaction ID: 2015110409345528101241023','2015-11-15 16:46:28','A'),
	(749,'8878894','EVD','You have received 35.00 GHC from 0240968100/233240968100. a/c balance is 250.86 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015110409302717602247438','2015-11-15 16:46:28','A'),
	(750,'8878879','555','Your main balance is GHc 233.39440 valid until 02-02-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-11-15 16:46:28','A'),
	(751,'8878852','555','Your main balance is GHc 198.39440 valid until 02-02-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-11-15 16:46:29','A'),
	(752,'8878851','555','You have received GHc35.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-15 16:46:29','A'),
	(753,'8878832','555','Your main balance is GHc 163.39440 valid until 02-02-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-11-15 16:46:29','A'),
	(754,'8878804','EVD','You have received 35.00 GHC from 0240968100/233240968100. a/c balance is 180.86 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015110409211405202238216','2015-11-15 16:46:29','A'),
	(755,'8878779','555','Your main balance is GHc 93.39440 valid until 02-02-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-11-15 16:46:29','A'),
	(756,'8878777','555','You have received GHc35.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-15 16:46:29','A'),
	(757,'8878768','555','Your main balance is GHc 58.39440 valid until 02-02-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-11-15 16:46:29','A'),
	(758,'8878767','555','You have received GHc35.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-15 16:46:29','A'),
	(759,'8878142','+121','You have transferred GHc15.00000 credit to 233205006658. Your own account balance is GHc23.55640.','2015-11-15 16:46:29','A'),
	(760,'8878062','EVD','You have successfully transferred amount 10.00 GHC to 233244642666 on Nov 4, 2015. Your updated balance is 110.86 GHC.Transaction ID: 2015110408371879702191988','2015-11-15 16:46:29','A'),
	(761,'8856866','MTN','Hilarious falls http://bit.ly/1izTsVN .Enjoy more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-15 16:46:29','A'),
	(762,'8855364','MTN Loyalty','Congratulation on your 12 year anniversary on MTN. You are rewarded 90 minutes free airtime for on-net calls.','2015-11-15 16:46:29','A'),
	(763,'8851186','MPower','4 Days To Go!! GHs100 up for grabs this Friday. Complete at least 2 transactions this week and you could receive a cool GHs100 to spend, send or transfer.','2015-11-15 16:46:29','A'),
	(764,'8850117','MTN','Congratulations! You have gained 37141 points! Dial *482# to redeem your reward. Talk, Text &amp; Browse more to gain more points and get more rewards from MTN.','2015-11-15 16:46:29','A'),
	(765,'8844239','Surfline','Dear Customer, you have exhausted your main bundle on  233255062021. Please log on to MySurfline to buy a new bundle. Thank you for being a Surfline customer','2015-11-15 16:46:29','A'),
	(766,'8823998','138','Yello, your expiring internet bundle has been renewed for GHC20 and valid until 02 Dec 2015. Keep topping up your account and carry your remaining bundle from your previous purchase.','2015-11-15 16:46:29','A'),
	(767,'8802281','+233240020588','I.ve sent you an InstaVoice message. To listen, download InstaVoice http://goo.gl/JbqrFo or dial *0* free. ','2015-11-15 16:46:29','A'),
	(768,'8745148','mt. olivet','hello Sels','2015-11-15 16:46:29','A'),
	(769,'8717586','MTN','Unleash the real you! Try something different! http://bit.ly/1kL6n8Z .Unleash more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-15 16:46:29','A'),
	(770,'8708477','+121','You have transferred GHc15.00000 credit to 233209137263. Your own account balance is GHc84.83040.','2015-11-15 16:46:29','A'),
	(771,'8703877','1303','Do you want to express yourself? Dial 130330 and sing that song in your head. Record and MTN will rate your performance. To cancel send STOP to 1303.','2015-11-15 16:46:29','A'),
	(772,'8669148','MPower','Avoid the end of month traffic! Pay all your bills at www.mpower.com.gh or dial *714*44#. Buy airtime credits, Surfline bundle; pay DSTV &amp; ECG Postpaid bills','2015-11-15 16:46:29','A'),
	(773,'8669132','MPower','Avoid the end of month traffic! Pay all your bills at www.mpower.com.gh or dial *714*44#. Buy airtime credits, Surfline bundle; pay DSTV &amp; ECG Postpaid bills','2015-11-15 16:46:29','A'),
	(774,'8661020','+121','You have transferred GHc5.00000 credit to 233209520106. Your own account balance is GHc120.12040.','2015-11-15 16:46:29','A'),
	(775,'8660967','+233546706099','Pls lemme know when u pls forward me the cdts','2015-11-15 16:46:30','A'),
	(776,'8660932','+233546706099','School is stressing me out tho ','2015-11-15 16:46:30','A'),
	(777,'8660872','+233546706099','A beg u fit hala me some crdts on my vodafone line i want take do bundle my number dis ( 0209520106)','2015-11-15 16:46:30','A'),
	(778,'8647391','MTN','Being awesome is a lifestyle http://bit.ly/1k6BSKg .Choose to be awesome .Enjoy more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-15 16:46:30','A'),
	(779,'8639474','EVD','You have successfully transferred amount 5.00 GHC to 233240020588 on Oct 27, 2015. Your updated balance is 173.04 GHC.Transaction ID: 2015102712492006502929103','2015-11-15 16:46:30','A'),
	(780,'8599802','138','Internet Usage Flash: Credit 437.50MB, Time Left 9 day(s).','2015-11-15 16:46:30','A'),
	(781,'8599223','555','Your main balance is GHc 125.29040 valid until 24-01-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-11-15 16:46:30','A'),
	(782,'8598678','Esoko','hello','2015-11-15 16:46:30','A'),
	(783,'8598665','Esoko','hello','2015-11-15 16:46:30','A'),
	(784,'8598521','Esoko','hello','2015-11-15 16:46:30','A'),
	(785,'8598520','EVD','You have received 37.00 GHC from 0240968100/233240968100. a/c balance is 178.19 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015102610493486702772219','2015-11-15 16:46:30','A'),
	(786,'8598478','Esoko','hello','2015-11-15 16:46:30','A'),
	(787,'8598475','EVD','You have received 37.00 GHC from 0240968100/233240968100. a/c balance is 141.19 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015102610485021702771562','2015-11-15 16:46:30','A'),
	(788,'8598402','Esoko','hello','2015-11-15 16:46:30','A'),
	(789,'8598401','EVD','You have received 37.00 GHC from 0240968100/233240968100. a/c balance is 104.19 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015102610475151102770712','2015-11-15 16:46:30','A'),
	(790,'8598390','EVD','You have received 37.00 GHC from 0240968100/233240968100. a/c balance is 67.19 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015102610465658402769940','2015-11-15 16:46:30','A'),
	(791,'8598387','Esoko','hello','2015-11-15 16:46:30','A'),
	(792,'8597941','Esoko','hello','2015-11-15 16:46:30','A'),
	(793,'8597940','EVD','You have received 30.00 GHC from 0240968100/233240968100. a/c balance is 30.19 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015102610351502302759549','2015-11-15 16:46:30','A'),
	(794,'8597902','Esoko','hello','2015-11-15 16:46:30','A'),
	(795,'8597284','Surfline','Dear Valued Customer, You have   0.970GB remaining on 233255062021. This is valid until 17/11/2015 . Kindly log on to MySurfline for more information. Thank you','2015-11-15 16:46:30','A'),
	(796,'8596727','5151','Dear Customer the confirmation code for password reset of your Esoko Account  is 109507.Please do not share the confirmation code and it expires in 30 minutes.','2015-11-15 16:46:30','A'),
	(797,'8596644','GloGh','You have been credited with Ghc 2.000 &amp; your main a/c is Ghc 4.008.','2015-11-15 16:46:31','A'),
	(798,'8596643','GloGh','You have earned a Recharge bonus of Ghc 0.600 valid until 29/10/2015.','2015-11-15 16:46:31','A'),
	(799,'8596534','GloGh','Sorry, your account is inactive. Please refill your account before 20/12/2015.','2015-11-15 16:46:31','A'),
	(800,'8596529','GloGh','Sorry, your account is inactive. Please refill your account before 20/12/2015.','2015-11-15 16:46:31','A'),
	(801,'8596528','GloGh','Sorry, your account is inactive. Please refill your account before 20/12/2015.','2015-11-15 16:46:31','A'),
	(802,'8596523','GLO','Dear Glo subscriber, if you have saved the settings, \r\nyou can send and receive pictures and use the internet. \r\nCall 121 for more Information. Stand Proud, Glo','2015-11-15 16:46:31','A'),
	(803,'8596518','GLO','Dear Glo subscriber, you are about to receive settings. Please use PIN 1111 to install the settings. Stand Proud, Glo!','2015-11-15 16:46:31','A'),
	(804,'8596491','GloGh','Sorry, your account is inactive. Please refill your account before 20/12/2015.','2015-11-15 16:46:31','A'),
	(805,'8594122','MPower','Deposits and withdrawals on MPower via MTN Mobile Money remain unstable. MTN engineers are working to resolve. All other services are stable. Have a great week.','2015-11-15 16:46:31','A'),
	(806,'8591379','mt. olivet','Congratulations John Doe. You&#039;ve been successfully added to Bread Of Life. Your password is +233504298056. Please visit http://churchcradle-1101.appspot.com, and use your mobile number as both username and password. You can pay and view your tithes, ','2015-11-15 16:46:31','A'),
	(807,'8586589','+233500190529','Thank you.','2015-11-15 16:46:31','A'),
	(808,'8585612','+233500190529','Please i need to talk to you','2015-11-15 16:46:31','A'),
	(809,'8567732','Surfline','Dear Valued Customer, You have   1.943GB remaining on 233255062021 . This is valid until 17/11/2015. Kindly log on to MySurfline for more information. Thank you','2015-11-15 16:46:31','A'),
	(810,'8565482','+233500190529','Please pick my call','2015-11-15 16:46:31','A'),
	(811,'8554841','+233209137263','I.ve sent you an InstaVoice message. To listen, download InstaVoice http://goo.gl/JbqrFo or dial *0* free. ','2015-11-15 16:46:31','A'),
	(812,'8530467','Esoko','hello','2015-11-15 16:46:31','A'),
	(813,'8530465','Esoko','hello','2015-11-15 16:46:31','A'),
	(814,'8523667','Surfline','Dear customer, access to our call centre has been fully restored. Thank you!','2015-11-15 16:46:31','A'),
	(815,'8499655','Surfline','Dear customer, access to our call centre has been fully restored. Thank you!','2015-11-15 16:46:31','A'),
	(816,'8497778','Surfline','Dear Customer, our call centre is currently unavailable. You can connect with us on Facebook at SurflineGH and Twitter @SurflineGH ','2015-11-15 16:46:31','A'),
	(817,'8497057','Surfline','Dear Customer, our call centre is currently unavailable. You can connect with us on Facebook at SurflineGH and Twitter @SurflineGH ','2015-11-15 16:46:31','A'),
	(818,'8456243','+233244348020','Classes','2015-11-15 16:46:31','A'),
	(819,'8437515','mt. olivet','Your Church Cradle verification code is cupkkgln','2015-11-15 16:46:32','A'),
	(820,'8427125','5200','Get 10 extra minutes free! Simple dial *125*30# to get 50mins to call USA, Canada, China and UK landlines for only Ghc5 and get 10 extra minutes to call Vodafone numbers!','2015-11-15 16:46:32','A'),
	(821,'8423557','451','Congratulations Mr Emmanuel Tabi, the Winner of GHS10 000 with MTN Dream Number draw! SMS WIN to 451 and change your life today! SMS WIN to 451 NOW. 60p/day','2015-11-15 16:46:32','A'),
	(822,'8415750','Vodafone','Calling Nigeria &amp; South Africa is cheapest on Vodafone! For only GhC 3.9, you get 16mins to call &amp; 2 SMS to text over the next 7 days! Just dial *125*32# today.','2015-11-15 16:46:32','A'),
	(823,'8413789','+233244348020','Classes','2015-11-15 16:46:32','A'),
	(824,'8397576','mt. olivet','Congratulations Ruby Akorfa. You&#039;ve been successfully added to Mt. Olivet Society. Your password is +233504298056. Please visit www.churchcradle.com, and use your mobile number as both username and password. You can pay and view your tithes, announce','2015-11-15 16:46:32','A'),
	(825,'8390342','TigoCash','Successful Tigo Cash withdrawal: collect GHC 98.00 from Agent DANIEL EGYIR - 0278798007. New Tigo Cash balance: GHC 0.61. 10/19/15 08:33 PM. Ref No 640006365. Thank you','2015-11-15 16:46:32','A'),
	(826,'8377888','EVD','You have successfully transferred amount 5.00 GHC to 233244642666 on Oct 19, 2015. Your updated balance is 12.98 GHC.Transaction ID: 2015101916430331304520309','2015-11-15 16:46:32','A'),
	(827,'8374049','EVD','You have successfully transferred amount 10.00 GHC to 233248242859 on Oct 19, 2015. Your updated balance is 18.08 GHC.Transaction ID: 2015101915325907704449763','2015-11-15 16:46:32','A'),
	(828,'8362836','mt. olivet','Hello Java, this is your tithe report for 2015\n\nJanuary: 0.0 GHS\nFebruary: 0.0 GHS\nMarch: 0.0 GHS\nApril: 0.0 GHS\nMay: 0.0 GHS\nJune: 0.0 GHS\nJuly: 0.0 GHS\nAugust: 0.0 GHS\nSeptember: 0.0 GHS\nOctober: 0.0 GHS\n\nGod richly bless you.','2015-11-15 16:46:32','A'),
	(829,'8362819','mt. olivet','Hello Java, this is your tithe report for 2015\n\nJanuary: 0.0 GHS\nFebruary: 0.0 GHS\nMarch: 0.0 GHS\nApril: 0.0 GHS\nMay: 0.0 GHS\nJune: 0.0 GHS\nJuly: 0.0 GHS\nAugust: 0.0 GHS\nSeptember: 0.0 GHS\nOctober: 0.0 GHS\n\nGod richly bless you.','2015-11-15 16:46:32','A'),
	(830,'8362778','+233504298056','Tithe','2015-11-15 16:46:32','A'),
	(831,'8362772','+233504298056','Tithe','2015-11-15 16:46:32','A'),
	(832,'8359512','web','test2','2015-11-15 16:46:32','A'),
	(833,'8359459','webed','test','2015-11-15 16:46:32','A'),
	(834,'8357772','wish','test msg from sandeep-route:gh3','2015-11-15 16:46:32','A'),
	(835,'8355388','+233244348020','Classes','2015-11-15 16:46:32','A'),
	(836,'8355341','+233244348020','Classes','2015-11-15 16:46:32','A'),
	(837,'8355316','+233244348020','Classes','2015-11-15 16:46:32','A'),
	(838,'8355293','+233244348020','Classes','2015-11-15 16:46:32','A'),
	(839,'8355264','+233244348020','Classes','2015-11-15 16:46:32','A'),
	(840,'8355197','+233244348020','Classes','2015-11-15 16:46:32','A'),
	(841,'8355066','+233244348020','Classes','2015-11-15 16:46:32','A'),
	(842,'8355036','+233244348020','Tithe','2015-11-15 16:46:33','A'),
	(843,'8355035','+233244387718','Tithe','2015-11-15 16:46:33','A'),
	(844,'8355031','+233244348020','Tithe','2015-11-15 16:46:33','A'),
	(845,'8354967','+233244348020','Tithe','2015-11-15 16:46:33','A'),
	(846,'8354961','+233244387718','Help','2015-11-15 16:46:33','A'),
	(847,'8354948','+233244387718','Tithe','2015-11-15 16:46:33','A'),
	(848,'8354939','+233244387718','Classes','2015-11-15 16:46:34','A'),
	(849,'8354841','+233244387718','Cls','2015-11-15 16:46:34','A'),
	(850,'8354768','+233244387718','Tithes','2015-11-15 16:46:35','A'),
	(851,'8354457','+233244387718','Cls','2015-11-15 16:46:35','A'),
	(852,'8353890','+233244387718','Tithes','2015-11-15 16:46:36','A'),
	(853,'8353284','+233244387718','Class','2015-11-15 16:46:36','A'),
	(854,'8353225','+233244387718','Tithes','2015-11-15 16:46:36','A'),
	(855,'8353149','+233244387718','Class','2015-11-15 16:46:36','A'),
	(856,'8353074','+233244387718','Tithes','2015-11-15 16:46:36','A'),
	(857,'8353000','+233244387718','Class','2015-11-15 16:46:36','A'),
	(858,'8350502','+233244387718','Cls','2015-11-15 16:46:36','A'),
	(859,'8349833','C Cradle','[Get] Worked!','2015-11-15 16:46:36','A'),
	(860,'8349719','+233244387718','Cls','2015-11-15 16:46:36','A'),
	(861,'8349193','+233244387718','Cls','2015-11-15 16:46:36','A'),
	(862,'8349169','+233244387718','Class','2015-11-15 16:46:36','A'),
	(863,'8349109','+233244387718','Tithes','2015-11-15 16:46:36','A'),
	(864,'8349084','+233244387718','Class','2015-11-15 16:46:36','A'),
	(865,'8349073','+233244387718','Tithes','2015-11-15 16:46:36','A'),
	(866,'8349010','EVD','You have successfully transferred amount 5.00 GHC to 233240020588 on Oct 18, 2015. Your updated balance is 28.27 GHC.Transaction ID: 2015101820364366504897317','2015-11-15 16:46:36','A'),
	(867,'8339629','Surfline','Dear Customer, you have successfully renewed your  1GB bundle on 233255062021, valid for 30 days . Please log on to MySurfline to track your data usage.','2015-11-15 16:46:36','A'),
	(868,'8338018','Surfline','Dear Customer, your main data bundle on 233255062021  will expire in the next 24hours ( 19/10/2015 11:52:18). Please log on to MySurfline to buy a new bundle to ensure uninterrupted browsing.','2015-11-15 16:46:36','A'),
	(869,'8336888','EVD','You have successfully transferred amount 3.00 GHC to 233240020588 on Oct 17, 2015. Your updated balance is 4.06 GHC.Transaction ID: 2015101711331391601248617','2015-11-15 16:46:36','A'),
	(870,'8335590','+233244264760','Hi bro hope you great, this is Nhyira Ba hope u hv not forgetting dat u promised me Game of thrones se 5 and some series. Will bring my ipad n ex HDD to church.','2015-11-15 16:46:36','A'),
	(871,'8330918','+233244387718','Tithes','2015-11-15 16:46:36','A'),
	(872,'8330842','mt. olivet','Thank you, Fetz. Mt. Olivet Society has acknowledged 200.0 GHS as tithe paid in October. \n Visit the website or Text TITHE,SERMON,EVENT or INFO to +233244960321 for more information. God richly bless you!','2015-11-15 16:46:37','A'),
	(873,'8330804','+233244387718','Class','2015-11-15 16:46:37','A'),
	(874,'8330796','Esoko','hello','2015-11-15 16:46:37','A'),
	(875,'8330768','+233244387718','Tit','2015-11-15 16:46:37','A'),
	(876,'8330760','+233244387718','Tithes','2015-11-15 16:46:37','A'),
	(877,'8330757','Esoko','hello','2015-11-15 16:46:37','A'),
	(878,'8330756','EVD','You have received 30.00 GHC from 0240968100/233240968100. a/c balance is 34.06 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015101722220540402595967','2015-11-15 16:46:37','A'),
	(879,'8330754','555','Your main balance is GHc 30.10040 valid until 15-01-2016. Yee Twi Kor promo is live! Win free cash or airtime instantly. Just top up with a recharge card today.','2015-11-15 16:46:37','A'),
	(880,'8330753','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-15 16:46:37','A'),
	(881,'8330739','+233244387718','Class','2015-11-15 16:46:37','A'),
	(882,'8330698','+233244387718','Help','2015-11-15 16:46:37','A'),
	(883,'8330692','+233244387718','Tithe','2015-11-15 16:46:37','A'),
	(884,'8330680','+233244387718','Cls','2015-11-15 16:46:37','A'),
	(885,'8330630','+233244387718','Cls','2015-11-15 16:46:37','A'),
	(886,'8330628','+233244387718','Clasa','2015-11-15 16:46:37','A'),
	(887,'8330322','+233244387718','Set birthday happy birthday to you, and God richly bless you.','2015-11-15 16:46:37','A'),
	(888,'8330309','+233244387718','Help','2015-11-15 16:46:37','A'),
	(889,'8330271','+233244387718','Tithe','2015-11-15 16:46:37','A'),
	(890,'8330261','+233244387718','Class','2015-11-15 16:46:37','A'),
	(891,'8318948','VFCash','Register for Vodafone Cash, the world  s number #1 mobile money service. Please visit our agents or retail shops with a valid photo ID now.','2015-11-15 16:46:37','A'),
	(892,'8318035','mt. olivet','hello','2015-11-15 16:46:38','A'),
	(893,'8316021','Google','Your Google verification code is 861052','2015-11-15 16:46:38','A'),
	(894,'8299540','mt. olivet','You are 30 years today Fetz.\nMt. Olivet and the entire congregation wishes you a Happy Birthday. God richly bless and strengthens you for more years to come.','2015-11-15 16:46:38','A'),
	(895,'8296743','Surfline','Enjoy 5% discount on your Surfline bundle with Slydepay!\r\nPay using the app or visit www.airty.me. Click this link for more details: http://goo.gl/rA0fRL\r\n','2015-11-15 16:46:38','A'),
	(896,'8287103','MTN','Cycling can be awesome too! http://bit.ly/1Qnd5fk. Enjoy more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-15 16:46:38','A'),
	(897,'8285041','Vodafone','Get 10 extra minutes free! Simply call family and friends in Nigeria. Talk for 50mins or more and get 10 extra minutes to call Vodafone numbers the next day.','2015-11-15 16:46:38','A'),
	(898,'8276202','mt. olivet','hello everybody','2015-11-15 16:46:38','A'),
	(899,'8276199','+233244387718','Set announce hello everybody','2015-11-15 16:46:38','A'),
	(900,'8275138','+233244387718','Help','2015-11-15 16:46:38','A'),
	(901,'8275130','+233244387718','Help','2015-11-15 16:46:38','A'),
	(902,'8275118','+233244387718','Class','2015-11-15 16:46:38','A'),
	(903,'8275105','+233244387718','Class','2015-11-15 16:46:38','A'),
	(904,'8275070','+233244387718','Class','2015-11-15 16:46:38','A'),
	(905,'8275058','+233244387718','Tithe','2015-11-15 16:46:38','A'),
	(906,'8275007','+233244387718','Tithe','2015-11-15 16:46:38','A'),
	(907,'8274927','+233244387718','Tithe','2015-11-15 16:46:38','A'),
	(908,'8274909','+233244387718','Tithe','2015-11-15 16:46:38','A'),
	(909,'8273865','+233244387718','Tithe','2015-11-15 16:46:38','A'),
	(910,'8273833','+233244387718','Tithe','2015-11-15 16:46:38','A'),
	(911,'8273830','+233244387718','Hello','2015-11-15 16:46:38','A'),
	(912,'8273782','+233244387718','Hello','2015-11-15 16:46:38','A'),
	(913,'8269128','MPower','MTN Mobile Money will be interrupted on Oct. 16 and restored by Oct. 19. Alternatively pay with MPower via bank cards, Airtel Money, Tigo Cash and tokens.','2015-11-15 16:46:38','A'),
	(914,'8256133','uniBank','Dear Valued Customer,\r\nHave you run out of units on your ECG prepaid meter and need to top up or are you an ECG vendor. \r\nVisit our Osu Main, Oxford Street, Accra Main, Makola, GIMPA, Madina, and East Legon branches to buy your ECG prepaid units if your l','2015-11-15 16:46:39','A'),
	(915,'8231348','mt. olivet','Thank you, Fetz. Mt. Olivet Society has acknowledged 56.0 GHS as tithe paid in October. \n Visit the website or Text TITHE,SERMON,EVENT or INFO to +233244960321 for more information. God richly bless you!','2015-11-15 16:46:39','A'),
	(916,'8231334','mt. olivet','Thank you, Fetz. Mt. Olivet Society has received 20.0 GHS as offering to the Lord. God richly bless you!','2015-11-15 16:46:39','A'),
	(917,'8231306','C Cradle','10.0 GHS has been made as a donation to the church. Thank you!.\n2015-10-15','2015-11-15 16:46:39','A'),
	(918,'8231263','C Cradle','11.0 GHS has been made as a donation to the church. Thank you!.\n2015-10-15','2015-11-15 16:46:39','A'),
	(919,'8219687','mt. olivet','hello','2015-11-15 16:46:39','A'),
	(920,'8217475','weber','test','2015-11-15 16:46:39','A'),
	(921,'8217450','web','test','2015-11-15 16:46:39','A'),
	(922,'8211465','Surfline','Dear Customer, your main data bundle on 233255062021 will expire in 5 days( 19/10/2015 11:52:18).Please log on to MySurfline to buy a new bundle to ensure uninterrupted browsing.Thank You','2015-11-15 16:46:39','A'),
	(923,'8206258','web','test','2015-11-15 16:46:39','A'),
	(924,'8206232','web','test','2015-11-15 16:46:39','A'),
	(925,'8205708','+233209423311','Aaaww thnk u dear','2015-11-15 16:46:39','A'),
	(926,'8205642','EVD','You have successfully transferred amount 30.00 GHC to 233546706099 on Oct 13, 2015. Your updated balance is 28.04 GHC.Transaction ID: 2015101320192574404944390','2015-11-15 16:46:39','A'),
	(927,'8205611','+121','You have transferred GHc30.00000 credit to 233209423311. Your own account balance is GHc12.57040.','2015-11-15 16:46:39','A'),
	(928,'8205599','555','Your main balance is GHc 42.62040 valid until 11-01-2016. Yee Twi Kor promo is live! Win free cash or airtime instantly. Just top up with a recharge card today.','2015-11-15 16:46:39','A'),
	(929,'8205598','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-15 16:46:39','A'),
	(930,'8198086','MTN','Click to see some of the amazing things on earth! http://bit.ly/1Ligzwn with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-15 16:46:39','A'),
	(931,'8186949','555','Dear Customer, your Browser Mini Double internet package has expired. Reactivate now to enjoy our internet packages. Vodafone, power to you.','2015-11-15 16:46:39','A'),
	(932,'8185687','Surfline','Dear Customer, your data bundle expires in 5 days (18/10/2015). Please ensure you have sufficient airtime on 233255062021 for auto renewal to occur. ','2015-11-15 16:46:39','A'),
	(933,'8183374','+233244245345','Have you recieved it?','2015-11-15 16:46:39','A'),
	(934,'8168979','+233244755702','Ok','2015-11-15 16:46:39','A'),
	(935,'8159663','+233209154458','Can I start coming?','2015-11-15 16:46:39','A'),
	(936,'8156827','+233244245345','Sent you the account.','2015-11-15 16:46:39','A'),
	(937,'8156821','Surfline','Dear Valued Customer, You have   1.992GB remaining on 233255062021 . This is valid until 19/10/2015. Kindly log on to MySurfline for more information. Thank you','2015-11-15 16:46:40','A'),
	(938,'8146198','+233500190529','He who does what is sinful is of the devil, because the devil has been sinning from the beginning. The reason the son of God appeared was to destroy the devil&#039;s work.','2015-11-15 16:46:40','A'),
	(939,'8145290','+233244245345','I can create it and credit it but we dont have any credit on the main account so messages wont deliver.   ','2015-11-15 16:46:40','A'),
	(940,'8140579','+233500190529','Please pick my God.','2015-11-15 16:46:40','A'),
	(941,'8130014','MTN','Super Hilarious! Click to watch the most weirdest &amp; funniest auditions http://bit.ly/1Z4ahd8 with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-15 16:46:40','A'),
	(942,'8124149','Vodafone','Calling Nigeria &amp; South Africa is cheapest on Vodafone! For only 99pesewas, you get 4mins to call family and friends! Just dial *125*32# today','2015-11-15 16:46:40','A'),
	(943,'8122276','EVD','You have successfully transferred amount 5.00 GHC to 233240020588 on Oct 9, 2015. Your updated balance is 29.34 GHC.Transaction ID: 2015100908324008204278469','2015-11-15 16:46:40','A'),
	(944,'8122080','555','Your main balance is GHc 110.52040 valid until 07-01-2016. Talk for 2 whole days. Dial 5544 to enjoy 250mins for Vodafone calls. 12mins for other networks and 50MB data for only GHS 2.','2015-11-15 16:46:40','A'),
	(945,'8122062','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-15 16:46:40','A'),
	(946,'8121586','Esoko','hello','2015-11-15 16:46:40','A'),
	(947,'8121548','Esoko','hello','2015-11-15 16:46:40','A'),
	(948,'8121546','Esoko','hello','2015-11-15 16:46:40','A'),
	(949,'8121500','555','Your main balance is GHc 80.52040 valid until 07-01-2016. Talk for 2 whole days. Dial 5544 to enjoy 250mins for Vodafone calls. 12mins for other networks and 50MB data for only GHS 2.','2015-11-15 16:46:40','A'),
	(950,'8121495','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-15 16:46:40','A'),
	(951,'8121427','555','Your main balance is GHc 50.52040 valid until 07-01-2016. Talk for 2 whole days. Dial 5544 to enjoy 250mins for Vodafone calls. 12mins for other networks and 50MB data for only GHS 2.','2015-11-15 16:46:40','A'),
	(952,'8110312','MPower','Beat the stress! Top up &amp; pay your bills at www.mpower.com.gh or dial *714*44#. Buy your Surfline bundle, airtime on all networks, pay ECG Postpaid &amp; DSTV bills','2015-11-15 16:46:40','A'),
	(953,'8089759','+233240591662','Thnks Nana. Much appreciated','2015-11-15 16:46:40','A'),
	(954,'8089730','+233240591662','Aryt wil add u soon','2015-11-15 16:46:40','A'),
	(955,'8089722','+121','You have transferred GHc10.00000 credit to 233208392598. Your own account balance is GHc20.77040.','2015-11-15 16:46:40','A'),
	(956,'8089664','+233240591662','Haaha. Re u nt on watsap dear?','2015-11-15 16:46:40','A'),
	(957,'8089642','+233240591662','Oooooh dear! Its u.','2015-11-15 16:46:40','A'),
	(958,'8089611','+233240591662','Pls  i dnt know u. Can tell me where you got my contact dear?','2015-11-15 16:46:40','A'),
	(959,'8089574','+233240591662','Thank you. Buh name pls?','2015-11-15 16:46:41','A'),
	(960,'8089478','555','Your main balance is GHc 30.82040 valid until 05-01-2016. Talk for 2 whole days. Dial 5544 to enjoy 250mins for Vodafone calls. 12mins for other networks and 50MB data for only GHS 2.','2015-11-15 16:46:41','A'),
	(961,'8089476','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-15 16:46:41','A'),
	(962,'8089172','Esoko','hello','2015-11-15 16:46:41','A'),
	(963,'8089171','EVD','You have received 30.00 GHC from 0240968100/233240968100. a/c balance is 62.36 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015100719164907102413918','2015-11-15 16:46:41','A'),
	(964,'8083227','MTN','Its The MTN GH Stands in Worship concert. Grab ur ticket @ MTN Osu, Graphic Road, Accra Mall or West Hills mall for Ghc50 VIP &amp; Ghc30 Regular. Dial 1355 to download ur favourite callertunez','2015-11-15 16:46:41','A'),
	(965,'8060563','Surfline','Dear Customer, the recent service interruptions have been resolved. We apologize for any inconvenience caused. Surfline... It&#039;s about time','2015-11-15 16:46:41','A'),
	(966,'8060375','Surfline','Dear Customer, the recent service interruptions have been resolved. We apologize for any inconvenience caused. Surfline... It&#039;s about time','2015-11-15 16:46:41','A'),
	(967,'8058326','5200','Calling USA,Canada,China and UK landlines  is cheapest on Vodafone! Get 50 minutes to call for only GHc5. Just dial *125*30# to subscribe &amp; get 10 extra minutes to Vodafone free!','2015-11-15 16:46:41','A'),
	(968,'8057296','+233209154458','Boss , sorry yu haven&#039;t heard from me for some time now .I know it&#039;s very unserious of me but travelled to my Uncle&#039;s place wasn&#039;t gettin a stable network back in town . Please set the date I could come around','2015-11-15 16:46:41','A'),
	(969,'8057241','Vodafone','Calling Nigeria &amp; South Africa is cheapest on Vodafone! For only GhC 3.9, you get 16mins to call &amp; 2 SMS to text over the next 7 days! Just dial *125*32# today','2015-11-15 16:46:41','A'),
	(970,'8057045','Surfline','Dear customer, we apologize for interruptions in our services today. Our team is working to rectify this in the shortest possible time. Thank you.','2015-11-15 16:46:41','A'),
	(971,'8053764','Surfline','Dear customer, we apologize for interruptions in our services today. Our team is working to rectify this in the shortest possible time. Thank you.','2015-11-15 16:46:41','A'),
	(972,'8051675','MTN','They love &amp; live magic! Click to share in the fun http://bit.ly/1Q1Birk with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-15 16:46:41','A'),
	(973,'8023155','+233244387718','0000  P NPP:2601   NDC:2001 CPP:100a','2015-11-15 16:46:41','A'),
	(974,'8023089','+233244387718','0000  P NPP:2601   NDC:2001 CPP:1004','2015-11-15 16:46:41','A'),
	(975,'8022978','+233244387718','0000  P NPP:2601   NDC:2001 CPP:1001','2015-11-15 16:46:41','A'),
	(976,'8022743','+233244387718','0000  P NPP:2601   NDC:2001 CPP:1001','2015-11-15 16:46:41','A'),
	(977,'8022681','+233244387718','0000  P NPP:2601   NDC:2001 CPP:1001','2015-11-15 16:46:41','A'),
	(978,'8022598','+233244387718','0020 P NPP:2601 NDC:2001 CPP:1001','2015-11-15 16:46:41','A'),
	(979,'8011335','+233244387718','0020 P NPP:2601 NDC:2001 CPP:1001','2015-11-15 16:46:41','A'),
	(980,'8011293','+233244387718','0020 P NPP:2600 NDC:2000 CPP:1000','2015-11-15 16:46:42','A'),
	(981,'8011248','+233244387718','0020 P NPP:2600 NDC:2000 CPP:1000','2015-11-15 16:46:42','A'),
	(982,'8011205','+233244387718','0000 P NPP : 2600 NDC:2000 CPP:1000','2015-11-15 16:46:42','A'),
	(983,'8011135','+233244387718','0000 P NPP: 2600 NDC:2000 CPP:1000','2015-11-15 16:46:42','A'),
	(984,'8011104','+233244387718','0000 P NPP:2600 NDC:2000 CPP:1000','2015-11-15 16:46:42','A'),
	(985,'8011087','+233244387718','0000 P NPP2600 NDC:2000 CPP:1000','2015-11-15 16:46:42','A'),
	(986,'8010667','+233244387718','0000 P NPP2600 NDC:2000 CPP:1000','2015-11-15 16:46:42','A'),
	(987,'8010597','+233244387718','0000 P NPP2600 NDC: 2000 CPP:1000','2015-11-15 16:46:42','A'),
	(988,'8010550','+233244387718','0000 PNPP:2600 NDC: 2000 CPP:1000','2015-11-15 16:46:42','A'),
	(989,'8010492','+233244387718','0000 P NPP:2600 NDC :2000 CPP:1000','2015-11-15 16:46:42','A'),
	(990,'8010433','+233244387718','0000 P NPP:2600 NDC: 2000 CPP:1000','2015-11-15 16:46:42','A'),
	(991,'8010302','+233244387718','0000 P NPP:2600 NDC:2000 CPP:1000','2015-11-15 16:46:42','A'),
	(992,'8010115','555','Service Request Handled Succesfully\r\n','2015-11-15 16:46:42','A'),
	(993,'8010069','555','Welcome to MTN Voice Mail Service. Please text &#039;START&#039; or &#039;STOP&#039; to activate or deactivate service.','2015-11-15 16:46:42','A'),
	(994,'8010022','555','Welcome to MTN Voice Mail Service. Please text &#039;START&#039; or &#039;STOP&#039; to activate or deactivate service.','2015-11-15 16:46:42','A'),
	(995,'8010016','555','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','2015-11-15 16:46:42','A'),
	(996,'8010011','555','Welcome to MTN Voice Mail Service. Please text &#039;START&#039; or &#039;STOP&#039; to activate or deactivate service.','2015-11-15 16:46:42','A'),
	(997,'8009985','555','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','2015-11-15 16:46:42','A'),
	(998,'8009983','EVD','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','2015-11-15 16:46:42','A'),
	(999,'8009958','+121','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','2015-11-15 16:46:42','A'),
	(1000,'8009956','EVD','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','2015-11-15 16:46:42','A'),
	(1001,'8009955','EVD','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','2015-11-15 16:46:42','A'),
	(1002,'8009954','EVD','Sorry, your results was rejected due to invalid result format. Please use the right result format. Thank you!','2015-11-15 16:46:43','A'),
	(1003,'8009762','+256205462012','i have a crazy plan for today and i want to share it with you, call me  +256205462012','2015-11-15 16:46:43','A'),
	(1004,'8009711','EVD','You have successfully transferred amount 1.00 GHC to 233244387718 on Oct 5, 2015. Your updated balance is 3.30 GHC.Transaction ID: 2015100509475620701250029','2015-11-15 16:46:43','A'),
	(1005,'8007831','EVD','You have successfully transferred amount 2.00 GHC to 233240020588 on Oct 5, 2015. Your updated balance is 4.40 GHC.Transaction ID: 2015100508533817601192051','2015-11-15 16:46:43','A'),
	(1006,'7987030','EVD','You have received 30.00 GHC from 0240968100/233240968100. a/c balance is 56.91 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015100319291069502507580','2015-11-15 16:46:43','A'),
	(1007,'7987029','Esoko','hello','2015-11-15 16:46:43','A'),
	(1008,'7987027','1330','esoko : 30.00 GHC top-up credit sent from esoko TXN ID: 6237248 Send money, top-up or pay with txtnpay. Help line:0302902981','2015-11-15 16:46:43','A'),
	(1009,'7978640','+233500190529','Please lets text','2015-11-15 16:46:43','A'),
	(1010,'7977960','+121','You have transferred GHc15.00000 credit to 233209425151. Your own account balance is GHc28.78040.','2015-11-15 16:46:43','A'),
	(1011,'7977936','+121','You have transferred GHc20.00000 credit to 233209423311. Your own account balance is GHc43.83040.','2015-11-15 16:46:43','A'),
	(1012,'7977911','Esoko','hello','2015-11-15 16:46:43','A'),
	(1013,'7977902','EVD','You have successfully transferred amount 15.00 GHC to 233546706099 on Oct 3, 2015. Your updated balance is 26.71 GHC.Transaction ID: 2015100319352336003001951','2015-11-15 16:46:43','A'),
	(1014,'7977892','Esoko','hello','2015-11-15 16:46:43','A'),
	(1015,'7977874','555','Your main balance is GHc 83.93040 valid until 01-01-2016. Talk for 2 whole days. Dial 5544 to enjoy 250mins for Vodafone calls. 12mins for other networks and 50MB data for only GHS 2.','2015-11-15 16:46:43','A'),
	(1016,'7977873','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-15 16:46:43','A'),
	(1017,'7977865','Esoko','hello','2015-11-15 16:46:43','A'),
	(1018,'7970067','+233271210210','0000 m Npp :290 ndc:190 cpp:120','2015-11-15 16:46:43','A'),
	(1019,'7969313','+233271210210','0000 m Npp:290 ndc:190 cpp:120','2015-11-15 16:46:43','A'),
	(1020,'7968988','+233271210210','Help','2015-11-15 16:46:43','A'),
	(1021,'7968890','+233271210210','info','2015-11-15 16:46:43','A'),
	(1022,'7963711','+233240020588','Pls am in church.i will call u wen i close.hope u gud','2015-11-15 16:46:43','A'),
	(1023,'7925839','MTN','Super hilarious! Click to have the best laugh today! http://bit.ly/1JzSKho .Enjoy more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-15 16:46:43','A'),
	(1024,'7924554','uniBank','Participate in the uniBank Super 360 Promo. Deposit GHS100 to join and maintain a minimum balance of GHS200. You stand the chance of winning GHS15K in the 3rd draw and GHS360K etc. in the ultimate draw. HURRY, Promo ends 30th October 2015. Terms and Condi','2015-11-15 16:46:44','A'),
	(1025,'7922611','+233208392598','Mwaah wo 10ghc di3 wo boa koraa','2015-11-15 16:46:44','A'),
	(1026,'7922448','+121','You have transferred GHc10.00000 credit to 233208392598. Your own account balance is GHc60.06040.','2015-11-15 16:46:44','A'),
	(1027,'7908436','Esoko','hello','2015-11-15 16:46:44','A'),
	(1028,'7906663','+121','You have transferred GHc10.00000 credit to 233209137263. Your own account balance is GHc70.69040.','2015-11-15 16:46:44','A'),
	(1029,'7906610','555','Your main balance is GHc 80.74040 valid until 30-12-2015. Talk for 2 whole days. Dial 5544 to enjoy 250mins for Vodafone calls. 12mins for other networks and 50MB data for only GHS 2.','2015-11-15 16:46:44','A'),
	(1030,'7905525','+233243342729','Am trying to reach u but ur line isn&#039;t going through, I just wanted to know how u are doing. ','2015-11-15 16:46:44','A'),
	(1031,'7878791','Vodafone','Vodafone appstore is live! Visit appstore.vodafone.com.gh,register &amp; download any app of your choice and stand a chance to win free 100MB data.','2015-11-15 16:46:44','A'),
	(1032,'7840794','5200','FREE INTERNET! Simply dial *700*24# to buy 30MB for only GHs1. Vodafone will add extra 30MB for free on Ghana&#039;s fastest 3G+ network. http://opr.as/e2Y','2015-11-15 16:46:44','A'),
	(1033,'7823363','+233244960321','Agent: annan\nPolling Station: Lebanon Club (a)\nConstituency: Bantama\n','2015-11-15 16:46:44','A'),
	(1034,'7823334','+233504298056','Info','2015-11-15 16:46:44','A'),
	(1035,'7823054','+233504298056','Info','2015-11-15 16:46:44','A'),
	(1036,'7822777','+233244960321','Agent: annan\nPolling Station: Lebanon Club (a)\nConstituency: Bantama\n','2015-11-15 16:46:44','A'),
	(1037,'7822734','+233504298056','Info','2015-11-15 16:46:44','A'),
	(1038,'7822292','+233504298056','Info','2015-11-15 16:46:44','A'),
	(1039,'7807169','MTN','Super fun tricks! http://bit.ly/1ODNpgT .Click to enjoy more with your 300MB YouTube Bundle! @ GHC 3 only. Dial *138*1*6#','2015-11-15 16:46:44','A'),
	(1040,'7803685','+233242112511','doctorkrow@yahoo.com','2015-11-15 16:46:44','A'),
	(1041,'7775139','+233244387718','Help','2015-11-15 16:46:44','A'),
	(1042,'7775096','+233244387718','Info','2015-11-15 16:46:44','A'),
	(1043,'7775035','+233244387718','Info','2015-11-15 16:46:44','A'),
	(1044,'7774967','+233244387718','Agent: edd\nPolling Station: Anglican Prim Sch Tepa (a)\nConstituency: Ahafo Ano North\n','2015-11-15 16:46:44','A'),
	(1045,'7774959','+233244387718','Info','2015-11-15 16:46:44','A'),
	(1046,'7774865','+233244387718','Info','2015-11-15 16:46:44','A'),
	(1047,'7774685','+233244387718','Info','2015-11-15 16:46:44','A'),
	(1048,'7774335','+233244387718','Info','2015-11-15 16:46:45','A'),
	(1049,'7770063','138','Internet Usage Flash, you have 294.00MB remaining on your assigned 1GB. Your 1GB will be renewed when you run out. To stop renewal, SMS STOP to 138.','2015-11-15 16:46:45','A'),
	(1050,'7767381','Esoko','hello','2015-11-15 16:46:45','A'),
	(1051,'7767380','1330','esoko : 15.00 GHC top-up credit sent from esoko TXN ID: 6227529 Send money, top-up or pay with txtnpay. Help line:0302902981','2015-11-15 16:46:45','A'),
	(1052,'7767371','Esoko','hello','2015-11-15 16:46:45','A'),
	(1053,'7767365','Esoko','hello','2015-11-15 16:46:45','A'),
	(1054,'7767364','1330','esoko : 30.00 GHC top-up credit sent from esoko TXN ID: 6227527 Send money, top-up or pay with txtnpay. Help line:0302902981','2015-11-15 16:46:45','A'),
	(1055,'7767363','EVD','You have received 30.00 GHC from 0240968100/233240968100. a/c balance is 34.84 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015092822213081502097330','2015-11-15 16:46:45','A'),
	(1056,'7767361','555','Your main balance is GHc 51.15170 valid until 27-12-2015. Its talk time. Dial 7171 &amp; enjoy free calls to any Vodafone number plus double bonus credit on GHs 2 top ups or more for GHS 3.99 weekly','2015-11-15 16:46:45','A'),
	(1057,'7767360','Esoko','hello','2015-11-15 16:46:45','A'),
	(1058,'7767353','555','You have received GHc30.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-15 16:46:45','A'),
	(1059,'7739471','MTNPay4Me','The number 233544295582 tried to reach you, but you did not answer the call','2015-11-15 16:46:45','A'),
	(1060,'7739441','MTNPay4Me','The number 233544295582 tried to reach you, but you did not answer the call','2015-11-15 16:46:45','A'),
	(1061,'7733624','+233544295582','I dont know','2015-11-15 16:46:45','A'),
	(1062,'7732729','+233544295582','Seriously. Will you go to the hospital? So sorry. God should mercifully and kindly grant you strength, it is not easy.','2015-11-15 16:46:45','A'),
	(1063,'7691538','+233244387718','0000 P NPP:2600 NDC:2000 CPP:1000','2015-11-15 16:46:45','A'),
	(1064,'7691530','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','2015-11-15 16:46:45','A'),
	(1065,'7691526','+233244387718','help','2015-11-15 16:46:45','A'),
	(1066,'7691511','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','2015-11-15 16:46:45','A'),
	(1067,'7691502','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','2015-11-15 16:46:45','A'),
	(1068,'7689025','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','2015-11-15 16:46:45','A'),
	(1069,'7684907','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','2015-11-15 16:46:45','A'),
	(1070,'7684729','+233244387718','help','2015-11-15 16:46:46','A'),
	(1071,'7683817','0000','help','2015-11-15 16:46:46','A'),
	(1072,'7683576','0000','help','2015-11-15 16:46:46','A'),
	(1073,'7680090',' 233244960321','help','2015-11-15 16:46:46','A'),
	(1074,'7677517','+233244960321','just a test. Hello!','2015-11-15 16:46:46','A'),
	(1075,'7675433','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','2015-11-15 16:46:46','A'),
	(1076,'7674905','0000','01234 P GOOG:12 APPL:13 YHOO:45 NYSE:56','2015-11-15 16:46:46','A'),
	(1077,'7674373','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','2015-11-15 16:46:46','A'),
	(1078,'7674182','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','2015-11-15 16:46:46','A'),
	(1079,'7672825','+233244387718','0000 M NPP:2600 NDC:2000 CPP:1000','2015-11-15 16:46:46','A'),
	(1080,'7671149','MTN','Young &amp; free! http://bit.ly/1KKcIeB .Watch more with your 300MB YouTube Bundle! @ GHC 3 only. Dial *138*1*6#','2015-11-15 16:46:46','A'),
	(1081,'7667872','0000','01234 m GOOG:12 APPL:13 YHOO:45 NYSE:56','2015-11-15 16:46:46','A'),
	(1082,'7667374','+233242679330','Feeling gr8,i woke up some few mins ago so that should tell u how well i slept :)','2015-11-15 16:46:46','A'),
	(1083,'7667287','+233242679330','Good morning naughty :)','2015-11-15 16:46:46','A'),
	(1084,'7664369','0000','0012 P GOOG:12 APPL:13 YHOO:45 NYSE:56','2015-11-15 16:46:46','A'),
	(1085,'7664268','0000','01234 V GOOG:12 APPL:13 YHOO:45 NYSE:56','2015-11-15 16:46:46','A'),
	(1086,'7664263','0000','01234 P GOOG:12 APPL:13 YHOO:45 NYSE:56','2015-11-15 16:46:46','A'),
	(1087,'7664255','0000','0012 M GOOG:12 APPL:13 YHOO:45 NYSE:56','2015-11-15 16:46:46','A'),
	(1088,'7664244','0000','01234 M JFK:68 GOOG:89 APPL:90','2015-11-15 16:46:46','A'),
	(1089,'7653811','2131','We congratulate you! It is our duty to reward loyal customers like you with special offers: 3 FREE Kitchen tips! To claim it, reply OK.\r\n','2015-11-15 16:46:46','A'),
	(1090,'7637777','+233209154458','How long would it take to build a website for &quot;campaign &quot; and how much ?','2015-11-15 16:46:46','A'),
	(1091,'7632137','Esoko','hello','2015-11-15 16:46:46','A'),
	(1092,'7632133','1330','esoko : 30.00 GHC top-up credit sent from esoko TXN ID: 6219355 Send money, top-up or pay with txtnpay. Help line:0302902981','2015-11-15 16:46:46','A'),
	(1093,'7632126','EVD','You have received 30.00 GHC from 0240968100/233240968100. a/c balance is 44.11 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015092322530139302301253','2015-11-15 16:46:47','A'),
	(1094,'9264962','Surfline','Dear Customer, your account has been rewarded with 200MB Gift Data valid for 30 days.Thank you for being a Surfline customer.','2015-11-16 11:53:39','A'),
	(1095,'9264953','Surfline','Dear Customer, you have successfully renewed your  1GB bundle on 233255062021, valid for 30 days . Please log on to MySurfline to track your data usage.','2015-11-16 11:53:39','A'),
	(1096,'9297845','MTN','Congratulations! You have gained 41548 points! Dial *482# to redeem your reward. Talk, Text &amp; Browse more to gain more points and get more rewards from MTN.','2015-11-17 03:17:57','A'),
	(1097,'9303491','MTNGH CC','&quot;On Twitter? follow @askMTNGhana Post on our wall or DM for assistance to your enquiry, request or complaint. Welcome to the New World&quot;','2015-11-17 09:36:00','A'),
	(1098,'9310153','MTN','Out of BOUNDS! http://bit.ly/1WX94FR .Enjoy more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-17 13:54:58','A'),
	(1099,'9340894','7777','Your number 244960321 was chosen to meet Cristiano Ronaldo and to win CASH and TRIPS to MADRID to see him playing (0.20/day)! Reply with YES','2015-11-18 11:42:05','A'),
	(1100,'9352888','+233500190529','I thank God. Having lect','2015-11-18 16:26:42','A'),
	(1101,'9353071','+233500190529','Yes 5pm','2015-11-18 16:36:17','A'),
	(1102,'9353292','+233500190529','When','2015-11-18 16:42:25','A'),
	(1103,'9353727','+233500190529','You are funny','2015-11-18 16:51:17','A'),
	(1104,'9353780','+233500190529','What will you study','2015-11-18 16:53:29','A'),
	(1105,'9353856','+233500190529','Really. Fortran or classical mechanics','2015-11-18 16:56:51','A'),
	(1106,'9353913','+233500190529','Do you know','2015-11-18 16:59:43','A'),
	(1107,'9354603','EVD','You have successfully transferred amount 9.00 GHC to 233240020588 on Nov 18, 2015. Your updated balance is 144.55 GHC.Transaction ID: 2015111817234347301872602','2015-11-18 17:24:08','A'),
	(1108,'9356611','EVD','You have successfully transferred amount 10.00 GHC to 233243723075 on Nov 18, 2015. Your updated balance is 131.37 GHC.Transaction ID: 2015111818441107903685052','2015-11-18 18:44:49','A'),
	(1109,'9356851','EVD','You have successfully transferred amount 5.00 GHC to 233248242859 on Nov 18, 2015. Your updated balance is 125.02 GHC.Transaction ID: 2015111818593513402765750','2015-11-18 19:00:01','A'),
	(1110,'9357002','+121','You have transferred GHc10.00000 credit to 233500190529. Your own account balance is GHc185.23039.','2015-11-18 19:08:09','A'),
	(1111,'9373544','MPower','In 24 hrs, you could receive GHs100 on ur MPower account to send/spend/transfer as you please. Complete 2 transactions by tomorrow and wait for our call.','2015-11-19 12:32:15','A'),
	(1112,'9380759','NLA','Give yourself a treat this Christmas. Win GHc15,825.75 in the NLA Soccer Cash Game this weekend. Click here to see the fixtures http://nla.com.gh/soccer_cash_page.php','2015-11-19 17:12:11','A'),
	(1113,'9374778','EVD','You have successfully transferred amount 10.00 GHC to 233243513608 on Nov 19, 2015. Your updated balance is 104.07 GHC.Transaction ID: 2015111913174915301680031','2015-11-19 17:12:12','A'),
	(1114,'9388142','+233500190529','Please pick up my call','2015-11-19 21:12:34','A'),
	(1115,'9398965','EVD','You have successfully transferred amount 7.00 GHC to 233244755702 on Nov 20, 2015. Your updated balance is 86.15 GHC.Transaction ID: 2015112009285290201520476','2015-11-20 09:29:37','A'),
	(1116,'9419536','+233244755702','1307 5214 612808','2015-11-20 13:31:36','A'),
	(1117,'9419638','EVD','You have successfully transferred amount 5.00 GHC to 233244755702 on Nov 20, 2015. Your updated balance is 90.82 GHC.Transaction ID: 2015112013345619202449270','2015-11-20 13:35:22','A'),
	(1118,'9419677','+233244755702','Thanks','2015-11-20 13:36:24','A'),
	(1119,'9419716','+121','You have transferred GHc5.00000 credit to 233208506335. Your own account balance is GHc99.89840.','2015-11-20 13:38:52','A'),
	(1120,'9437842','Esoko','hello','2015-11-21 12:19:01','A'),
	(1121,'9437882','Esoko','hello','2015-11-21 12:21:24','A'),
	(1122,'9437891','Esoko','hello','2015-11-21 12:22:50','A'),
	(1123,'9437896','555','Your main balance is GHc 131.68640 valid until 19-02-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-11-21 12:23:24','A'),
	(1124,'9437895','555','You have received GHc35.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-21 12:23:24','A'),
	(1125,'9437958','555','You have received GHc35.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-21 12:25:58','A'),
	(1126,'9438015','Esoko','hello','2015-11-21 12:30:04','A'),
	(1127,'9438018','Esoko','hello','2015-11-21 12:30:08','A'),
	(1128,'9438017','Esoko','hello','2015-11-21 12:30:08','A'),
	(1129,'9438019','Esoko','hello','2015-11-21 12:30:10','A'),
	(1130,'9438026','Esoko','hello','2015-11-21 12:30:18','A'),
	(1131,'9438056','Esoko','hello','2015-11-21 12:32:30','A'),
	(1132,'9438085','Esoko','hello','2015-11-21 12:35:01','A'),
	(1133,'9438162','555','Your main balance is GHc 306.68640 valid until 19-02-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-11-21 12:39:03','A'),
	(1134,'9438161','555','Your main balance is GHc 236.68640 valid until 19-02-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-11-21 12:39:03','A'),
	(1135,'9438160','555','You have received GHc35.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-21 12:39:03','A'),
	(1136,'9438159','555','Your main balance is GHc 271.68640 valid until 19-02-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-11-21 12:39:03','A'),
	(1137,'9438158','555','You have received GHc35.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-11-21 12:39:03','A'),
	(1138,'9452153','+121','You have transferred GHc5.00000 credit to 233201019861. Your own account balance is GHc343.91641.','2015-11-22 10:49:10','A'),
	(1139,'9460255','+121','You have transferred GHc10.00000 credit to 233508756637. Your own account balance is GHc332.02640.','2015-11-22 19:10:04','A'),
	(1140,'9466343','138','Internet Usage Flash: Credit 1132.50MB, Time Left 9 day(s).','2015-11-23 04:39:53','A'),
	(1141,'9475621','MPower','With a GHs100 MPower Token, you can shop on Telefonika, top up airtime/buy Surfline data, etc. 2 transactions from now till Friday and u could be GHs100 richer!','2015-11-23 12:44:16','A'),
	(1142,'9479831','MTN','Y&#039;ello, wish your family, friends and business partners in USA a Happy Thanksgiving Day. Keep calling USA at only Ghs 0.1200 / min. Welcome to the New World.','2015-11-23 15:21:41','A'),
	(1143,'9480239','+233202017882','Help','2015-11-23 15:42:03','A'),
	(1144,'9480295','+233202017882','Info','2015-11-23 15:43:43','A'),
	(1145,'9482598','Points','You have 3103 points this week in the Yee Twi Kor promo. Total points in the promo is 11423. Use more to win this week. Dial *5000# check points and stop SMS','2015-11-23 17:29:47','A'),
	(1146,'9500074','MTN','Awesomeness at its peak! http://bit.ly/1QFrOp6 .Watch more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-24 12:06:32','A'),
	(1147,'9511356','EVD','You have successfully transferred amount 5.00 GHC to 233240020588 on Nov 24, 2015. Your updated balance is 61.65 GHC.Transaction ID: 2015112417590181502281329','2015-11-24 19:53:50','A'),
	(1148,'9511360','Xtratime','Important: don&#039;t miss out on MTN Xtratime! You can now borrow credit whenever your balance runs low. Dial *506# and press 2 anytime you need it!','2015-11-24 19:53:59','A'),
	(1149,'9511368','Xtratime','Important: don&#039;t miss out on MTN Xtratime! You can now borrow credit whenever your balance runs low. Dial *506# and press 2 anytime you need it!','2015-11-24 19:54:14','A'),
	(1150,'9523657','+233500190529','Please pick up','2015-11-25 11:25:06','A'),
	(1151,'9521955','TigoCash','Successful Tigo Cash withdrawal: collect GHC 28.00 from Agent DANIEL EGYIR - 0278798007. New Tigo Cash balance: GHC 1.61. 11/25/15 09:49 AM. Ref No 690003406. Thank you','2015-11-25 11:25:06','A'),
	(1152,'9493185','MTN','Congratulations! You have gained 47150 points! Dial *482# to redeem your reward. Talk, Text &amp; Browse more to gain more points and get more rewards from MTN.','2015-11-25 11:25:06','A'),
	(1153,'9523836','+233244384793','HELP','2015-11-25 11:37:14','A'),
	(1154,'9527978','MPower','All issues affecting Add Money via MTN Mobile Money have been fixed, and the service is fully restored. Many thanks for your patience.','2015-11-25 13:54:18','A'),
	(1155,'9532572','+121','You have transferred GHc10.00000 credit to 233509714640. Your own account balance is GHc311.56641.','2015-11-25 16:30:28','A'),
	(1156,'9533487','+233500190529','Dont call me again','2015-11-25 17:08:17','A'),
	(1157,'9557181','+121','You have transferred GHc20.00000 credit to 233202017882. Your own account balance is GHc241.34641.','2015-11-26 15:32:49','A'),
	(1158,'9560165','+233240020588','Sorry, I am busy. Call back later','2015-11-26 17:55:46','A'),
	(1159,'9570756','MPower','#BlackFriday Special: Get rewarded by MPower! We?re adding a 2nd GHs100 Token today. It?s now 2 users getting a GHs100 Token each. Transact on MPower Now!','2015-11-27 10:10:40','A'),
	(1160,'9571468','451','MTN number 233244960321 could be chosen today in the MTN GHS10 000 Dream Number daily draw! Reply YES to stand a chance to win up to GHS10 000 today! 60p/day','2015-11-27 10:28:29','A'),
	(1161,'9571561','1303','Dial 1303 Now. Be it &#039;come over&#039; from Stoneboy, or &#039;Dancehall messiah&#039; from Shatawale? MTN radio has it all. Start listening now.','2015-11-27 10:31:23','A'),
	(1162,'9573578','MTN','Hilarious! Its called the impossible climb! http://bit.ly/1Opns25 .Click to watch more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-11-27 11:38:43','A'),
	(1163,'9609435','+121','You have transferred GHc1.00000 credit to 233209137263. Your own account balance is GHc146.34641.','2015-11-28 06:55:33','A'),
	(1164,'9613109','7777','On CR7 Live at MTN (0.20/day) you 244960321 can also WIN GHC 50,000 GRAND PRIZE!! You would be filthy rich right? So reply YES and make it Real!','2015-11-28 12:16:44','A'),
	(1165,'9618334','Points','You have 601 points this week in the Yee Twi Kor promo. Total points in the promo is 12817. Use more to win this week. Dial *5000# check points and stop SMS','2015-11-28 19:07:20','A'),
	(1166,'9620731','+233208506335','Oh ok,wil b der before 11','2015-11-29 00:18:51','A'),
	(1167,'9623556','+233240020588','am in church.','2015-11-29 07:35:41','A'),
	(1168,'9626000','Surfline','Dear Valued Customer, You have  0.46GB remaining on 233255062021. This is valid until 16/12/2015. Kindly log on to MySurfline for more information. Thank you','2015-11-29 11:42:24','A'),
	(1169,'9626030','+233244548522','Help','2015-11-29 11:43:48','A'),
	(1170,'9636216','138','Yello, your expiring internet bundle has been renewed for GHC20 and valid until 30 Dec 2015. Keep topping up your account and carry your remaining bundle from your previous purchase.','2015-11-30 06:01:45','A'),
	(1171,'9653750','+233244755702','Cha thanks oooo, I dey cut my hair for Isreal e der','2015-11-30 13:48:31','A'),
	(1172,'9651186','EVD','You have successfully transferred amount 2.00 GHC to 233244755702 on Nov 30, 2015. Your updated balance is 31.44 GHC.Transaction ID: 2015113013042327401964982','2015-11-30 13:48:31','A'),
	(1173,'9655762','EVD','You have successfully transferred amount 5.00 GHC to 233244642666 on Nov 30, 2015. Your updated balance is 25.75 GHC.Transaction ID: 2015113014381925904048610','2015-11-30 14:38:57','A'),
	(1174,'9660917','Points','You have 360 points this week in the Yee Twi Kor promo. Total points in the promo is 13295. Use more to win this week. Dial *5000# check points and stop SMS','2015-11-30 16:40:48','A'),
	(1175,'9670529','MTN','Congratulations! You have gained 47519 points! Dial *482# to redeem your reward. Talk, Text &amp; Browse more to gain more points and get more rewards from MTN.','2015-12-01 02:43:34','A'),
	(1176,'9674209','Vodafone','Vodafone Backup detected your new handset. You will receive a message to configure it and restore your previous data (contacts, calendar etc).','2015-12-01 07:55:24','A'),
	(1177,'9674228','Vodafone','Vodafone Backup allows you to protect your SIM phonebook. Make sure you save your contacts on the SIM card.','2015-12-01 07:57:45','A'),
	(1178,'9674229','555','Congratulations, you have successfully subscribed to the Streamer Basic data package which comes with 1.6 GB of data. Stay connected with Vodafone power to you.','2015-12-01 07:57:53','A'),
	(1179,'9678779','+233240020588','I will call u .av a nice day','2015-12-01 10:35:34','A'),
	(1180,'9678781','+233240020588','Sweetheart goodmorning .i missed ur call.am sorry for dat .i was at de reading room n i left my fon at de hall.hope u r good am at de library now.so wen i leave der wai','2015-12-01 10:35:36','A'),
	(1181,'9687045','MTN','Heard of Aquabatigue? Click to find out http://bit.ly/1YEPR9f .Click to watch more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6 #','2015-12-01 14:52:56','A'),
	(1182,'9679160','+121','You have transferred GHc5.00000 credit to 233508756637. Your own account balance is GHc84.63640.','2015-12-01 14:52:56','A'),
	(1183,'9689469','Asoriba','Hello king,\nThank you for signing up on Asoriba.\nEnter 0cea2 to verify your phone number.','2015-12-01 16:52:10','A'),
	(1184,'9689803','Vodafone','Dear Customer, if you miss a call or receive a message from unknown foreign numbers, SMS the no &amp; message to shortcode 419. Kindly do not return such calls','2015-12-01 17:11:53','A'),
	(1185,'9708280','Surfline','THIS IS BIG! Activate a SurfPlus bundle this season and get 75% bonus data to connect with loved ones this season. Offer runs from now till 31st Dec, 2015','2015-12-02 11:16:16','A'),
	(1186,'9720973','MPower','Top up your phone credits anytime, anywhere with MPower. Also pay for your DSTV, GoTV, ECG Postpaid and Surfline Internet at www.mpower.com.gh or dial *714*44#','2015-12-02 17:36:07','A'),
	(1187,'9763759','tiGO','Dear Customer! You have received free 10 mins to call all Tigo lines.Simply use your Tigo line and receive more surprises.Thank you!','2015-12-04 08:00:01','A'),
	(1188,'9765661','Surfline','Super Deal! Buy any LG Smart TV from Electromart and get a FREE Surfline Mifi plus FREE Internet for 6months. Call 0244313594 or 0264456100 for your orders. Terms &amp; Conditions apply','2015-12-04 09:03:36','A'),
	(1189,'9765742','555','You have received GHc37.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-12-04 09:06:07','A'),
	(1190,'9765751','555','Your main balance is GHc 109.96910 valid until 03-03-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-12-04 09:06:10','A'),
	(1191,'9765790','Esoko','hello','2015-12-04 09:07:08','A'),
	(1192,'9765789','EVD','You have received 37.00 GHC from 0240968100/233240968100. a/c balance is 46.85 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015120409064580002083605','2015-12-04 09:07:08','A'),
	(1193,'9765795','EVD','You have received 37.00 GHC from 0240968100/233240968100. a/c balance is 83.85 GHC. Recharge 50p &amp; Above,Dial *124# to see your bonus. Transaction ID: 2015120409073271902084516','2015-12-04 09:07:57','A'),
	(1194,'9765796','Esoko','hello','2015-12-04 09:08:02','A'),
	(1195,'9765807','555','Your main balance is GHc 146.96910 valid until 03-03-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-12-04 09:08:38','A'),
	(1196,'9765806','555','You have received GHc37.00000 as bonus credit and 20 FREE SMS. Vodafone, power to you.','2015-12-04 09:08:38','A'),
	(1197,'9765844','555','Your main balance is GHc 183.96910 valid until 03-03-2016. Get 20 Yee Twi Kor points! Dial 5544 &amp; get 250mins for Vodafone calls, 12mins for others &amp; more   GHS2 for 2days.','2015-12-04 09:10:07','A'),
	(1198,'9781376','232','Welcome to Airtel! Automatic data settings will be sent to you shortly. Kindly install, restart your device and start browsing now! Use 0000 as PIN code if requested. Top up and dial *202# to buy a Too Much pack now and enjoy great value on Voice, SMS and','2015-12-04 16:57:21','A'),
	(1199,'9781431','+233264148556','Hello','2015-12-04 16:59:17','A'),
	(1200,'9781432','232','Congratulations! Your device is now configured. Please restart your device and start browsing. Dial *125# to buy  a data bundle and browse chaw.','2015-12-04 16:59:19','A'),
	(1201,'9781433','+233264148556','Hi','2015-12-04 16:59:22','A'),
	(1202,'9787854','+233500190529','Please pick','2015-12-04 19:34:08','A'),
	(1203,'9791835','+233240020588','Selby am sorry please pick up.i was bored with some1 that was why.','2015-12-04 21:33:31','A'),
	(1204,'9808836','+233240020588','Selby pls am sorry.pls pls pick up.i wont repeat again i beg u.plsssssssssssss','2015-12-05 13:00:32','A'),
	(1205,'9809177','+233240020588','What are you doing that you cant talk.i said am sorry','2015-12-05 13:17:58','A'),
	(1206,'9809196','+233240020588','Pls dont make a big deal out of this small issue','2015-12-05 13:18:48','A'),
	(1207,'9809314','+233240020588',' please talk to me','2015-12-05 13:24:32','A'),
	(1208,'9809319','+233240020588','Please please please','2015-12-05 13:24:47','A'),
	(1209,'9809440','+233240020588','Plsssss','2015-12-05 13:29:32','A'),
	(1210,'9809439','+233240020588','Nothing it was just that i was bored by some1','2015-12-05 13:29:33','A'),
	(1211,'9821662','+233500190529','What did my sister tell you','2015-12-05 21:01:21','A'),
	(1212,'9835692','+121','You have transferred GHc5.00000 credit to 233509714640. Your own account balance is GHc80.51910.','2015-12-06 16:35:06','A'),
	(1213,'9836528','EVD','You have successfully transferred amount 10.00 GHC to 233548856722 on Dec 6, 2015. Your updated balance is 62.17 GHC.Transaction ID: 2015120617395920804376821','2015-12-06 17:40:25','A'),
	(1214,'9836608','EVD','You have successfully transferred amount 10.00 GHC to 233243887762 on Dec 6, 2015. Your updated balance is 52.07 GHC.Transaction ID: 2015120617413628202514591','2015-12-06 17:42:05','A'),
	(1215,'9836702','+233548856722','Thanks so much my dear, am very grateful for everything, may God bless you more','2015-12-06 17:46:06','A'),
	(1216,'9866254','Points','You have 2018 points this week in the Yee Twi Kor promo. Total points in the promo is 15518. Use more to win this week. Dial *5000# check points and stop SMS','2015-12-07 18:09:07','A'),
	(1217,'9870637','Esoko','hello','2015-12-07 20:26:22','A'),
	(1218,'9850872','EVD','You have successfully transferred amount 15.00 GHC to 233240020588 on Dec 7, 2015. Your updated balance is 36.92 GHC.Transaction ID: 2015120711002752204013558','2015-12-07 20:26:22','A'),
	(1219,'9876178','+233240020588','I.ve sent you an InstaVoice message. To listen, download InstaVoice http://goo.gl/JbqrFo or dial *0* free. Text LYF to 2510.','2015-12-08 04:36:59','A'),
	(1220,'9888496','MTN','Amazing talent http://bit.ly/1NSVe32 .Watch more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-12-08 21:18:50','A'),
	(1221,'9876173','MTN','Congratulations! You have gained 49172 points! Dial *482# to redeem your reward. Talk, Text &amp; Browse more to gain more points and get more rewards from MTN.','2015-12-08 21:18:50','A'),
	(1222,'9899520','+233244642666','I.ve sent you an InstaVoice message. To listen, download InstaVoice http://goo.gl/JbqrFo or dial *0* free. Text LYF to 2510.','2015-12-08 21:19:01','A'),
	(1223,'9899519','+233244642666','I.ve sent you an InstaVoice message. To listen, download InstaVoice http://goo.gl/JbqrFo or dial *0* free. Text LYF to 2510.','2015-12-08 21:19:01','A'),
	(1224,'9899633','+233500190529','Pleasssssse','2015-12-08 21:40:46','A'),
	(1225,'9899666','+233500190529','I.ve sent you an InstaVoice message. To listen, download InstaVoice http://goo.gl/JbqrFo or dial *0* free. Text LYF to 2510.','2015-12-08 21:46:58','A'),
	(1226,'9899675','+233500190529','I.ve sent you an InstaVoice message. To listen, download InstaVoice http://goo.gl/JbqrFo or dial *0* free. Text LYF to 2510.','2015-12-08 21:49:16','A'),
	(1227,'9899803','+233500190529','Can you make your thoughts clear to me? I will like it more if you make things clear than this silence.','2015-12-08 22:06:07','A'),
	(1228,'9899855','+233500190529','I have really missed you','2015-12-08 22:13:24','A'),
	(1229,'9942454','+233243342729','Why aren&#039;t u picking ur phone','2015-12-09 19:49:35','A'),
	(1230,'9899517','+233244642666','I.ve sent you an InstaVoice message. To listen, download InstaVoice http://goo.gl/JbqrFo or dial *0* free. Text LYF to 2510.','2015-12-09 19:49:35','A'),
	(1231,'9942459','+233243342729','I have a problem with my phone that&#039;s why it has been going off','2015-12-09 19:49:41','A'),
	(1232,'9959351','451','Another GHS10 000 winner with MTN Dream Number! Reply YES &amp; you too can win like Mr Haruna Fuseni our new GHS10 000 Jackpot winner! Just reply YES, NOW! 60p/day','2015-12-10 11:54:10','A'),
	(1233,'9967658','5200','FREE MINUTES to start the Christmas season! Get 1 FREE MINUTE after every 6 minutes of calls to talk to friends on Vodafone from now till Sunday. Dial *126# to see your minutes.','2015-12-10 16:37:46','A'),
	(1234,'9971011','+233500190529','Okay I understand. Thank you for everything and I wish you all the best in life. Merry christmas and happy birthday in advance. May the Almighty God be with you.','2015-12-10 20:03:02','A'),
	(1235,'9989192','MTN','Meet the amazing Dynamo! http://bit.ly/1IRfwSV .Watch more with your 300MB YouTube Bundle @ GHC 3 only! Dial *138*1*6#','2015-12-11 10:07:47','A'),
	(1236,'9982997','Surfline','Dear Customer, your main data bundle on 233255062021 will expire in 5 days( 16/12/2015 11:52:10).Please log on to MySurfline to buy a new bundle to ensure uninterrupted browsing.Thank You','2015-12-11 10:07:48','A'),
	(1237,'9993290','MPower','Three GHs100 Tokens To Go! Your next 2 transactions could earn you GHs100 on your MPower account to shop for amazing deals on Tisu, Telefonika and more.\r\n\r\n','2015-12-11 11:51:31','A'),
	(1238,'10004086','Vodafone','Win a smartphone this festive season. Text S404723 to 585 to use &quot;feliz navidad&quot; as your callertune this Christmas, for just 32p. A monthly subscription fee of 62p applies.','2015-12-11 14:53:55','A'),
	(1239,'10010876','+233209154458','Hi Selby,  it&#039;s been a while hope you good !','2015-12-11 16:46:21','A'),
	(1240,'10011873','121','You have transferred GHc5.00000 credit to 233508756637. Your own account balance is GHc64.74910.','2015-12-11 17:09:37','A'),
	(1241,'10014208','+233500190529','Please I need to discuss something with you, please.','2015-12-11 18:36:07','A'),
	(1242,'10014622','MTN_CC','Y&#039;ello! It doesnt get better than this! Get the best deals to talk,browse and send sms to all networks. Simply dial *575# and choose your preferred bundle now.','2015-12-11 18:52:08','A'),
	(1243,'10033153','5200','Awesome. You received 1.00 min for Vodafone calls. Keep enjoying 1 minute free after every 6 minutes call from now till Sunday. Dial*126# to see your mins','2015-12-12 12:06:46','A');

/*!40000 ALTER TABLE `sms_gateway_messages` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table special_offerings
# ------------------------------------------------------------

DROP TABLE IF EXISTS `special_offerings`;

CREATE TABLE `special_offerings` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `church_id` int(11) NOT NULL,
  `message` text NOT NULL,
  `amount` double NOT NULL,
  `approved_by` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `created_date` date DEFAULT NULL,
  `status` char(1) NOT NULL DEFAULT 'P',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `special_offerings` WRITE;
/*!40000 ALTER TABLE `special_offerings` DISABLE KEYS */;

INSERT INTO `special_offerings` (`id`, `member_id`, `church_id`, `message`, `amount`, `approved_by`, `created_by`, `created_date`, `status`)
VALUES
	(1,1,1,'i thank God for a successful return journey',800,1,1,'2015-09-21','A');

/*!40000 ALTER TABLE `special_offerings` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_modules
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_modules`;

CREATE TABLE `sys_modules` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `module_name` text NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `sys_modules` WRITE;
/*!40000 ALTER TABLE `sys_modules` DISABLE KEYS */;

INSERT INTO `sys_modules` (`id`, `module_name`, `status`)
VALUES
	(1,'SMS','A'),
	(2,'MARRIAGE ANNOUNCEMENTS','A'),
	(3,'LIBRARY','A'),
	(4,'MEMBERS','A'),
	(5,'OFFERINGS','A'),
	(6,'TITHES','A'),
	(7,'BIBLE READINGS','A'),
	(8,'FUNERAL ANNOUNCEMENTS','A'),
	(9,'EVENTS ANNOUNCEMENTS','A'),
	(10,'DONATIONS','A');

/*!40000 ALTER TABLE `sys_modules` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_year
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_year`;

CREATE TABLE `sys_year` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` int(11) NOT NULL,
  `alias` text NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `sys_year` WRITE;
/*!40000 ALTER TABLE `sys_year` DISABLE KEYS */;

INSERT INTO `sys_year` (`id`, `name`, `alias`, `status`)
VALUES
	(1,2015,'15','A'),
	(2,2016,'16','A'),
	(3,2017,'17','A');

/*!40000 ALTER TABLE `sys_year` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tithe_push_verifier
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tithe_push_verifier`;

CREATE TABLE `tithe_push_verifier` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `church_id` int(11) NOT NULL,
  `sms_count` double NOT NULL,
  `created_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `tithe_push_verifier` WRITE;
/*!40000 ALTER TABLE `tithe_push_verifier` DISABLE KEYS */;

INSERT INTO `tithe_push_verifier` (`id`, `church_id`, `sms_count`, `created_ts`, `status`)
VALUES
	(3,1,2,'2015-10-22 08:30:57','A'),
	(4,0,0,'2015-10-30 07:34:45','A'),
	(5,0,0,'2015-11-07 07:32:57','A'),
	(6,0,0,'2015-11-10 07:32:59','A'),
	(7,0,0,'2015-11-13 06:45:57','A'),
	(8,0,0,'2015-11-16 06:45:58','A'),
	(9,0,0,'2015-11-19 06:45:59','A'),
	(10,0,0,'2015-11-22 06:22:45','A'),
	(11,0,0,'2015-11-25 06:22:56','A'),
	(12,0,0,'2015-11-28 06:22:58','A'),
	(13,0,0,'2015-11-30 06:22:59','A');

/*!40000 ALTER TABLE `tithe_push_verifier` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table tithes
# ------------------------------------------------------------

DROP TABLE IF EXISTS `tithes`;

CREATE TABLE `tithes` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL,
  `created_by` int(11) NOT NULL,
  `amount` double NOT NULL,
  `created_ts` datetime NOT NULL,
  `date` datetime NOT NULL,
  `modified_ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `approved_by` int(11) NOT NULL,
  `modified_by` int(11) NOT NULL,
  `church_id` int(11) NOT NULL,
  `status` char(1) NOT NULL DEFAULT 'A',
  `is_editable` char(1) NOT NULL DEFAULT 'T',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `tithes` WRITE;
/*!40000 ALTER TABLE `tithes` DISABLE KEYS */;

INSERT INTO `tithes` (`id`, `member_id`, `created_by`, `amount`, `created_ts`, `date`, `modified_ts`, `approved_by`, `modified_by`, `church_id`, `status`, `is_editable`)
VALUES
	(3,1,1,1000,'2015-08-29 10:49:07','2015-08-29 10:49:07','2015-08-29 10:49:23',1,1,1,'A','F'),
	(4,1,0,4000,'2015-10-07 09:25:07','2015-10-07 09:25:07','2015-10-07 09:25:07',0,0,1,'A','F'),
	(16,1,1,100,'2015-10-14 12:53:23','2015-10-14 12:53:23','2015-10-14 12:53:35',1,1,1,'A','F'),
	(18,1,0,56,'2015-10-15 01:33:33','2015-10-15 01:33:33','2015-10-15 01:33:33',0,0,1,'A','F'),
	(19,1,1,200,'2015-10-17 22:40:44','2015-10-17 22:40:44','2015-10-17 22:40:55',1,1,1,'A','F'),
	(20,1,0,30,'2015-10-17 22:43:16','2015-10-17 22:43:16','2015-10-17 22:43:16',0,0,1,'A','F'),
	(21,19,1,1000,'2015-10-29 07:49:51','2015-10-29 07:49:51','2015-10-29 07:58:51',0,1,1,'D','T');

/*!40000 ALTER TABLE `tithes` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
