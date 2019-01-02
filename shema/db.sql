-- MySQL dump 10.13  Distrib 5.7.24, for Win32 (AMD64)
--
-- Host: localhost    Database: todo
-- ------------------------------------------------------
-- Server version	5.7.24

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
-- Current Database: `todo`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `todo` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `todo`;

--
-- Table structure for table `tasks`
--

DROP TABLE IF EXISTS `tasks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tasks` (
  `taskid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `datecreated` datetime NOT NULL,
  `description` varchar(45) NOT NULL,
  `task` varchar(45) NOT NULL,
  PRIMARY KEY (`taskid`),
  KEY `_idx` (`userid`),
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `users` (`userid`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasks`
--

LOCK TABLES `tasks` WRITE;
/*!40000 ALTER TABLE `tasks` DISABLE KEYS */;
INSERT INTO `tasks` VALUES (8,1,'2018-12-30 23:20:59','some text there','task by test user with id 7'),(9,1,'2018-12-30 23:21:58','sdfgdfgdfgfdg','dfdfsdfsdf'),(10,1,'2018-12-30 23:24:03','dfgdfgdfgfdg','gdfgdfgdfg'),(13,1,'2018-12-30 23:37:22','list of different items','Buy food'),(20,5,'2019-01-01 19:03:25','my first task','Bart task'),(67,1,'2019-01-01 22:02:04','rrrr','rrrr'),(78,11,'2019-01-01 23:42:21','old','new'),(99,7,'2019-01-02 19:00:16','tette','test****'),(100,7,'2019-01-02 01:14:21','ddd','dddd'),(101,7,'2019-01-02 01:17:30','testset','tests'),(102,7,'2019-01-02 15:56:26','eeee','eeee'),(103,7,'2019-01-02 15:57:38','777','877'),(104,7,'2019-01-02 16:48:03','dfgdfgdfg','dfgdfgdfg'),(105,7,'2019-01-02 16:48:29','dfgdfg','dgfdfg'),(106,7,'2019-01-02 16:59:46','test','test'),(114,7,'2019-01-02 17:34:26','sdf','sdf'),(116,7,'2019-01-02 19:00:24','new','new'),(117,7,'2019-01-02 19:00:43','view','from another');
/*!40000 ALTER TABLE `tasks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `location` varchar(45) NOT NULL,
  `gender` varchar(10) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `userid_UNIQUE` (`userid`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Homer','Simpson','hsimpson','qwerty','Springfield, CHCH','Male'),(2,'Lisa','Simpson','lsimpson','test','Springfield, MA','Female'),(5,'Bart','Simpson','bsimpson','test','Springfield, MA','Male'),(7,'testName','testLastname','test','test','test location','Male'),(8,'John','Simple','jsimple','test','test zone','Male'),(9,'1','2','3','4','test','Male'),(10,'5','5','5','5','5','Female'),(11,'6','6','6','6','6','Male');
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

-- Dump completed on 2019-01-02 19:06:04
