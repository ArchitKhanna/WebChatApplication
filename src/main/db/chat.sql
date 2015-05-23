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
  `user_id` varchar(20) NOT NULL,
  `text` varchar(1000) NOT NULL,
  `send_date` datetime NOT NULL,
  `modify_date` datetime DEFAULT NULL,
  `deleted` tinyint(1) NOT NULL DEFAULT '0',
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
INSERT INTO `messages` VALUES ('1164146276','9','deleted','2015-05-02 00:00:00',NULL,1),('1351579886','9','deleted','2015-05-23 20:26:30',NULL,1),('190983840','1','deleted','2015-05-16 23:41:43',NULL,1),('2352868120','9','deleted','2015-05-23 20:18:07',NULL,1),('3380920996','1','deleted','2015-05-23 11:55:19',NULL,1),('351555896','1','deleted','2015-05-23 11:55:05',NULL,1),('394596964','9','deleted','2015-05-23 20:26:47',NULL,1),('4102900304','9','Как дела, я знаю русский!!!','2015-05-23 14:39:17',NULL,0),('4217285636','9','deleted','2015-05-23 14:14:46',NULL,1),('5029971744','9','I love Bayern!!','2015-05-23 20:11:20','2015-05-23 20:16:26',0),('5191929950','1','Все отлично, я рад за тебя!','2015-05-23 14:39:27','2015-05-23 14:39:31',0),('5638966245','9','deleted','2015-05-23 20:18:06',NULL,1),('574612656','9','deleted','2015-05-23 14:39:09',NULL,1),('7445213255','3','deleted','2015-05-23 20:07:59',NULL,1),('7553710182','9','deleted','2015-05-23 20:07:40',NULL,1),('7609576367','9','Hello!!!!!!!!!!!!!','2015-05-23 20:26:27','2015-05-23 20:27:05',0),('7700117040','3','very  good!!!!','2015-05-23 20:11:02','2015-05-23 20:26:39',0),('836457294','9','sdggsdggsd','2015-05-23 20:26:45',NULL,0),('9004494502','1','It is Gennady!!!','2015-05-23 14:39:02',NULL,0),('9395368548','9','fdjkghsjkhg','2015-05-23 20:18:05',NULL,0),('9726272484','1','All is okay! And you?','2015-05-23 14:14:57','2015-05-23 14:15:04',0);
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

-- Dump completed on 2015-05-23 23:27:56
