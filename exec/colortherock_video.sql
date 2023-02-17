-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: colortherock.ciwlyvooegg7.ap-northeast-2.rds.amazonaws.com    Database: colortherock
-- ------------------------------------------------------
-- Server version	8.0.28

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
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '';

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `gym_name` varchar(30) DEFAULT NULL,
  `is_posted` bit(1) DEFAULT b'0',
  `is_success` bit(1) DEFAULT NULL,
  `level` int DEFAULT NULL,
  `s_3_url` varchar(200) DEFAULT NULL,
  `shooting_date` date DEFAULT NULL,
  `thumbnail_name` varchar(200) DEFAULT NULL,
  `thumbnail_url` varchar(200) DEFAULT NULL,
  `video_name` varchar(200) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt1qraed5ypqn1u5s3jiq0xu1i` (`member_id`),
  CONSTRAINT `FKt1qraed5ypqn1u5s3jiq0xu1i` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (1,'2023-02-16 08:30:03.351330','빨강','더클라임 강남점',_binary '\0',_binary '',1,'https://dhw80hz67vj6n.cloudfront.net/1676536200280더현수.mp4','2023-02-01','Thumb1676536201138더현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676536201138더현수.JPEG','1676536200280더현수.mp4',1),(2,'2023-02-16 08:31:18.046996','노랑','더클라임 강남점',_binary '\0',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676536275077더현수.mp4','2023-02-15','Thumb1676536276325더현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676536276325더현수.JPEG','1676536275077더현수.mp4',1),(4,'2023-02-16 08:35:22.266479','빨강','더클라임 강남점',_binary '\0',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676536520775현수.mov','2023-01-31','Thumb1676536520977현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676536520977현수.JPEG','1676536520775현수.mov',6),(5,'2023-02-16 08:39:33.031196','흰색','더클라임 강남점',_binary '\0',_binary '',1,'https://dhw80hz67vj6n.cloudfront.net/1676536772498현수.mov','2023-02-01','Thumb1676536772581현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676536772581현수.JPEG','1676536772498현수.mov',6),(6,'2023-02-16 08:39:53.699441','빨강','더클라임 강남점',_binary '\0',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676536793177현수.mov','2023-02-01','Thumb1676536793329현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676536793329현수.JPEG','1676536793177현수.mov',6),(7,'2023-02-16 08:40:45.513650','흰색','더클라임 강남점',_binary '\0',_binary '',3,'https://dhw80hz67vj6n.cloudfront.net/1676536845023현수.mov','2023-02-01','Thumb1676536845159현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676536845159현수.JPEG','1676536845023현수.mov',6),(8,'2023-02-16 08:41:21.329343','빨강','피커스 종로점',_binary '\0',_binary '',3,'https://dhw80hz67vj6n.cloudfront.net/1676536880929현수.mov','2023-02-02','Thumb1676536881024현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676536881024현수.JPEG','1676536880929현수.mov',6),(9,'2023-02-16 08:41:50.862024','빨강','피커스 종로점',_binary '\0',_binary '',4,'https://dhw80hz67vj6n.cloudfront.net/1676536910480현수.mov','2023-02-02','Thumb1676536910586현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676536910586현수.JPEG','1676536910480현수.mov',6),(11,'2023-02-16 08:43:01.396913','빨강','더클라임 양재점',_binary '\0',_binary '\0',4,'https://dhw80hz67vj6n.cloudfront.net/1676536980977현수.mov','2023-02-04','Thumb1676536981122현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676536981122현수.JPEG','1676536980977현수.mov',6),(13,'2023-02-16 08:43:41.675509','빨강','더클라임 양재점',_binary '\0',_binary '\0',1,'https://dhw80hz67vj6n.cloudfront.net/1676537021252현수.mov','2023-02-04','Thumb1676537021356현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537021356현수.JPEG','1676537021252현수.mov',6),(14,'2023-02-16 08:43:52.306317','빨강','test',_binary '\0',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676537024294더현수.mp4','2023-02-13','Thumb1676537024933더현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537024933더현수.JPEG','1676537024294더현수.mp4',1),(15,'2023-02-16 08:44:15.394533','빨강','더클라임 양재점',_binary '\0',_binary '',4,'https://dhw80hz67vj6n.cloudfront.net/1676537054850현수.mov','2023-02-06','Thumb1676537054920현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537054920현수.JPEG','1676537054850현수.mov',6),(16,'2023-02-16 08:44:19.092365','빨강','멀티캠퍼스 역삼',_binary '\0',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676537054318더현수.mp4','2023-02-13','Thumb1676537054444더현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537054444더현수.JPEG','1676537054318더현수.mp4',1),(17,'2023-02-16 08:44:42.772782','흰색','더클라임 양재점',_binary '\0',_binary '\0',4,'https://dhw80hz67vj6n.cloudfront.net/1676537082346현수.mov','2023-02-06','Thumb1676537082428현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537082428현수.JPEG','1676537082346현수.mov',6),(18,'2023-02-16 08:45:15.616199','주황','더클라임 양재점',_binary '\0',_binary '\0',4,'https://dhw80hz67vj6n.cloudfront.net/1676537115264현수.mov','2023-02-06','Thumb1676537115354현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537115354현수.JPEG','1676537115264현수.mov',6),(19,'2023-02-16 08:45:51.095891','주황','더클라임 강남점',_binary '\0',_binary '',5,'https://dhw80hz67vj6n.cloudfront.net/1676537150671현수.mov','2023-02-09','Thumb1676537150807현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537150807현수.JPEG','1676537150671현수.mov',6),(20,'2023-02-16 08:45:52.342824','빨강','더클라임 강남점',_binary '\0',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676537150550더현수.mp4','2023-02-14','Thumb1676537150674더현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537150674더현수.JPEG','1676537150550더현수.mp4',1),(21,'2023-02-16 08:46:18.266101','흰색','더클라임 강남점',_binary '\0',_binary '\0',5,'https://dhw80hz67vj6n.cloudfront.net/1676537177887현수.mov','2023-02-09','Thumb1676537178022현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537178022현수.JPEG','1676537177887현수.mov',6),(22,'2023-02-16 08:46:41.773388','흰색','더클라임 홍대점',_binary '\0',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676537199978더현수.mp4','2023-02-13','Thumb1676537200187더현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537200187더현수.JPEG','1676537199978더현수.mp4',1),(23,'2023-02-16 08:47:10.025808','빨강','피커스 종로점',_binary '\0',_binary '',6,'https://dhw80hz67vj6n.cloudfront.net/1676537229694현수.mov','2023-02-11','Thumb1676537229821현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537229821현수.JPEG','1676537229694현수.mov',6),(24,'2023-02-16 08:47:13.814654','빨강','이몸이다',_binary '\0',_binary '',3,'https://dhw80hz67vj6n.cloudfront.net/1676537232267더현수.mp4','2023-02-14','Thumb1676537232438더현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537232438더현수.JPEG','1676537232267더현수.mp4',1),(25,'2023-02-16 08:47:51.751574','흰색','피커스 종로점',_binary '\0',_binary '',4,'https://dhw80hz67vj6n.cloudfront.net/1676537271367현수.mov','2023-02-11','Thumb1676537271501현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537271501현수.JPEG','1676537271367현수.mov',6),(26,'2023-02-16 08:48:23.600055','흰색','더클라임 강남점',_binary '\0',_binary '',4,'https://dhw80hz67vj6n.cloudfront.net/1676537303362현수.mov','2023-02-12','Thumb1676537303440현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537303440현수.JPEG','1676537303362현수.mov',6),(27,'2023-02-16 08:49:07.358616','흰색','더클라임 강남점',_binary '\0',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676537346942현수.mov','2023-02-05','Thumb1676537347129현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537347129현수.JPEG','1676537346942현수.mov',6),(29,'2023-02-16 08:50:28.657252','흰색','더클라임 양재점',_binary '\0',_binary '',7,'https://dhw80hz67vj6n.cloudfront.net/1676537428377현수.mov','2023-02-06','Thumb1676537428492현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537428492현수.JPEG','1676537428377현수.mov',6),(30,'2023-02-16 08:50:28.911411','빨강','더클라임 강남점',_binary '\0',_binary '',3,'https://dhw80hz67vj6n.cloudfront.net/1676537427760더현수.mp4','2023-02-13','Thumb1676537427949더현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537427949더현수.JPEG','1676537427760더현수.mp4',1),(31,'2023-02-16 08:51:08.822972','흰색','더클라임 양재점',_binary '\0',_binary '',1,'https://dhw80hz67vj6n.cloudfront.net/1676537468423현수.mov','2023-02-05','Thumb1676537468583현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537468583현수.JPEG','1676537468423현수.mov',6),(32,'2023-02-16 08:51:47.479544','흰색','더클라임 양재점',_binary '\0',_binary '',5,'https://dhw80hz67vj6n.cloudfront.net/1676537507204현수.mov','2023-02-13','Thumb1676537507344현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537507344현수.JPEG','1676537507204현수.mov',6),(33,'2023-02-16 08:52:15.716084','흰색','더클라임 양재점',_binary '\0',_binary '',3,'https://dhw80hz67vj6n.cloudfront.net/1676537535428현수.mov','2023-02-12','Thumb1676537535526현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537535526현수.JPEG','1676537535428현수.mov',6),(34,'2023-02-16 08:52:57.559593','흰색','피커스 종로점',_binary '\0',_binary '',8,'https://dhw80hz67vj6n.cloudfront.net/1676537577198현수.mov','2023-02-13','Thumb1676537577313현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537577313현수.JPEG','1676537577198현수.mov',6),(36,'2023-02-16 08:55:41.370953','흰색','피커스 종로점',_binary '\0',_binary '',4,'https://dhw80hz67vj6n.cloudfront.net/1676537741125현수.mov','2023-02-15','Thumb1676537741214현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537741214현수.JPEG','1676537741125현수.mov',6),(37,'2023-02-16 08:58:07.194949','흰색','더클라임 강남점',_binary '\0',_binary '',5,'https://dhw80hz67vj6n.cloudfront.net/1676537886907현수.mov','2023-02-05','Thumb1676537887035현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676537887035현수.JPEG','1676537886907현수.mov',6),(38,'2023-02-16 18:01:33.841343','흰색','더클라임 양재점',_binary '\0',_binary '',4,'https://dhw80hz67vj6n.cloudfront.net/1676538093498현수.mov','2023-02-09','Thumb1676538093631현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676538093631현수.JPEG','1676538093498현수.mov',6),(39,'2023-02-16 18:08:51.146406','핑크','더클라임 강남점',_binary '\0',_binary '',3,'https://dhw80hz67vj6n.cloudfront.net/1676538528354현수.mov','2023-02-08','Thumb1676538528642현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676538528642현수.JPEG','1676538528354현수.mov',6),(41,'2023-02-16 14:05:06.262575','흰색','더클라임 강남점',_binary '\0',_binary '',1,'https://dhw80hz67vj6n.cloudfront.net/1676556295244123123.mov','2023-02-15','Thumb1676556298255123123.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676556298255123123.JPEG','1676556295244123123.mov',12),(43,'2023-02-16 14:07:22.795727','흰색','더클라임 강남점',_binary '',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676556441897123123.mp4','2023-02-15','Thumb1676556442289123123.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676556442289123123.JPEG','1676556441897123123.mp4',12),(44,'2023-02-16 14:08:34.832209','파랑','볼더프렌즈 클라이밍',_binary '',_binary '',4,'https://dhw80hz67vj6n.cloudfront.net/1676556513360더현수.mp4','2023-02-10','Thumb1676556514134더현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676556514134더현수.JPEG','1676556513360더현수.mp4',1),(46,'2023-02-16 14:13:21.106085','빨강','볼더프렌즈 클라이밍',_binary '',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676556798067더현수.mp4','2023-02-10','Thumb1676556799773더현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676556799773더현수.JPEG','1676556798067더현수.mp4',1),(49,'2023-02-16 14:19:29.537751','파랑','볼더프렌즈 클라이밍',_binary '',_binary '',5,'https://dhw80hz67vj6n.cloudfront.net/1676557163355더현수.mp4','2023-02-10','Thumb1676557166971더현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676557166971더현수.JPEG','1676557163355더현수.mp4',1),(51,'2023-02-16 14:22:25.721811','주황','더클라임 양재점',_binary '\0',_binary '',3,'https://dhw80hz67vj6n.cloudfront.net/1676557341509현수.mp4','2023-02-11','Thumb1676557342471현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676557342471현수.JPEG','1676557341509현수.mp4',6),(52,'2023-02-16 14:25:19.507651','빨강','더클라임 홍대점',_binary '\0',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676557514256123123.mov','2023-02-13','Thumb1676557515624123123.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676557515624123123.JPEG','1676557514256123123.mov',12),(55,'2023-02-16 14:49:43.354940','빨강','2월3일',_binary '\0',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676558978289123123.mov','2023-02-02','Thumb1676558979248123123.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676558979248123123.JPEG','1676558978289123123.mov',12),(56,'2023-02-16 14:49:48.360667','빨강','2월2일',_binary '\0',_binary '',1,'https://dhw80hz67vj6n.cloudfront.net/1676558984922123123.mov','2023-02-01','Thumb1676558985301123123.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676558985301123123.JPEG','1676558984922123123.mov',12),(58,'2023-02-16 14:55:18.841129','빨강','2월9일',_binary '\0',_binary '',3,'https://dhw80hz67vj6n.cloudfront.net/1676559313667123123.mov','2023-02-08','Thumb1676559314319123123.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676559314319123123.JPEG','1676559313667123123.mov',12),(59,'2023-02-16 14:55:56.670045','주황','더클라임 홍대점',_binary '',_binary '',3,'https://dhw80hz67vj6n.cloudfront.net/1676559354997더예지.mov','2023-02-02','Thumb1676559355256더예지.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676559355256더예지.JPEG','1676559354997더예지.mov',13),(61,'2023-02-16 15:04:20.910976','빨강','1월 20일',_binary '\0',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676559849745123123.mov','2023-01-19','Thumb1676559852526123123.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676559852526123123.JPEG','1676559849745123123.mov',12),(63,'2023-02-16 15:07:19.950417','초록','피커스 종로점',_binary '',_binary '',4,'https://dhw80hz67vj6n.cloudfront.net/1676560038121더예지.mov','2023-02-14','Thumb1676560038442더예지.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676560038442더예지.JPEG','1676560038121더예지.mov',13),(64,'2023-02-16 15:07:28.295671','빨강','2월11일',_binary '\0',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676560044897123123.mov','2023-02-10','Thumb1676560045237123123.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676560045237123123.JPEG','1676560044897123123.mov',12),(65,'2023-02-16 15:09:44.504845','주황','더클라임 양재점',_binary '',_binary '',3,'https://dhw80hz67vj6n.cloudfront.net/1676560182895더예지.mov','2023-02-08','Thumb1676560183203더예지.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676560183203더예지.JPEG','1676560182895더예지.mov',13),(66,'2023-02-16 15:11:22.170653','초록','볼더프렌즈 클라이밍',_binary '',_binary '',3,'https://dhw80hz67vj6n.cloudfront.net/1676560279822더현수.mp4','2023-02-10','Thumb1676560281364더현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676560281364더현수.JPEG','1676560279822더현수.mp4',1),(67,'2023-02-16 15:13:27.982799','초록','더클라임 마곡점',_binary '',_binary '',4,'https://dhw80hz67vj6n.cloudfront.net/1676560406214더예지.mov','2023-02-07','Thumb1676560406523더예지.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676560406523더예지.JPEG','1676560406214더예지.mov',13),(68,'2023-02-16 15:19:20.264284','빨강','2월4일',_binary '\0',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676560755114123123.mov','2023-02-04','Thumb1676560756103123123.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676560756103123123.JPEG','1676560755114123123.mov',12),(69,'2023-02-16 15:24:29.506608','초록','더클라임 강남점',_binary '',_binary '',4,'https://dhw80hz67vj6n.cloudfront.net/1676561067537현수.mov','2023-02-03','Thumb1676561067884현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676561067884현수.JPEG','1676561067537현수.mov',6),(70,'2023-02-16 15:25:59.752649','초록','서울숲클라이밍',_binary '',_binary '',1,'https://dhw80hz67vj6n.cloudfront.net/1676561151870으노니.mov','2022-01-07','Thumb1676561154310으노니.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676561154310으노니.JPEG','1676561151870으노니.mov',14),(71,'2023-02-16 15:27:15.703671','하늘','왕십리 훅 클라이밍',_binary '',_binary '',1,'https://dhw80hz67vj6n.cloudfront.net/1676561233462안암동매콤힐훅.mov','2022-12-24','Thumb1676561233887안암동매콤힐훅.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676561233887안암동매콤힐훅.JPEG','1676561233462안암동매콤힐훅.mov',15),(72,'2023-02-16 15:28:24.818088','초록','서울볼더스',_binary '',_binary '',1,'https://dhw80hz67vj6n.cloudfront.net/1676561302864으노니.mov','2021-11-27','Thumb1676561303551으노니.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676561303551으노니.JPEG','1676561302864으노니.mov',14),(73,'2023-02-16 15:31:53.890957','검정','더클라임 강남점',_binary '',_binary '',4,'https://dhw80hz67vj6n.cloudfront.net/1676561513376더현주.mp4','2023-02-15','Thumb1676561513641더현주.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676561513641더현주.JPEG','1676561513376더현주.mp4',5),(74,'2023-02-16 15:32:07.876225','파랑','웨어하우스짐 제기동',_binary '',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676561525998안암동매콤힐훅.mov','2022-09-15','Thumb1676561526486안암동매콤힐훅.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676561526486안암동매콤힐훅.JPEG','1676561525998안암동매콤힐훅.mov',15),(75,'2023-02-16 15:32:29.241112','핑크','클라이밍파크 종로점',_binary '',_binary '',3,'https://dhw80hz67vj6n.cloudfront.net/1676561542190더예지.mov','2023-02-09','Thumb1676561544298더예지.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676561544298더예지.JPEG','1676561542190더예지.mov',13),(76,'2023-02-16 15:33:18.092403','주황','더클라임 강남점',_binary '',_binary '',3,'https://dhw80hz67vj6n.cloudfront.net/1676561597242더현주.mp4','2023-02-13','Thumb1676561597589더현주.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676561597589더현주.JPEG','1676561597242더현주.mp4',5),(77,'2023-02-16 15:33:43.275059','검정','더클라임 강남점',_binary '',_binary '',4,'https://dhw80hz67vj6n.cloudfront.net/1676561614094더현수.mp4','2023-02-15','Thumb1676561615377더현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676561615377더현수.JPEG','1676561614094더현수.mp4',1),(78,'2023-02-16 15:33:58.570702','초록','서울볼더스',_binary '',_binary '',1,'https://dhw80hz67vj6n.cloudfront.net/1676561638286으노니.mov','2021-10-17','Thumb1676561638501으노니.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676561638501으노니.JPEG','1676561638286으노니.mov',16),(79,'2023-02-16 15:34:22.994158','흰색','부평클라이밍센터',_binary '\0',_binary '',1,'https://dhw80hz67vj6n.cloudfront.net/1676561661560ses_TcGL0qOcJx~1.mp4','2023-02-16','Thumb1676561661670ses_TcGL0qOcJx~1.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676561661670ses_TcGL0qOcJx~1.JPEG','1676561661560ses_TcGL0qOcJx~1.mp4',7),(80,'2023-02-16 15:36:36.712447','파랑','드림캐쳐 노원점',_binary '',_binary '',5,'https://dhw80hz67vj6n.cloudfront.net/1676561793817안암동매콤힐훅.mov','2022-12-28','Thumb1676561794261안암동매콤힐훅.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676561794261안암동매콤힐훅.JPEG','1676561793817안암동매콤힐훅.mov',15),(81,'2023-02-16 15:37:36.294119','하늘','서울숲클라이밍',_binary '',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676561854388으노니.mov','2022-01-17','Thumb1676561854762으노니.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676561854762으노니.JPEG','1676561854388으노니.mov',16),(82,'2023-02-16 15:39:42.863350','하늘','락트리클라이밍 강남점',_binary '',_binary '',4,'https://dhw80hz67vj6n.cloudfront.net/1676561979055안암동매콤힐훅.mov','2022-10-30','Thumb1676561979413안암동매콤힐훅.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676561979413안암동매콤힐훅.JPEG','1676561979055안암동매콤힐훅.mov',15),(83,'2023-02-16 15:39:57.586479','남색','일산더클라임',_binary '',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676561996107으노니.mov','2022-01-08','Thumb1676561996263으노니.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676561996263으노니.JPEG','1676561996107으노니.mov',16),(84,'2023-02-16 15:43:11.944577','초록','충격적 화질',_binary '\0',_binary '\0',3,'https://dhw80hz67vj6n.cloudfront.net/1676562190472ses_Se4Wc6KX9M.mp4','2023-02-16','Thumb1676562190591ses_Se4Wc6KX9M.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676562190591ses_Se4Wc6KX9M.JPEG','1676562190472ses_Se4Wc6KX9M.mp4',7),(90,'2023-02-16 16:12:44.603403','주황','아아',_binary '\0',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676563963196ses_IE5B8OlvZV.mp4','2023-02-16','Thumb1676563963370ses_IE5B8OlvZV.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676563963370ses_IE5B8OlvZV.JPEG','1676563963196ses_IE5B8OlvZV.mp4',12),(91,'2023-02-16 16:13:13.795641','핑크','아아',_binary '\0',_binary '',8,'https://dhw80hz67vj6n.cloudfront.net/1676563992262ses_IE5B8OlvZV~1.mp4','2023-02-16','Thumb1676563992434ses_IE5B8OlvZV~1.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676563992434ses_IE5B8OlvZV~1.JPEG','1676563992262ses_IE5B8OlvZV~1.mp4',12),(94,'2023-02-16 16:15:43.562115','흰색','예지하우스',_binary '\0',_binary '',1,'https://dhw80hz67vj6n.cloudfront.net/1676564143077더예지.mp4','2023-02-17','Thumb1676564143363더예지.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676564143363더예지.JPEG','1676564143077더예지.mp4',13),(95,'2023-02-16 16:16:46.046023','흰색','내방',_binary '\0',_binary '',1,'https://dhw80hz67vj6n.cloudfront.net/1676564204211ses_AjahsN6i90.mp4','2023-02-16','Thumb1676564204448ses_AjahsN6i90.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676564204448ses_AjahsN6i90.JPEG','1676564204211ses_AjahsN6i90.mp4',7),(96,'2023-02-16 16:17:18.685172','파랑','야호어린이집',_binary '\0',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676564237507ses_Wsr37VKggg.mp4','2023-02-16','Thumb1676564237580ses_Wsr37VKggg.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676564237580ses_Wsr37VKggg.JPEG','1676564237507ses_Wsr37VKggg.mp4',12),(97,'2023-02-16 16:18:26.460921','흰색','ㅋㅋ',_binary '\0',_binary '',1,'https://dhw80hz67vj6n.cloudfront.net/1676564304751ses_Z7ioRXJ0Y5.mp4','2023-02-16','Thumb1676564304938ses_Z7ioRXJ0Y5.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676564304938ses_Z7ioRXJ0Y5.JPEG','1676564304751ses_Z7ioRXJ0Y5.mp4',1),(103,'2023-02-16 16:22:46.263402','빨강','올레길 8코스(월평-대평 올레)',_binary '\0',_binary '',8,'https://dhw80hz67vj6n.cloudfront.net/1676564564999ses_ZNP0LsyxYu.mp4','2023-02-16','Thumb1676564565109ses_ZNP0LsyxYu.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676564565109ses_ZNP0LsyxYu.JPEG','1676564564999ses_ZNP0LsyxYu.mp4',12),(104,'2023-02-16 16:24:37.312605','빨강','현수집',_binary '\0',_binary '',1,'https://dhw80hz67vj6n.cloudfront.net/1676564675631ses_QFzZvXhQUl.mp4','2023-02-16','Thumb1676564675822ses_QFzZvXhQUl.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676564675822ses_QFzZvXhQUl.JPEG','1676564675631ses_QFzZvXhQUl.mp4',1),(105,'2023-02-16 16:25:56.379455','초록','도쿄',_binary '\0',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676564755025ses_PtoQiQ0rS0.mp4','2023-02-16','Thumb1676564755169ses_PtoQiQ0rS0.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676564755169ses_PtoQiQ0rS0.JPEG','1676564755025ses_PtoQiQ0rS0.mp4',12),(110,'2023-02-16 23:55:05.169855','주황','멀티캠퍼스 역삼',_binary '\0',_binary '',2,'https://dhw80hz67vj6n.cloudfront.net/1676591702373ses_DWsuftM98w.mp4','2023-02-16','Thumb1676591702803ses_DWsuftM98w.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676591702803ses_DWsuftM98w.JPEG','1676591702373ses_DWsuftM98w.mp4',5),(115,'2023-02-16 23:59:33.686499','파랑','망포 에픽클라임',_binary '',_binary '',5,'https://dhw80hz67vj6n.cloudfront.net/1676591971605LSJ.mov','2022-05-29','Thumb1676591971942LSJ.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676591971942LSJ.JPEG','1676591971605LSJ.mov',19),(118,'2023-02-17 00:07:37.156181','빨강','더클라이밍양재점',_binary '',_binary '',3,'https://dhw80hz67vj6n.cloudfront.net/1676592448991치토쯩.mov','2023-02-17','Thumb1676592451506치토쯩.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676592451506치토쯩.JPEG','1676592448991치토쯩.mov',20),(119,'2023-02-17 00:34:31.157507','주황','더클라임짐 연남점',_binary '',_binary '',3,'https://dhw80hz67vj6n.cloudfront.net/1676594069635진정한클친자.mov','2023-02-12','Thumb1676594069989진정한클친자.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676594069989진정한클친자.JPEG','1676594069635진정한클친자.mov',21),(120,'2023-02-17 00:34:54.045360','연두','볼더메이트',_binary '',_binary '',4,'https://dhw80hz67vj6n.cloudfront.net/1676594092216LSJ.mov','2022-07-10','Thumb1676594092535LSJ.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676594092535LSJ.JPEG','1676594092216LSJ.mov',19),(121,'2023-02-17 00:35:41.670960','파랑','더클라임 강남점',_binary '',_binary '',6,'https://dhw80hz67vj6n.cloudfront.net/1676594137864진정한클친자.mov','2023-02-15','Thumb1676594139458진정한클친자.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676594139458진정한클친자.JPEG','1676594137864진정한클친자.mov',21),(122,'2023-02-17 00:36:53.758218','핑크','더클라임 홍대점',_binary '',_binary '',6,'https://dhw80hz67vj6n.cloudfront.net/1676594208649진정한클친자.mov','2023-02-11','Thumb1676594211385진정한클친자.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676594211385진정한클친자.JPEG','1676594208649진정한클친자.mov',21),(123,'2023-02-17 00:38:32.583244','하늘','더클라임 홍대점',_binary '',_binary '',4,'https://dhw80hz67vj6n.cloudfront.net/1676594297866진정한클친자.mov','2023-02-07','Thumb1676594302620진정한클친자.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676594302620진정한클친자.JPEG','1676594297866진정한클친자.mov',21),(125,'2023-02-17 00:42:59.879353','흰색','피커스 종로점',_binary '\0',_binary '',6,'https://dhw80hz67vj6n.cloudfront.net/1676594579451현수.mov','2023-02-03','Thumb1676594579596현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676594579596현수.JPEG','1676594579451현수.mov',6),(132,'2023-02-17 01:40:51.027309','파랑','더클라임 강남',_binary '\0',_binary '',6,'https://dhw80hz67vj6n.cloudfront.net/1676598049198현수.mov','2023-02-14','Thumb1676598049349현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676598049349현수.JPEG','1676598049198현수.mov',6),(133,'2023-02-17 01:41:57.554600','초록','더클라임 강남점',_binary '\0',_binary '',4,'https://dhw80hz67vj6n.cloudfront.net/1676598111711현수.mov','2023-02-14','Thumb1676598112030현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676598112030현수.JPEG','1676598111711현수.mov',6),(134,'2023-02-17 01:43:30.794524','주황','더클라임 강남점',_binary '\0',_binary '\0',2,'https://dhw80hz67vj6n.cloudfront.net/1676598205270현수.mov','2023-02-14','Thumb1676598205521현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676598205521현수.JPEG','1676598205270현수.mov',6),(135,'2023-02-17 01:45:05.232105','빨강','더클라임 강남점',_binary '\0',_binary '\0',3,'https://dhw80hz67vj6n.cloudfront.net/1676598303054현수.mov','2023-02-14','Thumb1676598303604현수.JPEG','https://dhw80hz67vj6n.cloudfront.net/Thumb1676598303604현수.JPEG','1676598303054현수.mov',6);
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-17 10:56:25
