-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gestionservicios
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `business`
--

DROP TABLE IF EXISTS `business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business` (
  `id` int NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `deleted` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business`
--

LOCK TABLES `business` WRITE;
/*!40000 ALTER TABLE `business` DISABLE KEYS */;
INSERT INTO `business` VALUES (1,'calle empresa 1','e1@gmail.com',NULL,'empresa 1','987654321',0),(2,'calle empresa 2','e2@gmail.com','logo2','empresa 2','987654321',0),(4,'calle empresa 3','e3@gmail.com',NULL,'empresa 4','728394617',0);
/*!40000 ALTER TABLE `business` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `business_seq`
--

DROP TABLE IF EXISTS `business_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `business_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `business_seq`
--

LOCK TABLES `business_seq` WRITE;
/*!40000 ALTER TABLE `business_seq` DISABLE KEYS */;
INSERT INTO `business_seq` VALUES (301);
/*!40000 ALTER TABLE `business_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pro_family`
--

DROP TABLE IF EXISTS `pro_family`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pro_family` (
  `id` int NOT NULL,
  `deleted` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pro_family`
--

LOCK TABLES `pro_family` WRITE;
/*!40000 ALTER TABLE `pro_family` DISABLE KEYS */;
INSERT INTO `pro_family` VALUES (1,0,'family 1'),(2,0,'family 2'),(3,0,'family 3'),(4,0,'family 4');
/*!40000 ALTER TABLE `pro_family` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pro_family_seq`
--

DROP TABLE IF EXISTS `pro_family_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pro_family_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pro_family_seq`
--

LOCK TABLES `pro_family_seq` WRITE;
/*!40000 ALTER TABLE `pro_family_seq` DISABLE KEYS */;
INSERT INTO `pro_family_seq` VALUES (251);
/*!40000 ALTER TABLE `pro_family_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `id` int NOT NULL,
  `full_date` datetime(6) DEFAULT NULL,
  `report` varchar(255) DEFAULT NULL,
  `service_time` int NOT NULL,
  `servicio_id` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rq9flow5cj5w292x6iylngen` (`servicio_id`),
  KEY `FKl6ssom7r83r8bofvdkjkp18pj` (`student_id`),
  CONSTRAINT `FKj59jsrghrxmqcfenyv73clyx7` FOREIGN KEY (`servicio_id`) REFERENCES `servicio` (`id`),
  CONSTRAINT `FKl6ssom7r83r8bofvdkjkp18pj` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,'2023-12-12 00:00:00.000000','I thoroughly enjoyed learning Java. Its flexibility and platform independence make it a must-know language for any aspiring programmer. The object-oriented approach and vast community support are definite pluses.',280,1,202),(2,'2023-12-12 00:00:00.000000','Laravel was a game-changer for me in web development. Its elegant syntax and built-in features made creating web applications a breeze. The MVC architecture and Blade templating system are fantastic.',20,2,1),(3,'2023-12-12 00:00:00.000000','Flutter made mobile app development a breeze. Its widget-based approach and hot reload feature allowed me to create stunning cross-platform apps efficiently. It\'s a great choice for mobile development.',40,102,202);
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report_seq`
--

DROP TABLE IF EXISTS `report_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report_seq`
--

LOCK TABLES `report_seq` WRITE;
/*!40000 ALTER TABLE `report_seq` DISABLE KEYS */;
INSERT INTO `report_seq` VALUES (801);
/*!40000 ALTER TABLE `report_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicio`
--

DROP TABLE IF EXISTS `servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicio` (
  `id` int NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `finished` int NOT NULL,
  `happening_date` datetime(6) DEFAULT NULL,
  `register_date` datetime(6) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `valoration` float NOT NULL,
  `business_id` int DEFAULT NULL,
  `profesional_family_id` int DEFAULT NULL,
  `student_id` int DEFAULT NULL,
  `deleted` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKshcq55rq2if9al4s16om61m8l` (`business_id`),
  KEY `FK5slc8xy5eadh8cfthtusp98y9` (`profesional_family_id`),
  KEY `FKtjmo62f0u0wjyao7lqq06xdp0` (`student_id`),
  CONSTRAINT `FK5slc8xy5eadh8cfthtusp98y9` FOREIGN KEY (`profesional_family_id`) REFERENCES `pro_family` (`id`),
  CONSTRAINT `FKshcq55rq2if9al4s16om61m8l` FOREIGN KEY (`business_id`) REFERENCES `business` (`id`),
  CONSTRAINT `FKtjmo62f0u0wjyao7lqq06xdp0` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio`
--

LOCK TABLES `servicio` WRITE;
/*!40000 ALTER TABLE `servicio` DISABLE KEYS */;
INSERT INTO `servicio` VALUES (1,'Java is like the universal language of programming; you can use it for almost anything.','A versatile and widely used programming language.',1,'2024-01-12 23:00:00.000000','2023-01-11 23:00:00.000000','Java',2,1,3,202,0),(2,'Laravel is a popular PHP framework that simplifies web development with its elegant syntax and powerful features.','A popular PHP framework for building web applications.',1,'2024-01-01 23:00:00.000000','2023-11-11 23:00:00.000000','Laravel',0,1,3,202,0),(3,NULL,'A high-level programming language known for its simplicity and readability.',0,NULL,'2024-01-07 23:00:00.000000','Python',0,2,3,202,0),(4,'It\'s an incredibly versatile language with a clear syntax that makes it beginner-friendly. The course covered everything from basic concepts to advanced topics like ASP.NET, Unity, and Xamarin.','An open-source business management software suite.',1,'2024-01-14 23:00:00.000000','2024-01-13 23:00:00.000000','Odoo',3,1,3,202,0),(5,NULL,'A scripting language commonly used for web development.',0,NULL,'2023-12-16 00:00:00.000000','JavaScript',0,1,1,NULL,0),(52,NULL,'A powerful and versatile programming language.',0,NULL,'2023-12-22 00:00:00.000000','C++',0,2,2,NULL,0),(102,'Flutter is like the magic wand of app development; it makes everything easy and fast.','An open-source UI framework for building natively compiled applications for mobile.',1,'2024-01-13 23:00:00.000000','2024-01-13 23:00:00.000000','Flutter',3.5,1,1,1,0),(103,NULL,'A server-side scripting language used for web development.',0,NULL,'2023-12-19 00:00:00.000000','PHP',0,2,2,1,0),(104,NULL,'Learning C# was like unlocking a world of possibilities.',0,NULL,'2023-12-18 00:00:00.000000','C#',0,1,3,NULL,0),(105,NULL,'Spring is an open-source framework that simplifies the development of enterprise Java applications.',0,NULL,'2023-12-12 00:00:00.000000','Spring',0,1,3,1,0);
/*!40000 ALTER TABLE `servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicio_seq`
--

DROP TABLE IF EXISTS `servicio_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicio_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio_seq`
--

LOCK TABLES `servicio_seq` WRITE;
/*!40000 ALTER TABLE `servicio_seq` DISABLE KEYS */;
INSERT INTO `servicio_seq` VALUES (951);
/*!40000 ALTER TABLE `servicio_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int NOT NULL,
  `deleted` int NOT NULL,
  `enabled` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `profesional_family_id` int DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `id_student` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmfxx10uc5ct7k0x35npelafpp` (`profesional_family_id`),
  CONSTRAINT `FKmfxx10uc5ct7k0x35npelafpp` FOREIGN KEY (`profesional_family_id`) REFERENCES `pro_family` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,0,1,'pablo','$2a$10$fYj6uOeGFuoUlgeEC06ELO6BoJLp4p9hCsovavKKxGF9iLNLYKVD6','ROLE_STUDENT','espanol','',2,'pablo@gmail.com',0),(4,1,0,'ana','$2a$10$fYj6uOeGFuoUlgeEC06ELO6BoJLp4p9hCsovavKKxGF9iLNLYKVD6','ROLE_STUDENT','ana',NULL,4,'ana@gmail.com',0),(152,0,1,'empresa 1','$2a$10$Muz2FOEQwfKZSvRtD0cpxeSL.IBzga3y9TGy5C3uv7tWeqbLj/uL6','ROLE_BUSINESS','empresa 1',NULL,2,'e1@gmail.com',0),(202,0,1,'ale','$2a$10$fYj6uOeGFuoUlgeEC06ELO6BoJLp4p9hCsovavKKxGF9iLNLYKVD6','ROLE_STUDENT','jandro',NULL,3,'ale@gmail.com',0),(252,0,1,'a','$2a$10$/pFg2/JZ1rS.HYiSbEM4V.Xra5sPCzjCrBWHQq/gVJOP7s1ElEOJG','ROLE_ADMIN','a',NULL,1,'a@gmail.com',0),(402,0,1,'empresa 2','$2a$10$fYj6uOeGFuoUlgeEC06ELO6BoJLp4p9hCsovavKKxGF9iLNLYKVD6','ROLE_BUSINESS','empresa 2',NULL,1,'e2@gmail.com',0),(403,0,1,'empresa 3','$2a$10$fYj6uOeGFuoUlgeEC06ELO6BoJLp4p9hCsovavKKxGF9iLNLYKVD6','ROLE_BUSINESS','empresa 3',NULL,3,'e3@gmail.com',0);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_seq`
--

DROP TABLE IF EXISTS `student_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_seq`
--

LOCK TABLES `student_seq` WRITE;
/*!40000 ALTER TABLE `student_seq` DISABLE KEYS */;
INSERT INTO `student_seq` VALUES (501);
/*!40000 ALTER TABLE `student_seq` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-14 19:59:18
