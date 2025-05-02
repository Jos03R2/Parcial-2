-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: covidstatsdb
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `regions`
--

DROP TABLE IF EXISTS `regions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `iso` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=220 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regions`
--

LOCK TABLES `regions` WRITE;
/*!40000 ALTER TABLE `regions` DISABLE KEYS */;
INSERT INTO `regions` VALUES (1,'CHN','China'),(2,'TWN','Taipei and environs'),(3,'USA','US'),(4,'JPN','Japan'),(5,'THA','Thailand'),(6,'KOR','Korea, South'),(7,'SGP','Singapore'),(8,'PHL','Philippines'),(9,'MYS','Malaysia'),(10,'VNM','Vietnam'),(11,'AUS','Australia'),(12,'MEX','Mexico'),(13,'BRA','Brazil'),(14,'COL','Colombia'),(15,'FRA','France'),(16,'NPL','Nepal'),(17,'CAN','Canada'),(18,'KHM','Cambodia'),(19,'LKA','Sri Lanka'),(20,'CIV','Cote d\'Ivoire'),(21,'DEU','Germany'),(22,'FIN','Finland'),(23,'ARE','United Arab Emirates'),(24,'IND','India'),(25,'ITA','Italy'),(26,'GBR','United Kingdom'),(27,'RUS','Russia'),(28,'SWE','Sweden'),(29,'ESP','Spain'),(30,'BEL','Belgium'),(31,'Others','Others'),(32,'EGY','Egypt'),(33,'IRN','Iran'),(34,'ISR','Israel'),(35,'LBN','Lebanon'),(36,'IRQ','Iraq'),(37,'OMN','Oman'),(38,'AFG','Afghanistan'),(39,'BHR','Bahrain'),(40,'KWT','Kuwait'),(41,'AUT','Austria'),(42,'DZA','Algeria'),(43,'HRV','Croatia'),(44,'CHE','Switzerland'),(45,'PAK','Pakistan'),(46,'GEO','Georgia'),(47,'GRC','Greece'),(48,'MKD','North Macedonia'),(49,'NOR','Norway'),(50,'ROU','Romania'),(51,'DNK','Denmark'),(52,'EST','Estonia'),(53,'NLD','Netherlands'),(54,'SMR','San Marino'),(55,'AZE','Azerbaijan'),(56,'BLR','Belarus'),(57,'ISL','Iceland'),(58,'LTU','Lithuania'),(59,'NZL','New Zealand'),(60,'NGA','Nigeria'),(61,'IRL','Ireland'),(62,'LUX','Luxembourg'),(63,'MCO','Monaco'),(64,'QAT','Qatar'),(65,'ECU','Ecuador'),(66,'CZE','Czechia'),(67,'ARM','Armenia'),(68,'DOM','Dominican Republic'),(69,'IDN','Indonesia'),(70,'PRT','Portugal'),(71,'AND','Andorra'),(72,'LVA','Latvia'),(73,'MAR','Morocco'),(74,'SAU','Saudi Arabia'),(75,'SEN','Senegal'),(76,'ARG','Argentina'),(77,'CHL','Chile'),(78,'JOR','Jordan'),(79,'UKR','Ukraine'),(80,'BLM','Saint Barthelemy'),(81,'HUN','Hungary'),(82,'FRO','Faroe Islands'),(83,'GIB','Gibraltar'),(84,'LIE','Liechtenstein'),(85,'POL','Poland'),(86,'TUN','Tunisia'),(87,'PSE','West Bank and Gaza'),(88,'BIH','Bosnia and Herzegovina'),(89,'SVN','Slovenia'),(90,'ZAF','South Africa'),(91,'BTN','Bhutan'),(92,'CMR','Cameroon'),(93,'CRI','Costa Rica'),(94,'PER','Peru'),(95,'SRB','Serbia'),(96,'SVK','Slovakia'),(97,'TGO','Togo'),(98,'VAT','Holy See'),(99,'GUF','French Guiana'),(100,'MLT','Malta'),(101,'MTQ','Martinique'),(102,'BGR','Bulgaria'),(103,'MDV','Maldives'),(104,'BGD','Bangladesh'),(105,'MDA','Moldova'),(106,'PRY','Paraguay'),(107,'ALB','Albania'),(108,'CYP','Cyprus'),(109,'BRN','Brunei'),(110,'MAC','Macao SAR'),(111,'MAF','Saint Martin'),(112,'BFA','Burkina Faso'),(113,'GGY-JEY','Channel Islands'),(114,'MNG','Mongolia'),(115,'PAN','Panama'),(116,'cruise','Cruise Ship'),(117,'TWN','Taiwan*'),(118,'BOL','Bolivia'),(119,'HND','Honduras'),(120,'COD','Congo (Kinshasa)'),(121,'JAM','Jamaica'),(122,'REU','Reunion'),(123,'TUR','Turkey'),(124,'CUB','Cuba'),(125,'GUY','Guyana'),(126,'KAZ','Kazakhstan'),(127,'CYM','Cayman Islands'),(128,'GLP','Guadeloupe'),(129,'ETH','Ethiopia'),(130,'SDN','Sudan'),(131,'GIN','Guinea'),(132,'ATG','Antigua and Barbuda'),(133,'ABW','Aruba'),(134,'KEN','Kenya'),(135,'URY','Uruguay'),(136,'GHA','Ghana'),(137,'JEY','Jersey'),(138,'NAM','Namibia'),(139,'SYC','Seychelles'),(140,'TTO','Trinidad and Tobago'),(141,'VEN','Venezuela'),(142,'CUW','Curacao'),(143,'SWZ','Eswatini'),(144,'GAB','Gabon'),(145,'GTM','Guatemala'),(146,'GGY','Guernsey'),(147,'MRT','Mauritania'),(148,'RWA','Rwanda'),(149,'LCA','Saint Lucia'),(150,'VCT','Saint Vincent and the Grenadines'),(151,'SUR','Suriname'),(152,'RKS','Kosovo'),(153,'CAF','Central African Republic'),(154,'COG','Congo (Brazzaville)'),(155,'GNQ','Equatorial Guinea'),(156,'UZB','Uzbekistan'),(157,'GUM','Guam'),(158,'PRI','Puerto Rico'),(159,'BEN','Benin'),(160,'GRL','Greenland'),(161,'LBR','Liberia'),(162,'MYT','Mayotte'),(163,'SOM','Somalia'),(164,'TZA','Tanzania'),(165,'BHS','Bahamas'),(166,'BRB','Barbados'),(167,'MNE','Montenegro'),(168,'GMB','Gambia'),(169,'KGZ','Kyrgyzstan'),(170,'MUS','Mauritius'),(171,'ZMB','Zambia'),(172,'DJI','Djibouti'),(173,'TCD','Chad'),(174,'SLV','El Salvador'),(175,'FJI','Fiji'),(176,'NIC','Nicaragua'),(177,'MDG','Madagascar'),(178,'HTI','Haiti'),(179,'AGO','Angola'),(180,'CPV','Cabo Verde'),(181,'NER','Niger'),(182,'PNG','Papua New Guinea'),(183,'ZWE','Zimbabwe'),(184,'TLS','Timor-Leste'),(185,'ERI','Eritrea'),(186,'UGA','Uganda'),(187,'DMA','Dominica'),(188,'GRD','Grenada'),(189,'MOZ','Mozambique'),(190,'SYR','Syria'),(191,'BLZ','Belize'),(192,'LAO','Laos'),(193,'LBY','Libya'),(194,'NA-SHIP-DP','Diamond Princess'),(195,'GNB','Guinea-Bissau'),(196,'MLI','Mali'),(197,'KNA','Saint Kitts and Nevis'),(198,'BWA','Botswana'),(199,'BDI','Burundi'),(200,'SLE','Sierra Leone'),(201,'MMR','Burma'),(202,'MWI','Malawi'),(203,'SSD','South Sudan'),(204,'ESH','Western Sahara'),(205,'STP','Sao Tome and Principe'),(206,'NA-SHIP-MSZ','MS Zaandam'),(207,'YEM','Yemen'),(208,'COM','Comoros'),(209,'TJK','Tajikistan'),(210,'LSO','Lesotho'),(211,'SLB','Solomon Islands'),(212,'MHL','Marshall Islands'),(213,'VUT','Vanuatu'),(214,'WSM','Samoa'),(215,'KIR','Kiribati'),(216,'PLW','Palau'),(217,'TON','Tonga'),(218,'NRU','Nauru'),(219,'TUV','Tuvalu');
/*!40000 ALTER TABLE `regions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-26 22:56:40
