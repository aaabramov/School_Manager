CREATE DATABASE  IF NOT EXISTS `schooldb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `schooldb`;
-- MySQL dump 10.13  Distrib 5.6.24, for linux-glibc2.5 (x86_64)
--
-- Host: 127.0.0.1    Database: schooldb
-- ------------------------------------------------------
-- Server version	5.5.44-0ubuntu0.14.04.1

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
-- Table structure for table `families`
--

DROP TABLE IF EXISTS `families`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `families` (
  `id_parent` int(11) NOT NULL,
  `id_student` int(11) NOT NULL,
  KEY `fk_parent_idx` (`id_parent`),
  KEY `fk_student_idx` (`id_student`),
  CONSTRAINT `fk_parent` FOREIGN KEY (`id_parent`) REFERENCES `parents` (`id_parent`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_student` FOREIGN KEY (`id_student`) REFERENCES `students` (`id_student`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `families`
--

LOCK TABLES `families` WRITE;
/*!40000 ALTER TABLE `families` DISABLE KEYS */;
/*!40000 ALTER TABLE `families` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `groups` (
  `id_group` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `id_curator` int(11) NOT NULL,
  `notes` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`id_group`),
  KEY `fk_curator_idx` (`id_curator`),
  CONSTRAINT `fk_curator` FOREIGN KEY (`id_curator`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journal`
--

DROP TABLE IF EXISTS `journal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journal` (
  `id_lesson` int(11) NOT NULL,
  `date` varchar(45) NOT NULL,
  `id_student` int(11) NOT NULL,
  `presence` int(11) NOT NULL,
  `mark` int(11) NOT NULL,
  `mark_type` int(11) NOT NULL,
  KEY `fk_id_lesson_idx` (`id_lesson`),
  KEY `fk_id_student_idx` (`id_student`),
  CONSTRAINT `fk_id_lesson` FOREIGN KEY (`id_lesson`) REFERENCES `schedule` (`id_lesson`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_student` FOREIGN KEY (`id_student`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journal`
--

LOCK TABLES `journal` WRITE;
/*!40000 ALTER TABLE `journal` DISABLE KEYS */;
/*!40000 ALTER TABLE `journal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parents`
--

DROP TABLE IF EXISTS `parents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parents` (
  `id_parent` int(11) NOT NULL,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `patronymic` varchar(45) DEFAULT NULL,
  `address` varchar(45) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `job` varchar(2048) DEFAULT NULL,
  `notes` varchar(2048) DEFAULT NULL,
  KEY `fk_parents_1_idx` (`id_parent`),
  CONSTRAINT `fk_parents_1` FOREIGN KEY (`id_parent`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parents`
--

LOCK TABLES `parents` WRITE;
/*!40000 ALTER TABLE `parents` DISABLE KEYS */;
/*!40000 ALTER TABLE `parents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `id_lesson` int(11) NOT NULL AUTO_INCREMENT,
  `day` int(11) NOT NULL,
  `order` int(11) NOT NULL,
  `id_group` int(11) NOT NULL,
  `id_subject` int(11) NOT NULL,
  `id_teacher` int(11) NOT NULL,
  PRIMARY KEY (`id_lesson`),
  KEY `fk_id_group_idx` (`id_group`),
  KEY `fk_id_subject_idx` (`id_subject`),
  KEY `fk_id_teacher_idx` (`id_teacher`),
  CONSTRAINT `fk_id_group` FOREIGN KEY (`id_group`) REFERENCES `groups` (`id_group`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_subject` FOREIGN KEY (`id_subject`) REFERENCES `subjects` (`id_subject`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_teacher` FOREIGN KEY (`id_teacher`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `id_student` int(11) NOT NULL,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `patronymic` varchar(45) DEFAULT NULL,
  `id_group` int(11) NOT NULL,
  `bday` varchar(32) NOT NULL,
  `address` varchar(45) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `notes` varchar(2048) DEFAULT NULL,
  KEY `id_user_idx` (`id_student`),
  CONSTRAINT `id_user` FOREIGN KEY (`id_student`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,'Abramov','Andrew','Volodymyrovich',1,'08.12.96','Kn. Zaton Street','+380978847064','I will be happy if it works.');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjects`
--

DROP TABLE IF EXISTS `subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subjects` (
  `id_subject` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `description` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id_subject`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjects`
--

LOCK TABLES `subjects` WRITE;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teachers` (
  `id_teachers` int(11) NOT NULL,
  `fname` varchar(45) NOT NULL,
  `lname` varchar(45) NOT NULL,
  `patronymic` varchar(45) DEFAULT NULL,
  `subjects` varchar(128) DEFAULT NULL,
  `bday` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `notes` varchar(2048) DEFAULT NULL,
  KEY `fk_teachers_1_idx` (`id_teachers`),
  CONSTRAINT `fk_teachers_1` FOREIGN KEY (`id_teachers`) REFERENCES `users` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachers`
--

LOCK TABLES `teachers` WRITE;
/*!40000 ALTER TABLE `teachers` DISABLE KEYS */;
/*!40000 ALTER TABLE `teachers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(60) NOT NULL,
  `password` varchar(32) NOT NULL,
  `acc_type` int(11) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'10002','5N1VhUGw',0);
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

-- Dump completed on 2015-09-27  0:45:10
