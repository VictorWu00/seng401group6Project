-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: seng401
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `author` (
  `Name` varchar(100) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `PhoneNumber` varchar(100) NOT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES ('Douglas L','1234 University Dr, Calgary','587-7777-5342'),('Edison K','3244 University Dr, Calgary','587-2134-2344'),('Hukin F','8766 University Dr, Calgary','587-0988-0631'),('James C','9981 University Dr, Calgary','587-6666-5442'),('Jason T','6123 University Dr, Calgary','587-9778-0314'),('Kevin L','6123 University Dr, Calgary','587-2316-5412'),('Kim B','324 University Dr, Calgary','587-3133-9873'),('Krooz S','6313 University Dr, Calgary','403-2221-1334'),('Ronald C','99 University Dr, Calgary','403-1213-5466'),('Tyler S','6411 University Dr, Calgary','587-1234-5443');
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `BookID` int NOT NULL AUTO_INCREMENT,
  `ISBN` int NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Description` varchar(100) NOT NULL,
  `Category` varchar(100) NOT NULL,
  `Year` int NOT NULL,
  `Auhor` varchar(100) NOT NULL,
  `Publisher` varchar(100) NOT NULL,
  `SectionName` varchar(100) NOT NULL,
  `Location` int NOT NULL,
  `Status` varchar(100) NOT NULL,
  PRIMARY KEY (`BookID`),
  KEY `Auhor` (`Auhor`),
  KEY `Publisher` (`Publisher`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,33210,'Four Kindoms','Historical fiction book about wars of 4 kindoms','Fiction',2008,'Douglas L','CalgBOOK House','A',1,'Rented'),(2,45780,'Magic Rings','Adventure story of a rogue mage','Fiction',2018,'Douglas L','CalgBOOK House','B',1,'Available'),(3,77777,'FactoryL','A real player story','Biography',2019,'James C','CalgBOOK House','C',1,'Rented'),(11,56545,'The Universe','The Universe And the Galaxy Information','Science',2021,'Krooz S','MainBook House','G',1,'Available');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `borrow`
--

DROP TABLE IF EXISTS `borrow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `borrow` (
  `User_ID` int NOT NULL,
  `Book_ID` int NOT NULL,
  `Start_Date` date NOT NULL,
  `End_Date` date NOT NULL,
  PRIMARY KEY (`User_ID`,`Book_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `borrow`
--

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;
INSERT INTO `borrow` VALUES (232323,1,'2022-03-28','2022-03-30'),(232323,3,'2022-03-30','2022-03-31');
/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `library`
--

DROP TABLE IF EXISTS `library`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `library` (
  `LibraryID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `PhoneNumber` varchar(20) NOT NULL,
  PRIMARY KEY (`LibraryID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `library`
--

LOCK TABLES `library` WRITE;
/*!40000 ALTER TABLE `library` DISABLE KEYS */;
INSERT INTO `library` VALUES (1,'Chinook Library','250 Rock Road, Calgary','403-111-2222'),(2,'Brentwood Library','1 Brentwood Dr, Calgary','587-999-9999'),(3,'University Library','1 University Dr, Calgary','587-666-6666');
/*!40000 ALTER TABLE `library` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publisher`
--

DROP TABLE IF EXISTS `publisher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publisher` (
  `Name` varchar(100) NOT NULL,
  `Adddress` varchar(100) NOT NULL,
  `PhoneNumber` varchar(100) NOT NULL,
  PRIMARY KEY (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES ('AlbertaBook House','124 Avenue, Edmonton','587-911-5151'),('CalgBOOK House','1 centre st, Calgary','587-888-8888'),('ChinookBOOK House','3 Chinook Dr, Calgary','587-321-8543'),('EastcoastBook House','328 East Avenue, Calgary','403-121-9987'),('MainBook House','134 Center St, Calgary','487-145-1355'),('NorthlandBook House','132 Dr, Calgary','403-533-8972'),('SouthLibBook House','7699 South Center, Calgary','403-9971-5321'),('UniverseBOOK House','5555 University Dr, Calgary','587-321-3511');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `User_ID` int NOT NULL,
  `Book_ID` int NOT NULL,
  `Description` varchar(100) NOT NULL,
  `Rating` varchar(100) NOT NULL,
  `ReviewDate` date DEFAULT NULL,
  PRIMARY KEY (`User_ID`,`Book_ID`),
  KEY `Book_ID` (`Book_ID`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`Book_ID`) REFERENCES `book` (`BookID`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`User_ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (232323,1,'Best fiction of all','4 stars','2022-03-09'),(232324,1,'good book','3 stars','2022-03-14'),(232324,2,'Just so-so','2 stars','2022-03-15'),(232325,1,'Nice Book','4 stars','2022-03-08'),(232325,3,'Glad to read his story when sad','3 stars','2022-02-08');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `section` (
  `Name` varchar(100) NOT NULL,
  `AisleNumber` varchar(100) NOT NULL,
  `Library_ID` int NOT NULL,
  PRIMARY KEY (`Name`),
  KEY `Library_ID` (`Library_ID`),
  CONSTRAINT `section_ibfk_1` FOREIGN KEY (`Library_ID`) REFERENCES `library` (`LibraryID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES ('A','11',1),('B','12',1),('C','13',1);
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `PhoneNumber` varchar(100) NOT NULL,
  `DateOfBirth` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` int NOT NULL,
  `library_Id` int NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `library_Id` (`library_Id`),
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`library_Id`) REFERENCES `library` (`LibraryID`)
) ENGINE=InnoDB AUTO_INCREMENT=881326 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (881324,'Rui','2500 University Dr, Calgary','4034571234','2000/06/06','ruiguan@gmail.com',401,1),(881325,'Eason','376 SouthRoad, Calgary','4032137899','1999/05/26','eason@gmail.com',401,2);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Address` varchar(100) NOT NULL,
  `PhoneNumber` varchar(100) NOT NULL,
  `DateOfBirth` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Balance` double NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=232328 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (232323,'Tianfan','212 Rock Avenue, Calgary','5872331542','1996-01-01','Tianfan@gmail.com','seng401',20),(232324,'Manpreet','12121 Saddle< Calgary','5878888887','1996-04-03','manpreet@gmail.com','seng401',0),(232325,'Zheng','1919 University Dr, Calgary','5872336578','2000-01-01','zhengchen@gamil.com','seng401',50),(232326,'Weitao','129 Center Street, Calgary','5878999578','2000-01-27','weitao@gmail.com','seng401',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-28 21:08:45
