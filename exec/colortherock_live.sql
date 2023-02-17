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
-- Table structure for table `live`
--

DROP TABLE IF EXISTS `live`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `live` (
  `id` bigint NOT NULL,
  `gym_name` varchar(50) DEFAULT NULL,
  `is_live` bit(1) DEFAULT NULL,
  `is_public` bit(1) DEFAULT NULL,
  `session_id` varchar(100) DEFAULT NULL,
  `thumbnail_name` varchar(100) DEFAULT NULL,
  `thumbnail_url` varchar(100) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl6he3gaf8twhlabx224oqfnp5` (`member_id`),
  CONSTRAINT `FKl6he3gaf8twhlabx224oqfnp5` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `live`
--

LOCK TABLES `live` WRITE;
/*!40000 ALTER TABLE `live` DISABLE KEYS */;
INSERT INTO `live` VALUES (894,'이디야커피 선릉역점',_binary '',_binary '','ses_D4b1PuV4xm','1676552457850ses_D4b1PuV4xm','https://dhw80hz67vj6n.cloudfront.net/1676552457850ses_D4b1PuV4xm','실험일지 138',11),(916,'예지하우스',_binary '',_binary '','ses_Swiqiqby9E','1676564912208ses_Swiqiqby9E','https://dhw80hz67vj6n.cloudfront.net/1676564912208ses_Swiqiqby9E','Test',13),(917,'예지하우스',_binary '',_binary '','ses_UVN33uQfK6','1676565006492ses_UVN33uQfK6','https://dhw80hz67vj6n.cloudfront.net/1676565006492ses_UVN33uQfK6','Test',6),(918,'예지하우스',_binary '',_binary '','ses_CZgOLBPLh3','1676565022200ses_CZgOLBPLh3','https://dhw80hz67vj6n.cloudfront.net/1676565022200ses_CZgOLBPLh3','뭐야',6),(919,'와이낫',_binary '',_binary '','ses_Yxy04CcVXB','1676565039617ses_Yxy04CcVXB','https://dhw80hz67vj6n.cloudfront.net/1676565039617ses_Yxy04CcVXB','test',6),(920,'더클라임짐 연남점',_binary '',_binary '','ses_N3lfpg2Vqv','1676565149929ses_N3lfpg2Vqv','https://dhw80hz67vj6n.cloudfront.net/1676565149929ses_N3lfpg2Vqv','뭐냐고오오',13),(921,'예지하우스',_binary '',_binary '','ses_McWyoWwFah','1676565232466ses_McWyoWwFah','https://dhw80hz67vj6n.cloudfront.net/1676565232466ses_McWyoWwFah','되랏!!',13);
/*!40000 ALTER TABLE `live` ENABLE KEYS */;
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

-- Dump completed on 2023-02-17 10:56:39
