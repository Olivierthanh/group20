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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
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
  `date` date DEFAULT NULL,
  `note` text NOT NULL,
  `type` enum('income','expense') NOT NULL,
  `walletId` int(11) NOT NULL,
  `categoryId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`transactionId`),
  KEY `fk_expense_wallet1_idx` (`walletId`),
  KEY `fk_expense_category1_idx` (`categoryId`),
  KEY `fk_transaction_user1_idx` (`userId`),
  CONSTRAINT `fk_expense_category1` FOREIGN KEY (`categoryId`) REFERENCES `category` (`categoryid`),
  CONSTRAINT `fk_expense_wallet1` FOREIGN KEY (`walletId`) REFERENCES `wallet` (`walletid`),
  CONSTRAINT `fk_transaction_user1` FOREIGN KEY (`userId`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (3,200000,'2019-04-22','Travel expense','expense',1,22,1),(4,500000,'2019-04-10','Some thing Love','expense',1,14,1),(5,300000,'2019-04-15','Interest Money','income',1,3,1),(12,500000,'2019-04-23','Some thing','expense',1,11,1),(15,200000,'2019-04-21','some awards','income',1,1,1),(16,1000000,'2019-04-21','đi mua sắm','expense',1,20,1),(17,1000000,'2019-04-20','mua giày','expense',1,33,1),(18,1000000,'2019-04-19','test','income',1,3,1),(19,500000,'2019-04-19','Rút tiền','expense',1,23,1),(20,500000,'2019-04-18','Rút tiền','expense',1,23,1),(21,1000000,'2019-04-17','luwong','income',1,5,1),(22,600000,'2019-04-16','Mua game','expense',1,35,1),(23,1500000,'2019-04-14','restaurant','expense',1,50,1),(24,10000000,'2019-04-13','Thưởng','income',1,1,1),(25,100000,'2019-04-12','Đổ xăng','expense',1,45,1),(55,500000,'2019-04-26','Khám sức khỏe','expense',24,16,1),(58,100000,'2019-04-25','Chơi','expense',1,10,1),(60,100000,'2019-04-26','bán nhà','income',24,6,1),(61,2000000,'2019-04-20','nhận lương','income',1,5,1),(62,2000000,'2019-04-27','some award','income',1,1,1),(63,1000000,'2019-04-26','tiền học','expense',1,9,1),(64,1000000,'2019-04-27','mua quan ao','expense',1,29,1),(65,5000000,'2019-04-28','Mua dien thoai','expense',1,35,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'thanhlt998@gmail.com','$2a$10$k6dR7692BEvrtpeygt2t2e8PjBfaBGezW.65BM6X66ZrtFMxcO18S','Lê Tuấn Thành','male','1998-01-01','Hưng Yên'),(2,'test@gmail.com','$2a$10$WjatkZfOKejvj93WAXWQH.JmW3croR/HOiufBbUEQlKaSdzJQHytW','Test','female','1998-01-01','Hưng Yên');
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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_wallet`
--

LOCK TABLES `user_wallet` WRITE;
/*!40000 ALTER TABLE `user_wallet` DISABLE KEYS */;
INSERT INTO `user_wallet` VALUES (1,1,1),(2,2,1),(30,1,2),(37,24,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wallet`
--

LOCK TABLES `wallet` WRITE;
/*!40000 ALTER TABLE `wallet` DISABLE KEYS */;
INSERT INTO `wallet` VALUES (1,'Ví cá nhân',3000000,'VND','2019-04-13','shared'),(2,'Ví dùng chung',0,'VND','2019-04-13','shared'),(24,'Test1',-400000,'VND','2019-04-26','personal');
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

-- Dump completed on 2019-04-29 16:17:18
