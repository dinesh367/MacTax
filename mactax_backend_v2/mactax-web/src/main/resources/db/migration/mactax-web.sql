-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.7.27-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema `mactax-web`
--

CREATE DATABASE IF NOT EXISTS `mactax-web`;
USE `mactax-web`;

--
-- Definition of table `authority`
--

DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `role` varchar(50) NOT NULL,
  `status` int(1) unsigned NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `updated_by` int(10) unsigned DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_au.created_by_ref_u.id` (`created_by`),
  KEY `FK_au.updated_by_ref_u.id` (`updated_by`),
  CONSTRAINT `FK_au.created_by_ref_u.id` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_au.updated_by_ref_u.id` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `authority`
--

/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;


--
-- Definition of table `order_item_mapping`
--

DROP TABLE IF EXISTS `order_item_mapping`;
CREATE TABLE `order_item_mapping` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order` int(10) unsigned NOT NULL,
  `product_item` int(10) unsigned NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `updated_by` int(10) unsigned DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_oim.placed_by_ref_o.id` (`order`),
  KEY `FK_oim.product_item_ref_o.id` (`product_item`),
  KEY `FK_oim.created_by_ref_u.id` (`created_by`),
  KEY `FK_oim.updated_by_ref_u.id` (`updated_by`),
  CONSTRAINT `FK_oim.created_by_ref_u.id` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_oim.placed_by_ref_o.id` FOREIGN KEY (`order`) REFERENCES `orders` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_oim.product_item_ref_o.id` FOREIGN KEY (`product_item`) REFERENCES `product_item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_oim.updated_by_ref_u.id` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `order_item_mapping`
--

/*!40000 ALTER TABLE `order_item_mapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item_mapping` ENABLE KEYS */;


--
-- Definition of table `orders`
--

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `placed_by` int(10) unsigned NOT NULL,
  `documents` longtext,
  `status` int(1) NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `updated_by` int(10) unsigned DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_o.placed_by_ref_p.id` (`placed_by`),
  KEY `FK_o.created_by_ref_u.id` (`created_by`),
  KEY `FK_o.updated_by_ref_u.id` (`updated_by`),
  CONSTRAINT `FK_o.created_by_ref_u.id` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_o.placed_by_ref_u.id` FOREIGN KEY (`placed_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_o.updated_by_ref_u.id` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orders`
--

/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;


--
-- Definition of table `organization`
--

DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `contact_name` varchar(100) NOT NULL,
  `contact_number` varchar(15) NOT NULL,
  `pan` varchar(10) NOT NULL,
  `gst_number` varchar(15) NOT NULL,
  `line1` varchar(100) NOT NULL,
  `line2` varchar(100) DEFAULT NULL,
  `city` varchar(100) NOT NULL,
  `state` varchar(100) NOT NULL,
  `pincode` varchar(6) NOT NULL,
  `country` varchar(100) NOT NULL,
  `status` int(1) unsigned NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `organization`
--

/*!40000 ALTER TABLE `organization` DISABLE KEYS */;
/*!40000 ALTER TABLE `organization` ENABLE KEYS */;


--
-- Definition of table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `logo` varchar(500) DEFAULT NULL,
  `domain` varchar(2048) NOT NULL,
  `email_otp_type` int(1) unsigned NOT NULL,
  `sms_otp_type` int(1) unsigned NOT NULL,
  `service_provider` int(10) unsigned DEFAULT NULL,
  `organization` int(10) unsigned NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_p.organization_ref_o.id` (`organization`),
  KEY `FK_p.sp_ref_sp.id` (`service_provider`),
  CONSTRAINT `FK_p.organization_ref_o.id` FOREIGN KEY (`organization`) REFERENCES `organization` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_p.sp_ref_sp.id` FOREIGN KEY (`service_provider`) REFERENCES `service_provider` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;


--
-- Definition of table `product_item`
--

DROP TABLE IF EXISTS `product_item`;
CREATE TABLE `product_item` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `product_item_type` int(1) NOT NULL,
  `price` double(15,2) NOT NULL,
  `product` int(10) unsigned NOT NULL,
  `status` int(1) NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `updated_by` int(10) unsigned DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_pi.logged_in_user_ref_p.id` (`product`),
  KEY `FK_pi.created_by_ref_u.id` (`created_by`),
  KEY `FK_pi.updated_by_ref_u.id` (`updated_by`),
  CONSTRAINT `FK_pi.created_by_ref_u.id` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_pi.product_ref_u.id` FOREIGN KEY (`product`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_pi.updated_by_ref_u.id` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product_item`
--

/*!40000 ALTER TABLE `product_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_item` ENABLE KEYS */;


--
-- Definition of table `service_provider`
--

DROP TABLE IF EXISTS `service_provider`;
CREATE TABLE `service_provider` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email_host_name` varchar(255) DEFAULT NULL,
  `email_host_port` varchar(255) DEFAULT NULL,
  `email_auth_user` varchar(255) DEFAULT NULL,
  `email_auth_password` varchar(255) DEFAULT NULL,
  `email_host_type` varchar(255) DEFAULT NULL,
  `email_auth_type` varchar(255) DEFAULT NULL,
  `email_trasport_protocol` varchar(255) DEFAULT NULL,
  `email_trasport_auth` bit(1) DEFAULT NULL,
  `email_debug` bit(1) NOT NULL,
  `email_sender_name` varchar(255) DEFAULT NULL,
  `sms_provider` int(1) unsigned DEFAULT NULL,
  `sms_host` varchar(255) NOT NULL,
  `sms_sender_id` varchar(255) DEFAULT NULL,
  `sms_sender_password` varchar(255) DEFAULT NULL,
  `sms_sender_name` varchar(255) DEFAULT NULL,
  `sms_aws_max_price` varchar(45) DEFAULT NULL,
  `status` int(1) unsigned NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `service_provider`
--

/*!40000 ALTER TABLE `service_provider` DISABLE KEYS */;
/*!40000 ALTER TABLE `service_provider` ENABLE KEYS */;


--
-- Definition of table `user_authority`
--

DROP TABLE IF EXISTS `user_authority`;
CREATE TABLE `user_authority` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user` int(10) unsigned NOT NULL,
  `authority` int(10) unsigned NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `updated_by` int(10) unsigned DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_aam.authority_ref_au.id` (`authority`),
  KEY `FK_aam.created_by_ref_u.id` (`created_by`),
  KEY `FK_aam.updated_by_ref_u.id` (`updated_by`),
  KEY `FK_aam.user_ref_u.id` (`user`) USING BTREE,
  CONSTRAINT `FK_aam.authority_ref_au.id` FOREIGN KEY (`authority`) REFERENCES `authority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_aam.created_by_ref_u.id` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_aam.updated_by_ref_u.id` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_aam.user_ref_u.id` FOREIGN KEY (`user`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_authority`
--

/*!40000 ALTER TABLE `user_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_authority` ENABLE KEYS */;


--
-- Definition of table `user_login_details`
--

DROP TABLE IF EXISTS `user_login_details`;
CREATE TABLE `user_login_details` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `logged_in_user` int(10) unsigned NOT NULL,
  `email_otp` varchar(6) DEFAULT NULL,
  `sms_otp` varchar(6) DEFAULT NULL,
  `ip_address` varchar(45) NOT NULL,
  `created_by` int(10) unsigned NOT NULL,
  `updated_by` int(10) unsigned DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_uld.logged_in_user_ref_u.id` (`logged_in_user`),
  KEY `FK_uld.created_by_ref_u.id` (`created_by`),
  KEY `FK_uld.updated_by_ref_u.id` (`updated_by`),
  CONSTRAINT `FK_uld.created_by_ref_u.id` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_uld.logged_in_user_ref_u.id` FOREIGN KEY (`logged_in_user`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_uld.updated_by_ref_u.id` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user_login_details`
--

/*!40000 ALTER TABLE `user_login_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_login_details` ENABLE KEYS */;


--
-- Definition of table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `username` varchar(254) NOT NULL,
  `password` varchar(500) NOT NULL,
  `decrypt_password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(15) DEFAULT NULL,
  `email_otp` varchar(6) DEFAULT NULL,
  `sms_otp` varchar(6) DEFAULT NULL,
  `status` int(1) unsigned NOT NULL,
  `enabled` bit(1) NOT NULL,
  `product` int(10) unsigned NOT NULL,
  `organization` int(10) unsigned NOT NULL,
  `created_by` int(10) unsigned DEFAULT NULL,
  `updated_by` int(10) unsigned DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_updated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `FK_u.organization_ref_o.id` (`organization`),
  KEY `FK_u.product_ref_p.id` (`product`),
  KEY `FK_u.created_by_ref_u.id` (`created_by`),
  KEY `FK_u.updated_by_ref_u.id` (`updated_by`),
  CONSTRAINT `FK_u.created_by_ref_u.id` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_u.organization_ref_u.id` FOREIGN KEY (`organization`) REFERENCES `organization` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_u.product_ref_p.id` FOREIGN KEY (`product`) REFERENCES `product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_u.updated_by_ref_u.id` FOREIGN KEY (`updated_by`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
