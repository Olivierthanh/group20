-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: localhost    Database: wallet_project
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `categoryName` varchar(45) NOT NULL,
  `type` enum('expense','income') NOT NULL,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Award','income'),(2,'Gifts','income'),(3,'Interest Money','income'),(4,'Others','income'),(5,'Salary','income'),(6,'Selling','income'),(7,'Bills & Utilities','expense'),(8,'Business','expense'),(9,'Education','expense'),(10,'Entertainment','expense'),(11,'Family','expense'),(12,'Fees & Charges','expense'),(13,'Food & Beverage','expense'),(14,'Friends & Lover','expense'),(15,'Gifts & Donations','expense'),(16,'Health & Fitness','expense'),(17,'Insurances','expense'),(18,'Investment','expense'),(19,'Others','expense'),(20,'Shopping','expense'),(21,'Transportation','expense'),(22,'Travel','expense'),(23,'Withdrawal','expense'),(24,'Accessories','expense'),(25,'Books','expense'),(26,'Café','expense'),(27,'Charity','expense'),(28,'Children & Babies','expense'),(29,'Clothing','expense'),(30,'Doctor','expense'),(31,'Electricity','expense'),(32,'Electronics','expense'),(33,'Footwear','expense'),(34,'Funeral','expense'),(35,'Games','expense'),(36,'Gas','expense'),(37,'Home Improvement','expense'),(38,'Home Services','expense'),(39,'Internet','expense'),(40,'Maintenance','expense'),(41,'Marriage','expense'),(42,'Movies','expense'),(43,'Parking Fees','expense'),(44,'Personal Care','expense'),(45,'Petrol','expense'),(46,'Pets','expense'),(47,'Pharmacy','expense'),(48,'Phone','expense'),(49,'Rentals','expense'),(50,'Restaurants','expense'),(51,'Sports','expense'),(52,'Taxi','expense'),(53,'Television','expense'),(54,'Water','expense');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password_reset_token`
--

DROP TABLE IF EXISTS `password_reset_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `password_reset_token` (
  `tokenId` int(11) NOT NULL AUTO_INCREMENT,
  `token` varchar(45) NOT NULL,
  `expiryDate` timestamp NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`tokenId`),
  KEY `fk_password_reset_token_user_idx` (`userId`),
  CONSTRAINT `fk_password_reset_token_user` FOREIGN KEY (`userId`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password_reset_token`
--

LOCK TABLES `password_reset_token` WRITE;
/*!40000 ALTER TABLE `password_reset_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `password_reset_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `transaction` (
  `transactionId` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) NOT NULL,
  `date` timestamp NOT NULL,
  `note` text,
  `type` enum('income','expense') NOT NULL,
  `walletId` int(11) NOT NULL,
  `categoryId` int(11) NOT NULL,
  PRIMARY KEY (`transactionId`),
  KEY `fk_expense_wallet1_idx` (`walletId`),
  KEY `fk_expense_category1_idx` (`categoryId`),
  CONSTRAINT `fk_expense_category1` FOREIGN KEY (`categoryId`) REFERENCES `category` (`categoryid`),
  CONSTRAINT `fk_expense_wallet1` FOREIGN KEY (`walletId`) REFERENCES `wallet` (`walletid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(60) NOT NULL,
  `name` varchar(45) NOT NULL,
  `gender` enum('male','female') NOT NULL,
  `dateOfBirth` date NOT NULL,
  `address` varchar(60) NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'thanhlt998@gmail.com','$2a$10$WjatkZfOKejvj93WAXWQH.JmW3croR/HOiufBbUEQlKaSdzJQHytW','Lê Tuấn Thành','male','1998-01-01','Hà Nội');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_wallet`
--

DROP TABLE IF EXISTS `user_wallet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_wallet` (
  `userWalletId` int(11) NOT NULL AUTO_INCREMENT,
  `walletId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`userWalletId`),
  KEY `fk_user_wallet_wallet1_idx` (`walletId`),
  KEY `fk_user_wallet_user1_idx` (`userId`),
  CONSTRAINT `fk_user_wallet_user1` FOREIGN KEY (`userId`) REFERENCES `user` (`userid`),
  CONSTRAINT `fk_user_wallet_wallet1` FOREIGN KEY (`walletId`) REFERENCES `wallet` (`walletid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_wallet`
--

LOCK TABLES `user_wallet` WRITE;
/*!40000 ALTER TABLE `user_wallet` DISABLE KEYS */;
INSERT INTO `user_wallet` VALUES (3,1,1),(4,2,1);
/*!40000 ALTER TABLE `user_wallet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wallet`
--

DROP TABLE IF EXISTS `wallet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `wallet` (
  `walletId` int(11) NOT NULL AUTO_INCREMENT,
  `walletName` varchar(45) NOT NULL,
  `balance` int(11) NOT NULL,
  `currency` enum('ALL','AFN','ARS','AWG','AUD','AZN','BSD','BBD','BYN','BZD','BMD','BOB','BAM','BWP','BGN','BRL','BND','KHR','CAD','KYD','CLP','CNY','COP','CRC','HRK','CUP','CZK','DKK','DOP','XCD','EGP','SVC','EUR','FKP','FJD','GHS','GIP','GTQ','GGP','GYD','HNL','HKD','HUF','ISK','INR','IDR','IRR','IMP','ILS','JMD','JPY','JEP','KZT','KPW','KRW','KGS','LAK','LBP','LRD','MKD','MYR','MUR','MXN','MNT','MZN','NAD','NPR','ANG','NZD','NIO','NGN','NOK','OMR','PKR','PAB','PYG','PEN','PHP','PLN','QAR','RON','RUB','SHP','SAR','RSD','SCR','SGD','SBD','SOS','ZAR','LKR','SEK','CHF','SRD','SYP','TWD','THB','TTD','TRY','TVD','UAH','GBP','USD','UYU','UZS','VEF','VND','YER','ZWD') NOT NULL,
  `createdDate` date NOT NULL,
  `walletType` enum('personal','shared') DEFAULT NULL,
  PRIMARY KEY (`walletId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wallet`
--

LOCK TABLES `wallet` WRITE;
/*!40000 ALTER TABLE `wallet` DISABLE KEYS */;
INSERT INTO `wallet` VALUES (1,'Ví cá nhân',10000000,'VND','2019-04-13','personal'),(2,'Ví dùng chung',100000000,'VND','2019-04-13','shared');
/*!40000 ALTER TABLE `wallet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'wallet_project'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-16  2:59:33
