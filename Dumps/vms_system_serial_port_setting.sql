CREATE DATABASE  IF NOT EXISTS `vms` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `vms`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: vms
-- ------------------------------------------------------
-- Server version	5.5.32-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `system_serial_port_setting`
--

DROP TABLE IF EXISTS `system_serial_port_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_serial_port_setting` (
  `system_serial_port_setting_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `system_serial_port_setting_com` varchar(255) NOT NULL DEFAULT '',
  `system_serial_port_setting_date_created` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `system_serial_port_setting_last_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `serial_port_baud_rate_id` int(11) unsigned NOT NULL DEFAULT '0',
  `serial_port_parity_bit_id` int(11) unsigned NOT NULL DEFAULT '0',
  `serial_port_data_bit_id` int(10) unsigned NOT NULL DEFAULT '0',
  `serial_port_stop_bit_id` int(11) unsigned NOT NULL DEFAULT '0',
  `serial_port_buffer_size_id` int(11) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`system_serial_port_setting_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_serial_port_setting`
--

LOCK TABLES `system_serial_port_setting` WRITE;
/*!40000 ALTER TABLE `system_serial_port_setting` DISABLE KEYS */;
/*!40000 ALTER TABLE `system_serial_port_setting` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-07-12 13:26:17
