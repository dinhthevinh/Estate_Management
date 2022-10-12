-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: estateadvance2
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `assginmentbuilding`
--

LOCK TABLES `assginmentbuilding` WRITE;
/*!40000 ALTER TABLE `assginmentbuilding` DISABLE KEYS */;
INSERT INTO `assginmentbuilding` VALUES (2,2),(3,2);
/*!40000 ALTER TABLE `assginmentbuilding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `assginmentcustomer`
--

LOCK TABLES `assginmentcustomer` WRITE;
/*!40000 ALTER TABLE `assginmentcustomer` DISABLE KEYS */;
INSERT INTO `assginmentcustomer` VALUES (3,2),(4,3),(2,1);
/*!40000 ALTER TABLE `assginmentcustomer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `building`
--

LOCK TABLES `building` WRITE;
/*!40000 ALTER TABLE `building` DISABLE KEYS */;
INSERT INTO `building` VALUES (1,NULL,'',NULL,'','','','QUAN_1','',NULL,'','','','','Nam Giao Building Tower','',3,'','',15,'15 triệu/m2','',NULL,'59 phan xích long','','TANG_TRET,NGUYEN_CAN','Phường 2',''),(2,NULL,NULL,NULL,NULL,NULL,NULL,'QUAN_2',NULL,NULL,NULL,NULL,NULL,NULL,'ACM Tower',NULL,2,NULL,NULL,18,'18 triệu/m2',NULL,NULL,'96 cao thắng',NULL,'NGUYEN_CAN','Phường 4',NULL),(3,NULL,NULL,NULL,NULL,NULL,NULL,'QUAN_1',NULL,NULL,NULL,NULL,NULL,NULL,'Alpha 2 Building Tower',NULL,1,NULL,NULL,20,'20 triệu/m2',NULL,NULL,'153 nguyễn đình chiểu',NULL,'NOI_THAT','Phường 6',NULL),(4,NULL,NULL,NULL,NULL,NULL,NULL,'QUAN_4',NULL,NULL,NULL,NULL,NULL,NULL,'IDD 1 Building',NULL,1,NULL,NULL,12,'12 triệu/m2',NULL,NULL,'111 Lý Chính Thắng',NULL,'TANG_TRET,NGUYEN_CAN,NOI_THAT','Phường 7',NULL);
/*!40000 ALTER TABLE `building` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'','anh Thanh','','40m2 quận 1','',''),(2,NULL,'chị Hà',NULL,'100m2 quận 3 giá rẻ',NULL,NULL),(3,NULL,'anh Dũng',NULL,'130m2 quận Tân Bình',NULL,NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rentarea`
--

LOCK TABLES `rentarea` WRITE;
/*!40000 ALTER TABLE `rentarea` DISABLE KEYS */;
INSERT INTO `rentarea` VALUES (3,200,2),(4,300,2),(5,400,2),(6,300,3),(7,400,3),(8,500,3),(99,100,NULL),(100,200,NULL),(101,100,NULL),(102,200,NULL),(109,200,NULL),(110,300,NULL),(111,100,1),(112,200,1);
/*!40000 ALTER TABLE `rentarea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'manager','Quản lý'),(2,'staff','Nhân viên');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'CHAM_SOC_KHACH_HANG','Đã gọi điện và tư vấn',1),(2,'DAN_DI_XEM','Đi xem tòa nhà',1),(3,'DAN_DI_XEM','Đi xem tòa nhà',2);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,NULL,'nguyen van a','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvana'),(2,NULL,'nguyen van b','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvanb'),(3,NULL,'nguyen van c','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvanc'),(4,NULL,'nguyen van d','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG',1,'nguyenvand');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES (1,1),(2,2),(3,2),(4,2);
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-12 16:33:12
