-- MySQL dump 10.13  Distrib 5.7.7-rc, for Win64 (x86_64)
--
-- Host: localhost    Database: chat
-- ------------------------------------------------------
-- Server version	5.7.7-rc-log

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
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `messages` (
  `id` varchar(20) NOT NULL,
  `text` varchar(1000) NOT NULL,
  `date` datetime NOT NULL,
  `user_id` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `messages_ibfk_1` (`user_id`),
  CONSTRAINT `messages_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES ('1164146276','Bayern is the champion!','2015-05-02 00:00:00','9'),('190983840','Hello!','2015-05-16 23:41:43','1'),('3467369505','Fine too, cockatoo!','2015-05-16 23:46:43','5'),('4026089152','Neuer is the best keeper!','2015-05-16 23:44:43','9'),('4434381272','Dinamo won Granit 1-0!','2015-05-02 23:42:31','1'),('4839477818','Привет. Я тестер.','2015-05-02 17:36:50','3'),('5227858862','hello! how are you?','2015-05-16 23:45:28','5'),('5492609566','I am fine and you?','2015-05-16 23:45:38','4'),('6389072510','How are you?','2015-05-16 23:42:11','1'),('6461776988','I am user 1!','2015-05-15 08:00:45','6'),('6640571152','Hell knows, any suggestions','2015-05-16 23:45:57','4'),('7170559721','I have done algoritms on the weekend=)','2015-05-16 23:41:53','11'),('7668835110','Тест русского языка.','2015-05-02 23:47:12','3'),('8251411490','I want summer as faster as it possible.','2015-05-02 23:44:00','1'),('9925124706','What are you going tonight?','2015-05-16 23:45:51','5');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('1','Gennady'),('10','Vadim'),('11','Ann'),('2','Adam'),('3','Tester'),('4','Turtle Beach'),('5','Shyshpanchik'),('6','User 1'),('7','Mario'),('8','Mary'),('9','Manuel');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-17  0:53:52
