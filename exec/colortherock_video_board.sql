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
-- Table structure for table `video_board`
--

DROP TABLE IF EXISTS `video_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video_board` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `is_hidden` bit(1) DEFAULT NULL,
  `title` varchar(30) DEFAULT NULL,
  `member_id` bigint DEFAULT NULL,
  `video_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKer9rmiun2sfhppo1oaadd7bpk` (`member_id`),
  KEY `FKf1be2smmis7qlujq8lm8cjfin` (`video_id`),
  CONSTRAINT `FKer9rmiun2sfhppo1oaadd7bpk` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`),
  CONSTRAINT `FKf1be2smmis7qlujq8lm8cjfin` FOREIGN KEY (`video_id`) REFERENCES `video` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_board`
--

LOCK TABLES `video_board` WRITE;
/*!40000 ALTER TABLE `video_board` DISABLE KEYS */;
INSERT INTO `video_board` VALUES (10,'2023-02-16 14:07:22.807617',_binary '\0','햄버거맛짛~~',12,43),(11,'2023-02-16 14:08:34.843495',_binary '\0','[긴급]더현수 등장',1,44),(13,'2023-02-16 14:13:21.179188',_binary '\0','[긴급]',1,46),(15,'2023-02-16 14:19:29.738339',_binary '\0','나 더조한이다',1,49),(18,'2023-02-16 14:55:56.682434',_binary '\0','주황 초보!!',13,59),(21,'2023-02-16 15:07:19.963026',_binary '\0','첫 초록이?',13,63),(22,'2023-02-16 15:09:44.514773',_binary '\0','주황이??',13,65),(23,'2023-02-16 15:11:22.180985',_binary '\0','[중요] 저좀 봐주세요',1,66),(24,'2023-02-16 15:13:28.001202',_binary '\0','초록 성공!',13,67),(25,'2023-02-16 15:24:29.525710',_binary '\0','점프 성공 ㅋ ',6,69),(26,'2023-02-16 15:25:59.836891',_binary '\0','서울숲클라이밍',14,70),(27,'2023-02-16 15:27:15.713953',_binary '\0','왕십리 훅클라이밍',15,71),(28,'2023-02-16 15:28:24.827871',_binary '\0','기억나니 거미시절',14,72),(29,'2023-02-16 15:31:53.902576',_binary '\0','검정검정?',5,73),(30,'2023-02-16 15:32:07.888097',_binary '\0','웨어하우스짐',15,74),(31,'2023-02-16 15:32:29.320546',_binary '\0','핑크핑크???',13,75),(32,'2023-02-16 15:33:18.108533',_binary '\0','주황주황?',5,76),(33,'2023-02-16 15:33:43.291768',_binary '\0','저도 성공했어요',1,77),(34,'2023-02-16 15:33:58.580378',_binary '\0','코어야 힘내렴',16,78),(35,'2023-02-16 15:36:36.734387',_binary '\0','다이노는 코뿔소',15,80),(36,'2023-02-16 15:37:36.302741',_binary '\0','친구들의 환호속 성공 ㅋ',16,81),(37,'2023-02-16 15:39:42.881994',_binary '\0','다이소는 코뿔소',15,82),(38,'2023-02-16 15:39:57.597325',_binary '\0','인생 첫 남색',16,83),(40,'2023-02-16 23:59:33.706989',_binary '\0','원숭이',19,115),(41,'2023-02-17 00:07:37.243425',_binary '\0','더클양재 빨강클!(반동은 필수)',20,118),(42,'2023-02-17 00:34:31.167753',_binary '\0','주황색 성공~!~!',21,119),(43,'2023-02-17 00:34:54.056805',_binary '\0','오버행..',19,120),(44,'2023-02-17 00:35:41.852050',_binary '\0','역시 멋진 나?',21,121),(45,'2023-02-17 00:36:53.781531',_binary '\0','핑크 뿌셔?',21,122),(46,'2023-02-17 00:38:33.052012',_binary '\0','성공 성공 대성공??',21,123);
/*!40000 ALTER TABLE `video_board` ENABLE KEYS */;
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

-- Dump completed on 2023-02-17 10:56:33
