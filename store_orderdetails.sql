CREATE DATABASE  IF NOT EXISTS `store` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `store`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: store
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `orderdetails`
--

DROP TABLE IF EXISTS `orderdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderdetails` (
  `orderId` bigint NOT NULL,
  `productId` bigint NOT NULL,
  `productname` varchar(255) NOT NULL,
  `qty` bigint NOT NULL DEFAULT '1',
  `productprice` double NOT NULL,
  `register` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`orderId`,`productId`),
  KEY `FK_order_id` (`orderId`),
  KEY `FK_product_id` (`productId`),
  CONSTRAINT `fk_order_id` FOREIGN KEY (`orderId`) REFERENCES `orders` (`id`),
  CONSTRAINT `fk_product_id` FOREIGN KEY (`productId`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderdetails`
--

LOCK TABLES `orderdetails` WRITE;
/*!40000 ALTER TABLE `orderdetails` DISABLE KEYS */;
INSERT INTO `orderdetails` VALUES (3,1,'Long Black Robe - Slytherin',1,72.64,'2020-06-13 20:38:33'),(3,7,'Lord Voldemort Wand Pen and Bookmark',1,13.93,'2020-06-13 20:38:43'),(3,8,'Severus Snape Wand Pen and Bookmark',1,13.93,'2020-06-13 20:38:40'),(3,9,'New Edition Box Set Harry Potter Paperback Collection',1,66.85,'2020-06-13 20:38:50'),(3,10,'New Edition Harry Potter and The Deathly Hallows (Paperback)',1,10.02,'2020-06-13 20:38:46'),(4,1,'Long Black Robe - Slytherin',1,72.64,'2020-06-13 21:42:14'),(4,7,'Lord Voldemort Wand Pen and Bookmark',1,13.93,'2020-06-13 21:42:14'),(4,8,'Severus Snape Wand Pen and Bookmark',1,13.93,'2020-06-13 21:42:14'),(4,9,'New Edition Box Set Harry Potter Paperback Collection',1,66.85,'2020-06-13 21:42:14'),(4,10,'New Edition Harry Potter and The Deathly Hallows (Paperback)',1,10.02,'2020-06-13 21:42:14'),(7,7,'Lord Voldemort Wand Pen and Bookmark',1,13.93,'2020-06-19 21:32:44'),(7,9,'New Edition Box Set Harry Potter Paperback Collection',1,66.85,'2020-06-19 21:32:44'),(7,10,'New Edition Harry Potter and The Deathly Hallows (Paperback)',1,10.02,'2020-06-19 21:32:44');
/*!40000 ALTER TABLE `orderdetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-25 22:36:51
