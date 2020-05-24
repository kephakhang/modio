-- MySQL dump 10.13  Distrib 5.6.14, for osx10.7 (i386)
--
-- Host: localhost    Database: modio
-- ------------------------------------------------------
-- Server version	5.6.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientdetails`
--

DROP TABLE IF EXISTS `clientdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientdetails` (
  `appId` varchar(255) NOT NULL,
  `resourceIds` varchar(256) DEFAULT NULL,
  `appSecret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `grantTypes` varchar(256) DEFAULT NULL,
  `redirectUrl` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  `autoApproveScopes` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientdetails`
--

LOCK TABLES `clientdetails` WRITE;
/*!40000 ALTER TABLE `clientdetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_access_token`
--

DROP TABLE IF EXISTS `oauth_access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` longblob,
  `authentication_id` varchar(255) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` longblob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_access_token`
--

LOCK TABLES `oauth_access_token` WRITE;
/*!40000 ALTER TABLE `oauth_access_token` DISABLE KEYS */;
INSERT INTO `oauth_access_token` VALUES ('86bed84d160a057d5047e2e6274da17d','��\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$��\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z���\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0]��@Pxsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/�Gc��ɷ\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens�\ncT�^\0L\0valueq\0~\0xpt\0$450b5fc1-4719-4fbc-847b-b32926c14ee5sq\0~\0	w\0\0^��z-xsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0��^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet�l�Z��*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readxt\0bearert\0$7163fac6-4434-47e6-9759-8a55762c8997','7088415553ae4a09cb194414cb8b0bd2','user','233668646673605','��\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1��\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0��^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0233668646673605sr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0	client_idt\0233668646673605t\0scopet\0readt\0usernamet\0userxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet�l�Z��*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0q\0~\0!xsq\0~\0\'w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0\'w\0\0\0?@\0\0\0\0\0\0xsq\0~\0\'w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\02sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0q\0~\0 q\0~\0!q\0~\0\"q\0~\0#x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0$sr\0java.util.TreeSetݘP���[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0user','64dac2f4965a7a18dfab54671a024e0e');
/*!40000 ALTER TABLE `oauth_access_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_approvals`
--

DROP TABLE IF EXISTS `oauth_approvals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_approvals`
--

LOCK TABLES `oauth_approvals` WRITE;
/*!40000 ALTER TABLE `oauth_approvals` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_approvals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_client_details`
--

DROP TABLE IF EXISTS `oauth_client_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_client_details`
--

LOCK TABLES `oauth_client_details` WRITE;
/*!40000 ALTER TABLE `oauth_client_details` DISABLE KEYS */;
INSERT INTO `oauth_client_details` VALUES ('my_client_id',NULL,'my_client_secret','read,write','authorization_code,password,client_credentials,implicit,refresh_token',NULL,'ROLE_MY_CLIENT',36000,2592000,NULL,NULL),('your_client_id',NULL,'your_client_secret','read','authorization_code,implicit',NULL,'ROLE_YOUR_CLIENT',36000,2592000,NULL,NULL);
/*!40000 ALTER TABLE `oauth_client_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_client_token`
--

DROP TABLE IF EXISTS `oauth_client_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` longblob,
  `authentication_id` varchar(255) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_client_token`
--

LOCK TABLES `oauth_client_token` WRITE;
/*!40000 ALTER TABLE `oauth_client_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_client_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_code`
--

DROP TABLE IF EXISTS `oauth_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` longblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_code`
--

LOCK TABLES `oauth_code` WRITE;
/*!40000 ALTER TABLE `oauth_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_refresh_token`
--

DROP TABLE IF EXISTS `oauth_refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` longblob,
  `authentication` longblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_refresh_token`
--

LOCK TABLES `oauth_refresh_token` WRITE;
/*!40000 ALTER TABLE `oauth_refresh_token` DISABLE KEYS */;
INSERT INTO `oauth_refresh_token` VALUES ('64dac2f4965a7a18dfab54671a024e0e','��\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/�Gc��ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens�\ncT�^\0L\0valuet\0Ljava/lang/String;xpt\0$450b5fc1-4719-4fbc-847b-b32926c14ee5sr\0java.util.Datehj�KYt\0\0xpw\0\0^��z-x','��\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1��\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0��^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx����a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0233668646673605sr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0	client_idt\0233668646673605t\0scopet\0readt\0usernamet\0userxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet�l�Z��*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0q\0~\0!xsq\0~\0\'w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0\'w\0\0\0?@\0\0\0\0\0\0xsq\0~\0\'w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\02sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0q\0~\0 q\0~\0!q\0~\0\"q\0~\0#x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0$sr\0java.util.TreeSetݘP���[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0user');
/*!40000 ALTER TABLE `oauth_refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_board`
--

DROP TABLE IF EXISTS `tb_board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_board` (
  `sn` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '게시판 구분 숫자',
  `mb_id` bigint(20) NOT NULL COMMENT '업로더 아이디',
  `title` varchar(100) NOT NULL COMMENT '제목',
  `rmk` text NOT NULL COMMENT '내용',
  PRIMARY KEY (`sn`),
  KEY `FK_tb_borad_tb_member` (`mb_id`),
  CONSTRAINT `FK_tb_borad_tb_member` FOREIGN KEY (`mb_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='게시판 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_board`
--

LOCK TABLES `tb_board` WRITE;
/*!40000 ALTER TABLE `tb_board` DISABLE KEYS */;
INSERT INTO `tb_board` VALUES (2,6,'testest','sdfasdf'),(4,6,'test2','test2'),(5,6,'test3','test3'),(6,6,'test','test'),(8,21,'test','test'),(10,6,'test','test'),(25,6,'테스트1','테스트 페이지'),(26,6,'변경 테스트 1234','내용 변경 테스트 입니다.'),(27,6,'테스트3','테스트 페이지'),(28,6,'테스트4','테스트 페이지'),(29,6,'테스트5','테스트 페이지'),(30,6,'테스트6 수정','테스트페이지 수정'),(33,6,'asdfa','asdfasdf'),(34,6,'테스트','테스트'),(35,6,'테스트 글입니다.','테스트 글 입니다.');
/*!40000 ALTER TABLE `tb_board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_buy`
--

DROP TABLE IF EXISTS `tb_buy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_buy` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 id',
  `sort` int(11) NOT NULL DEFAULT '1' COMMENT '구매 종류(1:buy, 2:change)',
  `goods_id` bigint(20) NOT NULL COMMENT '상품 ID',
  `store_id` bigint(20) NOT NULL COMMENT '매장 ID',
  `buydate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '구매시각',
  `user_id` bigint(20) NOT NULL COMMENT '구매 사용자 ID',
  `price` double NOT NULL COMMENT '구매가격',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `store_id` (`store_id`),
  KEY `goods_id` (`goods_id`),
  CONSTRAINT `tb_buy_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `tb_goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_buy_ibfk_2` FOREIGN KEY (`store_id`) REFERENCES `tb_store` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_buy_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='구매 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_buy`
--

LOCK TABLES `tb_buy` WRITE;
/*!40000 ALTER TABLE `tb_buy` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_buy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_category`
--

DROP TABLE IF EXISTS `tb_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 번호',
  `name` varchar(128) NOT NULL COMMENT '카테고리 명',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='컨텐츠 카테고리 명 정의 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_category`
--

LOCK TABLES `tb_category` WRITE;
/*!40000 ALTER TABLE `tb_category` DISABLE KEYS */;
INSERT INTO `tb_category` VALUES (9,'test'),(2,'게임'),(5,'동물'),(7,'문화유산'),(1,'사람'),(3,'사물'),(6,'자동차'),(8,'집/건물'),(4,'캐릭터');
/*!40000 ALTER TABLE `tb_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_config`
--

DROP TABLE IF EXISTS `tb_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_config` (
  `title` varchar(255) NOT NULL DEFAULT '',
  `theme` varchar(255) NOT NULL DEFAULT '',
  `admin` varchar(255) NOT NULL DEFAULT '',
  `admin_email` varchar(255) NOT NULL DEFAULT '',
  `admin_email_name` varchar(255) NOT NULL DEFAULT '',
  `add_script` text NOT NULL,
  `use_point` tinyint(4) NOT NULL DEFAULT '0',
  `point_term` int(11) NOT NULL DEFAULT '0',
  `use_copy_log` tinyint(4) NOT NULL DEFAULT '0',
  `use_email_certify` tinyint(4) NOT NULL DEFAULT '0',
  `login_point` int(11) NOT NULL DEFAULT '0',
  `cut_name` tinyint(4) NOT NULL DEFAULT '0',
  `nick_modify` int(11) NOT NULL DEFAULT '0',
  `new_skin` varchar(255) NOT NULL DEFAULT '',
  `new_rows` int(11) NOT NULL DEFAULT '0',
  `search_skin` varchar(255) NOT NULL DEFAULT '',
  `connect_skin` varchar(255) NOT NULL DEFAULT '',
  `faq_skin` varchar(255) NOT NULL DEFAULT '',
  `read_point` int(11) NOT NULL DEFAULT '0',
  `write_point` int(11) NOT NULL DEFAULT '0',
  `comment_point` int(11) NOT NULL DEFAULT '0',
  `download_point` int(11) NOT NULL DEFAULT '0',
  `write_pages` int(11) NOT NULL DEFAULT '0',
  `mobile_pages` int(11) NOT NULL DEFAULT '0',
  `link_target` varchar(255) NOT NULL DEFAULT '',
  `delay_sec` int(11) NOT NULL DEFAULT '0',
  `filter` text NOT NULL,
  `possible_ip` text NOT NULL,
  `intercept_ip` text NOT NULL,
  `analytics` text NOT NULL,
  `add_meta` text NOT NULL,
  `syndi_token` varchar(255) NOT NULL,
  `syndi_except` text NOT NULL,
  `member_skin` varchar(255) NOT NULL DEFAULT '',
  `use_homepage` tinyint(4) NOT NULL DEFAULT '0',
  `req_homepage` tinyint(4) NOT NULL DEFAULT '0',
  `use_tel` tinyint(4) NOT NULL DEFAULT '0',
  `req_tel` tinyint(4) NOT NULL DEFAULT '0',
  `use_hp` tinyint(4) NOT NULL DEFAULT '0',
  `req_hp` tinyint(4) NOT NULL DEFAULT '0',
  `use_addr` tinyint(4) NOT NULL DEFAULT '0',
  `req_addr` tinyint(4) NOT NULL DEFAULT '0',
  `use_signature` tinyint(4) NOT NULL DEFAULT '0',
  `req_signature` tinyint(4) NOT NULL DEFAULT '0',
  `use_profile` tinyint(4) NOT NULL DEFAULT '0',
  `req_profile` tinyint(4) NOT NULL DEFAULT '0',
  `register_level` tinyint(4) NOT NULL DEFAULT '0',
  `register_point` int(11) NOT NULL DEFAULT '0',
  `icon_level` tinyint(4) NOT NULL DEFAULT '0',
  `use_recommend` tinyint(4) NOT NULL DEFAULT '0',
  `recommend_point` int(11) NOT NULL DEFAULT '0',
  `leave_day` int(11) NOT NULL DEFAULT '0',
  `search_part` int(11) NOT NULL DEFAULT '0',
  `email_use` tinyint(4) NOT NULL DEFAULT '0',
  `email_wr_super_admin` tinyint(4) NOT NULL DEFAULT '0',
  `email_wr_group_admin` tinyint(4) NOT NULL DEFAULT '0',
  `email_wr_board_admin` tinyint(4) NOT NULL DEFAULT '0',
  `email_wr_write` tinyint(4) NOT NULL DEFAULT '0',
  `email_wr_comment_all` tinyint(4) NOT NULL DEFAULT '0',
  `email_mb_super_admin` tinyint(4) NOT NULL DEFAULT '0',
  `email_mb_member` tinyint(4) NOT NULL DEFAULT '0',
  `email_po_super_admin` tinyint(4) NOT NULL DEFAULT '0',
  `prohibit_id` text NOT NULL,
  `prohibit_email` text NOT NULL,
  `new_del` int(11) NOT NULL DEFAULT '0',
  `memo_del` int(11) NOT NULL DEFAULT '0',
  `visit_del` int(11) NOT NULL DEFAULT '0',
  `popular_del` int(11) NOT NULL DEFAULT '0',
  `optimize_date` date NOT NULL DEFAULT '0000-00-00',
  `use_member_icon` tinyint(4) NOT NULL DEFAULT '0',
  `member_icon_size` int(11) NOT NULL DEFAULT '0',
  `member_icon_width` int(11) NOT NULL DEFAULT '0',
  `member_icon_height` int(11) NOT NULL DEFAULT '0',
  `login_minutes` int(11) NOT NULL DEFAULT '0',
  `image_extension` varchar(255) NOT NULL DEFAULT '',
  `flash_extension` varchar(255) NOT NULL DEFAULT '',
  `movie_extension` varchar(255) NOT NULL DEFAULT '',
  `formmail_is_member` tinyint(4) NOT NULL DEFAULT '0',
  `page_rows` int(11) NOT NULL DEFAULT '0',
  `mobile_page_rows` int(11) NOT NULL DEFAULT '0',
  `visit` varchar(255) NOT NULL DEFAULT '',
  `max_po_id` int(11) NOT NULL DEFAULT '0',
  `stipulation` text NOT NULL,
  `privacy` text NOT NULL,
  `open_modify` int(11) NOT NULL DEFAULT '0',
  `memo_send_point` int(11) NOT NULL DEFAULT '0',
  `mobile_new_skin` varchar(255) NOT NULL DEFAULT '',
  `mobile_search_skin` varchar(255) NOT NULL DEFAULT '',
  `mobile_connect_skin` varchar(255) NOT NULL DEFAULT '',
  `mobile_faq_skin` varchar(255) NOT NULL DEFAULT '',
  `mobile_member_skin` varchar(255) NOT NULL DEFAULT '',
  `captcha_mp3` varchar(255) NOT NULL DEFAULT '',
  `editor` varchar(255) NOT NULL DEFAULT '',
  `cert_use` tinyint(4) NOT NULL DEFAULT '0',
  `cert_ipin` varchar(255) NOT NULL DEFAULT '',
  `cert_hp` varchar(255) NOT NULL DEFAULT '',
  `cert_kcb_cd` varchar(255) NOT NULL DEFAULT '',
  `cert_kcp_cd` varchar(255) NOT NULL DEFAULT '',
  `lg_mid` varchar(255) NOT NULL DEFAULT '',
  `lg_mert_key` varchar(255) NOT NULL DEFAULT '',
  `cert_limit` int(11) NOT NULL DEFAULT '0',
  `cert_req` tinyint(4) NOT NULL DEFAULT '0',
  `sms_use` varchar(255) NOT NULL DEFAULT '',
  `sms_type` varchar(10) NOT NULL DEFAULT '',
  `icode_id` varchar(255) NOT NULL DEFAULT '',
  `icode_pw` varchar(255) NOT NULL DEFAULT '',
  `icode_server_ip` varchar(255) NOT NULL DEFAULT '',
  `icode_server_port` varchar(255) NOT NULL DEFAULT '',
  `googl_shorturl_apikey` varchar(255) NOT NULL DEFAULT '',
  `facebook_appid` varchar(255) NOT NULL,
  `facebook_secret` varchar(255) NOT NULL,
  `twitter_key` varchar(255) NOT NULL,
  `twitter_secret` varchar(255) NOT NULL,
  `kakao_js_apikey` varchar(255) NOT NULL,
  `tmp1_subj` varchar(255) NOT NULL DEFAULT '',
  `tmp2_subj` varchar(255) NOT NULL DEFAULT '',
  `tmp3_subj` varchar(255) NOT NULL DEFAULT '',
  `tmp4_subj` varchar(255) NOT NULL DEFAULT '',
  `tmp5_subj` varchar(255) NOT NULL DEFAULT '',
  `tmp6_subj` varchar(255) NOT NULL DEFAULT '',
  `tmp7_subj` varchar(255) NOT NULL DEFAULT '',
  `tmp8_subj` varchar(255) NOT NULL DEFAULT '',
  `tmp9_subj` varchar(255) NOT NULL DEFAULT '',
  `tmp10_subj` varchar(255) NOT NULL DEFAULT '',
  `tmp1` varchar(255) NOT NULL DEFAULT '',
  `tmp2` varchar(255) NOT NULL DEFAULT '',
  `tmp3` varchar(255) NOT NULL DEFAULT '',
  `tmp4` varchar(255) NOT NULL DEFAULT '',
  `tmp5` varchar(255) NOT NULL DEFAULT '',
  `tmp6` varchar(255) NOT NULL DEFAULT '',
  `tmp7` varchar(255) NOT NULL DEFAULT '',
  `tmp8` varchar(255) NOT NULL DEFAULT '',
  `tmp9` varchar(255) NOT NULL DEFAULT '',
  `tmp10` varchar(255) NOT NULL DEFAULT '',
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_config`
--

LOCK TABLES `tb_config` WRITE;
/*!40000 ALTER TABLE `tb_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_content`
--

DROP TABLE IF EXISTS `tb_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_content` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 번호',
  `uuid` varchar(36) NOT NULL COMMENT '원본 컨텐츠 uuid',
  `mb_id` bigint(20) NOT NULL COMMENT '회원아이디',
  `ca_id` int(11) NOT NULL COMMENT '컨텐츠 카테고리 분류 번호',
  `title` varchar(255) NOT NULL COMMENT '컨텐츠 제목',
  `rmk` text COMMENT '내용',
  `fname` varchar(64) NOT NULL COMMENT '원본 컨텐츠 파일명',
  `ftype` varchar(32) NOT NULL COMMENT '원본 컨텐츠 파일 타입',
  `fsize` bigint(20) NOT NULL COMMENT '원본 컨텐츠 파일 사이즈',
  `regtime` datetime NOT NULL COMMENT '등록시각',
  `updtime` datetime NOT NULL COMMENT '수정시각',
  `view_count` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '조회 수',
  `share_count` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '공유 수',
  `down_count` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '다운 수',
  `buy_count` int(20) unsigned NOT NULL DEFAULT '0' COMMENT '구매 수',
  `redirect_code` varchar(30) DEFAULT NULL COMMENT '숏 URL 코드',
  `orig_status` int(11) DEFAULT '0' COMMENT '원본증명 요청상태, 0:요청 안함, 1:요청함',
  `orig_key` varchar(100) DEFAULT NULL COMMENT '원본증명 반환키',
  `open` varchar(10) NOT NULL DEFAULT 'true' COMMENT '게시글 공개 유무(true:공개, false:미공개)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuid` (`uuid`),
  KEY `mb_id` (`mb_id`),
  KEY `ca_id` (`ca_id`),
  CONSTRAINT `tb_content_ibfk_1` FOREIGN KEY (`mb_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tb_content_ibfk_2` FOREIGN KEY (`ca_id`) REFERENCES `tb_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=508 DEFAULT CHARSET=utf8 COMMENT='컨텐츠 리스트 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_content`
--

LOCK TABLES `tb_content` WRITE;
/*!40000 ALTER TABLE `tb_content` DISABLE KEYS */;
INSERT INTO `tb_content` VALUES (194,'_VWHkxxWR3GRc0yWhJORBw',1,1,'KT입니다','KT입니다','kk.zip','obj',44,'2016-10-19 21:48:56','2016-10-19 21:48:56',37,11,2,0,'131',0,NULL,'true'),(195,'_GHblt7uR06htgpoWD7xNQ',1,1,'girl file','girl file upload','girl.zip','obj',144777,'2016-10-19 23:19:20','2016-10-19 23:19:20',16,1,6,0,'132',0,NULL,'true'),(197,'EPK69ikkRleKXxg6g_uWKA',1,1,'아저씨 캐릭터','아저씨 입니다.','man2.zip','obj',45,'2016-10-19 23:22:14','2016-10-19 23:22:14',8,2,0,0,'134',0,NULL,'true'),(198,'NLjEGoK5TpaBYJk66vnFnw',1,1,'비행기','비행기 모형','plain.zip','obj',50861,'2016-10-19 23:25:19','2016-10-19 23:25:19',44,0,20,14,'135',0,NULL,'true'),(204,'7gKE06o2TbG7qTvoUXJRrQ',1,1,'남성캐릭터','남자입니다','man1.zip','obj',40,'2016-10-20 01:52:44','2016-10-20 01:52:44',32,3,1,22,'142',0,NULL,'true'),(205,'I-D1-A2bTm6dKb5c4UXdmw',1,1,'아저씨','헬레ㅔ레렐','man2.zip','obj',45,'2016-10-22 03:05:42','2016-10-22 03:05:42',20,3,5,0,'143',0,NULL,'true'),(413,'XF1Aq1XmRaur5sEbD1hRBg',7,1,'남자 1','남자 1','man1.zip','obj',40,'2016-11-25 13:14:36','2016-11-25 13:14:36',1,0,0,0,NULL,0,NULL,'false'),(414,'EhXChTfHRV-eeHTXeKIfFA',6,1,'자동차','붕붕붕','car.zip','obj',38,'2016-11-25 13:19:01','2016-11-25 13:19:01',3,0,0,0,NULL,0,NULL,'false'),(418,'LAb1Pd87RwuV-nXSxbKUSQ',6,1,'남자','업로드','man2.zip','obj',45,'2016-11-25 13:40:44','2016-11-25 13:40:44',5,3,1,0,NULL,0,NULL,'true'),(420,'YFlRn28CTLa_wVWPkGQoZw',6,1,'비고개','배고ㅇ개','girl.zip','obj',144777,'2016-11-25 13:46:30','2016-11-25 13:46:30',38,0,4,0,NULL,0,NULL,'false'),(432,'POXK4hWqRli4_cvtImM6vg',12,2,'비행기','비행기 3D','plain.zip','obj',50861,'2016-11-28 12:28:45','2016-11-28 12:28:45',34,11,2,0,NULL,1,'bb95537c776c3eab4fdb2b6462661a9e691034bf8e095cefb6fbc9906f5fd3e2','true'),(435,'HA834WDmR4CoDrrM9JJ4Sw',12,1,'차','차','car.zip','obj',38,'2016-11-28 12:47:16','2016-11-28 12:47:16',12,0,0,0,NULL,0,NULL,'false'),(436,'qJ4DdBHRTJG8lLLWygf4GQ',12,1,'모델_원피스','모델_원피스','model.zip','obj',44,'2016-11-28 13:53:19','2016-12-02 09:27:24',11,0,1,0,NULL,1,'27299a5c50d064e9ade07badfcf418921d1d91b87c8b18a62c42fedf53e79e33','false'),(443,'NIFL92XFQzq6py28UWEm2A',6,2,'변경테스트','테스트','Desktop.zip','obj',144978,'2016-12-02 09:25:26','2016-12-02 11:10:44',43,1,0,0,NULL,1,'d5a780c11569e26157162d78227a622f1d325ce3702c98c7115321631594e48a','true'),(444,'siGgwmHTScKwNAaUVascQQ',12,1,'Man','캐릭터','man2.zip','obj',45,'2016-12-02 10:49:01','2016-12-08 14:08:06',28,1,0,0,NULL,0,NULL,'true'),(446,'inYnoSX8TaylUE9LEa5Y3A',12,1,'여성모델','여성모델','model.zip','obj',44,'2016-12-02 10:52:56','2016-12-02 10:52:56',30,0,1,0,NULL,0,NULL,'true'),(447,'KomOYvEFSSq6UjvIk_riNg',12,1,'비행기','비행기 입니다.','plain.zip','obj',50861,'2016-12-02 10:55:19','2016-12-14 22:44:29',108,4,4,0,NULL,0,NULL,'true'),(454,'Xns-EcMdQOOP2XOVh7R5xA',6,1,'Standard OBJ','OBJ only','OCP3.obj','obj',3318786,'2016-12-07 17:25:24','2016-12-14 18:13:08',24,0,0,0,NULL,0,'644f48019051aa68b06610b0ab2c0e99f28a48ed496a1b6ebe98bb37fe6c81da','true'),(465,'f9UmJRDtREeg0o7CKE-fng',22,2,'차체 프레임','승용차량 3D','car.zip','obj',38,'2016-12-08 15:42:58','2016-12-15 10:37:24',26,1,0,0,NULL,0,NULL,'true'),(475,'yG_V2czmQGWNSXAT3q7Lvw',12,1,'아이언맨','마블 히어로','IronMan.zip','obj',9368808,'2016-12-14 11:18:28','2016-12-14 20:40:08',40,0,1,0,NULL,1,'4fc38960111e1df845bf65dcbe74d63c50a590e160b6138ddb7185d3b6cb03b8','true'),(481,'c_1QztOxRJq_N--7Wo_AkA',12,2,'Car','Car','car.zip','obj',38,'2016-12-14 14:07:38','2016-12-14 23:01:04',48,2,1,0,NULL,0,'911e69068560996c6efbb5910bd0ba4b09b045bc5211b86d64f95af58ad5d64d','true'),(496,'A23TzkEYRXCxAXx4xUyt1w',22,1,'원피스','여성캐릭터','girl.zip','obj',144777,'2016-12-14 19:53:09','2016-12-14 19:53:09',4,0,0,0,NULL,0,NULL,'false'),(504,'KWJoIZHxSiyloVMOxZLd_w',12,2,'Teddy','','teddy.obj','obj',91538,'2016-12-16 13:39:10','2016-12-16 13:39:10',0,0,0,0,NULL,0,NULL,'true'),(505,'uv8aJCRLTt-dzQYAvvZY6w',12,2,'Tea','','teapot.obj','obj',210614,'2016-12-16 13:41:10','2016-12-16 13:41:10',2,0,0,0,NULL,0,NULL,'true'),(506,'fq4tQYEMQY6ByyVWmu1fJw',12,2,'송아지','','cow-nonormals.obj','obj',232997,'2016-12-16 13:42:41','2016-12-16 13:42:41',0,0,0,0,NULL,0,NULL,'true'),(507,'k_u15Hz6Spe4lVSm3CUWHg',12,2,'호박3D','','pumpkin_tall_10k.obj','obj',333135,'2016-12-16 13:43:38','2016-12-16 13:59:03',2,0,0,0,NULL,0,NULL,'true');
/*!40000 ALTER TABLE `tb_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_content_json`
--

DROP TABLE IF EXISTS `tb_content_json`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_content_json` (
  `co_id` bigint(20) NOT NULL COMMENT '컨텐츠 ID',
  `json` longtext NOT NULL COMMENT '컨텐츠 json 스트링',
  PRIMARY KEY (`co_id`),
  CONSTRAINT `tb_content_json_ibfk_1` FOREIGN KEY (`co_id`) REFERENCES `tb_content` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='컨텐츠 리스트 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_content_json`
--

LOCK TABLES `tb_content_json` WRITE;
/*!40000 ALTER TABLE `tb_content_json` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_content_json` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_content_payment`
--

DROP TABLE IF EXISTS `tb_content_payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_content_payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 번호',
  `co_id` bigint(20) NOT NULL COMMENT '컨텐츠 ID',
  `mb_id` bigint(20) NOT NULL COMMENT '회원아이디',
  `price` int(11) NOT NULL DEFAULT '0' COMMENT '구매가격',
  `regtime` datetime NOT NULL COMMENT '등록시각',
  PRIMARY KEY (`id`),
  KEY `co_id` (`co_id`),
  KEY `mb_id` (`mb_id`),
  CONSTRAINT `tb_content_payment_ibfk_1` FOREIGN KEY (`co_id`) REFERENCES `tb_content` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tb_content_payment_ibfk_2` FOREIGN KEY (`mb_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='컨텐츠 구매정보 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_content_payment`
--

LOCK TABLES `tb_content_payment` WRITE;
/*!40000 ALTER TABLE `tb_content_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_content_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_content_post`
--

DROP TABLE IF EXISTS `tb_content_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_content_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 번호',
  `mb_id` bigint(20) NOT NULL COMMENT '회원아이디',
  `co_id` bigint(20) NOT NULL COMMENT '컨텐츠 ID',
  `rmk` text COMMENT '컨텐츠 댓글 내용',
  `regtime` datetime NOT NULL COMMENT '등록시각',
  PRIMARY KEY (`id`),
  KEY `co_id` (`co_id`),
  KEY `mb_id` (`mb_id`),
  CONSTRAINT `tb_content_post_ibfk_1` FOREIGN KEY (`co_id`) REFERENCES `tb_content` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tb_content_post_ibfk_2` FOREIGN KEY (`mb_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='컨텐츠 댓글 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_content_post`
--

LOCK TABLES `tb_content_post` WRITE;
/*!40000 ALTER TABLE `tb_content_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_content_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_content_share`
--

DROP TABLE IF EXISTS `tb_content_share`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_content_share` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 번호',
  `mb_id` bigint(20) NOT NULL COMMENT '회원아이디',
  `ca_id` bigint(20) NOT NULL COMMENT '컨텐츠 ID',
  `type` int(11) NOT NULL COMMENT '공유타입(1:공유,2:임베디드,3:다운로드)',
  `url` varchar(300) DEFAULT NULL COMMENT '공유위치',
  `regtime` datetime NOT NULL COMMENT '등록시각',
  PRIMARY KEY (`id`),
  KEY `co_id` (`ca_id`),
  KEY `mb_id` (`mb_id`),
  CONSTRAINT `tb_content_share_ibfk_1` FOREIGN KEY (`ca_id`) REFERENCES `tb_content` (`id`) ON DELETE CASCADE,
  CONSTRAINT `tb_content_share_ibfk_2` FOREIGN KEY (`mb_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8 COMMENT='컨텐츠 공유 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_content_share`
--

LOCK TABLES `tb_content_share` WRITE;
/*!40000 ALTER TABLE `tb_content_share` DISABLE KEYS */;
INSERT INTO `tb_content_share` VALUES (88,6,205,1,'Twitter','2016-11-25 13:16:25'),(91,6,418,3,NULL,'2016-11-25 13:41:57'),(92,6,418,1,'FaceBook','2016-11-25 13:43:19'),(93,6,418,1,'FaceBook','2016-11-25 13:44:14'),(98,6,418,1,'FaceBook','2016-11-25 15:48:34'),(103,12,198,3,NULL,'2016-11-28 12:28:18'),(108,12,432,2,'Embedded_Tag','2016-11-28 14:25:01'),(109,12,195,3,NULL,'2016-11-28 14:31:21'),(110,12,432,3,NULL,'2016-11-29 13:15:12'),(114,6,443,1,'FaceBook','2016-12-02 17:26:15'),(147,6,420,3,NULL,'2016-12-07 16:12:39'),(148,6,420,3,NULL,'2016-12-07 16:12:51'),(149,6,420,3,NULL,'2016-12-07 16:12:55'),(151,12,447,2,'Embedded_Tag','2016-12-07 23:41:23'),(152,12,447,3,NULL,'2016-12-07 23:41:26'),(153,12,432,3,NULL,'2016-12-08 00:21:12'),(154,12,432,1,'FaceBook','2016-12-08 01:00:25'),(155,12,432,1,'FaceBook','2016-12-08 01:01:08'),(156,12,432,1,'Twitter','2016-12-08 01:01:29'),(157,12,432,1,'Twitter','2016-12-08 01:02:18'),(158,12,432,1,'FaceBook','2016-12-08 01:03:01'),(164,12,447,3,NULL,'2016-12-08 13:41:20'),(166,22,447,3,NULL,'2016-12-08 14:17:27'),(167,12,432,1,'FaceBook','2016-12-08 14:20:20'),(168,12,432,1,'FaceBook','2016-12-08 14:21:20'),(169,12,432,1,'Twitter','2016-12-08 14:26:20'),(170,12,436,3,NULL,'2016-12-08 14:41:19'),(171,12,465,1,'Twitter','2016-12-08 15:44:33'),(175,22,447,1,'FaceBook','2016-12-13 09:38:25'),(176,22,447,1,'FaceBook','2016-12-13 09:42:13'),(178,12,444,1,'FaceBook','2016-12-13 14:14:56'),(179,12,432,1,'FaceBook','2016-12-13 16:40:15'),(180,12,432,1,'FaceBook','2016-12-13 16:40:40'),(189,6,475,3,NULL,'2016-12-14 18:04:16'),(190,22,481,1,'FaceBook','2016-12-14 19:38:49'),(191,22,481,3,NULL,'2016-12-14 19:40:10'),(192,12,204,3,NULL,'2016-12-14 19:43:32'),(193,22,195,3,NULL,'2016-12-14 19:51:05'),(196,12,447,2,'Embedded_Tag','2016-12-14 21:18:14'),(197,22,447,3,NULL,'2016-12-15 10:58:10'),(200,22,481,1,'FaceBook','2016-12-15 13:09:30'),(201,12,194,3,NULL,'2016-12-16 14:05:48'),(202,12,446,3,NULL,'2016-12-16 14:15:18');
/*!40000 ALTER TABLE `tb_content_share` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_content_share_tracking`
--

DROP TABLE IF EXISTS `tb_content_share_tracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_content_share_tracking` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 id',
  `share_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '공유테이블 id',
  `ip` varchar(16) NOT NULL DEFAULT '0' COMMENT '접속자 ip',
  `port` varchar(10) NOT NULL DEFAULT '0' COMMENT '접속자 port',
  `regtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '접속 시간',
  PRIMARY KEY (`id`),
  KEY `tb_content_share_tracking` (`share_id`),
  CONSTRAINT `tb_content_share_tracking` FOREIGN KEY (`share_id`) REFERENCES `tb_content_share` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8 COMMENT='공유 컨텐츠 추적 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_content_share_tracking`
--

LOCK TABLES `tb_content_share_tracking` WRITE;
/*!40000 ALTER TABLE `tb_content_share_tracking` DISABLE KEYS */;
INSERT INTO `tb_content_share_tracking` VALUES (37,92,'175.209.192.76','20079','2016-11-25 13:44:34'),(38,93,'175.209.192.76','20079','2016-11-25 13:44:34'),(50,154,'183.98.5.173','2269','2016-12-08 01:03:57'),(51,155,'183.98.5.173','2269','2016-12-08 01:03:57'),(52,158,'183.98.5.173','2269','2016-12-08 01:03:57'),(53,154,'66.220.145.244','48968','2016-12-08 01:03:58'),(54,155,'66.220.145.244','48968','2016-12-08 01:03:58'),(55,158,'66.220.145.244','48968','2016-12-08 01:03:58'),(60,156,'175.223.22.61','8433','2016-12-08 14:29:15'),(61,157,'175.223.22.61','8433','2016-12-08 14:29:15'),(62,169,'175.223.22.61','8433','2016-12-08 14:29:15'),(63,171,'175.223.22.61','29551','2016-12-08 15:45:32'),(65,175,'221.148.76.88','58572','2016-12-13 09:43:27'),(66,176,'221.148.76.88','58572','2016-12-13 09:43:27'),(67,175,'221.148.76.88','58572','2016-12-13 09:43:39'),(68,176,'221.148.76.88','58572','2016-12-13 09:43:39'),(69,175,'221.148.76.88','58572','2016-12-13 09:44:26'),(70,176,'221.148.76.88','58572','2016-12-13 09:44:26'),(71,175,'221.148.76.88','58615','2016-12-13 09:46:02'),(72,176,'221.148.76.88','58615','2016-12-13 09:46:02'),(79,175,'221.148.76.88','59214','2016-12-13 14:18:23'),(80,176,'221.148.76.88','59214','2016-12-13 14:18:23'),(81,175,'221.148.76.88','59214','2016-12-13 14:18:28'),(82,176,'221.148.76.88','59214','2016-12-13 14:18:28'),(83,175,'66.220.145.243','61732','2016-12-13 14:18:47'),(84,176,'66.220.145.243','61732','2016-12-13 14:18:47'),(85,175,'221.148.76.88','59214','2016-12-13 14:19:24'),(86,176,'221.148.76.88','59214','2016-12-13 14:19:24'),(87,154,'39.7.56.86','24884','2016-12-13 16:41:43'),(88,155,'39.7.56.86','24884','2016-12-13 16:41:43'),(89,158,'39.7.56.86','24884','2016-12-13 16:41:43'),(90,167,'39.7.56.86','24884','2016-12-13 16:41:43'),(91,168,'39.7.56.86','24884','2016-12-13 16:41:43'),(92,179,'39.7.56.86','24884','2016-12-13 16:41:43'),(93,180,'39.7.56.86','24884','2016-12-13 16:41:43'),(103,190,'112.217.210.202','59143','2016-12-15 13:10:15'),(104,200,'112.217.210.202','59143','2016-12-15 13:10:15');
/*!40000 ALTER TABLE `tb_content_share_tracking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_goods`
--

DROP TABLE IF EXISTS `tb_goods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 id',
  `store_id` bigint(20) NOT NULL COMMENT '매장 id',
  `name` varchar(255) NOT NULL COMMENT '상품명',
  `eng_name` varchar(255) NOT NULL COMMENT '영문 상품명',
  `ca_id` bigint(20) NOT NULL COMMENT '상품 카테고리 ID',
  `price` double NOT NULL COMMENT '가격',
  `img_id` bigint(20) NOT NULL COMMENT '이미지 ID',
  PRIMARY KEY (`id`),
  KEY `store_id` (`store_id`),
  KEY `ca_id` (`ca_id`),
  CONSTRAINT `tb_goods_ibfk_1` FOREIGN KEY (`ca_id`) REFERENCES `tb_goods_category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `tb_goods_ibfk_2` FOREIGN KEY (`store_id`) REFERENCES `tb_store` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='상품 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_goods`
--

LOCK TABLES `tb_goods` WRITE;
/*!40000 ALTER TABLE `tb_goods` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_goods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_goods_category`
--

DROP TABLE IF EXISTS `tb_goods_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_goods_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 id',
  `name` varchar(255) NOT NULL COMMENT '상품 카테고리 명',
  `subname` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`subname`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='상품 카테고리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_goods_category`
--

LOCK TABLES `tb_goods_category` WRITE;
/*!40000 ALTER TABLE `tb_goods_category` DISABLE KEYS */;
INSERT INTO `tb_goods_category` VALUES (9,'원두/기타','Beans'),(12,'원두/기타','etc'),(10,'원두/기타','Mug Cup'),(11,'원두/기타','Tumbler'),(1,'음료/Coffee','Coffee'),(4,'음료/Coffee','etc'),(3,'음료/Coffee','Frappucchino'),(2,'음료/Coffee','Latte'),(5,'푸드/Food','Bakery'),(6,'푸드/Food','Cake'),(8,'푸드/Food','etc'),(7,'푸드/Food','Sandwich');
/*!40000 ALTER TABLE `tb_goods_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_image`
--

DROP TABLE IF EXISTS `tb_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 id',
  `share` int(11) NOT NULL DEFAULT '1' COMMENT '0: 공유안함, 1: 공유함',
  `user_id` bigint(20) NOT NULL COMMENT '등록 사용자 ID',
  `fname` varchar(128) NOT NULL COMMENT '이미지 파일 명',
  `uuid` varchar(128) NOT NULL COMMENT '이미지 uuid 명',
  `yyyymm` varchar(6) NOT NULL COMMENT '년월',
  `regtime` datetime NOT NULL COMMENT '등록시각',
  `updtime` datetime NOT NULL COMMENT '수정시각',
  `ext` varchar(8) NOT NULL COMMENT '이미지 파일 확장자',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='이미지 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_image`
--

LOCK TABLES `tb_image` WRITE;
/*!40000 ALTER TABLE `tb_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_last_email`
--

DROP TABLE IF EXISTS `tb_last_email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_last_email` (
  `last_time` bigint(20) DEFAULT NULL COMMENT '마지막 메일 수신 시각',
  `last_hash` char(32) DEFAULT NULL COMMENT '마지막 메일 제목 해쉬값'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='마지막 이메일 정보 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_last_email`
--

LOCK TABLES `tb_last_email` WRITE;
/*!40000 ALTER TABLE `tb_last_email` DISABLE KEYS */;
INSERT INTO `tb_last_email` VALUES (1481612590000,'fac8980dd29c43e4e2f5a079556585c3');
/*!40000 ALTER TABLE `tb_last_email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_menu`
--

DROP TABLE IF EXISTS `tb_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_menu` (
  `idx` int(11) NOT NULL AUTO_INCREMENT COMMENT '메뉴 고유 인덱스 번호',
  `code` varchar(255) NOT NULL DEFAULT '' COMMENT '메뉴 코드',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '메뉴 명',
  `link` varchar(255) NOT NULL DEFAULT '' COMMENT '메뉴 링크 URL',
  `target` varchar(255) NOT NULL DEFAULT '' COMMENT '메뉴 타',
  `order` int(11) NOT NULL DEFAULT '0' COMMENT '메뉴 순서',
  `use` tinyint(4) NOT NULL DEFAULT '0' COMMENT '메뉴 사용',
  `mobile_use` tinyint(4) NOT NULL DEFAULT '0' COMMENT '메뉴 모바일 사용',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_menu`
--

LOCK TABLES `tb_menu` WRITE;
/*!40000 ALTER TABLE `tb_menu` DISABLE KEYS */;
INSERT INTO `tb_menu` VALUES (1,'10','컨텐츠 게시판','/main.do','self',1,1,1),(2,'1010','전체 컨텐츠','/main.do','self',0,1,1),(3,'1020','사람 컨텐츠','/main.do','self',0,1,1),(4,'1030','사물 컨텐츠','/main.do','self',0,1,1),(5,'20','컨텐츠 관리','/main.do','self',2,1,1),(6,'2010','생산 컨텐츠','/main.do','self',0,1,1),(7,'2020','구매 컨텐츠','/main.do','self',0,1,1),(8,'2030','판매 내역','/main.do','self',0,1,1),(9,'2040','공유이력','/main.do','self',0,1,1),(10,'30','컨텐츠 업로드','/main.do','self',3,1,1),(11,'40','프로필 관리','/main.do','self',4,1,1),(12,'4010','프로필 보기','/main.do','self',0,1,1),(13,'4020','프로필 수정','/main.do','self',0,1,1),(14,'4030','HELP&FAQ','/main.do','self',0,1,1),(15,'4040','회원 탈퇴','/main.do','self',0,1,1);
/*!40000 ALTER TABLE `tb_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_store`
--

DROP TABLE IF EXISTS `tb_store`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_store` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 id',
  `name` varchar(128) NOT NULL COMMENT '매장명',
  `address` varchar(255) NOT NULL COMMENT '매장 주소',
  `lat` double NOT NULL COMMENT '위도',
  `lon` double NOT NULL COMMENT '경도',
  `user_id` bigint(20) NOT NULL COMMENT '매장 등록 사용자 ID',
  `ca_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '매장 카테고리 ID',
  `phone` varchar(24) NOT NULL COMMENT '매장 전화번호',
  `description` varchar(1024) NOT NULL COMMENT '매장 설명',
  `open_time` varchar(1024) NOT NULL COMMENT '매장  영업시간',
  `closed_day` varchar(1024) NOT NULL COMMENT '매장   휴무일',
  `regtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록시각',
  `updtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '수정시각',
  `image_id1` bigint(20) NOT NULL COMMENT '이미지 id1',
  `image_id2` bigint(20) NOT NULL COMMENT '이미지 id2',
  `image_id3` bigint(20) NOT NULL COMMENT '이미지 id3',
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tb_store_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='매장 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_store`
--

LOCK TABLES `tb_store` WRITE;
/*!40000 ALTER TABLE `tb_store` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_store` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_store_category`
--

DROP TABLE IF EXISTS `tb_store_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_store_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 id',
  `name` varchar(255) NOT NULL COMMENT '매장 카테고리 명',
  `subname` varchar(255) NOT NULL COMMENT '매장 서브 카테고리 명',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`,`subname`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='매장 카테고리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_store_category`
--

LOCK TABLES `tb_store_category` WRITE;
/*!40000 ALTER TABLE `tb_store_category` DISABLE KEYS */;
INSERT INTO `tb_store_category` VALUES (3,'빵집','Bakery'),(2,'찻집','찻집'),(1,'커피숍','Caffe');
/*!40000 ALTER TABLE `tb_store_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_store_event`
--

DROP TABLE IF EXISTS `tb_store_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_store_event` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 id',
  `store_id` bigint(20) NOT NULL COMMENT '매장 id',
  `img_id` bigint(20) NOT NULL COMMENT '이벤트 이미지 id',
  `title` varchar(255) NOT NULL COMMENT '이벤트 제목',
  `description` varchar(1024) NOT NULL COMMENT '이벤트 내용',
  `starttime` datetime NOT NULL COMMENT '이벤트 시작 시각',
  `endtime` datetime NOT NULL COMMENT '이벤트 종료 시각',
  PRIMARY KEY (`id`),
  KEY `store_id` (`store_id`),
  CONSTRAINT `tb_store_event_ibfk_1` FOREIGN KEY (`store_id`) REFERENCES `tb_store` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='매장 이벤트 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_store_event`
--

LOCK TABLES `tb_store_event` WRITE;
/*!40000 ALTER TABLE `tb_store_event` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_store_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_store_image`
--

DROP TABLE IF EXISTS `tb_store_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_store_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 id',
  `store_id` bigint(20) NOT NULL COMMENT '매장 id',
  `img_id` bigint(20) NOT NULL COMMENT '매장 id',
  PRIMARY KEY (`id`),
  KEY `store_id` (`store_id`),
  CONSTRAINT `tb_store_image_ibfk_1` FOREIGN KEY (`store_id`) REFERENCES `tb_store` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='매장 이미지 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_store_image`
--

LOCK TABLES `tb_store_image` WRITE;
/*!40000 ALTER TABLE `tb_store_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_store_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '시퀀스 번호',
  `email` varchar(120) NOT NULL COMMENT '회원아이디 email',
  `level` int(11) NOT NULL DEFAULT '1' COMMENT '멤버타입(1:일반,2:제휴,10:관리자)',
  `name` varchar(32) NOT NULL COMMENT '회원이름',
  `password` varchar(255) NOT NULL COMMENT '비밀번호',
  `tel` varchar(18) DEFAULT NULL COMMENT '전화번호',
  `birth` varchar(10) DEFAULT NULL COMMENT '생년월일',
  `is_email_auth` int(11) NOT NULL DEFAULT '0' COMMENT '이메일인증여부',
  `email_key` varchar(64) NOT NULL,
  `is_avail` int(11) NOT NULL DEFAULT '1' COMMENT ' 멤버상태(1:정상, 9:탈퇴)',
  `regtime` datetime NOT NULL COMMENT '등록시각',
  `updtime` datetime NOT NULL COMMENT '수정시각',
  `access_token` varchar(64) DEFAULT NULL COMMENT '접근 허가 토근',
  `access_time` datetime DEFAULT NULL COMMENT '접근 시각',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `email_key` (`email_key`),
  UNIQUE KEY `access_time` (`access_time`),
  UNIQUE KEY `access_token` (`access_token`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='멤버 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,'mgkaki@youngplussoft.com',2,'강명구','18138372fad4b94533cd4881f03dc6c69296dd897234e0cee83f727e2e6b1f63','010-9990-2251','690620',1,'8LmFDzolSEGzVubWJv7giA',1,'2016-09-17 12:43:53','2016-09-17 12:48:32','94829e1e3a590a5aec751eafae20fa52caa173e13d21ccffd219c17a0e7d831f','2017-08-02 17:22:20'),(2,'test@naver.com',0,'test','*94BDCEBE19083CE2A1F959FD02F964C7AF4CFC29','01053265311','910118',0,'erUjm5wsTvyk_IthDmpPcQ',1,'2016-09-23 10:42:16','2016-09-23 10:42:16',NULL,NULL),(6,'wndrlf2003@hanmail.net',1,'이중길','1918313ae4bda5d347afe0dbfa3179ebac4f0b4c74ae33f1013011b5a7d0011b',NULL,NULL,1,'668ca340f890b0ca3d7a2f2263b7969889d7d7a18c477f5263d3e76b10833f79',1,'2016-12-08 02:07:02','2016-12-08 02:07:02','57c24d22eec35f9e23201b23451d0d97752320434fd9bc654f75f4c32d2d68c0','2016-12-22 10:01:00'),(7,'mgkaki@naver.com',1,'강명구','18138372fad4b94533cd4881f03dc6c69296dd897234e0cee83f727e2e6b1f63',NULL,NULL,1,'76e4bf01604510f79f8c6fa60623071f7919076180645aa69dbeea2d695129fc',1,'2016-11-10 23:55:18','2016-11-10 23:55:18','f02092374da94b0a566927f19e58c02115646104be0ec1825ce3dd671e985f76','2016-12-26 17:19:40'),(12,'wylee7@gmail.com',1,'이원영','8053550ed34312e8e4f95affff19762d676c92fe91ca49fe491ed67452611fda',NULL,NULL,1,'650eeadbdb833af885167f38483d3d55a3bb7f541a32e953693bb7f66f9705ba',1,'2016-11-17 15:46:19','2016-11-17 15:46:19','8d63bd1d06ea78d69f180d6cd1007532bd4377566e4ae62e178e54c7b5f999ee','2016-12-20 16:41:09'),(13,'mgkaki@daum.net',1,'강명구','18138372fad4b94533cd4881f03dc6c69296dd897234e0cee83f727e2e6b1f63',NULL,NULL,1,'2f999a0c495e57782dd952d718e70e1e694b883497d1aa812eceb3a85c4218cb',1,'2016-11-23 20:49:29','2016-11-23 20:49:29','fd8a81b33e63c536dcd9906ca42f3abc3ab25972fec8a23028dc2b4778190c13','2016-11-23 21:41:59'),(19,'jjm_test@naver.com',1,'장재민','01f8de4ebf4bcce7fc7f50ad0ed40f4ece780897b5c88a65875ab895654cdc67',NULL,NULL,0,'df66799be8c9e3c2447d2fa55ca766e43817f7ef335d6368a65cc0a95b990560',0,'2016-12-05 16:50:01','2016-12-05 16:50:01',NULL,NULL),(20,'jjm@yally.com',1,'장재민','01f8de4ebf4bcce7fc7f50ad0ed40f4ece780897b5c88a65875ab895654cdc67',NULL,NULL,1,'c46c88ca56a8e0b1b8d51f9a45979e1bd0eb71216e42b183dfb1e0709258bdf1',1,'2016-12-05 16:51:11','2016-12-05 16:51:11','27c1eddd5a0c11621c975819fa1ad66930695d629540cc578e292b61260ed8e3','2016-12-14 20:39:48'),(21,'admin@real3d.com',3,'관리자','97ea69567c3888edd524f24f6ef344822b95943b4b80f2d7f4cb1b0efcfc1b76','01053265311','910118',1,'5d72612478889c215b531a25a4160e32ae4016dfa28939bdcc0e670364a74d38',1,'2016-12-14 02:36:30','2016-12-14 02:36:30','781a2d93c5f89688ec613c4b34107612f578d6bd513ea4d5d47f9f63cda2f8e8','2016-12-15 10:29:54'),(22,'wylee73@gmail.com',1,'이원영','8053550ed34312e8e4f95affff19762d676c92fe91ca49fe491ed67452611fda',NULL,NULL,1,'86ac6a37162b83c46edd66517fba7c9876a1eefb42799036ec415a440357ecd0',1,'2016-12-08 13:30:33','2016-12-08 13:30:33','8eed47fae9decb837aab3f3f37358ab654dcc351ff65837eb200a3633d840675','2016-12-15 14:29:15'),(24,'kt.in@kt.com',1,'김기현','363754fc0f793cdefb9edab2852fa8c872af47894b55aaa64ca8d0a486680bf1',NULL,NULL,0,'39754fc1cd796bb427f0ed3c1df31b12aa5c238f119f67b401b9f93821e9ce98',0,'2016-12-13 16:03:20','2016-12-13 16:03:20',NULL,NULL);
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-18 21:19:22
