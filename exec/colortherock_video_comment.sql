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
-- Table structure for table `video_comment`
--

DROP TABLE IF EXISTS `video_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  `video_board_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdua6k5g54jahee2y1hwmakd04` (`member_id`),
  KEY `FKf9jsmcquuj3qn20xap419srmx` (`video_board_id`),
  CONSTRAINT `FKdua6k5g54jahee2y1hwmakd04` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKf9jsmcquuj3qn20xap419srmx` FOREIGN KEY (`video_board_id`) REFERENCES `video_board` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_comment`
--

LOCK TABLES `video_comment` WRITE;
/*!40000 ALTER TABLE `video_comment` DISABLE KEYS */;
INSERT INTO `video_comment` VALUES (12,'2023-02-16 14:09:46.177116','이게뭐죠? 신고합니다',6,10),(13,'2023-02-16 15:07:57.311239','썸네일 돌려봤어요 기뻐서',13,21),(14,'2023-02-16 15:28:31.461616','와 쩐다 ',6,27),(15,'2023-02-16 15:29:42.515908','팬이에요',1,28),(16,'2023-02-16 15:33:09.066304','와 뭐에여??? 짱잘함',13,30),(17,'2023-02-16 15:33:21.838615','거미 ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ',13,28),(18,'2023-02-16 15:35:40.823953','코어 쩐다',13,34),(19,'2023-02-16 15:36:33.294460','굿!',5,33),(20,'2023-02-16 15:36:34.860537','팬이에요',1,34),(21,'2023-02-16 15:39:32.929563','이거 실패영상인데요...ㅠㅠㅠ',7,15),(22,'2023-02-16 15:39:37.404763','햄버거 마이쪙~~',12,10),(23,'2023-02-16 16:22:56.012844','캬',13,37);
/*!40000 ALTER TABLE `video_comment` ENABLE KEYS */;
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

-- Dump completed on 2023-02-17 10:56:34
