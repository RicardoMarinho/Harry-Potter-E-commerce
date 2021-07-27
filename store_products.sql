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
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text,
  `category` varchar(255) NOT NULL,
  `subcategory` varchar(255) NOT NULL,
  `stock` bigint NOT NULL,
  `price` double NOT NULL,
  `imgUrl` varchar(255) DEFAULT 'app/img/product-img/default.png',
  `isPopular` bit(1) DEFAULT b'0',
  `register` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Long Black Robe - Slytherin','Become the ultimate Slytherin student with this beautiful long black Slytherin Robe, complete with coloured lining and an embroidered house crest patch.','clothing','costumes',10,72.64,'app/img/product-img/product-3.png,app/img/product-img/product-3-back.png',_binary '','2020-05-20 14:01:38'),(2,'Long Black Robe - Gryffindor','Become the ultimate Gryffindor student with this beautiful long, black Gryffindor Robe, complete with coloured lining and an embroidered house crest patch.','clothing','costumes',5,72.64,'app/img/product-img/product-4.png,app/img/product-img/product-4-back.png',_binary '','2020-05-20 14:02:18'),(3,'Long Black Robe - Hufflepuff','Become the ultimate Hufflepuff student with this beautiful long black Hufflepuff Robe, complete with coloured lining and an embroidered house crest patch.','clothing','costumes',3,72.64,'app/img/product-img/product-1.png,app/img/product-img/product-1-back.png',_binary '','2020-05-20 14:02:23'),(5,'Long Black Robe - Ravenclaw','Become the ultimate Ravenclaw student with this beautiful long black Ravenclaw Robe, complete with coloured lining and an embroidered house crest patch.','clothing','costumes',0,72.64,'app/img/product-img/product-2.png,app/img/product-img/product-2-back.png',_binary '','2020-05-20 14:03:29'),(7,'Lord Voldemort Wand Pen and Bookmark','The pen is crafted to look like a miniature version of Voldemort\'s wand and measures 7 1/4 inches long. The bookmark features an image of Voldemort from the films and measures 6 3/4 x 2 1/4 inches.','souvenirs','bookmarks',10,13.93,'app/img/product-img/product-15.png,app/img/product-img/product-15-back.png',_binary '\0','2020-05-21 22:48:55'),(8,'Severus Snape Wand Pen and Bookmark','The pen is crafted to look like a miniature version of Snape\'s wand and measures 7 1/4 inches long. The bookmark features an image of Snape from the films and measures 6 3/4 x 2 1/4 inches.','souvenirs','bookmarks',3,13.93,'app/img/product-img/product-14.png,app/img/product-img/product-14-back.png',_binary '\0','2020-05-21 22:48:55'),(9,'New Edition Box Set Harry Potter Paperback Collection','A new edition of the Harry Potter books by J.K Rowling featuring brand new illustrated covers, presented in a beautiful display box. The collection contains all seven Harry Potter books in paperback.','departments','books',10,66.85,'app/img/product-img/product-13.png,app/img/product-img/product-13-back.png',_binary '\0','2020-05-21 22:48:55'),(10,'New Edition Harry Potter and The Deathly Hallows (Paperback)','When Dumbledore arrives at Privet Drive one summer night to collect Harry Potter, his wand hand is blackened and shrivelled, but he does not reveal why. Secrets and suspicion are spreading through the wizarding world, and Hogwarts itself is not safe. Harry is convinced that Malfoy bears the Dark Mark: there is a Death Eater amongst them. Harry will need powerful magic and true friends as he explores Voldemort\'s darkest secrets, and Dumbledore prepares him to face his destiny.','departments','books',3,10.02,'app/img/product-img/product-12.png,app/img/product-img/product-12-back.png',_binary '\0','2020-05-21 22:48:55'),(11,'New Edition Harry Potter and Philosopher\'s Stone (Paperback)','Harry Potter has never even heard of Hogwarts when the letters start dropping on the doormat at number four, Privet Drive. Addressed in green ink on yellowish parchment with a purple seal, they are swiftly confiscated by his grisly aunt and uncle. Then, on Harry\'s eleventh birthday, a great beetle-eyed giant of a man called Rubeus Hagrid bursts in with some astonishing news: Harry Potter is a wizard, and he has a place at Hogwarts School of Witchcraft and Wizardry. An incredible adventure is about to begin!','departments','books',1,8.9,'app/img/product-img/product-11.png,app/img/product-img/product-11-back.png',_binary '\0','2020-05-21 22:48:55'),(12,'Hufflepuff Quidditch Jumper','Cheer on your favourite Hogwarts house Quidditch team with this Hufflepuff Quidditch jumper. Featuring an embroidered Hufflepuff house crest patch on the left chest, and reproduced in the yellow and black colours of the house, this Quidditch sweater is a faithful celebration of the ones seen in the Harry Potter film series. ','clothing','knitwear',7,50.15,'app/img/product-img/product-6.png,app/img/product-img/product-6-back.png',_binary '\0','2020-05-21 22:48:55'),(13,'Gryffindor Quidditch Jumper','Cheer on your favourite Hogwarts house Quidditch team with this Gryffindor Quidditch jumper. Featuring an embroidered Gryffindor house crest patch on the left chest, and reproduced in the scarlet and gold colours of the house, this Quidditch sweater is a faithful celebration of the ones seen in the Harry Potter film series. ','clothing','knitwear',10,50.15,'app/img/product-img/product-7.png,app/img/product-img/product-7-back.png',_binary '\0','2020-05-21 22:48:55'),(14,'Ravenclaw Quidditch Jumper','Cheer on your favourite Hogwarts house Quidditch team with this Ravenclaw Quidditch jumper. Featuring an embroidered Ravenclaw house crest patch on the left chest, and reproduced in the blue and silver colours of the house, this Quidditch sweater is a faithful celebration of the ones seen in the Harry Potter film series. ','clothing','knitwear',5,50.15,'app/img/product-img/product-9.png,app/img/product-img/product-9-back.png',_binary '\0','2020-05-21 22:48:55');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-25 22:36:50
