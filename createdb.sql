CREATE DATABASE  IF NOT EXISTS `quan_ly_sinh_vien` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `quan_ly_sinh_vien`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quan_ly_sinh_vien
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `hoa_don_hoc_phi`
--

DROP TABLE IF EXISTS `hoa_don_hoc_phi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hoa_don_hoc_phi` (
  `ma_hd` int NOT NULL AUTO_INCREMENT,
  `ma_sv` varchar(20) DEFAULT NULL,
  `ky_hoc` varchar(50) DEFAULT NULL,
  `so_tien` float DEFAULT NULL,
  `trang_thai` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ma_hd`),
  KEY `ma_sv` (`ma_sv`),
  CONSTRAINT `hoa_don_hoc_phi_ibfk_1` FOREIGN KEY (`ma_sv`) REFERENCES `sinh_vien` (`ma_sv`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hoa_don_hoc_phi`
--

LOCK TABLES `hoa_don_hoc_phi` WRITE;
/*!40000 ALTER TABLE `hoa_don_hoc_phi` DISABLE KEYS */;
INSERT INTO `hoa_don_hoc_phi` VALUES (10,'B22DCCN001','Kỳ 1 - 2026',14500000,'Đã nộp'),(11,'B22DCCN002','Kỳ 1 - 2026',14500000,'Đã nộp'),(12,'B23DCCN001','Kỳ 1 - 2026',15000000,'Đã nộp'),(13,'B23DCCN002','Kỳ 1 - 2026',15000000,'Chưa nộp'),(14,'B24DCCN001','Kỳ 1 - 2026',15500000,'Đã nộp'),(15,'B24DCCN002','Kỳ 1 - 2026',15500000,'Đã nộp'),(16,'B25DCCN001','Kỳ 1 - 2026',16000000,'Chưa nộp'),(17,'B25DCCN002','Kỳ 1 - 2026',16000000,'Chưa nộp');
/*!40000 ALTER TABLE `hoa_don_hoc_phi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phong_ktx`
--

DROP TABLE IF EXISTS `phong_ktx`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phong_ktx` (
  `ma_phong` varchar(20) NOT NULL,
  `loai_phong` varchar(50) DEFAULT NULL,
  `suc_chua` int DEFAULT NULL,
  `so_nguoi_dang_o` int DEFAULT '0',
  PRIMARY KEY (`ma_phong`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phong_ktx`
--

LOCK TABLES `phong_ktx` WRITE;
/*!40000 ALTER TABLE `phong_ktx` DISABLE KEYS */;
INSERT INTO `phong_ktx` VALUES ('P101','Phòng thường Nam',8,2),('P102','Phòng dịch vụ Nam',4,1),('P201','Phòng thường Nữ',8,2),('P202','Phòng dịch vụ Nữ',4,0),('P301','Phòng thường Nữ',8,0);
/*!40000 ALTER TABLE `phong_ktx` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sinh_vien`
--

DROP TABLE IF EXISTS `sinh_vien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sinh_vien` (
  `ma_sv` varchar(20) NOT NULL,
  `ho_ten` varchar(100) NOT NULL,
  `ngay_sinh` date DEFAULT NULL,
  `gioi_tinh` varchar(10) DEFAULT NULL,
  `chuyen_nganh` varchar(100) DEFAULT NULL,
  `diem_tb` float DEFAULT '0',
  `ma_phong` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ma_sv`),
  KEY `ma_phong` (`ma_phong`),
  CONSTRAINT `sinh_vien_ibfk_1` FOREIGN KEY (`ma_phong`) REFERENCES `phong_ktx` (`ma_phong`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sinh_vien`
--

LOCK TABLES `sinh_vien` WRITE;
/*!40000 ALTER TABLE `sinh_vien` DISABLE KEYS */;
INSERT INTO `sinh_vien` VALUES ('B22DCCN001','Nguyen Van A','2004-03-15','Nam','Cong Nghe Phan Mem',0,'P101'),('B22DCCN002','Tran Thi B','2004-07-22','Nu','He Thong Thong Tin',2.5,'P201'),('B23DCCN001','Le Van C','2005-01-10','Nam','An Toan Thong Tin',0,'P101'),('B23DCCN002','Pham Thi D','2005-11-05','Nu','Cong Nghe Phan Mem',0,'P201'),('B24DCCN001','Nguyen Van A','2004-05-12','Nam','Cong Nghe Phan Mem',3,'P102'),('B24DCCN002','Vu Thi F','2006-09-18','Nu','Khoa Hoc May Tinh',2.4,NULL),('B25DCCN001','Ngo Van G','2007-02-28','Nam','Cong Nghe Phan Mem',0,NULL),('B25DCCN002','Do Thi H','2007-12-12','Nu','He Thong Thong Tin',0,NULL);
/*!40000 ALTER TABLE `sinh_vien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tai_khoan`
--

DROP TABLE IF EXISTS `tai_khoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tai_khoan` (
  `ten_dang_nhap` varchar(50) NOT NULL,
  `mat_khau` varchar(50) NOT NULL,
  PRIMARY KEY (`ten_dang_nhap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tai_khoan`
--

LOCK TABLES `tai_khoan` WRITE;
/*!40000 ALTER TABLE `tai_khoan` DISABLE KEYS */;
INSERT INTO `tai_khoan` VALUES ('admin','123456');
/*!40000 ALTER TABLE `tai_khoan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-07-26 12:50:37
