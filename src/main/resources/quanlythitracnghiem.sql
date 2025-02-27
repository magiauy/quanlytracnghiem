-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               10.4.32-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for quanlythitracnghiem
CREATE DATABASE IF NOT EXISTS `quanlythitracnghiem` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `quanlythitracnghiem`;

-- Dumping structure for table quanlythitracnghiem.answers
CREATE TABLE IF NOT EXISTS `answers` (
  `awID` int(11) NOT NULL AUTO_INCREMENT,
  `qID` int(11) NOT NULL,
  `awContent` text DEFAULT NULL,
  `awPictures` text DEFAULT NULL,
  `isRight` tinyint(4) DEFAULT NULL,
  `awStatus` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`awID`),
  KEY `qID` (`qID`),
  CONSTRAINT `FK_answers_question` FOREIGN KEY (`qID`) REFERENCES `question` (`qID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table quanlythitracnghiem.answers: ~0 rows (approximately)

-- Dumping structure for table quanlythitracnghiem.exam
CREATE TABLE IF NOT EXISTS `exam` (
  `exID` int(11) NOT NULL AUTO_INCREMENT,
  `testID` int(11) DEFAULT NULL,
  `exOrder` varchar(1) DEFAULT NULL,
  `ex_Questions` text DEFAULT NULL,
  PRIMARY KEY (`exID`) USING BTREE,
  KEY `testID` (`testID`),
  CONSTRAINT `FK_exam_test` FOREIGN KEY (`testID`) REFERENCES `test` (`testID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table quanlythitracnghiem.exam: ~0 rows (approximately)

-- Dumping structure for table quanlythitracnghiem.logs
CREATE TABLE IF NOT EXISTS `logs` (
  `logsID` int(11) NOT NULL AUTO_INCREMENT,
  `logUserID` int(11) DEFAULT NULL,
  `logExID` int(11) DEFAULT NULL,
  `logContent` text DEFAULT NULL,
  `logDate` date DEFAULT NULL,
  PRIMARY KEY (`logsID`),
  KEY `logUserID` (`logUserID`),
  KEY `logExID` (`logExID`),
  CONSTRAINT `FK_logs_exam` FOREIGN KEY (`logExID`) REFERENCES `exam` (`exID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_logs_user` FOREIGN KEY (`logUserID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table quanlythitracnghiem.logs: ~0 rows (approximately)

-- Dumping structure for table quanlythitracnghiem.question
CREATE TABLE IF NOT EXISTS `question` (
  `qID` int(11) NOT NULL AUTO_INCREMENT,
  `qContent` text DEFAULT NULL,
  `qPictures` text DEFAULT NULL,
  `qTopicID` int(11) DEFAULT NULL,
  `qLevel` enum('easy','medium','diff') DEFAULT NULL,
  `qStatus` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`qID`),
  KEY `qTopicID` (`qTopicID`),
  CONSTRAINT `FK_question_topic` FOREIGN KEY (`qTopicID`) REFERENCES `topic` (`tpID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table quanlythitracnghiem.question: ~0 rows (approximately)

-- Dumping structure for table quanlythitracnghiem.result
CREATE TABLE IF NOT EXISTS `result` (
  `rsID` int(11) NOT NULL AUTO_INCREMENT,
  `exID` int(11) DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `rsAnswers` text DEFAULT NULL,
  `rsMark` int(11) DEFAULT NULL,
  `rsDate` int(11) DEFAULT NULL,
  PRIMARY KEY (`rsID`),
  KEY `exID` (`exID`),
  KEY `userID` (`userID`),
  CONSTRAINT `FK_result_exam` FOREIGN KEY (`exID`) REFERENCES `exam` (`exID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_result_user` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table quanlythitracnghiem.result: ~0 rows (approximately)

-- Dumping structure for table quanlythitracnghiem.test
CREATE TABLE IF NOT EXISTS `test` (
  `testID` int(11) NOT NULL AUTO_INCREMENT,
  `testTitle` text DEFAULT NULL,
  `testTime` int(11) DEFAULT NULL,
  `num_easy` int(11) DEFAULT NULL,
  `num_medium` int(11) DEFAULT NULL,
  `num_diff` int(11) DEFAULT NULL,
  `testLimit` tinyint(4) DEFAULT NULL,
  `testDate` date DEFAULT NULL,
  `testStatus` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`testID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table quanlythitracnghiem.test: ~0 rows (approximately)

-- Dumping structure for table quanlythitracnghiem.test_topic
CREATE TABLE IF NOT EXISTS `test_topic` (
  `testID` int(11) NOT NULL,
  `tpID` int(11) NOT NULL,
  PRIMARY KEY (`testID`,`tpID`),
  KEY `testID` (`testID`),
  KEY `tpID` (`tpID`),
  CONSTRAINT `FK_test_topic_test` FOREIGN KEY (`testID`) REFERENCES `test` (`testID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_test_topic_topic` FOREIGN KEY (`tpID`) REFERENCES `topic` (`tpID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table quanlythitracnghiem.test_topic: ~0 rows (approximately)

-- Dumping structure for table quanlythitracnghiem.topic
CREATE TABLE IF NOT EXISTS `topic` (
  `tpID` int(11) NOT NULL AUTO_INCREMENT,
  `tpTitle` text DEFAULT NULL,
  `tpParent` int(11) DEFAULT NULL,
  `tpStatus` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`tpID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table quanlythitracnghiem.topic: ~0 rows (approximately)

-- Dumping structure for table quanlythitracnghiem.user
CREATE TABLE IF NOT EXISTS `user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(40) DEFAULT NULL,
  `userEmail` varchar(20) DEFAULT NULL,
  `userPassword` varchar(40) DEFAULT NULL,
  `userFullName` varchar(40) DEFAULT NULL,
  `isAdmin` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table quanlythitracnghiem.user: ~0 rows (approximately)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
